/*

Stanford CS106A - Programming Methodology
Assignment6: NameSurfer (Graph)
Author: Alex Perse - http://github.com/alexperse

 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);

		entryGraph = new ArrayList<NameSurferEntry>();
			}


	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		entryGraph.clear();
		updateCanvas();
	}


	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling updateCanvas.
	*/
	public void addEntry(NameSurferEntry entry) {
		entryGraph.add(entry);
	}


	/**
	* updateCanvass the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call updateCanvas after
	* calling either clear or addEntry; updateCanvas is also called whenever
	* the size of the canvas changes.
	*/
	public void updateCanvas() {
		removeAll();
		drawGraph();

		for(int input = 0; input < entryGraph.size(); input++) {
			drawEntry(entryGraph.get(input), input);
		}
	}

	private void drawEntry(NameSurferEntry entry, int color) {
		spacing = getWidth() / NDECADES;

		for (int marker = 0; marker < NDECADES - 1; marker++) {
			GLine line = new GLine(spacing * marker, verticalValue(entry.getRank(marker)),
					spacing * (marker + 1), verticalValue(entry.getRank(marker + 1)));
			if (color % 4 == 0) {
				line.setColor(Color.BLUE);
			} else if (color % 4 == 1) {
				line.setColor(Color.RED);
			} else if (color % 4 == 2) {
				line.setColor(Color.MAGENTA);
			} else if (color % 4 == 3) {
			line.setColor(Color.BLACK);
			}
			add(line);
		}
		for (int marker = 0; marker < NDECADES; marker++) {
			String labelEntry = "";

			if (entry.getRank(marker) != 0) {
				labelEntry = entry.getName() + " " + entry.getRank(marker);
			} else {
				labelEntry = entry.getName() + " * ";
		}
			GLabel nameLabel = new GLabel(labelEntry, spacing * marker, verticalValue(entry.getRank(marker)));
			if (color % 4 == 0) {
				nameLabel.setColor(Color.BLUE);
			} else if (color % 4 == 1) {
				nameLabel.setColor(Color.RED);
			} else if (color % 4 == 2) {
				nameLabel.setColor(Color.MAGENTA);
			} else if (color % 4 == 3) {
				nameLabel.setColor(Color.BLACK);
			}
			add(nameLabel);
	 }

	}

	private double verticalValue(int rank) {
		double rankDouble = rank;

		if (rank != 0) {
			rankDouble = rankDouble / MAX_RANK;
			rankDouble = rankDouble * (getHeight() - 2 * GRAPH_MARGIN_SIZE);
			rankDouble = rankDouble + GRAPH_MARGIN_SIZE;
		} else {
			rankDouble = getHeight() - GRAPH_MARGIN_SIZE;
		}
		return rankDouble;
	}

	private void drawGraph() {
		drawHorizontalLines();
		drawVerticalLines();
		drawDateLabel();
	}

	private void drawDateLabel() {

		spacing = getWidth() / NDECADES;

		for (int date = 0; date < NDECADES; date++) {
			String dateString = Integer.toString(date * 10 + START_DECADE);
			GLabel dateLabel = new GLabel(dateString, date * spacing, getHeight());
			add(dateLabel);
		}
	}

	private void drawHorizontalLines() {
		GLine topLine = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
		add(topLine);

		GLine botLine = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
		add(botLine);

	}

	private void drawVerticalLines() {

		spacing = getWidth() / NDECADES;

		for (int lines = 0; lines < NDECADES; lines++) {
			GLine verticalLine = new GLine((lines + 1) * spacing, 0, (lines + 1) * spacing, getHeight());
			add(verticalLine);
		}

	}


	/* ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { updateCanvas(); }
	public void componentShown(ComponentEvent e) { }

	/* Instance variables */
	private double spacing = getWidth() / NDECADES;
	private ArrayList<NameSurferEntry> entryGraph;
}
