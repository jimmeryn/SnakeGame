package parts;
import java.awt.Color;

/**
 * Wall class - extending Part class to create wall 
 */
public class Wall extends Part{
	/**
	 * Wall constructor
	 * @param x
	 * @param y
	 * @param tileSize
	 * @param color
	 */
	public Wall(int x, int y, int tileSize, Color color) {
		super(x, y, tileSize, color);
	}

	/**
	 * Wall constructor with default color: black
	 * @param x
	 * @param y
	 * @param tileSize
	 */
	public Wall(int x, int y, int tileSize) {
		super(x, y, tileSize, Color.black);
	}	
}
