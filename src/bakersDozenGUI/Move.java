package bakersDozenGUI;

import java.awt.Point;

import edu.buffalo.cse116.Card;

public class Move {

	private String labelNum;
	private Point location;
	private String action;
	private int newPile;
	private int prePile;
	private Card card;

	public Move(String labelNum,String action, Point location, int newPile, int prePile, Card card) {
		this.labelNum = labelNum;
		this.location = location;
		this.action = action;
		this.newPile = newPile;
		this.prePile = prePile;
		this.card = card;
	}

	public String getJlabelNum() {
		return labelNum;
	}

	public Point getLocation() {
		return location;
	}
	
	public String getAction() {
		
		return action;
	}
	
	public int getNewPile() {
		return newPile;
	}
	
	public int getPrePile() {
		return prePile;
	}
	
	public Card getCard() {
		return card;
	}

}
