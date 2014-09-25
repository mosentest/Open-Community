package org.mu.community.common.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.mu.community.common.constants.PropertiesManager;
import org.mu.community.common.entity.JsonResponse;
import org.mu.community.common.enumtype.FileType;
import org.mu.community.common.security.Authentication;
import org.mu.community.common.util.FileUtil;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/image/")
public class ImageController {
	
	private static final long MAX_POST_SIZE = PropertiesManager.getLongProperty("file.image.post.max", 5000000);
	
	private static final String[] VALID_EXTENSION = PropertiesManager.getStringArray("file.image.extension");

	@RequestMapping(value = "{type}/{image:.+}", method = RequestMethod.GET)
	public void displayImage(@PathVariable("type") String type, @PathVariable("image") String image, 
			HttpServletResponse response) {
		FileType fileType = FileType.getType(type);
		byte[] bytes = FileUtil.getFileAsStream(FileUtil.getPath(fileType, image));
		try {
			String extension = image.substring(image.lastIndexOf('.') + 1);
			response.setContentType("image/" + extension);
			response.getOutputStream().write(bytes);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "upload/o", method = RequestMethod.POST)
	public @ResponseBody JsonResponse upload(@AuthenticationPrincipal Authentication auth, 
			MultipartHttpServletRequest request) {
		JsonResponse response = new JsonResponse();
		Iterator<String> iterator =  request.getFileNames();
		MultipartFile multipartFile = request.getFile(iterator.next());
		if (multipartFile.getSize() > MAX_POST_SIZE) {
			response.setMessage("File can not be larget than 5 MB.");
			return response;
		}
		if (!FileUtil.isValid(multipartFile.getOriginalFilename(), VALID_EXTENSION)) {
			response.setMessage("File type is invalid.");
			return response;
		}
		InputStream inputStream = null;
		try {
			inputStream = multipartFile.getInputStream();
			String file = FileUtil.toFile(inputStream, FileType.POST, 
					auth.getPath(), FileUtil.getExtension(multipartFile.getOriginalFilename()));
			if (file != null) {
				response.setSuccess(true);
				response.setContent(file);
			} else {
				response.setMessage("Could not upload file.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {}
		}
		return response;
	}	
}
