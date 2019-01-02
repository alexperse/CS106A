/*

Stanford CS106A - Programming Methodology
Assignment6: NameSurfer (Entry)
Author: Alex Perse - http://github.com/alexperse

 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;

import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

/* Constructor: NameSurferEntry(line) */
/**
 * Creates a new NameSurferEntry from a data line as it appears
 * in the data file.  Each line begins with the name, which is
 * followed by integers giving the rank of that name for each
 * decade.
 */

	private String name = "";
	//private String nums = "";
	private int[] decadeArray = new int[NDECADES];

	public NameSurferEntry(String line) {
		name = line.substring(0, line.indexOf(" "));
		String[] splitString = line.split(" ");
		decadeArray[0] = Integer.parseInt(splitString[1]);
		decadeArray[1] = Integer.parseInt(splitString[2]);
		decadeArray[2] = Integer.parseInt(splitString[3]);
		decadeArray[3] = Integer.parseInt(splitString[4]);
		decadeArray[4] = Integer.parseInt(splitString[5]);
		decadeArray[5] = Integer.parseInt(splitString[6]);
		decadeArray[6] = Integer.parseInt(splitString[7]);
		decadeArray[7] = Integer.parseInt(splitString[8]);
		decadeArray[8] = Integer.parseInt(splitString[9]);
		decadeArray[9] = Integer.parseInt(splitString[10]);
		decadeArray[10] = Integer.parseInt(splitString[11]);
	}

/**
 * Returns the name associated with this entry.
 */
	public String getName() {
		// You need to turn this stub into a real implementation //
		return name;
	}

/**
 * Returns the rank associated with an entry for a particular
 * decade.  The decade value is an integer indicating how many
 * decades have passed since the first year in the database,
 * which is given by the constant START_DECADE.  If a name does
 * not appear in a decade, the rank value is 0.
 */
	public int getRank(int decade) {
		return decadeArray[decade];
	}

/**
 * Returns a string that makes it easy to see the value of a
 * NameSurferEntry.
 */
	public String toString() {
		String result = "";
		result += "\"" + name + " [";
		for (int i=0; i<NDECADES - 1; i++) {
			result += decadeArray[i] + " ";
		}
		result += decadeArray[NDECADES - 1] + "]\"";

		return result;
	}
}
