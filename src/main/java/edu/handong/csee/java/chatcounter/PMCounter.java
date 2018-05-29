package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Lists;

public class PMCounter {
		private ArrayList<String> name = new ArrayList<String>();
		private ArrayList<String> trash = new ArrayList<String>();
		public HashMap<String, Integer> count = new HashMap<String, Integer>();
		public ArrayList<String>count (ArrayList<String> lines) {	
			
		Pattern pattern1 = Pattern.compile("([0-9]{4}. [0-9]+. [0-9]+. .+),(.+), \\[(.+)\\]");//txtkor
		Pattern pattern2 = Pattern.compile("([0-9]+-[0-9]+-[0-9]+ [0-9]+:[0-9]+:[0-9]+), (.+), \\[(.+)\\]");//csv
		Pattern pattern3 = Pattern.compile("([A-Za-z]+, [A-Za-z]+ [0-9]+, [0-9]+) ([0-9]+:[0-9]+ [A-Z]+), (.+), \\[(.+)\\]");	
		
		
		for(String line : lines) {
			
			if(line.contains("null"))
				trash.add(line);
			
			else {
				
				Matcher match = pattern1.matcher(line);
				 
				if(match.find()) 
					name.add(match.group(2));
					
				
				Matcher match1 = pattern2.matcher(line);
				if(match1.find())
					name.add(match1.group(2));
					
					
				Matcher match2 = pattern3.matcher(line);
				if(match2.find())
					name.add(match2.group(2));
					
					
			}
			
			
			
		
		
		}
		return name;
		}
			
			
	
			
			 	
			/**public HashMap<String,Integer> countInfo(ArrayList<Lists> list) {
				HashMap<String,Integer> nameAndMessage = new HashMap<String,Integer>();
				int length = list.size();


				for(int i=0 ; i<length ; i++){

					int count=0;
					Lists person = list.get(i);
					
					

				return nameAndMessage;

			}

		}
**/
		
		
		
	}
