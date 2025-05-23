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
public class CouleurTest extends RangPokerTest
{

	public void testÉvalueMain()
	{
		/*
		 * Test avec main qui n'est pas une couleur
		 */
		Main main = new Main();
		main.add(new Carte("trois", "carreau"));
		main.add(new Carte("neuf", "pique"));
		main.add(new Carte("as", "trèfle"));
		main.add(new Carte("dame", "coeur"));
		main.add(new Carte("deux", "coeur"));
		
		assertFalse(new Couleur().reconnaîtreMain(new DemandeRecMain(main)));

		/*
		 * Test avec main qui est une couleur
		 */
		main = new Main();
		main.add(new Carte("trois", "carreau"));
		main.add(new Carte("neuf", "carreau"));
		main.add(new Carte("as", "carreau"));
		main.add(new Carte("dame", "carreau"));
		main.add(new Carte("deux", "carreau"));
		
		assertTrue(new Couleur().reconnaîtreMain(new DemandeRecMain(main)));

	}
}

///
