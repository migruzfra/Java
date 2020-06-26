package mosaic;

import static keyboard.Keyboard.*;
import java.util.Random;

/**
 * @author Miguel Angel
 *
 */
public class Menu {
	/**
	 * Random utility
	 */
	private Random random = new Random();
	/**
	 * Original mosaic of the application
	 */
	private Mosaic mosaic;
	/**
	 * List of figures set in the mosaic
	 */
	private String figuresList = "";
	/**
	 * Indicates if the mosaic is being created randomly or not
	 */
	private boolean isRandom;

	/**
	 * Displays the menu for creating a mosaic, showing or cloning it
	 */
	public void runMenu() {
		int select;
		boolean created = false;
		do {
			select = readRange("1. Create mosaic\n2. Show mosaic\n3. Clone mosaic\n4. Exit", 1, 4, Range.II);
			if (!created && (select == 2 || select == 3)) {
				do {
					select = readRange("You have to create a mosaic first", 1, 4, Range.II);
				} while (select == 2 || select == 3);
			}
			if (select == 1) {
				if (readRange("1. Configuration mode\n2. Random mode", 1, 2, Range.II) == 1) {
					isRandom = false;
				} else {
					isRandom = true;
				}

				try {
					createMosaic();
					created = true;
				} catch (IllegalMosaicCreationException e) {
					System.out.println(e);
				}
			} else if (select == 2) {
				showMosaic();
			} else if (select == 3) {
				cloneMosaic();
			} else if (select == 4) {
				System.out.println("Bye");
			}
		} while (select != 4);
	}

	/**
	 * Sets the height of all the figures of the mosaic
	 * 
	 */
	private void enterH() {
		if (!isRandom) {
			do {
				Figure.h = readRange("Enter height of each figure (odd number between 5 and 21):", 5, 21, Range.II);
			} while (Figure.h % 2 == 0);
		} else {
			do {
				Figure.h = random.nextInt(6) + 5;
			} while (Figure.h % 2 == 0);
		}

	}

	/**
	 * Shows menu for picking one of the figures available
	 * 
	 * @return A number associated with each figure
	 */
	private int menuFigure() {
		if (!isRandom) {
			return readRange("Select a figure:\n1. Circle\n2. Triangle Up\n3. Triangle Down\n4. Cross\n5. Rhombus", 1,
					5, Range.II);
		} else {
			return random.nextInt(5) + 1;
		}

	}

	/**
	 * Picks a color
	 * 
	 * @param isRandom Indicates if the mosaic is being created randomly or not
	 * @return The color selected
	 */
	private Color selectColor(boolean isRandom) {
		int aux;
		if (!isRandom) {
			aux = readRange(
					"1. Black\n2. White\n3. Grey\n4. Dark grey\n5. Red\n6. Dark red\n7. Blue\n8. Dark blue\n9. Cyan\n10. Jade\n11. Green\n12. Dark green\n13. Yellow\n14. Ocher\n15. Purple\n16. Dark purple",
					1, 16, Range.II);
		} else {
			aux = random.nextInt(16) + 1;
		}
		switch (aux) {
		case 1:
			return Color.BLACK;
		case 2:
			return Color.WHITE;
		case 3:
			return Color.GREY;
		case 4:
			return Color.DARKGREY;
		case 5:
			return Color.RED;
		case 6:
			return Color.DARKRED;
		case 7:
			return Color.BLUE;
		case 8:
			return Color.DARKBLUE;
		case 9:
			return Color.CYAN;
		case 10:
			return Color.JADE;
		case 11:
			return Color.GREEN;
		case 12:
			return Color.DARKGREEN;
		case 13:
			return Color.YELLOW;
		case 14:
			return Color.OCHER;
		case 15:
			return Color.PURPLE;
		case 16:
			return Color.DARKPURPLE;
		default:
			return Color.BLACK;
		}
	}

	/**
	 * Shows error message in case of picking same color for figure and background
	 */
	private void errorSameColorAndBackground() {
		System.out.println("Figure and background must have different colors.");
	}

	/**
	 * Creates a mosaic
	 * 
	 * @throws IllegalMosaicCreationException Controls the creation of a mosaic in
	 */
	private void createMosaic() throws IllegalMosaicCreationException {

		int rows, cols;
		if (!isRandom) {
			rows = readRange("Enter number of rows of the mosaic (between 1 and 5)", 1, 5, Range.II);
			cols = readRange("Enter number of columns of the mosaic (between 1 and 5)", 1, 5, Range.II);
		} else {
			rows = random.nextInt(5) + 1;
			cols = random.nextInt(5) + 1;
		}

		enterH();

		mosaic = new Mosaic(rows, cols, fillFigures(rows, cols));

	}

	/**
	 * Draws a mosaic
	 */
	private void showMosaic() {
		mosaic.draw();
	}

	/**
	 * Creates the list of figures to be inserted in the mosaic
	 * 
	 * @param rowsMosaic Rows of the mosaic
	 * @param colsMosaic Columns of the mosaic
	 * 
	 * @return Array of figures to be inserted in the mosaic
	 */
	private Figure[] fillFigures(int rowsMosaic, int colsMosaic) {
		int numFigures, maxFigures = 5;

		if (rowsMosaic * colsMosaic < 5) {
			maxFigures = rowsMosaic * colsMosaic;
		}
		numFigures = enterNumFigures(rowsMosaic, colsMosaic, maxFigures);

		return fillListAndSelectFigures(numFigures);
	}

	/**
	 * Fills the array of figures to be inserted in the mosaic
	 * 
	 * @param numFigures Number of different figures the mosaic has
	 * @return Array of figures to be inserted in the mosaic
	 */
	private Figure[] fillListAndSelectFigures(int numFigures) {
		Color color, background;
		boolean check, created;
		int menu;
		int[] repeated = new int[numFigures];
		Figure[] figures = new Figure[numFigures];

		for (int k = 0; k < figures.length; k++) {
			do {
				check = true;
				menu = menuFigure();
				for (int l = 0; l < figures.length; l++) {
					if (repeated[l] == menu) {
						check = false;
					}
				}
			} while (!check);
			repeated[k] = menu;
			do {
				if (!isRandom) {
					System.out.println("Color of the figure: ");
				}
				color = selectColor(isRandom);
				if (!isRandom) {
					System.out.println("Background color: ");
				}
				background = selectColor(isRandom);
				if (color == background && !isRandom) {
					errorSameColorAndBackground();
				}
			} while (color == background);
			do {
				created = true;
				try {
					figures[k] = createFigure(menu, color, background);
				} catch (IllegalFigureCreationException e) {
					System.out.println(e);
					created = false;
				}
			} while (!created);

			figuresList += String.format("%d. %s", k + 1, figures[k].toString());
		}
		return figures;
	}

	/**
	 * Creates a figure according to the selection, color and background
	 * 
	 * @param menu       Selection of the menu which decides what figure will be
	 *                   created
	 * @param color      Color of the body of the figure
	 * @param background Color of the background of the figure
	 * 
	 * @return The figure selected
	 * @throws IllegalFigureCreationException Controls the color and the background
	 *                                        to be different
	 */
	private Figure createFigure(int menu, Color color, Color background) throws IllegalFigureCreationException {
		if (menu == 1) {
			return new Circle(color, background);
		} else if (menu == 2) {
			return new TriangleUp(color, background);
		} else if (menu == 3) {
			return new TriangleDown(color, background);
		} else if (menu == 4) {
			return new Cross(color, background);
		} else {
			return new Rhombus(color, background);
		}
	}

	/**
	 * Picks the number of different figures of the mosaic
	 * 
	 * @param rowsMosaic Rows of the mosaic
	 * @param colsMosaic Columns of the mosaic
	 * @param maxFigures Maximum number of different figures
	 * 
	 * @return Number of different figures of the mosaic
	 */
	private int enterNumFigures(int rowsMosaic, int colsMosaic, int maxFigures) {

		int numFigures;
		if (!isRandom) {
			System.out.printf("Enter how many different figures will have the mosaic, between 1 and %d%n", maxFigures);
			numFigures = readRange(1, maxFigures, Range.II);

		} else {
			numFigures = random.nextInt(maxFigures) + 1;
		}

		return numFigures;
	}

	/**
	 * Clones a mosaic
	 */
	private void cloneMosaic() {
		Mosaic mosaicClon = null;
		try {
			mosaicClon = new Mosaic(mosaic);
			compareTwoMosaics(mosaicClon);
			changeSomeFigure(mosaicClon);
			compareTwoMosaics(mosaicClon);
		} catch (IllegalFigureCreationException e) {
			System.out.println(e);
		}
	}

	/**
	 * Makes a comparison of two mosaics
	 * 
	 * @param mosaicClon The mosaic we want to compare to
	 */
	private void compareTwoMosaics(Mosaic mosaicClon) {
		System.out.println("Original:");
		mosaic.draw();
		System.out.println("Clon:");
		mosaicClon.draw();
		System.out.printf("mosaic.equals(mosaicClon) --- %s%n", mosaic.equals(mosaicClon));
		System.out.printf("mosaic==mosaicClon --- %s%n", mosaic == mosaicClon);

	}

	/**
	 * Changes the color of a figure of the mosaic
	 * 
	 * @param mosaic The mosaic that will be changed
	 */
	private void changeSomeFigure(Mosaic mosaic) {
		int position;
		Color color;

		System.out.printf("Let's change the color of a figure.%n%s", figuresList);
		position = readRange(1, mosaic.figures.length, Range.II);

		if (readRange("1. Color figure\n2. Background color", 1, 2, Range.II) == 1) {
			color = selectColor(false);
			while (color == mosaic.figures[position - 1].background) {
				System.out.println("Error. The background has the same color as the selected one. Try again");
				color = selectColor(false);
			}
			mosaic.figures[position - 1].setColor(color);
		} else {
			color = selectColor(false);
			while (color == mosaic.figures[position - 1].color) {
				System.out.println("Error. The figure has the same color as the background selected. Try again");
				color = selectColor(false);
			}
			mosaic.figures[position - 1].setColorBackground(color);
		}
	}

}
