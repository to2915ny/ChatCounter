package edu.handong.csee.java.chatcounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * This class implements Runnable so it can can implement thread when reading
 * the files
 * @author to291
 *
 */
public class CSVreader implements Runnable {

	ArrayList<String> macMessages = new ArrayList<String>();
	File path;
	/**
	 * This is a constructor which takes in path and saves it in instance variable path
	 * @param path
	 */
	public CSVreader(File path) {

		this.path = path;
	}
	/**
	 * executor runs and invokes method readFile
	 */
	public void run() {
		readLine(path);
	}
	/**
	 *  This method reads files and adds to ArrayList macMessages
	 * @param file
	 */
	public void readLine(File file){
		try {

			BufferedReader br = new BufferedReader(new FileReader(file));
			String thisLine = null;
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
