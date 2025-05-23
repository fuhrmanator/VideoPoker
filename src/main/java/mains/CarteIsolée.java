/*
 * Created on Dec 10, 2004
 *
 */
package mains;

import cartes.Carte;

/**
 * @author Cris
 *
 */
public class CarteIsolée extends AbstractConnaîsseurRang
{

	//	/**
	//	 * @param main
	//	 */
	//	public CarteIsolée(Main main)
	//	{
	//		super(main);
	//		this.valeurMajeure = RangPoker.RANG_CARTE_ISOLEE;
	//		// première carte dans la main (triée) est la plus haute
	//		Carte carteHaute = (Carte)main.iterator().next();
	//		this.valeurMineure = carteHaute.getValeur();
	//	}
	//
	public boolean reconnaîtreMain(DemandeRecMain demande)
	{
		boolean résultat = false;
		Main main = demande.getMain();
		System.out.println("CarteIsolée: évalueMain pour " + main);
		Carte carte = (Carte) main.first();
		résultat = true;
		demande.setRangReconnu(
			new RangPoker(RangPoker.RANG_CARTE_ISOLEE, carte.getRang()));
		return résultat;
	}

}