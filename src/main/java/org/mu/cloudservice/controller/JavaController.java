package org.mu.cloudservice.controller;

import java.util.List;

import org.mu.opencomm.code.entity.FileInfo;
import org.mu.opencomm.code.entity.JavaLibrary;
import org.mu.opencomm.code.service.CategoryService;
import org.mu.opencomm.code.service.JavaLibraryService;
import org.mu.opencomm.code.service.TagService;
import org.mu.opencomm.common.dbutil.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/source/")
public class JavaController {
	
	private static final int LIST_SIZE = 20;
	
	@Autowired
	private JavaLibraryService javaLibraryService;
	
	@Autowired
	private TagService tagService;

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "u/{path}/{project}.html", method = RequestMethod.GET) 
	public void test() {}
	
	@RequestMapping(value = "categories.html", method = RequestMethod.GET)
	public ModelAndView categories(ModelMap model) {
		model.put("categoryList", categoryService.getMostTagged("java"));
		return new ModelAndView("source/categoryList", model);
	}
	
	@RequestMapping(value = "tags.html", method = RequestMethod.GET)
	public ModelAndView tags(ModelMap model) {
		model.put("tagList", tagService.getMostTagged("java"));
		return new ModelAndView("source/tagList", model);
	}
	
	@RequestMapping(value = "libraries.html", method = RequestMethod.GET)
	public ModelAndView search(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "project", required = false) String project,
			@RequestParam(value = "version", required = false) String version,
			@RequestParam(value = "category", required = false) String category,
			@RequestParam(value = "tag", required = false) String tag,
			@RequestParam(value = "verified", required = false) boolean verified,
			@RequestParam(value = "pn", required = false, defaultValue = "0") Integer pn,
			ModelMap model) {
		if (name != null) {
			JavaLibrary javaLibrary = javaLibraryService.getJarFile(name);
			if (javaLibrary != null) {
				model.put("javaLibrary", javaLibrary);
				return new ModelAndView("source/library", model);
			}
		}
		String[] tags = tag == null ? null : tag.trim().split("\\s*;\\s*");
		String[] categories = category == null ? null : category.trim().split("\\s*;\\s*");
		Page<JavaLibrary> libraries = javaLibraryService.search(name, project, version, categories, tags, verified, pn, LIST_SIZE);
		model.put("libraryList", libraries);
		model.put("name", name);
		model.put("project", project);
		model.put("version", version);
		model.put("category", category);
		model.put("tag", tag);
		model.put("verified", verified);
		return new ModelAndView("source/libraryList", model);
	}
	
	@RequestMapping(value = "library/{library}.html", method = RequestMethod.GET)
	public ModelAndView library(@PathVariable("library") String library,
			@RequestParam(value = "file", required = false) String file,
			@RequestParam(value = "directory", required = false) String directory,
			ModelMap model) {
		JavaLibrary javaLibrary = javaLibraryService.getJarFile(library);
		javaLibrary.setDescription("Description: " + javaLibrary.getName());
		model.put("javaLibrary", javaLibrary);
		if (file != null) {
			model.put("javaFile", javaLibraryService.getJavaFile(library, file));
			return new ModelAndView("source/javafile", model);
		} else if (directory != null) {
			FileInfo libraryDir = getDirectory(javaLibrary.getStructure(), directory);
			model.put("directory", directory);
			model.put("libraryDir", libraryDir);
			return new ModelAndView("source/javadirectory", model);
		}
		return new ModelAndView("source/library", model);
	}
	
	@RequestMapping(value = "code.html", method = RequestMethod.GET)
	public String code() {
		return "source/codeindex";
	}
	
	@RequestMapping(value = "codes.html", method = RequestMethod.GET)
	public ModelAndView code(@RequestParam("query") String query, 
			@RequestParam(value = "pn", required = false, defaultValue = "0") Integer pn,
			ModelMap model) {
		Page<JavaLibrary> libraries = javaLibraryService.searchByClass(query, pn, LIST_SIZE);
		model.put("libraryList", libraries);
		model.put("query", query);
		return new ModelAndView("source/codeList", model);
	}
	
	private FileInfo getDirectory(List<FileInfo> dir, String directory) {
		for (FileInfo fileInfo : dir) {
			if (fileInfo.isDir()) {
				if (fileInfo.getName().equals(directory)) {
					return fileInfo;
				} else {
					FileInfo info = getDirectory(fileInfo.getFiles(), directory);
					if (info != null) {
						return info;
					}
				}
			}
		}
		return null;
	}

	public void setJavaLibraryService(JavaLibraryService javaLibraryService) {
		this.javaLibraryService = javaLibraryService;
	}

	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
}
