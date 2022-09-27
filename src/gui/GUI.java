package gui;

import java.awt.CardLayout;

import java.awt.Color;
import java.awt.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import bakersDozenGUI.CreateCardsPanel;


public class GUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JFrame _window;
	JPanel firstPanel;
	CreateCardsPanel bdPanel;
	FreecellGUI fPanel;
	AcesUpGUI aPanel;
	JPanel panel;
	CardLayout cardLayout;

	JMenuBar menuBar;
	JMenu menu;

	public GUI() throws IOException {
		_window = new JFrame("Solitaire Game");
		JLayeredPane layer = new JLayeredPane();
		panel = new JPanel();
		cardLayout = new CardLayout();
		panel.setLayout(cardLayout);

		firstPanel = new JPanel();
		ImageIcon image = new ImageIcon("Images/solitaire logo2.png");
		JLabel label = new JLabel(image);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		firstPanel.add(label);
		egg m = new egg(label);
		label.addMouseListener(m);
		label.addMouseMotionListener(m);

		bdPanel = new CreateCardsPanel();
		fPanel = new FreecellGUI();
		aPanel = new AcesUpGUI();
		panel.add(firstPanel, "firstPanel");
		panel.add(bdPanel.getBDPanel(), "bdPanel");
		panel.add(fPanel.getFPanel(), "fPanel");
		panel.add(aPanel.getaPanel(), "aPanel");

		cardLayout.show(panel, "firstPanel");

		menuBar = new JMenuBar();
		menu = new JMenu("Menu");
		JMenuItem bdGame = new JMenuItem("Play Bakers Dozen");
		JMenuItem fGame = new JMenuItem("Play Freecell");
		JMenuItem aGame = new JMenuItem("Play Ace's Up");
		JMenuItem exit = new JMenuItem("Quit");

		JMenu background = new JMenu("Background");

		JMenuItem green = new JMenuItem("Green");
		JMenuItem red = new JMenuItem("Red");
		JMenuItem grey = new JMenuItem("Gray");
		JMenuItem white = new JMenuItem("White");
		JMenuItem black = new JMenuItem("Black");
		JButton b1 = new JButton();
		JButton b2 = new JButton();
		firstPanel.add(b1);
		b1.setSize(1000, 1000);
		b1.setVisible(true);
		b1.setBackground(Color.WHITE);
		b1.setForeground(Color.WHITE);
		b1.setBorderPainted(false);
		b1.setBounds(100, 100, 1000, 1000);
		b2.setBounds(100, 100, 20, 20);
		b2.setVisible(true);
		layer.add(b2);
		panel.add(layer);
		background.add(green);
		background.add(red);
		background.add(grey);
		background.add(white);
		background.add(black);

		menu.add(bdGame);
		menu.add(fGame);
		menu.add(aGame);
		menu.add(background);
		menu.add(exit);
		menuBar.add(menu);

		black.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				firstPanel.setBackground(Color.black);
				bdPanel.getBDPanel().setBackground(Color.black);
				fPanel.getFPanel().setBackground(Color.black);
				aPanel.getaPanel().setBackground(Color.black);
			}
		});
		green.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				firstPanel.setBackground(Color.green);
				bdPanel.getBDPanel().setBackground(Color.green);
				fPanel.getFPanel().setBackground(Color.green);
				aPanel.getaPanel().setBackground(Color.green);
			}
		});
		red.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				firstPanel.setBackground(Color.red);
				bdPanel.getBDPanel().setBackground(Color.red);
				fPanel.getFPanel().setBackground(Color.red);
				aPanel.getaPanel().setBackground(Color.red);
			}
		});
		grey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				firstPanel.setBackground(Color.gray);
				bdPanel.getBDPanel().setBackground(Color.gray);
				fPanel.getFPanel().setBackground(Color.gray);
				aPanel.getaPanel().setBackground(Color.gray);
			}
		});
		white.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				firstPanel.setBackground(Color.white);
				bdPanel.getBDPanel().setBackground(Color.white);
				fPanel.getFPanel().setBackground(Color.white);
				aPanel.getaPanel().setBackground(Color.white);

			}
		});
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {

				int random1 = (int) (Math.random() * 255);
				int random2 = (int) (Math.random() * 255);
				int random3 = (int) (Math.random() * 255);
				Color ran = new Color(random1, random2, random3);
				b1.setBackground(ran);
				b1.setForeground(ran);
				firstPanel.setBackground(ran);
				bdPanel.getBDPanel().setBackground(ran);
				fPanel.getFPanel().setBackground(ran);
			}
		});
		bdGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				bdPanel = new CreateCardsPanel();
				panel.add(bdPanel.getBDPanel(), "bdPanel");
				bdPanel.getBDPanel().setBackground(firstPanel.getBackground());
				cardLayout.show(panel, "bdPanel");
			}
		});
		aGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				aPanel = new AcesUpGUI();
				panel.add(aPanel.getaPanel(), "aPanel");
				aPanel.getaPanel().setBackground(firstPanel.getBackground());
				cardLayout.show(panel, "aPanel");
			}
		});
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		fGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fPanel = new FreecellGUI();
				panel.add(fPanel.getFPanel(), "fPanel");
				fPanel.getFPanel().setBackground(firstPanel.getBackground());
				cardLayout.show(panel, "fPanel");

			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				bdPanel = new CreateCardsPanel();
				panel.add(bdPanel.getBDPanel(), "bdPanel");
				bdPanel.getBDPanel().setBackground(firstPanel.getBackground());
				cardLayout.show(panel, "bdPanel");
			}
		});
		_window.add(panel);
		_window.setJMenuBar(menuBar);
		_window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		_window.setVisible(true);
		_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
