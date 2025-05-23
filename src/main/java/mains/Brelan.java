/*
 * Created on Dec 10, 2004
 *
 */
package mains;

import java.util.SortedSet;

import cartes.Carte;

/**
 * @author Cris
 *
 */
public class Brelan extends AbstractConnaîsseurRang
{

	public boolean reconnaîtreMain(DemandeRecMain demande)
	{
		boolean résultat = false;
		Main main = demande.getMain();
		// essayer de reconnaître un brelan
		SortedSet brelans = RangPoker.trouverDénominationN(main.iterator(), 3);
		if (brelans.size() > 0)
		{
			// créer une le nouveau rang, avec le rang du brelan comme valeur mineure
			Carte brelan = (Carte) brelans.first();
			demande.setRangReconnu(
				new RangPoker(RangPoker.RANG_BRELAN, brelan.getRang()));
			résultat = true;
		}
		return résultat;
	}
}