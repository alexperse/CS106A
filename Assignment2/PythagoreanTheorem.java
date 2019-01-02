/*

Stanford CS106A - Programming Methodology
Assignment2: PythagoreanTheorem
Author: Alex Perse - http://github.com/alexperse

*/

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {

	println("Enter values to compute Pythagorean theorem.");

	double a = readDouble ("a: ");
	double b = readDouble ("b: ");
	double c = Math.sqrt(a*a+b*b);
	println("c = " + c);
	}
}
