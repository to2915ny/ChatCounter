package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Lists;

public class PMCounter {
	HashMap<String,Integer> counter = new HashMap<String,Integer>();
	
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
