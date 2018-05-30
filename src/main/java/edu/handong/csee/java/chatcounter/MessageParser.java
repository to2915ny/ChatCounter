package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MessageParser {
	
	
	private String date = null;
	private String name = null;
	private String message = null;
	private String temp = null;
	private ArrayList<String> trash = new ArrayList<String>();
	private ArrayList<String> parsed = new ArrayList<String>();
	
	
	
	public void parseCSV(ArrayList<String> macMessages) {
		
		String pattern1 = "([0-9]+-[0-9]+-[0-9]+ [0-9]+:[0-9]+:[0-9]+),\"([^\"]+)\",\"([^\"]+)";//ends with no " at the end
		String pattern2 = "([0-9]+-[0-9]+-[0-9]+ [0-9]+:[0-9]+:[0-9]+),\"([^\"]+)\",\"([^\"]+)\"";
		
		Pattern r = Pattern.compile(pattern1);
		Pattern r1 = Pattern.compile(pattern2);
		
		for(String line : macMessages) {
			
		if(line.contains("Photo")||line.contains("사진")|| line.contains(" joined this chatroom.") || 
				line.contains("(Emoticon)") || line.contains("(이모티콘)") || line.contains(" left this chatroom.")) {
			trash.add(line);
		}
		
		else {
			Matcher match = r.matcher(line);
			if(match.find()) {
				date = match.group(1);
				name = match.group(2);
				message= match.group(3);
				
			}
			
		
		
		
			Matcher match1 = r1.matcher(line);
			if(match1.find()) {
				date = match1.group(1);
				name = match1.group(2);
				message = match1.group(3);
			}
			
			
		}
		parsed.add(" " + date+","+name+"," +" " + "["+message+"]" );
		
		}
		parsed.remove(0);
		parsed.remove(0);
		
		
		
	}

	
	public void parseTxt(ArrayList<String> winMessage) {
	 	String pattern1 ="-+ ([0-9]{4}. [0-9]+. [0-9]+. .+) -+"; // 한
			String pattern2 = "-+ ([A-Za-z]+, [A-Za-z]+ [0-9]+. [0-9]+) -+"; //eng
			String pattern3 = "\\[(.+)\\] \\[(.+)\\] (.+)";
			
			Pattern r = Pattern.compile(pattern1);
			Pattern r1 = Pattern.compile(pattern2);
			Pattern r2 = Pattern.compile(pattern3);
			
		for(String line : winMessage) {
			if(line.contains("photo")||line.contains("사진")|| line.contains(" joined this chatroom.") || 
				line.contains("(Emoticon)") || line.contains("(이모티콘)") || line.contains(" left this chatroom.")) {
			trash.add(line);
		}
			
			
			else{
		
				Matcher match = r.matcher(line);
				 
				if(match.find()) {
					date = match.group(1);
					name = null;
					message = null;
					temp = match.group(1);
					
				}
				Matcher match1 = r1.matcher(line);
				if(match1.find()){
					date = match1.group(1);
					name= null;
					message = null;
					temp = match1.group(1);}
					
				Matcher match2 = r2.matcher(line);
				if(match2.find()){
					date = temp + " " + match2.group(2);
					this.name = match2.group(1);
					this.message = match2.group(3);}
					}
					
					
					parsed.add(" " + date+","+this.name+"," +" "+ "["+message+"]");
			
		}
		
			
				
		
			
		}
	public ArrayList<String> returnParsed() {
		return parsed;
	}
	
	
	
	}
		
	
	