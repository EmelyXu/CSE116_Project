package AceUp;

import java.util.ArrayList;

import edu.buffalo.cse116.Card;

public class AceHomecell {
ArrayList<Card> home;
	
/**
	* Constructor of AceHomecell
	* create a new Arraylist for homecell                       
	* 
*/
	public AceHomecell(){
		home = new ArrayList<Card>();
		
	}
	
		
/**
	* Determine if card can be add to homecell(which is always true)
	* @return boolean true of false(which is always true here)                     
	* 
*/
	public boolean addCard(Card card){
		home.add(card);
		return true;
		}	
	
		
/**
	* get the size of homecell
	* @return size of homecell                     
	* 
*/
	public int size() {
		return home.size();
	}
	
		
/**
	* get the Arraylist of homecell
	* @return the ArrayList that stored all cards of homecell                     
	* 
*/
	public ArrayList<Card> getHomecellList(){
		return home;
	}
		
/**
	* Determine if the top card can be remove from homecell(which is always false)
	* @return boolean true of false(which is always false here)                     
	* 
*/		
	public boolean removeTopCard(){
		return false;
	}
/**
	* This method get the top card of homecell
	* @return the top card of homecell                     
	* 
*/	
	public Card getTopCard(){
		return home.get(home.size() - 1);
	}
		
	
	

}
