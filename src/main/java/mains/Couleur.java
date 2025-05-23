/*
 * Created on Dec 10, 2004
 *
 */
package mains;

/**
 * @author Cris
 *
 */
public class Couleur extends AbstractAnalyseurRang
{

	public boolean reconnaîtreMain(ReqAnalyseMain demande)
	{
		boolean résultat = false;
		Main main = demande.getMain();
		// essayer de reconnaître une couleur
		if (RangPoker.estMêmeCouleur(main))
		{
			// créer le nouveau rang - dans le cas d'une couleur, ça peut prendre toute la main pour décider le gangant
			demande.setRangReconnu(
				new RangPoker(RangPoker.RANG_COULEUR, main.getCartes()));
			résultat = true;
		}
		return résultat;
	}

}