/*
 * Created on Dec 16, 2004
 *
 */
package mains;

import cartes.Carte;
import cartes.Dénomination;

/**
 * Ensemble de tests unitaires pour tester l'analyse d'une main qui est une quinte de Poker
 * 
 * @author Cris Fuhrman
 */
public class QuinteTest extends AbstractAnalyseurRangTest
{

	/**
	 * @param arg0
	 */
	public QuinteTest(String arg0)
	{
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public void testÉvalueMain()
	{
		/*
		 * Test avec main qui n'est pas une quinte
		 */
		Main main = new Main();
		main.add(new Carte(Dénomination.TROIS, cartes.CouleurCarte.CARREAU));
		main.add(new Carte(Dénomination.NEUF, cartes.CouleurCarte.PIQUE));
		main.add(new Carte(Dénomination.AS, cartes.CouleurCarte.TRÈFLE));
		main.add(new Carte(Dénomination.DAME, cartes.CouleurCarte.COEUR));
		main.add(new Carte(Dénomination.DEUX, cartes.CouleurCarte.COEUR));
		
		assertFalse(new Quinte().reconnaîtreMain(new ReqAnalyseMain(main)));

		/*
		 * Test avec main qui est une quinte avec AS
		 */
		main = new Main();
		main.add(new Carte(Dénomination.TROIS, cartes.CouleurCarte.CARREAU));
		main.add(new Carte(Dénomination.CINQ, cartes.CouleurCarte.CARREAU));
		main.add(new Carte(Dénomination.QUATRE, cartes.CouleurCarte.CARREAU));
		main.add(new Carte(Dénomination.DEUX, cartes.CouleurCarte.CARREAU));
		main.add(new Carte(Dénomination.AS, cartes.CouleurCarte.CARREAU));
		
		assertTrue(new Quinte().reconnaîtreMain(new ReqAnalyseMain(main)));

		/*
		 * Test avec main qui est une quinte sans AS
		 */
		main = new Main();
		main.add(new Carte(Dénomination.TROIS, cartes.CouleurCarte.PIQUE));
		main.add(new Carte(Dénomination.CINQ, cartes.CouleurCarte.CARREAU));
		main.add(new Carte(Dénomination.QUATRE, cartes.CouleurCarte.TRÈFLE));
		main.add(new Carte(Dénomination.DEUX, cartes.CouleurCarte.COEUR));
		main.add(new Carte(Dénomination.SIX, cartes.CouleurCarte.CARREAU));
		
		assertTrue(new Quinte().reconnaîtreMain(new ReqAnalyseMain(main)));

	}
}

///
