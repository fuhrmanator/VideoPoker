/*
 * Created on Dec 11, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package cartes;

import java.util.Observable;

/**
 * @author Cris
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DefaultCardModel extends Observable
{
	public DefaultCardModel(Dénomination rank, CouleurCarte suit)
	{
		this.rank = rank;
		this.suit = suit;
		this.visible = true;
	}

//	/**
//	 * @param rank
//	 * @param suit
//	 */
//	private void validate(int rank, int suit)
//	{
//		if (suit != CardUtils.SUIT_DIAMONDS
//				&& suit != CardUtils.SUIT_CLUBS
//				&& suit != CardUtils.SUIT_HEARTS
//				&& suit != CardUtils.SUIT_SPADES)
//				{
//					throw new IllegalArgumentException("Illegal suit value: " + suit);
//				}
//
//		if(
//			rank != CardUtils.RANK_ACE
//				&& rank != CardUtils.RANK_2
//				&& rank != CardUtils.RANK_3
//				&& rank != CardUtils.RANK_4
//				&& rank != CardUtils.RANK_5
//				&& rank != CardUtils.RANK_6
//				&& rank != CardUtils.RANK_7
//				&& rank != CardUtils.RANK_8
//				&& rank != CardUtils.RANK_9
//				&& rank != CardUtils.RANK_10
//				&& rank != CardUtils.RANK_JACK
//				&& rank != CardUtils.RANK_QUEEN
//				&& rank != CardUtils.RANK_KING)
//		{
//			throw new IllegalArgumentException("Illegal rank value: " + rank);
//		}
//	}

	public void update(Dénomination rank, CouleurCarte suit)
	{
		this.rank = rank;
		this.suit = suit;
		this.setChanged();
		this.notifyObservers();
	}
	
	public void setVisible(boolean visible)
	{
		this.visible = visible;
		this.setChanged();
		this.notifyObservers();
	}
	
	public boolean isVisible()
	{
		return visible;
	}
	
	/**
	 * @return
	 */
	public Dénomination getRank()
	{
		return rank;
	}

	/**
	 * @return
	 */
	public CouleurCarte getSuit()
	{
		return suit;
	}

	/**
	 * @param i
	 */
	public void setRank(Dénomination i)
	{
		rank = i;
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * @param i
	 */
	public void setSuit(CouleurCarte i)
	{
		suit = i;
		this.setChanged();
		this.notifyObservers();
	}

	private Dénomination rank;
	private CouleurCarte suit;
	private boolean visible;

}
