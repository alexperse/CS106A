/*

Stanford CS106A - Programming Methodology
Assignment2: Hailstone
Author: Alex Perse - http://github.com/alexperse

*/

import acm.program.*;


public class Hailstone extends ConsoleProgram {



	public void run() {

		int counter = 0;

		int n = readInt("Enter a number: ");

		while (n != 1){

		if (n % 2 == 0){
			int newNumber = isEven(n);
			println(+n + " is even so I take half: " +newNumber);
			n = newNumber;
		}
		else if (n % 2 != 0){
			int newNumber = isOdd(n);
			println(+n + " is odd, so I make 3n + 1: " +newNumber);
			n = newNumber;
		}
		counter++;
	}

println("The process took " +counter + " to reach 1");

 }

private int isEven(int n) {
		int newNumber = n / 2;
		return newNumber;
	}

private int isOdd(int n) {
		int newNumber = (3*n) + 1;
		return newNumber;
 }

}
