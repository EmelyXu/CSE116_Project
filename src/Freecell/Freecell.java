package Freecell;

import java.util.ArrayList;

import edu.buffalo.cse116.Card;


/**
 * This class is the Freecell pile rule, initially hold no card, however, card can be added if the pile is
 * currently empty.
 */
public class Freecell extends Pile{
	
	/**
	   * number Cards the pile initially hold
	   */
	ArrayList<Card> _card = new ArrayList<Card>();
	
	public int getCardSize() {
		return _card.size();
	}
	
	
	public boolean removCard(){
		if(!_card.isEmpty()){
			_card.remove(0);
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	   * check if card addable to this pile
	   * if card pile is empty, card can be added
	   * only one card can be added to this pile
	   * @param card to be added to the pile
	   * @return the card is legal to add or not
	   */
	//only holds one card
		//if empty return true; else return false;
	@Override
	public boolean addCard(Card para){
		if(_card.isEmpty()){
			_card.add(para);
			return true;
		}
		else{
			return false;
		}
	}
	
	public Card storedCard(){
		return _card.get(0);
	}
	
	

}
