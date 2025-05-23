package gui;

import javax.swing.*;

import cartes.CardUtils;

import java.awt.*;
import java.awt.event.*;

/**
 * @author Cris
 * 
 * Basé sur SwingApplication.java:
 * http://java.sun.com/docs/books/tutorial/uiswing/learn/example-1dot4/SwingApplication.java
 */
public class CardTest implements ActionListener
{

	public Component createComponents()
	{
		JPanel pane = new JPanel();

		/*
		 * Mise en page du JPanel
		 */
		// pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
		pane.setBorder(BorderFactory.createEmptyBorder(30, //top
		30, //left
		10, //bottom
		30) //right
		);

		Image image = CardUtils.JACK_C_IMAGE;

		JLabel label = new JLabel(new ImageIcon(image));

		pane.add(label, BorderLayout.CENTER);

		return pane;
	}

	/*
	 * méthode appelée suite d'un clique du bouton
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e)
	{
	}

	/**
	 * Create the GUI and show it.  For thread safety,
	 * this method should be invoked from the
	 * event-dispatching thread.
	 */
	private static void createAndShowGUI()
	{

		//Make sure we have nice window decorations.
		//JFrame.setDefaultLookAndFeelDecorated(true);

		//Create and set up the window.
		JFrame frame = new JFrame("Card test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		CardTest app = new CardTest();
		Component contents = app.createComponents();
		frame.getContentPane().add(contents, BorderLayout.CENTER);

		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args)
	{
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				createAndShowGUI();
			}
		});
	}
}
