package com.fnma.aceso.resource.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fnma.aceso.utilities.LogService;
import com.github.sommeri.less4j.Less4jException;
import com.github.sommeri.less4j.LessCompiler;
import com.github.sommeri.less4j.LessCompiler.CompilationResult;
import com.github.sommeri.less4j.core.DefaultLessCompiler;

@Service
public class LessService {
	private LessCompiler compiler = new DefaultLessCompiler();

	public void compile(File file) throws Less4jException, IOException {

		if (file.getName().matches(".*[.less]")) {
			CompilationResult result = compiler.compile(file);
			String cssString = result.getCss();
			if (cssString != null) {
				String absolutePath = file.getAbsolutePath();
				absolutePath = absolutePath + (".css");
				File cssFile = new File(absolutePath);
				if (!cssFile.exists()) {
					if (!cssFile.getParentFile().exists())
						cssFile.getParentFile().mkdirs();
					cssFile.createNewFile();
				}
				FileWriter fileWriter = new FileWriter(cssFile.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fileWriter);
				bw.write(cssString);
				bw.close();
			}
		}
	}

}
