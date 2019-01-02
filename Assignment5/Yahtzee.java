/*

Stanford CS106A - Programming Methodology
Assignment5: Yahtzee
Author: Alex Perse - http://github.com/alexperse

*/

import java.util.Arrays;
import java.util.stream.IntStream;

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	public void run() {
		setupPlayers();
		initDisplay();
		playGame();
	}

	/**
	 * Prompts the user for information about the number of players, then sets up the
	 * players array and number of players.
	 */
	private void setupPlayers() {
		nPlayers = chooseNumberOfPlayers();

		/* Set up the players array by reading names for each player. */
		playerNames = new String[nPlayers];
		for (int i = 0; i < nPlayers; i++) {
			/* IODialog is a class that allows us to prompt the user for information as a
			 * series of dialog boxes.  We will use this here to read player names.
			 */
			IODialog dialog = getDialog();
			playerNames[i] = dialog.readLine("Enter name for player " + (i + 1));
		}
	}

	/**
	 * Prompts the user for a number of players in this game, reprompting until the user
	 * enters a valid number.
	 *
	 * @return The number of players in this game.
	 */
	private int chooseNumberOfPlayers() {
		/* See setupPlayers() for more details on how IODialog works. */
		IODialog dialog = getDialog();

		while (true) {
			/* Prompt the user for a number of players. */
			int result = dialog.readInt("Enter number of players");

			/* If the result is valid, return it. */
			if (result > 0 && result <= MAX_PLAYERS)
				return result;

			dialog.println("Please enter a valid number of players.");
		}
	}

	/**
	 * Sets up the YahtzeeDisplay associated with this game.
	 */
	private void initDisplay() {
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
	}

	/**
	 * Actually plays a game of Yahtzee.  This is where you should begin writing your
	 * implementation.
	 */
	private void playGame() {

		int currentRound = 0;

		while (currentRound < N_SCORING_CATEGORIES) {
			while(currentPlayer <= nPlayers) {
				aSingleTurn();
				currentPlayer++;
				if (currentPlayer >= nPlayers) {
					currentPlayer = 0;
					currentRound++;
					break;
				}
			}
		}
		calculateWinner();
		display.printMessage("Congratulations, "+playerNames[winningPlayer] + "!"
				+ " You're the winner with a total score of: " +scoreCard[TOTAL][winningPlayer]);

	}

	private int calculateScore(int currentPlayer, int beginRange, int endRange) {
		int sum = 0;
		for (int i = beginRange; i < endRange; i++) {
			println("i is: " +i);
			sum += scoreCard[i][currentPlayer];
		}
		println("sum is: "+sum);
		return sum;

	}

	private void firstRoll() {
		display.printMessage(playerNames[currentPlayer] + "'s turn!"
				+ " Click the \"Roll Dice\" button to roll the dice.");

		//Initial roll
		display.waitForPlayerToClickRoll(currentPlayer);
		rollAllDice();
		display.displayDice(dice);
		display.printMessage("Select the dice you wish to re-roll"
				+ " and click \"Roll Again\"");
	}

	private void secondRoll() {
		//Second roll
		display.waitForPlayerToSelectDice();
		reRollSingleDice();
		display.displayDice(dice);
		display.printMessage("Select the dice you wish to re-roll"
				+ " and click \"Roll Again\"");
	}

	private void thirdRoll() {
		//Third roll
		display.waitForPlayerToSelectDice();
		reRollSingleDice();
		display.displayDice(dice);
		display.printMessage("Select a category for this roll");

		int category = checkIfCategoryAlreadySelected();

		int score = selectCategory(category);

		scoreCard[category][currentPlayer] = score;

		display.updateScorecard(category, currentPlayer, score);

		int totalScore = calculateScore(currentPlayer, ONES, N_CATEGORIES);

		display.updateScorecard(TOTAL, currentPlayer, totalScore);
	}

	private void calculateWinner() {
		for (int i = 0; i < nPlayers; i++) {
			scoreCard[UPPER_SCORE][i] = calculateScore(i, ONES, UPPER_SCORE);
			display.updateScorecard(UPPER_SCORE, i, scoreCard[UPPER_SCORE][i]);
			if (scoreCard[UPPER_SCORE][i] >= 63) {
				scoreCard[UPPER_BONUS][i] = 35;
				display.updateScorecard(UPPER_BONUS, i, scoreCard[UPPER_BONUS][i]);
			}
			scoreCard[LOWER_SCORE][i] = calculateScore(i,THREE_OF_A_KIND, CHANCE);
			display.updateScorecard(LOWER_SCORE, i, scoreCard[LOWER_SCORE][i]);
			scoreCard[TOTAL][i] = scoreCard[UPPER_SCORE][i] + scoreCard[UPPER_BONUS][i] +
					scoreCard[LOWER_SCORE][i];
			display.updateScorecard(TOTAL, i, scoreCard[TOTAL][i]);
		}
		int winningTotal = 0;
		winningPlayer = 0;
		for (int i = 0; i < nPlayers; i++) {
			if (scoreCard[TOTAL][i] > winningTotal) {
				winningTotal = scoreCard[TOTAL][i];
				winningPlayer = i;
			}
		}
	}

	private void aSingleTurn() {
		firstRoll();
		secondRoll();
		thirdRoll();
	}

	private int checkIfCategoryAlreadySelected() {
		int category = display.waitForPlayerToSelectCategory();

		while (scoreCard[category][currentPlayer] != 0) {
			display.printMessage("You already picked that "
					+ "category. Please choose another.");
			category = display.waitForPlayerToSelectCategory();
		}
		scoreCard[category][currentPlayer] = category;
		return category;
	}

	private int selectCategory(int category) {
		int score = 0;
		if (category <= SIXES || category == CHANCE) {
			score = categoryOneThroughSixAndChance(category);
			return score;
		}
		if (category == THREE_OF_A_KIND || category == FOUR_OF_A_KIND || category == YAHTZEE) {
			score = threeAndFourOfAKindAndYahtzee(category);
			return score;
		}
		if (category == FULL_HOUSE) {
			score = fullHouse(category);
			return score;
		}
		if (category == SMALL_STRAIGHT || category == LARGE_STRAIGHT) {
			score = smallAndLargeStraight(category);
			return score;
		}
		return score;
	}

	private void testAnOutcome() {
		scoreCard[ONES][0] = 3;
		scoreCard[TWOS][0] = 6;
		scoreCard[THREES][0] = 9;
		scoreCard[FOURS][0] = 16;
		scoreCard[FIVES][0] = 20;
		scoreCard[SIXES][0] = 24;

		firstRoll();
		secondRoll();
		thirdRoll();

	}

	private void rollAllDice() {
		dice[0] = rgen.nextInt(1, 6);
		dice[1] = rgen.nextInt(1, 6);
		dice[2] = rgen.nextInt(1, 6);
		dice[3] = rgen.nextInt(1, 6);
		dice[4] = rgen.nextInt(1, 6);
	}

	private void fakeRoll() {
		dice[0] = 5;
		dice[1] = 3;
		dice[2] = 5;
		dice[3] = 3;
		dice[4] = 5;
	}

	private int categoryOneThroughSixAndChance(int category) {
		int total = 0;
		if (category <= SIXES) {
		for (int i = 0; i < N_DICE; i++) {
			if (dice[i] == category + 1) {
				total += dice[i];
			}
		}
	}
		else if (category == CHANCE) {
			for (int i = 0; i < N_DICE; i++) {
				total += dice[i];
			}
		}
		return total;
}

	private int threeAndFourOfAKindAndYahtzee (int category) {
		int score = 0;
		int numberOfMatches = 1;
		Arrays.sort(dice);
		//Calculate number of matches
		for (int i = 1; i < dice.length; i++) {
			if (dice[i] == dice[i - 1]) {
				numberOfMatches++;
			}
		}

		//Three of a kind
		if (numberOfMatches == 3 | numberOfMatches == 4 && category == THREE_OF_A_KIND) {
			score = IntStream.of(dice).sum();
			return score;
			}
		//Four of a kind
		if (numberOfMatches == 4 | numberOfMatches == 5 && category == FOUR_OF_A_KIND) {
				score = IntStream.of(dice).sum();
				return score;
			}
		//Yahtzee
		if (numberOfMatches == N_DICE && category == YAHTZEE) {
				score = 50;
				return score;
			}
		return score;
	}

	private int smallAndLargeStraight(int category) {
		//Small straight (4 sequential numbers)
		score = 0;
		int numberOfMatches = 1;
		Arrays.sort(dice);

		for (int i = 0; i < N_DICE - 1; i++) {
			int firstElement = dice[i] + 1;
			int secondElement = dice[i+1];
			if (firstElement == secondElement){
				numberOfMatches++;
			}
	        }
		//Check for Small Straight
		if (category == SMALL_STRAIGHT && numberOfMatches == 4 |
				numberOfMatches == 5) {
			score = 30;
			return score;
		}
		//Check for Large Straight
		if (category == LARGE_STRAIGHT & numberOfMatches == 5) {
			score = 40;
			return score;
		}
		else {
			return score;
		}
	}

	private int fullHouse(int category) {
		//Full house
		int score = 0;
		boolean parents = false;
		boolean children = false;
		int[] counts = new int[6];
		for (int i=0; i<dice.length; i++)
		    //Count occurrences in array
		    counts[dice[i]-1]++;
		//Check for a 2 and a 3 (2 parents and 3 children - IE full house)
		for (int i: counts) {
		    if (i==2) {
		    	parents = true;
		    }
		    if (i==3){
			    	children = true;
			    }
		    }
		if (parents && children == true) {
			score = 25;
			return score;
		}
		return score;
	}

	private void reRollSingleDice() {
		for (int i = 0; i < N_DICE; i++) {
			if (display.isDieSelected(i) == true) {
				dice[i] = rgen.nextInt(1, 6);
			}
		}
	}



	/* Private instance variables */
	private int[] dice = new int[N_DICE];
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private int nPlayers;
	private int currentPlayer;
	private String[] playerNames;
	private int[][] scoreCard = new int[N_CATEGORIES][MAX_PLAYERS];
	private int winningPlayer;
	private int score = 0;
	private YahtzeeDisplay display;
}
