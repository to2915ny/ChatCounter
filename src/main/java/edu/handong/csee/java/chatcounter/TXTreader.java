package edu.handong.csee.java.chatcounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TXTreader implements Runnable {
	
	
	
	
	ArrayList<String> winMessages = new ArrayList<String>();
	File path;
	
	public TXTreader(File path) {
		
		this.path = path;
	}
	
	public void run() {
		readLine(path);
	}
	
	public void readLine(File path){
	try {
		
		BufferedReader br = new BufferedReader(new FileReader(path));
		String thisLine = null;
		while((thisLine = br.readLine()) != null ) {

			winMessages.add(thisLine);

		}
		br.close();
	}
	catch(IOException e) {
		System.err.println("Error: " + e);
	}
	}
}
