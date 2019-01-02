/*

Stanford CS106A - Programming Methodology
Assignment2: FindRange
Author: Alex Perse - http://github.com/alexperse

*/
import acm.program.*;


public class FindRange extends ConsoleProgram {


	public void run() {

		int sentinel = 1;
		int firstInput = 0;
		int secondInput = 0;
		int maximum = 0;
		int minimum = 0;
		int counter = 0;

		println("This program finds the largest and smallest numbers. Press 0 when you are finished.");

		while (sentinel != 0){

			//Read two inputs, each time assigning them to the sentinel and do a zero check
			firstInput = readInt(" ? ");
			counter++;
			sentinel = firstInput;
			if (counter == 1 && firstInput != 0){
				maximum = firstInput;
				minimum = firstInput;
			}
			if (firstInput != 0 && firstInput > maximum){
				maximum = firstInput;
				}
			if (firstInput != 0 && firstInput < minimum){
				minimum = firstInput;
			}
			if (sentinel == 0) break;
			secondInput = readInt(" ? ");
			counter++;
			sentinel = secondInput;
			if (secondInput != 0 && secondInput > maximum && secondInput > firstInput){
				maximum = secondInput;
			}
		if (secondInput != 0 && secondInput < minimum && secondInput < firstInput){
			minimum = secondInput;
		}
			if (sentinel == 0) break;

	}
		//End of while loop
		if (sentinel == 0 && counter == 1){
			println("You entered zero on the first line without "
					+ "entering anything else.");
		} else if (sentinel == 0 && counter == 2){
			println(+firstInput + " is the largest and smallest number.");
		}

		//Print final count
		if (counter > 2) {
			println("smallest: " +minimum);
			println("largest: " +maximum);
		}

 }

}
