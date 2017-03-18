package edu.nyu.cs.ank352;

/**
 * This program asks the user to enter a baby's name and returns/prints
 	* it's popularity rank, the year it was ranked, and its gender based on 
 	* https://data.cityofnewyork.us/Health/Most-Popular-Baby-Names-by-Sex-and-Mother-s-Ethnic/25th-nujf
 *From this data set, the birth year (BRTH-YR), gender (GNDR), name (NM), and rank (RNK) columns are used
 *"Baby_Names.txt" is a tab-separated text file
 * @author Alexander Kessaris
 * @version 0.1
 */

/**
 * import java classes to get file and Scanner
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Baby_Name_Data_Mining {
/**
 * This allows you to do data mining within the data set
 * @param name
 * @return baby
 * @throws FileNotFoundException
 */

	public static String [] data_mining (String name) throws FileNotFoundException {
		
		/**
		 * Declare and initialize baby, which will be returned
		 */
		String[] baby=new String [3];
		
		/**
		 * Open Baby Names file
		 */
		Scanner file_input = new Scanner (new File ("src/Baby_Names.txt"));
		
		/**
		 * Create array of words from file
		 */
		String s = "";
		while (file_input.hasNextLine()) {
			s += file_input.nextLine();
			s += "\n";
		}
		
		/**
		 * Make simple array by splitting line breaks
		 */
		String [] row = s.split("[\n]");
		
		/**
		 * Split simple array by tabs into 2-D array
		 */
		String[][] matrix = new String [row.length][];
		for (int i = 0; i<row.length; i++) {
			matrix [i] = row[i].split("\t");
		}
		
		/**
		 * If name is found, put the rank, year, and gender into baby array
		 */
		for (int a = 0; a<row.length; a++) {
			for (int j = 0; j<6; j++) {
				if (matrix[a][j].equals(name)) {
					/**
					 * Baby's rank
					 */
					baby[0] = matrix[a][j+2];
					
					/**
					 * Baby's birth year
					 */
					baby[1] = matrix[a][j-3];
					
					/**
					 * Baby's gender
					 */
					baby[2] = matrix[a][j-2];
				}
			}
		}
		
		/**
		 * Close scanner
		 */
		file_input.close();
		
		/**
		 * Return baby array
		 */
		return baby;
}	
	
	
/**
 * Main method that starts the program
 * @param args
 * @throws FileNotFoundException
 */
	public static void main(String[] args) throws FileNotFoundException {
		
		/**
		 * Create Scanner
		 */
		Scanner input = new Scanner (System.in);
		
		/**
		 * Introduce app and its use to the user
		 * Prompt them to enter a baby name
		 */
		System.out.printf("Welcome to the Baby Name App!\n\n\n"
				+ "This app borrows data from: \nhttps://data.cityofnewyork.us/Health/Most-Popular\n"
				+ "-Baby-Names-by-Sex-and-Mother-s-Ethnic/25th-nujf\n\n");
		System.out.printf("Enter any first name for a baby and we can tell you it's popularity rank\n"
				+ "in NYC, birth year, and it's gender according to our data: ");
		
		/**
		 * Declare and initialize name
		 */
		String name = input.nextLine();
		System.out.printf("This might take a few seconds...\n");
		
		/**
		 * Run the method and find name's rank, gender, and year
		 * If the name can't be found, program will tell user
		 */
		String[] baby = data_mining(name);
		if (baby[0]!=null) {
			baby[2] = baby[2].toLowerCase();
			System.out.printf("%s was ranked %s in %s and is a %s name.",name,baby[0],baby[1],baby[2]);
			
		}
		else  {
			System.out.printf("Sorry, we don't have that name in our database.");
		}
		
		/**
		 * Close Scanner
		 */
		input.close();

	}
}
