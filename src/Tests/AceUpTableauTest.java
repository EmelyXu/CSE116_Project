package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import AceUp.AceTableau;
import AceUp.AceUp;
import edu.buffalo.cse116.Card;

public class AceUpTableauTest {
	
	/**
	 * 
	 */
	@Test
	public void Tableauinital() {
		//Tableau piles in Ace's Up initially hold 1 card [2 points]
		AceTableau tab = new AceTableau();
		Card card1 = new Card("spade","A");
		tab.addstartingCard(card1);
		assertEquals("AceUp started with 1 card.", 1, tab.size());
	}
	
	/**
	 * 
	 */
	@Test
	public void TableauCanAdd() {
		//Ace's Up tableau pile method correctly determines if adding a specific card is legal or illegal.
		//To receive points, your test cannot call two separate methods 
		//(e.g., one for when the card comes from the stock pile and one for when the card comes from another tableau) 
		//to perform this check. [4 points]
		AceUp tab = new AceUp();
		tab.addFourCards();
		assertEquals("adding a specific card is legal when card came from"
				+ " stock pile",1,tab.getTableau(1).size());
		assertEquals("adding a specific card is legal when card came from"
				+ " stock pile",1,tab.getTableau(2).size());
		assertEquals("adding a specific card is legal when card came from"
				+ " stock pile",1,tab.getTableau(3).size());
		assertEquals("adding a specific card is legal when card came from"
				+ " stock pile",1,tab.getTableau(0).size());
		
		tab.getTableau(0).removeTopCard();
		Card card = tab.getTableau(1).getTopCard();
		tab.getTableau(1).removeTopCard();
		
		assertTrue("adding a specific card is legal when card came from "
				+ "another tableau pile",tab.getTableau(tab.checkPileEmptyNum()).addCard(card));
	}
	
	/**
	 * 
	 */
	@Test
	public void TableauRemovable() {
		
		//Ace's Up tableau pile method correctly determines if removing a specific card is legal or illegal. 
		//To receive points, your test cannot call two separate methods
		//(e.g., one for when the card is being moved to the homecell pile and one for when the card is moved to another tableau pile) 
		//to perform this check. [4 points]
		
		AceUp tab = new AceUp();
		
		Card card1 = new Card("heart", "A");
		Card card2 = new Card("heart", "K");
		Card card3 = new Card("diamond", "3");
		
		tab.getTableau(0).addCard(card1);
		tab.getTableau(1).addCard(card2);
		
		assertTrue("removing a specific card to homecell is legal when game rule apply",
				tab.removable(card2));
		
		tab.getTableau(3).addCard(card3);
		tab.getTableau(2).addCard(card3);
		tab.getTableau(3).removeTopCard();
		
		assertEquals("removing a specific card to an empty tableau pile is legal,"
				+ "card from tableau pile 3 to tableau pile 2",tab.getTableau(2).getTopCard(),
				card3);
	}
	
	/**
	 * 
	 */
	@Test
	public void TopcardAfterAdd() {
		//Adding card to Ace's Up tableau pile results in that card 
		//being the tableau pile's new top card 
		
		AceTableau tab = new AceTableau();
		Card card1 = new Card("heart","K");
		Card card2 = new Card("spade","Q");
		tab.addCard(card1);
		tab.addCard(card2);
		assertEquals(card2,tab.getTopCard());
		
	}
	@Test
	public void SizeAfteradd() {
		//Adding card to Ace's Up tableau pile increases its number of cards
		
		AceTableau tab = new AceTableau();
		Card card = new Card("spade","A");
		tab.addstartingCard(card);
		Card card1 = new Card("heart","K");
		Card card2 = new Card("spade","Q");
		tab.addCard(card1);
		tab.addCard(card2);
		assertEquals(3,tab.size());
		
	}
	@Test
	public void SizeAndTopcardAfterRemove(){
		//Removing card from Ace's Up tableau pile decreases its number of cards 
		//and results in following card being the new top card [4 points]
        AceUp tab = new AceUp();
		
		Card card1 = new Card("heart", "A");
		Card card2 = new Card("heart", "K");
		Card card3 = new Card("diamond", "3");
		
		
		tab.getTableau(0).addCard(card1);
		tab.getTableau(1).addCard(card3);
		tab.getTableau(1).addCard(card2);
		
		tab.getTableau(1).removeTopCard();
		
		assertEquals("Removing card from Ace's Up tableau pile decreases its number of cards",1,tab.getTableau(1).size());
		assertEquals("Removing card from Ace's Up tableau pilere sults in following card being the new top card"
				,card3, tab.getTableau(1).getTopCard());
		
		
		
	}

}
