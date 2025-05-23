/*
 * Created on Dec 10, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mains;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;

import cartes.Carte;

/**
 * @author Cris
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MainPleine extends AbstractAnalyseurRang
{

	//	public MainPleine(Main main, Carte brelan, Carte paire)
	//	{
	//		super(main);
	//		this.valeurMajeure = RangPoker.RANG_MAIN_PLEINE;
	//		this.valeurMineure = brelan.getRang() * 13 + paire.getRang();
	//	}

	public boolean reconnaîtreMain(ReqAnalyseMain demande)
	{
		boolean résultat = false;
		Main main = demande.getMain();
		SortedSet<Carte> brelans = RangPoker.trouverDénominationN(main.iterator(), 3);
		if (brelans.size() > 0)
		{
			SortedSet<Carte> paires =
				RangPoker.trouverDénominationN(main.iterator(), 2);
			if (paires.size() > 1)
			{
				// créer une instance de MainPleine, avec la main, une carte du brelan 
				// et une de la paire
				Iterator<Carte> brelanIter = brelans.iterator();
				Carte brelan = brelanIter.next();

				// traverser liste de paires, mais ignorer les paires du brelan si jamais
				Iterator<Carte> paireIter = paires.iterator();
				Carte paire;
				do
				{
					paire = paireIter.next();
				} while (paire.getDénomination() != brelan.getDénomination());

				// créer le nouveau rang, avec deux cartes comme déterminantes
				Collection<Carte> déterminante = new ArrayList<Carte>();
				déterminante.add(brelan);
				déterminante.add(paire);
				
				demande.setRangReconnu(
					new RangPoker(
						RangPoker.RANG_MAIN_PLEINE,
						déterminante));

				résultat = true;
			}
		}
		return résultat;
	}

}