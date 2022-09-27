package Freecell;

import java.util.ArrayList;

import edu.buffalo.cse116.Card;

/**
 * This class is the Homecell rule, each pile initially holds no card, card can be added if the it has the same
 * suit and in correct order of rank. Cards are not removable for this pile.
 */
public class Homecell extends Pile {
	
	/**
	   * number Cards the pile initially hold
	   */
	ArrayList<Card> _card = new ArrayList<Card>();
	
	/**
	   * check if card addable to the pile
	   * only card with the same suit and is in correct order of rank can be added
	   * @param card to be added to the pile
	   * @return the card is legal to add or not
	   */
	// if empty, can only add A (of any suit)
	// the rest of the cards needs to follow the color pattern (same suit, higher rank)
	@Override
	public boolean cardAddable(Card para){
		boolean result = false;
		String suit = para.getColor();
		int rank = para.getValueOfRank(para.getRank());
		if(_card.isEmpty()){
			if(para.getRank().equals("A")){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			if(_card.get(0).getColor().equals(suit) && _card.get(_card.size()-1).getValueOfRank(_card.get(_card.size()-1).getRank())==rank-1){
				result = true;
			}
			else{
				result = false;
			}
		}
		return result;
	}
	
	/**
	   * card adds to a pile
	   * @param card that will use to add to a pile
	   * @return the card is legal to add or not
	   */
	//adds card to the last index
	@Override
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
	   * This method gets the card stored in the last index
	   * @return the card stored in the last index
	   */
	@Override
	public Card getLastIndex(){
		return _card.get(_card.size()-1);
	}
	
	/**
	   * number of card in _card currently
	   * @return number of card in _card currently
	   */
	@Override
	public int size(){
		return _card.size();
	}
	
	/**
	   * cards been added to this pile are not removable
	   */
	@Override
	public boolean cardRemoveable(){
		return false;
	}

}
