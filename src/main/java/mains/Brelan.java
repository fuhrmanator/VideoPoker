/*
 * Created on Dec 10, 2004
 *
 */
package mains;

import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedSet;

import cartes.Carte;

/**
 * @author Cris
 *
 */
public class Brelan extends AbstractAnalyseurRang
{

	public boolean reconnaîtreMain(ReqAnalyseMain demande)
	{
		boolean résultat = false;
		Main main = demande.getMain();
		// essayer de reconnaître un brelan
		SortedSet<Carte> brelans = RangPoker.trouverDénominationN(main.iterator(), 3);
		if (brelans.size() > 0)
		{
			// créer une le nouveau rang, avec une carte du brelan comme déterminante
			Collection<Carte> brelan = new ArrayList<Carte>();
			brelan.add(brelans.first());
			demande.setRangReconnu(
				new RangPoker(RangPoker.RANG_BRELAN, brelan));
			résultat = true;
		}
		return résultat;
	}
}