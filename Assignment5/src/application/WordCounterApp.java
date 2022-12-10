package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.HashElement;
import model.WordCounter;

/**
 * this class includes the main method and controls the
 * program. the user input is taken and verified and 
 * the outputs of the word count are given
 * @author Opemipo Lano
 *
 */
public class WordCounterApp {

	/**
	 * this main method prompts for user input 
	 * and requires a file name and size of hash map. 
	 * this method will sort through the file and display 
	 * various information about the words in the file
	 * @param args
	 */
	public static void main(String[] args){
		
		Scanner kb = new Scanner(System.in);
		
		
		// file name: res/argfoo.txt || res/mru.txt
		System.out.println("Whats the name of the text file: ");
		
		Scanner in;
		//verify user input for the file name
		while(true) {
			
			String file = kb.nextLine();
			try {
				in = new Scanner(new File(file));
				break;	
			}catch(FileNotFoundException e) {
				System.out.println("File not Found. Please try again: ");
			}
		}
		
		//ask user for size of the array
		int number = -1;
		do {
			
			System.out.println("What is the size of the table: ");
			String num = kb.next();	
			try {
			
				number = Integer.parseInt(num);
				
			}catch(NumberFormatException e){
				System.out.println("Must be a positive integer, try again.");
			}
				
		} while(number < 1);
		
		WordCounter wc = new WordCounter(number);
		
		while (in.hasNext()){
			  
			String word = in.next();  
			HashElement toAdd = new HashElement(word.toLowerCase());
			wc.put(toAdd);

		} 
	    in.close();
	    kb.close();
		
	    // print the table
	    //wc.printTable(); 
	    
	    //print the output for the assignment
	    int total = 0;
	    HashElement[] hash = wc.getHashtable();
	    HashElement highest = new HashElement();
	    int uniqueCount = 0;
	    
	    for(int i = 0; i < wc.getSize(); i++) {
	    	
	    	total += hash[i].getCount();
	    	
	    	if(highest.getCount() < hash[i].getCount())
	    		highest = hash[i];
	    	
	    	if(!hash[i].getWord().equals("null"))
	    		uniqueCount++;
	    }
	    System.out.println();
	    System.out.println("The total number of unique words was: " + uniqueCount);	 
	    System.out.println("The most common word was '" + highest.getWord() + "' with " + highest.getCount() + " uses.");
	    System.out.println("The total word count of the txt file was: " + total);
	        
	}
	
	

}
