package com.bogdans.textanalizer.service;

import java.util.List;

import com.bogdans.textanalizer.model.AnalyzeFilenameOccurence;

public interface AnalyzeService {
	public List<AnalyzeFilenameOccurence> analyzeTerm(String inputTerm);
}
