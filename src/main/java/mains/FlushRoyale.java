/*
 * Created on Dec 10, 2004
 *
 */
package mains;

import cartes.Dénomination;

/**
 * @author Cris
 *
 */
public class FlushRoyale extends AbstractAnalyseurRang
{

	public boolean reconnaîtreMain(ReqAnalyseMain demande)
	{
		boolean résultat = false;
		Main main = demande.getMain();
		if (RangPoker.estEnSérie(main)
			&& RangPoker.estMêmeCouleur(main)
			&& main.first().getDénomination() == Dénomination.AS)
		{
			// créer le nouveau rang, avec toute la main comme déterminante
			demande.setRangReconnu(
				new RangPoker(RangPoker.RANG_FLUSH_ROYALE, main.getCartes()));

			résultat = true;
		}
		return résultat;
	}

}