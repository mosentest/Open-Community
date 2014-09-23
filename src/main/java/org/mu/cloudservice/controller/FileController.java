package org.mu.cloudservice.controller;

import org.mu.opencomm.code.entity.JavaLibrary;
import org.mu.opencomm.code.service.JavaLibraryService;
import org.mu.opencomm.code.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/file/")
public class FileController {

	@Autowired
	private JavaLibraryService javaLibraryService;

	@Autowired
	private StatService statService;

	@RequestMapping(value = "download.html", method = RequestMethod.GET)
	public ResponseEntity<byte[]> download(
			@RequestParam("file") String id,
			@RequestParam(value = "type" , defaultValue = "LIB") String type,
			@RequestParam(value = "jtype", defaultValue = JavaLibrary.LIB) String jtype) {
		String name = null;
		byte[] bytes = null;
		switch (type) {
			case "LIB" :
				JavaLibrary library = javaLibraryService.getLibrary(id);
				if (library == null) {
					break;
				}
				name = library.getName() + ".jar";
				bytes = javaLibraryService.getLibraryFile(name, jtype);
				break;
			default:
				break;
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", name);
		if (bytes != null) {
			statService.increase(id);
			return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
		} else {
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("attachment", "error.txt");
			return new ResponseEntity<byte[]>(("File '" + id + "' does not exist.").getBytes(), headers, HttpStatus.OK);
		}
	}

	public void setJavaLibraryService(JavaLibraryService javaLibraryService) {
		this.javaLibraryService = javaLibraryService;
	}

	public void setStatService(StatService statService) {
		this.statService = statService;
	}

}
