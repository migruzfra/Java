package mosaic;

/**
 * @author Miguel Angel
 *
 */
public class Mosaic implements Drawable {

	/**
	 * Whole composition of figures
	 */
	private Figure[][] mosaic;
	/**
	 * List of the different figures of the mosaic
	 */
	protected Figure[] figures;

	/**
	 * Constructs a mosaic given rows, columns and list of figures
	 * 
	 * @param i       Rows of the mosaic
	 * @param j       Columns of the mosaic
	 * @param figures List of figures to be inserted in the mosaic
	 * @throws IllegalMosaicCreationException Controls that there are not more
	 *                                        figures than cells in the mosaic
	 */
	public Mosaic(int i, int j, Figure[] figures) throws IllegalMosaicCreationException {
		if (i * j < figures.length) {
			throw new IllegalMosaicCreationException("There are more figures than cells in the mosaic");
		}
		this.figures = figures;
		mosaic = new Figure[i][j];
		fillMosaic(figures);
	}

	/**
	 * Clones a mosaic
	 * 
	 * @param mosaico Mosaic to be cloned
	 * @throws IllegalFigureCreationException Controls the color and the background
	 *                                        to be different
	 */
	public Mosaic(Mosaic mosaico) throws IllegalFigureCreationException {
		figuresClon(mosaico);
		mosaic = new Figure[mosaico.mosaic.length][mosaico.mosaic[0].length];
		fillMosaic(figures);
	}

	public boolean equals(Object obj) {
		boolean equals = false;
		if (obj instanceof Mosaic && ((Mosaic) obj).mosaic.length == this.mosaic.length) {
			equals = true;
			for (int i = 0; i < mosaic.length; i++) {
				for (int j = 0; j < mosaic[i].length; j++) {
					if (!((Mosaic) obj).mosaic[i][j].equals(mosaic[i][j])) {
						equals = false;
					}
				}
			}
		}
		return equals;
	}

	/**
	 * Fill the mosaic with the figures specified
	 * 
	 * @param figures Array of different figures of the mosaic
	 */
	private void fillMosaic(Figure[] figures) {
		int k = 0;
		for (int i = 0; i < mosaic.length; i++) {
			for (int j = 0; j < mosaic[i].length; j++) {
				mosaic[i][j] = figures[k];
				if (k < figures.length - 1) {
					k++;
				} else {
					k = 0;
				}
			}
		}
	}

	/**
	 * Clones an array of figures according to the specified mosaic
	 * 
	 * @param mosaic The mosaic we want to be cloned
	 * @throws IllegalFigureCreationException Controls the color and the background
	 *                                        to be different
	 */
	private void figuresClon(Mosaic mosaic) throws IllegalFigureCreationException {
		this.figures = new Figure[mosaic.figures.length];
		for (int i = 0; i < mosaic.figures.length; i++) {
			if (mosaic.figures[i] instanceof Circle) {
				figures[i] = new Circle((Circle) mosaic.figures[i]);
			} else if (mosaic.figures[i] instanceof Cross) {
				figures[i] = new Cross((Cross) mosaic.figures[i]);
			} else if (mosaic.figures[i] instanceof Rhombus) {
				figures[i] = new Rhombus((Rhombus) mosaic.figures[i]);
			} else if (mosaic.figures[i] instanceof TriangleUp) {
				figures[i] = new TriangleUp((TriangleUp) mosaic.figures[i]);
			} else if (mosaic.figures[i] instanceof TriangleDown) {
				figures[i] = new TriangleDown((TriangleDown) mosaic.figures[i]);
			}
		}
	}

	public void draw() {
		for (int i = 0; i < mosaic.length; i++) {
			for (Figure.line = 0; Figure.line < Figure.h; Figure.line++) {
				for (int j = 0; j < mosaic[i].length; j++) {
					mosaic[i][j].draw();
				}
				System.out.println();
			}
		}
	}
}
