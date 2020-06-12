package generators;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import entities.Snake;
import parts.Apple;

/**
 * Tool to generate apples in random places on the map
 */
public class AppleGenerator {
	public Apple apple;
	public ArrayList<Apple> apples;
	private Thread thread;
	private Random r;
	
	/**
	 * Constructor - creating new apple on the apples list.
	 * In current version only one apple is used but we can generate more.
	 */
	public AppleGenerator() {
		apples = new ArrayList<Apple>();
		this.start();
	}

	/**
	 * start - starting Apple Generator Thread
	 */
	public void start() {
		thread = new Thread("AppleGenerator");
		thread.start();
	}
	
	/**
	 * stop - stopping Apple Generator Thread
	 */
	public void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * generating apples in random place on the map
	 * @param xList - list of x coordinates where apple cannot be generated
	 * @param yList - list of y coordinates where apple cannot be generated
	 */
	public void Generate(ArrayList<Integer> xList, ArrayList<Integer> yList) {
		if(this.apples.size() == 0) {
			int x = Randomizer(xList, 39);
			int y = Randomizer(yList, 39);
			apple = new Apple(x, y, 10);
			apples.add(apple);
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
	 * Check if snake has eaten the apple every frame and add new part to snake if he did
	 * @param s - snake to check
	 */
	public void tick(Snake s) {
		for(int i = 0; i < apples.size(); i++) {
			if(apples.get(i).Collision(s.x, s.y)) {
				s.AddBodyPart();
				apples.remove(i);
				i--;
			}
		}
	}
	
	/**
	 * Drawing apples on the screen
	 * @param g - graphics
	 */
	public void DrawApples(Graphics g) {
		for(int i = 0; i < apples.size(); i++) {
			apples.get(i).draw(g);
		}
	}
}
