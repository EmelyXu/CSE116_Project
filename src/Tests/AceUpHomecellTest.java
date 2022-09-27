package Tests;

import static org.junit.Assert.*;

import org.junit.Test;
import AceUp.*;
import edu.buffalo.cse116.Card;

public class AceUpHomecellTest {

	@Test
	public void HomeCellTestInitial(){
		//	Homecell pile in Ace's Up initially holds 0 cards [2 points]
	AceHomecell test = new AceHomecell();
	assertEquals(0,test.size());
	
	
	}
	
	@Test
	public void HomeCellTestAdd(){
		//	Ace's Up homecell pile correctly determines if adding a specific card is 
		//legal or illegal (e.g., always legal) [2 points]
	AceHomecell test = new AceHomecell();
	Card card = new Card("heart","K");
	assertTrue(test.addCard(card));
	
	}
	
	@Test
	public void HomeCellTestRemove(){
		//	Ace's Up homecell pile correctly returns if removing top card is 
		//legal or illegal (e.g., always illegal) [2 points]
	AceHomecell test = new AceHomecell();
	assertFalse(test.removeTopCard());
	}
	
	@Test
	public void HomeCellTestTopCard(){
		//	Adding card to Ace's Up homecell pile increases its number of cards and results 
		//in that card being the homecell pile's new top card [2 points]
	AceHomecell test = new AceHomecell();
	Card card = new Card("heart","K");
	assertTrue(test.addCard(card));
	assertEquals(1,test.size());
	assertEquals(card, test.getTopCard());
	
	
		
	}
	
}
