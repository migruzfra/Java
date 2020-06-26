package mosaic;

/**
 * @author Miguel Angel
 *
 */
public class TriangleDown extends Triangle {

	/**
	 * Constructs a down triangle given its color and background
	 * 
	 * @param color      Color of the body of the down triangle
	 * @param background Color of the background of the down triangle
	 * @throws IllegalFigureCreationException Controls the color and the background
	 *                                        to be different
	 */
	public TriangleDown(Color color, Color background) throws IllegalFigureCreationException {
		super(color, background);
	}

	/**
	 * Clones a down triangle
	 * 
	 * @param triangleDown The down triangle to be cloned
	 * @throws IllegalFigureCreationException Controls the color and the background
	 *                                        to be different
	 */
	protected TriangleDown(TriangleDown triangleDown) throws IllegalFigureCreationException {
		super(triangleDown);
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
		return String.format("Triangle down%n");
	}

	@Override
	public boolean equals(Object obj) {
		boolean equals = false;
		if ((obj instanceof TriangleDown) && ((TriangleDown) obj).color == color
				&& ((TriangleDown) obj).background == background) {
			equals = true;
		}
		return equals;
	}

	/**
	 * Draws a line of the down triangle
	 */
	/*
	 * 1. First print the figure and then print the rest of the figure with background.
	 *    Triangle increases while line decreases
	 * 2. The figure for the current line corresponds with the line
	 * 3. Background will be the difference between the total length of the triangle and the cells of the triangle.
	 */
	@Override
	public void draw() {
		for (int i = 0; i <= line; i++) {
			System.out.printf(color.getColor() + "  " + Color.RESET.getColor());
		}
		for (int i = h - line - 1; i > 0; i--) {
			System.out.printf(background.getColor() + "  " + Color.RESET.getColor());
		}

	}
}
