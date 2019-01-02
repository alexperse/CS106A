/*

Stanford CS106A - Programming Methodology
Assignment3: Supposed to be Breakout, overwritten with Hangman code ... 
Author: Alex Perse - http://github.com/alexperse

I am not sure how Breakout was overwritten with the code from Hangman ...

*/

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {

	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
	}

    public void run() {
    	HangmanLexicon hng = new HangmanLexicon();
    	int randomSelection = rgen.nextInt(1, hng.getWordCount() -1);
    	String theWord = hng.getWord(randomSelection);
    	String secretWord = hideTheWord(theWord);


    	canvas.reset();


    	println("Welcome to Hangman!");

    	//Main loop
    	while(isGameWon == false && numGuessesLeft > 0) {

    		inputAccepted  = false;

    		println("The word now looks like this: " +secretWord);
    		println("You have " +numGuessesLeft + " guesses left.");
    		String guess = readUserInput();

    		while (inputAccepted == false) {
    			guess = readUserInput();
    		}

    		secretWord = guessAndReveal(theWord, secretWord, guess);
    		isGuessCorrect(guess);
    		canvas.displayWord(secretWord);
    		isWordComplete(secretWord, theWord);

    	}

    }

    private String readUserInput() {
    	String guess = readLine("Your guess (single letter only please): ");
    	if (guess.length() > 1) {
    		println("One character only, please.");
    	} else {
    		guess = checkInputChar(guess);
    	}
    	return guess;
    }

    private void isWordComplete(String secretWord, String theWord) {
    	if (secretWord.equals(theWord)) {
    		println("Congratulations. You win.");
    		println("GAME OVER! ");
    		isGameWon = true;
    	}
    	else if (numGuessesLeft == 0) {
    		println("I'm sorry, you lost.");
    		println("The secret word was: " +theWord);
    	}
    }

    private void isGuessCorrect(String guess) {
    	if (guessedCorrectly == false) {
			println("There are no " +guess +"'s in this word.");
			char ch = guess.charAt(0);
			canvas.noteIncorrectGuess(ch);
			numGuessesLeft--;
		}
    	else if (guessedCorrectly == true) {
			println("That guess is correct.");
		}
    }

    private String checkInputChar(String guess) {
    	char ch = guess.charAt(0);
    	if (Character.isLowerCase(ch)) {
    		ch = Character.toUpperCase(ch);
    		inputAccepted = true;
    		return guess = Character.toString(ch);
    	}
    	else if (Character.isUpperCase(ch)) {
    		inputAccepted = true;
    		return guess;
    	}
    	else if (Character.isDigit(ch)) {
    		println("Letters only please. ");
    		inputAccepted = false;
    	}
    	else if (Character.isSpaceChar(ch)) {
    		println("Letters only please. No spaces. ");
    		inputAccepted = false;
    	}
    	return guess;
    }

    private String hideTheWord(String theWord) {
    	String secretWord = "";
    	for (int i = 0; i < theWord.length(); i++) {
    		secretWord += '-';
    	}
    	return secretWord;
    }

    private String guessAndReveal(String theWord, String secretWord, String guess) {
    	guessedCorrectly = false;
    	String temp = "";
    	for (int i = 0; i < theWord.length(); i++) {
    		char theWordChar = theWord.charAt(i);
    		char secretWordChar = secretWord.charAt(i);
    		char guessChar = guess.charAt(0);

    		if (guessChar == theWordChar) {
    			temp += theWord.charAt(i);
    			guessedCorrectly = true;
    			}
    		else if (guessChar != theWordChar) {
    			temp += secretWord.charAt(i);
    		}
    	}
    	return temp;
   }

    /** Private instance variables */
    private RandomGenerator rgen = RandomGenerator.getInstance();
    private boolean inputAccepted  = false;
    private boolean guessedCorrectly = false;
    private boolean isGameWon = false;
    private int numGuessesLeft = 8;
    private HangmanCanvas canvas;



}
