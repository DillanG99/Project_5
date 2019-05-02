import java.util.ArrayList;

/**
 * This class calculates certain data using the Ascii values of the characters in a 4-letter string
 * @author Dillan Gibbons
 *
 */
public class MesoAscii extends MesoAsciiAbstract{
	private String station;
	
	/**
	 * sets the value of station which stores the 4-letter StID to be used in calculations
	 * @param station
	 */
	public MesoAscii(String station) {
		this.station = station;
	}
	
	/**
	 * gets the ascii values of each character in a 4 letter string
	 * @param station
	 * @return an array of int storing the Ascii values of the characters in the 4-letter string
	 * each index corresponds to the same index of the string being used (i.e. int[0] = String.charAt(0))
	 */
	private static ArrayList<Integer> getAscii(String station) {
		ArrayList<Integer> asciiVals = new ArrayList<Integer>();
		for(int index = 0; index < station.length(); ++index) {
			int currAscii = station.charAt(index);
			asciiVals.add(currAscii);
		}
		return asciiVals;
	}
	
	/**
	 * Calculates the average of the Ascii values of the StID String rounded using basic rounding rules
	 * (i.e. if the decimal value on the average is 0.5 or greater it is rounded up otherwise it is rounded
	 * down
	 * @return the asciiAverage - the average of the Ascii values of StID
	 */
	@Override
	public int calAverage() {
		ArrayList<Integer> ascii = MesoAscii.getAscii(this.station);
		int total = 0;
		
		for(Integer i : ascii) {
			total = total + i;
		}
		
		double average = total / ascii.size();
		
		int asciiCeil = (int)Math.ceil(average);
		int asciiFloor = (int)Math.floor(average);
		
		int asciiAverage;
		if(average - asciiFloor >= 0.5) {
			asciiAverage = asciiCeil;
		}
		else {
			asciiAverage = asciiFloor;
		}
		return asciiAverage;
	}

}