package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import AceUp.AceUp;
import edu.buffalo.cse116.Card;

public class AcesUpGUI extends JPanel {

	/**
	 * 
	 */
	// don't know why but we need it.....
	private static final long serialVersionUID = 1L;

	private JLayeredPane layeredPane;

	private JPanel aPanel;

	private JLabel[] cardLabel;

	private JLabel[] pile;
	private JButton homecellPile;

	public AceUp game;

	int z;

	/**
	 * Makes layer panel puts JLabels which are the cards into the panel adds mouse
	 * listener
	 */
	public AcesUpGUI() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		layeredPane = new JLayeredPane();
		aPanel = new JPanel(new BorderLayout());
		game = new AceUp();
		z = 4;

		cardLabel = new JLabel[52];
		pile = new JLabel[4];

		Point pileOrigin = new Point(10, 20);
		homecellPile = createButton("Images/stock1.png", pileOrigin);
		pileOrigin.x += 200;
		layeredPane.add(homecellPile);
		easter e = new easter(homecellPile);
		homecellPile.addMouseListener(e);
		homecellPile.addMouseMotionListener(e);

		int offset = 100;
		for (int i = 0; i < pile.length; i++) {
			pile[i] = createCardLabel("Images/homecell.png", pileOrigin);
			pileOrigin.x += offset;
			layeredPane.add(pile[i]);
		}

		int count = 0;
		String[] strings = game.getCardImage();
		for (int i = 0; i < cardLabel.length; i++) {
			if (count == 4) {
				count = 0;
			}
			cardLabel[i] = createCardLabel(strings[i], homecellPile.getLocation());
			cardLabel[i].setName(Integer.toString(i));
			CardDragger dragger = new CardDragger(cardLabel[i]);
			cardLabel[i].addMouseListener(dragger);
			cardLabel[i].addMouseMotionListener(dragger);
			count += 1;

		}

		int count2 = 0;
		for (int z = 0; z < 4; z++) {
			if (count2 == 4) {
				count2 = 0;
			}
			cardLabel[z].setLocation(pile[count2].getLocation());
			layeredPane.add(cardLabel[z]);
			layeredPane.moveToFront(cardLabel[z]);
			count2 += 1;
		}

		game.addFourCards();

		homecellPile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			   if (z > 48) {
					
					JOptionPane.showMessageDialog(null, "No More Cards");
					
				} else {
					int counter = 0;
					for (int y = z; y < (z + 4); y++) {
						if (counter == 4) {
							counter = 0;
						}
						if (counter == 0) {
							int test1 = game.getTableau(0).size();
							int bound1 = pile[counter].getLocation().y;
							for (int d = 0; d < test1; d++) {
								bound1 += 20;
							}
							cardLabel[y].setLocation(pile[counter].getLocation().x, bound1);
							layeredPane.add(cardLabel[y]);
							layeredPane.moveToFront(cardLabel[y]);
							counter += 1;
						} else if (counter == 1) {
							int test2 = game.getTableau(1).size();
							int bound2 = pile[counter].getLocation().y;
							for (int d = 0; d < test2; d++) {
								bound2 += 20;
							}
							cardLabel[y].setLocation(pile[counter].getLocation().x, bound2);
							layeredPane.add(cardLabel[y]);
							layeredPane.moveToFront(cardLabel[y]);
							counter += 1;
						} else if (counter == 2) {
							int test3 = game.getTableau(2).size();
							int bound3 = pile[counter].getLocation().y;
							for (int d = 0; d < test3; d++) {
								bound3 += 20;
							}
							cardLabel[y].setLocation(pile[counter].getLocation().x, bound3);
							layeredPane.add(cardLabel[y]);
							layeredPane.moveToFront(cardLabel[y]);
							counter += 1;
						} else if (counter == 3) {
							int test4 = game.getTableau(3).size();
							int bound4 = pile[counter].getLocation().y;
							for (int d = 0; d < test4; d++) {
								bound4 += 20;
							}
							cardLabel[y].setLocation(pile[counter].getLocation().x, bound4);
							layeredPane.add(cardLabel[y]);
							layeredPane.moveToFront(cardLabel[y]);
							counter += 1;
						}

					}
					game.addFourCards();
					z += 4;
				}
			}
		});

		aPanel.add(layeredPane, BorderLayout.CENTER);

	}

	public JPanel getaPanel() {
		return aPanel;
	}

	public AceUp getGame() {
		return game;
	}

	/**
	 * Create card JLabel
	 * 
	 * @param string
	 * @param origin
	 * @return
	 */
	public JLabel createCardLabel(String string, Point origin) {
		ImageIcon icon = new ImageIcon(string);
		JLabel cardLabel = new JLabel(icon);
		cardLabel.setBorder(BorderFactory.createEmptyBorder());
		cardLabel.setVerticalAlignment(JLabel.TOP);
		cardLabel.setHorizontalAlignment(JLabel.CENTER);
		cardLabel.setOpaque(false);

		cardLabel.setBounds(origin.x, origin.y, icon.getIconWidth(), icon.getIconHeight());
		return cardLabel;
	}

	public JButton createButton(String string, Point origin) {
		
		ImageIcon icon = new ImageIcon(string);
		JButton button = new JButton();
		button.setBackground(new Color(0,0,0));
		button.setOpaque(false);
		button.setIcon(icon);
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setVerticalAlignment(JLabel.TOP);
		button.setHorizontalAlignment(JLabel.CENTER);
		
		

		button.setBounds(origin.x, origin.y, icon.getIconWidth(), icon.getIconHeight());
		return button;
	}

	public class CardDragger implements MouseInputListener {

		private Point originalPoint;

		private Point origin;

		private JLabel card;

		private Card actualCard;

		/**
		 * drags card around
		 * 
		 * @param a
		 */
		public CardDragger(JLabel a) {
			card = a;
			originalPoint = new Point();
			origin = new Point();

			int cardNumber = Integer.parseInt(card.getName());
			ArrayList<Card> cardList = game.getDeck();
			actualCard = cardList.get(cardNumber);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			Component temp = (Component) e.getSource();
			originalPoint = temp.getLocation();
			origin.x = e.getX();
			origin.y = e.getY();
			
			if(actualCard == AceUp.tableau0.getTopCard() ||actualCard == AceUp.tableau1.getTopCard()
					||actualCard == AceUp.tableau2.getTopCard() || actualCard == AceUp.tableau3.getTopCard()){
				
			if (originalPoint.x == 210) {
				if (game.removable(actualCard)) {
					game.removeCard(0);
					card.setLocation(10, 200);
					layeredPane.moveToFront(card);
				} else if (!game.removable(actualCard) && game.checkPiles()) {
					game.removeCard(0);
					int emptyPile = game.checkPileEmptyNum();
					if (emptyPile == 0) {
						game.getTableau(0).addCard(actualCard);
						card.setLocation(pile[0].getLocation());
					} else if (emptyPile == 1) {
						game.getTableau(1).addCard(actualCard);
						card.setLocation(pile[1].getLocation());
					} else if (emptyPile == 2) {
						game.getTableau(2).addCard(actualCard);
						card.setLocation(pile[2].getLocation());
					} else if (emptyPile == 3) {
						game.getTableau(3).addCard(actualCard);
						card.setLocation(pile[3].getLocation());
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Wrong move!");
				}
			} else if (originalPoint.x == 310) {
				if (game.removable(actualCard)) {
					game.removeCard(1);
					card.setLocation(10, 200);
					layeredPane.moveToFront(card);
				} else if (!game.removable(actualCard) && game.checkPiles()) {
					game.removeCard(1);
					int emptyPile = game.checkPileEmptyNum();
					if (emptyPile == 0) {
						game.getTableau(0).addCard(actualCard);
						card.setLocation(pile[0].getLocation());
					} else if (emptyPile == 1) {
						game.getTableau(1).addCard(actualCard);
						card.setLocation(pile[1].getLocation());
					} else if (emptyPile == 2) {
						game.getTableau(2).addCard(actualCard);
						card.setLocation(pile[2].getLocation());
					} else if (emptyPile == 3) {
						game.getTableau(3).addCard(actualCard);
						card.setLocation(pile[3].getLocation());
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Wrong move!");
				}
			} else if (originalPoint.x == 410) {
				if (game.removable(actualCard)) {
					game.removeCard(2);
					card.setLocation(10, 200);
					layeredPane.moveToFront(card);
				} else if (!game.removable(actualCard) && game.checkPiles()) {
					game.removeCard(2);
					int emptyPile = game.checkPileEmptyNum();
					if (emptyPile == 0) {
						game.getTableau(0).addCard(actualCard);
						card.setLocation(pile[0].getLocation());
					} else if (emptyPile == 1) {
						game.getTableau(1).addCard(actualCard);
						card.setLocation(pile[1].getLocation());
					} else if (emptyPile == 2) {
						game.getTableau(2).addCard(actualCard);
						card.setLocation(pile[2].getLocation());
					} else if (emptyPile == 3) {
						game.getTableau(3).addCard(actualCard);
						card.setLocation(pile[3].getLocation());
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Wrong move!");
				}
			} else if (originalPoint.x == 510) {
				if (game.removable(actualCard)) {
					game.removeCard(3);
					card.setLocation(10, 200);
					layeredPane.moveToFront(card);
				} else if (!game.removable(actualCard) && game.checkPiles()) {
					game.removeCard(3);
					int emptyPile = game.checkPileEmptyNum();
					if (emptyPile == 0) {
						game.getTableau(0).addCard(actualCard);
						card.setLocation(pile[0].getLocation());
					} else if (emptyPile == 1) {
						game.getTableau(1).addCard(actualCard);
						card.setLocation(pile[1].getLocation());
					} else if (emptyPile == 2) {
						game.getTableau(2).addCard(actualCard);
						card.setLocation(pile[2].getLocation());
					} else if (emptyPile == 3) {
						game.getTableau(3).addCard(actualCard);
						card.setLocation(pile[3].getLocation());
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Wrong move!");
				}
			}
		}
		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

		@Override
		public void mouseDragged(MouseEvent e) {

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			Card T1 = AceUp.tableau0.getTopCard();
			Card T2 = AceUp.tableau1.getTopCard();
			Card T3 = AceUp.tableau2.getTopCard();
			Card T4 = AceUp.tableau3.getTopCard();

			ArrayList<Card> cardList = new ArrayList<Card>();
			cardList.add(T1);
			cardList.add(T2);
			cardList.add(T3);
			cardList.add(T4);

			if (cardList.contains(actualCard)) {
				card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

		}

		@Override
		public void mouseClicked(MouseEvent e) {

		}

	}

}
	class easter implements MouseInputListener {
	
	JButton stock;
	int clickedTimes;
	
	public easter(JButton a) {
		clickedTimes = 0;
		this.stock = a;
	
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		clickedTimes = clickedTimes + 1;
		Random r = new Random();
		
		this.stock.setIcon(new ImageIcon("Images/stock" + r.nextInt(9) + ".png"));
		
		if(clickedTimes>=12){
		this.stock.setIcon(new ImageIcon("Images/homecell.png"));
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
