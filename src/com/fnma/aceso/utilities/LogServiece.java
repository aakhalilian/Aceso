package com.fnma.aceso.utilities;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.springframework.stereotype.Service;

@Service
public class LogServiece {
	private Logger log;

	public void cleanSet() {
		Logger.getRootLogger().getLoggerRepository().resetConfiguration();
	}

	public Logger getLog(String filePath, Class sourceClass) {
		setLog(Logger.getLogger(sourceClass.getName()));
		ConsoleAppender console = new ConsoleAppender(); // create appender

		String PATTERN = "%d [%p|%c|%C{1}] %m%n";
		console.setLayout(new PatternLayout(PATTERN));
		console.setThreshold(Level.FATAL);
		console.activateOptions();
		log.addAppender(console);

		FileAppender fa = new FileAppender();
		fa.setName("FileLogger");
		fa.setFile(filePath);
		fa.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
		fa.setThreshold(Level.DEBUG);
		fa.setAppend(true);
		fa.activateOptions();
		log.addAppender(fa);
		return log;
	}

	public void setLog(Logger log) {
		this.log = log;
	}

}