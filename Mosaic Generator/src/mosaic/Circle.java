package mosaic;

/**
 * @author Miguel Angel
 *
 */
public class Circle extends Figure {

	/**
	 * Constructs a circle given its color and background
	 * 
	 * @param color      Color of the body of the circle
	 * @param background Color of the background of the circle
	 * @throws IllegalFigureCreationException Controls the color and the background
	 *                                        to be different
	 */
	public Circle(Color color, Color background) throws IllegalFigureCreationException {
		super(color, background);
	}

	/**
	 * Clones a circle
	 * 
	 * @param circle The circle to be cloned
	 * @throws IllegalFigureCreationException Controls the color and the background
	 *                                        to be different
	 */
	protected Circle(Circle circle) throws IllegalFigureCreationException {
		super(circle);
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
		return String.format("Circle%n");
	}

	@Override
	public boolean equals(Object obj) {
		boolean equals = false;
		if ((obj instanceof Circle) && ((Circle) obj).color == color && ((Circle) obj).background == background) {
			equals = true;
		}
		return equals;
	}

	/*
	 * 1. Determine the maximum number of blank spaces
	 * 2. If the current line corresponds with the top of the circle, draw that line from the top of the circle 
	 * 3. If the current line corresponds with the center of the circle, draw that line from the center of the circle 
	 * 4. If the current line corresponds with the bottom of the circle, draw that line from the bottom of the circle 
	 */
	@Override
	public void draw() {
		int maxSpaces;
		maxSpaces = (h / 2 + 1) / 2;
		if (line < maxSpaces) {//Top of the circle
			drawTopCircle(maxSpaces);
		} else if (h - line <= maxSpaces) {//Bottom of the circle
			drawBottomCircle(maxSpaces);
		} else {//Center of the circle
			drawCenterCircle();
		}
	}

	/**
	 * Draws the top of the circle
	 */
	/*
	 * 1. First print white spaces, then print the figure, and then print the same amount of blank spaces.
	 *    Blank spaces decrease while line increases
	 * 2. The number of blank spaces for the current line obeys the formula: maximum number of spaces - current line
	 * 3. The figure will be the difference between the total length of the figure and the two blocks of blank spaces
	 */
	private void drawTopCircle(int maxSpaces) {
		for (int i = maxSpaces - line; i > 0; i--) {
			System.out.printf(background.getColor() + "  ");
		}
		for (int i = h - 2 * (maxSpaces - line); i >= 1; i--) {
			System.out.print(color.getColor() + "  ");
		}
		for (int i = maxSpaces - line; i >= 1; i--) {
			System.out.print(background.getColor() + "  " + Color.RESET.getColor());
		}
	}

	/**
	 * Draws a bottom line of the circle
	 */
	/*
	 * 1. First print white spaces, then print the figure, and then print the same amount of blank spaces.
	 *    Blank spaces increase while line decreases
	 * 2. The number of blank spaces for the current line obeys the formula: maximum number of spaces + current line - height
	 * 3. The figure will be the difference between the total length of the figure and the two blocks of blank spaces
	 */
	private void drawBottomCircle(int maxSpaces) {
		for (int i = maxSpaces + (line + 1) - h; i > 0; i--) {
			System.out.print(background.getColor() + "  ");
		}
		for (int i = h - 2 * (maxSpaces + (line + 1) - h); i > 0; i--) {
			System.out.print(color.getColor() + "  ");
		}
		for (int i = maxSpaces + (line + 1) - h; i > 0; i--) {
			System.out.print(background.getColor() + "  " + Color.RESET.getColor());
		}
	}

	/**
	 * Draws a center line of the circle
	 */
	/*
	 * 1. Print all with the color of the figure
	 */
	private void drawCenterCircle() {
		for (int i = 0; i < h; i++) {
			System.out.print(color.getColor() + "  " + Color.RESET.getColor());
		}
	}

}
