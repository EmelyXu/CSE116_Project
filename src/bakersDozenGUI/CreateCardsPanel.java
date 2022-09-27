package bakersDozenGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import BakersDozen.BakersDozen;
import edu.buffalo.cse116.Card;

public class CreateCardsPanel extends JPanel {

	/**
	 * 
	 */
	// don't know why but we need it.....
	private static final long serialVersionUID = 1L;
	private JLayeredPane layeredPane;
	private JPanel bdPanel;

	private JLabel[] cardLabel;

	private JLabel homecell1;
	private JLabel homecell2;
	private JLabel homecell3;
	private JLabel homecell4;

	private BakersDozen game;

	private Stack<Move> moveStack;

	private JButton undo;

	/**
	 * Makes layer panel puts JLabels which are the cards into the panel adds mouse
	 * listener
	 */
	public CreateCardsPanel() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		layeredPane = new JLayeredPane();
		bdPanel = new JPanel(new BorderLayout());
		game = new BakersDozen();
		cardLabel = new JLabel[52];
		JButton b1 = new JButton();
		layeredPane.add(b1);
		b1.setVisible(true);
		b1.setBackground(Color.WHITE);
		b1.setForeground(Color.WHITE);
		b1.setBorderPainted(false);
		b1.setBounds(750, 10, 30, 30);

		moveStack = new Stack<Move>();

		Point homecellOrigin = new Point(10, 20);
		homecell1 = createCardLabel("Images/homecell_club.png", homecellOrigin);
		homecellOrigin.x += 100;
		homecell2 = createCardLabel("Images/homecell_d.png", homecellOrigin);
		homecellOrigin.x += 100;
		homecell3 = createCardLabel("Images/homecell_spade.png", homecellOrigin);
		homecellOrigin.x += 100;
		homecell4 = createCardLabel("Images/homecell_heart.png", homecellOrigin);

		layeredPane.add(homecell1);
		layeredPane.add(homecell2);
		layeredPane.add(homecell3);
		layeredPane.add(homecell4);

		undo = new JButton("Undo Button");
		undo.setBounds(500, 25, 100, 40);
		layeredPane.add(undo);

		Point origin = new Point(10, 150);
		int offset = 20;

		int count = 0;
		for (int i = 0; i < cardLabel.length; i++) {
			cardLabel[i] = createCardLabel(game.getPath(i), origin);

			cardLabel[i].setName(Integer.toString(i));
			CardDragger dragger = new CardDragger(cardLabel[i]);

			cardLabel[i].addMouseListener(dragger);
			cardLabel[i].addMouseMotionListener(dragger);

			layeredPane.add(cardLabel[i]);
			layeredPane.moveToFront(cardLabel[i]);

			origin.y += offset;

			count++;
			if (count == 4) {

				origin.x += 85;
				origin.y = 150;
				count = 0;
			}

		}

		undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (moveStack.peek().getAction() != "homecell") {
						for (int i = 0; i < cardLabel.length; i++) {
							if (cardLabel[i].getName() == moveStack.peek().getJlabelNum()) {
								cardLabel[i].setBounds(moveStack.peek().getLocation().x,
										moveStack.peek().getLocation().y, 73, 97);
								game.removeTableauCard(moveStack.peek().getNewPile());
								game.forceAddCard(moveStack.peek().getCard(), moveStack.peek().getPrePile());
								layeredPane.moveToFront(cardLabel[i]);
							}
						}
					} else {
						for (int i = 0; i < cardLabel.length; i++) {
							if (cardLabel[i].getName() == moveStack.peek().getJlabelNum()) {
								cardLabel[i].setBounds(moveStack.peek().getLocation().x,
										moveStack.peek().getLocation().y, 73, 97);
								game.forceRemoveHomecell(moveStack.peek().getNewPile());
								game.forceAddCard(moveStack.peek().getCard(), moveStack.peek().getPrePile());
								CardDragger dragger = new CardDragger(cardLabel[i]);
								cardLabel[i].addMouseListener(dragger);
								cardLabel[i].addMouseMotionListener(dragger);
								layeredPane.moveToFront(cardLabel[i]);
							}
						}
					}
					moveStack.pop();
				} catch (EmptyStackException r) {

				}
			}
		});

		bdPanel.add(layeredPane, BorderLayout.CENTER);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {

				int random1 = (int) (Math.random() * 255);
				int random2 = (int) (Math.random() * 255);
				int random3 = (int) (Math.random() * 255);
				Color ran = new Color(random1, random2, random3);
				b1.setBackground(ran);
				b1.setForeground(ran);
				bdPanel.setBackground(ran);
			}
		});
	}

	public JPanel getBDPanel() {
		return bdPanel;
	}

	/**
	 * Home cell Locations
	 * 
	 * @param i
	 * @return
	 */
	public Point getHomecellLocation(int i) {
		switch (i) {
		case 1:
			return homecell1.getLocation();
		case 2:
			return homecell2.getLocation();
		case 3:
			return homecell3.getLocation();
		default:
			return homecell4.getLocation();
		}

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

	public JLabel createWinLabel(String string, Point origin) {
		ImageIcon icon = new ImageIcon(string);
		JLabel cardLabel = new JLabel(icon);
		cardLabel.setVerticalAlignment(JLabel.TOP);
		cardLabel.setHorizontalAlignment(JLabel.CENTER);
		cardLabel.setOpaque(true);

		cardLabel.setBounds(origin.x, origin.y, 1280, 731);
		return cardLabel;
	}

	/**
	 * @return Layered Panel
	 */
	public JLayeredPane getJLayeredPane() {

		return layeredPane;
	}

	public class CardDragger implements MouseInputListener {

		private Point originalPoint;
		private Point origin;

		private JLabel card;
		private Card actualCard;

		private int pileNum;

		/**
		 * drags card around
		 * 
		 * @param a
		 */
		public CardDragger(JLabel a) {
			card = a;
			originalPoint = new Point();
			origin = new Point();

			int cardNumber = Integer.parseInt(a.getName());
			ArrayList<Card> cardList = new ArrayList<Card>();
			cardList = game.getAllTableauCardList();
			actualCard = cardList.get(cardNumber);
		}

		// When mouse clicked, it gets the location and determines which pile it is from

		@Override
		public void mousePressed(MouseEvent e) {
			Component temp = (Component) e.getSource();
			originalPoint = temp.getLocation();
			if (originalPoint.x == 10) {
				pileNum = 0;
			} else if (originalPoint.x == 95) {
				pileNum = 1;
			} else if (originalPoint.x == 180) {
				pileNum = 2;
			} else if (originalPoint.x == 265) {
				pileNum = 3;
			} else if (originalPoint.x == 350) {
				pileNum = 4;
			} else if (originalPoint.x == 435) {
				pileNum = 5;
			} else if (originalPoint.x == 520) {
				pileNum = 6;
			} else if (originalPoint.x == 605) {
				pileNum = 7;
			} else if (originalPoint.x == 690) {
				pileNum = 8;
			} else if (originalPoint.x == 775) {
				pileNum = 9;
			} else if (originalPoint.x == 860) {
				pileNum = 10;
			} else if (originalPoint.x == 945) {
				pileNum = 11;
			} else if (originalPoint.x == 1030) {
				pileNum = 12;
			}
			for (int i = 0; i < 13; i++) {
				if (game.getTableauList(i) != null) {
					if (actualCard == game.getTableauLastCard(i)) {
						origin.x = e.getX();
						origin.y = e.getY();

					}
				}
			}
		}

		// release and checks if it's addable
		// if not addable, returns to original location
		@Override
		public void mouseReleased(MouseEvent e) {
			Point p = card.getLocation();
			String toPile = "pile";
			String toHomecell = "homecell";

			if (p.getY() <= 40 && p.getX() >= 0 && p.getX() <= 24 && actualCard.getColor() == "club"
					&& game.checkCardAddableHomecell(actualCard, 0)) {
				game.addCardHomecell(actualCard, 0);
				game.removeTableauCard(pileNum);
				if (game.getTableauSize(pileNum) == 0) {
					game.setTableauListEmpty(pileNum);
				}
				card.setLocation(getHomecellLocation(1).x, getHomecellLocation(1).y);
				card.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				card.removeMouseListener(this);
				card.removeMouseMotionListener(this);
				Move newMove = new Move(card.getName(), toHomecell, originalPoint, 0, pileNum, actualCard);
				moveStack.push(newMove);
			}

			else if (p.getY() <= 40 && p.getX() >= 95 && p.getX() <= 127 && actualCard.getColor() == "diamond"
					&& game.checkCardAddableHomecell(actualCard, 1)) {
				game.addCardHomecell(actualCard, 1);
				game.removeTableauCard(pileNum);
				if (game.getTableauSize(pileNum) == 0) {
					game.setTableauListEmpty(pileNum);
				}
				card.setLocation(getHomecellLocation(2).x, getHomecellLocation(2).y);
				card.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				card.removeMouseListener(this);
				card.removeMouseMotionListener(this);
				Move newMove = new Move(card.getName(), toHomecell, originalPoint, 1, pileNum, actualCard);
				moveStack.push(newMove);
			}

			else if (p.getY() <= 40 && p.getX() >= 197 && p.getX() <= 225 && actualCard.getColor() == "spade"
					&& game.checkCardAddableHomecell(actualCard, 2)) {
				game.addCardHomecell(actualCard, 2);
				game.removeTableauCard(pileNum);
				if (game.getTableauSize(pileNum) == 0) {
					game.setTableauListEmpty(pileNum);
				}
				card.setLocation(getHomecellLocation(3).x, getHomecellLocation(3).y);
				card.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				card.removeMouseListener(this);
				card.removeMouseMotionListener(this);
				Move newMove = new Move(card.getName(), toHomecell, originalPoint, 2, pileNum, actualCard);
				moveStack.push(newMove);
			}

			else if (!(game.getPileEmpty(pileNum)) && p.getY() <= 40 && p.getX() >= 295 && p.getX() <= 324
					&& actualCard.getColor() == "heart" && game.checkCardAddableHomecell(actualCard, 3)) {
				game.addCardHomecell(actualCard, 3);
				game.removeTableauCard(pileNum);
				if (game.getTableauSize(pileNum) == 0) {
					game.setTableauListEmpty(pileNum);
				}
				card.setLocation(getHomecellLocation(4).x, getHomecellLocation(4).y);
				card.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				card.removeMouseListener(this);
				card.removeMouseMotionListener(this);
				Move newMove = new Move(card.getName(), toHomecell, originalPoint, 3, pileNum, actualCard);
				moveStack.push(newMove);
			}

			else if (!(game.getPileEmpty(0)) && p.getY() > 40 && pileNum != 0 && p.getX() >= 0 && p.getX() <= 16
					&& game.cardAddable(actualCard, 0)) {
				game.tableauAddCard(actualCard, 0);
				game.removeTableauCard(pileNum);
				if (game.getTableauSize(pileNum) == 0) {
					game.setTableauListEmpty(pileNum);
				}
				int test = game.getTableauSize(0);

				int bound1 = 130;
				for (int d = 0; d < test; d++) {
					bound1 += 20;
				}
				card.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				card.setLocation(10, bound1);
				Move newMove = new Move(card.getName(), toPile, originalPoint, 0, pileNum, actualCard);
				moveStack.push(newMove);

			} else if (!(game.getPileEmpty(1)) && p.getY() > 40 && pileNum != 1 && p.getX() >= 89 && p.getX() <= 101
					&& game.cardAddable(actualCard, 1)) {
				game.tableauAddCard(actualCard, 1);
				game.removeTableauCard(pileNum);
				if (game.getTableauSize(pileNum) == 0) {
					game.setTableauListEmpty(pileNum);
				}
				int test = game.getTableauSize(1);
				int bound2 = 130;
				for (int d = 0; d < test; d++) {
					bound2 += 20;
				}
				card.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				card.setLocation(95, bound2);
				Move newMove = new Move(card.getName(), toPile, originalPoint, 1, pileNum, actualCard);
				moveStack.push(newMove);

			} else if (!(game.getPileEmpty(2)) && p.getY() > 40 && pileNum != 2 && p.getX() >= 174 && p.getX() <= 186
					&& game.cardAddable(actualCard, 2)) {
				game.tableauAddCard(actualCard, 2);
				game.removeTableauCard(pileNum);
				if (game.getTableauSize(pileNum) == 0) {
					game.setTableauListEmpty(pileNum);
				}
				int test = game.getTableauSize(2);
				int bound3 = 130;
				for (int d = 0; d < test; d++) {
					bound3 += 20;
				}
				card.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				card.setLocation(180, bound3);
				Move newMove = new Move(card.getName(), toPile, originalPoint, 2, pileNum, actualCard);
				moveStack.push(newMove);

			} else if (!(game.getPileEmpty(3)) && p.getY() > 40 && pileNum != 3 && p.getX() >= 260 && p.getX() <= 271
					&& game.cardAddable(actualCard, 3)) {
				game.tableauAddCard(actualCard, 3);
				game.removeTableauCard(pileNum);
				if (game.getTableauSize(pileNum) == 0) {
					game.setTableauListEmpty(pileNum);
				}
				int test = game.getTableauSize(3);
				int bound4 = 130;
				for (int d = 0; d < test; d++) {
					bound4 += 20;
				}
				card.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				card.setLocation(265, bound4);
				Move newMove = new Move(card.getName(), toPile, originalPoint, 3, pileNum, actualCard);
				moveStack.push(newMove);

			} else if (!(game.getPileEmpty(4)) && p.getY() > 40 && pileNum != 4 && p.getX() >= 344 && p.getX() <= 356
					&& game.cardAddable(actualCard, 4)) {
				game.tableauAddCard(actualCard, 4);
				game.removeTableauCard(pileNum);
				int test = game.getTableauSize(4);
				int bound5 = 130;
				for (int d = 0; d < test; d++) {
					bound5 += 20;
				}
				card.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				card.setLocation(350, bound5);
				Move newMove = new Move(card.getName(), toPile, originalPoint, 4, pileNum, actualCard);
				moveStack.push(newMove);

			} else if (!(game.getPileEmpty(5)) && p.getY() > 40 && pileNum != 5 && p.getX() >= 429 && p.getX() <= 441
					&& game.cardAddable(actualCard, 5)) {
				game.tableauAddCard(actualCard, 5);
				game.removeTableauCard(pileNum);
				if (game.getTableauSize(pileNum) == 0) {
					game.setTableauListEmpty(pileNum);
				}
				int test = game.getTableauSize(5);
				int bound6 = 130;
				for (int d = 0; d < test; d++) {
					bound6 += 20;
				}
				card.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				card.setLocation(435, bound6);
				Move newMove = new Move(card.getName(), toPile, originalPoint, 5, pileNum, actualCard);
				moveStack.push(newMove);

			} else if (!(game.getPileEmpty(6)) && p.getY() > 40 && pileNum != 6 && p.getX() >= 513 && p.getX() <= 526
					&& game.cardAddable(actualCard, 6)) {
				game.tableauAddCard(actualCard, 6);
				game.removeTableauCard(pileNum);
				if (game.getTableauSize(pileNum) == 0) {
					game.setTableauListEmpty(pileNum);
				}
				int test = game.getTableauSize(6);
				int bound7 = 130;
				for (int d = 0; d < test; d++) {
					bound7 += 20;
				}
				card.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				card.setLocation(520, bound7);
				Move newMove = new Move(card.getName(), toPile, originalPoint, 6, pileNum, actualCard);
				moveStack.push(newMove);

			} else if (!(game.getPileEmpty(7)) && p.getY() > 40 && pileNum != 7 && p.getX() >= 599 && p.getX() <= 611
					&& game.cardAddable(actualCard, 7)) {
				game.tableauAddCard(actualCard, 7);
				game.removeTableauCard(pileNum);
				if (game.getTableauSize(pileNum) == 0) {
					game.setTableauListEmpty(pileNum);
				}
				int test = game.getTableauSize(7);
				int bound8 = 130;
				for (int d = 0; d < test; d++) {
					bound8 += 20;
				}
				card.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				card.setLocation(605, bound8);
				Move newMove = new Move(card.getName(), toPile, originalPoint, 7, pileNum, actualCard);
				moveStack.push(newMove);

			} else if (!(game.getPileEmpty(8)) && p.getY() > 40 && pileNum != 8 && p.getX() >= 684 && p.getX() <= 695
					&& game.cardAddable(actualCard, 8)) {
				game.tableauAddCard(actualCard, 8);
				game.removeTableauCard(pileNum);
				if (game.getTableauSize(pileNum) == 0) {
					game.setTableauListEmpty(pileNum);
				}
				int test = game.getTableauSize(8);
				int bound9 = 130;
				for (int d = 0; d < test; d++) {
					bound9 += 20;
				}
				card.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				card.setLocation(690, bound9);
				Move newMove = new Move(card.getName(), toPile, originalPoint, 8, pileNum, actualCard);
				moveStack.push(newMove);

			} else if (!(game.getPileEmpty(9)) && p.getY() > 40 && pileNum != 9 && p.getX() >= 769 && p.getX() <= 781
					&& game.cardAddable(actualCard, 9)) {
				game.tableauAddCard(actualCard, 9);
				game.removeTableauCard(pileNum);
				if (game.getTableauSize(pileNum) == 0) {
					game.setTableauListEmpty(pileNum);
				}
				int test = game.getTableauSize(9);
				int bound10 = 130;
				for (int d = 0; d < test; d++) {
					bound10 += 20;
				}
				card.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				card.setLocation(775, bound10);
				Move newMove = new Move(card.getName(), toPile, originalPoint, 9, pileNum, actualCard);
				moveStack.push(newMove);

			} else if (!(game.getPileEmpty(10)) && p.getY() > 40 && pileNum != 10 && p.getX() >= 853 && p.getX() <= 866
					&& game.cardAddable(actualCard, 10)) {
				game.tableauAddCard(actualCard, 10);
				game.removeTableauCard(pileNum);
				if (game.getTableauSize(pileNum) == 0) {
					game.setTableauListEmpty(pileNum);
				}
				int test = game.getTableauSize(10);
				int bound11 = 130;
				for (int d = 0; d < test; d++) {
					bound11 += 20;
				}
				card.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				card.setLocation(860, bound11);
				Move newMove = new Move(card.getName(), toPile, originalPoint, 10, pileNum, actualCard);
				moveStack.push(newMove);

			} else if (!(game.getPileEmpty(11)) && p.getY() > 40 && pileNum != 11 && p.getX() >= 939 && p.getX() <= 952
					&& game.cardAddable(actualCard, 11)) {
				game.tableauAddCard(actualCard, 11);
				game.removeTableauCard(pileNum);
				if (game.getTableauSize(pileNum) == 0) {
					game.setTableauListEmpty(pileNum);
				}
				int test = game.getTableauSize(11);
				int bound12 = 130;
				for (int d = 0; d < test; d++) {
					bound12 += 20;
				}
				card.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				card.setLocation(945, bound12);
				Move newMove = new Move(card.getName(), toPile, originalPoint, 11, pileNum, actualCard);
				moveStack.push(newMove);

			} else if (!(game.getPileEmpty(12)) && p.getY() > 40 && pileNum != 12 && p.getX() >= 1022
					&& p.getX() <= 1035 && game.cardAddable(actualCard, 12)) {
				game.tableauAddCard(actualCard, 12);
				game.removeTableauCard(pileNum);
				if (game.getTableauSize(pileNum) == 0) {
					game.setTableauListEmpty(pileNum);
				}
				int test = game.getTableauSize(12);
				int bound13 = 130;
				for (int d = 0; d < test; d++) {
					bound13 += 20;
				}
				card.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				card.setLocation(1030, bound13);
				Move newMove = new Move(card.getName(), toPile, originalPoint, 12, pileNum, actualCard);
				moveStack.push(newMove);

			}

			else {

				card.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				card.setLocation(originalPoint.x, originalPoint.y);
				// JOptionPane.showMessageDialog(null, "Illegal Move");
			}

			Point f = new Point(0, 0);
			JLabel thumb = createWinLabel("Images/win.gif", f);
			int counter = 0;
			for (int o = 0; o < 13; o++) {
				if (game.getTableauList(o) == null) {
					counter += 1;
				}
			}
			if (counter == 13) {
				layeredPane.add(thumb);
				layeredPane.moveToBack(thumb);
			}

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
			for (int i = 0; i < 13; i++) {
				if (game.getTableauList(i) != null) {
					if (actualCard == game.getTableauLastCard(i)) {
						layeredPane.moveToFront(card);

						Point p = card.getLocation();
						card.setLocation(p.x + (e.getX() - origin.x), p.y + (e.getY() - origin.y));
					}
				}
			}

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			for (int i = 0; i < 13; i++) {
				if (game.getTableauList(i) != null) {
					if (actualCard == game.getTableauLastCard(i)) {
						card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					}
				}
			}

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

}
