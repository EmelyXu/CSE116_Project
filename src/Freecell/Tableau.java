package Freecell;

import java.util.ArrayList;

import edu.buffalo.cse116.Card;


/**
 * This class is the 6 cards Tableau Pile rule, each pile initially hold 6 card. Card is only addable if it has 
 * the correct order of rank and an opposite color of suit as the card in the last index.
 */

public class Tableau extends Pile {
	
	/**
	   * initialize pile to be size 6
	   * 
	   */
	 ArrayList<Card> _card = new ArrayList<Card>(6);

	
	
	/**
	   * check if card is addable to the pile
	   * only cards with a opposite color of suit and with the correct order of rank can be added
	   * @param card that used to check if it is legal to add or not
	   * @return the card is legal to add or not
	   */
	//The only case that returns true
	//(放进去的卡要比原本组里的低一个rank，要不一样的颜色【红黑】） 
	// the card we put into the list should be one rank lower than the previous card and in different color (red and black)
	@Override
	public boolean cardAddable(Card para){
		boolean result = false;
		if(_card.size() == 0){
			
		}
		else{
		int rank = para.getValueOfRank(para.getRank());
		
		Card cardToCompare = _card.get(_card.size()-1);
		
		int rankOfCardToCompare = cardToCompare.getValueOfRank(cardToCompare.getRank());
		String suit = para.getColor();
		String suitOfCardToCompare = cardToCompare.getColor();
		if(rank==rankOfCardToCompare-1){
				if(suit.equals("diamond") || suit.equals("heart")){
					if(suitOfCardToCompare.equals("spade") || suitOfCardToCompare.equals("club")){
						result = true;
					}
					else{
						result = false;
					}
				}
				else{
					if(suitOfCardToCompare.equals("diamond") || suitOfCardToCompare.equals("heart")){
						result = true;
					}
					else{
						result = false;
					}
				}
			}
			else{
				result = false;
			}
		}

		return result;
	}
	
	public boolean addCard(Card para){
		if(cardAddable(para)){
			_card.add(para);
			return true;
		}
		else{
			return false;
		}	
	
	}
	
	public Card removeCard(){
		Card re = _card.get(_card.size() -1);
		_card.remove(re);
		return re;
	}
	/**
	   * add a starting Card in Tableau (should be used in Game class to make sure the cards are all from the same deck)
	   */
	
	
	public void addStartingCard(Card a){
		_card.add(a);
		
	}
	

	/**
	 * this method return an arraylist contains all cards of this tableau
	   * @return the arraylist contains all cards of this tableau
	   */
	public ArrayList<Card> getTableau(){
		return _card;
	}
	
	/**
	   * This method return a string of location of the card at index i
	   * @param i the index of the card
	   * @return the string of location of the card at index i
	   */
	
	public String getCardImage(int i){
		
		return "Images/cards/" + _card.get(i).getColor() +" "+ _card.get(i).getRank()+".gif";
	}
	
	/**
	  * This method return the card at index i
	  * @return the card at index i
	  */
	public Card getCard(int i){
		return _card.get(i);
	}
	
	/**
	   * @return the size of tableau
	   */
	
	public int size(){
		return _card.size();
	}
	
	/**
	   * @return the card at the end of the tableau
	   */
	public Card getLastCard(){
		return _card.get(_card.size()-1);
	}


}

