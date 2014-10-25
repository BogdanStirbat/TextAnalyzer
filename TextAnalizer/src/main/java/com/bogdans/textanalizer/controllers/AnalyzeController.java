package com.bogdans.textanalizer.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AnalyzeController {
	Logger logger = LoggerFactory.getLogger(AnalyzeController.class);
	
	@RequestMapping("analyze")
	public ModelAndView getAnalyze() {
		return new ModelAndView("analyze");
	}
	
	@RequestMapping(value = "/analyze", method = RequestMethod.POST)
	public @ResponseBody ModelAndView analyzeTerm(@RequestParam("input_term") String inputText) {
		
		return new ModelAndView("analyze", "inputText", inputText);
	}
}
