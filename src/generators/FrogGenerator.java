package generators;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
	
	private int nextRandomCoord(){
		return ThreadLocalRandom.current().nextInt(0, 6);
	}
	
	public void FrogAI(Snake player, WallsGenerator wallGen) {
		int nextX = this.frog.getX();
		int nextY = this.frog.getY();
		int rn = nextRandomCoord();
		switch(rn) {
		  case 0:
			nextX++;
		    break;
		  case 1:
		    nextX--;
		    break;
		  case 2:
		    nextY++;
		    break;
		  case 3:
		    nextY--;
		    break;
		  default:
			  break;
		}
		if(!player.Collision(nextX, nextY) && !Collision(nextX, nextY, wallGen)) {
			this.frog.Move(nextX, nextY);
		}
	}
	
	public boolean Collision(int nextX, int nextY, WallsGenerator wallGen) {
		if(WallCollision(nextX, nextY, wallGen)) {
			return true;
		} else return false;
	}
	
	private boolean WallCollision(int nextX, int nextY, WallsGenerator wallGen) {
		return wallGen.Collision(nextX, nextY);
	}
}
