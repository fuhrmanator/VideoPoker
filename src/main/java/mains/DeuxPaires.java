/*
 * Created on Dec 10, 2004
 *
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
 */
public class DeuxPaires extends AbstractAnalyseurRang
{

	public boolean reconnaîtreMain(ReqAnalyseMain demande)
	{
		boolean résultat = false;
		Main main = demande.getMain();
		SortedSet<Carte> paires = RangPoker.trouverDénominationN(main.iterator(), 2);
		if (paires.size() > 1)
		{
			// créer le nouveau rang de deux paires, avec une carte de chaque paire plus le kicker
			Iterator<Carte> paireIter = paires.iterator();
			Carte paire1 = paireIter.next();
			Carte paire2 = paireIter.next();
			Carte kicker = RangPoker.identifieKicker(main, paires);
			Collection<Carte> déterminantes = new ArrayList<Carte>();
			déterminantes.add(paire1);
			déterminantes.add(paire2);
			déterminantes.add(kicker);

			demande.setRangReconnu(
				new RangPoker(
					RangPoker.RANG_DEUX_PAIRES, déterminantes));
			résultat = true;
		}
		return résultat;
	}

}