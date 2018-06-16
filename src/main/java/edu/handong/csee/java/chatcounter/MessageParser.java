package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
	
	
	public void parseCSV(ArrayList<String> macMessages) {
		
		String pattern = "([0-9]+-[0-9]+-[0-9]+ [0-9]+:[0-9]+:[0-9]+),\"([^\"]+)\",\"([^\"]+)";
		String pattern2 = "([0-9]+-[0-9]+-[0-9]+ [0-9]+:[0-9]+:[0-9]+),\"([^\"]+)\",\"([^\"]+)\"";
		
		Pattern r = Pattern.compile(pattern);
		Pattern r1 = Pattern.compile(pattern2);
		
		for(String line : macMessages) {
			Matcher match = r.matcher(line);
			Matcher match1 = r1.matcher(line);
			
			if(match.find()) {
				time.add(match.group(1));
				name.add(match.group(2));
				message.add(match.group(3));
				macLine.add(match.group(1)+" "+"<"+match.group(2)+">"+" "+match.group(3));
			}
			
			
			if(match1.find()) {
				time.add(match1.group(1));
				name.add(match1.group(2));
				message.add(match1.group(3));
				macLine.add(match1.group(1)+" "+"<"+match1.group(2)+">"+" "+ match1.group(3));
			}
		
			
		}
		
		
		
		
		
	}

	
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
		
	
	