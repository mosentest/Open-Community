package org.mu.cloudservice.controller;

import java.util.Date;
import java.util.List;

import org.mu.opencomm.code.entity.JavaLibrary;
import org.mu.opencomm.code.service.JavaLibraryService;
import org.mu.opencomm.code.service.StatService;
import org.mu.opencomm.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/statistics/")
public class StatController {

	@Autowired
	private StatService statService;

	@Autowired
	private JavaLibraryService javaLibraryService;

	@RequestMapping(value = "download.html", method = RequestMethod.GET)
	public ModelAndView download(@RequestParam("file") String id,
			@RequestParam(value = "", required = false, defaultValue = StatService.MONTHLY) String type,
			ModelMap model) {
		JavaLibrary library = javaLibraryService.getLibrary(id);
		if (library == null) {
			return null;
		}
		model.put("javaLibrary", library);
		model.put("file", library.getName() + ".jar");
		model.put("title", "Download Statistics");
		List<Long> value = statService.getDownloadValue(id, type);
		model.put("values", value);
		model.put("type", type);
		Date to = DateUtil.clearTimeValue(new Date());
		Date from = null;
		switch (type) {
			case StatService.WEEKLY:
				from = DateUtil.backOneWeek(to);
				model.put("subTitle", "Weekly statistics for file: " + model.get("file"));
				model.put("labels", DateUtil.getDateLabels(from, to));
				break;
			case StatService.MONTHLY:
				from = DateUtil.backOneMonth(to);
				model.put("subTitle", "Monthly statistics for file: " + model.get("file"));
				model.put("labels", DateUtil.getDateLabels(from, to));
				break;
			case StatService.ANNUALLY:
				from = DateUtil.backOneYear(to);
				model.put("subTitle", "Annually statistics for file: " + model.get("file"));
				model.put("labels", DateUtil.getMonthLabels(from, to));
				break;
		}
		return new ModelAndView("source/downloadstat", model);
	}
	
	public void setStatService(StatService statService) {
		this.statService = statService;
	}

	public void setJavaLibraryService(JavaLibraryService javaLibraryService) {
		this.javaLibraryService = javaLibraryService;
	}
	
}
