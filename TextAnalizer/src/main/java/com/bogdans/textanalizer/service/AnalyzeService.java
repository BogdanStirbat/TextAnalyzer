package com.bogdans.textanalizer.service;

import java.util.List;

import com.bogdans.textanalizer.model.AnalyzeResult;

public interface AnalyzeService {
	public List<AnalyzeResult> analyzeTerm(String inputTerm);
}
