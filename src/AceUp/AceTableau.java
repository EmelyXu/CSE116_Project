package AceUp;

import java.util.ArrayList;

import edu.buffalo.cse116.Card;


public class AceTableau {
	
/**	
	* This ArrayList store the cards of tableau                        
*/
	ArrayList<Card> tab;

/**
	* Constructor of AceTableau
	* create a new Arraylist for tableau                       
	* 
*/
	public AceTableau() {
		tab = new ArrayList<Card>();
	}
	
/**
	* This method remove the top card of tableau                      
	* 
*/
	public void removeTopCard() {
		tab.remove(tab.size() - 1);
	}
	
/**
	* This method add a starting card to tableau
	* @param card the card to be added                       
	* 
*/
	public void addstartingCard(Card card) {
		tab.add(card);
	}

/**
	* get the top card of tableau
	* @return the card on top of tableau                      
	* 
*/
	public Card getTopCard() {
		if (size() == 0) {
			return null;
		} else {
			return tab.get(tab.size() - 1);
		}
	}
	
/**
	* This method add card to tableau                     
	* 
*/
	public boolean addCard(Card card) {
		tab.add(card);
		return true;
	}
/**
	* This method return the size of tableau
	* @return size of tableau                      
	* 
*/
	public int size() {
		return tab.size();
	}

	public ArrayList<Card> getCardList() {
		return tab;
	}

}
