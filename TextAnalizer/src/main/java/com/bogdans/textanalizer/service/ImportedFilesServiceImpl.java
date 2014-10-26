package com.bogdans.textanalizer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.bogdans.textanalizer.constants.MongoCollections;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class ImportedFilesServiceImpl implements ImportedFilesService{
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<String> getListOfImportedFiles() {
		List<String> importedFiles = new ArrayList<String>();
		
		DBCollection imported_files = mongoTemplate.getCollection(MongoCollections.IMPORTED_FILES);
		DBCursor cursor = imported_files.find();
		
		while(cursor.hasNext()) {
			DBObject object = cursor.next();
			String fileName = (String) object.get("file");
			if (fileName != null && fileName.length() > 0) {
				importedFiles.add(fileName);
			}
		}
		
		return importedFiles;
	}

	@Override
	public boolean deleteFile(String fileName) {
		DBCollection imported_files = mongoTemplate.getCollection(MongoCollections.IMPORTED_FILES);
		DBCollection words_per_file = mongoTemplate.getCollection(MongoCollections.WORDS_PER_FILE);
		BasicDBObject fileSearch = getInputFileSearch(fileName);
		DBCursor importedFilesCursor = imported_files.find(fileSearch);
		while(importedFilesCursor.hasNext()) {
			DBObject object = importedFilesCursor.next();
			imported_files.remove(object);
		}
		DBCursor wordsPerFileCursor = words_per_file.find(fileSearch);
		while(wordsPerFileCursor.hasNext()) {
			DBObject object = wordsPerFileCursor.next();
			words_per_file.remove(object);
		}
		return false;
	}
	
	
	private BasicDBObject getInputFileSearch(String inputFile) {
		BasicDBObject result = new BasicDBObject();
		result.put("file", inputFile);
		return result;
	}
}
