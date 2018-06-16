package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class parses each messages from txt file and csv file into the format that i want it to be 
 * Used regular expression to extract time name and message and concatenated the lines once again
 * in the same format so Redundancy check will be alot easier
 * @author to291
 *
 */
public class MessageParser {
	
	private ArrayList<String> newTime = new ArrayList<String>();
	private ArrayList<String> time = new ArrayList<String>();
	private ArrayList<String> name = new ArrayList<String>();
	private ArrayList<String> message = new ArrayList<String>();
	private ArrayList<String> winTime = new ArrayList<String>();
	private ArrayList<String> winName = new ArrayList<String>();
	private ArrayList<String> winMsg = new ArrayList<String>();
	
	
	private ArrayList<String> macLine = new ArrayList<String>();
	private ArrayList<String> winLine = new ArrayList<String>();
	
	/**
	 * This method takes in messages in CSV file and parses it in the format i want
	 * @param macMessages
	 */
	public void parseCSV(ArrayList<String> macMessages) {
		
		
		
		Pattern r = Pattern.compile(".+\\s([0-9]+:[0-9]+):[0-9]+,\"(.+)\",\"(.+)\"");
		
		
		for(String line : macMessages) {
			Matcher match = r.matcher(line);
			
			
			while(match.find()) {
				time.add(match.group(1));
				name.add(match.group(2));
				message.add(match.group(3));
				macLine.add("<"+match.group(2)+">"+" "+match.group(1)+" "+match.group(3));
				
			}
			
			
			
			
		}
		
		
		
		
		
	}

	/**
	 * this method takes in messages from a txt file and parses it into a format that i want
	 * also i changed the hours into 24hour clock format so the time from csv and txt are coherent
	 * @param winMessage
	 */
	public void parseTxt(ArrayList<String> winMessage) {
	 	
			String pattern = "\\[(.+)\\] \\[(.+)\\] (.+)";
			
			Pattern r = Pattern.compile(pattern);
			
			
		for(String line : winMessage) {
			
			
			
			
		
				Matcher match = r.matcher(line);
				 
				while(match.find()) {
					winName.add(match.group(1));
					winTime.add(match.group(2));
					winMsg.add(match.group(3));
					
					
				}
						
			
		}
		
			
		timeEqual();		
		
			
		}
	/**
	 * This is the method i mentioned earlier which is invoked in the txtparse method to obtain 24hour clock format
	 */
	private void timeEqual(){
		String hour =null;
		String min = null;
		int temp;
		for(int i=0; i<winTime.size(); i++) {
			
			String time = winTime.get(i);
			
			if(time.contains("오전")||time.contains("AM")) {
				Pattern a = Pattern.compile("([0-9]+):([0-9]+)");
				Matcher match = a.matcher(time);
				
				while(match.find()) {
					
					hour = match.group(1);
					min = match.group(2);
				}
				if(hour.contains("12")) {
					hour = "00";
					newTime.add(i,hour+":"+min);
				}
				else if(hour.length() ==1) 
					newTime.add(i,"0"+hour+":"+min);
				else
					newTime.add(i,hour+":"+min);
				}
			else if(time.contains("오후")||time.contains("PM")) {
				Pattern b = Pattern.compile("([0-9]+):([0-9]+)");
				Matcher match1 = b.matcher(time);
				
				while(match1.find()) {
					hour = match1.group(1);
					min = match1.group(2);
					
				}
				if(!hour.contains("12")) {
				temp = Integer.parseInt(hour);
				temp = temp + 12;
				hour = String.valueOf(temp);
				}
				newTime.add(i,hour+":"+min);
			}
		}
		
		for(int i =0; i<winName.size();i++)
			winLine.add(i,"<"+winName.get(i)+">"+" "+newTime.get(i)+" "+winMsg.get(i));
		
	}
	public ArrayList<String> getWinLine(){
		return winLine;
	}
	public ArrayList<String> getMacLine(){
		return macLine;
	}
	public ArrayList<String> getWinName(){
		return winName;
	}
	public ArrayList<String> getMacName(){
		return name;
	}
	
	
	}
		
	
	