/*
 * 2005-06-12
 * Code source inspiré et traduit à partir d'un énoncé de laboratoire du MIT
 * 6.170  	Laboratory in Software Engineering, Fall 2002
 * http://6170.lcs.mit.edu/www-archive/Old-2002-Fall/psets/ps2/ps2.html
 * 
 */

// cpf 2005-11-09 Mise à jour pour les types "generics" dans Java 1.5
 
package cartes;

import junit.framework.*;

/**
 * Un ensemble de test unitaires dans JUnit pour tester la classe Carte
 */
public class CarteTest extends TestCase {

    //
    // MEMBER VARIABLES
    //

    protected Carte carte;
    protected Dénomination carteDénomination;
    protected CouleurCarte carteCouleur;

    protected Dénomination carteDénomInf;
    protected CouleurCarte carteCoulInf;
    protected Dénomination carteDénomSup;
    protected CouleurCarte carteCoulSup;



    //
    // METHODS
    //

    public CarteTest(String name) {
        super(name);
    }


    protected void setUp() {
        carte = new Carte(Dénomination.VALET, CouleurCarte.PIQUE);
        carteDénomination = Dénomination.VALET;
        carteCouleur = CouleurCarte.PIQUE;

        carteDénomInf = Dénomination.CINQ;
        carteCoulInf = CouleurCarte.CARREAU;

        carteDénomSup = Dénomination.ROI;
        carteCoulSup = CouleurCarte.COEUR;
    }


    public static Test suite() {        
        return new TestSuite(CarteTest.class);
    }


    public void testValue() {
        assertEquals(carteDénomination, carte.getDénomination());
    }


    public void testSuit() {
        assertEquals(carteCouleur, carte.getCouleur());
    }


    public void testCompareTo() {
        
        // Comparing to a null card should throw a NullPointerException.
        try {
            carte.compareTo(null);
            fail("Should raise a NullPointerException");
        }
        catch (NullPointerException npe) {
        }
        catch (Exception e) {
            fail("Should raise a NullPointerException: " + e.toString());
        }


        // Comparing to a String should throw a ClassCastException.
		// cpf 2005-11-09 le test suivant est désuet depuis Java 1.5 et les "generics"
		/*
        try {
            carte.compareTo("test");
            fail("Should raise a ClassCastException");
        }
        catch (ClassCastException cce) {
        }
        catch (Exception e) {
            fail("Should raise a ClassCastException: " + e.toString());
        }
		*/


        // A card cannot be less than the same card.
        Carte carteÉgale = new Carte(carte.getDénomination(), carte.getCouleur());
        assertTrue(carte.compareTo(carteÉgale) == 0);

        // Test two cards with different values and suits.
        Carte carteInf = new Carte(carteDénomInf, carteCoulInf);
        assertTrue(carte.compareTo(carteInf) > 0);
        assertTrue(carteInf.compareTo(carte) < 0);
        carteInf = new Carte(carteDénomInf, carteCoulSup);
        assertTrue(carte.compareTo(carteInf) > 0);
        assertTrue(carteInf.compareTo(carte) < 0);

        // Test two cards with equal values but different suits.
        Carte carteCoulIdenDénomInf = new Carte(carte.getDénomination(), carteCoulInf);
        assertTrue(carte.compareTo(carteCoulIdenDénomInf) > 0);
        assertTrue(carteCoulIdenDénomInf.compareTo(carte) < 0);
        
        // Test that an Ace is always greater than a two and a King.
        Carte carteAs = new Carte(Dénomination.AS, carte.getCouleur());
        Carte carteDeux = new Carte(Dénomination.DEUX, carte.getCouleur());
        Carte carteRoi = new Carte(Dénomination.ROI, carte.getCouleur());
        assertTrue(carteAs.compareTo(carteDeux) > 0);
        assertTrue(carteAs.compareTo(carteRoi) > 0);              
    }


    public void testEquals() {
        assertTrue(!carte.equals(null));
		// cpf 2005-11-09 le test suivant n'est pas (!) désuet depuis Java 1.5 et les "generics"
		//     equals() doit être redéfini avec un argument de type Object toujours.
        assertTrue(!carte.equals("test2"));
        assertEquals(carte, carte);
        assertEquals(carte, (new Carte(carte.getDénomination(), carte.getCouleur())));
        Assert.assertTrue(!carte.equals(new Carte(carteDénomInf, carte.getCouleur())));
    }
}
