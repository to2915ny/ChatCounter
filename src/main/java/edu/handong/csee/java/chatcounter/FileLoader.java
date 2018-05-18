package edu.handong.csee.java.chatcounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileLoader {
	
	private ArrayList<String> messages;
	
	public void readDirectory(String path) {
		
		ArrayList<File> fileName = getFileNames(path);
	}
	
	private ArrayList<File> getFileNames(String path){
		
		ArrayList<File> fileNames = new ArrayList<File>();
		File myPath = new File(path);
		myPath.listFiles();
		for(File fileName: myPath.listFiles()) {
			fileNames.add(fileName);
		}
		return fileNames;
		}
	
	public void getMessages(String fileName,boolean removeHeader) {
		ArrayList<String> lines = new ArrayList<String>();
		String thisLine="";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while(thisLine != br.readLine()) {
				lines.add(thisLine);
		}
		br.close();
		}
		catch(IOException e) {
			System.err.println("Error: " + e);
		}
		if(removeHeader)
			lines.remove(0);
		
		this.messages = lines;
		
		
	}
	
	
		
	}
