package com.bogdans.textanalizer.service;

import java.io.InputStream;

import com.bogdans.textanalizer.model.FileUploadResultModel;

public interface FileUploadService {
	
	public FileUploadResultModel uploadFile(InputStream inputStream, String fileName);
}
