package com.bogdans.textanalizer.model;

import java.util.List;

public class AnalyzeResult {
	private String inputTerm;
	private List<AnalyzeFilenameOccurence> occurences;
	public String getInputTerm() {
		return inputTerm;
	}
	public void setInputTerm(String filename) {
		this.inputTerm = filename;
	}
	public List<AnalyzeFilenameOccurence> getOccurences() {
		return occurences;
	}
	public void setOccurences(List<AnalyzeFilenameOccurence> occurences) {
		this.occurences = occurences;
	}
}
