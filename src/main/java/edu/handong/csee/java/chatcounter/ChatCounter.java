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
		runner.run(args);

	}
	public void run(String[] args) {
		Options options = createOptions();

		if(parseOptions(options, args)) {
			if (help){
				printHelp(options);
				return;
			}
		FileLoader fileName = new FileLoader();
		MessageParser parse = new MessageParser();
		RedundancyChecker redun = new RedundancyChecker();
		PMCounter count = new PMCounter();
		FileWriter write = new FileWriter();
		
		
		fileName.readDirectory(path);
		parse.parseTxt(fileName.returnWinMessages());
		parse.parseCSV(fileName.returnMacMessages());
		
		ArrayList<String> one = new ArrayList<String>();
		one.addAll(parse.getWinLine());
		one.addAll(parse.getMacLine());
		
		
		
		HashMap<String, Integer> hashlist = new HashMap<String, Integer>();
		hashlist = count.countData(redun.eliminateRepeat(one));
		
		write.writeFile(hashlist);
		
		
		}
	
	}
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
		}
}
