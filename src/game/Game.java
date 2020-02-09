import java.util.Random;

public class Game {
	
		
		// Instance variables
		private Decklist totalDeck, communalDeck;
		private Player[] player;
		private Player currentPlayer, roundWinner;
		private int numberOfOpponent;
		private int roundNum, drawNum;
		private int highestAttributeValue, comparedAttributeValue, drawAttributeValue;
		
		// set human player as the first position of Player 
		protected static final int HUMAN_PLAYER = 0;		
		// set 1 for win and 2 for draw 
		protected static final int STATE_ROUND_WON = 1;
		protected static final int STATE_ROUND_DRAW = 2;

		/**
		 * Constructor for a Game instance
		 * @param totallDeck: a Decklist including the cards 
		 * 		
		 */
		public Game(Decklist totalDeck) {
			this.totalDeck = totalDeck;
			communalDeck = new Decklist(totalDeck.getCategory());
		}
		
		/**
		 * @return a random integer to set as the first player 
		 * 
		 */
		private int randomFirstPlayer() {
			return new Random().nextInt(numberOfOpponent + 1);
		}
		
		/**
		 * shuffling the deck 
		 * dealing the top card to each player by turn until there are no cards left
		 */
		private void dealCardToPlayer() {
			
			totalDeck.shuffleDeck();
			
			for(int i = 0, j = 0; i < totalDeck.getCapacity(); i++) {
				player[j].getDeck().addCardToFirst(totalDeck.showTheTopCard());
				if(j < numberOfOpponent) {
					j++;
				}
				else {
					 j = 0;
				}
			
			}
				
		}
		
		/**
		 * Initiate a new game state and prepare to start the first round of a new game
		 * @param sumofopponents: the sum of opponents chosen by the human player when they start the game
		 * 
		 */
		public void startFirstRound(int numberOfOpponent) {
			this.numberOfOpponent = numberOfOpponent;
			
			player = new Player[numberOfOpponent + 1];
			for(int playerNumber = 0; playerNumber < numberOfOpponent + 1; playerNumber++) {
				player[playerNumber] = new Player(playerNumber, new Decklist(totalDeck.getCategory()));
			}
						
			dealCardToPlayer();  
			currentPlayer = player[randomFirstPlayer()];
			roundNum = 0;
			drawNum = 0;
		}
		
		
		
		/**
		 * current player choose a category and compare it with all other player who has cards
		 * set the player with the highest attribute value as the round winner or if draw keep the current player not change
		 * then returns the game state.
		 * @param chosenCategory: an integer which represents a category chosen by player
		 * @return 1 for win or 2 for draw
		 * 
		 */
		
		protected int eachRoundRun(int chosenCategory) {
						
			highestAttributeValue = currentPlayer.getDeck().showTheTopCard().getCategoryValue(chosenCategory);
			roundWinner = currentPlayer;			
			comparedAttributeValue = 0;
			drawAttributeValue = 0;
			roundNum++;
			
			// compare values iterate through each player who has a card
			for(int i = 0; i < numberOfOpponent + 1; i++) {
				
				if(player[i] != currentPlayer && player[i].getDeck().hasCard()) {
										
					comparedAttributeValue = player[i].getDeck().showTheTopCard().getCategoryValue(chosenCategory);
					
					if(comparedAttributeValue > highestAttributeValue) {
						highestAttributeValue = comparedAttributeValue;
						roundWinner = player[i];
					}
					else if(comparedAttributeValue == highestAttributeValue) {
						drawAttributeValue = highestAttributeValue;
					}
					else {
						// continue when compared value< highest value
						continue;
					}
					
				}
				
			}
						
			// return an integer representing round won or draw
		    if(highestAttributeValue == drawAttributeValue) {
		    	drawNum++;
		    	return STATE_ROUND_DRAW;
		    }
		    else {
		    	currentPlayer = roundWinner;
		    	currentPlayer.wonRound();
		    	drawNum = 0;
		    	return STATE_ROUND_WON;
		    }		    
		    
		}
		
		/**
		 * check if anyone has a card except the last round winner
		 * @return true if someone has been won
		 * 
		 */
		public boolean checkGameWon() {
			for(int i = 0; i < numberOfOpponent + 1; i++) {
				// At least two players will have cards to play in the next round
				if(player[i] != currentPlayer && player[i].getDeck().getCapacity() > 1) {				
					return false;		
			    }  
		    }
			// Only the currentPlayer will have cards in the next round
			return true;
		}
		
		/**
		 * the round has a winner
		 * then transfer the card in communal deck and all the cards played in this round to the round winner 
		 *  
		 */
		public void transferCardsToWinner() {
			
			// transfer cards from communal deck to last of the list
			if(communalDeck.hasCard()) {
				do {
					currentPlayer.getDeck().addCardToLast(communalDeck.getTheTopCard());
				} while(communalDeck.hasCard());
			}
			
			// give winner everyone's played-cards  to last of the list
			for(int i = 0; i < numberOfOpponent + 1; i++) {
				if(player[i].getDeck().hasCard()) {
					player[i].transferCardTo(currentPlayer.getDeck());
				}
			}
			
		}
		
		/**
		 * the round is draw 
		 * then transfer all top cards into the communal deck and check the player has a card or not before
		 * 
		 */
		public void transferCardsToCommunal() {
			for(int i = 0; i < numberOfOpponent; i++) {
				
				if(player[i].getDeck().hasCard()) {
					player[i].transferCardTo(communalDeck);
				}
			}
			
			if(!currentPlayer.getDeck().hasCard()) {
				for(int i = 0; i <= numberOfOpponent; i++) {
					if(player[i].getDeck().hasCard()) {
						currentPlayer = player[i];
					}
				}
			}
			
		}


		public Player getHumanPlayer() {
			return player[HUMAN_PLAYER];
		}
		
		// Accessors
		
		public Decklist getCommunalDeck(){
			return communalDeck;
		}
		
		public Player getCurrentPlayer() {
			return currentPlayer;
		}
		
		public int getRoundNum() {
			return roundNum;
		}

		public int getDrawNum() {
			return drawNum;
		}
		
		public Player getPlayer(int i){
			return player[i];
		}
		
		public int getNumOfPlayers() {
			return numberOfOpponent + 1;
		}

}
