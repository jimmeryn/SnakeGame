import java.awt.Color;

public class SnakePart extends Part{
	public SnakePart(int x, int y, int tileSize, Color color) {
		super(x, y, tileSize, color);
	}
	
	public SnakePart(int x, int y, int tileSize) {
		super(x, y, tileSize, Color.green);
	}
}
