package parts;
import java.awt.Color;

/**
 * Snake Part - extending Part to create snake part of body
 */
public class SnakePart extends Part{
	/**
	 * Snake part constructor
	 * @param x
	 * @param y
	 * @param tileSize
	 * @param color
	 */
	public SnakePart(int x, int y, int tileSize, Color color) {
		super(x, y, tileSize, color);
	}
	
	/**
	 * Snake part constructor with default color: green
	 * @param x
	 * @param y
	 * @param tileSize
	 */
	public SnakePart(int x, int y, int tileSize) {
		super(x, y, tileSize, Color.green);
	}
}
