package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;

public class RedundancyChecker {

	static ArrayList<String> checker;

	public ArrayList<String> eliminateRepeat(ArrayList<String> line){
		checker = new ArrayList<String>();
		for(int i=0; i < line.size(); i++) {

			if(!checker.contains(line.get(i))) {
				checker.add(line.get(i));				
			}

		}
		return checker;
	}		
}
