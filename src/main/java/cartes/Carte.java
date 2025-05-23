/*
 * Created on Dec 10, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package cartes;

/**
 * @author Cris
 *
 *
 */
public class Carte implements Comparable
{
	public static String[] sortes = { "Trèfle", "Carreau", "Pique", "Coeur" };
	public static String[] rangs =
		{
			"Deux",
			"Trois",
			"Quatre",
			"Cinq",
			"Six",
			"Sept",
			"Huit",
			"Neuf",
			"Dix",
			"Valet",
			"Dame",
			"Roi",
			"As",
			};

	public static int rangNumérique(String rangString)
	{

		for (int rangIndice = 0; rangIndice < rangs.length; rangIndice++)
		{
			if (rangs[rangIndice].equalsIgnoreCase(rangString))
			{
				return rangIndice + 1;
			}
		}

		throw new IllegalArgumentException("Le rang '" + rangString + "' pour une carte n'est pas légal");
		
	}

	public static int sorteNumérique(String sorteString)
	{
		for (int sorteIndice = 0; sorteIndice < rangs.length; sorteIndice++)
		{
			if (sortes[sorteIndice].equalsIgnoreCase(sorteString))
			{
				return sorteIndice + 1;
			}
		}

		throw new IllegalArgumentException("La sorte '" + sorteString + "' pour une carte n'est pas légale");
	}

	/**
	 * @param rang valeur de 0 &agrave; 12
	 * @param sorte valeur de 0 &agrave; 3
	 */
	public Carte(int rang, int sorte)
	{
		if (rang < 1 || rang > rangs.length)
		{
			throw new IllegalArgumentException("Le rang pour une carte doit être entre 1 et " + rangs.length);
		}
		else 
		if (sorte < 1 || sorte > sortes.length)
		{
			throw new IllegalArgumentException("La sorte pour une carte doit être entre 1 et " + sortes.length);
		}

		this.rang = rang;
		this.sorte = sorte;
	}

	/**
	 * Constructeur pour une carte avec des noms de rang et de sorte.
	 * 
	 * @param rangString le rang de la carte. Les noms de rang légaux sont "deux", "trois", ..., "dix", "valet", "dame", "roi", "as"
	 * @param sorteString la sorte de la carte. Les noms de sorte légaux sont "trèfle", "carreau", "pique", "coeur"
	 */
	public Carte(String rangString, String sorteString)
	{
		this.rang = rangNumérique(rangString);
		this.sorte = sorteNumérique(sorteString);		
	}

	/**
	 * @return
	 */
	public int getValeur()
	{
		return this.rang * sortes.length + sorte;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o)
	{
		// trier de la carte la plus haute vers le bas
		return
			- new Integer(this.getValeur()).compareTo(
				new Integer(((Carte) o).getValeur()));
	}

	/**
	 * @return
	 */
	public int getRang()
	{
		return rang;
	}
	
	/**
	 * @return
	 */
	public int getSorte()
	{
		return sorte;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return rangs[this.rang - 1] + " de " + sortes[this.sorte - 1];
	}
	
	private int sorte;    // p.ex. coeur, pique, etc.
	private int rang;     // p.ex. 2, 3, 4, ..., Dame, Roi, As

	
}
