/*
 * Created on Dec 10, 2004
 *
 */
package mains;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import cartes.CardUtils;
import cartes.Carte;

/**
 * @author Cris
 *
 */
public class RangPoker implements Comparable
{

	private static Class[] connaîsseursRangsEnOrdre = 
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

	static AbstractConnaîsseurRang chaîneConnaîsseurs = initChaîneConnaîsseurs();

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
	 * @param valeurMineure valeur pour distinguer entre deux main ayant le même rang
	 */
	public RangPoker(int valeurMajeure, int valeurMineure)
	{
		this.valeurMajeure = valeurMajeure;
		this.valeurMineure = valeurMineure;
	}

	/**
	 * @return
	 */
	private static AbstractConnaîsseurRang initChaîneConnaîsseurs()
	{
		AbstractConnaîsseurRang connaîsseur = null;
		AbstractConnaîsseurRang premierConnaîsseur = null;
		AbstractConnaîsseurRang connaîsseurDernier = null;

		/*
		 * Créer la chaîne dynamiquement, à partir du tableau de connaîsseurs
		 */
		for (int rang = 0; rang < connaîsseursRangsEnOrdre.length; rang++)
		{
			try
			{
				connaîsseur = (AbstractConnaîsseurRang) connaîsseursRangsEnOrdre[rang].newInstance();
				// mémoriser le premier
				if (premierConnaîsseur == null)
				{
					premierConnaîsseur = connaîsseur;
				}
				// enchaîner avec le précédant avec le courant
				if (connaîsseurDernier != null)
				{
					connaîsseurDernier.setSuivant(connaîsseur);
				}
				connaîsseurDernier = connaîsseur;
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
		return premierConnaîsseur;
	}

	/**
	 * Retourner une instance de RangPoker
	 * Cette méthode trouve le rang le plus haut d'une main.
	 * C'est une impémentation du patron Chain of Responsability
	 * @param main
	 * @return
	 */
	public static RangPoker fabriqueRang(Main main)
	{
		DemandeRecMain demande = new DemandeRecMain(main);
		chaîneConnaîsseurs.traiterDemande(demande);
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
	protected static SortedSet trouverDénominationN(Iterator cartes, int n)
	{
		SortedSet trouvailles = new TreeSet();
		SortedSet trouvaillesTemp = null;

		int rang = -1;
		while(cartes.hasNext())
		{
			Carte carte = (Carte) cartes.next();
			// 
			if (carte.getRang() != rang)
			{
				rang = carte.getRang();
				trouvaillesTemp = new TreeSet();
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
	 * Comparer deux rangs -- normalement redéfini dans chaque sous-classe
	 */
	public int compareTo(Object o)
	{
		RangPoker autreRang = (RangPoker) o;
		Integer valThis = new Integer(this.valeurMajeure * FACTEUR_MAJEURE  + this.valeurMineure);
		Integer valAutre = new Integer(autreRang.valeurMajeure * FACTEUR_MAJEURE + autreRang.valeurMineure);
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
				if (carteMain.getRang() == paire.getRang())
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
	 * @return true si toutes les cartes dans la main sont de la même sorte
	 */
	public static boolean estMêmeSorte(Main main)
	{
		boolean estMêmeSorte = false;
		Iterator carteIter = main.iterator();
		// mémoriser la sorte de la 1ere carte
		Carte carte = (Carte) carteIter.next();
		int sorteTemp = carte.getSorte();

		while(carteIter.hasNext())
		{
			if (carte.getSorte() != sorteTemp) 
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
		int rangTemp = carte.getRang();
		int compte = 1;
		System.out.println("estEnSérie: " + carte);
		
		while(carteIter.hasNext())
		{
			carte = (Carte) carteIter.next();
			System.out.println("estEnSérie: " + carte);
			// ordre décroissant, cas spécial où l'as est dans une série
			if (carte.getRang() != rangTemp - 1 && !(rangTemp == CardUtils.RANK_ACE && carte.getRang() == CardUtils.RANK_2 + main.size() - 2)) 
			{
				break;
			}
			else
			{
				compte++;
			}

			rangTemp = carte.getRang();
		}
		// si on est passé par toutes les cartes à la sortie de la boucle
		// alors il s'agit d'une quinte
		if (!carteIter.hasNext() && compte == main.size())
		{
			estEnSérie = true;
		}
		return estEnSérie;
	}

	/**
	 * Chaque sous-classe de rang a une valeure
	 */
	protected int valeurMajeure;

	/**
	 * Valeur pour distinguer entre deux instances du même rang
	 */
	protected int valeurMineure = 0;

	// multiplicateur pour la valeur majeure
	private static int FACTEUR_MAJEURE = 10000;

	/**
	 * @return
	 */
	public int getRang()
	{
		return this.valeurMajeure;
	}

	// TODO add the winning value for video poker for each hand

}
