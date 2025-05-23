/*
 * Created on Dec 11, 2004
 *
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import cartes.DefaultCardModel;

/**
 * @author Cris
 *
 */
public class BoutonGarder extends JButton implements ActionListener
{
	public BoutonGarder(String texte, DefaultCardModel modèleCarte)
	{
		super(texte);
		this.modèleCarte = modèleCarte;
		this.addActionListener(this);
	}
	
	private DefaultCardModel modèleCarte;

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e)
	{
		modèleCarte.setVisible(!modèleCarte.isVisible());
	}
}
