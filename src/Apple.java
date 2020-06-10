import java.awt.Color;
import java.awt.Graphics;

public class Apple {
	private int x, y, width, height;
	public Apple(int x, int y, int tileSize) {
		this.x = x;
		this.y = y;
		this.width = tileSize;
		this.height = tileSize;
	}
	
	public void tick() {
		
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(this.x*width, this.y*height, this.width, this.height);
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
