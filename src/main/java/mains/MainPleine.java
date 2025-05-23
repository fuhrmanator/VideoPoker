/*
 * Created on Dec 10, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mains;

import java.util.Iterator;
import java.util.SortedSet;

import cartes.Carte;

/**
 * @author Cris
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MainPleine extends AbstractConnaîsseurRang
{

	//	public MainPleine(Main main, Carte brelan, Carte paire)
	//	{
	//		super(main);
	//		this.valeurMajeure = RangPoker.RANG_MAIN_PLEINE;
	//		this.valeurMineure = brelan.getRang() * 13 + paire.getRang();
	//	}

	public boolean reconnaîtreMain(DemandeRecMain demande)
	{
		boolean résultat = false;
		Main main = demande.getMain();
		SortedSet brelans = RangPoker.trouverDénominationN(main.iterator(), 3);
		if (brelans.size() > 0)
		{
			SortedSet paires =
				RangPoker.trouverDénominationN(main.iterator(), 2);
			if (paires.size() > 1)
			{
				// créer une instance de MainPleine, avec la main, une carte du brelan 
				// et une de la paire
				Iterator brelanIter = brelans.iterator();
				Carte brelan = (Carte) brelanIter.next();

				// traverser liste de paires, mais ignorer les paires du brelan si jamais
				Iterator paireIter = paires.iterator();
				Carte paire;
				do
				{
					paire = (Carte) paireIter.next();
				} while (paire.getRang() != brelan.getRang());

				demande.setRangReconnu(
					new RangPoker(
						RangPoker.RANG_MAIN_PLEINE,
						brelan.getRang() * 13 + paire.getRang()));

				résultat = true;
			}
		}
		return résultat;
	}

}