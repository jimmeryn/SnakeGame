package generators;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import entities.Snake;
import parts.Frog;


/**
 * Frog generator class - generating frog on map
 */
public class FrogGenerator {
	public Frog frog;
	private Thread thread;
	private Random r;
	public boolean frogAlive = false;
	
	/**
	 * Constructor - starting new thread
	 */
	public FrogGenerator() {
		this.start();
	}
	
	/**
	 * Creating and starting thread
	 */
	public void start() {
		this.thread = new Thread("FrogGenerator");
		thread.start();
	}
	
	/**
	 * Stopping the thread
	 */
	public void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * If frog is dead generate new one, but not in the wall
	 * @param xList - list of x coordinates where frog cannot be generated
	 * @param yList - list of y coordinates where frog cannot be generated
	 */
	public void Generate(ArrayList<Integer> xList, ArrayList<Integer> yList) {
		if(!frogAlive) {
			int x = Randomizer(xList, 39);
			int y = Randomizer(yList, 39);
			frog = new Frog(x, y, 10);
			this.frogAlive = true;
		}
	}
	
	/**
	 * generate random number which not occurs in given list
	 * @param list - list of integers that cannot be generated
	 * @param size - max integer that can be generated
	 * @return ans - random integer
	 */
	private int Randomizer(ArrayList<Integer> list, int size) {
		r = new Random();
		int ans = r.nextInt(size);
		while(list.contains(ans)) {
			ans = r.nextInt(size);
		}
		return ans;
	}
	
	/**
	 * Check if snake has eaten the frog every frame and add new part to snake if he did
	 * @param s - snake to check
	 */
	public void tick(Snake s) {
		if(frog.Collision(s.x, s.y)) {
			s.AddBodyPart();
			this.frogAlive = false;
		}
	}
	
	/**
	 * Drawing apples on the screen
	 * @param g - graphics
	 */
	public void DrawFrog(Graphics g) {
		frog.draw(g);
	}
	
	/**
	 * Creating random number in range 0-some number (5 in this case)
	 * Used in FrogAI - random walking
	 */
	private int nextRandomCoord(){
		return ThreadLocalRandom.current().nextInt(0, 6);
	}
	
	/**
	 * TODO: Implement real AI. 
	 * Now it's just randomly walking around, trying not to hit walls and snake
	 * 
	 * @param player - snake to avoid
	 * @param wallGenerator - walls generator used to check for collisions with walls 
	 */
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
	
	/**
	 * Using wall generator and future coordinates to check if collision will occur
	 * Possible adding more collision thats why not using just WallCollision
	 * @see WallCollision
	 * @param nextX - future x coordinate
	 * @param nextY - future y coordinate
	 * @param wallGen - walls generator with walls coordinates
	 * @return true - if collision will occur
	 * @return false - if collision will not occur
	 */
	public boolean Collision(int nextX, int nextY, WallsGenerator wallGen) {
		if(WallCollision(nextX, nextY, wallGen)) {
			return true;
		} else return false;
	}
	
	/**
	 * Check if x and y is safe from walls
	 * @param nextX - x coordinate to check
	 * @param nextY - y coordinate to check
	 * @param wallGen - walls generator with walls coordinates
	 */
	private boolean WallCollision(int nextX, int nextY, WallsGenerator wallGen) {
		return wallGen.Collision(nextX, nextY);
	}
}
