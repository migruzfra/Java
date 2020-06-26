package mosaic;

/**
 * @author Miguel Angel
 *
 */
public class Cross extends Figure {
	/**
	 * Indicates how many blank spaces are going to be painted at the current line
	 * 
	 */
	private int spaces;

	/**
	 * Constructs a cross given its color and background
	 * 
	 * @param color      Color of the body of the cross
	 * @param background Color of the background of the cross
	 * @throws IllegalFigureCreationException Controls the color and the background
	 *                                        to be different
	 */
	public Cross(Color color, Color background) throws IllegalFigureCreationException {
		super(color, background);
	}

	/**
	 * Clones a cross
	 * 
	 * @param cross The cross to be cloned
	 * @throws IllegalFigureCreationException Controls the color and the background
	 *                                        to be different
	 */
	protected Cross(Cross cross) throws IllegalFigureCreationException {
		super(cross);
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public void setColorBackground(Color colorBackground) {
		this.background = colorBackground;
	}

	@Override
	public String toString() {
		return String.format("Cross%n");
	}

	@Override
	public boolean equals(Object obj) {
		boolean equals = false;
		if ((obj instanceof Cross) && ((Cross) obj).color == color && ((Cross) obj).background == background) {
			equals = true;
		}
		return equals;
	}

	/*
	 * 1. Determine the number of blank spaces
	 * 2. If the current line corresponds with the top of the cross, draw that line from the top of the cross 
	 * 3. If the current line corresponds with the center of the cross, draw that line from the center of the cross 
	 * 4. If the current line corresponds with the bottom of the cross, draw that line from the bottom of the cross 
	 */
	@Override
	public void draw() {
		determineSpacesCross();
		if (line < spaces || h - line - 1 < spaces) {//Top and bottom of the cross are drawn in the same way
			drawTopAndBottomCross();
		} else {//Center of the cross
			drawCenterCross();
		}
	}

	/**
	 * Determines the number of blank spaces of the cross
	 */
	//For aesthetic reasons, the number of blank spaces for crosses with height of 5 or 7 will be smaller
	private void determineSpacesCross() {
		if (h <= 7) {
			spaces = (h - 1) / 2;
		} else {
			spaces = h / 2 - 1;
		}
	}

	/**
	 * Draws a top or bottom line of the cross
	 */
	/*
	 * 1. First print white spaces, then print the figure, and then print the same amount of blank spaces.
	 *    Blank spaces increase while line decreases
	 * 2. The number of blank spaces will be the same for the specified figure
	 * 3. The figure block will be the difference between the total length of the figure and the two blocks of blank spaces
	 */
	private void drawTopAndBottomCross() {
		for (int i = spaces; i > 0; i--) {
			System.out.printf(background.getColor() + "  " + Color.RESET.getColor());
		}
		for (int i = h - 2 * spaces; i > 0; i--) {
			System.out.print(color.getColor() + "  ");
		}
		for (int i = spaces; i > 0; i--) {
			System.out.print(background.getColor() + "  " + Color.RESET.getColor());
		}
	}

	/**
	 * Draws a center line of the cross
	 */
	/*
	 * 1. Print all with the color of the figure
	 */
	private void drawCenterCross() {
		for (int i = 0; i < h; i++) {
			System.out.print(color.getColor() + "  " + Color.RESET.getColor());
		}
	}
}
