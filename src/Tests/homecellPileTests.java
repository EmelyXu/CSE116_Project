package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import BakersDozen.BakersDozen;
import Freecell.Homecell;
import edu.buffalo.cse116.Card;

public class homecellPileTests {

	@Test

	public void Freecell_Homecell_InitalZero() {

		Homecell test = new Homecell();

		// Homecell piles in Freecell initially hold 0 cards [4 points]

		assertEquals("Homecell piles initially holds 0 card in Freecell", 0, test.size());
	}

	@Test
	public void Freecell_Homecell_TsetAdding() {

		Homecell test = new Homecell();
		Card card1 = new Card("spade", "A");
		Card card2 = new Card("spade", "2");
		Card card3 = new Card("club", "A");
		// Freecell homecell pile correctly determines if adding a specific card is
		// legal or illegal [4 points]

		assertTrue("Freecell homecell pile correctly determines if adding a specific card is legal",
				test.cardAddable(card1));

		test.addCard(card1);

		assertTrue("Freecell homecell pile correctly determines if adding a specific card is legal",
				test.cardAddable(card2));

		assertFalse("Freecell homecell pile correctly determines if adding a specific card is illegal",
				test.cardAddable(card3));

	}

	@Test
	public void Freecell_Homecell_TsetRemoveToping() {

		Homecell test = new Homecell();

		// Freecell homecell pile correctly returns if
		// removing top card is legal or illegal (e.g., always false) [4 points]
		assertFalse("Freecell homecell pile correctly returns if removing top card is legal", test.cardRemoveable());
	}

	@Test
	public void Freecell_Homecell_TsetTopCardandSzie() {

		// Adding card to Freecell homecell pile increases its number of cards and
		// results in that card being the homecell pile's new top card [4 points]
		Homecell test = new Homecell();
		Card card1 = new Card("spade", "A");
		Card card2 = new Card("spade", "2");

		test.addCard(card1);
		test.addCard(card2);
		Card top = test.getLastIndex();
		assertEquals(2, test.size());
		assertEquals(card2, top);

	}

	@Test

	public void BakersDozen_InitalZero() {
		// Homecell piles in Baker's Dozen initially hold 0 cards [4 points]

		BakersDozen test = new BakersDozen();

		assertEquals("Homecell piles in Baker's Dozen initially hold 0 cards", 0, test.getHomecellSize(0));

		assertEquals("Homecell piles in Baker's Dozen initially hold 0 cards", 0, test.getHomecellSize(3));
	}

	@Test
	public void BakersDozen_TestAdding() {

		BakersDozen test = new BakersDozen();

		Card card1 = new Card("spade", "A");
		Card card2 = new Card("spade", "2");
		Card card3 = new Card("club", "A");

		// Baker's Dozen homecell pile correctly determines if adding a specific card is
		// legal or illegal [4 points]

		assertTrue(
				"Baker's Dozen homecell pile correctly determines if adding " + "a specific card is legal or illegal",
				test.addCardHomecell(card1, 0));
		assertFalse(
				"Baker's Dozen homecell pile correctly determines if adding " + "a specific card is legal or illegal",
				test.addCardHomecell(card2, 1));
		assertTrue(
				"Baker's Dozen homecell pile correctly determines if adding" + " a specific card is legal or illegal",
				test.addCardHomecell(card3, 2));
	}

	@Test
	public void BakersDozen_TestMoving() {
		// BakersDozen_TestRemoving()

		BakersDozen test = new BakersDozen();

		// Baker's Dozen homecell pile correctly returns if removing top card is
		// legal or illegal (e.g., always false) [4 points]
		assertFalse("Baker's Dozen homecell pile correctly returns if removing " + "top card is legal or illegal",
				test.cardRemoveableHomecell());
	}

	@Test
	public void BakersDozen_TopandSize() {
		BakersDozen test = new BakersDozen();

		Card card1 = new Card("spade", "A");

		// Adding card to Baker's Dozen homecell pile increases its number of cards and
		// results
		// in that card being the homecell pile's new top card [4 points]

		test.addCardHomecell(card1, 0);

		assertEquals("Adding card to Baker's Dozen homecell pile increases its number of cards", 1,
				test.getHomecellSize(0));
		assertEquals("that card being the homecell pile's new top card", card1, test.getHomecellLastCard(0));
	}

}
