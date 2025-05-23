/*
 * Created on Dec 10, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package cartes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
/**
 * @author Cris
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Jeu extends ArrayList
{

	public Jeu()
	{
		/*
		 * iterate through the suits, in their defined order
		 */
		Collection suits = CardUtils.getSuits();
		for (Iterator suitIter = suits.iterator(); suitIter.hasNext();)
		{
			int suit = ((Integer) suitIter.next()).intValue();

			/*
			 * iterate through the ranks, in their defined order
			 */
			Collection ranks = CardUtils.getRanks();
			for (Iterator rankIter = ranks.iterator(); rankIter.hasNext();)
			{
				int rank = ((Integer) rankIter.next()).intValue();
				this.add(new Carte(rank, suit));
			}
		}
		// System.out.println(this);
		
	}

	public void brasser()
	{
		Collections.shuffle(this);
	}
}
