package org.mu.cloudservice.controller;

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

	@RequestMapping(value = "download.html", method = RequestMethod.GET)
	public ResponseEntity<byte[]> download(
			@RequestParam("file") String id,
			@RequestParam(value = "type" , defaultValue = "LIB") String type,
			@RequestParam(value = "jtype", defaultValue = "") String jtype) {
		String name = null;
		byte[] bytes = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", name);
		if (bytes != null) {
			return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
		} else {
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("attachment", "error.txt");
			return new ResponseEntity<byte[]>(("File '" + id + "' does not exist.").getBytes(), headers, HttpStatus.OK);
		}
	}

}
