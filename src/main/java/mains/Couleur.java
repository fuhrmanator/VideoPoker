/*
 * Created on Dec 10, 2004
 *
 */
package mains;

/**
 * @author Cris
 *
 */
public class Couleur extends AbstractConnaîsseurRang
{

	public boolean reconnaîtreMain(DemandeRecMain demande)
	{
		boolean résultat = false;
		Main main = demande.getMain();
		// essayer de reconnaître une couleur
		if (RangPoker.estMêmeSorte(main))
		{
			demande.setRangReconnu(new RangPoker(RangPoker.RANG_COULEUR, 0));
			résultat = true;
		}
		return résultat;
	}

}