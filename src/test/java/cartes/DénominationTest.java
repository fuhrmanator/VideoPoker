/*
 * 2005-06-12
 * Code source inspiré et traduit à partir d'un énoncé de laboratoire du MIT
 * 6.170  	Laboratory in Software Engineering, Fall 2002
 * http://6170.lcs.mit.edu/www-archive/Old-2002-Fall/psets/ps2/ps2.html
 * 
 */
 
package cartes;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Ensemble de tests dans JUnit pour tester la classe Dénomination
 */
public class DénominationTest extends TestCase
{

	/**
	 * Constructor for DénominationTest.
	 * @param arg0
	 */
	public DénominationTest(String arg0)
	{
		super(arg0);
	}

	//
	// METHODS
	//

	public static Test suite() {        
		return new TestSuite(DénominationTest.class);
	}


	public void testCompareTo() {
        
		// Comparing to a null card suit should throw a NullPointerException.
		try {
			Dénomination.VALET.compareTo(null);
			fail("Devrait lancer une NullPointerException");
		}
		catch (NullPointerException npe) {
		}
		catch (Exception e) {
			fail("Devrait lancer une NullPointerException: " + e.toString());
		}


		// Comparing to a String should throw a ClassCastException.
		try {
			Dénomination.TROIS.compareTo("test");
			fail("Devrait lancer une ClassCastException");
		}
		catch (ClassCastException cce) {
		}
		catch (Exception e) {
			fail("Devrait lancer une ClassCastException: " + e.toString());
		}


		// A card value cannot be less than the same card value.
		assertTrue(Dénomination.HUIT.compareTo(Dénomination.HUIT) == 0);

		// Test two different card values.
		assertTrue(Dénomination.AS.compareTo(Dénomination.DEUX) > 0);
		assertTrue(Dénomination.DEUX.compareTo(Dénomination.AS) < 0);
	}


	public void testEquals() {
		assertTrue(!Dénomination.VALET.equals(null));
		assertEquals(Dénomination.SEPT, Dénomination.SEPT);
		Assert.assertTrue(!Dénomination.DEUX.equals(Dénomination.AS));
	}
}
