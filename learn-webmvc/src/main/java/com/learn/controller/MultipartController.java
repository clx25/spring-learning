package com.learn.controller;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class MultipartController {

	@PostMapping("/upload")
	public String upload(@RequestParam("file")MultipartFile file) throws IOException {
		FileCopyUtils.copy(file.getInputStream(), new FileOutputStream("d:/" + file.getOriginalFilename()));
		return "success";
	}
}
