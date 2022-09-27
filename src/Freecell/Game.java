package Freecell;

import java.util.ArrayList;

import edu.buffalo.cse116.Card;
import edu.buffalo.cse116.Deck;

/**
 * create instances for all piles needed for this game
 */
public class Game {
	
	public static Tableau Tableau1;
	public static Tableau Tableau2;
	public static Tableau Tableau3;
	public static Tableau Tableau4;
	
	public static Tableau Tableau5;
	public static Tableau Tableau6;
	public static Tableau Tableau7;
	public static Tableau Tableau8;
	
	private Freecell Freecell1;
	private Freecell Freecell2;
	private Freecell Freecell3;
	private Freecell Freecell4;
	
	private Homecell Homecell1;
	private Homecell Homecell2;
	private Homecell Homecell3;
	private Homecell Homecell4;
	
	/**
 	* Initial cards hold for All TableauPiles                    
 	* 
	*/
	public Game(){
		Deck _deck = new Deck();
		_deck.shuffleCards();
		ArrayList<Card> _cardList = _deck.getCardList(); 
		
		 Tableau1 = new Tableau();
		 Tableau2 = new Tableau();
		 Tableau3 = new Tableau();
		 Tableau4 = new Tableau();
		 Tableau5 = new Tableau();
		 Tableau6 = new Tableau();
		 Tableau7 = new Tableau();
		 Tableau8 = new Tableau();
		 Freecell1 = new Freecell();
		 Freecell2 = new Freecell();
		 Freecell3 = new Freecell();
		 Freecell4 = new Freecell();
		 Homecell1 = new Homecell();
		 Homecell2 = new Homecell();
		 Homecell3 = new Homecell();
		 Homecell4 = new Homecell();
		
		for( int i = 0; i < 52; i ++){
		 if( i<6){
			 Tableau1.addStartingCard(_cardList.get(i));
			 
			
		 }
		 else if(i<12){
			 Tableau2.addStartingCard(_cardList.get(i));
			 
		 }
		 else if(i<18){
			 Tableau3.addStartingCard(_cardList.get(i));
			 
		 }
		 else if(i<24){
			 Tableau4.addStartingCard(_cardList.get(i));
			
		 }
		 else if(i<31){
			 Tableau5.addStartingCard(_cardList.get(i));
			
		 }
		 else if(i<38){
			 Tableau6.addStartingCard(_cardList.get(i));
			 
		 }
		 else if(i<45){
			 Tableau7.addStartingCard(_cardList.get(i));
			 
		 }
		 else if(i<52){
			 Tableau8.addStartingCard(_cardList.get(i));
			 
		 }
		}
	}
	
	public Tableau getTableau(int i){
		if( i == 1 ){
			return Tableau1; 
		}
		else if(i == 2){
			return Tableau2; 
		}
		else if(i == 3){
			return Tableau3; 
		}
		else if(i == 4){
			return Tableau4;
		}
		else if(i == 5){
			return Tableau5;
		}
		else if(i == 6){
			return Tableau6;
		}
		else if(i == 7){
			return Tableau7;
		}
		else{
			return Tableau8;
		}
	}
	
	public Freecell getFreecell(int i){
		if(i == 1){
			return Freecell1;
		}
		else if(i == 2){
			return Freecell2;
		}
		else if(i == 3){
			return Freecell3;
		}
		else {
			return Freecell4;
		}
	}
	public Homecell getHomecell(int i){
		if(i == 1){
			return Homecell1;
		}
		else if(i == 2){
			return Homecell2;
		}
		else if(i == 3){
			return Homecell3;
		}
		else {
			return Homecell4;
		}
		
	}

	
	

}
