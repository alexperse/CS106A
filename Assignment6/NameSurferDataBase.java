/*

Stanford CS106A - Programming Methodology
Assignment6: NameSurfer (Database)
Author: Alex Perse - http://github.com/alexperse

 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import acm.util.*;
import java.io.*;
import java.util.*;

public class NameSurferDataBase implements NameSurferConstants {

/**
 * Creates a new NameSurferDataBase and initializes it using the
 * data in the specified file.  The constructor throws an error
 * exception if the requested file does not exist or if an error
 * occurs as the file is being read.
 */
	public NameSurferDataBase(String filename) {

		try {
			BufferedReader rd = new BufferedReader(new FileReader("names-data.txt"));

			while(true) {
				String line = rd.readLine();
				if (line == null) break;
				NameSurferEntry entry = new NameSurferEntry(line);
				nameDataBase.put(entry.getName(), entry);
			}
			rd.close();
		}
		catch (IOException ex) {
			throw new ErrorException(ex);
		}
	}
/* Method: findEntry(name) */

	public NameSurferEntry findEntry(String name) {
		name = checkName(name);
		return nameDataBase.get(name);
	}

	private String checkName(String name) {
		char firstLetter = name.charAt(0);
		if (Character.isLowerCase(firstLetter)) {
			firstLetter = Character.toUpperCase(firstLetter);
		}
		String otherLetters = name.substring(1);
		otherLetters = otherLetters.toLowerCase();

		name = firstLetter + otherLetters;
		return name;
	}

	private HashMap<String, NameSurferEntry> nameDataBase = new HashMap<String, NameSurferEntry>();

}
