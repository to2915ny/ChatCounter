package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;



public class ChatCounter {
	
	String path;
	String output;
	boolean verbose;
	boolean help;
	
	public static void main(String[] args) {
		ChatCounter runner = new ChatCounter();
		runner.run();

	}
	public void run() {
		//Options options = createOptions();

		/**if(parseOptions(options, args)) {
			if (help){
				printHelp(options);
				return;
			}**/
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
		//ArrayList<String>check = redun.eliminateRepeat(list);
		//System.out.println(list);
		ArrayList<String> newList = counter.count(redun.eliminateRepeat(list));
		
		//System.out.println(newList);
		ArrayList<String> names = newList;
		
		TreeSet<String> unique = new TreeSet<String>(names);
		ArrayList<String> name = new ArrayList<String>(unique);
		//System.out.println(names);
		
		//counter.counter(name);
		
		FileWriter write = new FileWriter();
		write.writeFile(counter.counter(name));
		
		
		
		
		
			
		
		
		
		
		
		
		}
	
	}
		/**private boolean parseOptions(Options options, String[] args) {
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
		
		private void printHelp(Options options) {
			// automatically generate the help statement
			HelpFormatter formatter = new HelpFormatter();
			String header = "CLI test program";
			String footer ="\nPlease report issues at https://github.com/to2915ny/CLIExample/issues";
			formatter.printHelp("CLIExample", header, options, footer, true);
		}**/
