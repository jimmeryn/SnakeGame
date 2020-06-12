package parts;
import java.awt.Color;

/**
 * Frog - basic game part. Is moving and can be eaten by snakes.
 */
public class Frog extends Part{
	/**
	 * Construct Frog
	 * @param x
	 * @param y
	 * @param tileSize
	 * @param color
	 */
	public Frog(int x, int y, int tileSize, Color color) {
		super(x, y, tileSize, color);
	}

	/**
	 * Construct Frog with default color: blue
	 * @param x
	 * @param y
	 * @param tileSize
	 */
	public Frog(int x, int y, int tileSize) {
		super(x, y, tileSize, Color.blue);
	}
	
	/**
	 * Move Frog to certain point. 
	 * @param x
	 * @param y
	 */
	public void Move(int x, int y) {
		this.setX(x);
		this.setY(y);
	}
}
