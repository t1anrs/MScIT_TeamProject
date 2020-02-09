/**
 * Set the card information
 * @author RUNSHENG TIAN
 *
 */
public class Card {
	
	//Instance variables
	private String cardName, cardInfo;
	private final int sumOfAttributes=5;
	private int[] attribute= new int[sumOfAttributes];
	
	/**
	 * card constructor
	 * @param String  card information
	 * 
	 */
	public Card(String cardInformation) {	  //takes a string and breaks it into chunks
		this.cardInfo = cardInformation;
    	String [] categories = cardInformation.split("\\s+");
    	this.cardName = categories[0];					    	// title is the first word in the String
    	for (int i = 0; i < sumOfAttributes; i++){		// put category keywords into attribute array
    		attribute[i] = Integer.parseInt(categories[i+1]);
    	}
	}
	

	/**
	 * @return a String formatted ----- the card name and attributes value  	 * 
	 */
	public String toString(){	
		return cardInfo;  // return String used in constructor
	}
	
	
	/**
	 * get attribute value.
	 * @param integer represent category
	 * @return the attribute value of the category
	 */
	public int getCategoryValue(int categoryNum){
		return attribute[categoryNum-1];
	}
	
	
	/**
	 * @return card name
	 */
	public String getcardName(){
		return cardName;
		}
	
}



