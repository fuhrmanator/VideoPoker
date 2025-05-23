/*
 * Created on Dec 10, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package cartes;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Cris
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Jeu extends ArrayList<Carte>
{

	public Jeu()
	{
		/*
		 * iterate through the suits, in their defined order
		 */
		for (CouleurCarte suit : CouleurCarte.COULEURS) {
			/*
			 * iterate through the ranks, in their defined order
			 */
			for (Dénomination rank : Dénomination.DÉNOMINATIONS) {			
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
