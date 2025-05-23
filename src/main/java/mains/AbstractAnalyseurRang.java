/*
 * Created on Dec 16, 2004
 *
 */
package mains;

/**
 * Classe abstraite représentant l'élément dans une chaîne de responsabilité 
 * (selon le patron "Chain of responsibility" des GoF).
 * Les instances sous-classes de cet classe sont placées dans une chaîne, par
 * ordre d'importance décroissante, afin que le rang de Poker le plus fort soit
 * reconnu en premier.
 * <p>
 * La requête, selon le patron de conception, est dans un objet du type ReqAnalyseMain
 *  
 * @author Cris Fuhrman (École de technologie supérieure)
 * @see DemandeRecMain
 *
 */
public abstract class AbstractAnalyseurRang
{
	/**
	 * @param analyseur
	 */
	public void setSuivant(AbstractAnalyseurRang analyseur)
	{
		suivant = analyseur;
	}

	/**
	 * @return
	 */
	public AbstractAnalyseurRang getSuivant()
	{
		return suivant;
	}

	/**
	 * Essayer de traiter la demande, sinon la passer au prochain connaisseur dans la chaîne
	 * @param demande
	 */
	public void traiterDemande(ReqAnalyseMain demande)
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
	public abstract boolean reconnaîtreMain(ReqAnalyseMain demande);

	private AbstractAnalyseurRang suivant;

}
