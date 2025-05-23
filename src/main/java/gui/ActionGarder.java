/*
 * Created on Dec 11, 2004
 *
 */
package gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JLabel;

import cartes.DefaultCardModel;

/**
 * @author Cris
 *
 */
public class ActionGarder extends AbstractAction
{
	private DefaultCardModel modèleCarte;
	private String gardéTexte;
	private JLabel label;

	public ActionGarder(DefaultCardModel modèleCarte, JLabel label, String gardéTexte)
	{
		this.modèleCarte = modèleCarte;
		this.label = label;
		this.gardéTexte = gardéTexte;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e)
	{
		label.setText(label.getText().equalsIgnoreCase(" ") ? gardéTexte : " ");
		// modèleCarte.setVisible(!modèleCarte.isVisible());
	}
}
