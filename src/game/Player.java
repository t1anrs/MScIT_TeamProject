/** 
	 * player class
	 * choose the card category for computer players
	 * @author RUNSHENG TIAN
	 * 
	 */
public class Player {
	
	
	    //Instance variables
		private final int numOfCategories;
		private Decklist cardsInPlayerHand;
		private int playerNumber,categoryNum;
		private int roundsWon;

		public Player(int playerNumber, Decklist cardsInPlayerHand) {
			this.playerNumber = playerNumber;
			this.cardsInPlayerHand = cardsInPlayerHand;
			numOfCategories = cardsInPlayerHand.getCategory().length;
		}

		/**
		 * choose the highest attribute value of category for computer player
		 * @return index of the chosen category 
		 */
		public int chooseCategory() {  
			Card c = cardsInPlayerHand.showTheTopCard();
			
		    categoryNum = 1;
			for(int max = 0, i = 0; i < numOfCategories; i++) {
				int categoryValue = c.getCategoryValue(i + 1);			
				if(categoryValue > max) {
					max = categoryValue;
					categoryNum = i + 1;			
				}
			}
			return categoryNum;
		}
		
		/**
		 * transfers card from this player's deck to other players' decks or communal deck
		 */ 
		public void transferCardTo(Decklist deck) {   
			deck.addCardToLast(cardsInPlayerHand.getTheTopCard());
		}
        
		public Decklist getDeck(){   
			return cardsInPlayerHand;
		}
		
		public int getPlayerNumber() {
			return playerNumber;
		}
		
		/**
		 * Method increments rounds won.
		 */
		public void wonRound() {
					roundsWon++;
		}
		
		public int getRoundsWon() {
			return roundsWon;
		}

		

}
