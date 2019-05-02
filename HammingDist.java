

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
		  */
		 public HammingDist(String str1) {
			 this.station1 = str1;
			
		 }
		
		 public static String[] findWordsOfHamming(String refStr,int userHamming, ArrayList<String> vals) {
			 int counter = 0;
			 ArrayList<String> wordsOfHamming = new ArrayList<String>();
			for(String s : vals) {
				 int currHammDist = 0;
				 for(int index = 0; index < NUMBER_OF_CHAR; ++index) {
					 if(refStr.equalsIgnoreCase(s)) {
						 break;
					 }
					 char ref1 = refStr.charAt(index);
					 char ref2 = s.charAt(index);
					 int comparator = Character.compare(ref1, ref2);
					 if(comparator < 0 || comparator > 0) {
						 ++currHammDist;
					 }
				 }
				 if(currHammDist == userHamming) {
					 wordsOfHamming.add(s);
				 }
			 }
			String[] placeHolder = new String[0];
			String[] words = wordsOfHamming.toArray(placeHolder);
			return words;
		 }
		public static int findHammingList(String station, int userHamming, ArrayList<String> words) {
			 int counter = 0;
				 for (String s : words) {
					 int currHammDist = 0;
				 for(int index = 0; index < NUMBER_OF_CHAR; ++index) {
					 if(station.equalsIgnoreCase(s)) {
						 break;
					 }
					 char ref1 = station.charAt(index);
					 char ref2 = s.charAt(index);
					 int comparator = Character.compare(ref1, ref2);
					 if(comparator < 0 || comparator > 0) {
						 ++currHammDist;
					 }
				 }
				 if(currHammDist == userHamming) {
					 ++counter;
				 }
			 }
			return counter;
		 }
	
	}
	

