/*
 * Created on Dec 16, 2004
 *
 */
package mains;

import cartes.Carte;
import cartes.CouleurCarte;
import cartes.Dénomination;

/**
 * Ensemble de tests unitaires pour tester l'analyse d'une main qui est une couleur (Flush) de Poker
 * 
 * @author Cris Fuhrman
 */
public class CouleurTest extends AbstractAnalyseurRangTest
{

	/**
	 * @param arg0
	 */
	public CouleurTest(String arg0)
	{
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public void testÉvalueMain()
	{
		/*
		 * Test avec main qui n'est pas une couleur
		 */
		Main main = new Main();
		main.add(new Carte(Dénomination.TROIS, CouleurCarte.CARREAU));
		main.add(new Carte(Dénomination.NEUF, CouleurCarte.PIQUE));
		main.add(new Carte(Dénomination.AS, CouleurCarte.TRÈFLE));
		main.add(new Carte(Dénomination.DAME, CouleurCarte.COEUR));
		main.add(new Carte(Dénomination.DEUX, CouleurCarte.COEUR));
		
		assertFalse(new mains.Couleur().reconnaîtreMain(new ReqAnalyseMain(main)));

		/*
		 * Test avec main qui est une couleur
		 */
		main = new Main();
		main.add(new Carte(Dénomination.TROIS, CouleurCarte.CARREAU));
		main.add(new Carte(Dénomination.NEUF, CouleurCarte.CARREAU));
		main.add(new Carte(Dénomination.AS, CouleurCarte.CARREAU));
		main.add(new Carte(Dénomination.DAME, CouleurCarte.CARREAU));
		main.add(new Carte(Dénomination.DEUX, CouleurCarte.CARREAU));
		
		assertTrue(new mains.Couleur().reconnaîtreMain(new ReqAnalyseMain(main)));

	}
}

///
