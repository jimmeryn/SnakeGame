import java.awt.Graphics;
import java.util.ArrayList;

public class Snake {
	public SnakePart bodyPart;
	public ArrayList<SnakePart> body = new ArrayList<SnakePart>();
	public int x, y, size;
	private boolean right = false, left = false, up = true, down = false; 
	
	public Snake(int x, int y) {
		this.x = y;
		this.y = y;
		this.size = 10;
		AddBodyPart();
	}
	
	public void AddBodyPart() {
		SnakePart b = new SnakePart(x, y, size);
		this.body.add(b);
	}
	
	public void DrawSnake(Graphics g) {
		for(int i = 0; i < this.body.size(); i++) {
			this.body.get(i).draw(g);
		}
	}
	
	public void Move() {
		if(this.up) this.y--;
		else if(this.down) this.y++;
		else if(this.left) this.x--;
		else if(this.right) this.x++;
		SnakePart b = new SnakePart(this.x, this.y, this.size);
		this.body.add(b);
		if(this.body.size() > 1) {
			this.body.remove(0);
		}
	}
}
