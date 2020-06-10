package parts;
import java.awt.Color;

public class Wall extends Part{
	public Wall(int x, int y, int tileSize, Color color) {
		super(x, y, tileSize, color);
	}
	
	public Wall(int x, int y, int tileSize) {
		super(x, y, tileSize, Color.black);
	}	
}
