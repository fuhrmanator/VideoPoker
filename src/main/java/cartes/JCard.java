/******************************************************
 Nom du fichier :    JCard.java
 Date crée :         Jun 25, 2004
 Date dern. modif.   aaaa-mm-jj
*******************************************************
 Historique des modifications
*******************************************************
  Jun 25, 2004         Version initiale
*******************************************************/

package cartes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Cris Fuhrman
 *
 */
public class JCard extends JPanel implements Observer, MouseListener
{
	private boolean pressed;
	private boolean inside;
	protected static int CARD_WIDTH = 50, CARD_HEIGHT = 70;
	private static double SYMBOL_SCALE_FACTOR_X = 0.1,
		SYMBOL_SCALE_FACTOR_Y = 0.1;

	private DefaultCardModel model;
	private Area suitSymbol;
	private String rankSymbol;
	private Image image;
	private JLabel imageLabel; // = new JLabel();
	private String label;
	private Color color;

	private List actionListeners = new ArrayList();

	private Dimension preferredSize;

	/**
	 * Creates a JCard object with suit and rank, according to the values
	 * specified in the @see CardUtils class. 
	 * @param suit
	 * @param rank
	 */
	public JCard(DefaultCardModel model)
	{
		this.model = model;
		model.addObserver(this);
		this.addMouseListener(this);

		/*
		 * Some elements of JPanel will be custom-drawn in paintComponent
		 */
		this.setBorder(BorderFactory.createRaisedBevelBorder());
		this.setLayout(new BorderLayout());

		updateDerivedValues();

	}

	public JCard(DefaultCardModel model, String label)
	{
		this(model);
		this.label = label;
	}

	private void updateDerivedValues()
	{
		this.image = null;
		if (this.imageLabel != null)
		{
			this.imageLabel.setIcon(null);
			this.remove(this.imageLabel);
			this.imageLabel = null;
		}

		switch (model.getSuit())
		{
			case CardUtils.SUIT_DIAMONDS :
				suitSymbol = CardUtils.DIAMOND_AREA;
				color = Color.RED;
				break;

			case CardUtils.SUIT_CLUBS :
				suitSymbol = CardUtils.CLUB_AREA;
				color = Color.BLACK;
				break;

			case CardUtils.SUIT_HEARTS :
				suitSymbol = CardUtils.HEART_AREA;
				color = Color.RED;
				break;

			case CardUtils.SUIT_SPADES :
				suitSymbol = CardUtils.SPADE_AREA;
				color = Color.BLACK;
				break;
		}

		switch (model.getRank())
		{
			case CardUtils.RANK_2 :
				rankSymbol = "2";
				break;
			case CardUtils.RANK_3 :
				rankSymbol = "3";
				break;
			case CardUtils.RANK_4 :
				rankSymbol = "4";
				break;
			case CardUtils.RANK_5 :
				rankSymbol = "5";
				break;
			case CardUtils.RANK_6 :
				rankSymbol = "6";
				break;
			case CardUtils.RANK_7 :
				rankSymbol = "7";
				break;
			case CardUtils.RANK_8 :
				rankSymbol = "8";
				break;
			case CardUtils.RANK_9 :
				rankSymbol = "9";
				break;
			case CardUtils.RANK_10 :
				rankSymbol = "10";
				break;

			case CardUtils.RANK_ACE :
				rankSymbol = "A";
				break;

			case CardUtils.RANK_KING :
				rankSymbol = "K";
				switch (model.getSuit())
				{
					case CardUtils.SUIT_DIAMONDS :
						image = CardUtils.KING_D_IMAGE;
						break;

					case CardUtils.SUIT_CLUBS :
						image = CardUtils.KING_C_IMAGE;
						break;

					case CardUtils.SUIT_HEARTS :
						image = CardUtils.KING_H_IMAGE;
						break;

					case CardUtils.SUIT_SPADES :
						image = CardUtils.KING_S_IMAGE;
						break;
				}
				break;

			case CardUtils.RANK_QUEEN :
				rankSymbol = "Q";
				switch (model.getSuit())
				{
					case CardUtils.SUIT_DIAMONDS :
						image = CardUtils.QUEEN_D_IMAGE;
						break;

					case CardUtils.SUIT_CLUBS :
						image = CardUtils.QUEEN_C_IMAGE;
						break;

					case CardUtils.SUIT_HEARTS :
						image = CardUtils.QUEEN_H_IMAGE;
						break;

					case CardUtils.SUIT_SPADES :
						image = CardUtils.QUEEN_S_IMAGE;
						break;
				}
				break;

			case CardUtils.RANK_JACK :
				rankSymbol = "J";
				switch (model.getSuit())
				{
					case CardUtils.SUIT_DIAMONDS :
						image = CardUtils.JACK_D_IMAGE;
						break;

					case CardUtils.SUIT_CLUBS :
						image = CardUtils.JACK_C_IMAGE;
						break;

					case CardUtils.SUIT_HEARTS :
						image = CardUtils.JACK_H_IMAGE;
						break;

					case CardUtils.SUIT_SPADES :
						image = CardUtils.JACK_S_IMAGE;
						break;
				}
				break;

		}
		/*
		 * In case of an image, we will have two copies of it, one flipped
		 * vertically, and the two will be centered in the card inside a
		 * JLabel (as a new ImageIcon with both images in it)
		 */
		if (this.image != null)
		{
			int w = this.image.getWidth(this);
			int h = this.image.getHeight(this);
			/*
			 * Draw the first image, plus the second image, flipped vertically into a new bufferedImage
			 */
			BufferedImage bi =
				new BufferedImage(w, h * 2, BufferedImage.TYPE_INT_ARGB_PRE);
			Graphics2D biContext = bi.createGraphics();
			biContext.drawImage(this.image, 0, 0, null);

			// shift down the height of the image
			biContext.translate(0, h);
			// rotate around the opposite corner
			AffineTransform flipVertically =
				AffineTransform.getRotateInstance(Math.PI);
			AffineTransform toCenterAt = new AffineTransform();
			toCenterAt.concatenate(flipVertically);
			toCenterAt.translate(-w, -h);
			biContext.transform(toCenterAt);
			biContext.drawImage(this.image, 0, 0, null);

			// have to do and undo this due to Swing quirkiness
			this.imageLabel = new JLabel(new ImageIcon(bi));
			this.imageLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
			this.imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.add(this.imageLabel, BorderLayout.CENTER);
		}
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 * @see http://www.brainjar.com/css/cards/default2.asp
	 */
	protected void paintComponent(Graphics g)
	{
		/*
		 * Image label will get displayed in superclass' paintComponent
		 */
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(
			RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);

		if (model.isVisible())
		{
			drawCard(g2);
		} else
		{
			//    		/*
			//    		 * Do a textured fill, as per the Java Tutorial
			//    		 * http://java.sun.com/docs/books/tutorial/2d/display/strokeandfill.html
			//    		 */
			//    		int tempWidth = 7;
			//			BufferedImage bi =
			//				new BufferedImage(tempWidth, tempWidth, BufferedImage.TYPE_INT_RGB);
			//			Graphics2D big = bi.createGraphics();
			//			big.setColor(Color.blue);
			//			big.fillRect(0, 0, tempWidth, tempWidth);
			//			big.setColor(Color.lightGray);
			//			big.fillOval(0, 0, tempWidth-1, tempWidth-1);
			//			Rectangle r = new Rectangle(0, 0, tempWidth, tempWidth);
			//			g2.setPaint(new TexturePaint(bi, r));
			/*
			 * Do a gradient fill, as per the Java Tutorial
			 */
			GradientPaint gradient =
				new GradientPaint(
					0,
					0,
					Color.BLUE,
					this.getWidth(),
					this.getHeight(),
					Color.WHITE);
			g2.setPaint(gradient);
			g2.fillRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
		}
	}

	/**
	 * @param g
	 */
	private void drawCard(Graphics2D g2)
	{

		/*
		 * Clear the background as white, unless the card is "pressed"
		 */
		g2.setColor(pressed && inside ? Color.GRAY : Color.WHITE);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());

		g2.setColor(this.color);

		int height = getHeight();
		int width = getWidth();
		Insets insets = getInsets();

		// optimization?
		int tempRank = model.getRank();
		// int tempSuit = model.getSuit();

		AffineTransform saveXform = g2.getTransform();
		//		  AffineTransform at = new AffineTransform();

		// generate the suit symbols scaled to the size of the card
		// special case for ACE -- gets a big symbol
		double scaleFactor = 12.0d;
		if (tempRank == CardUtils.RANK_ACE)
		{
			scaleFactor = 6.0d;
		}

		Area scaledSuitSymbol =
			this.suitSymbol.createTransformedArea(
				AffineTransform.getScaleInstance(
					(width / (double) CARD_WIDTH) / scaleFactor,
					(width / (double) CARD_WIDTH) / scaleFactor));

		double smallScaleFactor = (width / (double) CARD_WIDTH) / 20.0;
		Area scaledSuitSymbolSmall =
			suitSymbol.createTransformedArea(
				AffineTransform.getScaleInstance(
					smallScaleFactor,
					smallScaleFactor));

		// draw the rank symbol at a scaled font size proportional to card
		double xOffset = insets.left + (int) (width / 10.0);
		Font font =
			new Font(
				"Arial",
				Font.PLAIN,
				(int) (10 * width / (double) CARD_WIDTH));
		g2.setFont(font);
		//		Measure the font and the message
		FontRenderContext frc = g2.getFontRenderContext();
		Rectangle2D bounds = font.getStringBounds(this.rankSymbol, frc);

		g2.drawString(
			this.rankSymbol,
			(float) (xOffset - bounds.getWidth() / 2f),
			(float) insets.top + g2.getFontMetrics().getAscent());
		// draw the small suit symbol just below it
		g2.translate(
			xOffset - scaledSuitSymbolSmall.getBounds().getWidth() / 2.0,
			insets.top + g2.getFontMetrics().getHeight());
		g2.fill(scaledSuitSymbolSmall);

		double halfSymbolHeight =
			scaledSuitSymbol.getBounds2D().getHeight() / 2.0;

		/*
		 * First column of symbols (A1-A5)
		 */
		xOffset = width / 4 - scaledSuitSymbol.getBounds2D().getWidth() / 2.0;

		// important to reset this!
		g2.setTransform(saveXform);

		// spot A1
		if (tempRank >= CardUtils.RANK_4 && tempRank <= CardUtils.RANK_10)
		{
			g2.translate(xOffset, height / 5.0 - halfSymbolHeight);
			g2.fill(scaledSuitSymbol);
			// important to reset this!
			g2.setTransform(saveXform);
		}

		// spot A2
		if (tempRank == CardUtils.RANK_9 || tempRank == CardUtils.RANK_10)
		{
			g2.translate(xOffset, 2 * height / 5.0 - halfSymbolHeight);
			g2.fill(scaledSuitSymbol);

			// important to reset this!
			g2.setTransform(saveXform);
		}

		// spot A3
		if (tempRank >= CardUtils.RANK_6 && tempRank <= CardUtils.RANK_8)
		{
			g2.translate(xOffset, height / 2.0 - halfSymbolHeight);
			g2.fill(scaledSuitSymbol);
			// important to reset this!
			g2.setTransform(saveXform);
		}

		// spot A4
		if (tempRank == CardUtils.RANK_9 || tempRank == CardUtils.RANK_10)
		{
			g2.translate(xOffset, 3 * height / 5.0 - halfSymbolHeight);
			g2.fill(scaledSuitSymbol);
			// important to reset this!
			g2.setTransform(saveXform);
		}

		// spot A5
		if (tempRank >= CardUtils.RANK_4 && tempRank <= CardUtils.RANK_10)
		{
			g2.translate(xOffset, 4 * height / 5.0 - halfSymbolHeight);
			g2.fill(scaledSuitSymbol);
			// important to reset this!
			g2.setTransform(saveXform);
		}

		/*
		 * Second column of symbols (B1-B5)
		 */
		xOffset = width / 2 - scaledSuitSymbol.getBounds2D().getWidth() / 2.0;

		// important to reset this!
		g2.setTransform(saveXform);

		// spot B1
		if (tempRank == CardUtils.RANK_2 || tempRank == CardUtils.RANK_3)
		{
			g2.translate(xOffset, height / 5.0 - halfSymbolHeight);
			g2.fill(scaledSuitSymbol);
			// important to reset this!
			g2.setTransform(saveXform);
		}

		// spot B2
		if (tempRank == CardUtils.RANK_7
			|| tempRank == CardUtils.RANK_8
			|| tempRank == CardUtils.RANK_10)
		{
			g2.translate(
				xOffset,
				(height / 5)
					+ (height / 2 - height / 5) / 2
					- halfSymbolHeight);
			g2.fill(scaledSuitSymbol);
			// important to reset this!
			g2.setTransform(saveXform);
		}

		// spot B3
		if (tempRank == CardUtils.RANK_ACE
			|| tempRank == CardUtils.RANK_3
			|| tempRank == CardUtils.RANK_5
			|| tempRank == CardUtils.RANK_9)
		{
			g2.translate(xOffset, height / 2.0 - halfSymbolHeight);
			g2.fill(scaledSuitSymbol);
			// important to reset this!
			g2.setTransform(saveXform);
		}

		// spot B4
		if (tempRank == CardUtils.RANK_8 || tempRank == CardUtils.RANK_10)
		{
			g2.translate(
				xOffset,
				(height / 2)
					+ (height / 2 - height / 5) / 2
					- halfSymbolHeight);
			g2.fill(scaledSuitSymbol);
			// important to reset this!
			g2.setTransform(saveXform);
		}

		// spot B5
		if (tempRank == CardUtils.RANK_2 || tempRank == CardUtils.RANK_3)
		{
			g2.translate(xOffset, 4 * height / 5.0 - halfSymbolHeight);
			g2.fill(scaledSuitSymbol);
			// important to reset this!
			g2.setTransform(saveXform);
		}

		/*
		 * Third column of symbols (C1-C5)
		 */
		xOffset =
			3 * width / 4 - scaledSuitSymbol.getBounds2D().getWidth() / 2.0;

		// important to reset this!
		g2.setTransform(saveXform);

		// spot C1
		if (tempRank >= CardUtils.RANK_4 && tempRank <= CardUtils.RANK_10)
		{
			g2.translate(xOffset, height / 5.0 - halfSymbolHeight);
			g2.fill(scaledSuitSymbol);
			// important to reset this!
			g2.setTransform(saveXform);
		}

		// spot C2
		if (tempRank == CardUtils.RANK_9 || tempRank == CardUtils.RANK_10)
		{
			g2.translate(xOffset, 2 * height / 5.0 - halfSymbolHeight);
			g2.fill(scaledSuitSymbol);

			// important to reset this!
			g2.setTransform(saveXform);
		}

		// spot C3
		if (tempRank >= CardUtils.RANK_6 && tempRank <= CardUtils.RANK_8)
		{
			g2.translate(xOffset, height / 2.0 - halfSymbolHeight);
			g2.fill(scaledSuitSymbol);
			// important to reset this!
			g2.setTransform(saveXform);
		}

		// spot C4
		if (tempRank == CardUtils.RANK_9 || tempRank == CardUtils.RANK_10)
		{
			g2.translate(xOffset, 3 * height / 5.0 - halfSymbolHeight);
			g2.fill(scaledSuitSymbol);
			// important to reset this!
			g2.setTransform(saveXform);
		}

		// spot C5
		if (tempRank >= CardUtils.RANK_4 && tempRank <= CardUtils.RANK_10)
		{
			g2.translate(xOffset, 4 * height / 5.0 - halfSymbolHeight);
			g2.fill(scaledSuitSymbol);
			// important to reset this!
			g2.setTransform(saveXform);
		}

		/*
		 * Draw face cards if necessary
		 */
		//		  if (tempRank >= 11 && tempRank <= 13)
		//		  {
		//			  Image scaledImage =
		//				  this.image.getScaledInstance(30, -1, Image.SCALE_SMOOTH);
		//			  // start at A1 location
		//			  xOffset =
		//				  width / 4 - scaledSuitSymbol.getBounds2D().getWidth() / 2.0;
		//
		//			  g2.drawImage(
		//				  image,
		//				  (int) xOffset,
		//				  (int) (height / 5.0 - halfSymbolHeight),
		//				  g2.getBackground(),
		//				  null);
		//		  }
	}

	/*
	 * Overrides the method from the superclass in order to manage default
	 * size. 
	 */
	public Dimension getPreferredSize()
	{
		if (preferredSize == null)
		{
			return new Dimension(CARD_WIDTH, CARD_HEIGHT);
		} else
		{
			Dimension newPrefSize = super.getPreferredSize();
			System.out.println("Correcting preferred size from " + newPrefSize);
			newPrefSize.height = 7 * newPrefSize.width / 5;
			System.out.println("to " + newPrefSize);
			return newPrefSize;
		}
	}

	/*
	 * Overrides the method from the superclass in order to manage default
	 * size. 
	 */
	public void setPreferredSize(Dimension newPrefSize)
	{
		/*
		 * Force a proportion that's fixed to 5 x 7 roughly
		 */
		preferredSize = newPrefSize;
		super.setPreferredSize(newPrefSize);
	}

	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Test");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container pane = frame.getContentPane();
		pane.setLayout(new GridLayout(0, 13));
		//		pane.setLayout(new FlowLayout());

		/*
		 * iterate through the suits, in their defined order
		 */
		LinkedHashSet suits = CardUtils.getSuits();
		for (Iterator suitIter = suits.iterator(); suitIter.hasNext();)
		{
			int suit = ((Integer) suitIter.next()).intValue();

			/*
			 * iterate through the ranks, in their defined order
			 */
			LinkedHashSet ranks = CardUtils.getRanks();
			for (Iterator rankIter = ranks.iterator(); rankIter.hasNext();)
			{
				int rank = ((Integer) rankIter.next()).intValue();
				//System.out.println("Adding card for " + suit + " and " + rank);
				pane.add(new JCard(new DefaultCardModel(rank, suit)));
			}
		}

		frame.pack();
		frame.show();
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable o, Object arg)
	{
		updateDerivedValues();
		// trop!
		// this.setVisible(model.isVisible());
		if (this.imageLabel != null)
		{
			this.imageLabel.setVisible(model.isVisible());
		}
		this.repaint();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	public void mouseEntered(MouseEvent e)
	{
		if (this.isEnabled())
		{
			inside = true;
			repaint();
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	public void mouseExited(MouseEvent e)
	{
		if (this.isEnabled())
		{
			inside = false;
			repaint();
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	public void mousePressed(MouseEvent e)
	{
		if (this.isEnabled())
		{
			pressed = true;
			repaint();
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	public void mouseReleased(MouseEvent e)
	{
		if (this.isEnabled())
		{
			pressed = false;
			repaint();
			if (!inside)
			{
				return;
			}
			for (int i = 0; i < actionListeners.size(); i++)
			{
				ActionListener listener =
					(ActionListener) actionListeners.get(i);
				listener.actionPerformed(
					new ActionEvent(this, ActionEvent.ACTION_PERFORMED, label));
			}
		}
	}

	public synchronized void addActionListener(ActionListener listener)
	{
		this.actionListeners.add(listener);
	}

	public synchronized void removeActionListener(ActionListener listener)
	{
		this.actionListeners.remove(listener);
	}

}
