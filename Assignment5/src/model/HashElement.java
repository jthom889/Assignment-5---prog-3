package model;

/**
 * this class controls and creates the 
 * necessary variables for a Hash Element
 * @author Opemipo Lano
 *
 */
public class HashElement {

	private String word; //key for the hash table
	private int count;
	
	/**
	 * generic constructor that 
	 * initializes variables to zero
	 */
	public HashElement() {
		word = null;
		count = 0;
	}
	
	/**
	 * constructor where a word is initialized 
	 * and the count starts at 1
	 * @param word
	 */
	public HashElement(String word) {
		this.word = word;
		count = 1;
	}
	
	public String toString() {
		return word + ": " + count;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	


}
