/*
 * Created on Dec 16, 2004
 *
 */
package mains;

import cartes.Carte;
import cartes.Dénomination;

/**
 * Ensemble de tests unitaires pour tester l'analyse d'une main qui est une quinte couleur (Straight Flush) de Poker
 * 
 * @author Cris
 */
public class QuinteCouleurTest extends AbstractAnalyseurRangTest
{

	/**
	 * @param arg0
	 */
	public QuinteCouleurTest(String arg0)
	{
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public void testÉvalueMain()
	{
		/*
		 * Test avec main qui n'est pas une quinte couleur
		 */
		Main main = new Main();
		main.add(new Carte(Dénomination.TROIS, cartes.CouleurCarte.CARREAU));
		main.add(new Carte(Dénomination.NEUF, cartes.CouleurCarte.PIQUE));
		main.add(new Carte(Dénomination.AS, cartes.CouleurCarte.TRÈFLE));
		main.add(new Carte(Dénomination.DAME, cartes.CouleurCarte.COEUR));
		main.add(new Carte(Dénomination.DEUX, cartes.CouleurCarte.COEUR));
		
		assertFalse(new QuinteCouleur().reconnaîtreMain(new ReqAnalyseMain(main)));

		/*
		 * Test avec main qui n'est pas une quinte couleur
		 */
		main = new Main();
		main.add(new Carte(Dénomination.TROIS, cartes.CouleurCarte.TRÈFLE));
		main.add(new Carte(Dénomination.CINQ, cartes.CouleurCarte.CARREAU));
		main.add(new Carte(Dénomination.QUATRE, cartes.CouleurCarte.CARREAU));
		main.add(new Carte(Dénomination.DEUX, cartes.CouleurCarte.CARREAU));
		main.add(new Carte(Dénomination.AS, cartes.CouleurCarte.CARREAU));
		
		assertFalse(new QuinteCouleur().reconnaîtreMain(new ReqAnalyseMain(main)));

		/*
		 * Test avec main qui est une quinte couleur avec AS
		 */
		main = new Main();
		main.add(new Carte(Dénomination.TROIS, cartes.CouleurCarte.CARREAU));
		main.add(new Carte(Dénomination.CINQ, cartes.CouleurCarte.CARREAU));
		main.add(new Carte(Dénomination.QUATRE, cartes.CouleurCarte.CARREAU));
		main.add(new Carte(Dénomination.DEUX, cartes.CouleurCarte.CARREAU));
		main.add(new Carte(Dénomination.AS, cartes.CouleurCarte.CARREAU));
		
		assertTrue(new QuinteCouleur().reconnaîtreMain(new ReqAnalyseMain(main)));

		/*
		 * Test avec main qui est une quinte couleur sans AS
		 */
		main = new Main();
		main.add(new Carte(Dénomination.TROIS, cartes.CouleurCarte.COEUR));
		main.add(new Carte(Dénomination.CINQ, cartes.CouleurCarte.COEUR));
		main.add(new Carte(Dénomination.QUATRE, cartes.CouleurCarte.COEUR));
		main.add(new Carte(Dénomination.DEUX, cartes.CouleurCarte.COEUR));
		main.add(new Carte(Dénomination.SIX, cartes.CouleurCarte.COEUR));

		assertTrue(new QuinteCouleur().reconnaîtreMain(new ReqAnalyseMain(main)));

	}
}

///
