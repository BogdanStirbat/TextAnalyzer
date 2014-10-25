package com.bogdans.textanalizer.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bogdans.textanalizer.model.AnalyzeFilenameOccurence;
import com.bogdans.textanalizer.model.AnalyzeResult;
import com.bogdans.textanalizer.service.AnalyzeService;

@Controller
public class AnalyzeController {
	Logger logger = LoggerFactory.getLogger(AnalyzeController.class);
	
	@Autowired
	private AnalyzeService analyzeService;
	
	@RequestMapping("analyze")
	public ModelAndView getAnalyze() {
		return new ModelAndView("analyze");
	}
	
	@RequestMapping(value = "/analyze", method = RequestMethod.POST)
	public @ResponseBody ModelAndView analyzeTerm(@RequestParam("input_term") String inputText) {
		AnalyzeResult result = analyzeService.analyzeTerm(inputText);
		
		return new ModelAndView("analyze", "result", result);
	}
}
