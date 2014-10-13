package com.bogdans.textanalizer.validators;

import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;

public class FileUploadValidatorImpl implements FileUploadValidator {
	
	Logger logger = LoggerFactory.getLogger(FileUploadValidatorImpl.class);

	@Override
	public boolean isDuplicateFileName(String fileName) {
		
		try {
			Mongo mongo = new Mongo("localhost", 27017);
			DB db = mongo.getDB("text_analyzer");
			DBCollection table = db.getCollection("imported_files");
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("file", fileName);
			DBCursor cursor = table.find(searchQuery);
			if(cursor.hasNext()) {
				return true;
			}
		} catch (UnknownHostException e) {
			logger.error("error connecting to mongoDB");
		}
		
		return false;
	}

}
