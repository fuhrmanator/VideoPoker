/*
 * 2005-06-12
 * Code source inspiré et traduit à partir d'un énoncé de laboratoire du MIT
 * 6.170  	Laboratory in Software Engineering, Fall 2002
 * http://6170.lcs.mit.edu/www-archive/Old-2002-Fall/psets/ps2/ps2.html
 */
 
package cartes;

/**
 * Carte est une classe représentant une carte dans le jeu de Poker,
 * ayant une couleur et une dénomination. C'est une classe immuable.
 * 
 * @author Cris Fuhrman
 */
public class Carte implements Comparable<Carte>
{
	/**
	 * @param dénomination
	 * @param couleur 
	 */
	public Carte(Dénomination dénomination, CouleurCarte couleur)
	{
		this.dénomination = dénomination;
		this.couleur = couleur;
	}

	//-------------------------------------------
	/**
	 * Retourne true si cette carte est égale à l'autre carte.
	 * Deux cartes sont égales chacune a les mêmes couleur et dénomination. 
	 *
	 * @param autreCarte  the other card
	 * @return true si les cartes sont égales; sinon false
	 */
	@Override
	public boolean equals(Object autreCarte)
	{
		if (autreCarte instanceof Carte)
		{
			return (this.hashCode() == autreCarte.hashCode());
		}
		else return false;
	}

	//-------------------------------------------
	/**
	 * Retourne un code de hachage unique (unique hash code) pour l'objet.
	 * Ce code de hachage est pareil pour toutes les cartes égales à cette carte
	 * (selon la méthode equals). C'est une bonne pratique de redéfinir cette
	 * méthode lorsque l'on redéfinit la méthode equals.
	 *
	 * @return le code de hachage de cet objet
	 */
	@Override
	public int hashCode()
	{
		return
			(couleurNuméro(this.couleur) * Dénomination.DÉNOMINATIONS.size())
				+ dénomNuméro(this.dénomination);
	}

	/**
	 * Retourne l'indice de la couleur de la carte, utilisé pour calculer les valeurs
	 * numériques (internes) de la carte.
	 * 
	 * @param couleur
	 * @return valeur entre 0 et 3
	 */
	private int couleurNuméro(CouleurCarte couleur)
	{
		return CouleurCarte.COULEURS.indexOf(couleur);
	}
	
	/**
	 * Retourne l'indice de la dénomination de la carte, utilisé pour calculer les valeurs
	 * numériques (internes) de la carte.
	 * 
	 * @param dénomination pour laquelle on cherche son numéro
	 * @return valeur entre 1 et 13
	 */
	private int dénomNuméro(Dénomination dénom)
	{
		// note : le "2" est le premier numéro
		return Dénomination.DÉNOMINATIONS.indexOf(dénom) + 1;
	}

	/*
	 * 
     * @param o  l'objet qui sera comparé
     * @return   un entier négatif, zéro ou un entier positif, selon si
     *           cette carte est inférieure, égal ou supérieure à
     * 			 l'objet spécifié.
     * @exception ClassCastException si l'objet spécifié n'a pas le type Carte
     * @exception NullPointerException si l'objet spécifié est null
	 */
	public int compareTo(Carte c)
	{
		if (c == null)
		{
			throw new NullPointerException();
		}
		Carte autreCarte = c;
		int cetteCarteVal =
			dénomNuméro(this.dénomination) * CouleurCarte.COULEURS.size()
				+ couleurNuméro(this.couleur);
		int autreCarteVal =
			dénomNuméro(autreCarte.dénomination) * CouleurCarte.COULEURS.size()
				+ couleurNuméro(autreCarte.couleur);

		//System.out.println("compareTo: this.carte: " + this + ", cetteCarteVal = " + cetteCarteVal + ", autreCarte: " + autreCarte + ", autreCarteVal = " + autreCarteVal);

		return cetteCarteVal - autreCarteVal;
	}

	/**
	 * Retourne la dénomination pour la carte
	 * @return la dénomination
	 */
	public Dénomination getDénomination()
	{
		return dénomination;
	}
	
	/**
	 * Retourne la couleur (carreau, pique, etc.) de la carte
	 * @return la couleur
	 */
	public CouleurCarte getCouleur()
	{
		return couleur;
	}
	
	/* 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return this.dénomination.toString() + " de " + this.couleur.toString();
	}
	
	private CouleurCarte couleur;    // p.ex. coeur, pique, etc.
	private Dénomination dénomination;     // p.ex. 2, 3, 4, ..., Dame, Roi, As
	
}
