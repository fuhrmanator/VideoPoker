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
public class Carré extends AbstractAnalyseurRang
{

	public boolean reconnaîtreMain(ReqAnalyseMain demande)
	{
		boolean résultat = false;
		Main main = demande.getMain();
		SortedSet<Carte> carrés = RangPoker.trouverDénominationN(main.iterator(), 4);
		if (carrés.size() > 0)
		{
			// créer une le nouveau rang, avec une carte du carré comme déterminante
			Collection<Carte> carré = new ArrayList<Carte>();
			carré.add(carrés.first());
			demande.setRangReconnu(
				new RangPoker(RangPoker.RANG_CARRÉ, carré));
			résultat = true;
		}
		return résultat;
	}

}