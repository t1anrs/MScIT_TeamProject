
import java.util.Random;
import java.util.LinkedList;

/**
 * deck class set as linkedlist
 * @author RUNSHENG TIAN
 *
 */
public class Decklist {
	
	//Instance variables
	private int deckLastPointer;
	private String[] category;
	private LinkedList<Card> deck;
	
    /**
     * deck constructor
     * @param category: the card information
     */
	public Decklist(String[] category) {
		this.category = category;
		deck = new LinkedList<Card>();
		deckLastPointer = 0;
    	}
	
    /**
     * add a card to the first of the list
     * @param card: a card object
     */
	public void addCardToFirst(Card card) {
		deck.addFirst(card);
		this.deckLastPointer++;
	}
	
	/**
	 * add a card to the first of the list
	 * @param cardInfor: the card information
	 */
	public void addCardToFirst(String cardInfor) {
		Card newCard = new Card(cardInfor);	
		deck.addFirst(newCard);
		this.deckLastPointer++;
	}
	
	/**
	 * add a card to the last of the list
	 * @param card: card object
	 */
	public void addCardToLast(Card card) {
		deck.addLast(card);
		deckLastPointer++;
	}
	
	/**
	 * remove the first card from the list
	 */
	public void removeFirstCard() {
		if(deck.size()>0) {
		deck.removeFirst();
		deckLastPointer--;
		}
	}
	
	/**
	 * shuffles the Card based on Fisher-Yates shuffle algorithm
	 */
	public void shuffleDeck() {
		for(int i=0; i<deck.size()-2;i++) {
			Random ran = new Random();
			int r = i + ran.nextInt((deck.size()-i));
			
			Card swapcard = deck.get(i);
			deck.set(i,deck.get(r));
			deck.set(r, swapcard);
		}
	}
	
	/**
	 * get the first card and then remove it from the list 
	 * @return the first card
	 */
	public Card getTheTopCard() {
		Card topcard = deck.getFirst();
		removeFirstCard();
		return topcard;
	}
	
	/**
	 * @return the first card in the list
	 */
	public Card showTheTopCard() {
		return deck.getFirst();
	}
	
	/**
	 * check the player has card or not
	 * @return true if player has a card
	 */
	public Boolean hasCard() {
		if (deckLastPointer==0)
			return false;
		else
			return true;
	}
	
	
	public int getsize() {
		return deckLastPointer;
	}
	
	public int getCapacity() {
		return deck.size();
	}
	
	public String getCategoryName(int i) {
		return category[i - 1];
	}
	
	public String[] getCategory() {
		return category;
	}
	

	
	
}
