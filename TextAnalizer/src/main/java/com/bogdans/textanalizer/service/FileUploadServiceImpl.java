package com.bogdans.textanalizer.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.bogdans.textanalizer.constants.ImportedFilesTable;
import com.bogdans.textanalizer.constants.MongoCollections;
import com.bogdans.textanalizer.constants.WordsPerFileTable;
import com.bogdans.textanalizer.model.FileUploadResultModel;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

public class FileUploadServiceImpl implements FileUploadService {
	
	Logger logger = LoggerFactory.getLogger(FileUploadServiceImpl.class);
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public FileUploadResultModel uploadFile(InputStream inputStream,
			String fileName) {
		FileUploadResultModel model = new FileUploadResultModel();
		
		try {
			DBCollection imported_files = mongoTemplate.getCollection(MongoCollections.IMPORTED_FILES);
			DBCollection words_per_file = mongoTemplate.getCollection(MongoCollections.WORDS_PER_FILE);
			addFileNameToDatabase(imported_files, fileName);
			addFileContentToDatabase(words_per_file, inputStream, fileName);
		} catch (IOException e) {
			model.setMessage("An error occured while importing file");
			model.setSuccess(false);
			return model;
		}
		model.setMessage("File uploaded successfully");
		model.setSuccess(true);
		return model;
	}
	
	private void addFileNameToDatabase(DBCollection imported_files, String fileName) {
		BasicDBObject document = new BasicDBObject();
		document.put(ImportedFilesTable.FILE, fileName);
		imported_files.insert(document);
	}
	
	private void addFileContentToDatabase(DBCollection words_per_file, InputStream inputStream, String fileName) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String line = "";
		while ((line = bufferedReader.readLine()) != null) {
			addWordsFromLineToDatabase(words_per_file, fileName, line);
		}
	}
	
	private void addWordsFromLineToDatabase(DBCollection imported_files, String fileName, String line) {
		if (line == null) {
			return;
		}
		String[] words = line.replaceAll("[^a-zA-Z'\\s]", "").toLowerCase().split("\\s+");
		for(String word : words) {
			BasicDBObject document = new BasicDBObject();
			document.put(WordsPerFileTable.FILE, fileName);
			document.put(WordsPerFileTable.WORD, word);
			imported_files.insert(document);
		}
	}
	

}
