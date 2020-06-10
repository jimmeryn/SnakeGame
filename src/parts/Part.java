package parts;
import java.awt.Color;
import java.awt.Graphics;

public class Part implements PartInterface{
	private int x, y, width, height;
	private Color color;
	
	public Part(int x, int y, int tileSize, Color color) {
		this.x = x;
		this.y = y;
		width = tileSize;
		height = tileSize;
		this.color = color;
	}
	
	public void tick() {
		
	}
	
	public void draw(Graphics g) {
		g.setColor(this.color);
		g.fillRect(x*width, y*height, width, height);		
	}
	
	public boolean Collision(int x, int y) {
		if(this.x == x && this.y == y) {
			return true;
		} else return false;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
