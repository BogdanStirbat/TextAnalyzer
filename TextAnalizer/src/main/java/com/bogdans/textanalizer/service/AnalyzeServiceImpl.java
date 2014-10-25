package com.bogdans.textanalizer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.bogdans.textanalizer.constants.MongoCollections;
import com.bogdans.textanalizer.model.AnalyzeFilenameOccurence;
import com.bogdans.textanalizer.model.AnalyzeResult;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class AnalyzeServiceImpl implements AnalyzeService{
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public AnalyzeResult analyzeTerm(String inputTerm) {
		AnalyzeResult result = new AnalyzeResult();
		List<AnalyzeFilenameOccurence> references = new ArrayList<AnalyzeFilenameOccurence>();
		
		DBCollection words_per_file = mongoTemplate.getCollection(MongoCollections.WORDS_PER_FILE);
		
		DBCursor cursor = words_per_file.find(getInputTermSearch(inputTerm));
		while(cursor.hasNext()) {
			DBObject object = cursor.next();
			String fileName = (String) object.get("file");
			boolean found = false;
			for (AnalyzeFilenameOccurence analyzeResult : references) {
				if (analyzeResult.getFileName().equalsIgnoreCase(fileName)) {
					int count = analyzeResult.getNumberOfOccurences();
					count++;
					analyzeResult.setNumberOfOccurences(count);
					found = true;
					break;
				}
			}
			if (!found) {
				AnalyzeFilenameOccurence analyzeResult = new AnalyzeFilenameOccurence();
				analyzeResult.setFileName(fileName);
				analyzeResult.setNumberOfOccurences(1);
				references.add(analyzeResult);
			}
		}
		result.setInputTerm(inputTerm);
		result.setOccurences(references);
		return result;
	}
	
	
	private BasicDBObject getInputTermSearch(String inputTerm) {
		BasicDBObject result = new BasicDBObject();
		
		result.put("word", inputTerm);
		
		return result;
	}
}
