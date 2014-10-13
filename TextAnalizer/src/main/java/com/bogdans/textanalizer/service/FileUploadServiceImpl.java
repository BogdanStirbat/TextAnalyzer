package com.bogdans.textanalizer.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bogdans.textanalizer.model.FileUploadResultModel;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class FileUploadServiceImpl implements FileUploadService {
	
	Logger logger = LoggerFactory.getLogger(FileUploadServiceImpl.class);
	
	@Override
	public FileUploadResultModel uploadFile(InputStream inputStream,
			String fileName) {
		FileUploadResultModel model = new FileUploadResultModel();
		
		try {
			Mongo mongo = new Mongo("localhost", 27017);
			DB db = mongo.getDB("text_analyzer");
			DBCollection imported_files = db.getCollection("imported_files");
			DBCollection words_per_file = db.getCollection("words_per_file");
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
		document.put("file", fileName);
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
			document.put("file", fileName);
			document.put("word", word);
			imported_files.insert(document);
		}
	}
	

}
