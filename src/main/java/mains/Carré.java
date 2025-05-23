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
public class Carré extends AbstractConnaîsseurRang
{

	public boolean reconnaîtreMain(DemandeRecMain demande)
	{
		boolean résultat = false;
		Main main = demande.getMain();
		SortedSet carré = RangPoker.trouverDénominationN(main.iterator(), 4);
		if (carré.size() > 0)
		{
			Carte paire = (Carte) carré.first();
			demande.setRangReconnu(
				new RangPoker(RangPoker.RANG_CARRÉ, paire.getRang()));
			résultat = true;
		}
		return résultat;
	}

}