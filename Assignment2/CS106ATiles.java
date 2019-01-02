/*

Stanford CS106A - Programming Methodology
Assignment2: CS106A Tiles
Author: Alex Perse - http://github.com/alexperse

*/

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class CS106ATiles extends GraphicsProgram {

	/** Amount of space (in pixels) between tiles */
	private static final int TILE_SPACE = 20;
	private static final int TILE_WIDTH = 100;
	private static final int TILE_HEIGHT = 50;

	public void run() {

		int topLeftBoxCoordinateX = (getWidth() / 2) - (TILE_WIDTH);
		int topLeftBoxCoordinateY = (getHeight() / 2) - (TILE_HEIGHT);


			GRect topLeftBox = new GRect
				(topLeftBoxCoordinateX, topLeftBoxCoordinateY, TILE_WIDTH, TILE_HEIGHT);
			add(topLeftBox);

			GLabel topLeftLabel = new GLabel
					("CS106A", topLeftBoxCoordinateX, topLeftBoxCoordinateY + (TILE_HEIGHT / 2));
			double topLeftLabelWidth = topLeftLabel.getWidth() / 2;
			double topLeftLabelAscent = topLeftLabel.getAscent() / 2;
			topLeftLabel.move(topLeftLabelWidth, topLeftLabelAscent);
			add(topLeftLabel);

			GRect topRightBox = new GRect
				((topLeftBoxCoordinateX + TILE_WIDTH + TILE_SPACE), topLeftBoxCoordinateY, TILE_WIDTH, TILE_HEIGHT);
			add(topRightBox);

			GLabel topRightLabel = new GLabel
					("CS106A", topLeftBoxCoordinateX + TILE_WIDTH + TILE_SPACE, topLeftBoxCoordinateY + (TILE_HEIGHT / 2));
			double topRightLabelWidth = topRightLabel.getWidth() / 2;
			double topRightLabelAscent = topRightLabel.getAscent() / 2;
			topRightLabel.move(topRightLabelWidth, topRightLabelAscent);
			add(topRightLabel);


			GRect bottomLeftBox = new GRect
					(topLeftBoxCoordinateX, (topLeftBoxCoordinateY + TILE_HEIGHT + TILE_SPACE), TILE_WIDTH, TILE_HEIGHT);
			add(bottomLeftBox);

			GLabel bottomLeftLabel = new GLabel
					("CS106A", topLeftBoxCoordinateX, topLeftBoxCoordinateY + (TILE_HEIGHT + TILE_SPACE) + (TILE_HEIGHT /2));
			double bottomLeftLabelWidth = bottomLeftLabel.getWidth() / 2;
			double bottomLeftLabelAscent = bottomLeftLabel.getAscent() / 2;
			bottomLeftLabel.move(bottomLeftLabelWidth, bottomLeftLabelAscent);
			add(bottomLeftLabel);


			GRect bottomRightBox = new GRect
					((topLeftBoxCoordinateX + TILE_WIDTH + TILE_SPACE), (topLeftBoxCoordinateY + TILE_HEIGHT + TILE_SPACE), TILE_WIDTH, TILE_HEIGHT);
			add(bottomRightBox);

			GLabel bottomRightLabel = new GLabel
					("CS106A", topLeftBoxCoordinateX + TILE_WIDTH + TILE_SPACE, topLeftBoxCoordinateY + (TILE_HEIGHT + TILE_SPACE) + (TILE_HEIGHT / 2));
			double bottomRightLabelWidth = bottomRightLabel.getWidth() / 2;
			double bottomRightLabelAscent = bottomRightLabel.getAscent() / 2;
			bottomRightLabel.move(bottomRightLabelWidth, bottomRightLabelAscent);
			add(bottomRightLabel);




	}




}
