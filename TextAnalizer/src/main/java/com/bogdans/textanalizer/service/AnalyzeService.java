package com.bogdans.textanalizer.service;

import com.bogdans.textanalizer.model.AnalyzeResult;

public interface AnalyzeService {
	public AnalyzeResult analyzeTerm(String inputTerm);
}
