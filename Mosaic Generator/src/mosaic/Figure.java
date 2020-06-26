package mosaic;

/**
 * @author Miguel Angel
 *
 */
public abstract class Figure implements Drawable {
	/**
	 * Height of the figure
	 */
	static int h;
	/**
	 * Current line of the figure
	 */
	static int line;
	/**
	 * Color of the body of the figure
	 */
	Color color;
	/**
	 * Color of the background of the figure
	 */
	Color background;

	/**
	 * Constructs a figure given its color and background
	 * 
	 * @param color      Color of the body of the figure
	 * @param background Color of the background of the figure
	 * @throws IllegalFigureCreationException Controls the color and the background
	 *                                        to be different
	 */
	public Figure(Color color, Color background) throws IllegalFigureCreationException {
		if (color == background) {
			throw new IllegalFigureCreationException("The color of the figure and the background must not be the same");
		}
		this.color = color;
		this.background = background;
	}

	protected Figure(Figure figure) throws IllegalFigureCreationException {
		this(figure.color, figure.background);
	}

	/**
	 * Sets a specified color of the figure
	 * 
	 * @param color Color of the body of the figure which is going to be set
	 */
	protected void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Sets a specified background of the figure
	 * 
	 * @param colorBackground Color of the background of the figure which is going
	 *                        to be set
	 */
	protected void setColorBackground(Color colorBackground) {
		this.background = colorBackground;
	}

	@Override
	public boolean equals(Object obj) {
		boolean equals = false;
		if ((obj instanceof Figure) && ((Figure) obj).color.equals(color)
				&& ((Figure) obj).background.equals(background)) {
			equals = true;
		}
		return equals;
	}

	@Override
	public abstract String toString();

	public abstract void draw();
}
