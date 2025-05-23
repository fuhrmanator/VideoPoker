/*
 * Created on Dec 16, 2004
 *
 */
package mains;

/**
 * Objet qui est l'équivalent d'une requête dans le patron chaîne de responsibilités
 * @author Cris
 *
 */
public class ReqAnalyseMain
{
	public ReqAnalyseMain(Main main)
	{
		if (!main.estValide())
		{
			throw new IllegalArgumentException();
		}
		this.main = main;
	}
	
	public void setRangReconnu(RangPoker rang)
	{
		rangReconnu = rang;
	}
	
	public RangPoker getRangReconnu()
	{
		return rangReconnu;
	}
	
	// main à reconnaître
	private Main main;
	
	private RangPoker rangReconnu;
	
	/**
	 * @return
	 */
	public Main getMain()
	{
		return main;
	}

}
