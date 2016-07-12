package com.fnma.aceso.utilities;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;

@Configuration
public class MongoService {
private Properties properties;
	
	public @Bean MongoDbFactory mongoDbFactory() throws Exception {
		properties= ServiceAccessor.getProperties();
		MongoClient mongoClient = new MongoClient(properties.get("db.host"),
				Integer.parseInt(properties.get("db.port")));
		UserCredentials credential = new UserCredentials(properties.get("db.username"),
				properties.get("db.password"));
		return new SimpleMongoDbFactory(mongoClient, properties.get("db.name"), credential);
	}

	public @Bean MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		return mongoTemplate;
	}

}
