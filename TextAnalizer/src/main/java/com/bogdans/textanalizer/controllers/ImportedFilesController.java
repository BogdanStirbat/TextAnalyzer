package com.bogdans.textanalizer.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bogdans.textanalizer.service.ImportedFilesService;

@Controller
public class ImportedFilesController {
	
	Logger logger = LoggerFactory.getLogger(ImportedFilesController.class);
	
	@Autowired
	private ImportedFilesService importedFilesService;
	
	@RequestMapping("files")
	public ModelAndView getImported() {
		List<String> importedFiles = importedFilesService.getListOfImportedFiles();
		return new ModelAndView("files", "importedFiles", importedFiles);
	}
	
	@RequestMapping(value = "/files", method = RequestMethod.POST)
	public ModelAndView deleteSelectedFile(@RequestParam("input_file") String inputFile) {
		importedFilesService.deleteFile(inputFile);
		List<String> importedFiles = importedFilesService.getListOfImportedFiles();
		return new ModelAndView("files", "importedFiles", importedFiles);
	}
}
