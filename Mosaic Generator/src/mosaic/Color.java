package mosaic;

/**
 * @author Miguel Angel
 *
 */
public enum Color {

	/**
	 * Reset color
	 */
	RESET("\u001B[0m"),
	/**
	 * Black
	 */
	BLACK("\u001B[40m"),
	/**
	 * White
	 */
	WHITE("\033[0;107m"),
	/**
	 * Grey
	 */
	GREY("\u001B[47m"),
	/**
	 * Dark grey
	 */
	DARKGREY("\033[0;100m"),
	/**
	 * Red
	 */
	RED("\033[0;101m"),
	/**
	 * Dark red
	 */
	DARKRED("\u001B[41m"),
	/**
	 * Blue
	 */
	BLUE("\033[0;104m"),
	/**
	 * Dark blue
	 */
	DARKBLUE("\u001B[44m"),
	/**
	 * Cyan
	 */
	CYAN("\033[0;106m"),
	/**
	 * Jade
	 */
	JADE("\u001B[46m"),
	/**
	 * Green
	 */
	GREEN("\033[0;102m"),
	/**
	 * Dark Green
	 */
	DARKGREEN("\u001B[42m"),
	/**
	 * Yellow
	 */
	YELLOW("\033[0;103m"),
	/**
	 * Ocher
	 */
	OCHER("\u001B[43m"),
	/**
	 * Purple
	 */
	PURPLE("\033[0;105m"),
	/**
	 * Dark purple
	 */
	DARKPURPLE("\u001B[45m");

	/**
	 * Color code
	 * 
	 */
	private String color;

	/**
	 * Constructor of the Color enum
	 * 
	 * @param color Color code
	 */
	private Color(String color) {
		this.color = color;
	}

	/**
	 * Provides the color code of the corresponding color
	 * 
	 * @return color
	 */
	protected String getColor() {
		return color;
	}

}
