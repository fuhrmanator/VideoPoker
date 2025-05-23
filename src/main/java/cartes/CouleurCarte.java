/*
 * 2005-06-12
 * Code source inspiré et traduit à partir d'un énoncé de laboratoire du MIT
 * 6.170  	Laboratory in Software Engineering, Fall 2002
 * http://6170.lcs.mit.edu/www-archive/Old-2002-Fall/psets/ps2/ps2.html
 * 
 * Note : support pour les icônes a été supprimé
 */
package cartes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Représente la couleur d'une carte. Les quatres couleurs sont : 
 * coeur, pique, carreau, trèfle. 
 * Au poker, le terme "couleur" ne représente jamais le noir ou le rouge.
 */
public final class CouleurCarte implements Comparable {

    //
    // CONSTANTES
    //

    /**
     * La couleur de trèfle.
     */
    public static final CouleurCarte TRÈFLE = new CouleurCarte("trèfle");

    /**
     * La couleur de carreau.
     */
    public static final CouleurCarte CARREAU = new CouleurCarte("carreau");

    /**
     * La couleur de pique.
     */
    public static final CouleurCarte PIQUE = new CouleurCarte("pique");

    /**
     * La couleur de coeur.
     */
    public static final CouleurCarte COEUR = new CouleurCarte("coeur");



    // Rang des couleurs.

    /**
     * Une liste spécifiant l'importance des couleurs en ordre descendant
     */
    public static final List<CouleurCarte> COULEURS =
        Collections.unmodifiableList(Arrays.asList(new CouleurCarte[] {TRÈFLE, CARREAU, PIQUE, COEUR}));


    //
    // VARIABLES MEMBRES
    //

    /**
     * The nom of this suit.
     */
    private final String nom;

    //
    // MÉTHODES
    //

    //-------------------------------------------
    /**
     * Crée une nouvelle couleur de carte
     *
     * @param nom le nom de la couleur
     *
     */
    private CouleurCarte(String nom) {
        if (nom == null) {
            throw new NullPointerException();
        }

        this.nom = nom;
    }


    //-------------------------------------------
    /**
     * Retourne le nom de la couleur
     *
     * @return le nom de la couleur
     *
     */
    public String getNom() {
        return nom;
    }


    //-------------------------------------------
    /**
     * Compare cette couleur à la couleur spécifiée afin de déterminer l'ordre
     *
     * @param o  l'objet qui sera comparé
     * @return   un entier négatif, zéro ou un entier positif, selon si
     *           cette couleur est inférieure, égal ou supérieure à
     * 			 l'objet spécifié.
     * @exception ClassCastException si l'objet spécifié n'a pas le type CouleurCarte
     * @exception NullPointerException si l'objet spécifié est null
     *
     */
    public int compareTo(Object o) {
	     
        if (o == null)
            throw new NullPointerException();
	     
        return COULEURS.indexOf(this) - COULEURS.indexOf((CouleurCarte)o);
    }


    //-------------------------------------------
    /**
     * Returns une description de cette couleur.
     *
     * @return une description de cette couleur
     */
    public String toString() {
        return getNom();
    }

}
