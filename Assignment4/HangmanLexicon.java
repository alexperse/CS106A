/*

Stanford CS106A - Programming Methodology
Assignment4: Hangman (Lexicon)
Author: Alex Perse - http://github.com/alexperse

*/

import acm.util.*;
import java.io.*;
import java.util.*;

public class HangmanLexicon {


	private ArrayList <String> strList = new ArrayList <String>();

	public HangmanLexicon() {
		try {
			BufferedReader rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			while (true) {
				String line = rd.readLine();
				if (line == null) break;
				strList.add(line);
			}
		}
		catch (IOException ex) {
			throw new ErrorException(ex);
		}

	}


/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return strList.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		return strList.get(index);
		}
}
