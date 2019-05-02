

	import java.io.*;
import java.util.ArrayList;
	/**
	 * Computes the hamming distance of two four letter Strings as well as 
	 * four letter Strings read from a file
	 * @author Dillan Gibbons
	 */
	public class HammingDist {
		 private String station1;
		 private int hammingDist;
		 private static final int NUMBER_OF_CHAR = 4;
		 private int[] station1Nodes;
		 
		 public HammingDist() {
			 
		 }
		 /**
		  * Constructor for HammingDist, takes in two String and
		  *  passes them as arguments to static methods to initialize other 
		  *  class variables
		  * 
		  * @param str1
		  * @param str2
		  * @throws IOException
		  */
		 public HammingDist(String str1) throws IOException {
			 this.station1 = str1;
			 
			 station1Nodes = new int[NUMBER_OF_CHAR];
			 this.station1Nodes[0] = HammingDist.findHammingFile(str1, 1, "Mesonet.txt");
			 this.station1Nodes[1] = HammingDist.findHammingFile(str1, 2, "Mesonet.txt");
			 this.station1Nodes[2] = HammingDist.findHammingFile(str1, 3, "Mesonet.txt");
			 this.station1Nodes[3] = HammingDist.findHammingFile(str1, 4, "Mesonet.txt");
		 }
		 /**
		  * Takes in a String and a file of strings and computes how many strings have a 
		  * specified hamming distance from reStr
		  * @param refStr
		  * @param userHamming
		  * @param fileName
		  * @return an int representing the number of Strings with a hamming distance of userHamming
		  * @throws IOException
		  */
		 public static int findHammingFile(String refStr,int userHamming, String fileName) throws IOException {
			 int counter = 0;
			 //construct BufferedReader
			 BufferedReader br = new BufferedReader(new FileReader(fileName));
			 String currLine = br.readLine();
			 while(currLine != null) {
				 int currHammDist = 0;
				 for(int index = 0; index < NUMBER_OF_CHAR; ++index) {
					 if(refStr.equalsIgnoreCase(currLine)) {
						 break;
					 }
					 char ref1 = refStr.charAt(index);
					 char ref2 = currLine.charAt(index);
					 int comparator = Character.compare(ref1, ref2);
					 if(comparator < 0 || comparator > 0) {
						 ++currHammDist;
					 }
				 }
				 if(currHammDist == userHamming) {
					 ++counter;
				 }
				 currLine = br.readLine();
			 }
			 //close reader
			 br.close();
			return counter;
		 }
		 
		 public static String[] findWordsOfHammingFile(String refStr,int userHamming, String fileName) throws IOException {
			 int counter = 0;
			 //construct BufferedReader
			 ArrayList<String> wordsOfHamming = new ArrayList<String>();
			 BufferedReader br = new BufferedReader(new FileReader(fileName));
			 String currLine = br.readLine();
			 while(currLine != null) {
				 int currHammDist = 0;
				 for(int index = 0; index < NUMBER_OF_CHAR; ++index) {
					 if(refStr.equalsIgnoreCase(currLine)) {
						 break;
					 }
					 char ref1 = refStr.charAt(index);
					 char ref2 = currLine.charAt(index);
					 int comparator = Character.compare(ref1, ref2);
					 if(comparator < 0 || comparator > 0) {
						 ++currHammDist;
					 }
				 }
				 if(currHammDist == userHamming) {
					 wordsOfHamming.add(currLine);
				 }
				 currLine = br.readLine();
			 }
			 //close reader
			 br.close();
			 String[] vals = new String[0];
			 String[] words = wordsOfHamming.toArray(vals);
			return words;
		 }
	}

