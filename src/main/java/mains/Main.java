/*
 * Created on Dec 10, 2004
 *
 */
package mains;

import java.util.Iterator;
import java.util.TreeSet;

import cartes.Carte;
/**
 * @author Cris
 * 
 * Main utilise un TreeSet afin d'avoir toujours un ensemble trié de cartes
 * C'est un exemple du patron "Delegation" documenté par Mark Grand, "Patterns
 * in Java" vol 1
 */
public class Main implements Comparable
{

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o)
	{
		Main autreMain = (Main) o;
		return this.rangPoker.compareTo(autreMain.getRangPoker());
	}

	/**
	 * @return vrai si la main est valide
	 */
	public boolean estValide()
	{
		return cartes.size() == 5;
	}
	
	/**
	 * @return
	 */
	public RangPoker getRangPoker()
	{
		/*
		 * Optimisation, mais pas tout à fait correct si on change la main après et on
		 * ne met pas rangPoker à null.
		 */
		if (rangPoker == null)
		{
			rangPoker = RangPoker.fabriqueRang(this);
		}
		
		return rangPoker;
	}

	private TreeSet cartes = new TreeSet();
	private RangPoker rangPoker;

	/**
	 * Ajouter une carte dans la main.
	 * @param carte
	 * @return true si la carte a été ajouté (n'y était pas déja)
	 */
	public boolean add(Carte carte)
	{
		rangPoker = null;
		return cartes.add(carte);
	}
	
	/**
	 * Enlever une carte de la main.
	 * @param carte
	 * @return true si la carte a été enlevé
	 */
	public boolean remove(Carte carte)
	{
		rangPoker = null;
		return cartes.remove(carte);
	}

	/**
	 * 
	 */
	public Iterator iterator()
	{
		return cartes.iterator();
	}

	/**
	 * @return
	 */
	public int size()
	{
		return cartes.size();
	}

	/**
	 * @return
	 */
	public Carte first()
	{
		return (Carte) cartes.first();
	}
	
}
