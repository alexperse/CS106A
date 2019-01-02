/*
Stanford CS106A - Programming Methodology
Assignment1: Problem 3

Author: Alex Perse - http://github.com/alexperse

Algorithm: Karel tries to "paint itself into a corner" by placing beepers on the farthest
corners on the outside of the world until they are filled to the center. As beepers accumulate
from the outside-in, Karel will be eventually standing in the middle, detected by the presence
of two beepers next to each other - a condition that is continually checked at each move for
all-world compatibility.

After Karel is in the middle, Karel will then clean up all the beepers to the right and left
of the center beeper, then will go sit on center beeper, as the middle has successfully been found.


I'm new to coding and welcome any feedback or recommendations on my solutions! :)

File: MidpointFindingKarel.java
-------------------------------
When you finish writing it, the MidpointFindingKarel class should
leave a beeper on the corner closest to the center of 1st Street
(or either of the two central corners if 1st Street has an even
number of corners).  Karel can put down additional beepers as it
looks for the midpoint, but must pick them up again before it
stops.  The world may be of any size, but you are allowed to
assume that it is at least as tall as it is wide.

*/

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {


public void run() {
	while (noBeepersPresent()){
	walkUntilYouHitAWallOrBeeper();
	putABeeperThere();
	}
}


private void walkUntilYouHitAWallOrBeeper() { //Karel walks until a wall or a beeper is there
	while (frontIsClear() && noBeepersPresent()){
		move();
	}
}

private void putABeeperThere() { //Karel checks multiple conditions, puts a beeper, then moves
	if (beepersPresent() && frontIsClear()){
		turnAroundAndMove();
	}
	if (noBeepersPresent() && (frontIsClear())){
		putBeeper();
		if (frontIsClear()){
			move();
		}
	}
	if (noBeepersPresent() && (frontIsBlocked())){
		putBeeper();
		turnAroundAndMove();
	} else if
		(beepersPresent() && (frontIsBlocked())){
			turnAroundAndMove();
			if (noBeepersPresent()){
				putBeeper();
				if (frontIsClear()){
					move();
				} else if
					(frontIsClear() && (beepersPresent())){
						karelIsInTheMiddle();
					}
				}
			}
	checkIfKarelIsInTheMiddle();
		}

private void turnAroundAndMove() {
		turnAround();
		move();
}

private void checkIfKarelIsInTheMiddle() {
	if (beepersPresent()) {
			finalCleanUpProcess();
		}
}

private void karelIsInTheMiddle() {

	while (frontIsClear() && (beepersPresent())) {
		pickBeeper();
		move();
	}
	pickBeeper();
}

private void finalCleanUpProcess() {
	karelIsInTheMiddle();
	cleanUpEverythingExceptTheMiddleBeeper();
	karelIsInTheMiddle();
	goSitOnTheMiddleBeeper();
}

private void cleanUpEverythingExceptTheMiddleBeeper() {
	turnAround();
	while (noBeepersPresent()){
		move();
	}
	if (beepersPresent()){
		move();
	}

}

private void goSitOnTheMiddleBeeper() {
	while (frontIsBlocked()) {
		turnAround();
	}
	while (noBeepersPresent()) {
		move();
	}
	if (leftIsBlocked()) { //Make sure Karel is right-side up :)
		turnAround();
	}

}
}
