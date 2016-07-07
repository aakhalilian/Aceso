package com.fnma.aceso.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("app.properties")

public class PropService {
	@Autowired
	private Environment env;

	public String get(String prop) {
		return env.getProperty(prop);
	}

}
