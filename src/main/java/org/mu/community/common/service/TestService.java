package org.mu.community.common.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.bson.types.ObjectId;
import org.mu.community.blog.entity.Blog;
import org.mu.community.blog.entity.BlogCategory;
import org.mu.community.blog.entity.BlogType;
import org.mu.community.blog.repository.BlogModifyRepository;
import org.mu.community.blog.repository.BlogRepository;
import org.mu.community.code.entity.Category;
import org.mu.community.code.entity.FileInfo;
import org.mu.community.code.entity.JavaFile;
import org.mu.community.code.entity.JavaLibrary;
import org.mu.community.code.entity.Tag;
import org.mu.community.code.repository.CategoryRepository;
import org.mu.community.code.repository.JavaFileRepository;
import org.mu.community.code.repository.JavaLibraryRDRepository;
import org.mu.community.code.repository.JavaLibraryRepository;
import org.mu.community.code.repository.StatRepository;
import org.mu.community.code.repository.TagRepository;
import org.mu.community.common.entity.User;
import org.mu.community.common.util.DateUtil;
import org.mu.community.common.util.FileUtil;
import org.mu.community.common.util.LibraryAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private BlogModifyRepository blogModifyRepository;

    @Transactional
    public void newBlogCategories() {
        BlogCategory c1 = new BlogCategory();
        c1.setName("Java");
        c1.setUser(1l);
        BlogCategory c2 = new BlogCategory();
        c2.setName("C++");
        c2.setUser(1l);
        BlogCategory c3 = new BlogCategory();
        c3.setName("Redis");
        c3.setUser(1l);
        BlogCategory c4 = new BlogCategory();
        c4.setName("Data structure");
        c4.setUser(1l);
        BlogCategory c5 = new BlogCategory();
        c5.setName("Functional Programming");
        c5.setUser(1l);
        BlogCategory c6 = new BlogCategory();
        c6.setName("Erlang");
        c6.setUser(1l);

        blogModifyRepository.createCategory(c1);
        blogModifyRepository.createCategory(c2);
        blogModifyRepository.createCategory(c3);
        blogModifyRepository.createCategory(c4);
        blogModifyRepository.createCategory(c5);
        blogModifyRepository.createCategory(c6);
    }

    @Transactional
    public void newBlogs() {
        User user = new User(1l);
        List<BlogCategory> categories = blogRepository.getCategories(user.getId());
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            Blog blog = new Blog();
            blog.setCreate(DateUtil.getRandomDateInRange("2013-10-01", "2014-09-25"));
            blog.setUser(user);
            blog.setType(BlogType.values()[random.nextInt(BlogType.values().length)]);
            blog.setCategory(categories.get(random.nextInt(categories.size())));
            blog.setSummary("Summary for blog number: " + i);
            blog.setTitle("Title for blog number: " + i);
            blog.setContent("Content for blog number: " + i);
            blogModifyRepository.createBlog(blog);
        }
    }
	
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

    public StatRepository getStatRepository() {
        return statRepository;
    }

    public JavaLibraryRepository getJavaLibraryRepository() {
        return javaLibraryRepository;
    }

    public JavaLibraryRDRepository getJavaLibraryRDRepository() {
        return javaLibraryRDRepository;
    }

    public JavaFileRepository getJavaFileRepository() {
        return javaFileRepository;
    }

    public TagRepository getTagRepository() {
        return tagRepository;
    }

    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }

    public BlogRepository getBlogRepository() {
        return blogRepository;
    }

    public void setBlogRepository(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public BlogModifyRepository getBlogModifyRepository() {
        return blogModifyRepository;
    }

    public void setBlogModifyRepository(BlogModifyRepository blogModifyRepository) {
        this.blogModifyRepository = blogModifyRepository;
    }
}
