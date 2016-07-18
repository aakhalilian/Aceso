package com.fnma.aceso.utilities;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.servlet.ServletContext;

public class FileService {
	public static ArrayList<String> LookFor(String fileName,String target) throws URISyntaxException{
		ArrayList<String> result=new ArrayList<String>();
		File targetFile=new File(getAbsolutePath(target));
		if (!targetFile.exists() || !targetFile.isDirectory())
			return result;
		else {
			File[] files=targetFile.listFiles();
			for(File file : files){
				if(file.isDirectory())
					result.addAll(LookFor(fileName,file.getPath()));
				else if(file.getName().matches(".*"+fileName))
					result.add(file.getAbsolutePath());
			}
			return result;
		}
	}
	
	public static String getAbsolutePath(String path) throws URISyntaxException{
		path=path.replace("\\","/");
		URI uri=new URI(path);
		if(uri.isAbsolute())	
			return path;
		else {
			ServletContext servletContext=ServiceAccessor.getServletContext();
			return servletContext.getRealPath(path);
		}
	}
}
