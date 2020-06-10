package parts;
import java.awt.Color;

public class Apple extends Part{
	public Apple(int x, int y, int tileSize, Color color) {
		super(x, y, tileSize, color);
	}
	public Apple(int x, int y, int tileSize) {
		super(x, y, tileSize, Color.red);
	}
}
