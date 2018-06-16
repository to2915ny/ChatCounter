package edu.handong.csee.java.chatcounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * This class lists all the files into the array then extracts the messages from each file into two
 * separate ArrayList one for CSV and one for TXT
 * @author to291
 *
 */
public class FileLoader {
	
	private ArrayList<String> winMessages = new ArrayList<String>();
	private ArrayList<String> macMessages = new ArrayList<String>();
	
	
	
/**
 * This method gets all the filename then separates the messages from each files : one for CSV and one for TXT
 * @param path
 */
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
			
		
	
		
	
	/**
	 * This method gets the file from the directory then puts the list of names into an ArrayList
	 * @param path
	 * @return
	 */
	public ArrayList<File> getFileNames(String path){
		
		ArrayList<File> fileNames = new ArrayList<File>();
		
		File myPath = new File(path);
		myPath.listFiles();
		for(File fileName: myPath.listFiles()) {
			
			fileNames.add(fileName);
		}
		return fileNames;
		}
	
	/**
	 * This is a getter for txt messages
	 * @return
	 */
	public ArrayList<String> returnWinMessages(){
		
		return winMessages;
	}
/**
 * This is a getter for csv messages
 * @return
 */
	public ArrayList<String> returnMacMessages(){
		
		return macMessages;
	}
	
	
		
	}
