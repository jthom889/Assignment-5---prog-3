package model;

/**
 * this class contains all of the methods 
 * required to setup and control the word counter
 * @author Jonah Thompson
 *
 */
public class WordCounter implements HashInterface<HashElement>{

	private int size;
	private HashElement[] hashtable;
	
	/**
	 * default constructor to initialize a word counter
	 */
	public WordCounter() {
		size = 0;
		hashtable = new HashElement[size];
		for(int i = 0; i < size; i++) {
			hashtable[i] = new HashElement("null");
			hashtable[i].setCount(0);
		}
	}
	
	/**
	 * this constructor initializes the 
	 * size based on a parameter given
	 * @param size is the given size of the table
	 */
	public WordCounter(int size) {
		this.size = size;
		hashtable = new HashElement[size];
		for(int i = 0; i < size; i++) {
			hashtable[i] = new HashElement("null");
			hashtable[i].setCount(0);
		}
	}
	
	/**
	 * this method gets the hash code for a given element
	 * @param key is the given key to find the code for
	 * @return the hash code of the key
	 */
	@Override
	public int gethashCode(HashElement key) {
		
		int count = 0;
		String word = key.getWord();
		
		for(int i = 0; i < word.length(); i++) 
			count = count + word.charAt(i);
		
		return count % size;
		
	}

	/**
	 * this method will put an element into the table
	 * @param key is the element to put into the hash table
	 */
	@Override
	public void put(HashElement key) {
		
		int place = gethashCode(key);
		
		//new word in an empty spot. no collisions
		if(hashtable[place].getWord() == "null") 
			hashtable[place] = key;
		
		//word exists
		else if(hashtable[place].getWord().equals(key.getWord())) {
			hashtable[place].setCount(hashtable[place].getCount() + 1);
		}
		
		//if the spot is full but word is different. collision occurred
		else 
			putQuadratic(place, key);
	
	}

	/**
	 * this method will remove an elemnt from the table
	 * @param key is the key to be removed
	 * @return the key that was removed
	 */
	@Override
	public HashElement remove(HashElement key) {
		
		int toRemove = gethashCode(key);
		
		hashtable[toRemove].setWord("null");
		hashtable[toRemove].setCount(0);
		
		return key;
	}

	/**
	 * this method will reset the table by 
	 * replacing all elements to "null" with a count of 0
	 */
	@Override
	public void reset() {
		
		for ( int i = 0; i < hashtable.length; i++) {
			hashtable[i].setWord("null");
			hashtable[i].setCount(0);
		}
	}

	/**
	 * this method will print the method 
	 * using the toString method of the hash element
	 */
	@Override
	public void printTable() {
		for(int i = 0; i < hashtable.length; i++) {
			System.out.print(hashtable[i].toString() + "     ");	
		}
	}
	
	/**
	 * this method will put an element into the list 
	 * based on quadratic probing if a collision occurs
	 * @param i is the index to try and add the element to
	 * @param arg is the argument to add into the table
	 */
	public void putQuadratic(int i, HashElement arg) {

		if (hashtable[i].getWord() == "null") 
			// the space is empty
			hashtable[i] = arg;
		//the space is full with the desired word
		else if(hashtable[i].getWord().equals(arg.getWord())) {
			hashtable[i].setCount(hashtable[i].getCount() + 1);
		}
		else {
			int j = probeQuadratic(i, arg);
			if (j == -1) 
				System.out.println("Error! Table Full! The word " + arg.getWord() + " could not be added. Try again with a larger table. The following numbers may be skewed:");
			
			}
		}
	
	/**
	 * this method probes an element and handles 
	 * collisions to find the correct spot to add the element to
	 * @param index is the index to add original index that is filled
	 * @param arg is the argument to add to the table
	 * @return the index the item was put into or -1 if the item could not be entered
	 */
	private int probeQuadratic(int index, HashElement arg) {
		
		for (int j = 1; j <= size; j++) {
			int tempIndex = index;
			tempIndex = (index + (j * j)) % size; 
			
			if(hashtable[tempIndex].getWord().equals(arg.getWord())) {
				hashtable[tempIndex].setCount(hashtable[tempIndex].getCount() + 1);
				return tempIndex;
			}
			
			else if (hashtable[tempIndex].getWord() == "null") {
				hashtable[tempIndex] = arg;
				return tempIndex;
			}
		}
		return -1;
	}
	

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public HashElement[] getHashtable() {
		return hashtable;
	}

	public void setHashtable(HashElement[] hashtable) {
		this.hashtable = hashtable;
	}
	

}
