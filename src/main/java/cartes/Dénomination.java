/*
 * 2005-06-12
 * Code source inspiré et traduit à partir d'un énoncé de laboratoire du MIT
 * 6.170  	Laboratory in Software Engineering, Fall 2002
 * http://6170.lcs.mit.edu/www-archive/Old-2002-Fall/psets/ps2/ps2.html
 */
package cartes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Représente une valeur d'une carte, parmi 13 valeurs possibles : 
 * (ex.: valet, huit, as)
 */
public final class Dénomination implements Comparable {


    //
    // CONSTANTES
    //

    // les 13 dénominations légales

    /**
     * La dénomination de carte représentant le 2.
     */
    public static final Dénomination DEUX   = new Dénomination("deux",   "2");

    /**
     * La dénomination de carte représentant le 3.
     */
    public static final Dénomination TROIS = new Dénomination("trois", "3");

    /**
     * La dénomination de carte représentant le 4.
     */
    public static final Dénomination QUATRE  = new Dénomination("quatre",  "4");

    /**
     * La dénomination de carte représentant le 5.
     */
    public static final Dénomination CINQ  = new Dénomination("cinq",  "5");

    /**
     * La dénomination de carte représentant le 6.
     */
    public static final Dénomination SIX   = new Dénomination("six",   "6");

    /**
     * La dénomination de carte représentant le 7.
     */
    public static final Dénomination SEPT = new Dénomination("sept", "7");

    /**
     * La dénomination de carte représentant le 8.
     */
    public static final Dénomination HUIT = new Dénomination("huit", "8");

    /**
     * La dénomination de carte représentant le 9.
     */
    public static final Dénomination NEUF  = new Dénomination("neuf",  "9");

    /**
     * La dénomination de carte représentant le 10.
     */
    public static final Dénomination DIX   = new Dénomination("dix",   "10");

    /**
     * La dénomination de carte représentant le valet.
     */
    public static final Dénomination VALET  = new Dénomination("valet",  "V");

    /**
     * La dénomination de carte représentant la dame.
     */
    public static final Dénomination DAME = new Dénomination("dame", "D");

    /**
     * La dénomination de carte représentant le roi.
     */
    public static final Dénomination ROI  = new Dénomination("roi",  "R");

    /**
     * La dénomination de carte représentant l'as.
     */
    public static final Dénomination AS   = new Dénomination("as",   "A");



    // Rang des dénominations

    /**
     * Une liste des dénominations en ordre croissant
     */
    public static final List<Dénomination> DÉNOMINATIONS = 
        Collections.unmodifiableList(Arrays.asList(new Dénomination[] {
            DEUX, TROIS, QUATRE, CINQ, SIX, SEPT, HUIT, NEUF, DIX, VALET, DAME, ROI, AS
        }));

    //
    // MÉTHODES
    //

    //-------------------------------------------
    /**
     * Crée une nouvelle dénomination
     *
     * @param nom  the name of this value
     * @param caractèreSurCarte     le symbole représentant cette carte
     *
     */
    private Dénomination(String nom, String caractèreSurCarte) {
		this.nom = nom;
        this.caractèreSurCarte = caractèreSurCarte;
    }


    //-------------------------------------------
    /**
     * Retourne le nom de cette dénomination
     * <p>
     * Par exemple "2", "3", ..., "10", "valet", "dame", "roi", ou "as".
     *
     * @return le nom de cette dénomination
     */
    public String getNom() {
        return nom;
    }


	//-------------------------------------------
    /**
     * Retourne le symbole représentant cette dénomination
     * <p>
     * Par exemple, "2", "3", ..., "10", "V", "D", "R", ou "A"
     * 
	 * @return le symbole représentant cette dénomination
	 */
    public String caractèreSurCarte() { 
        return caractèreSurCarte;
    }


    //-------------------------------------------
    /**
     * Compare cette dénomination à la dénomination spécifiée pour déterminer l'ordre
     *
     * @param o  l'objet qui sera comparé
     * @return   un entier négatif, zéro ou un entier positif, selon si
     *           cette dénomination est inférieure, égal ou supérieure à
     * 			 l'objet spécifié.
     * @exception ClassCastException si l'objet spécifié n'a pas le type Dénomination
     * @exception NullPointerException si l'objet spécifié est null
     *
     */
    public int compareTo(Object o) {        
        if (o == null) {
            throw new NullPointerException();
        }

        return DÉNOMINATIONS.indexOf(this) - DÉNOMINATIONS.indexOf((Dénomination)o);
    }
	

    //-------------------------------------------
    /**
     * Retourne une description de cette dénomination.
     *
     * @return une description de cette dénomination
     *
     */
    public String toString() { 
        return getNom(); 
    }

	//
	// VARIABLES MEMBRES
	//

	/**
	 * Le nom de cette dénomination
	 */
	private final String nom;

	/**
	 * Le symbole apparaissant sur la carte 
	 */
	private final String caractèreSurCarte;


}
