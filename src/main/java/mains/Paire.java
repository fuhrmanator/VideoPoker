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
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Paire extends AbstractAnalyseurRang
{

	public boolean reconnaîtreMain(ReqAnalyseMain demande)
	{
		boolean résultat = false;
		Main main = demande.getMain();
		SortedSet<Carte> paires = RangPoker.trouverDénominationN(main.iterator(), 2);
		if (paires.size() > 0)
		{
			// créer le nouveau rang, avec deux cartes comme déterminantes
			Carte paire = paires.first();
			Carte kicker = RangPoker.identifieKicker(main, paires);

			Collection<Carte> déterminante = new ArrayList<Carte>();
			déterminante.add(paire);
			déterminante.add(kicker);

			demande.setRangReconnu(
				new RangPoker(
					RangPoker.RANG_PAIRE,
					déterminante));
			résultat = true;
		}
		return résultat;
	}

}