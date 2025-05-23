import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/*
 * Created on Dec 10, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

/**
 * @author Cris
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Values
{
	static String[] suits = { "Clubs", "Diamonds", "Spades", "Hearts" };
	static String[] ranks =
		{
			"Two",
			"Three",
			"Four",
			"Five",
			"Six",
			"Seven",
			"Eight",
			"Nine",
			"Ten",
			"Jack",
			"Queen",
			"King",
			"Ace",
			};

	public static void main(String[] args)
	{
		for(int i = 0; i < 100; i++)
		{
			new Values().dealHandsAndCheck();
		}
	}

	private void dealHandsAndCheck()
	{
		Collection deck = new TreeSet();
		// generate a deck of cards
		for (int suit = 0; suit < suits.length; suit++)
		{
			for (int rank = 0; rank < ranks.length; rank++)
			{
				deck.add(new Card(rank, suit));
			}
		}

//		for (Iterator iter = deck.iterator(); iter.hasNext();)
//		{
//			Card card = (Card) iter.next();
//			System.out.println(card);
//		}

		List shuffledDeck = new ArrayList(deck);
		Collections.shuffle(shuffledDeck);

//		for (Iterator iter = shuffledDeck.iterator(); iter.hasNext();)
//		{
//			Card card = (Card) iter.next();
//			System.out.println(card);
//		}

		int nHands = 2;
		Hand[] hands = new Hand[nHands];
		
		System.out.println("Dealing " + nHands + " hands:");
		
		Iterator dealer = shuffledDeck.iterator();
		for (int c = 0; c < 5 && dealer.hasNext(); c++)
		{
			for (int hand = 0; hand < nHands; hand++)
			{
				if (hands[hand] == null) 
				{
					hands[hand] = new Hand();
				}
				
				if (dealer.hasNext())
					hands[hand].add((Card) dealer.next()); 
			}
		}
		
		
		for (int hand = 0; hand < nHands; hand++)
		{
			Collections.sort(hands[hand]);
			System.out.println(hands[hand]);
			if (hands[hand].isPair())
			{
				System.out.println("Hand has a pair");
			} else
				if (hands[hand].isTwoPair())
				{
					System.out.println("Hand has 2 pair");
				}
		}
	}

	private static class Card implements Comparable
	{

		Card(int rank, int suit)
		{
			this.rank = rank;
			this.suit = suit;
		}

		public String toString()
		{
			return ranks[this.rank] + " of " + suits[this.suit];
		}

		private int suit;
		private int rank;
		/* (non-Javadoc)
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		public int compareTo(Object o)
		{
			// make it the negative compareTo() value so we sort by highest card to lowest
			return - new Integer(this.value()).compareTo(
				new Integer(((Card) o).value()));
		}

		private int value()
		{
			return rank * suits.length + suit;
		}

	}
	
	private static class Hand extends ArrayList implements Comparable
	{			
		private int value()
		{
			return 0;
		}

		/*
		 * Assumes that hand is always sorted
		 */
		boolean isPair()
		{
			int value = -1;
			int matches = 0;
			for (Iterator cardIter = this.iterator(); cardIter.hasNext();)
			{
				Card card = (Card) cardIter.next();
				if (card.rank != value)
				{
					value = card.rank;
				}
				else /* found a matching card */
				{
					matches ++;
				}
			}
			return matches == 1;
		}

		/*
		 * Assumes that hand is always sorted
		 */
		boolean isTwoPair()
		{
			int value = -1;
			int matches = 0;
			int pairs = 0;
			for (Iterator cardIter = this.iterator(); cardIter.hasNext();)
			{
				Card card = (Card) cardIter.next();
				if (card.rank != value)
				{
					value = card.rank;
					if (matches == 1)
					{
						pairs++;
						matches = 0;
					}
				} else /* found a matching card */ {
					matches++;
				}
			}
			return pairs == 2;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		public int compareTo(Object o)
		{
			// TODO Auto-generated method stub
			return 0;
		}
	}
}
