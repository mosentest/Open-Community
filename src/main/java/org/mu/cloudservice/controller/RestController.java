package org.mu.cloudservice.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;
import org.mo.opencomm.code.vo.JavaLibrary;
import org.mu.opencomm.code.repository.JavaLibraryRepository;
import org.mu.opencomm.common.controller.GenericController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rest/")
public class RestController implements GenericController {
	private JavaLibraryRepository javaLibraryRepository;

	private final static Logger LOG = LoggerFactory
			.getLogger(RestController.class);

	/**
	 * 修改数据
	 * 
	 * @param javaLibrary
	 * @return
	 */
	@RequestMapping(value = "updateData", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public JavaLibrary updateData(@RequestBody JavaLibrary javaLibrary) {
		LOG.info("updateData---" + javaLibrary.toString());
		javaLibrary.setName("moziqi");
		return javaLibrary;
	}

	/**
	 * 更新数据前操作
	 * 
	 * @param value
	 */
	@RequestMapping(value = "getDataById", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public JavaLibrary getDataById(@RequestBody JavaLibrary value) {
		// 查找信息
		ObjectId id = new ObjectId(value.getId());
		org.mu.opencomm.code.entity.JavaLibrary javaLibrary = javaLibraryRepository
				.findOne(id);
		// 构造vo对象
		JavaLibrary edit_library = new JavaLibrary(javaLibrary.getId(),
				javaLibrary.getnDownload(), javaLibrary.getName(),
				javaLibrary.getCategories());
		LOG.info("获取过来的信息---" + edit_library.toString());
		return edit_library;
	}

	/**
	 * 删除数据
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "deleteDataById", method = RequestMethod.POST)
	public void deleteDataById(HttpServletRequest request,
			HttpServletResponse response) {
		// 获取要删除id数组
		String checkbox_del_ids = request.getParameter("checkbox_del_ids");
		String[] del_id = checkbox_del_ids.split(",");
		for (int i = 0; i < del_id.length; i++) {
			LOG.info("要删除的id---" + del_id[i]);
		}
		try {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("{\"success\":true }");
			response.getWriter().flush();
		} catch (IOException e) {
			LOG.error("deleteDataById方法响应出错");
		}
	}

	public JavaLibraryRepository getJavaLibraryRepository() {
		return javaLibraryRepository;
	}

	@Resource
	public void setJavaLibraryRepository(
			JavaLibraryRepository javaLibraryRepository) {
		this.javaLibraryRepository = javaLibraryRepository;
	}
}
