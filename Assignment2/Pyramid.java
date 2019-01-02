/*

Stanford CS106A - Programming Methodology
Assignment2: Pyramid
Author: Alex Perse - http://github.com/alexperse

*/

import acm.graphics.*;
import acm.program.*;

public class Pyramid extends GraphicsProgram {

	/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

	/** Height of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

	/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;

	/** First brick on the bottom base, distance from left, AKA X coordinate */
	private static final int FIRST_BRICK_DISTANCE_FROM_LEFT = 165;

	/** First brick on the bottom base, distance from top, AKA Y coordinate */
	private static final int FIRST_BRICK_DISTANCE_FROM_TOP = 479;

	public void run() {

		buildPyramid();

	}
		private void buildPyramid() {

		int verticalStacksOfBricksRemaining = BRICKS_IN_BASE;
		int numberOfHorizontalBricksRemainingInThisLine = BRICKS_IN_BASE;
		int horizontalBrickDistanceFromLeft = FIRST_BRICK_DISTANCE_FROM_LEFT;
		int counter = 0;

		int verticalBrickDistanceFromTop = FIRST_BRICK_DISTANCE_FROM_TOP;

		while (verticalStacksOfBricksRemaining > 0){


		for (int i=0; i < numberOfHorizontalBricksRemainingInThisLine; i++){
		GRect brick = new GRect
				(horizontalBrickDistanceFromLeft, verticalBrickDistanceFromTop,
						BRICK_WIDTH, BRICK_HEIGHT);
		add(brick);
		horizontalBrickDistanceFromLeft = horizontalBrickDistanceFromLeft + 30;
		}

		counter++;
		numberOfHorizontalBricksRemainingInThisLine--;
		verticalBrickDistanceFromTop = verticalBrickDistanceFromTop - 12;
		verticalStacksOfBricksRemaining--;
		horizontalBrickDistanceFromLeft = FIRST_BRICK_DISTANCE_FROM_LEFT + 15 * counter;
		}
	}

}
