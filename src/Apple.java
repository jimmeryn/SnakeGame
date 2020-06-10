import java.awt.Color;
import java.awt.Graphics;

public class Apple extends Part{
	public Apple(int x, int y, int tileSize, Color color) {
		super(x, y, tileSize, color);
	}
	public Apple(int x, int y, int tileSize) {
		super(x, y, tileSize, Color.red);
	}
}
