package BakersDozen;

import java.util.ArrayList;
import edu.buffalo.cse116.Card;
import edu.buffalo.cse116.Deck;

/**
 * This class creates the array of array lists and can add and remove cards.
 * 
 * @author eldermejia
 *
 */
public class BakersDozen extends Deck {

	private ArrayList<Card>[] cardList;

	private ArrayList<Card>[] homecellCardList;

	private String[] cardName;

	private boolean[] pileEmpty;

	/**
	 * This constructor creates the array of array lists.
	 */
	@SuppressWarnings("unchecked")
	public BakersDozen() {
		cardName = new String[52];
		pileEmpty = new boolean[13];

		// Creates card lists (array of card lists)
		cardList = new ArrayList[13];
		homecellCardList = new ArrayList[4];

		// Instantiates (assigns) array into a new arraylist
		for (int i = 0; i < cardList.length; i++) {
			cardList[i] = new ArrayList<Card>();
		}

		for (int i = 0; i < homecellCardList.length; i++) {
			homecellCardList[i] = new ArrayList<Card>();
		}

		addStartingCards();

		for (int u = 0; u < cardList.length; u++) {
			for (int w = 0; w < cardList[u].size(); w++) {
				if (cardList[u].get(w).getRank() == "K") {
					Card temp = cardList[u].get(w);
					cardList[u].remove(w);
					cardList[u].add(0, temp);
				}
			}
		}

		for (int i = 0; i < getAllTableauCardList().size(); i++) {
			String suit = getAllTableauCardList().get(i).getColor();
			String number = getAllTableauCardList().get(i).getRank();
			cardName[i] = "Images/cards/" + suit + " " + number + ".gif";
		}

		for (int i = 0; i < 13; i++) {
			pileEmpty[i] = false;
		}

	}

	public void setTableauListEmpty(int index) {
		cardList[index] = null;
		pileEmpty[index] = true;
	}

	public boolean getPileEmpty(int index) {
		return pileEmpty[index];
	}

	/**
	 * This method just returns the list of cards at a specific pile.
	 * 
	 * @param index
	 *            The tableau pile which you wish to use.
	 * @return This method returns an array list of the cards at specific index.
	 */
	public ArrayList<Card> getTableauList(int index) {
		return cardList[index];
	}

	public ArrayList<Card> getAllTableauCardList() {
		ArrayList<Card> retVal = new ArrayList<Card>();

		for (int i = 0; i < cardList.length; i++) {
			for (int x = 0; x < getTableauSize(i); x++)
				retVal.add(cardList[i].get(x));
		}

		return retVal;
	}

	/**
	 * This method returns the last card in the list of a specific pile.
	 * 
	 * @param index
	 *            The tableau pile which you wish to use.
	 * @return The card at the end of the list.
	 */
	public Card getTableauLastCard(int index) {
		return cardList[index].get(getTableauSize(index) - 1);
	}

	/**
	 * This method returns the amount of cards in the list of a specific pile.
	 * 
	 * @param index
	 *            The tableau pile which you wish to use.
	 * @return The number of cards in the pile.
	 */
	public int getTableauSize(int index) {
		return getTableauList(index).size();
	}

	/**
	 * This method adds a card to a specific pile.
	 * 
	 * @index The tableau pile which you wish to use.
	 * @return Whether or not it was able to add a card.
	 */
	public boolean tableauAddCard(Card para, int index) {
		// GameRules call = new GameRules();
		if (cardAddable(para, index)) {
			cardList[index].add((getTableauSize(index)), para);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method check if you can add a card to the tableau pile.
	 * 
	 * @param para
	 *            The card that is being added.
	 * @index The tableau pile which you wish to use.
	 * @return It will return true if you can add the card and false otherwise.
	 */
	// Tableau Pile ( only adds card only based on ranking, suit does not matter)
	public boolean cardAddable(Card para, int index) {
		int rank = para.getValueOfRank(para.getRank());
		if ((getTableauSize(index) != 0)) {
			Card cardToCompare = getTableauLastCard(index);
			int rankOfCardToCompare = cardToCompare.getValueOfRank(cardToCompare.getRank());
			if (rank < rankOfCardToCompare && rank > (rankOfCardToCompare - 2)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	/**
	 * This method return the card list at a specific pile.
	 * 
	 * @param index
	 *            The homecell pile which you wish to use.
	 * @return The list of cards in the pile.
	 */
	public ArrayList<Card> getHomecellList(int index) {
		return homecellCardList[index];
	}

	/**
	 * This method return the last card in the homecell pile.
	 * 
	 * @param index
	 *            The homecell pile which you wish to use.
	 * @return The card at the end of the list.
	 */
	public Card getHomecellLastCard(int index) {
		Card retVal = homecellCardList[index].get(getHomecellSize(index) - 1);
		return retVal;
	}

	/**
	 * This method returns the number of cards in a homecell pile.
	 * 
	 * @param index
	 *            The homecell pile which you wish to use.
	 * @return The number of acrds in a pile.
	 */
	public int getHomecellSize(int index) {
		return homecellCardList[index].size();
	}

	/**
	 * This method adds a card to a homecell pile.
	 * 
	 * @param para
	 *            The card to be added to the homecell pile.
	 * @param homecellNum
	 *            The specific homecell pile to which the card will be added to.
	 * @return This method will return true if it added the card and false
	 *         otherwise.
	 */
	// Adds card to Homecell pile based on suit and ranking
	// ex:
	public boolean addCardHomecell(Card para, int index) {
		if (checkCardAddableHomecell(para, index)) {
			if (para.getValueOfRank(para.getRank()) == 1) {
				homecellCardList[index].add((getHomecellSize(index)), para);
				return true;
			} else {
				homecellCardList[index].add((getHomecellSize(index)), para);
				return true;
			}
		} else {
			return false;
		}
	}

	/**
	 * This method removes a card from the tableau pile.
	 * 
	 * @index The homecell pile which you wish to use.
	 * @return Whether the card was removed or not.
	 */
	public boolean removeTableauCard(int index) {

		if (cardRemoveableTableau()) {
			cardList[index].remove(getTableauSize(index) - 1);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method adds a card to a specific pile.
	 * 
	 * @index The tableau pile which you wish to use.
	 * @return Whether or not it was able to add a card.
	 */
	// only for testing purposes
	// ignores all rules
	public boolean forceAddCard(Card para, int index) {
		cardList[index].add((getTableauSize(index)), para);
		return true;
	}

	public void forceRemoveHomecell(int index) {
		if (getHomecellSize(index) != 0) {
			homecellCardList[index].remove(getHomecellSize(index) - 1);
		}
	}

	/**
	 * This method checks if the Tableau pile is empty.
	 *
	 * @return It will return true if it is empty and false otherwise.
	 */
	public boolean checkTableauEmpty(int index) {

		if (cardList[index].size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method checks if the homecell pile is empty.
	 * 
	 * @return It will return true if it is empty and false otherwise.
	 */
	public boolean checkHomecellEmpty(int index) {

		if (homecellCardList[index].size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method check if you can add a card to the homecell pile.
	 * 
	 * @param para
	 *            The card that is being added.
	 * @index The homecell pile which you wish to use.
	 * @return It will return true if you can add the card and false otherwise.
	 */
	// Adds card based on ranking and suit
	public boolean checkCardAddableHomecell(Card para, int index) {
		String suit = para.getColor();
		int rank = para.getValueOfRank(para.getRank());
		if (checkHomecellEmpty(index)) {
			if (rank == 1) {
				return true;
			} else {
				return false;
			}
		} else {
			Card homecellCard = getHomecellLastCard(index);
			if (homecellCard.getColor().equals(suit)
					&& homecellCard.getValueOfRank(homecellCard.getRank()) == rank - 1) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * This method checks if you can remove a card from the homecell pile.
	 * 
	 * @return It will return false because once a card is added to homecell it
	 *         can't be moved.
	 */
	public boolean cardRemoveableHomecell() {
		return false;
	}

	/**
	 * This method checks if you can remove a card from the tableau pile.
	 * 
	 * @return It will return true.
	 */
	public boolean cardRemoveableTableau() {
		return true;
	}

	/**
	 * This method adds the cards to the array of array lists.
	 * 
	 */
	// Why QAQ
	// adds card from deck class and shuffles, and then puts into an arraylist
	// cardlist.length = 13
	public void addStartingCards() {
		Deck deck = new Deck();
		deck.shuffleCards();
		ArrayList<Card> deckList = deck.getCardList();

		for (int i = 0; i < cardList.length; i++) {
			switch (i) {

			case 0:
				for (int x = 0; x < 4; x++) {
					cardList[i].add(deckList.get(x));
				}

				break;
			case 1:
				for (int x = 4; x < 8; x++) {
					cardList[i].add(deckList.get(x));
				}

				break;

			case 2:
				for (int x = 8; x < 12; x++) {
					cardList[i].add(deckList.get(x));
				}

				break;

			case 3:
				for (int x = 12; x < 16; x++) {
					cardList[i].add(deckList.get(x));
				}

				break;

			case 4:
				for (int x = 16; x < 20; x++) {
					cardList[i].add(deckList.get(x));
				}

				break;

			case 5:
				for (int x = 20; x < 24; x++) {
					cardList[i].add(deckList.get(x));
				}

				break;

			case 6:
				for (int x = 24; x < 28; x++) {
					cardList[i].add(deckList.get(x));
				}

				break;

			case 7:
				for (int x = 28; x < 32; x++) {
					cardList[i].add(deckList.get(x));
				}

				break;

			case 8:
				for (int x = 32; x < 36; x++) {
					cardList[i].add(deckList.get(x));
				}

				break;

			case 9:
				for (int x = 36; x < 40; x++) {
					cardList[i].add(deckList.get(x));
				}

				break;

			case 10:
				for (int x = 40; x < 44; x++) {
					cardList[i].add(deckList.get(x));
				}

				break;

			case 11:
				for (int x = 44; x < 48; x++) {
					cardList[i].add(deckList.get(x));
				}
				break;

			case 12:
				for (int x = 48; x < 52; x++) {
					cardList[i].add(deckList.get(x));
				}
				break;
			}
		}
	}

	public String getPath(int param) {
		String retVal = "";

		retVal = cardName[param];

		return retVal;

	}

}
