/*
 * Created on Dec 10, 2004
 *
 */
package mains;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import cartes.Carte;
import cartes.CouleurCarte;
import cartes.Dénomination;

/**
 * @author Cris
 *
 */
public class RangPoker implements Comparable
{

	private static Class[] analyseursRangsEnOrdre = 
	{
		FlushRoyale.class,
		QuinteCouleur.class,
		Carré.class,
		MainPleine.class,
		Couleur.class,
		Quinte.class,
		Brelan.class, 
		DeuxPaires.class,
		Paire.class,
		CarteIsolée.class,
	};

	static AbstractAnalyseurRang chaîneAnalyseurs = initChaîneAnalyseurs();

	public static int RANG_CARTE_ISOLEE = 0,
		RANG_PAIRE = 1,
		RANG_DEUX_PAIRES = 2,
		RANG_BRELAN = 3,
		RANG_QUINTE = 4,
		RANG_COULEUR = 5,
		RANG_MAIN_PLEINE = 6,
		RANG_CARRÉ = 7,
		RANG_QUINTE_COULEUR = 8,
		RANG_QUINTUPLET = 9,
		RANG_FLUSH_ROYALE = 10;

	public static String[] RANGS_RESSOURCE = {
		"highCard",
		"pair",
		"twoPairs",
		"threeOfAKind",
		"straight",
		"flush",
		"fullHouse",
		"fourOfAKind",
		"straightFlush",
		"fiveOfAKind",
		"royalFlush",
	};

	/**
	 * Créer un rang pour une main de Poker. 
	 * @param valeurMajeure valeur du rang de la main
	 * @param cartesDéterminantes autres cartes nécessaire pour l'importance du rang
	 */
	public RangPoker(int valeurMajeure, Collection<Carte> cartesDéterminantes)
	{
		this.rang = valeurMajeure;
		this.cartesDéterminantes = cartesDéterminantes;
	}

	/**
	 * Initialiser la chaîne d'analyseurs
	 * @return le premier analyseur de la liste (chaîne) 
	 */
	private static AbstractAnalyseurRang initChaîneAnalyseurs()
	{
		AbstractAnalyseurRang analyseur = null;
		AbstractAnalyseurRang premierAnalyseur = null;
		AbstractAnalyseurRang analyseurDernier = null;

		/*
		 * Créer la chaîne dynamiquement, à partir du tableau de connaîsseurs
		 */
		for (int rang = 0; rang < analyseursRangsEnOrdre.length; rang++)
		{
			try
			{
				analyseur = (AbstractAnalyseurRang) analyseursRangsEnOrdre[rang].newInstance();
				// mémoriser le premier
				if (premierAnalyseur == null)
				{
					premierAnalyseur = analyseur;
				}
				// enchaîner avec le précédant avec le courant
				if (analyseurDernier != null)
				{
					analyseurDernier.setSuivant(analyseur);
				}
				analyseurDernier = analyseur;
			} catch (InstantiationException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// afficher la chaîne
//		System.out.println("Chaîne de connaîsseurs: ");
//		connaîsseur = premierConnaîsseur;
//		while(connaîsseur != null)
//		{
//			System.out.println("Connaîsseur " + connaîsseur.getClass().getName());
//			connaîsseur = connaîsseur.getSuivant();
//		}
		// retourner le premier élément dans la chaîne
		return premierAnalyseur;
	}

	/**
	 * Retourner une instance de RangPoker
	 * Cette méthode trouve le rang le plus haut d'une main.
	 * C'est une implémentation du patron Chain of Responsability
	 * @param main de poker pour laquelle on veut obtenir son rang
	 * @return le RangPoker correspondant à la main
	 */
	public static RangPoker fabriqueRang(Main main)
	{
		ReqAnalyseMain demande = new ReqAnalyseMain(main);
		chaîneAnalyseurs.traiterDemande(demande);
		RangPoker rangMain = demande.getRangReconnu();
		return rangMain;
	}	

	/**
	 * Identifie les groupes ayant N cartes avec la même dénomination
	 * Inspiré de http://6170.lcs.mit.edu/www-archive/Old-2002-Fall/psets/ps2/ps2.html
	 * p.ex. si cherche des paires dans la liste {4 de trèfle, 4 de carreau, 4 de pique, 4 de coeur, 8 de pique}, 
	 * cette méthode trouvera deux groupes : {4 de trèfle, 4 de carreau} et {4 de pique, 4 de coeur}. 
	 * La méthode retournera finalement le SortedSet {4 de trèfle, 4 de pique}.
	 * 
	 * @param cartes la liste de cartes pour la recherche
	 * @param n
	 * @return un SortedSet contenant la carte inférieure de chaque groupe de N cartes étant de la même dénomination
	 *  
	 */
	protected static SortedSet<Carte> trouverDénominationN(Iterator cartes, int n)
	{
		SortedSet<Carte> trouvailles = new TreeSet<Carte>(Collections.reverseOrder());
		SortedSet<Carte> trouvaillesTemp = null;

		int rang = -1;
		while(cartes.hasNext())
		{
			Carte carte = (Carte) cartes.next();
			// 
			int valeurRelative = Dénomination.DÉNOMINATIONS.indexOf(carte.getDénomination());
			if (valeurRelative != rang)
			{
				rang = valeurRelative;
				trouvaillesTemp = new TreeSet<Carte>(Collections.reverseOrder());
				trouvaillesTemp.add(carte);
			}
			else
			{
				trouvaillesTemp.add(carte);
				if (trouvaillesTemp.size() == n)
				{
					// ajoute première carte dans l'ensemble à retourner
					trouvailles.add(trouvaillesTemp.iterator().next());
					// annuler les correspondances
					rang = -1;
				}
			}
		}
		System.out.println("trouverDénominationN : " + trouvailles);
		return trouvailles;
	}

	/**
	 * Comparer deux rangs
	 */
	public int compareTo(Object o)
	{
		RangPoker autreRang = (RangPoker) o;
		// TODO -- this needs fixing for subtle rules - should use cartesDéterminantes to give values
//		Integer valThis = new Integer(this.rang * FACTEUR_MAJEURE  + this.valeurMineure);
		Integer valThis = new Integer(this.rang * FACTEUR_MAJEURE);
		Integer valAutre = new Integer(autreRang.rang * FACTEUR_MAJEURE);
		System.out.println("RangPoker: compareTo() " + valThis + " compared to " + valAutre);
		return - valThis.compareTo(valAutre);
	}

	/**
	 * @param main
	 * @param paires
	 * @return
	 */
	public static Carte identifieKicker(Main main, SortedSet paires)
	{
		// identifier la carte (la kicker) qui n'est pas dans le cas de deux paires
		for (Iterator mainIter = main.iterator(); mainIter.hasNext();)
		{
			Carte carteMain = (Carte) mainIter.next();
			boolean dansPaire = false;
			for (Iterator paireIter = paires.iterator(); paireIter.hasNext();)
			{
				Carte paire = (Carte) paireIter.next();
				if (carteMain.getDénomination() == paire.getDénomination())
				{
					dansPaire = true;
				}
			}
			if (!dansPaire)
			{
				System.out.println("RangPoker: identifieKicker() -> " + carteMain);
				return carteMain;
			}
		}
		assert(false);
		return null;
	}

	/**
	 * @param main
	 * @return true si toutes les cartes dans la main sont de la même couleur
	 */
	public static boolean estMêmeCouleur(Main main)
	{
		boolean estMêmeSorte = false;
		Iterator carteIter = main.iterator();
		// mémoriser la sorte de la 1ere carte
		Carte carte = (Carte) carteIter.next();
		CouleurCarte couleurTemp = carte.getCouleur();

		System.out.println("estMêmeCouleur: couleurTemp = " + couleurTemp);

		while(carteIter.hasNext())
		{
			System.out.println("estMêmeCouleur: carte.getSorte() = " + carte.getCouleur());
			if (!carte.getCouleur().equals(couleurTemp)) 
			{
				break;
			}
			carte = (Carte) carteIter.next();
		}
		// si on est passé par toutes les cartes à la sortie de la boucle
		// alors il s'agit d'une couleur
		if (!carteIter.hasNext())
		{
			estMêmeSorte = true;
		}
		return estMêmeSorte;
	}

	/**
	 * @param main
	 * @return true si toutes les cartes dans la main sont dans une série
	 */
	public static boolean estEnSérie(Main main)
	{
		System.out.println("estEnSérie: " + main);
		boolean estEnSérie = false;
		Iterator carteIter = main.iterator();
		// mémoriser le rang de la 1ere carte
		Carte carte = (Carte) carteIter.next();
		int dénomValDern = Dénomination.DÉNOMINATIONS.indexOf(carte.getDénomination());
		int compte = 1;
		System.out.println("estEnSérie: " + carte);
		
		while (carteIter.hasNext())
		{
			carte = (Carte) carteIter.next();
			System.out.println("estEnSérie: " + carte);
			// ordre décroissant, cas spécial où l'as est dans une série
			// if (carte.getRang() != rangTemp - 1 && !(rangTemp == CardUtils.RANK_ACE && carte.getRang() == CardUtils.RANK_2 + main.size() - 2)) 

			// cartes sont triées ordre décroissant
			boolean suit = Dénomination.DÉNOMINATIONS.indexOf(carte.getDénomination()) == dénomValDern - 1;
			boolean premierAs = dénomValDern == Dénomination.DÉNOMINATIONS.indexOf(Dénomination.AS);
			boolean deuxiemeCinq = carte.getDénomination() == Dénomination.CINQ;

//			System.out.println("estEnSérie: suit = " + suit);
//			System.out.println("estEnSérie: premierAs = " + premierAs);
//			System.out.println("estEnSérie: deuxiemeCinq = " + deuxiemeCinq);

			if (suit || (premierAs && deuxiemeCinq))
			{
				compte++;
			} else
			{
				break;
			}
			dénomValDern = Dénomination.DÉNOMINATIONS.indexOf(carte.getDénomination());
		}
		// si on est passé par toutes les cartes à la sortie de la boucle
		// alors il s'agit d'une quinte
		if (!carteIter.hasNext() && compte == main.size())
		{
			estEnSérie = true;
		}
		return estEnSérie;
	}

	// multiplicateur pour la valeur majeure
	private static int FACTEUR_MAJEURE = 10000;

	/**
	 * @return
	 */
	public int getRang()
	{
		return this.rang;
	}

	// TODO add the winning value for video poker for each hand

	/**
	 * Chaque sous-classe de rang a une valeure
	 */
	protected int rang;

	/**
	 * Valeur pour distinguer entre deux instances du même rang
	 */
	protected Collection<Carte> cartesDéterminantes = new ArrayList<Carte>();

}
