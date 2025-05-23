/*
 * Created on Dec 11, 2004
 *
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mains.Main;
import mains.RangPoker;
import cartes.CardUtils;
import cartes.Carte;
import cartes.DefaultCardModel;
import cartes.JCard;
import cartes.Jeu;

/**
 * @author Cris
 *
 */
public class JeuPoker extends JFrame implements ActionListener
{
	static final int ÉTAT_MISE = 1, ÉTAT_ÉCHANGE = 2, DÉLAI_ANIMATION = 200;
	static final String 
	    GAGNÉ_MESSAGE = ApplicationSupport.getResource("app.frame.label.win"),
		UNITÉ_MONÉTAIRE = ApplicationSupport.getResource("app.frame.label.monetaryUnit"),
		DISTRIBUER_MESSAGE = ApplicationSupport.getResource("app.frame.button.deal");

	public JeuPoker()
	{
		this.mise = 1;
		this.montant = 100;

		initStrings();
		initValeursRangMain();
		initComponents();

		boutonPrendCartes.addActionListener(this);
		boutonMiseUn.addActionListener(this);
		boutonMiseMax.addActionListener(this);
		pack();
		this.changerÉtat(ÉTAT_MISE);
	}

	/**
	 * 
	 */
	private void initValeursRangMain()
	{
		valeursRangMain = new int[nomsRangMain.length];
		for (int rang = 0; rang < nomsRangMain.length; rang++)
		{
			valeursRangMain[rang] =
				Integer.parseInt(
					ApplicationSupport.getResource(
						"app.hand.rank.value."
							+ RangPoker.RANGS_RESSOURCE[rang]));
		}
	}

	/**
	 * Initialiser les chaînes de caractères
	 */
	private void initStrings()
	{
		// Charger les noms de rang Poker
		this.nomsRangMain = new String[RangPoker.RANGS_RESSOURCE.length];
		for (int rang = 0; rang < nomsRangMain.length; rang++)
		{
			nomsRangMain[rang] =
				ApplicationSupport.getResource(
					"app.hand.rank.name." + RangPoker.RANGS_RESSOURCE[rang]);
		}

	}

	/**
	 * @param nouvelÉtat
	 */
	private void changerÉtat(int nouvelÉtat)
	{
		switch (nouvelÉtat)
		{
			case ÉTAT_MISE :
				// désactiver les boutons pour les échanges de cartes
				for (int i = 0; i < jCartes.length; i++)
				{
					jCartes[i].setEnabled(false);
				}
				mise = 0;
				mettreAJourMiseGUI();
				boutonPrendCartes.setText(DISTRIBUER_MESSAGE);
				break;
			case ÉTAT_ÉCHANGE :
				// activer les boutons pour les échanges de cartes
				for (int i = 0; i < jCartes.length; i++)
				{
					jCartes[i].setEnabled(true);
				}
				boutonPrendCartes.setEnabled(true);
				boutonPrendCartes.setText(
					ApplicationSupport.getResource("app.frame.button.draw"));
				break;
		}

		this.étatJeu = nouvelÉtat;

	}

	private void initComponents()
	{
		Container pane = this.getContentPane();
		pane.setLayout(new BorderLayout());
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1, 5));

		// Une main de 5 cartes, msg au dessus de chaque carte
		for (int i = 0; i < mainJoueur.length; i++)
		{
			JPanel cartePanel = new JPanel();
			mainPanel.add(cartePanel);

			cartePanel.setLayout(
				new BoxLayout(cartePanel, BoxLayout.PAGE_AXIS));
			cartePanel.add(labelCarteGardée[i] = new JLabel(" "));
			labelCarteGardée[i].setAlignmentX(Component.CENTER_ALIGNMENT);
			jCartes[i] =
				new JCard(
					mainJoueur[i] =
						new DefaultCardModel(
							CardUtils.RANK_ACE - i,
							CardUtils.SUIT_SPADES));
			mainJoueur[i].setVisible(false);
			cartePanel.add(jCartes[i]);

			// créer l'action pour la carte
			actionsGarderCartes[i] =
				new ActionGarder(
					mainJoueur[i],
					labelCarteGardée[i],
					ApplicationSupport.getResource("app.frame.button.keep"));
			jCartes[i].addActionListener(actionsGarderCartes[i]);
			jCartes[i].setEnabled(false);

			// initialiser la GUI
			mettreAJourMiseGUI();

			// BoutonGarder bouton = new BoutonGarder(ApplicationSupport.getResource("app.frame.button.keep"), mainJoueur[i]);
			// BoutonGarder bouton = new BoutonGarder(new Integer(i+1).toString(), mainJoueur[i]);
			// Les raccourcis pour les touches 1, 2, 3, 4, 5 (0x31 == 1), voir KeyEvent
			// bouton.setMnemonic(0x31 + i);
			// bouton.setAlignmentX(Component.CENTER_ALIGNMENT);
			// bouton.setEnabled(false);
			// bouton.addActionListener(this);
			// cartePanel.add(bouton);

			// boutonsCartes[i] = bouton;
		}

		pane.add(mainPanel, BorderLayout.CENTER);

		// les boutons pour miser, prendre des cartes
		JPanel optionsPanel = new JPanel();
		pane.add(optionsPanel, BorderLayout.SOUTH);

		optionsPanel.add(boutonMiseUn);
		optionsPanel.add(boutonMiseMax);
		optionsPanel.add(boutonPrendCartes);

		// les informations
		JPanel infoPanel = new JPanel();
		pane.add(infoPanel, BorderLayout.NORTH);

		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.LINE_AXIS));
		infoPanel.add(labelMise);
		infoPanel.add(Box.createHorizontalGlue());
		infoPanel.add(labelMessage);
		infoPanel.add(Box.createHorizontalGlue());
		infoPanel.add(labelMontant);

	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e)
	{
		final JButton source = (JButton) e.getSource();

		if (source == boutonPrendCartes)
		{

			final SwingWorker worker = new SwingWorker()
			{
				public Object construct()
				{
						//...code that might take a while to execute is here...
	/*
		 * Distribuer cartes
		 */
					if (étatJeu == ÉTAT_MISE)
					{
						distribuerCartes();
					}
					/*
					 * Échanger cartes
					 */
					else
						if (étatJeu == ÉTAT_ÉCHANGE)
						{
							changerCartes();
						}

					return new Integer(0);
				}
			};
			worker.start(); //required for SwingWorker 3
		} else
			if (source == boutonMiseUn)
			{
				mise++;
				montant--;
				mettreAJourMiseGUI();
			} else
				if (source == boutonMiseMax)
				{
					montant -= MISE_MAX - mise;
					mise = MISE_MAX;
					mettreAJourMiseGUI();
				}

	}
	/**
	 * 
	 */
	private void mettreAJourMiseGUI()
	{
		labelMise.setText(
			ApplicationSupport.getResource("app.frame.label.bet") + " " + mise);
		labelMontant.setText(UNITÉ_MONÉTAIRE + montant);

		boutonMiseUn.setEnabled(mise < MISE_MAX && montant > 0);
		boutonMiseMax.setEnabled(mise < MISE_MAX && montant >= MISE_MAX);
		boutonPrendCartes.setEnabled(mise > 0);
	}

	/**
	 * Distribuer les cartes
	 */
	private void distribuerCartes()
	{
		// cacher de nouveau les cartes si nécessaire
		for (int i = 0; i < 5; i++)
		{
			mainJoueur[i].setVisible(false);
		}

		// brasser jeu and distribuer cartes
		this.jeu.brasser();
		this.jeuIterator = jeu.iterator();

		for (int i = 0; i < 5; i++)
		{
			Carte carte = (Carte) this.jeuIterator.next();
			mainJoueur[i].update(carte.getRang(), carte.getSorte());
			mainJoueur[i].setVisible(true);
			// attendre pour l'animation
			attendre(DÉLAI_ANIMATION);
		}
		labelMessage.setForeground(Color.BLACK);
		labelMessage.setText(
			ApplicationSupport.getResource("app.frame.label.clickToKeep"));
		changerÉtat(ÉTAT_ÉCHANGE);
	}

	/**
	 * 
	 */
	private void changerCartes()
	{
		/*
		 * Pour chaque carte qui n'est pas gardée, on prend
		 * une nouvelle carte du jeu, avec animation
		 */

		//Schedule a job for the event-dispatching thread:
		//modifying this application's GUI.
		//		try
		//		{
		//			javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
		//				public void run() {

		/*
		 * cacher les cartes à changer
		 */
		for (int i = 0; i < mainJoueur.length; i++)
		{
			if (labelCarteGardée[i].getText().equalsIgnoreCase(" "))
			{
				mainJoueur[i].setVisible(false);
			}
		}

		//				}
		//			});
		//		} catch (InterruptedException e)
		//		{
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		} catch (InvocationTargetException e)
		//		{
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}

		// changer les cartes non gardées
		for (int i = 0; i < mainJoueur.length; i++)
		{
			if (labelCarteGardée[i].getText().equalsIgnoreCase(" "))
			{

				//Schedule a job for the event-dispatching thread:
				//modifying this application's GUI.
				//				try
				//				{
				final int iTemp = i;
				//					javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
				//						public void run() {
				// attendre un temps (pour l'animation)
				attendre(DÉLAI_ANIMATION);
				/*
				 * montrer la cartes changée
				 */
				mainJoueur[iTemp].setVisible(true);
				//						}
				//					});
				//				} catch (InterruptedException e)
				//				{
				//					// TODO Auto-generated catch block
				//					e.printStackTrace();
				//				} catch (InvocationTargetException e)
				//				{
				//					// TODO Auto-generated catch block
				//					e.printStackTrace();
				//				}

				Carte nouvelleCarte = (Carte) jeuIterator.next();
				mainJoueur[i].update(
					nouvelleCarte.getRang(),
					nouvelleCarte.getSorte());

			} else
			{
				labelCarteGardée[i].setText(" ");
			}
		}

		/*
		 * Évaluer la main
		 */
		évaluerMain();

		changerÉtat(ÉTAT_MISE);
	}

	/**
	 * 
	 */
	private void évaluerMain()
	{
		Main main = new Main();
		for (int i = 0; i < mainJoueur.length; i++)
		{
			main.add(
				new Carte(mainJoueur[i].getRank(), mainJoueur[i].getSuit()));
		}

		RangPoker résultat = main.getRangPoker();
		System.out.println("résultat.getRang() = " + résultat.getRang());
		int prix = valeursRangMain[résultat.getRang()] * mise;
		montant += prix;
		labelMessage.setForeground(Color.RED);
		labelMessage.setText(
			nomsRangMain[résultat.getRang()]
				+ (prix > 0 ? "! " + GAGNÉ_MESSAGE + " " + UNITÉ_MONÉTAIRE + prix : ""));
	}

	private synchronized void attendre(int ms)
	{
		try
		{
			Thread.sleep(ms);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String args[])
	{
		ApplicationSupport.launch(
			new JeuPoker(),
			ApplicationSupport.getResource("app.frame.title"),
			0,
			0,
			600,
			300);
	}

	private int mise;
	private int montant;
	private int étatJeu;

	private static int N_CARTES = 5;
	private static int MISE_MAX = 3;

	private DefaultCardModel[] mainJoueur = new DefaultCardModel[N_CARTES];
	private Jeu jeu = new Jeu();
	private Iterator jeuIterator;

	private JLabel[] labelCarteGardée = new JLabel[N_CARTES];
	private ActionGarder[] actionsGarderCartes = new ActionGarder[N_CARTES];
	private JCard[] jCartes = new JCard[N_CARTES];

	private JButton boutonMiseUn =
		new JButton(ApplicationSupport.getResource("app.frame.button.bet1"));
	private JButton boutonMiseMax =
		new JButton(ApplicationSupport.getResource("app.frame.button.betmax"));
	private JButton boutonPrendCartes =
		new JButton(ApplicationSupport.getResource("app.frame.button.draw"));
	private JLabel labelMessage =
		new JLabel(ApplicationSupport.getResource("app.frame.label.welcome"));
	private JLabel labelMontant = new JLabel(" ");
	private JLabel labelMise = new JLabel(" ");

	private String[] nomsRangMain;
	private int[] valeursRangMain;
}
