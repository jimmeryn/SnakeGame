package parts;
import java.awt.Color;
import java.awt.Graphics;

/*
 * Implementing Part Interface to create basic Part class.
 * Used in Apple, Frog, Wall, SnakePart
 */
public class Part implements PartInterface{
	private int x, y, width, height;
	private Color color;
	
	/**
	 * Part Constructor
	 * @param x
	 * @param y
	 * @param tileSize
	 * @param color
	 */
	public Part(int x, int y, int tileSize, Color color) {
		this.x = x;
		this.y = y;
		width = tileSize;
		height = tileSize;
		this.color = color;
	}
	
	/**
	 * Tick - do sth. every frame
	 */
	public void tick() {
		
	}
	
	/**
	 * drawing element on the screen
	 */
	public void draw(Graphics g) {
		g.setColor(this.color);
		g.fillRect(x*width, y*height, width, height);		
	}
	
	/**
	 * Checks for collision with certain point
	 * @param x
	 * @param y
	 * @return true or false accordingly 
	 */
	public boolean Collision(int x, int y) {
		if(this.x == x && this.y == y) {
			return true;
		} else return false;
	}
	
	/**
	 * Get x parameter - current location on x axis 
	 * @return x 
	 */
	public int getX() {
		return x;
	}

	/**
	 * Set x parameter - on x axis 
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Get y parameter - current location on y axis 
	 * @return y 
	 */
	public int getY() {
		return y;
	}

	/**
	 * Set y parameter - on y axis 
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
}
