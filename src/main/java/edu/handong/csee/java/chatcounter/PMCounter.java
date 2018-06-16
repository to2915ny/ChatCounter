package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Lists;
/**
 * This class counts the number of chat made by each person in class and returns them in HashMap
 * which is convenient to express a String with a corresponding integer
 * @author to291
 *
 */
public class PMCounter {
	HashMap<String,Integer> counter = new HashMap<String,Integer>();
	/**
	 * This method takes in ArrayList and extracts names from each line and counts names and puts in a hashmap
	 * @param list
	 * @return
	 */
	public HashMap<String, Integer> countData(ArrayList<String> list){
		for(String readLine : list) {
			int start = readLine.indexOf('<'); 
			int end = readLine.indexOf('>');
			int count;
			if(start>=0) {
				String name = readLine.substring(start+1, end);
				if(counter.get(name)!=null) {
					count = counter.get(name);
					count ++;
					counter.put(name, count);
				}
				else counter.put(name, 1);
			}
		}
		return counter;
	}



}
