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
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Paire extends AbstractConnaîsseurRang
{

	public boolean reconnaîtreMain(DemandeRecMain demande)
	{
		boolean résultat = false;
		Main main = demande.getMain();
		SortedSet paires = RangPoker.trouverDénominationN(main.iterator(), 2);
		if (paires.size() > 0)
		{
			Carte paire = (Carte) paires.first();
			Carte kicker = RangPoker.identifieKicker(main, paires);
			// créer une instance de Paire, avec la main et une carte de la paire
			demande.setRangReconnu(
				new RangPoker(
					RangPoker.RANG_PAIRE,
					paire.getRang() * 13 + kicker.getRang()));
			résultat = true;
		}
		return résultat;
	}

}