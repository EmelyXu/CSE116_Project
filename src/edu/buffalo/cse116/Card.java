package edu.buffalo.cse116;

public class Card {

	/**
	 * @author      Emely Xu <emelyxu @ buffalo.edu>
 	 */
		
		private String rank;
		private String suit;
		
	/**
 	* Constructor of Card                        
 	* 
	* Use theSuit and theRank to define a Card
	* 
	* @param theSuit suit of a Card, need to be cube, heart, diamond or spade
	* @param theRank rank of a Card, need to be 2,3,4,5,6,7,8,9,10,J,Q,K,A
	*/
		public Card(String theSuit, String theRank) {
			
			rank = theRank;
			
			suit = theSuit;		
			
		}
	
	/**
 	* This method can get the rank of the Card
	* 
	* @return the rank of the Card
	*/
	
		public String getRank(){
			return rank;
		}
	
	/**
 	* This method turn the rank to integer so that we can put the card in order                        
 	* 
	* 
	* @param ranks is the rank of a Card, need to be 2,3,4,5,6,7,8,9,10,J,Q,K,A
	* which J=11, Q=12, K=13, A=1
	*
	* @return the integer repersent the rank
	*/
	
		public int getValueOfRank(String ranks){
			int i = 0;
			if(ranks == "A"){
				i = 1;
			}
			else if(ranks == "J"){
				i = 11;
			}
			else if (ranks == "Q"){
				i = 12;
			}
			else if (ranks == "K"){
				i = 13;
			}
			else{
			i = Integer.parseInt(ranks);
			}
			return i;
		}
	/**
 	* This method can get the suit of the Card
	* 
	* @return the suit of the Card
	*/
		public String getColor(){
			return suit;
		}
	/**
 	* This method get the string of the Card
	* e.g. cube 4, spade 7, heart A
	* 
	* @return the string of the Card
	*/
	
		public String toString(){
			return suit + " " + String.valueOf(rank) ;
		}
		
	
}
