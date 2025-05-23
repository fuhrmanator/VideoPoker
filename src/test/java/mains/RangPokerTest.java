/*
 * Created on Dec 11, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mains;

import cartes.Carte;
import cartes.Dénomination;
import junit.framework.TestCase;
import java.util.*;

/**
 * @author Cris
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RangPokerTest extends TestCase
{

	public void testEstMêmeCouleur()
	{
		Main main = new Main();
		main.add(new Carte(Dénomination.TROIS, cartes.CouleurCarte.CARREAU));
		main.add(new Carte(Dénomination.NEUF, cartes.CouleurCarte.PIQUE));
		main.add(new Carte(Dénomination.AS, cartes.CouleurCarte.TRÈFLE));
		main.add(new Carte(Dénomination.DAME, cartes.CouleurCarte.COEUR));
		main.add(new Carte(Dénomination.DEUX, cartes.CouleurCarte.COEUR));
		
		assertFalse(RangPoker.estMêmeCouleur(main));

		main = new Main();
		main.add(new Carte(Dénomination.TROIS, cartes.CouleurCarte.COEUR));
		main.add(new Carte(Dénomination.NEUF, cartes.CouleurCarte.COEUR));
		main.add(new Carte(Dénomination.AS, cartes.CouleurCarte.COEUR));
		main.add(new Carte(Dénomination.DAME, cartes.CouleurCarte.COEUR));
		main.add(new Carte(Dénomination.DEUX, cartes.CouleurCarte.COEUR));
		
		assertTrue(RangPoker.estMêmeCouleur(main));
		
	}

	public void testEstEnSérie()
	{
		Main main = new Main();
		main.add(new Carte(Dénomination.HUIT, cartes.CouleurCarte.CARREAU));
		main.add(new Carte(Dénomination.NEUF, cartes.CouleurCarte.PIQUE));
		main.add(new Carte(Dénomination.DIX, cartes.CouleurCarte.TRÈFLE));
		main.add(new Carte(Dénomination.VALET, cartes.CouleurCarte.COEUR));
		main.add(new Carte(Dénomination.DEUX, cartes.CouleurCarte.COEUR));
		
		assertFalse(RangPoker.estEnSérie(main));

		main = new Main();
		main.add(new Carte(Dénomination.HUIT, cartes.CouleurCarte.CARREAU));
		main.add(new Carte(Dénomination.NEUF, cartes.CouleurCarte.PIQUE));
		main.add(new Carte(Dénomination.DIX, cartes.CouleurCarte.TRÈFLE));
		main.add(new Carte(Dénomination.VALET, cartes.CouleurCarte.COEUR));
		main.add(new Carte(Dénomination.SEPT, cartes.CouleurCarte.COEUR));
		
		assertTrue(RangPoker.estEnSérie(main));
		
	}


	public void testTrouverDénominationN()
	{
		/*
		 * Test avec zéro paires
		 */
		Main main = new Main();
		main.add(new Carte(Dénomination.TROIS, cartes.CouleurCarte.CARREAU));
		main.add(new Carte(Dénomination.NEUF, cartes.CouleurCarte.PIQUE));
		main.add(new Carte(Dénomination.AS, cartes.CouleurCarte.TRÈFLE));
		main.add(new Carte(Dénomination.DAME, cartes.CouleurCarte.COEUR));
		main.add(new Carte(Dénomination.DEUX, cartes.CouleurCarte.COEUR));
		
		assertTrue(RangPoker.trouverDénominationN(main.iterator(), 2).size() == 0);

		/*
		 * Test avec un paire
		 */
		main = new Main();
		main.add(new Carte(Dénomination.TROIS, cartes.CouleurCarte.CARREAU));
		main.add(new Carte(Dénomination.TROIS, cartes.CouleurCarte.PIQUE));
		main.add(new Carte(Dénomination.AS, cartes.CouleurCarte.TRÈFLE));
		main.add(new Carte(Dénomination.DAME, cartes.CouleurCarte.COEUR));
		main.add(new Carte(Dénomination.DEUX, cartes.CouleurCarte.COEUR));
		
		SortedSet résultat = RangPoker.trouverDénominationN(main.iterator(), 2);

		// set devrait avoir une carte
		assertTrue(résultat.size() == 1);
		// le trois
		assertTrue(((Carte)(résultat.iterator().next())).getDénomination().equals(Dénomination.TROIS));

		/*
		 * Test avec deux paires
		 */
		main = new Main();
		main.add(new Carte(Dénomination.TROIS, cartes.CouleurCarte.CARREAU));
		main.add(new Carte(Dénomination.TROIS, cartes.CouleurCarte.PIQUE));
		main.add(new Carte(Dénomination.AS, cartes.CouleurCarte.TRÈFLE));
		main.add(new Carte(Dénomination.DIX, cartes.CouleurCarte.COEUR));
		main.add(new Carte(Dénomination.DIX, cartes.CouleurCarte.TRÈFLE));
		
		résultat = RangPoker.trouverDénominationN(main.iterator(), 2);

		// set devrait avoir deux cartes
		assertTrue(résultat.size() == 2);
		Iterator cartesIter = résultat.iterator();
		assertTrue(((Carte)(cartesIter.next())).getDénomination().equals(Dénomination.DIX));
		assertTrue(((Carte)(cartesIter.next())).getDénomination().equals(Dénomination.TROIS));

		/*
		 * Test avec un brelan (3 de la même dénomination)
		 */
		main = new Main();
		main.add(new Carte(Dénomination.TROIS, cartes.CouleurCarte.CARREAU));
		main.add(new Carte(Dénomination.AS, cartes.CouleurCarte.PIQUE));
		main.add(new Carte(Dénomination.AS, cartes.CouleurCarte.TRÈFLE));
		main.add(new Carte(Dénomination.AS, cartes.CouleurCarte.COEUR));
		main.add(new Carte(Dénomination.TROIS, cartes.CouleurCarte.TRÈFLE));
		
		résultat = RangPoker.trouverDénominationN(main.iterator(), 3);

		// set devrait avoir une carte, l'as
		assertTrue(résultat.size() == 1);
		assertTrue(((Carte)(résultat.iterator().next())).getDénomination().equals(Dénomination.AS));

		/*
		 * Test avec un carré (4 de la même dénomination)
		 */
		main = new Main();
		main.add(new Carte(Dénomination.AS, cartes.CouleurCarte.CARREAU));
		main.add(new Carte(Dénomination.AS, cartes.CouleurCarte.PIQUE));
		main.add(new Carte(Dénomination.AS, cartes.CouleurCarte.TRÈFLE));
		main.add(new Carte(Dénomination.AS, cartes.CouleurCarte.COEUR));
		main.add(new Carte(Dénomination.TROIS, cartes.CouleurCarte.TRÈFLE));
		
		résultat = RangPoker.trouverDénominationN(main.iterator(), 4);

		// set devrait avoir une carte, l'as
		assertTrue(résultat.size() == 1);
		assertTrue(((Carte)(résultat.iterator().next())).getDénomination().equals(Dénomination.AS));

		/*
		 * Test avec un paire (devrait retourner deux paires)
		 */
		résultat = RangPoker.trouverDénominationN(main.iterator(), 2);

		// set devrait avoir deux cartes, les as (deux fois)
		assertTrue(résultat.size() == 2);
		assertTrue(((Carte)(résultat.iterator().next())).getDénomination().equals(Dénomination.AS));
	}

//	public void testFabriqueRang()
//	{
//		/*
//		 * Test avec une main carte isolée
//		 */
//		Main main = new Main();
//		main.add(new Carte("trois", "carreau"));
//		main.add(new Carte("neuf", "pique"));
//		main.add(new Carte("as", "trèfle"));
//		main.add(new Carte("dame", "coeur"));
//		main.add(new Carte("deux", "coeur"));
//		
//		RangPoker résultat = RangPoker.fabriqueRang(main);
//		assertTrue(résultat.valeurMajeure == RangPoker.RANG_CARTE_ISOLEE);
//		
//		/*
//		 * Test avec une paire
//		 */
//		main = new Main();
//		main.add(new Carte("trois", "carreau"));
//		main.add(new Carte("neuf", "pique"));
//		main.add(new Carte("as", "trèfle"));
//		main.add(new Carte("dame", "coeur"));
//		main.add(new Carte("neuf", "coeur"));
//		
//		résultat = RangPoker.fabriqueRang(main);
//		assertTrue(résultat.valeurMajeure == RangPoker.RANG_PAIRE);
//		
//		/*
//		 * Test avec deux mains, légèrement différent
//		 */
//		main = new Main();
//		main.add(new Carte("roi", "carreau"));
//		main.add(new Carte("neuf", "pique"));
//		main.add(new Carte("roi", "trèfle"));
//		main.add(new Carte("dame", "coeur"));
//		main.add(new Carte("neuf", "coeur"));
//		
//		résultat = RangPoker.fabriqueRang(main);
//		assertTrue(résultat.valeurMajeure == RangPoker.RANG_DEUX_PAIRES);
//
//		Main main2 = new Main();
//		main2.add(new Carte("roi", "coeur"));
//		main2.add(new Carte("neuf", "carreau"));
//		main2.add(new Carte("roi", "pique"));
//		main2.add(new Carte("dame", "pique"));
//		main2.add(new Carte("neuf", "trèfle"));
//		
//		RangPoker résultat2 = RangPoker.fabriqueRang(main2);
//		assertTrue(résultat2.valeurMajeure == RangPoker.RANG_DEUX_PAIRES);
//		
//		assertTrue(résultat.compareTo(résultat2) == 0);
//		
//		/*
//		 * Test avec deux mains, légèrement différent
//		 */
//		main = new Main();
//		main.add(new Carte("roi", "carreau"));
//		main.add(new Carte("neuf", "pique"));
//		main.add(new Carte("roi", "trèfle"));
//		main.add(new Carte("as", "coeur"));
//		main.add(new Carte("neuf", "coeur"));
//		
//		résultat = RangPoker.fabriqueRang(main);
//		assertTrue(résultat.valeurMajeure == RangPoker.RANG_DEUX_PAIRES);
//
//		main2 = new Main();
//		main2.add(new Carte("roi", "coeur"));
//		main2.add(new Carte("neuf", "carreau"));
//		main2.add(new Carte("roi", "pique"));
//		main2.add(new Carte("dame", "pique"));
//		main2.add(new Carte("neuf", "trèfle"));
//		
//		résultat2 = RangPoker.fabriqueRang(main2);
//		assertTrue(résultat2.valeurMajeure == RangPoker.RANG_DEUX_PAIRES);
//		
//		// main1 > main2
//		assertTrue(résultat.compareTo(résultat2) == -1);
//
//		/*
//		 * Test avec une différence plus importante
//		 */		
//		main2 = new Main();
//		main2.add(new Carte("as", "coeur"));
//		main2.add(new Carte("neuf", "carreau"));
//		main2.add(new Carte("as", "pique"));
//		main2.add(new Carte("dame", "pique"));
//		main2.add(new Carte("neuf", "trèfle"));
//		
//		résultat2 = RangPoker.fabriqueRang(main2);
//		assertTrue(résultat2.valeurMajeure == RangPoker.RANG_DEUX_PAIRES);
//		
//		// main2 > main1
//		assertTrue(résultat.compareTo(résultat2) == 1);
//	}

}
