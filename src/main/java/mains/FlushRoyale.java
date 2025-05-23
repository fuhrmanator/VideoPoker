/*
 * Created on Dec 10, 2004
 *
 */
package mains;

import cartes.CardUtils;

/**
 * @author Cris
 *
 */
public class FlushRoyale extends AbstractConnaîsseurRang
{

	public boolean reconnaîtreMain(DemandeRecMain demande)
	{
		boolean résultat = false;
		Main main = demande.getMain();
		if (RangPoker.estEnSérie(main) && RangPoker.estMêmeSorte(main) && main.first().getRang() == CardUtils.RANK_ACE)
		{
			// on utilise la sorte pour distinguer entre FLUSH ROYALE
			demande.setRangReconnu(
				new RangPoker(
					RangPoker.RANG_FLUSH_ROYALE,
					main.first().getSorte()));

			résultat = true;
		}
		return résultat;
	}

}