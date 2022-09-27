package Freecell;

import java.util.ArrayList;

/**
 * This class setups the piles rule, including pile size, add and remove cards.
 */

import edu.buffalo.cse116.Card;
import edu.buffalo.cse116.Deck;

public class Pile extends Deck {
	
	/**
	   * number Cards the pile initially hold
	   */
	ArrayList<Card> _card = new ArrayList<Card>();
	
	
	/**
	   * check if card is addable to the pile
	   * @param card that used to check if it is legal to add or not
	   */
	public boolean cardAddable(Card para){
		return true;
	}
	
	/**
	   * add a card in without following rules
	   * @param card to be added to the pile
	   */
	// Force add card (God Mode)
	public void cardAddWithoutRule(Card para){
		_card.add(para);
	}
	
	/**
	   * card adds to a pile
	   * @param card that will use to add to a pile
	   * @return the card is legal to add or not
	   */
	public boolean addCard(Card para){
		if(cardAddable(para)){
			_card.add(para);
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	   * check if a card is removable from the pile
	   * @return card removable
	   */
	public boolean cardRemoveable(){
		return true;
	}
	
	/**
	   * card removes from a pile
	   * @return card been removed
	   */
	public Card removeCard(){
		Card temp = null;
		if(cardRemoveable()){
			int lastCardIndex = size()-1;
			_card.remove(lastCardIndex);
			temp = _card.get(size()-1);
		}
		return temp;
	}
	
	/**
	   * number of card in _card currently
	   * @return number of card in _card currently
	   */
	public int size(){
		return _card.size();
	}
	
	/**
	   * This method gets the card stored in the last index
	   * @return the card stored in the last index
	   */
	public Card getLastIndex(){
		return _card.get(size()-1);
	}
	
	/**
	   * initially add cards to the pile
	   */
	public void kingToTheBack(){
		for(Card c : _card){
			if(c.getValueOfRank(c.getRank())==13){
				_card.remove(c);
				_card.add(0, c);
			}
		}
	}

}
