package parts;
import java.awt.Color;

public class Frog extends Part{
	public Frog(int x, int y, int tileSize, Color color) {
		super(x, y, tileSize, color);
	}
	
	public Frog(int x, int y, int tileSize) {
		super(x, y, tileSize, Color.blue);
	}
	
	public void Move(int x, int y) {
		this.setX(x);
		this.setY(y);
	}
}
