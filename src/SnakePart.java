import java.awt.Color;
import java.awt.Graphics;

public class SnakePart {
	private int x, y, width, height;
	
	public SnakePart(int x, int y, int tileSize) {
		this.x = x;
		this.y = y;
		width = tileSize;
		height = tileSize;
	}
	
	public void tick() {
		
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x*width, y*height, width, height);		
	}
}
