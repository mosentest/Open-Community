package org.mu.cloudservice.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.bson.types.ObjectId;
import org.mu.opencomm.code.entity.Category;
import org.mu.opencomm.code.entity.FileInfo;
import org.mu.opencomm.code.entity.JavaFile;
import org.mu.opencomm.code.entity.JavaLibrary;
import org.mu.opencomm.code.entity.Tag;
import org.mu.opencomm.code.repository.CategoryRepository;
import org.mu.opencomm.code.repository.JavaFileRepository;
import org.mu.opencomm.code.repository.JavaLibraryRDRepository;
import org.mu.opencomm.code.repository.JavaLibraryRepository;
import org.mu.opencomm.code.repository.StatRepository;
import org.mu.opencomm.code.repository.TagRepository;
import org.mu.opencomm.common.util.DateUtil;
import org.mu.opencomm.common.util.FileUtil;
import org.mu.opencomm.common.util.LibraryAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;

@Service("testService")
public class TestService {

	@Autowired
	private StatRepository statRepository;

	@Autowired
	private JavaLibraryRepository javaLibraryRepository;

	@Autowired
	private JavaLibraryRDRepository javaLibraryRDRepository;

	@Autowired
	private JavaFileRepository javaFileRepository;

	@Autowired
	private TagRepository tagRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	public void loadJar() {
		List<Category> categories = categoryRepository.findByType("java");
		List<Tag> tags = tagRepository.findByType("java");
		javaLibraryRepository.delete(new BasicDBObject());
		javaFileRepository.delete(new BasicDBObject());
		File base = new File("C:\\Users\\Muu\\.m2\\repository\\org");
		List<File> files = new ArrayList<>();
		getFiles(files, base);
		Random random = new Random();
		for (File file : files) {
			try {
				JavaLibrary javaLibrary = LibraryAnalyzer.parseJarFile(file.getAbsolutePath());
				javaLibrary.setDescription("Description for java library :" + javaLibrary.getName());
				javaLibrary.setCreate(new Date());
				javaLibrary.setCategories(new String[] { 
						categories.get(random.nextInt(categories.size())).getCategory(),
						categories.get(random.nextInt(categories.size())).getCategory()});
				javaLibrary.setTags(new String[] { 
						tags.get(random.nextInt(tags.size())).getTag(),
						tags.get(random.nextInt(tags.size())).getTag(),
						tags.get(random.nextInt(tags.size())).getTag()});
				List<JavaFile> libraryFiles = null;
				FileUtil.saveLibrary(file, file.getName());
				File sourceFile = new File(file.getParent() + "\\" + javaLibrary.getName() + "-sources.jar");
				File docFile = new File(file.getParent() + "\\" + javaLibrary.getName() + "-javadoc.jar");
				if (sourceFile.exists()) {
					javaLibrary.setSource(sourceFile.getName());
					libraryFiles = LibraryAnalyzer.parseSourceFile(sourceFile.getAbsolutePath(), javaLibrary.getName(), false);
					FileUtil.saveLibrarySource(sourceFile, sourceFile.getName());
				}
				if (docFile.exists()) {
					javaLibrary.setJavadoc(docFile.getName());
					FileUtil.saveLibraryDoc(docFile, docFile.getName());
				}
				ObjectId id = javaLibraryRepository.save(javaLibrary);
				javaLibrary.setId(id);
				if (libraryFiles != null) {
					for (JavaFile jFile : libraryFiles) {
						jFile.setCreate(new Date());
						jFile.setLibrary(javaLibrary.getName());
						javaFileRepository.save(jFile);
					}
				}
				for (JavaFile jFile : javaLibrary.getJavaFiles()) {
					jFile.setCreate(new Date());
					jFile.setLibrary(javaLibrary.getName());
					javaFileRepository.save(jFile);
				}
				StringBuilder buffer1 = new StringBuilder();
				StringBuilder buffer2 = new StringBuilder();
				saveFiles(javaLibrary.getStructure(), buffer1, buffer2);
				javaLibraryRDRepository.associateClass(javaLibrary.getId().toHexString(), buffer1.toString());
				javaLibraryRDRepository.associateClass(javaLibrary.getId().toHexString(), buffer2.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void saveFiles(List<FileInfo> files, StringBuilder buffer1, StringBuilder buffer2) {
		for (FileInfo file : files) {
			if (file.isDir()) {
				saveFiles(file.getFiles(), buffer1, buffer2);
			} else {
				if (file.getExtension().equals("class")) {
					if (file.getName().matches("^([a-nA-N]).*")) {
						buffer1.append(file.getName().toLowerCase());
						buffer1.append(";");
					} else {
						buffer2.append(file.getName().toLowerCase());
						buffer2.append(";");
					}
				}
			}
		}
	}
	
	private static void getFiles(List<File> list, File dir) {
		for (File file : dir.listFiles()) {
			if (file.isDirectory()) {
				getFiles(list, file);
			}
			if (isJar(file)) {
				list.add(file);
			}
		}
	}
	
	private static boolean isJar(File file) {
		return !file.getName().endsWith("sources.jar") &&
				 !file.getName().endsWith("javadoc.jar") &&
				 file.getName().endsWith("jar");
	}

	public void addDownloadStats() {
		Date start = DateUtil.getDate("2013-01-01", "yyyy-MM-dd");
		Date end = DateUtil.getDate("2014-09-18", "yyyy-MM-dd");
		List<Date> dates = DateUtil.getDatesBetween(start, end);
		Random random = new Random();
		long base = random.nextInt(30) + 50;
		int reset = 30;
		for (Date date : dates) {
			base = base + (-5 + random.nextInt(12));
			statRepository.set("5419c38665e4eb20bfa14163", base, date);
			if (reset-- == 0) {
				reset = 30;
				base = random.nextInt(30) + 50;				
			}
		}
	}

	public void setStatRepository(StatRepository statRepository) {
		this.statRepository = statRepository;
	}

	public void setJavaLibraryRepository(JavaLibraryRepository javaLibraryRepository) {
		this.javaLibraryRepository = javaLibraryRepository;
	}

	public void setJavaLibraryRDRepository(
			JavaLibraryRDRepository javaLibraryRDRepository) {
		this.javaLibraryRDRepository = javaLibraryRDRepository;
	}

	public void setJavaFileRepository(JavaFileRepository javaFileRepository) {
		this.javaFileRepository = javaFileRepository;
	}

	public void setTagRepository(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}

	public void setCategoryRepository(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
}
