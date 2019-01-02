/*

Stanford CS106A - Programming Methodology
Assignment4: Hangman (Canvas)
Author: Alex Perse - http://github.com/alexperse

*/

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

	int incorrectGuesses = 0;

/** Resets the display so that only the scaffold appears */
	public void reset() {
		removeAll();
		showScaffold();
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		GObject refreshLabel = getElementAt(getWidth() / 2, getHeight() / 4 + BODY_LENGTH * 2.2);
		if (refreshLabel == null) {
			GLabel gameDisplay = new GLabel(word, getWidth() / 2 - 70, getHeight() / 4 + BODY_LENGTH * 2.2);
			gameDisplay.setFont("Verdana-36");
			add(gameDisplay);
		} else if (refreshLabel != null) {
			remove(refreshLabel);
			GLabel gameDisplay = new GLabel(word, getWidth() / 2 - 70, getHeight() / 4 + BODY_LENGTH * 2.2);
			gameDisplay.setFont("Verdana-36");
			add(gameDisplay);
		}
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		incorrectGuesses++;

		switch (incorrectGuesses) {
			case 1: showHead();
					break;
			case 2: showBody();
					break;
			case 3: showLeftArm();
					break;
			case 4: showRightArm();
					break;
			case 5: showLeftLeg();
					break;
			case 6: showRightLeg();
					break;
			case 7: showLeftFoot();
					break;
			case 8: showRightFoot();
					break;
		}

	}

	private void showScaffold() {
		GLine scaffold = new GLine(
				getWidth() / 2 - BEAM_LENGTH,
				getHeight() / 4 - HEAD_DIAM - HEAD_RADIUS,
				getWidth() / 2 - BEAM_LENGTH,
				getHeight() / 4 - HEAD_DIAM - HEAD_RADIUS + SCAFFOLD_HEIGHT
				);
		add(scaffold);

		GLine beam = new GLine(
				getWidth() / 2,
				getHeight() / 4 - HEAD_DIAM - HEAD_RADIUS,
				getWidth() / 2 - BEAM_LENGTH,
				getHeight() / 4 - HEAD_DIAM - HEAD_RADIUS);
		add(beam);

		GLine noose = new GLine(
				getWidth() / 2,
				getHeight() / 4 - HEAD_DIAM - HEAD_RADIUS,
				getWidth() / 2,
				getHeight() / 4 - HEAD_DIAM - 1
				);
		add(noose);
	}

	private void showHead() {
		GOval head = new GOval((getWidth() / 2 - HEAD_DIAM / 2), getHeight() / 4 - HEAD_DIAM -1, HEAD_DIAM, HEAD_DIAM);
		add(head);
	}

	private void showBody() {
		GLine body = new GLine
				(getWidth() / 2,
				getHeight() / 4,
				getWidth() / 2,
				getHeight() / 4 + BODY_LENGTH);
		add(body);
	}

	private void showLeftArm() {
		GLine leftUpperArm = new GLine(
				(getWidth() / 2 - UPPER_ARM_LENGTH),
				(getHeight() / 4 + ARM_OFFSET_FROM_HEAD),
				(getWidth() / 2),
				(getHeight() / 4 + ARM_OFFSET_FROM_HEAD));
		add(leftUpperArm);

		GLine leftLowerArm = new GLine(
				(getWidth() / 2 - UPPER_ARM_LENGTH),
				(getHeight() / 4 + ARM_OFFSET_FROM_HEAD),
				(getWidth() / 2 - UPPER_ARM_LENGTH),
				(getHeight() / 4 + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH));
		add(leftLowerArm);
	}

	private void showRightArm() {
		GLine rightUpperArm = new GLine(
				(getWidth() / 2 + UPPER_ARM_LENGTH),
				(getHeight() / 4 + ARM_OFFSET_FROM_HEAD),
				(getWidth() / 2),
				(getHeight() / 4 + ARM_OFFSET_FROM_HEAD));
		add(rightUpperArm);

		GLine rightLowerArm = new GLine(
				(getWidth() / 2 + UPPER_ARM_LENGTH),
				(getHeight() / 4 + ARM_OFFSET_FROM_HEAD),
				(getWidth() / 2 + UPPER_ARM_LENGTH),
				(getHeight() / 4 + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH));
		add(rightLowerArm);
	}

	private void showLeftLeg() {
		GLine leftHip = new GLine
				(getWidth() / 2,
				getHeight() / 4 + BODY_LENGTH,
				getWidth() / 2 - HIP_WIDTH,
				getHeight() / 4 + BODY_LENGTH);
		add(leftHip);

		GLine leftLeg = new GLine
				(getWidth() / 2 - HIP_WIDTH,
				getHeight() / 4 + BODY_LENGTH,
				getWidth() / 2 - HIP_WIDTH,
				getHeight() / 4 + BODY_LENGTH + LEG_LENGTH);
		add(leftLeg);
	}

	private void showRightLeg() {

		GLine rightHip = new GLine
				(getWidth() / 2,
				getHeight() / 4 + BODY_LENGTH,
				getWidth() / 2 + HIP_WIDTH,
				getHeight() / 4 + BODY_LENGTH);
		add(rightHip);

		GLine rightLeg = new GLine
				(getWidth() / 2 + HIP_WIDTH,
				getHeight() / 4 + BODY_LENGTH,
				getWidth() / 2 + HIP_WIDTH,
				getHeight() / 4 + BODY_LENGTH + LEG_LENGTH);
		add(rightLeg);
	}

	private void showLeftFoot() {
		GLine leftFoot = new GLine(
				getWidth() / 2 - HIP_WIDTH,
				getHeight() / 4 + BODY_LENGTH + LEG_LENGTH,
				getWidth() / 2 - HIP_WIDTH - FOOT_LENGTH,
				getHeight() / 4 + BODY_LENGTH + LEG_LENGTH
				);
		add(leftFoot);
	}

	private void showRightFoot() {
		GLine rightFoot = new GLine(
				getWidth() / 2 + HIP_WIDTH,
				getHeight() / 4 + BODY_LENGTH + LEG_LENGTH,
				getWidth() / 2 + HIP_WIDTH + FOOT_LENGTH,
				getHeight() / 4 + BODY_LENGTH + LEG_LENGTH
				);
		add(rightFoot);
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 24;
	private static final int HEAD_DIAM = HEAD_RADIUS * 2;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

}
