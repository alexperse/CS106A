/*
Stanford CS106A - Programming Methodology

Assignment1: Problem 3

Author: Alex Perse - http://github.com/alexperse

I'm new to coding and welcome any feedback or recommendations on my solutions! :)

File: CheckerboardKarel.java
--------------------------
In this exercise, your job is to get Karel to create a checkerboard pattern of beepers
inside an empty rectangular world.

This problem has a nice decomposition structure along with some interesting algorithmic issues.
As you think about how you will solve the problem, you should make sure that your solution works
with checkerboards that are different in size from the standard 8x8 checkerboard shown in the
example. Odd-sized checkerboards are tricky, and you should make sure that your program generates
the proper pattern in a 5x3 world.

Another special case you need to consider is that of a world which is only one column wide or
one row high. The starter folder contains several sample worlds that test these special cases,
and you should make sure that your program works for each of them.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

public void run(){

		reOrientNorthForSingleFileWorld();
		putBeeper();
		while (frontIsClear()){
		distributeALineOfBeepers();
		reOrientKarelToTheWest();
		distributeALineOfBeepers();
		reOrientKarelToTheEast();
		}
		}

private void distributeALineOfBeepers() {
	while (frontIsClear()) {
		if (frontIsClear()) {
			move();
			}
			if (frontIsClear()){
				move();
				putBeeper();
			}
	}
}


private void reOrientNorthForSingleFileWorld(){

	if (frontIsBlocked()) {
			turnLeft();
		}
}

private void reOrientKarelToTheWest() {
	if (frontIsBlocked() && (noBeepersPresent())) {
			turnLeft();
				if (frontIsClear()) {
					move();
					turnLeft();
					putBeeper();
			}
		} else if (frontIsBlocked() && (beepersPresent())){
			turnLeft();
				if (frontIsClear()) {
					move();
					turnLeft();
					move();
					putBeeper();
			}
		}
		}


private void reOrientKarelToTheEast() {
	if (frontIsBlocked() && (noBeepersPresent())) {
			turnRight();
				if (frontIsClear()) {
					move();
					turnRight();
					putBeeper();
			}
		} else if (frontIsBlocked() && (beepersPresent())){
			turnRight();
				if (frontIsClear()) {
					move();
					turnRight();
					move();
					putBeeper();
			}
		}
	}
}
