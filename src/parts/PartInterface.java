package parts;
import java.awt.Graphics;

/**
 * Interface for basic game parts 
 */
interface PartInterface {
	public void tick();
	public void draw(Graphics g);
}