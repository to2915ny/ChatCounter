package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;
import java.util.HashMap;



public class ChatCounter {

	public static void main(String[] args) {
		ChatCounter runner = new ChatCounter();
		runner.run();

	}
	public void run() {
		FileLoader fileName = new FileLoader();
		
		
		fileName.readDirectory("/Users/seungyonglee/Documents/java git/ChatCounter/ChatCount");
		
		//fileName.returnWinMessages()
		MessageParser parse = new MessageParser();
		
		parse.parseCSV(fileName.returnMacMessages());
		parse.parseTxt(fileName.returnWinMessages());
		
		ArrayList<String> list = parse.returnParsed();
		
		RedundancyChecker redun = new RedundancyChecker();
		
		//System.out.println(list);
		PMCounter counter = new PMCounter();
		
		ArrayList<String> newList = counter.count(redun.eliminateRepeat(list));
		HashMap<String, Integer> count = new HashMap<String, Integer>();
		
		count = counter.countinfo(newList);
		//fileName.returnMacMessages()
		
		
		
			
		
		
		
		
		
		
	}

}
