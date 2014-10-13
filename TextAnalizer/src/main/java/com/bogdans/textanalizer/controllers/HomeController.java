package com.bogdans.textanalizer.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bogdans.textanalizer.model.FileUploadResultModel;
import com.bogdans.textanalizer.service.FileUploadService;
import com.bogdans.textanalizer.validators.FileUploadValidator;


@Controller
public class HomeController {
	
	Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private FileUploadValidator fileUploadValidator;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping("index")
	public ModelAndView getIndex() {
		return new ModelAndView("home");
	}
	
	@RequestMapping("analyze")
	public ModelAndView getAnalyze() {
		return new ModelAndView("analyze");
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public @ResponseBody ModelAndView uploadFileHandler(@RequestParam("file") MultipartFile file) {
		FileUploadResultModel result = new FileUploadResultModel();
		String fileName = file.getOriginalFilename();
		if (file.isEmpty()) {
			result.setMessage("Error: file empty");
			result.setSuccess(false);
			return new ModelAndView("home", "result", result);
		}
		if (fileUploadValidator.isDuplicateFileName(fileName)) {
			result.setMessage("Error: duplicate file found: " + fileName);
			result.setSuccess(false);
			return new ModelAndView("home", "result", result);
		}
		try {
			InputStream inputStream = file.getInputStream();
			result = fileUploadService.uploadFile(inputStream, fileName);
			
		} catch (IOException e) {
			result.setMessage("An error occured");
			result.setSuccess(false);
			return new ModelAndView("home", "result", result);
		}
		
		return new ModelAndView("home", "result", result);
	}
}
