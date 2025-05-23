/*
 * Created on Dec 10, 2004
 *
 */
package mains;

import java.util.ArrayList;
import java.util.Collection;

import cartes.Carte;

/**
 * @author Cris
 *
 */
public class CarteIsolée extends AbstractAnalyseurRang
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
	public boolean reconnaîtreMain(ReqAnalyseMain demande)
	{
		boolean résultat = false;
		Main main = demande.getMain();
		System.out.println("CarteIsolée: évalueMain pour " + main);
		// créer une le nouveau rang, avec une carte du brelan comme déterminante
		Collection<Carte> carteIsolée = new ArrayList<Carte>();
		carteIsolée.add(main.first());
		demande.setRangReconnu(
			new RangPoker(RangPoker.RANG_CARTE_ISOLEE, carteIsolée));
		résultat = true;
		return résultat;
	}

}