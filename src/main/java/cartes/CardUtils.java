/******************************************************
 Nom du fichier :    SuitAreas.java
 Date crée :         Jun 25, 2004
 Date dern. modif.   aaaa-mm-jj
*******************************************************
 Historique des modifications
*******************************************************
  Jun 25, 2004         Version initiale
*******************************************************/
package cartes;

import java.awt.Image;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;

/**
 * @author Cris
 *
 */
public class CardUtils
{
	public static final Image KING_H_IMAGE = createImage("face-kh.gif", "");
	public static final Image KING_D_IMAGE = createImage("face-kd.gif", "");
	public static final Image KING_S_IMAGE = createImage("face-ks.gif", "");
	public static final Image KING_C_IMAGE = createImage("face-kc.gif", "");

	public static final Image QUEEN_H_IMAGE = createImage("face-qh.gif", "");
	public static final Image QUEEN_D_IMAGE = createImage("face-qd.gif", "");
	public static final Image QUEEN_S_IMAGE = createImage("face-qs.gif", "");
	public static final Image QUEEN_C_IMAGE = createImage("face-qc.gif", "");

	public static final Image JACK_H_IMAGE = createImage("face-jh.gif", "");
	public static final Image JACK_D_IMAGE = createImage("face-jd.gif", "");
	public static final Image JACK_S_IMAGE = createImage("face-js.gif", "");
	public static final Image JACK_C_IMAGE = createImage("face-jc.gif", "");

    public static final Area 
		DIAMOND_AREA = generateDiamondArea(),
		CLUB_AREA = generateClubArea(),
		HEART_AREA = generateHeartArea(),
    	SPADE_AREA = generateSpadeArea();

	/**
	 * Creates a Spade-shaped Area, based on Constructive area geometry (CAG)
	 * @return
	 */
	public static final Area generateSpadeArea()
	{
		Ellipse2D.Double circle = new Ellipse2D.Double(), circle2 = new Ellipse2D.Double();
		Rectangle2D.Double bbox = new Rectangle2D.Double();
		Area spade;
		
		// outer roundish parts
		circle.setFrame(69 - 14, 58, 58, 58);
		spade = new Area(circle);
		circle2.setFrame(19 - 14, 58, 58, 58);
		spade.add(new Area(circle2));

		// peak (point) top of spade
		bbox.setFrame(29 - 14, 5, 87, 70);
		circle.setFrame(69 - 14, -116, 194, 194);
		circle2.setFrame(-117 - 14, -116, 194, 194);
		Area pointArea = new Area(bbox);
		pointArea.subtract(new Area(circle));
		pointArea.subtract(new Area(circle2));
		spade.add(pointArea);

		// stem at bottom
		RoundRectangle2D.Double rrect = new RoundRectangle2D.Double(73 - 14, 25, 150, 117, 90, 90);
		RoundRectangle2D.Double rrect2 = new RoundRectangle2D.Double(-77 - 14, 25, 150, 117, 90, 90);
		bbox.setFrame(27 - 14, 92, 92, 50);
		Area stemArea = new Area(bbox);
		stemArea.subtract(new Area(rrect));
		stemArea.subtract(new Area(rrect2));
		spade.add(stemArea);
				
		return spade;
	}

	/**
	 * Creates a Club-shaped Area, based on Constructive area geometry (CAG)
	 * @return
	 */
	public static final Area generateClubArea()
	{
		Ellipse2D.Double circle = new Ellipse2D.Double(), circle2 = new Ellipse2D.Double();
		Rectangle2D.Double bbox = new Rectangle2D.Double();
		Area club;
		
		// outer roundish parts
		circle.setFrame(69 - 14, 58, 58, 58);
		club = new Area(circle);
		circle2.setFrame(19 - 14, 58, 58, 58);
		club.add(new Area(circle2));

		// top of club
		circle.setFrame(29, 15, 58, 58);
		club.add(new Area(circle));

		// stem at bottom
		RoundRectangle2D.Double rrect = new RoundRectangle2D.Double(73 - 14, 25, 150, 117, 90, 90);
		RoundRectangle2D.Double rrect2 = new RoundRectangle2D.Double(-77 - 14, 25, 150, 117, 90, 90);
		bbox.setFrame(27 - 14, 92, 92, 50);
		Area stemArea = new Area(bbox);
		stemArea.subtract(new Area(rrect));
		stemArea.subtract(new Area(rrect2));
		club.add(stemArea);
				
		return club;
	}

	/**
	 * Creates a Heart-shaped Area, based on Constructive area geometry (CAG)
	 * @return
	 */
	public static final Area generateHeartArea()
	{
		Ellipse2D.Double circle = new Ellipse2D.Double(), circle2 = new Ellipse2D.Double();
		Rectangle2D.Double bbox = new Rectangle2D.Double();
		Area heart;
		
		// outer roundish parts
		circle.setFrame(55, 8, 58, 67);
		heart = new Area(circle);
		circle2.setFrame(5, 8, 58, 67);
		heart.add(new Area(circle2));

		// point of heart
		bbox.setFrame(15, 58, 87, 84);
		circle.setFrame(55, 49.5, 195, 254);
		circle2.setFrame(-131.4, 49.5, 195, 254);
		Area pointArea = new Area(bbox);
		pointArea.subtract(new Area(circle));
		pointArea.subtract(new Area(circle2));
		heart.add(pointArea);
	
		return heart;
	}

	/**
	 * Creates a Diamond-shaped Area, based on Constructive area geometry (CAG)
	 * @return
	 */
	public static final Area generateDiamondArea()
	{
		Ellipse2D.Double circle = new Ellipse2D.Double();
		Rectangle2D.Double bbox = new Rectangle2D.Double();
		Area diamond;
		
		// 4 very large circles chip off the corners of a rectangle to form a slightly concaved diamond
		bbox.setFrame(15, 9, 87, 132);
		diamond = new Area(bbox);
		circle.setFrame(-574, -469, 667, 667);
		diamond.subtract(new Area(circle));
		circle.setFrame(24, -469, 667, 667);
		diamond.subtract(new Area(circle));
		circle.setFrame(24, -44, 667, 667);
		diamond.subtract(new Area(circle));
		circle.setFrame(-574, -44, 667, 667);
		diamond.subtract(new Area(circle));
	
		return diamond;
	}
	
	/** Returns an Image, or null if the path was invalid. */
	protected static Image createImage(String path,
											   String description) {
		path = "/images/" + path;
		java.net.URL imgURL = CardUtils.class.getResource(path);
		if (imgURL != null) {
			ImageIcon imageIcon = new ImageIcon(imgURL, description);
			return imageIcon.getImage();
		} else {
			System.err.println("Couldn't find image file: " + path);
			return null;
		}
	}

}
