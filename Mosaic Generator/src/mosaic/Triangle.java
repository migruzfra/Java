package mosaic;

/**
 * @author Miguel Angel
 *
 */
public abstract class Triangle extends Figure {

	/**
	 * Constructs a triangle given its color and background
	 * 
	 * @param color      Color of the body of the triangle
	 * @param background Color of the background of the triangle
	 * @throws IllegalFigureCreationException Controls the color and the background
	 *                                        to be different
	 */
	public Triangle(Color color, Color background) throws IllegalFigureCreationException {
		super(color, background);
	}

	public Triangle(Triangle triangle) throws IllegalFigureCreationException {
		super(triangle);
	}
}
