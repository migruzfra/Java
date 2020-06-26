package mosaic;

/**
 * @author Miguel Angel
 *
 */
public class TriangleUp extends Triangle {

	/**
	 * Constructs an up triangle given its color and background
	 * 
	 * @param color      Color of the body of the up triangle
	 * @param background Color of the background of the up triangle
	 * @throws IllegalFigureCreationException Controls the color and the background
	 *                                        to be different
	 */
	public TriangleUp(Color color, Color background) throws IllegalFigureCreationException {
		super(color, background);
	}

	/**
	 * Clones an up triangle
	 * 
	 * @param triangleUp The up triangle to be cloned
	 * @throws IllegalFigureCreationException Controls the color and the background
	 *                                        to be different
	 */
	protected TriangleUp(TriangleUp triangleUp) throws IllegalFigureCreationException {
		super(triangleUp);
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
		return String.format("Triangle up%n");
	}

	@Override
	public boolean equals(Object obj) {
		boolean equals = false;
		if ((obj instanceof TriangleUp) && ((TriangleUp) obj).color == color
				&& ((TriangleUp) obj).background == background) {
			equals = true;
		}
		return equals;
	}

	/**
	 * Draws a line of the up triangle
	 */
	/*
	 * 1. First prints the background and then prints the triangle.
	 *    Triangle decreases while line increases
	 * 2. The background for the current line corresponds with the line
	 * 3. Figure will be the difference between the total length of the triangle and the cells of the background.
	 */
	@Override
	public void draw() {
		for (int i = 0; i < line; i++) {
			System.out.printf(background.getColor() + "  " + Color.RESET.getColor());
		}
		for (int i = h - line; i > 0; i--) {
			System.out.printf(color.getColor() + "  " + Color.RESET.getColor());
		}

	}
}
