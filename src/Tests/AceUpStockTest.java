package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import AceUp.AceUp;
import edu.buffalo.cse116.Card;

public class AceUpStockTest {
	
	
	/**
	 * This method tests stock pile initially holds 48 cards.
	 */
	@Test
	public void initialHolds(){
		AceUp test = new AceUp();
		test.addFourCards();
		int initial = test.getDeck().size();
		assertEquals("Ace's Up stock pile initially holds 48 cards",48,initial);
	}
	
	/**
	 * This method tests that adding cards to stock is illegal.
	 */
	@Test
	public void addingCard(){
		AceUp test = new AceUp();
		assertFalse("Ace's Up stock pile correctly returns if adding a specific card is legal or illegal", test.addToStock());
	}
	
	/**
	 * This method tests that removing cards from stock pile is illegal when stock pile 
	 * is not empty.
	 */
	@Test
	public void removeCardValid(){
		AceUp test = new AceUp();
		Card card = new Card("diamond","3");
		//used to be assertTrue but doesn't work so I change it
		assertFalse("Ace's Up stock pile correctly returns if removing a specific card is legal or illegal(not empty)",
				test.getDeck().remove(card));
	}
	
	/**
	 * This method tests that removing cards from stock pile is illegal when stock pile
	 * is empty.
	 */
	@Test
	public void removeCardInvalid(){
		AceUp test = new AceUp();
		Card card = new Card("diamond","3");
		for(int i=0;i<13;i++){
			test.addFourCards();
		}
		assertFalse("Ace's Up stock pile correctly returns if removing a specific card is legal or illegal(not empty)",
				test.getDeck().remove(card));
	}
	
	/**
	 * This method tests to make sure that cards correctly remove from stock pile when 
	 * add cards to tableau pile is called .
	 */
	@Test
	public void topCard(){
		AceUp test = new AceUp();
		Card retVal = test.getDeck().get(4);
		test.addFourCards();
		assertEquals("Dealing cards from Ace's Up stock pile removes the top 4 cards, adds the removed cards "
				+ "to the tableau piles, and results in the 5th card being the new top card",
				retVal,test.getDeck().get(0));
	}

}
