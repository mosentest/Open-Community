package org.mu.community.code.service;

import java.util.List;

import org.mu.community.code.entity.JavaFile;
import org.mu.community.code.entity.JavaLibrary;
import org.mu.community.code.repository.JavaFileRepository;
import org.mu.community.code.repository.JavaLibraryRDRepository;
import org.mu.community.code.repository.JavaLibraryRepository;
import org.mu.community.common.dbutil.Page;
import org.mu.community.common.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("javaLibraryService")
public class JavaLibraryService {

	private JavaLibraryRepository javaLibraryRepository;

	private JavaFileRepository javaFileRepository;

	private JavaLibraryRDRepository javaLibraryRDRepository;
	
	public Page<JavaLibrary> search(String name, String project, String version,
			String[] category, String[] tag, boolean verified, int pn, int size) {
		Page<JavaLibrary> libraries = new Page<>();
		libraries.setTotalElement(javaLibraryRepository.search(name, project, version, category, tag, verified), size);
		libraries.setContent(javaLibraryRepository.search(name, project, version, category, tag, verified, pn * size, size));
		if (pn >= libraries.getTotalPage()) {
			libraries.setCurrentPage(libraries.getTotalPage() - 1);
		} else {
			libraries.setCurrentPage(pn);
		}
		return libraries;
	}
	
	public JavaLibrary getJarFile(String name) {
		return javaLibraryRepository.getJarFile(name);
	}
	
	public Page<JavaLibrary> getJarFiles(int pn, int size) {
		Page<JavaLibrary> libraries = new Page<>();
		libraries.setTotalElement(javaLibraryRepository.count(), size);
		libraries.setContent(javaLibraryRepository.getJarFiles(pn * size, size));
		libraries.setCurrentPage(pn);
		return libraries;
	}
	
	public JavaLibrary getLibrary(String id) {
		return javaLibraryRepository.getLibrary(id);
	}
	
	public Page<JavaLibrary> searchByClass(String query, int pn, int size) {
		Page<JavaLibrary> libraries = new Page<>();
		query = ";" + query.toLowerCase() + ";";
		libraries.setTotalElement(javaLibraryRDRepository.countLibrary(query), size);
		if (libraries.getTotalElement() == 0) {
			return libraries;
		}
		List<String> idList = javaLibraryRDRepository.searchLibrary(query, pn * size, size);
		List<JavaLibrary> libraryList = javaLibraryRepository.getLibraries(idList, pn * size, size);
		libraries.setCurrentPage(pn);
		libraries.setContent(libraryList);
		return libraries;
	}
	
	public byte[] getLibraryFile(String name, String type) {
		byte[] bytes = null;
		try {
			switch (type) {
				case JavaLibrary.DOC :
					bytes = FileUtil.getFileAsStream(FileUtil.LIBRARY_DOC_PATH + "/" + name);
					break;
				case JavaLibrary.SRC :
					bytes = FileUtil.getFileAsStream(FileUtil.LIBRARY_SRC_PATH + "/" + name);
					break;
				default:
					bytes = FileUtil.getFileAsStream(FileUtil.LIBRARY_PATH + "/" + name);
			}
		} catch (Exception e) {
			e.printStackTrace();
			bytes = null;
		}
		return bytes;
	}
	
	public JavaFile getJavaFile(String library, String file) {
		String fullName = file.substring(file.lastIndexOf('/') + 1);
		String name = fullName.substring(0, fullName.lastIndexOf('.'));
		String extension = null;
		try {
			extension = fullName.substring(fullName.lastIndexOf('.') + 1, fullName.length());
		} catch (Exception e) {
			extension = "";
		}
		if (extension.equals("class")) {
			extension = "java";
		}
		return javaFileRepository.getJavaFile(library, file.substring(0, file.lastIndexOf('/')), name, extension);
	}

    @Autowired
	public void setJavaLibraryRepository(JavaLibraryRepository javaLibraryRepository) {
		this.javaLibraryRepository = javaLibraryRepository;
	}

    @Autowired
	public void setJavaFileRepository(JavaFileRepository javaFileRepository) {
		this.javaFileRepository = javaFileRepository;
	}

    @Autowired
	public void setJavaLibraryRDRespository(JavaLibraryRDRepository javaLibraryRDRepository) {
		this.javaLibraryRDRepository = javaLibraryRDRepository;
	}
	
}
