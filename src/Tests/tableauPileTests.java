package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import BakersDozen.BakersDozen;
import Freecell.Tableau;
import edu.buffalo.cse116.Card;

public class tableauPileTests {
	public int change;

	@Test
	/**
	 * This Junit Test the Tableau class for FreeCell and Bakers Dozen
	 */
	public void Freecell_Tableautest() {
		// The initial cards
		Card index0 = new Card("diamond", "2");
		Card index1 = new Card("spade", "6");
		Card index2 = new Card("club", "5");
		Card index3 = new Card("heart", "10");
		Card index4 = new Card("diamond", "6");
		Card index5 = new Card("spade", "3");
		// Cards to be added during the tests
		Card card1 = new Card("diamond", "2");
		Card card2 = new Card("heart", "3");
		// New instance of Tableau
		Tableau test = new Tableau();
		// Adds Initial Cards
		test.cardAddWithoutRule(index0);
		test.cardAddWithoutRule(index1);
		test.cardAddWithoutRule(index2);
		test.cardAddWithoutRule(index3);
		test.cardAddWithoutRule(index4);
		test.cardAddWithoutRule(index5);

		// Tableau piles in Freecell initially hold 6 or 7 cards [4 points]
		assertEquals("Tableau piles in Freecell initially hold 6 or 7 cards", 6, test.size());
		// Freecell tableau pile correctly determines if adding a specific card is legal
		// or illegal [4 points]
		assertTrue("Freecell tableau pile correctly determines if adding a specific card is legal",
				test.cardAddable(card1));
		assertFalse("Freecell tableau pile correctly determines if adding a specific card is illegal",
				test.cardAddable(card2));
		// Freecell tableau pile correctly returns if removing top card is legal or
		// illegal (e.g., if the tableau pile is NOT empty) [4 points]
		assertTrue("Freecell tableau pile correctly returns if removing top card is legal", test.cardRemoveable());
		// Adding card to Freecell tableau pile increases its number of cards and
		// results in that card being the tableau pile's new top card [4 points]
		test.addCard(card1);// Adds a card to the top
		assertEquals("Adding card to Freecell tableau pile increases its number of cards", 7, test.size());
		assertEquals("Adding card to Freecell tableau pile results in that card being the tableau pile's new top card",
				card1, test.getLastIndex());
		// Removing card from Freecell tableau pile decreases its number of cards and
		// results in following card being the new top card [4 points]
		test.removeCard();// removes the top card
		assertEquals("Removing card from Freecell tableau pile decreases its number of cards", 6, test.size());
		assertEquals("Removing card from Freecell tableau pile results in following card being the new top card",
				index5, test.getLastIndex());

	}

	@Test
	public void BakersDozenInitialTest() {
		// New Instance of BakerS Dozen
		BakersDozen test = new BakersDozen();
		test.addStartingCards();

		// Tableau piles in Baker's Dozen initially hold 4 cards [4 points]
		assertEquals("Tableau piles in BakersDozen initially hold 4 cards", 4, test.getTableauSize(0));

		assertEquals("Tableau piles in BakersDozen initially hold 4 cards", 4, test.getTableauSize(4));

		assertEquals("Tableau piles in BakersDozen initially hold 4 cards", 4, test.getTableauSize(12));
	}

	@Test
	public void BakersDozenRemoveTest() {
		// New Instance of BakerS Dozen
		BakersDozen test = new BakersDozen();

		// Baker's Dozen tableau pile correctly returns if removing top card is legal or
		// illegal (e.g., if the tableau pile is NOT empty) [4 points]
		assertTrue("BakersDozen tableau pile correctly returns if removing top card is legal",
				test.cardRemoveableTableau());
	}

	@Test
	public void BakersDozenAddTest() {
		BakersDozen test = new BakersDozen();

		// The initial cards
		Card index0 = new Card("diamond", "2");
		Card index1 = new Card("spade", "6");
		Card index2 = new Card("club", "10");
		Card index3 = new Card("heart", "5");
		Card index4 = new Card("diamond", "6");
		Card index5 = new Card("spade", "3");

		test.forceAddCard(index0, 0);
		test.forceAddCard(index1, 0);
		test.forceAddCard(index2, 0);
		test.forceAddCard(index3, 1);
		test.forceAddCard(index4, 1);
		test.forceAddCard(index5, 2);

		// Cards to be added
		Card card = new Card("heart", "9");

		assertEquals("Homecell Pile intiial numbers of cards", 3, test.getTableauSize(0));
		assertEquals("Adding card to Homecell tableau pile results in that card being the tableau pile's new top card",
				index2, test.getTableauLastCard(0));
		// Adding card to Baker's Dozen tableau pile increases its number of cards and
		// results in that card being the tableau pile's new top card [4 points]
		assertTrue("Freecell tableau pile correctly determines if adding a specific card is legal",
				test.tableauAddCard(card, 0));// Adds a card to the top of the deck
		assertEquals("Adding card to Homecell tableau pile increases its number of cards", 4, test.getTableauSize(0));
		assertEquals("Adding card to Homecell tableau pile results in that card being the tableau pile's new top card",
				card, test.getTableauLastCard(0));
		// Removing card from Baker's Dozen tableau pile decreases its number of cards
		// and results in following card being the new top card [4 points]
		assertEquals("Removing card from Homecell tableau pile decreases its number of cards", 2,
				test.getTableauSize(1));
		test.removeTableauCard(1);// removes the top Card
		assertEquals("Removing card from Homecell tableau pile decreases its number of cards", 1,
				test.getTableauSize(1));
		test.forceAddCard(index5, 1);
		assertEquals("Removing card from Homecell tableau pile decreases its number of cards", 2,
				test.getTableauSize(1));
		assertEquals("Removing card from Homecell tableau pile results in following card being the new top card",
				index5, test.getTableauLastCard(1));

	}

}
