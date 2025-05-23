/*
 * Created on Jun 12, 2005
 *
 */
package cartes;

import java.util.Comparator;

/**
 * Classe pour inverser l'ordre de trie de nos cartes.
 * Normalement elles seront triée en ordre croissante (naturelle).
 *
 * @author Cris Fuhrman
 *
 */
public class ComparateurCartesInversé implements Comparator
{

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object arg0, Object arg1)
	{
		// inverser l'ordre des cartes dans une liste (plutôt que arg0.compareTo(arg1))
		return ((Carte) arg1).compareTo((Carte) arg0);
	}

}
