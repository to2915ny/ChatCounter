package edu.handong.csee.java.chatcounter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class FileWriter {
	public void writeFile(HashMap<String,Integer> nameAndMessage) {


		String fileName = "outputResult.txt";
		PrintWriter output = null;

		try {
			output = new PrintWriter(fileName);
			
			for(Entry<String, Integer> in : nameAndMessage.entrySet()) {
				output.println(in);
				System.out.println(in);
			}
		} catch(FileNotFoundException e) {
			System.out.println("Error occured to open " + fileName);
			System.exit(0);
		}
		output.close();
	}

}

