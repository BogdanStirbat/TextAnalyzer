package com.bogdans.textanalizer.service;

import java.util.List;

public interface ImportedFilesService {
	
	public List<String> getListOfImportedFiles();
	
	public boolean deleteFile(String fileName);
}
