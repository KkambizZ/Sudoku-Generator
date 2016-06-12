package Sudoku;

/**
 * 
 * @author Kambiz
 * @version 0.1
 */

import java.util.ArrayList;
import java.util.Random;

//Generating a Sudoku puzzle
public class SudokuGenerator {

	private int[][] puzzle;
	private int[] row;
	private Random randNumber;
	private ArrayList<Integer> availableNums;

	//size of the puzzle
	final int SIZE;
	//size of inner squares
	final int BOX_SIZE;

	SudokuGenerator(int size) { //size of the Sudoku puzzle is received as parameter

		this.SIZE = size;
		//Initialize the puzzle size
		puzzle = new int[SIZE][SIZE];
		// Size of the inner squares
		BOX_SIZE = (int) Math.sqrt(SIZE);
		//Initialize row size
		row = new int[SIZE];
		//Random number generator
		randNumber = new Random();
		//List of the available numbers to put in the puzzle
		availableNums = new ArrayList<Integer>();

		//Now we fill the puzzle
		for(int i = 0; i < SIZE; i++)
			fillPuzzle(i);
	}

	/**
	 * Method to fill each row of the puzzle with non-duplicate numbers
	 * @param n
	 */

	private void fillPuzzle(int n) {

		generateUniqueRow(n); // get a new non-duplicate row

		//copy the row into the puzzle
		for(int i = 0; i < SIZE; i++) {
			puzzle[n][i] = row[i];
		}

	}

	/**
	 * The main method to check for the conditions; i.e.
	 * No duplicate numbers in a row
	 * No duplicate numbers in a column
	 *  
	 * @param rowNumber
	 * @return a row with non-duplicate numbers
	 */

	private void generateUniqueRow(int rowNumber) {

		//It's used to check which numbers are available for the row 'n'
		//The numbers stored in it are the numbers already used in the row
		boolean[] usedNums = new boolean[SIZE];

		//Set a flag to enter the "conditions-checking" loop
		boolean flag = true;

		/* "conditions-checking" loop
		 * Enter the loop to check the conditions, so to pick non-duplicate numbers for each row
		 * Then we add this unique row to the puzzle  */
		while(flag) {
			//To get out of the loop when a new row generated
			flag = false;
			//loop for each value of the row (or we can say each column of the row)
			for(int i = 0; i < SIZE; i++) {

				//First make all numbers available for the row (clear any previous data to start fresh)
				for(int j = 0; j < SIZE; j++)
					usedNums[j] = false;

				//Make "not available" any previous number used in that row
				for(int j = 0; j < i; j++)
					usedNums[row[j]] = true;

				//Make "not available" any previous number used in that column
				for(int j = 0; j < rowNumber; j++)
					usedNums[puzzle[j][i]] = true;

				//Make "not available" any previous number used in that square
				for(int j = 0; j < rowNumber; j++) {
					//get the corners of each box
					int r = (j / BOX_SIZE) * BOX_SIZE;
					int c = (i / BOX_SIZE) * BOX_SIZE;
					
					//check if the number is already used in that box
					for (int k = 0; k < SIZE; k++) {
						if (puzzle[r + (k % BOX_SIZE)][c + (k / BOX_SIZE)] == puzzle[j][i])
							usedNums[puzzle[j][i]] = true;	
					}
				}
				
				availableNums.clear(); // first clear any previous data from the List

				//fill the array with the possible numbers
				for(int j = 0; j < SIZE; j++) {
					if(!usedNums[j]) { // if number not used
						availableNums.add(j); // add it to the list of available numbers
					}
				}

				//If we have no available numbers left, make the flag true to exit the loop
				if(availableNums.size() == 0) {
					flag = true;
					break; //Exit the loop
				}

				//To generate a random row, each time we pick a number from the available numbers randomly
				row[i] = (int) availableNums.remove(randNumber.nextInt(availableNums.size()));
			}
		}
	}

	/**
	 * @return This method can be used to print the puzzle
	 */

	public String toString() {
		String string = "";
		for(int i = 0; i < SIZE; i++) { // every row
			for(int j = 0; j < SIZE; j++) { // every column
				//numbers in the puzzle are from (0 to size-1)
				//So for a better look we will display it from (1 to size) by adding 1 to each value
				string += " " + (puzzle[i][j] + 1); // add value to String
			}
			string += "\n"; // go to next row
		}
		return string;  //return puzzle numbers (as string)
	}
}

