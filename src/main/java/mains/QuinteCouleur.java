/*
 * Created on Dec 10, 2004
 *
 */
package mains;

/**
 * @author Cris
 *
 */
public class QuinteCouleur extends AbstractConnaîsseurRang
{

	public boolean reconnaîtreMain(DemandeRecMain demande)
	{
		boolean résultat = false;
		Main main = demande.getMain();
		if (RangPoker.estEnSérie(main) && RangPoker.estMêmeSorte(main))
		{
			// la carte la plus haute
			demande.setRangReconnu(
				new RangPoker(
					RangPoker.RANG_QUINTE_COULEUR,
					main.first().getRang()));

			résultat = true;
		}
		return résultat;
	}

}