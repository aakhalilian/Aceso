package com.fnma.aceso.resource.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fnma.aceso.utilities.LogServiece;
import com.github.sommeri.less4j.Less4jException;
import com.github.sommeri.less4j.LessCompiler;
import com.github.sommeri.less4j.LessCompiler.CompilationResult;
import com.github.sommeri.less4j.core.DefaultLessCompiler;

@Service
public class LessService {
	@Autowired
	ServletContext servletContext;
	@Autowired
	private LogServiece logServiece;

	private LessCompiler compiler = new DefaultLessCompiler();

	public void doComplile() throws Less4jException, IOException {
		File[] files = new File(servletContext.getRealPath("/assets/less")).listFiles();
		compilation(files);
	}

	private void compilation(File[] files) throws Less4jException, IOException {
		for (File file : files) {
			if (file.isDirectory()) {
				compilation(file.listFiles()); // Calls same method again.
			} else if (file.getName().matches(".*[.less]")) {
				CompilationResult result = compiler.compile(file);
				String cssString = result.getCss();
				if (cssString != null) {
					String absolutePath = file.getAbsolutePath();
					absolutePath = absolutePath.replace("less", "css");
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

}
