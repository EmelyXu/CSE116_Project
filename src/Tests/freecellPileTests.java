package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Freecell.Freecell;
import edu.buffalo.cse116.Card;

public class freecellPileTests {
	
	/**
	 * This test is to check if the freecell pile is empty in the beggining.
	 */
	@Test
	public void freecellPileBeginHoldingZeroTest() {
		Freecell call = new Freecell();
		assertEquals("Freecell pile did not start empty.", call.getCardSize(), 0);
	}
	
	/**
	 * This test is to check if it lets me add a card when the pile is empty.
	 */
	@Test
	public void freecellPileCanAddCardTest() {
		Freecell call = new Freecell();
		Card testCard = new Card("diamond", String.valueOf(4));
		assertTrue("The card wasn't able to be added and method returned false.", call.cardAddable(testCard));
	}
	
	/**
	 * This test is to check if  I can remove a card from the pile only when its empty.
	 */
	@Test
	public void freecellPileCanRemoveCardTest() {
		Freecell call = new Freecell();
		assertFalse("The pile must be empty so it returns false and nothing can be removed", call.cardRemoveable());
	}
	
	/**
	 * This test is to see if added the card and if it is the one on top in the pile.
	 */
	@Test
	public void freecellPileChangesTopCardTest() {
		Freecell call = new Freecell();
		Card testCard = new Card("diamond", String.valueOf(4));
		assertTrue(call.addCard(testCard));
	}

}
