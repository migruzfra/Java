package mosaic;

/**
 * @author Miguel Angel
 *
 */
public class Rhombus extends Figure {

	/**
	 * Constructs a rhombus given its color and background
	 * 
	 * @param color      Color of the body of the rhombus
	 * @param background Color of the background of the rhombus
	 * @throws IllegalFigureCreationException Controls the color and the background
	 *                                        to be different
	 */
	public Rhombus(Color color, Color background) throws IllegalFigureCreationException {
		super(color, background);
	}

	/**
	 * Clones a rhombus
	 * 
	 * @param rhombus The rhombus to be cloned
	 * @throws IllegalFigureCreationException Controls the color and the background
	 *                                        to be different
	 */
	protected Rhombus(Rhombus rhombus) throws IllegalFigureCreationException {
		super(rhombus);
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
		return String.format("Rhombus%n");
	}

	@Override
	public boolean equals(Object obj) {
		boolean equals = false;
		if ((obj instanceof Rhombus) && ((Rhombus) obj).color == color && ((Rhombus) obj).background == background) {
			equals = true;
		}
		return equals;
	}

	/*
	 * 1. Determine the maximum number of blank spaces
	 * 2. If the current line corresponds with the top of the rhombus, draw that line from the top of the rhombus 
	 * 3. If the current line corresponds with the center of the rhombus, draw that line from the center of the rhombus 
	 * 4. If the current line corresponds with the bottom of the rhombus, draw that line from the bottom of the rhombus 
	 */
	@Override
	public void draw() {
		int maxSpaces = h / 2;
		if (line == h / 2) {//Center of the rhombus
			drawCenterRhombus();
		} else if (line < h / 2) {//Top of the rhombus
			drawTopRhombus(maxSpaces);
		} else {//Bottom of the rhombus
			drawBottomRhombus(maxSpaces);
		}
	}

	/**
	 * Draws the top of the rhombus
	 */
	/*
	 * 1. First print white spaces, then print the figure, and then print the same amount of blank spaces.
	 *    Blank spaces decrease while line increases
	 * 2. The number of blank spaces for the current line obeys the formula: maximum number of spaces - current line
	 * 3. The figure will be the difference between the total length of the figure and the two blocks of blank spaces
	 */
	private void drawTopRhombus(int maxSpaces) {
		for (int i = maxSpaces - line; i > 0; i--) {
			System.out.printf(background.getColor() + "  ");
		}
		for (int i = h - 2 * (maxSpaces - line); i > 0; i--) {
			System.out.print(color.getColor() + "  ");
		}
		for (int i = maxSpaces - line; i > 0; i--) {
			System.out.print(background.getColor() + "  " + Color.RESET.getColor());
		}
	}

	/**
	 * Draws a bottom line of the rhombus
	 */
	/*
	 * 1. First print white spaces, then print the figure, and then print the same amount of blank spaces.
	 *    Blank spaces increase while line decreases
	 * 2. The number of blank spaces for the current line obeys the formula: maximum number of spaces + current line - height
	 * 3. The figure will be the difference between the total length of the figure and the two blocks of blank spaces
	 */
	private void drawBottomRhombus(int maxSpaces) {
		for (int i = maxSpaces + (line + 1) - h; i > 0; i--) {
			System.out.printf(background.getColor() + "  ");
		}
		for (int i = (h - line) * 2 - 1; i > 0; i--) {
			System.out.print(color.getColor() + "  ");
		}
		for (int i = maxSpaces + (line + 1) - h; i > 0; i--) {
			System.out.print(background.getColor() + "  " + Color.RESET.getColor());
		}
	}

	/**
	 * Draws the center line of the rhombus
	 */
	/*
	 * 1. Print all with the color of the figure
	 */
	private void drawCenterRhombus() {
		for (int i = 0; i < h; i++) {
			System.out.print(color.getColor() + "  " + Color.RESET.getColor());
		}
	}
}
