package edu.handong.csee.java.chatcounter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.*;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;


/** 
 * This class is controller class ChatCounter
 * threadNum and getNumThread added for thread branch
 * these are used for processing file lines
 * @author to291
 *
 */
public class ChatCounter {

	String path;
	String output;
	boolean verbose;
	boolean help;
	int threadNum;
	String getThreads;

	private File dirc =null;
	/**
	 * This is the main method which instantiate ChatCounter as runner and run the program
	 * @param args
	 */
	public static void main(String[] args) {
		ChatCounter runner = new ChatCounter();
		runner.run(args);

	}
	/**
	 * This is the actual control method which takes in path as parameter and 
	 * controls the flow of the program until output is produced
	 * @param args
	 */
	public void run(String[] args) {
		Options options = createOptions();

		if(parseOptions(options, args)) {
			if (help){
				printHelp(options);
				return;
			}
			//FileLoader fileName = new FileLoader();
			MessageParser parse = new MessageParser();
			RedundancyChecker redun = new RedundancyChecker();
			PMCounter count = new PMCounter();
			FileWriter write = new FileWriter();
			ArrayList<CSVreader> csv = new ArrayList<CSVreader>();
			ArrayList<TXTreader> txt = new ArrayList<TXTreader>();
			ArrayList<String> csvMsg = new ArrayList<String>();
			ArrayList<String> txtMsg = new ArrayList<String>();
			this.dirc = new File(path);

			System.out.println("Please enter the number of threads  : ");
			Scanner keyboard = new Scanner(System.in);
			String Decoy = keyboard.nextLine();
			getThreads="4";
			threadNum = Integer.parseInt(getThreads);


			ExecutorService executor = Executors.newFixedThreadPool(threadNum);
			for(File file:dirc.listFiles())
			{
				if (file.getName().contains(".csv")) {

					Runnable worker = new CSVreader(file); //instantiate Runnable as worker and implements on CSVreader
					executor.execute(worker);
					csv.add((CSVreader)worker);
				}
			}

			executor.shutdown();
			while(!executor.isTerminated()) {

			}			  	

			for(CSVreader runner : csv) {
				csvMsg.addAll(runner.macMessages);
			}

			ExecutorService executor2 = Executors.newFixedThreadPool(threadNum);

			for(File file:dirc.listFiles())
			{
				if (file.getName().contains(".txt")) {

					Runnable worker = new TXTreader(file);
					executor2.execute(worker);
					txt.add((TXTreader)worker);
				}
			}

			executor2.shutdown();
			while(!executor2.isTerminated()) {

			}			  	


			for(TXTreader runner : txt) {
				txtMsg.addAll(runner.winMessages);
			}
			//fileName.readDirectory(path);
			parse.parseTxt(txtMsg);
			parse.parseCSV(csvMsg);

			ArrayList<String> one = new ArrayList<String>();
			one.addAll(parse.getWinLine());
			one.addAll(parse.getMacLine());



			HashMap<String, Integer> hashlist = new HashMap<String, Integer>();
			hashlist = count.countData(redun.eliminateRepeat(one));

			write.writeFile(hashlist);


		}

	}
	/**
	 * CLI methods which parses different method as user types in the corresponding option
	 * @param options
	 * @param args
	 * @return
	 */
	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);

			path = cmd.getOptionValue("p");
			verbose = cmd.hasOption("v");
			help = cmd.hasOption("h");

		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
	}

	// Definition Stage
	/**
	 * This is the Definition Stage where each options are defined
	 * @return
	 */
	private Options createOptions() {
		Options options = new Options();

		// add options by using OptionBuilder
		options.addOption(Option.builder("p").longOpt("path")
				.desc("Set a path of a directory or a file to display")
				.hasArg()
				.argName("Path name to display")
				.required()
				.build());

		// add options by using OptionBuilder
		options.addOption(Option.builder("v").longOpt("verbose")
				.desc("Display detailed messages!")
				//.hasArg()     // this option is intended not to have an option value but just an option
				.argName("verbose option")
				//.required() // this is an optional option. So disabled required().
				.build());

		// add options by using OptionBuilder
		options.addOption(Option.builder("h").longOpt("help")
				.desc("Help")
				.build());

		return options;
	}
	/**
	 * This method prints out various instruction for clear and easy to use environment for the user
	 * @param options
	 */
	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "ChatCounting test program";
		String footer ="\nPlease report issues via email to2915ny@me.com";
		formatter.printHelp("ChatCounter", header, options, footer, true);
	}
}
