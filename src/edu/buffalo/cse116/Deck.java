package edu.buffalo.cse116;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

	/**
	 * @author      Emely Xu <emelyxu @ buffalo.edu>
	 * @author 	Xinyi Zhu <xinlinzhu @ buffalo.edu>
 	 */

public class Deck {

	/**
	 * This variable allCards means the Card list that will contains 52 cards.
 	*/
	private ArrayList<Card> allCards;
	
	/**
 	* this method will put 52 different cards in allCards                        
 	* 
	*/
	public void setCardList(){
		
		allCards = new ArrayList<Card> ();
		
		for(int i = 1; i <= 13 ; i++){
			
			if (i == 1){
				
				Card clubi = new Card("club", "A");
				Card hearti = new Card("heart", "A");
				Card diamondi = new Card("diamond", "A");
				Card spadei = new Card("spade", "A");
				
				
				allCards.add(clubi);
				allCards.add(hearti);
				allCards.add(diamondi);
				allCards.add(spadei);
				
				
			}
			
			else if (i == 11){
				Card clubi = new Card("club", "J");
				Card hearti = new Card("heart", "J");
				Card diamondi = new Card("diamond", "J");
				Card spadei = new Card("spade", "J");
				
				allCards.add(clubi);
				allCards.add(hearti);
				allCards.add(diamondi);
				allCards.add(spadei);
			}
			
			else if (i == 12){
				Card clubi = new Card("club", "Q");
				Card hearti = new Card("heart", "Q");
				Card diamondi = new Card("diamond", "Q");
				Card spadei = new Card("spade", "Q");
				
				allCards.add(clubi);
				allCards.add(hearti);
				allCards.add(diamondi);
				allCards.add(spadei);
			}
			
			else if(i == 13){
				
				Card clubi = new Card("club", "K");
				Card hearti = new Card("heart", "K");
				Card diamondi = new Card("diamond", "K");
				Card spadei = new Card("spade", "K");
				
				allCards.add(clubi);
				allCards.add(hearti);
				allCards.add(diamondi);
				allCards.add(spadei);
				
			}
			
			else{
			
			Card clubi = new Card("club", String.valueOf(i));
			Card hearti = new Card("heart", i+"");
			Card diamondi = new Card("diamond", i+"");
			Card spadei = new Card("spade", i+"");
			
			allCards.add(clubi);
			allCards.add(hearti);
			allCards.add(diamondi);
			allCards.add(spadei);
			
			}
		}
		
		
		
	}
	
	/**
 	* The constructor method of Deck                       
 	* will put 52 different Cards into the list allCards
	*/
	public Deck(){
		
		setCardList();
	}
	
	/**
 	* This method will get a random card from the card list
	*
	* When you use this method, make sure you setCardList first
	*
 	* Since this method will return a random card, make sure you 
	* check it is not the same card if you wan to get more than two cards
	*
	* @return a random card from allCards list
	*/
	public Card getRandomCard(){
		
		// When you use this method, make sure you setCardList first
		// Since this method will return a random card, make sure you 
		// check it is not the same card if you wan to get more than two cards
		
		Random r = new Random();
		int i = r.nextInt(51);
		Card c = allCards.get(i);
		
		
		return c;
		
		
		
	}
	
	/**
 	* This method shuffled the Card list                     
 	*
	*/
	
	public void shuffleCards(){
		// This method shuffled the Card list
		
		 Collections.shuffle(allCards);
		 
		 
	}
	
	/**
 	* This method will get the cardlist                     
 	*
	* @return the Card list allCards
	*/
	public ArrayList<Card> getCardList(){
		
		return allCards;
		
	}
	
	
	
	/**
 	* This method will return the first card of deck and then remove it                    
 	*
	* @return the first card of deck
	*/
	public Card dealCard(){
		Card re = allCards.get(0);
		allCards.remove(0);
		return re;
	} 
	
	
	/**
 	* This method will return the number of cards in deck                    
 	*
	* @return the size of deck
	*/
	public int size(){
		return allCards.size();
	}

}

