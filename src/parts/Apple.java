package parts;
import java.awt.Color;

/**
 * Apple class - basic part of the game
 * Used in apple constructor
 */
public class Apple extends Part{
	/**
	 * Apple constructor
	 * @param x - coordinate on the map
	 * @param y - coordinate on the map
	 * @param tileSize
	 * @param color - Apple color
	 */
	public Apple(int x, int y, int tileSize, Color color) {
		super(x, y, tileSize, color);
	}
	
	/**
	 * Apple constructor with default color: red
	 * @param x - coordinate on the map
	 * @param y - coordinate on the map
	 * @param tileSize
	 */
	public Apple(int x, int y, int tileSize) {
		super(x, y, tileSize, Color.red);
	}
}
