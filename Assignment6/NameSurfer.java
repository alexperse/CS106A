/*

Stanford CS106A - Programming Methodology
Assignment6: NameSurfer
Author: Alex Perse - http://github.com/alexperse

*/

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the top of the window.
 */
	public void init() {
		graph = new NameSurferGraph();
		add(graph);

	    nameLabel = new JLabel ("Name: ");
	    add(nameLabel,NORTH);

	    nameField = new JTextField(30);
	    add(nameField, NORTH);
	    nameField.addActionListener(this);

	    graphButton = new JButton("Graph");
	    add(graphButton,NORTH);

	    clearButton = new JButton("Clear");
	    add(clearButton,NORTH);

	    addActionListeners();
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == graphButton) {
			println("Graph: " + nameField.getText());
			NameSurferEntry entry = nameDataBase.findEntry(nameField.getText());
			println("Graph: " + entry.toString());
			graph.addEntry(entry);
			graph.updateCanvas();

		} else if (e.getSource() == nameField) {
			println("Graph: " + nameField.getText());
			NameSurferEntry entry = nameDataBase.findEntry(nameField.getText());
			println("Graph: " + entry.toString());
			graph.addEntry(entry);
			graph.updateCanvas();

		} else if (e.getSource() == clearButton) {
			println("Clear");
			graph.clear();
			graph.updateCanvas();
		}
	}
	private JLabel nameLabel;
	private JTextField nameField;
	private JButton graphButton;
	private JButton clearButton;
	private NameSurferDataBase nameDataBase = new NameSurferDataBase("names-data.txt");
	private NameSurferGraph graph;
}
