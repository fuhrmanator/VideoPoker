/*
 * Created on Jun 12, 2005
 *
 */
package mains;

import java.util.Collection;
import java.util.Iterator;

import cartes.Carte;
import cartes.Dénomination;
import junit.framework.TestCase;

/**
 * @author Cris
 *
 */
public class MainTest extends TestCase
{

	/**
	 * Constructor for MainTest.
	 * @param arg0
	 */
	public MainTest(String arg0)
	{
		super(arg0);
	}

	public void testGetCartes()
	{
		Main main = new Main();
		main.add(new Carte(Dénomination.TROIS, cartes.CouleurCarte.CARREAU));
		main.add(new Carte(Dénomination.NEUF, cartes.CouleurCarte.PIQUE));
		main.add(new Carte(Dénomination.AS, cartes.CouleurCarte.TRÈFLE));
		main.add(new Carte(Dénomination.DAME, cartes.CouleurCarte.COEUR));
		main.add(new Carte(Dénomination.DEUX, cartes.CouleurCarte.COEUR));
		
		Collection cartes = main.getCartes();
		
		assertTrue(cartes.size() == 5);
		Iterator iter = cartes.iterator();
		Carte carte = (Carte) iter.next();
		
		// vérifier l'ordre décroissant
		assertTrue(carte.getDénomination() == Dénomination.AS);
		carte = (Carte) iter.next();
		assertTrue(carte.getDénomination() == Dénomination.DAME);
		carte = (Carte) iter.next();
		assertTrue(carte.getDénomination() == Dénomination.NEUF);
		carte = (Carte) iter.next();
		assertTrue(carte.getDénomination() == Dénomination.TROIS);
		carte = (Carte) iter.next();
		assertTrue(carte.getDénomination() == Dénomination.DEUX);

		assertFalse(iter.hasNext());
		
	}

}
