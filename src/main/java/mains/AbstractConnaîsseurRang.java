/*
 * Created on Dec 16, 2004
 *
 */
package mains;

/**
 * @author Cris
 *
 */
public abstract class AbstractConnaîsseurRang
{
	/**
	 * @param connaîsseur
	 */
	public void setSuivant(AbstractConnaîsseurRang connaîsseur)
	{
		suivant = connaîsseur;
	}

	/**
	 * @return
	 */
	public AbstractConnaîsseurRang getSuivant()
	{
		return suivant;
	}

	/**
	 * Essayer de traiter la demande, sinon la passer au prochain connaisseur dans la chaîne
	 * @param demande
	 */
	public void traiterDemande(DemandeRecMain demande)
	{
		System.out.println(this.getClass().getName() + ": traiterDemande():");
		if (!reconnaîtreMain(demande))
		{
			System.out.println(this.getClass().getName() + ": main n'est pas reconnue.");
			suivant.traiterDemande(demande);
		}
		else
		{
			System.out.println(this.getClass().getName() + ": main est reconnue!");
		}
	}
	
	/**
	 * Méthode qui reconnaît (ou non) un rang de Poker étant donné une main. 
	 * @param main
	 * @return true si la main a été reconnue
	 */
	public abstract boolean reconnaîtreMain(DemandeRecMain demande);

	private AbstractConnaîsseurRang suivant;

}
