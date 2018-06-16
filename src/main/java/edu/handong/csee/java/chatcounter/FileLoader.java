package edu.handong.csee.java.chatcounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {
	
	private ArrayList<String> winMessages = new ArrayList<String>();
	private ArrayList<String> macMessages = new ArrayList<String>();
	
	
	

	public void readDirectory(String path) {
		
		ArrayList<File> fileName = getFileNames(path);
		
			
		
			
		
		for(File file :fileName) {
			String thisLine = null;
			if(file.toString().contains("txt")) {
		
			
			try {
				
				BufferedReader br = new BufferedReader(new FileReader(file));
				while((thisLine = br.readLine()) != null ) {
					
					winMessages.add(thisLine);
				
			}
			br.close();
			}
			catch(IOException e) {
				System.err.println("Error: " + e);
			}
		}
			else {
				try {
					
					BufferedReader br = new BufferedReader(new FileReader(file));
					while((thisLine = br.readLine()) != null ) {
						
						macMessages.add(thisLine);
					
				}
				br.close();
				}
				catch(IOException e) {
					System.err.println("Error: " + e);
				}
				
			}
		}
		
		}
			
		
	
		
	
	
	public ArrayList<File> getFileNames(String path){
		
		ArrayList<File> fileNames = new ArrayList<File>();
		
		File myPath = new File(path);
		myPath.listFiles();
		for(File fileName: myPath.listFiles()) {
			
			fileNames.add(fileName);
		}
		return fileNames;
		}
	
	
	public ArrayList<String> returnWinMessages(){
		
		return winMessages;
	}

	public ArrayList<String> returnMacMessages(){
		
		return macMessages;
	}
	
	
		
	}
