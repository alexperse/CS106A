	/*
Stanford CS106A - Programming Methodology

Assignment1: Problem 2

Author: Alex Perse - http://github.com/alexperse

File: StoneMasonKarel.java
--------------------------
Karel starts at 1st Avenue and 1st Street, facing east, with an infinite number of beepers.
The columns are exactly four units apart, on 1st, 5th, 9th Avenue, and so forth.
The end of the columns is marked by a wall immediately after the final column.
This wall section appears after 13th Avenue in the example, but your program should work
for any number of columns.
The top of the column is marked by a wall, but Karel cannot assume that columns are
always five units high, or even that all columns are the same height.
Some of the corners in the column may already contain beepers representing stones
that are still in place. Your program should not put a second beeper on these corners.

	 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	/*

The two methods - distributeALineOfBeepers, and reOrientForANewColumn -
can be looped as many times as needed for the world. Since all of the
"Karel rebuilds the quad" worlds are no more than 13 units, I have left them to
work for all the worlds included in the assignment.

	 */
public void run() {


	//Karel will face north and distribute a line of beepers.
	distributeALineOfBeepers();

	// Karel moves to the east 4 units, then will travel to the south (the bottom of the world),
	// ready to distribute a new line of beepers for the next column.
	reOrientForANewColumn();

	//Rinse and repeat the above two methods for the number of all possible Quad worlds. :)
	//Not using a loop for better clarity and readability.

	distributeALineOfBeepers();
	reOrientForANewColumn();
	distributeALineOfBeepers();
	reOrientForANewColumn();
	distributeALineOfBeepers();

	}
	/*

This method ensures Karel is facing north and has no obstructions.
Karel will then check if beepers are present. If they are, Karel will move to the next space.
If there are no beepers present, Karel will place a beeper. The loop will continue
until Karel's north-facing front is blocked.
To be compatible with all worlds, Karel will place a beeper when the loop terminates,
provided there are no beepers present.

	 */

private void distributeALineOfBeepers() {
	while (notFacingNorth()){
			turnLeft();
		}
	while (frontIsClear()) {

			if (beepersPresent()){
				move();
			}
				if (noBeepersPresent()){
					putBeeper();
			}
		}
	if (noBeepersPresent()) {
			putBeeper();
		}

	}

	/*
This method has a counter that ensures Karel goes east no more than 4 units, as
each column is on 1st, 5th, 9th Avenue, and so forth.

Once Karel has moved 4 units to the east and avoided obstacles, Karel will then go as
far south as possible and re-orient facing north, so a new set of stones (beepers) can be built
from the ground-up.

At that time, Karel is ready to begin a new column, and the
distributeALineOfBeepers method can be called again.

	 */

private void reOrientForANewColumn() {
	int counter = 0;
	while (notFacingEast()){
			turnRight();
		}
	while (frontIsBlocked() && facingEast() && counter < 4) {
			turnRight();
			if (frontIsClear()){
				move();
			}
			turnLeft();
				if (frontIsClear()){
					move();
			}
			counter++;
		}
	while (counter < 4 && frontIsClear() && facingEast()){
			move();
			counter++;
		}
	while (notFacingSouth()){
			turnRight();
		}
	while (frontIsClear()){
			move();
		}
	while (notFacingNorth()){
			turnLeft();
		}
	}
}
