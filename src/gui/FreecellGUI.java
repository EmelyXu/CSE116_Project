package gui;

import edu.buffalo.cse116.Card;
import java.awt.Cursor;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import Freecell.*;



public class FreecellGUI {
	private JLayeredPane lay;
	JPanel fPanel;
	public static Game game;
	CardLayout cardLayout;
	/**
	 * This method instantiate everything(make a new Freecell game,
	 * create a new JPanel JLayeredPane to put all piles
	 * and then add all the 52 cards to the pane
	 * 
	 */
	public FreecellGUI() {
		cardLayout = new CardLayout();
		game = new Game();
		fPanel = new JPanel();
		fPanel.setLayout(null);
		fPanel.add(new JLabel("Freecell Game Panel"));
		lay = new JLayeredPane();
		JButton b1 = new JButton();
		lay.add(b1);
		b1.setVisible(true);
		b1.setBackground(Color.WHITE);
		b1.setForeground(Color.WHITE);
		b1.setBorderPainted(false);
		b1.setBounds(750,10,30,30);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {

				int random1 = (int) (Math.random() * 255);
				int random2 = (int) (Math.random() * 255);
				int random3 = (int) (Math.random() * 255);
				Color ran = new Color(random1, random2, random3);
				b1.setBackground(ran);
				b1.setForeground(ran);
				fPanel.setBackground(ran);
			}
		});
		// start from pile 1 to pile 8 to get all the cards
		// and makes cards JLabels
		// and adds cards to the layer
		
		for (int tab6 = 1; tab6 <= 8; tab6++) {
			Tableau x = game.getTableau(tab6);

			Point p = new Point();
			p.setLocation(10 + (tab6 - 1) * (73 + 10), 120);
			if (tab6 < 5) {
				for (int carIdx = 5; carIdx >= 0; carIdx--) {
					String str = x.getCardImage(carIdx);
					p.setLocation(10 + (tab6 - 1) * (73 + 10), 120 + carIdx * 20);
					JLabel cardLabel = createCardLabel(str, p);
					lay.add(cardLabel);
					Card mark = x.getCard(carIdx);

					MouseEventListenerFreecell mouse = new MouseEventListenerFreecell(cardLabel, mark, lay, tab6);
					cardLabel.addMouseListener(mouse);
					cardLabel.addMouseMotionListener(mouse);

				}
			} else {
				for (int carIdx = 6; carIdx >= 0; carIdx--) {
					String str = x.getCardImage(carIdx);
					p.setLocation(10 + (tab6 - 1) * (73 + 10), 120 + carIdx * 20);
					JLabel cardLabel = createCardLabel(str, p);
					lay.add(cardLabel);
					Card mark = x.getCard(carIdx);

					MouseEventListenerFreecell mouse = new MouseEventListenerFreecell(cardLabel, mark, lay, tab6);
					cardLabel.addMouseListener(mouse);
					cardLabel.addMouseMotionListener(mouse);

				}
			}
		}

		fPanel.add(lay);

		lay.setBounds(0, 0, 12000, 7000);

		// Free Cell and Home Cell added 
		// ImageIcon
		JLabel freecell_F = new JLabel(new ImageIcon("Images/Freecell_F.png"));
		JLabel freecell_R = new JLabel(new ImageIcon("Images/Freecell_R.png"));
		JLabel freecell_E1 = new JLabel(new ImageIcon("Images/Freecell_E.png"));
		JLabel freecell_E2 = new JLabel(new ImageIcon("Images/Freecell_E.png"));
		JLabel homecell_1 = new JLabel(new ImageIcon("Images/homecell.png"));
		JLabel homecell_2 = new JLabel(new ImageIcon("Images/homecell.png"));
		JLabel homecell_3 = new JLabel(new ImageIcon("Images/homecell.png"));
		JLabel homecell_4 = new JLabel(new ImageIcon("Images/homecell.png"));

		fPanel.add(freecell_F);
		fPanel.add(freecell_R);
		fPanel.add(freecell_E1);
		fPanel.add(freecell_E2);
		fPanel.add(homecell_1);
		fPanel.add(homecell_2);
		fPanel.add(homecell_3);
		fPanel.add(homecell_4);

		freecell_F.setBounds(10, 10, 73, 97);
		freecell_R.setBounds(93, 10, 73, 97);
		freecell_E1.setBounds(176, 10, 73, 97);
		freecell_E2.setBounds(259, 10, 73, 97);
		homecell_1.setBounds(342, 10, 73, 97);
		homecell_2.setBounds(425, 10, 73, 97);
		homecell_3.setBounds(508, 10, 73, 97);
		homecell_4.setBounds(591, 10, 73, 97);

		EggListener ee = new EggListener(homecell_4);
		homecell_4.addMouseListener(ee);
		homecell_4.addMouseMotionListener(ee);

	}

	/**
	 * This method will creat a card JLabel with the 
	 * given adress of image and 
	 * the point the JLabel need to be located
	 *
	 * @param string is the file name of the image
	 * @param origin is the point the JLabel need to be located
	 * @return the card JLabel with a picture and location
	 */
	public JLabel createCardLabel(String string, Point origin) {
		ImageIcon icon = new ImageIcon(string);
		JLabel cardLabel = new JLabel(icon);
		cardLabel.setBounds(origin.x, origin.y, icon.getIconWidth(), icon.getIconHeight());
		return cardLabel;
	}

	public JPanel getFPanel() {
		return fPanel;
	}

	public static Game getGame() {
		return game;
	}

	//Easter egg
	class EggListener implements MouseInputListener {

		JLabel card;
		int clickedTimes;

		public EggListener(JLabel a) {
			this.card = a;

			clickedTimes = 0;

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			clickedTimes = clickedTimes + 1;
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (clickedTimes == 3 && fPanel.getBackground() == Color.black) {
				card.setIcon(new ImageIcon("Images/spiderweb2.png"));
			}

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
		}

	}
	
	

	public class MouseEventListenerFreecell implements MouseInputListener {

		Point origin;
		JLabel card;
		Point re;
		Card sr;
		boolean move;

		int tabl;
		private JLayeredPane lay;

		private String pile = "tableau";
		private int freepile = 0;

		private ImageIcon image = new ImageIcon("Images/nopenope.gif");

		/**
		 * @param a : Card JLabel
		 * @param c : Card
		 * @param l : Layered Panel
		 * @param t : Tableau Pile number
		 */
		public MouseEventListenerFreecell(JLabel a, Card c, JLayeredPane l, int t) {
			card = a;
			origin = card.getLocation();
			sr = c;
			lay = l;
			tabl = t;

		}

		@Override
		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {
			origin.x = e.getX();
			origin.y = e.getY();
			re = card.getLocation();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			Point p = this.card.getLocation();
			if (p.getX() < 620) {
				if (pile == "homecell") {

					this.card.setBounds(re.x, re.y, 73, 97);

				} else if (pile == "freecell") {
					// move from freecell to homecell
					if (p.getY() < 113) {

						if (p.getX() <= 415) {
							if (game.getHomecell(1).addCard(sr)) {
								this.card.setBounds(342, 10, 73, 97);
								pile = "homecell";
								origin.setLocation(342, 10);
								game.getFreecell(freepile).removCard();
								
							} else {
								this.card.setBounds(re.x, re.y, 73, 97);
							}
						}

						else if (p.getX() <= 498) {
							if (game.getHomecell(2).addCard(sr)) {
								this.card.setBounds(425, 10, 73, 97);
								pile = "homecell";
								origin.setLocation(425, 10);
								game.getFreecell(freepile).removCard();
							} else {
								this.card.setBounds(re.x, re.y, 73, 97);
							}
						}

						else if (p.getX() <= 581) {
							if (game.getHomecell(3).addCard(sr)) {
								this.card.setBounds(508, 10, 73, 97);
								pile = "homecell";
								origin.setLocation(508, 10);
								game.getFreecell(freepile).removCard();
							} else {
								this.card.setBounds(re.x, re.y, 73, 97);
							}
						}

						else if (p.getX() <= 664) {
							if (game.getHomecell(4).addCard(sr)) {
								this.card.setBounds(591, 10, 73, 97);
								pile = "homecell";
								origin.setLocation(591, 10);
								game.getFreecell(freepile).removCard();
							} else {
								this.card.setBounds(re.x, re.y, 73, 97);
							}
						}

						
					}

					// move card from freecell to tableau
					else if (p.getY() > 113 && p.getY() < 400) {
						if (p.getX() <= 83) {
							if (game.getTableau(1).addCard(sr)) {
								game.getFreecell(freepile).removCard();
								tabl = 1;
								pile = "tableau";
								freepile = 0;
								this.card.setBounds(10, 120 + game.getTableau(1).size() * 20 - 20, 73, 97);

							} else {
								this.card.setBounds(re.x, re.y, 73, 97);
								JOptionPane.showMessageDialog(null, image);
							}

						}

						else if (p.getX() <= 166) {
							if (game.getTableau(2).addCard(sr)) {
								game.getFreecell(freepile).removCard();
								tabl = 2;
								pile = "tableau";
								freepile = 0;
								this.card.setBounds(93, 120 + game.getTableau(2).size() * 20 - 20, 73, 97);

							} else {
								this.card.setBounds(re.x, re.y, 73, 97);
								JOptionPane.showMessageDialog(null, image);
							}

						}

						else if (p.getX() <= 249) {
							if (game.getTableau(3).addCard(sr)) {
								game.getFreecell(freepile).removCard();
								tabl = 3;
								pile = "tableau";
								freepile = 0;
								this.card.setBounds(176, 120 + game.getTableau(3).size() * 20 - 20, 73, 97);

							} else {
								this.card.setBounds(re.x, re.y, 73, 97);
								JOptionPane.showMessageDialog(null, image);
							}

						}

						else if (p.getX() <= 332) {
							if (game.getTableau(4).addCard(sr)) {
								game.getFreecell(freepile).removCard();
								tabl = 4;
								pile = "tableau";
								freepile = 0;
								this.card.setBounds(259, 120 + game.getTableau(4).size() * 20 - 20, 73, 97);

							} else {
								this.card.setBounds(re.x, re.y, 73, 97);
								JOptionPane.showMessageDialog(null, image);
							}

						}

						else if (p.getX() <= 415) {
							if (game.getTableau(5).addCard(sr)) {
								game.getFreecell(freepile).removCard();
								tabl = 5;
								pile = "tableau";
								freepile = 0;
								this.card.setBounds(342, 120 + game.getTableau(5).size() * 20 - 20, 73, 97);

							} else {
								this.card.setBounds(re.x, re.y, 73, 97);
								JOptionPane.showMessageDialog(null, image);
							}

						}

						else if (p.getX() <= 498) {
							if (game.getTableau(6).addCard(sr)) {
								game.getFreecell(freepile).removCard();
								tabl = 6;
								pile = "tableau";
								freepile = 0;
								this.card.setBounds(425, 120 + game.getTableau(6).size() * 20 - 20, 73, 97);

							} else {
								this.card.setBounds(re.x, re.y, 73, 97);
								JOptionPane.showMessageDialog(null, image);
							}

						}

						else if (p.getX() <= 581) {
							if (game.getTableau(7).addCard(sr)) {
								game.getFreecell(freepile).removCard();
								tabl = 7;
								pile = "tableau";
								freepile = 0;
								this.card.setBounds(508, 120 + game.getTableau(7).size() * 20 - 20, 73, 97);

							} else {
								this.card.setBounds(re.x, re.y, 73, 97);
								JOptionPane.showMessageDialog(null, image);
							}

						}

						else if (p.getX() <= 664) {
							if (game.getTableau(8).addCard(sr)) {
								game.getFreecell(freepile).removCard();
								tabl = 8;
								freepile = 0;
								pile = "tableau";
								this.card.setBounds(591, 120 + game.getTableau(8).size() * 20 - 20, 73, 97);

							} else {

								this.card.setBounds(re.x, re.y, 73, 97);
								JOptionPane.showMessageDialog(null, image);
							}

						}

					}

				}

				// move card to freecell

				else if (p.getY() < 113) {

					if (p.getX() <= 83) {

						if (game.getFreecell(1).addCard(sr)) {
							this.card.setBounds(10, 10, 73, 97);
							pile = "freecell";
							freepile = 1;
							game.getTableau(tabl).removeCard();
							origin.setLocation(10, 10);
							tabl = 0;
						} else {
							this.card.setBounds(re.x, re.y, 73, 97);
							JOptionPane.showMessageDialog(null, image);
						}
					}

					else if (p.getX() <= 166) {

						if (game.getFreecell(2).addCard(sr)) {
							this.card.setBounds(93, 10, 73, 97);
							pile = "freecell";
							freepile = 2;
							game.getTableau(tabl).removeCard();
							tabl = 0;
							origin.setLocation(93, 10);
						} else {
							this.card.setBounds(re.x, re.y, 73, 97);
							JOptionPane.showMessageDialog(null, image);
						}
					}

					else if (p.getX() <= 249) {

						if (game.getFreecell(3).addCard(sr)) {
							this.card.setBounds(176, 10, 73, 97);
							pile = "freecell";
							freepile = 3;
							game.getTableau(tabl).removeCard();
							tabl = 0;
							origin.setLocation(176, 10);
						} else {
							this.card.setBounds(re.x, re.y, 73, 97);
							JOptionPane.showMessageDialog(null, image);
						}
					}

					else if (p.getX() <= 332) {

						if (game.getFreecell(4).addCard(sr)) {
							this.card.setBounds(259, 10, 73, 97);
							pile = "freecell";
							freepile = 4;
							game.getTableau(tabl).removeCard();
							tabl = 0;
							origin.setLocation(259, 10);
						} else {
							this.card.setBounds(re.x, re.y, 73, 97);
							JOptionPane.showMessageDialog(null, image);
						}
					}

					// move card to homecell

					else if (p.getX() <= 415) {
						if (game.getHomecell(1).addCard(sr)) {
							this.card.setBounds(342, 10, 73, 97);
							pile = "homecell";
							origin.setLocation(342, 10);
							game.getTableau(tabl).removeCard();
						} else {
							this.card.setBounds(re.x, re.y, 73, 97);
							JOptionPane.showMessageDialog(null, image);
						}
					}

					else if (p.getX() <= 498) {
						if (game.getHomecell(2).addCard(sr)) {
							this.card.setBounds(425, 10, 73, 97);
							pile = "homecell";
							origin.setLocation(425, 10);
							game.getTableau(tabl).removeCard();
						} else {
							this.card.setBounds(re.x, re.y, 73, 97);
							JOptionPane.showMessageDialog(null, image);
						}
					}

					else if (p.getX() <= 581) {
						if (game.getHomecell(3).addCard(sr)) {
							this.card.setBounds(508, 10, 73, 97);
							pile = "homecell";
							origin.setLocation(508, 10);
							game.getTableau(tabl).removeCard();
						} else {
							this.card.setBounds(re.x, re.y, 73, 97);
							JOptionPane.showMessageDialog(null, image);
						}
					}

					else if (p.getX() <= 700) {
						if (game.getHomecell(4).addCard(sr)) {
							this.card.setBounds(591, 10, 73, 97);
							pile = "homecell";
							origin.setLocation(591, 10);
							game.getTableau(tabl).removeCard();
						} else {
							this.card.setBounds(re.x, re.y, 73, 97);
							JOptionPane.showMessageDialog(null, image);
						}
					}

				}
				// add to tableau
				else if (p.getY() > 113 && p.getY() < 400) {

					if (p.getX() <= 83) {
						if (game.getTableau(1).addCard(sr)) {
							game.getTableau(tabl).removeCard();
							tabl = 1;
							this.card.setBounds(10, 120 + game.getTableau(1).size() * 20 - 20, 73, 97);

						} else {
							this.card.setBounds(re.x, re.y, 73, 97);
							JOptionPane.showMessageDialog(null, image);
						}

					}

					else if (p.getX() <= 166) {
						if (game.getTableau(2).addCard(sr)) {
							game.getTableau(tabl).removeCard();
							tabl = 2;
							this.card.setBounds(93, 120 + game.getTableau(2).size() * 20 - 20, 73, 97);

						} else {
							this.card.setBounds(re.x, re.y, 73, 97);
							JOptionPane.showMessageDialog(null, image);
						}

					}

					else if (p.getX() <= 249) {
						if (game.getTableau(3).addCard(sr)) {
							game.getTableau(tabl).removeCard();
							tabl = 3;
							this.card.setBounds(176, 120 + game.getTableau(3).size() * 20 - 20, 73, 97);

						} else {
							this.card.setBounds(re.x, re.y, 73, 97);
							JOptionPane.showMessageDialog(null, image);
						}

					}

					else if (p.getX() <= 332) {
						if (game.getTableau(4).addCard(sr)) {
							game.getTableau(tabl).removeCard();
							tabl = 4;
							this.card.setBounds(259, 120 + game.getTableau(4).size() * 20 - 20, 73, 97);

						} else {
							this.card.setBounds(re.x, re.y, 73, 97);
							JOptionPane.showMessageDialog(null, image);
						}

					}

					else if (p.getX() <= 415) {
						if (game.getTableau(5).addCard(sr)) {
							game.getTableau(tabl).removeCard();
							tabl = 5;
							this.card.setBounds(342, 120 + game.getTableau(5).size() * 20 - 20, 73, 97);

						} else {
							this.card.setBounds(re.x, re.y, 73, 97);
							JOptionPane.showMessageDialog(null, image);
						}

					}

					else if (p.getX() <= 498) {
						if (game.getTableau(6).addCard(sr)) {
							game.getTableau(tabl).removeCard();
							tabl = 6;
							this.card.setBounds(425, 120 + game.getTableau(6).size() * 20 - 20, 73, 97);

						} else {
							this.card.setBounds(re.x, re.y, 73, 97);
							JOptionPane.showMessageDialog(null, image);
						}

					}

					else if (p.getX() <= 581) {
						if (game.getTableau(7).addCard(sr)) {
							game.getTableau(tabl).removeCard();
							tabl = 7;
							this.card.setBounds(508, 120 + game.getTableau(7).size() * 20 - 20, 73, 97);

						} else {
							this.card.setBounds(re.x, re.y, 73, 97);
							JOptionPane.showMessageDialog(null, image);
						}

					}

					else if (p.getX() <= 700) {
						if (game.getTableau(8).addCard(sr)) {
							game.getTableau(tabl).removeCard();
							tabl = 8;
							this.card.setBounds(591, 120 + game.getTableau(8).size() * 20 - 20, 73, 97);

						} else {
							this.card.setBounds(re.x, re.y, 73, 97);
							JOptionPane.showMessageDialog(null, image);
						}

					}

				}

				else {

					this.card.setBounds(re.x, re.y, 73, 97);

				}
			} else {
				card.setBounds(re.x, re.y, 73, 97);
			}

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// when the mouse entered a card can be moved, 
			// it will turn to a hand
			if (pile == "freecell" ||pile == "tableau" && sr == game.getTableau(tabl).getLastCard()) {
				card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

		}

		@Override
		public void mouseExited(MouseEvent e) {
			//when mouse exited the card, 
			// the mouse will be the default one
			card.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			//When the mouse dragged, the card will move with mouse
			// Only when the card is in freecell or it is the last card of a tableau pile
			Point p = this.card.getLocation();

			if (pile == "freecell" || pile == "tableau" && sr == game.getTableau(tabl).getLastCard()) {
				lay.moveToFront(card);
				card.setLocation(p.x + (e.getX() - origin.x), p.y + (e.getY() - origin.y));

			}

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}

}
