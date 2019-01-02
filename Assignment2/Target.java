/*

Stanford CS106A - Programming Methodology
Assignment2: Target
Author: Alex Perse - http://github.com/alexperse

*/

import acm.graphics.*;
import acm.program.*;

import java.awt.*;

public class Target extends GraphicsProgram {

	public void run() {

		//Get the middle of the canvas's X coordinate
		int canvasWidth = getWidth() / 2;

		//Get the middle of the canvas's Y coordinate
		int canvasHeight = getHeight() /2;

		//draw large outer red circle
		GOval outerRedCircle = filledCircle(canvasWidth, canvasHeight, 36, Color.RED);
		add(outerRedCircle);

		//draw smaller inner white circle
		GOval innerWhiteCircle = filledCircle(canvasWidth, canvasHeight, 23.4, Color.WHITE);
		add (innerWhiteCircle);

		//draw red dot in the center
		GOval innerMostRedDot = filledCircle(canvasWidth, canvasHeight, 10.8, Color.RED);
		add (innerMostRedDot);
	}

	private GOval filledCircle(double x, double y, double r, Color color) {
		GOval circle = new GOval(x-r, y-r, 2*r, 2*r);
		circle.setFilled(true);
		circle.setColor(color);
		return circle;
	}

}
