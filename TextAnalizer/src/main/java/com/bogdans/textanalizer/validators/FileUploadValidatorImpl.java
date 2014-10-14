package com.bogdans.textanalizer.validators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.bogdans.textanalizer.constants.ImportedFilesTable;
import com.bogdans.textanalizer.constants.MongoCollections;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class FileUploadValidatorImpl implements FileUploadValidator {
	
	Logger logger = LoggerFactory.getLogger(FileUploadValidatorImpl.class);
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public boolean isDuplicateFileName(String fileName) {
		
		try {
			DBCollection imported_files = mongoTemplate.getCollection(MongoCollections.IMPORTED_FILES);
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put(ImportedFilesTable.FILE, fileName);
			DBCursor cursor = imported_files.find(searchQuery);
			if(cursor.hasNext()) {
				return true;
			}
		} catch (Exception e) {
			logger.error("error connecting to mongoDB");
		}
		
		return false;
	}

}
