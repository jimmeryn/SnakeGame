package parts;
import java.awt.Color;

public class Frog extends Part{
	public boolean up, down, left, right;
	public Frog(int x, int y, int tileSize, Color color) {
		super(x, y, tileSize, color);
	}
	
	public Frog(int x, int y, int tileSize) {
		super(x, y, tileSize, Color.blue);
	}
}
