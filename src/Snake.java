import java.awt.Graphics;
import java.util.ArrayList;

public class Snake {
	public SnakePart bodyPart;
	public ArrayList<SnakePart> body = new ArrayList<SnakePart>();
	public int x, y, size;
	private boolean right = false, left = false, up = false, down = true; 
	
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
	
	public void Turn(String direction) {		
		if(direction == "Up" && !this.down) {
			this.right = false;
			this.left = false;
			this.up = true;
		}
		if(direction == "Down" && !this.up) {
			this.right = false;
			this.left = false;
			this.down = true;
		}
		if(direction == "Left" && !this.right) {
			this.up = false;
			this.down = false;
			this.left = true;
		}
		if(direction == "Right" && !this.left) {	
			this.up = false;
			this.down = false;
			this.right = true;
		}
	}
	
	private boolean FrameCollision() {
		if(x <= 0 || x >= 39 || y <= 0 || y >= 39) {
			return true;
		} else return false;		
	}
	
	public boolean Collision() {
		if(FrameCollision()) {
			return true;
		} else return false;
	}
}
