package com.fnma.aceso.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;

@Configuration
public class MongoService {
	@Autowired
	private PropService propService;

	public @Bean MongoDbFactory mongoDbFactory() throws Exception {
		MongoClient mongoClient = new MongoClient(propService.get("db.host"),
				Integer.parseInt(propService.get("db.port")));
		UserCredentials credential = new UserCredentials(propService.get("db.username"),
				propService.get("db.password"));
		return new SimpleMongoDbFactory(mongoClient, propService.get("db.name"), credential);
	}

	public @Bean MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		return mongoTemplate;
	}

}
