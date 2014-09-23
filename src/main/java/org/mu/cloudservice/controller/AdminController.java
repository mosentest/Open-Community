package org.mu.cloudservice.controller;

import javax.annotation.Resource;

import org.mu.opencomm.code.repository.JavaLibraryRepository;
import org.mu.opencomm.code.service.TagService;
import org.mu.opencomm.common.controller.GenericController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/")
public class AdminController implements GenericController {

	private JavaLibraryRepository javaLibraryRepository;
	private TagService tagService;

	public JavaLibraryRepository getJavaLibraryRepository() {
		return javaLibraryRepository;
	}

	@Resource
	public void setJavaLibraryRepository(
			JavaLibraryRepository javaLibraryRepository) {
		this.javaLibraryRepository = javaLibraryRepository;
	}

	public TagService getTagService() {
		return tagService;
	}

	@Resource
	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}

	@RequestMapping(value = "index.html", method = RequestMethod.GET)
	public ModelAndView index(
			@RequestParam(value = "pn", required = false, defaultValue = "0") Integer pn,
			@RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
			ModelMap modelMap) {
//		Page<JavaLibrary> jarFiles = javaLibraryRepository
//				.getJarFiles(pn, size);
//		modelMap.put("jarFiles", jarFiles);
		return new ModelAndView("admin/index", modelMap);
	}

	@RequestMapping(value = "libs.html", method = RequestMethod.GET)
	public ModelAndView libs(
			@RequestParam(value = "pn", required = false, defaultValue = "0") Integer pn,
			@RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
			@RequestParam(value = "tag", required = false) String tag,
			ModelMap model) {
//		Page<JavaLibrary> jarFiles = javaLibraryRepository
//				.getJarFiles(pn, size);
//		// 假设获取下载列表
//		Page<JavaLibrary> downloadJarFiles = javaLibraryRepository.getJarFiles(
//				PN, SIZE);
//		model.put("downloadJarFiles", downloadJarFiles);
//		model.put("page", jarFiles);
//		model.put("tags", tagService.getMostTagged("java"));
		return new ModelAndView("admin/libs", model);
	}
}
