/*
Stanford CS106A - Programming Methodology
Assignment1: Problem 4

Author: Alex Perse - http://github.com/alexperse

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

public class MidpointFindingKarel_MathSolution extends SuperKarel {

public void run() {
	measureSizeOfWorldThenPlaceBeeperInTheMiddle();
}

private void measureSizeOfWorldThenPlaceBeeperInTheMiddle() {

	int howManySpacesDidKarelMove = 0;

	while (frontIsClear()) {
		move();
		howManySpacesDidKarelMove++;
	}

	turnAround();

	howManySpacesDidKarelMove /= 2;

	while (howManySpacesDidKarelMove > 0) {
		move();
		howManySpacesDidKarelMove--;
	}
	putBeeper();
}


}
