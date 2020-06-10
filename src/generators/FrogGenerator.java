package generators;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import entities.Snake;
import parts.Frog;

public class FrogGenerator {
	public Frog frog;
	private Thread thread;
	private Random r;
	public boolean frogAlive = false;
	
	public FrogGenerator() {
		this.start();
	}
	
	public void start() {
		this.thread = new Thread("FrogGenerator");
		thread.start();
	}
	
	public void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void Generate(ArrayList<Integer> xList, ArrayList<Integer> yList) {
		if(!frogAlive) {
			int x = Randomizer(xList, 39);
			int y = Randomizer(yList, 39);
			frog = new Frog(x, y, 10);
			this.frogAlive = true;
		}
	}
	
	private int Randomizer(ArrayList<Integer> list, int size) {
		r = new Random();
		int ans = r.nextInt(size);
		while(list.contains(ans)) {
			ans = r.nextInt(size);
		}
		return ans;
	}
	
	public void tick(Snake s) {
		if(frog.Collision(s.x, s.y)) {
			s.AddBodyPart();
			this.frogAlive = false;
		}
	}
	
	public void DrawFrog(Graphics g) {
		frog.draw(g);
	}
	
	public void Move() {
		if(this.frog.up) this.frog.setY(this.frog.getY() - 1);
		else if(this.frog.down) this.frog.setY(this.frog.getY() + 1);
		else if(this.frog.left) this.frog.setX(this.frog.getX() - 1);
		else if(this.frog.right) this.frog.setX(this.frog.getX() + 1);
	}
	
	public void FrogAI() {
		
	}
}
