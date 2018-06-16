package edu.handong.csee.java.chatcounter;

import java.awt.List;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
/**
 * This class Writes all produces the final output of the program, ChatCounter
 * @author to291
 *
 */
public class FileWriter {
	/**
	 * This method takes HashMap which contains the name and the number of chat made and writes 
	 * result into txt file Also Collections class is used for making the results in descending order of contribution in chat
	 * @param nameAndMessage
	 */
	public void writeFile(HashMap<String,Integer> nameAndMessage) {

		ArrayList<String> sorting = new ArrayList<String>();
		sorting.addAll(nameAndMessage.keySet());
		String fileName = "outputResult.txt";
		PrintWriter output = null;
		Collections.sort(sorting, new Comparator<Object>() {

			public int compare(Object o1, Object o2) {
				Object v1 = nameAndMessage.get(o1);
				Object v2 = nameAndMessage.get(o2);
				return ((Comparable<Object>) v1).compareTo(v2);
			}	
		});
		Collections.reverse(sorting);
		try {
			output = new PrintWriter(fileName);



		} catch(FileNotFoundException e) {
			System.out.println("Error occured to open " + fileName);
			System.exit(0);
		}

		output.println("kakao_id,count");
		for(int i=0; i<sorting.size(); i++) {

			output.print(sorting.get(i));
			output.println(","+nameAndMessage.get(sorting.get(i)));
		}
		output.close();
	}

}

