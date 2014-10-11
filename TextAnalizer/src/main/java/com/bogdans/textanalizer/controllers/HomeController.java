package com.bogdans.textanalizer.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {
	
	Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping("index")
	public ModelAndView getIndex() {
		return new ModelAndView("home");
	}
	
	@RequestMapping("analyze")
	public ModelAndView getAnalyze() {
		return new ModelAndView("analyze");
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public @ResponseBody String uploadFileHandler(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return "Error: file empty";
		}
		
		try {
			InputStream inputStream = file.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			String line = "";
			while ((line = bufferedReader.readLine()) != null){
				logger.debug(line);
			}
			
		} catch (IOException e) {
			return "An error occured";
		}
		
		
		return "File uploaded successfully";
	}
}
