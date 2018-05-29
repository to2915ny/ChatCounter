package edu.handong.csee.java.chatcounter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map.Entry;

public class FileWriter {
	public void getOpFile(HashMap<String,Integer> nameAndMessage) {


		String fileName = "outputResult.csv";
		PrintWriter output = null;

		try {
			output = new PrintWriter(fileName);

			for(Entry<String, Integer> in : nameAndMessage.entrySet()) {
				output.println(in.getValue());
				System.out.println(in.getValue());
			}
		} catch(FileNotFoundException e) {
			System.out.println("Error occured to open " + fileName);
			System.exit(0);
		}
		output.close();
	}

}
