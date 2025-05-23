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
public class QuinteTest extends RangPokerTest
{

	public void testÉvalueMain()
	{
		/*
		 * Test avec main qui n'est pas une quinte
		 */
		Main main = new Main();
		main.add(new Carte("trois", "carreau"));
		main.add(new Carte("neuf", "pique"));
		main.add(new Carte("as", "trèfle"));
		main.add(new Carte("dame", "coeur"));
		main.add(new Carte("deux", "coeur"));
		
		assertFalse(new Quinte().reconnaîtreMain(new DemandeRecMain(main)));

		/*
		 * Test avec main qui est une quinte avec AS
		 */
		main = new Main();
		main.add(new Carte("trois", "carreau"));
		main.add(new Carte("cinq", "carreau"));
		main.add(new Carte("quatre", "carreau"));
		main.add(new Carte("deux", "carreau"));
		main.add(new Carte("as", "carreau"));
		
		assertTrue(new Quinte().reconnaîtreMain(new DemandeRecMain(main)));

		/*
		 * Test avec main qui est une quinte sans AS
		 */
		main = new Main();
		main.add(new Carte("trois", "pique"));
		main.add(new Carte("cinq", "carreau"));
		main.add(new Carte("quatre", "trèfle"));
		main.add(new Carte("deux", "coeur"));
		main.add(new Carte("six", "carreau"));

		assertTrue(new Quinte().reconnaîtreMain(new DemandeRecMain(main)));

	}
}

///
