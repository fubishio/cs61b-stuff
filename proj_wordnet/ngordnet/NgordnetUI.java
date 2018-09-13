package ngordnet;
import java.io.FileNotFoundException;

import java.util.*;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.In;

/*@author [Johnathan Chow] */

public class NgordnetUI {
    public static void main(String[] args) throws FileNotFoundException {
        In in = new In("./ngordnet/ngordnetui.config");
        System.out.println("Reading ngordnetui.config...");

        String wordFile = in.readString();
        String countFile = in.readString();
        String synsetFile = in.readString();
        String hyponymFile = in.readString();
        WordNet wn = new WordNet(synsetFile, hyponymFile);
        NGramMap ng = new NGramMap(wordFile, countFile);
        System.out.println("\nBased on ngordnetui.config, using the following: "
                           + wordFile + ", " + countFile + ", " + synsetFile +
                           ", and " + hyponymFile + ".");
        int startDate = 1500;
        int endDate = 2008;
        while (true) {
            
        	System.out.print("> ");
            String line = StdIn.readLine();
            String[] rawTokens = line.split(" ");
            String command = rawTokens[0];
            String[] tokens = new String[rawTokens.length - 1];
            System.arraycopy(rawTokens, 1, tokens, 0, rawTokens.length - 1);
            
            Plotter toPlot;
            
            switch (command) {
                case "quit": 
                    return;
                case "help":
                    In in1 = new In("help.txt");
                    String helpStr = in1.readAll();
                    System.out.println(helpStr);
                    break;  
                case "range": 
                	if (Integer.parseInt(tokens[0]) >= Integer.parseInt(tokens[1])) {
                		System.out.println("Invalid year range");
                	} else {
	                	startDate = Integer.parseInt(tokens[0]); 
	                    endDate = Integer.parseInt(tokens[1]);
	                    System.out.println("Start date: " + startDate);
	                    System.out.println("End date: " + endDate);
                	}
	                break;
                case "count":
                    if (tokens.length == 2) {
	                	String countWord= tokens[0];
	                    int countYear = Integer.parseInt(tokens[1]);
	                    System.out.println(ng.countInYear(countWord, countYear));
                    }
                    break;
                    
                case "hyponyms":
                	if (tokens.length == 1) {
                		String word = tokens[0];
                		if (wn.hyponyms(word).isEmpty()) {
                			System.out.println("No hyponyms in data");
                		} else {
                		System.out.println(wn.hyponyms(word));
                		}
                	} else {
                		System.out.println("Please enter only one word");
                	}
                	break;
                case "history":
                	toPlot = new Plotter();
                	toPlot.plotAllWords(ng, tokens, startDate, endDate);
                	break;
                case "hypohist":
                    /*List<String> hypList = new ArrayList<String>();
                    for (String x : tokens) {
                    	for (String y : wn.hyponyms(x)) {
                    		hypList.add(y);
                    	}
                    }
                    String[] hypArray = hypList.toArray(new String[hypList.size()]);*/
                    toPlot = new Plotter();
                    toPlot.plotCategoryWeights(ng, wn, tokens, startDate, endDate);
                    break;
                case "wordlength":
                	YearlyRecordProcessor yRP = new WordLengthProcessor();
                	toPlot = new Plotter();
                	toPlot.plotProcessedHistory(ng, startDate, endDate, yRP);
                	break;
                case "zipf":
                	if (tokens.length == 1) {
                	    toPlot = new Plotter();
                	    toPlot.plotZipfsLaw(ng, Integer.parseInt(tokens[0]));
                	} else {
                		System.out.println("Not enough arguments");
                	}
                    break;
                default:
                    System.out.println("Invalid command.");  
                    break;
            }
        }
    }
} 
