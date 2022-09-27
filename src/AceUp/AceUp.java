package AceUp;

import java.util.ArrayList;

import edu.buffalo.cse116.Card;
import edu.buffalo.cse116.Deck;

public class AceUp {
/**
	* create 4 tableau for the AceUp game                    
	* 
*/
	public static AceTableau tableau0;
	public static AceTableau tableau1;
	public static AceTableau tableau2;
	public static AceTableau tableau3;
/**
	* create 1 AceUp homecell                       
	* 
*/
	public static AceHomecell homecell;
/**
	* create a deck and a cardlist to store the cards of deck                       
	* This work as stock 
*/
	Deck deck;
	ArrayList<Card> cardList;
/**
	* The constructor of AceUp                    
	* initialize everything
*/
	public AceUp() {
		tableau0 = new AceTableau();
		tableau1 = new AceTableau();
		tableau2 = new AceTableau();
		tableau3 = new AceTableau();

		homecell = new AceHomecell();

		deck = new Deck();
		deck.shuffleCards();
		cardList = deck.getCardList();
	}
/**
	* This method remove the top card from tableau                   
	* @param tab the tableau want to be edited
*/
	public void removeCard(int tab) {
		if (tab == 0) {
			tableau0.removeTopCard();
		} else if (tab == 1) {
			tableau1.removeTopCard();
		} else if (tab == 2) {
			tableau2.removeTopCard();
		} else if (tab == 3) {
			tableau3.removeTopCard();
		}
	}

/**
	* This method return a list contain the top cards of the 4 tableaus                       
	* @return the list contain the top cards of the 4 tableaus 
*/
	public ArrayList<Card> getTopCardList() {
		ArrayList<Card> list = new ArrayList<Card>();
		if (AceUp.tableau0.getTopCard() != null) {
			list.add(AceUp.tableau0.getTopCard());
		} else if (AceUp.tableau1.getTopCard() != null) {
			list.add(AceUp.tableau1.getTopCard());
		} else if (AceUp.tableau2.getTopCard() != null) {
			list.add(AceUp.tableau2.getTopCard());
		} else if (AceUp.tableau3.getTopCard() != null) {
			list.add(AceUp.tableau3.getTopCard());
		}

		return list;
	}

	/**
	 * This method check if the top card of the tableau is remove able (when compare
	 * to other tableau)
	 * 
	 * @return if it is remove able, return true; else, return false
	 */
	public boolean removable(Card card) {
		if (checkPiles()) {
			ArrayList<Card> list = getTopCardList();
			ArrayList<Integer> rankList = new ArrayList<Integer>();

			for (int y = 0; y < list.size(); y++) {
				rankList.add(list.get(y).getValueOfRank(list.get(y).getRank()));
			}
			
			if (card.getRank() == "A") {
				return false;
			}

			for (int x = 0; x < rankList.size(); x++) {
				if (rankList.get(x) == 1) {
					rankList.set(x, 14);
				}
			}

			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getColor() == card.getColor()) {
					if (rankList.get(i) > card.getValueOfRank(card.getRank())) {
						return true;
					}
				}
			}

			return false;
		} else {
			Card card1 = AceUp.tableau0.getTopCard();
			Card card2 = AceUp.tableau1.getTopCard();
			Card card3 = AceUp.tableau2.getTopCard();
			Card card4 = AceUp.tableau3.getTopCard();
			ArrayList<Card> list = new ArrayList<Card>();
			ArrayList<Integer> rankList = new ArrayList<Integer>();

			list.add(card1);
			list.add(card2);
			list.add(card3);
			list.add(card4);

			rankList.add(card1.getValueOfRank(card1.getRank()));
			rankList.add(card2.getValueOfRank(card2.getRank()));
			rankList.add(card3.getValueOfRank(card3.getRank()));
			rankList.add(card4.getValueOfRank(card4.getRank()));

			if (card.getRank() == "A") {
				return false;
			}

			for (int x = 0; x < rankList.size(); x++) {
				if (rankList.get(x) == 1) {
					rankList.set(x, 14);
				}
			}

			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getColor() == card.getColor()) {
					if (rankList.get(i) > card.getValueOfRank(card.getRank())) {
						return true;
					}
				}
			}

			return false;
		}

	}
/**
	* This method check if one of tableau is empty                  
	* @return true if one of the 4 tableau is empty
*/
	public boolean checkPiles() {
		boolean result = false;
		if (tableau0.size() == 0 || tableau1.size() == 0 || tableau2.size() == 0 || tableau3.size() == 0) {
			result = true;
		}

		return result;
	}
/**
	* check which tableau is empty                   
	* @return the int which respersent the tableau
*/
	public int checkPileEmptyNum() {
		if (tableau0.size() == 0) {
			return 0;
		} else if (tableau1.size() == 0) {
			return 1;
		} else if (tableau2.size() == 0) {
			return 2;
		} else if (tableau3.size() == 0) {
			return 3;
		}

		return 0;
	}
/**
	* This method add one card to each tableau from the cardlist(stock)                   
	* 
*/
	public void addFourCards() {
		tableau0.addCard(cardList.get(0));
		cardList.remove(0);
		tableau1.addCard(cardList.get(0));
		cardList.remove(0);
		tableau2.addCard(cardList.get(0));
		cardList.remove(0);
		tableau3.addCard(cardList.get(0));
		cardList.remove(0);
	}
/**
	* This method return the tableau                    
	* @param num repersent the number of tableau
	* @return the tableau
*/
	public AceTableau getTableau(int num) {
		if (num == 0) {
			return tableau0;
		} else if (num == 1) {
			return tableau1;
		} else if (num == 2) {
			return tableau2;
		} else if (num == 3) {
			return tableau3;
		} else {
			return null;
		}
	}
/**
	* This method return the cardlist of deck                    
	* @return the cardlist of deck
*/
	public ArrayList<Card> getDeck() {
		return deck.getCardList();
	}
/**
	* This method check if we can add a card to stock                   
	* @return true if we can add a card to stock    
	* (which is always false here)
*/
	public boolean addToStock(){
		return false;
	}
/**
	* This method return an array contains the location of
	* all the images of cards in stock                   
	* @return an array contains the location of
	* all the images of cards in stock   
*/
	public String[] getCardImage() {
		String[] retVal = new String[52];
		for (int i = 0; i < retVal.length; i++) {
			retVal[i] = "Images/cards/" + deck.getCardList().get(i).getColor() + " "
					+ deck.getCardList().get(i).getRank() + ".gif";
		}
		return retVal;
	}

}
