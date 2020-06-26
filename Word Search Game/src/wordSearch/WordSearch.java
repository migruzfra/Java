package wordSearch;

import java.util.Arrays;
import java.util.Random;

import colors.Color;

import static keyboard.Keyboard.*;

public class WordSearch {

	public static void main(String[] args) {
		menu();
		/*String wordsPetition[][] = new String[][] { { "Programacion", "5", "1", "downright", "notfound" },
				{ "BaseDeDatos", "11", "5", "upright", "notfound" },
				{ "DesarrolloInterfaces", "16", "1", "right", "notfound" }, { "Marcas", "4", "13", "left", "notfound" },
				{ "Acceso A Datos", "1", "16", "left", "notfound" }, { "Fol", "8", "3", "up", "notfound" },
				{ "Entornos", "1", "1", "right", "notfound" }, { "Hlc", "15", "15", "right", "notfound" },
				{ "Android", "7", "14", "downleft", "notfound" }, { "Sistemas", "4", "6", "down", "notfound" },
				{ "Multihilo", "16", "9", "upleft", "notfound" },
				{ "Mantenimiento", "4", "13", "downleft", "notfound" } };*/
	}

	public static void menu() {
		boolean exit = false, firstPlay = true, charGridNull = true, stringGridNull = true;
		String[][] wordsPetition = null, wordsPetition4Play = null, stringGrid = null;
		char[][] charGrid = null;
		int menu, N;
		do {
			System.out.println("1. Create word search\n2. Show word search\n3. Play word search\n4. Exit");
			menu = readRange(1, 4, Range.II);

			switch (menu) {
			case 1:
				firstPlay = true;
				N = readLimit("Enter the size N of the grid NxN: ", Limit.GE, 2);
				wordsPetition = wordsPetition(readLimit("How many words are you going to enter?: ", Limit.GE, 1), N);
				charGrid = createGridWords(wordsPetition, N);
				charGridNull = false;
				fill(charGrid);
				stringGrid = createWordSearch(charGrid);
				stringGridNull = false;
				//Inicializamos el array de juego ahora, de modo que SOLO se inicializa cuando se inicialice el array original
				//Si se juega varias veces con el mismo array no se inicializa varias veces, sino solo una.
				wordsPetition4Play = new String[wordsPetition.length][5];
				copyArray(wordsPetition4Play, wordsPetition);
				break;
			case 2:
				if (!charGridNull) {
					showWordSearch(charGrid);
					wordsControl(wordsPetition4Play);
				} else {
					System.out.println("You have to create a grid for show");
				}
				break;
			case 3:
				//If firstPlay were true, there is no need to create again all the arrays for play, but if we want to repeat the game is necessary to create them
				if (!firstPlay) {
					copyArray(wordsPetition4Play, wordsPetition);
					stringGrid = createWordSearch(charGrid);
					stringGridNull = false;
				}

				if (!stringGridNull) {
					play(stringGrid, wordsPetition4Play);
					firstPlay = false;
				} else {
					System.out.println("You have to create a grid for play");
				}
				break;
			case 4:
				exit = true;
				break;
			}
		} while (!exit);
		//Close keyboard
		ck();
	}

	/*
	 * 1. Ask for the word
	 *    1.1 If its length is bigger than the maximum dimension of the grid (N), ask again
	 *    1.2 If the word don't match the pattern allowed, ask again
	 * 2. Ask for row and column of word. If it is larger than the maximum dimension of the grid, ask again
	 * 3. Ask for orientation. If it is different from one of the eight dimensions, ask again
	 */
	//numWords es un parámetro de entrada por valor
	//N es un parámetro de entrada por valor
	//words es un parámetro de salida por referencia
	public static String[][] wordsPetition(int numWords, int N) { //N is parameter in order to know maximum length of the grid
		String words[][] = new String[numWords][5];
		boolean repeat;
		int aux;
		System.out.printf("Maximum length of the word: %d letters%n", N);
		for (int word = 0; word < numWords; word++) {
			for (int j = 0; j < 5; j++) {
				//1. Ask for the word
				//1.1 If its length is bigger than the maximum dimension of the grid (N), ask again
				//1.2 If the word don't match the pattern allowed, ask again
				if (j == 0) {
					do {
						System.out.printf("Word %d: ", word + 1);
						words[word][j] = readString();
						//This is for avoiding that the user enter a repeated word
						repeat = false;
						if (word > 0) {
							for (int i = 0; i < word; i++) {
								if (words[word][j].equals(words[i][j])) {
									repeat = true;
								}
							}
						}

					} while (!words[word][j].matches("[A-ZÑ][a-zñ]+([A-ZÑ]{1,2}[a-zñ]+)*")
							|| words[word][j].length() > N || repeat);
					//2. Ask for row and column of word. If it is larger than the maximum dimension of the grid, ask again
				} else if (j == 1) {
					System.out.printf("Row start of word %d: ", word + 1);
					aux = readRange(1, N, Range.II);
					words[word][j] = Integer.toString(aux);
					//2. Ask for row and column of word. If it is larger than the maximum dimension of the grid, ask again
				} else if (j == 2) {
					System.out.printf("Column start of word %d: ", word + 1);
					aux = readRange(1, N, Range.II);
					words[word][j] = Integer.toString(aux);
					//3. Ask for orientation. If it is different from one of the eight dimensions, ask again
				} else if (j == 3) {
					do {
						System.out
								.printf("Orientation (up, down, left, right, upleft, upright, downleft, downright): ");
						words[word][j] = readString().toLowerCase();
					} while (!words[word][j].equals("up") && !words[word][j].equals("down")
							&& !words[word][j].equals("left") && !words[word][j].equals("right")
							&& !words[word][j].equals("upleft") && !words[word][j].equals("upright")
							&& !words[word][j].equals("downleft") && !words[word][j].equals("downright"));
				} else if (j == 4) {
					words[word][j] = "notfound";
				}
			}
		}
		return words;
	}

	/*
	 * 1. Go through the array of words and insert them into the grid
	 * 2. According to the length of the word, row, column and orientation, insert word into the grid
	 */
	//wordsPetition es un parámetro de entrada/salida por referencia
	//N es un parámetro de entrada por valor
	//grid es un parámetro de salida por referencia
	public static char[][] createGridWords(String[][] wordsPetition, int N) throws IllegalArgumentException {
		//If someone wants to enter a wrong array, there is an illegal argument exception
		for (int i = 0; i < wordsPetition.length; i++) {
			if (!wordsPetition[i][0].matches("[A-ZÑ][a-zñ]+([A-ZÑ]{1,2}[a-zñ]+)*")) {
				throw new IllegalArgumentException("Error. Words entered are not valid");
			}
		}
		final int WORD = 0, ROW = 1, COLUMN = 2, ORIENTATION = 3, AUXILIARY = 4;
		int row, col;
		char[][] grid = new char[N][N];
		for (int i = 0; i < N; i++) {
			//Arrays.fill is a function that works only on one-dimensional arrays
			Arrays.fill(grid[i], ' ');
		}

		//1. Go through the array of words and insert them into the grid
		for (int numWord = 0; numWord < wordsPetition.length; numWord++) {
			//2. According to the length of the word, row, column and orientation, insert word into the grid
			row = Integer.parseInt(wordsPetition[numWord][ROW]);
			col = Integer.parseInt(wordsPetition[numWord][COLUMN]);
			if (checkIfFits(grid, wordsPetition[numWord][WORD], row - 1, col - 1,
					wordsPetition[numWord][ORIENTATION])) {
				insertWordInGrid(grid, wordsPetition[numWord][WORD], row - 1, col - 1,
						wordsPetition[numWord][ORIENTATION]);
			} else {
				wordsPetition[numWord][AUXILIARY] = "error";
			}

		}
		return grid;
	}

	/*
	 * 1. According to orientation row and column, insert word into the grid if it fits
	 * 2. If orientation is up, column decrements
	 * 3. If orientation is down, column increments
	 * 4. If orientation is right, row increments
	 * 5. If orientation is left, row decrements
	 * 6. If orientation is upright, row increments and column decrements
	 * 7. If orientation is upleft, row decrements and column decrements
	 * 8. If orientation is downright, row increments and column increments
	 * 9. If orientation is downleft, row decrements and column increments
	 */
	//grid es un parámetro de entrada-salida por referencia
	//word es un parámetro de entrada por valor
	//row es un parámetro de entrada por valor
	//col es un parámetro de entrada por valor
	//orientation es un parámetro de entrada por valor
	public static void insertWordInGrid(char[][] grid, String word, int row, int col, String orientation) {
		//1. According to orientation row and column, insert word into the grid
		for (int i = 0; i < word.length(); i++) {
			//2. If orientation is up, column decrements
			if (orientation.equals("up")) {
				grid[row - i][col] = word.charAt(i);
				//3. If orientation is down, column increments
			} else if (orientation.equals("down")) {
				grid[row + i][col] = word.charAt(i);
				//4. If orientation is right, row increments
			} else if (orientation.equals("right")) {
				grid[row][col + i] = word.charAt(i);
				//5. If orientation is left, row decrements
			} else if (orientation.equals("left")) {
				grid[row][col - i] = word.charAt(i);
				//6. If orientation is upright, row increments and column decrements
			} else if (orientation.equals("upright")) {
				grid[row - i][col + i] = word.charAt(i);
				//7. If orientation is upleft, row decrements and column decrements
			} else if (orientation.equals("upleft")) {
				grid[row - i][col - i] = word.charAt(i);
				//8. If orientation is downright, row increments and column increments
			} else if (orientation.equals("downright")) {
				grid[row + i][col + i] = word.charAt(i);
				//9. If orientation is downleft, row decrements and column increments
			} else if (orientation.equals("downleft")) {
				grid[row + i][col - i] = word.charAt(i);
			}
		}
	}

	/*
	 * 1. According to orientation row and column, check if each word of the list fits in the grid
	 * 2. None of the letters in the grid are blank space neither the same letter we are placing in, plus:
	 * 3. If orientation is up, check that word fits from the top
	 * 4. If orientation is down, check that word fits from the bottom
	 * 5. If orientation is right, check that word fits from the right
	 * 6. If orientation is left, check that word fits from the left
	 * 7. If orientation is upright, check that word fits from the top and the right
	 * 8. If orientation is upleft, check that word fits from the top and left
	 * 9. If orientation is downright, check that word fits from the bottom and right
	 * 10. If orientation is downleft, check that word fits from the bottom and left
	 */
	//grid es un parámetro de entrada por referencia
	//word es un parámetro de entrada por valor
	//row es un parámetro de entrada por valor
	//col es un parámetro de entrada por valor
	//orientation es un parámetro de entrada por valor
	//fits es un parámetro de salida por valor
	public static boolean checkIfFits(char[][] grid, String word, int row, int col, String orientation) {
		boolean fits = true;

		//1. According to orientation row and column, check if each word of the list fits in the grid
		//2. None of the letters in the grid are blank space neither the same letter we are placing in, plus:
		for (int i = 0; i < word.length() && fits; i++) {
			//3. If orientation is up, check that word fits from the top
			if (orientation.equals("up")) {
				if (row + 1 < word.length() || grid[row - i][col] != word.charAt(i) && grid[row - i][col] != ' ') {
					fits = false;
				}
				//4. If orientation is down, check that word fits from the bottom
			} else if (orientation.equals("down")) {
				if ((grid.length - row) < word.length()
						|| grid[row + i][col] != ' ' && grid[row + i][col] != word.charAt(i)) {
					fits = false;
				}
				//5. If orientation is right, check that word fits from the right
			} else if (orientation.equals("right")) {
				if ((grid.length - col) < word.length()
						|| grid[row][col + i] != word.charAt(i) && grid[row][col + i] != ' ') {
					fits = false;
				}
				//6. If orientation is left, check that word fits from the left
			} else if (orientation.equals("left")) {
				if (col + 1 < word.length() || grid[row][col - i] != word.charAt(i) && grid[row][col - i] != ' ') {
					fits = false;
				}
				//7. If orientation is upright, check that word fits from the top and the right
			} else if (orientation.equals("upright")) {
				if ((row + 1 < word.length() || (grid.length - col) < word.length())
						|| grid[row - i][col + i] != word.charAt(i) && grid[row - i][col + i] != ' ') {
					fits = false;
				}
				//8. If orientation is upleft, check that word fits from the top and left
			} else if (orientation.equals("upleft")) {
				if ((row + 1 < word.length() || col + 1 < word.length())
						|| grid[row - i][col - i] != word.charAt(i) && grid[row - i][col - i] != ' ') {
					fits = false;
				}
				//9. If orientation is downright, check that word fits from the bottom and right
			} else if (orientation.equals("downright")) {
				if (((grid.length - row) < word.length() || (grid.length - col) < word.length())
						|| grid[row + i][col + i] != word.charAt(i) && grid[row + i][col + i] != ' ') {
					fits = false;
				}
				//10. If orientation is downleft, check that word fits from the bottom and left
			} else if (orientation.equals("downleft")) {
				if ((grid.length - row) < word.length() || col + 1 < word.length()
						|| grid[row + i][col - i] != word.charAt(i) && grid[row + i][col - i] != ' ') {
					fits = false;
				}
			}
		}
		return fits;
	}

	/*
	 * 1. Print the top border, separating the cells with a +
	 * 2. Print the content of the grid, separating the cells with a bar
	 * 3. Print the bottom row with the number of the columns
	 */
	//grid es un parámetro de entrada por referencia
	public static void showWordSearch(char[][] grid) {
		//1. Print the top border
		//It has blank space at the beginning, it corresponds with the numbers of rows that are just below
		System.out.printf("   +");
		for (int r = 0; r < grid.length; r++) {
			System.out.printf("---+");
		}
		System.out.print("\n");
		//2. Print the content of the grid, separating the cells with a bar
		//The beginning of this row corresponds with the number of the row of the grid
		for (int i = 0; i < grid.length; i++) {
			if (i < 9) {
				System.out.printf(" %d |", i + 1);
			} else {
				System.out.printf("%d |", i + 1);
			}
			for (int j = 0; j < grid.length; j++) {
				System.out.printf(" %c |", grid[i][j]);
			}
			System.out.print("\n");
			System.out.printf("   +");
			for (int r = 0; r < grid.length; r++) {
				System.out.printf("---+");
			}
			System.out.print("\n");
		}
		//3. Print the bottom row with the number of the columns
		System.out.printf("   ");
		for (int n = 1; n <= grid[0].length; n++) {
			if (n < 10) {
				System.out.printf("  %d ", n);
			} else {
				System.out.printf("  %d", n);
			}
		}
		System.out.println();
	}

	//OVERCHARGING THE FUNCTION
	/*
	 * 1. Print the top border, separating the cells with a +
	 * 2. Print the content of the grid, separating the cells with a bar
	 * 3. Print the bottom row with the number of the columns
	 */
	//grid es un parámetro de entrada por referencia
	public static void showWordSearch(String[][] grid) {
		//1. Print the top border
		//It has blank space at the beginning, it corresponds with the numbers of rows that are just below
		System.out.printf("   +");
		for (int r = 0; r < grid.length; r++) {
			System.out.printf("---+");
		}
		System.out.print("\n");
		//2. Print the content of the grid, separating the cells with a bar
		//The beginning of this row corresponds with the number of the row of the grid
		for (int i = 0; i < grid.length; i++) {
			if (i < 9) {
				System.out.printf(" %d |", i + 1);
			} else {
				System.out.printf("%d |", i + 1);
			}
			for (int j = 0; j < grid.length; j++) {
				System.out.printf(Color.ROJO + " %s |", grid[i][j] + Color.RESET);
			}
			System.out.print("\n");
			System.out.printf("   +");
			for (int r = 0; r < grid.length; r++) {
				System.out.printf("---+");
			}
			System.out.print("\n");
		}
		//3. Print the bottom row with the number of the columns
		System.out.printf("   ");
		for (int n = 1; n <= grid[0].length; n++) {
			if (n < 10) {
				System.out.printf("  %d ", n);
			} else {
				System.out.printf("  %d", n);
			}
		}
		System.out.printf("%n");
	}

	/*
	 * 1. Create template of all the available letters
	 * 2. Pick one randomly
	 * 3. If there are a blank space, insert random letter
	 */
	//charGrid es un parámetro de entrada-salida por referencia.
	public static void fill(char[][] charGrid) {
		Random random = new Random();
		//1. Create template of all the available letters
		String letters = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz";

		for (int row = 0; row < charGrid.length; row++) {
			for (int col = 0; col < charGrid[0].length; col++) {
				//3. If there are a blank space, insert random letter
				if (charGrid[row][col] == ' ') {
					//2. Pick one randomly
					charGrid[row][col] = letters.charAt(random.nextInt(letters.length()));
				}
			}
		}
	}

	/*
	 * 1. Create template of all the available letters
	 * 2. Go through all rows and columns 
	 * 3. If the cell is blank space, insert a random letter. If not, do nothing
	 * 4. In any case, go to the next cell in a recursive function
	 * 5. When it reaches the last cell, the calling ends
	 */
	//charGrid es un parámetro de entrada/salida por referencia
	//row es un parámetro de entrada por valor
	//col es un parámetro de entrada por valor
	public static void fillRecursive(char[][] charGrid, int row, int col) {
		Random random = new Random();
		//1. Create template of all the available letters
		String letters = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz";
		//2. Go through all rows and columns 
		//5. When it reaches the last cell, the calling ends
		if (row < charGrid.length) {
			if (col < charGrid[row].length) {
				//3. If the cell is blank space, insert a random letter. If not, do nothing
				if (charGrid[row][col] == ' ') {
					charGrid[row][col] = letters.charAt(random.nextInt(letters.length()));
				}
				//4. In any case, go to the next cell in a recursive function
				fillRecursive(charGrid, row, col + 1);
			} else {
				fillRecursive(charGrid, row + 1, 0);
			}
		}
	}

	//Overcharging the function for convinience. Is less easier to get wrong to code this function instead of having row and column in parameters
	//charGrid es un parámetro de entrada/salida por referencia
	public static void fillRecursive(char[][] charGrid) {
		fillRecursive(charGrid, 0, 0);
	}

	//createWordSearch will be the grid on which we are going to work. Type String[][]
	/*
	 * 1. Take the grid "fill" and transform it into String cell by cell
	 */
	//fill es un parámetro de entrada por referencia
	//mainGrid es un parámetro de salida por referencia
	public static String[][] createWordSearch(char[][] fill) throws NullPointerException {
		String mainGrid[][] = new String[fill.length][fill[0].length];
		//1. Take the grid fill and transform it into String cell by cell
		for (int row = 0; row < fill.length; row++) {
			for (int col = 0; col < fill[0].length; col++) {
				mainGrid[row][col] = Character.toString(fill[row][col]);
			}
		}
		return mainGrid;
	}

	/*
	 * 1. Calculate the number of words that there are in the grid
	 * 2. Ask for data (word, row, column, orientation)
	 *    2.1 If user gets right, words in game reduce by one.
	 * 3. Show changes
	 * 4. Repeat until user wants to exit, or until the number of words in game is zero
	 * 5. Show score
	 */
	//mainGrid es un parámetro de entrada/salida por referencia
	//wordsPetition es un parámetro de entrada/salida por referencia
	public static void play(String[][] mainGrid, String[][] wordsPetition) throws NullPointerException {
		int counter = 0, wordsInGame = 0;
		boolean exit = false;
		if (mainGrid == null || wordsPetition == null) {
			throw new NullPointerException();
		}
		//1. Calculate the number of words that there are in the grid
		for (int i = 0; i < wordsPetition.length; i++) {
			if (wordsPetition[i][4].equals("notfound")) {
				wordsInGame++;
			}
		}
		if (wordsInGame == 0) {
			System.out.println("No game. You have entered no valid words.");
			//2. Ask for data (word, row, column, orientation)
		} else {
			System.out.printf("%d words in game%n", wordsInGame);
			do {
				counter++;
				System.out.printf("Attempt %d%n", counter);
				//2.1 If user gets right, words in game reduce by one.
				//Enter data is boolean because we need to know if it is valid, in order to decrement the words in game
				if (enterData(mainGrid, wordsPetition)) {
					wordsInGame--;
				}
				//3. Show changes
				showWordSearch(mainGrid);
				//When we finish the word search, we exit the game
				if (wordsInGame > 0) {
					System.out.printf("%d words in game%n", wordsInGame);
					exit = readBoolean("Exit?");
				} else {
					System.out.println("Word search completed. Congrats!!!");
					exit = true;
				}
				//4. Repeat until user wants to exit, or until the number of words in game is zero
			} while (!exit);
			//5. Show score
			wordsControl(wordsPetition);
			recount(wordsPetition, counter, mainGrid);
		}

	}

	/*
	 * 1. Ask user for word, row, column and orientation
	 * 2. Check if entered data is valid according to the wordsPetition table
	 * 3. If it is, change color letter to blue
	 */
	//We will make enterData boolean because we need to know if the word entered is valid or not
	//mainGrid es un parámetro de entrada/salida por referencia
	//wordsPetition es un parámetro de entrada/salida por referencia
	//validWord es un parámetro de salida por valor
	public static boolean enterData(String[][] mainGrid, String[][] wordsPetition) {
		int aux;
		String word, row, col, orientation;
		boolean validWord = false;

		//1. Ask user for word, row, column and orientation
		//Asking for the word
		do {
			System.out.println("Enter word: ");
			word = readString();
		} while (!word.matches("[A-ZÑ][a-zñ]+([A-ZÑ]{1,2}[a-zñ]+)*") || word.length() > mainGrid.length);

		//Asking for row
		//In string because later we will compare it with wordsPetition, which is in String format
		System.out.println("Enter row: ");
		aux = readRange(1, mainGrid.length, Range.II);
		row = Integer.toString(aux);

		//Asking for column
		System.out.println("Enter column: ");
		aux = readRange(1, mainGrid.length, Range.II);
		col = Integer.toString(aux);

		//Asking for orientation
		do {
			System.out.printf("Enter orientation (up, down, left, right, upleft, upright, downleft, downright): ");
			orientation = readString().toLowerCase();
		} while (!orientation.equals("up") && !orientation.equals("down") && !orientation.equals("left")
				&& !orientation.equals("right") && !orientation.equals("upleft") && !orientation.equals("upright")
				&& !orientation.equals("downleft") && !orientation.equals("downright"));
		//2. Check if entered data is valid according to the wordsPetition table
		if (checkPlay(wordsPetition, word, row, col, orientation)) {
			//3. If it is, change color letter to blue
			printBlue(mainGrid, word.length(), Integer.parseInt(row) - 1, Integer.parseInt(col) - 1, orientation);
			validWord = true;
		}
		return validWord;
	}

	/*
	 * 1. Look for the word in the array and check that the rest of the parameters (row, column and orientation) are correct
	 * 2. If they are, indicate it in the auxiliary column 5 of the array inserting a "found"
	 */
	//wordsPetition es un parámetro de entrada/salida por referencia
	//word es un parámetro de entrada por valor
	//row es un parámetro de entrada por valor
	//col es un parámetro de entrada por valor
	//orientation es un parámetro de entrada por valor
	//isWord es un parámetro de salida por valor
	public static boolean checkPlay(String[][] wordsPetition, String word, String row, String col, String orientation)
			throws NullPointerException {
		final int WORD = 0, ROW = 1, COLUMN = 2, ORIENTATION = 3, AUXILIARY = 4;
		boolean isWord = false;
		//1. Look for the word in the array and check that the rest of the parameters (row, column and orientation) are correct
		for (int i = 0; i < wordsPetition.length; i++) {
			//2. If they are, indicate it in the auxiliary column 5 of the array inserting a "found"
			if (wordsPetition[i][WORD].equals(word) && wordsPetition[i][ROW].equals(row)
					&& wordsPetition[i][COLUMN].equals(col) && wordsPetition[i][ORIENTATION].equals(orientation)
					&& wordsPetition[i][AUXILIARY].equals("notfound")) {
				wordsPetition[i][AUXILIARY] = "found";
				isWord = true;
			}
		}
		return isWord;
	}

	/*
	 * 1. According to word length, orientation, row and column, add green color to the word
	 * 2. If orientation is up, column decrements
	 * 3. If orientation is down, column increments
	 * 4. If orientation is right, row increments
	 * 5. If orientation is left, row decrements
	 * 6. If orientation is upright, row increments and column decrements
	 * 7. If orientation is upleft, row decrements and column decrements
	 * 8. If orientation is downright, row increments and column increments
	 * 9. If orientation is downleft, row decrements and column increments
	 */
	//mainGrid es un parámetro de entrada/salida por referencia
	//wordlength es un parámetro de entrada por valor
	//row es un parámetro de entrada por valor
	//col es un parámetro de entrada por valor
	//orientation es un parámetro de entrada por valor
	public static void printBlue(String[][] mainGrid, int wordlength, int row, int col, String orientation) {
		for (int i = 0; i < wordlength; i++) {
			//2. If orientation is up, column decrements
			if (orientation.equals("up")) {
				mainGrid[row - i][col] = Color.CELESTE + mainGrid[row - i][col] + Color.RESET;
				//3. If orientation is down, column increments
			} else if (orientation.equals("down")) {
				mainGrid[row + i][col] = Color.CELESTE + mainGrid[row + i][col] + Color.RESET;
				//4. If orientation is right, row increments
			} else if (orientation.equals("right")) {
				mainGrid[row][col + i] = Color.CELESTE + mainGrid[row][col + i] + Color.RESET;
				//5. If orientation is left, row decrements
			} else if (orientation.equals("left")) {
				mainGrid[row][col - i] = Color.CELESTE + mainGrid[row][col - i] + Color.RESET;
				//6. If orientation is upright, row increments and column decrements
			} else if (orientation.equals("upright")) {
				mainGrid[row - i][col + i] = Color.CELESTE + mainGrid[row - i][col + i] + Color.RESET;
				//7. If orientation is upleft, row decrements and column decrements
			} else if (orientation.equals("upleft")) {
				mainGrid[row - i][col - i] = Color.CELESTE + mainGrid[row - i][col - i] + Color.RESET;
				//8. If orientation is downright, row increments and column increments
			} else if (orientation.equals("downright")) {
				mainGrid[row + i][col + i] = Color.CELESTE + mainGrid[row + i][col + i] + Color.RESET;
				//9. If orientation is downleft, row decrements and column increments
			} else if (orientation.equals("downleft")) {
				mainGrid[row + i][col - i] = Color.CELESTE + mainGrid[row + i][col - i] + Color.RESET;
			}
		}
	}

	/*
	 * 1. Calculate number of words found and not found
	 * 2. Words backwards score twice
	 * 3. Implement a score system
	 */
	//wordsPetition es un parámetro de entrada por referencia
	//counter es un parámetro de entrada por valor
	//mainGrid es un parámetro de entrada por referencia
	public static void recount(String[][] wordsPetition, int counter, String[][] mainGrid) {
		int found = 0, notFound = 0;
		final int AUXILIARY = 4, ORIENTATION = 3;
		//1. Calculate number of words found and not found
		for (int i = 0; i < wordsPetition.length; i++) {
			if (wordsPetition[i][AUXILIARY].equals("found")) {
				found++;
				//2. Words backwards and up-right score twice
				if (wordsPetition[i][ORIENTATION].equals("left") && wordsPetition[i][ORIENTATION].equals("upleft")
						&& wordsPetition[i][ORIENTATION].equals("downleft")
						&& wordsPetition[i][ORIENTATION].equals("upright")) {
					found++;
				}
			} else if (wordsPetition[i][AUXILIARY].equals("notfound")) {
				notFound++;
			}
		}
		//3. Implement a score system
		System.out.printf(
				"You have had %d attempts%nYou have found %d words and you have missed %d words%nYour score is %d points%n",
				counter, found, notFound, (found * mainGrid.length * 100) / ((found + notFound) * counter));
	}

	/*
	 * 1. For each row and column, copy value cell by cell
	 */
	//copy es un parámetro de entrada/salida por referencia
	//a es un parámetro de entrada por referencia
	public static void copyArray(String[][] copy, String[][] a) throws NullPointerException {
		//1. For each row and column, copy value cell by cell
		for (int row = 0; row < a.length; row++) {
			for (int col = 0; col < a[0].length; col++) {
				copy[row][col] = a[row][col];
			}
		}
	}

	/*
	 * 1. Identify if there are any "notfound", "found" or "error"
	 * 2. If there are any "not found", identify them
	 * 3. If there are any "found", identify them
	 * 4. If there are any "error", identify them
	 */
	//wordsPetition es un parámetro de entrada por referencia
	public static void wordsControl(String[][] wordsPetition) throws IllegalArgumentException {
		if (wordsPetition == null) {
			throw new IllegalArgumentException("Error. Words entered are not valid");
		}
		final int WORD = 0, AUXILIARY = 4;
		boolean found = false, notfound = false, error = false;
		for (int row = 0; row < wordsPetition.length; row++) {
			if (wordsPetition[row][AUXILIARY].equals("notfound") && !notfound) {
				notfound = true;
			} else if (wordsPetition[row][AUXILIARY].equals("found") && !found) {
				found = true;
			} else if (wordsPetition[row][AUXILIARY].equals("error") && !error) {
				error = true;
			}
		}

		if (error) {
			System.out.println("Words that could not be put in grid: ");
			for (int row = 0; row < wordsPetition.length; row++) {
				if (wordsPetition[row][AUXILIARY].equals("error")) {
					System.out.println(wordsPetition[row][WORD]);
				}
			}
		}
		if (found) {
			System.out.println("Words found: ");
			for (int row = 0; row < wordsPetition.length; row++) {
				if (wordsPetition[row][AUXILIARY].equals("found")) {
					System.out.println(wordsPetition[row][WORD]);
				}
			}
		}
		if (notfound) {
			System.out.println("Words left to find: ");
			for (int row = 0; row < wordsPetition.length; row++) {
				if (wordsPetition[row][AUXILIARY].equals("notfound")) {
					System.out.println(wordsPetition[row][WORD]);
				}
			}
		}
	}
}
