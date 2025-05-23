/*
 * Created on Dec 10, 2004
 *
 */
package mains;

import java.util.ArrayList;
import java.util.Collection;

import cartes.Carte;

/**
 * @author Cris
 *
 */
public class Quinte extends AbstractAnalyseurRang
{

	public boolean reconnaîtreMain(ReqAnalyseMain demande)
	{
		boolean résultat = false;
		Main main = demande.getMain();

		if (RangPoker.estEnSérie(main))
		{
			// créer le nouveau rang, avec la carte la plus haute comme déterminante
			Collection<Carte> déterminante = new ArrayList<Carte>();
			déterminante.add(main.first());

			demande.setRangReconnu(
				new RangPoker(RangPoker.RANG_QUINTE, déterminante));

			résultat = true;
		}
		return résultat;
	}

}