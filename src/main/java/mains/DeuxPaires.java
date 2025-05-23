/*
 * Created on Dec 10, 2004
 *
 */
package mains;

import java.util.Iterator;
import java.util.SortedSet;

import cartes.Carte;

/**
 * @author Cris
 *
 */
public class DeuxPaires extends AbstractConnaîsseurRang
{

	public boolean reconnaîtreMain(DemandeRecMain demande)
	{
		boolean résultat = false;
		Main main = demande.getMain();
		SortedSet paires = RangPoker.trouverDénominationN(main.iterator(), 2);
		if (paires.size() > 1)
		{
			// créer une instance de Paire, avec la main et une carte de la paire
			Iterator paireIter = paires.iterator();
			Carte paire1 = (Carte) paireIter.next();
			Carte paire2 = (Carte) paireIter.next();
			Carte kicker = RangPoker.identifieKicker(main, paires);
			demande.setRangReconnu(
				new RangPoker(
					RangPoker.RANG_DEUX_PAIRES,
					paire1.getRang() * 13 * 13
						+ paire2.getRang() * 13
						+ kicker.getRang()));
			résultat = true;
		}
		return résultat;
	}

}