/*
 * Created on Dec 16, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mains;

import cartes.Carte;

/**
 * @author Cris
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class QuinteCouleurTest extends RangPokerTest
{

	public void testÉvalueMain()
	{
		/*
		 * Test avec main qui n'est pas une quinte couleur
		 */
		Main main = new Main();
		main.add(new Carte("trois", "carreau"));
		main.add(new Carte("neuf", "pique"));
		main.add(new Carte("as", "trèfle"));
		main.add(new Carte("dame", "coeur"));
		main.add(new Carte("deux", "coeur"));
		
		assertFalse(new QuinteCouleur().reconnaîtreMain(new DemandeRecMain(main)));

		/*
		 * Test avec main qui n'est pas une quinte couleur
		 */
		main = new Main();
		main.add(new Carte("trois", "trèfle"));
		main.add(new Carte("cinq", "carreau"));
		main.add(new Carte("quatre", "carreau"));
		main.add(new Carte("deux", "carreau"));
		main.add(new Carte("as", "carreau"));
		
		assertFalse(new QuinteCouleur().reconnaîtreMain(new DemandeRecMain(main)));

		/*
		 * Test avec main qui est une quinte couleur avec AS
		 */
		main = new Main();
		main.add(new Carte("trois", "carreau"));
		main.add(new Carte("cinq", "carreau"));
		main.add(new Carte("quatre", "carreau"));
		main.add(new Carte("deux", "carreau"));
		main.add(new Carte("as", "carreau"));
		
		assertTrue(new QuinteCouleur().reconnaîtreMain(new DemandeRecMain(main)));

		/*
		 * Test avec main qui est une quinte couleur sans AS
		 */
		main = new Main();
		main.add(new Carte("trois", "coeur"));
		main.add(new Carte("cinq", "coeur"));
		main.add(new Carte("quatre", "coeur"));
		main.add(new Carte("deux", "coeur"));
		main.add(new Carte("six", "coeur"));

		assertTrue(new QuinteCouleur().reconnaîtreMain(new DemandeRecMain(main)));

	}
}

///
