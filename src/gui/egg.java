package gui;

import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.event.MouseInputListener;

/**
 * @author Emely Xu <emelyxu @ buffalo.edu>
 *
 */
class egg implements MouseInputListener {
	// This class is a mouse listener that will change the color of the logo
	// Works as the easter egg for this project
	JLabel card;
	
	/**
	 * This method would set the JLabel equal to the paramater
	 * @param a is the JLabel need this MouseListener
	 */
	public egg(JLabel a) {
		this.card = a;
	
	}

	//set a random number from 0 to 6 to get 7 different picture of logo
	//when mouse clicked, the image changed
	@Override
	public void mouseClicked(MouseEvent e) {
		
		Random r = new Random();
		
		this.card.setIcon(new ImageIcon("Images/solitaire logo" + r.nextInt(7) + ".png"));
		
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

