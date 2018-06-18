package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * This class checks if any redundant messages are made in the chat if the user made the exact same message at the exact time then
 * the two corresponding messages are merged to one
 * @author to291
 *
 */
public class RedundancyChecker {

	static ArrayList<String> checker;

/**
 * This method eliminates any repeated line
 * @param line
 * @return
 */
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
