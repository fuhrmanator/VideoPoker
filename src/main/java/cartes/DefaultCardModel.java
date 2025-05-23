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
	public DefaultCardModel(int rank, int suit)
	{
		validate(rank, suit);
		this.rank = rank;
		this.suit = suit;
		this.visible = true;
	}

	/**
	 * @param rank
	 * @param suit
	 */
	private void validate(int rank, int suit)
	{
		if (suit != CardUtils.SUIT_DIAMONDS
				&& suit != CardUtils.SUIT_CLUBS
				&& suit != CardUtils.SUIT_HEARTS
				&& suit != CardUtils.SUIT_SPADES)
				{
					throw new IllegalArgumentException("Illegal suit value: " + suit);
				}

		if(
			rank != CardUtils.RANK_ACE
				&& rank != CardUtils.RANK_2
				&& rank != CardUtils.RANK_3
				&& rank != CardUtils.RANK_4
				&& rank != CardUtils.RANK_5
				&& rank != CardUtils.RANK_6
				&& rank != CardUtils.RANK_7
				&& rank != CardUtils.RANK_8
				&& rank != CardUtils.RANK_9
				&& rank != CardUtils.RANK_10
				&& rank != CardUtils.RANK_JACK
				&& rank != CardUtils.RANK_QUEEN
				&& rank != CardUtils.RANK_KING)
		{
			throw new IllegalArgumentException("Illegal rank value: " + rank);
		}
	}

	public void update(int rank, int suit)
	{
		validate(rank, suit);
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
	public int getRank()
	{
		return rank;
	}

	/**
	 * @return
	 */
	public int getSuit()
	{
		return suit;
	}

	/**
	 * @param i
	 */
	public void setRank(int i)
	{
		rank = i;
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * @param i
	 */
	public void setSuit(int i)
	{
		suit = i;
		this.setChanged();
		this.notifyObservers();
	}

	private int rank;
	private int suit;
	private boolean visible;

}
