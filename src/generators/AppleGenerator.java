package generators;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import entities.Snake;
import parts.Apple;

public class AppleGenerator {
	public Apple apple;
	public ArrayList<Apple> apples;
	private Thread thread;
	private Random r;
	
	public AppleGenerator() {
		apples = new ArrayList<Apple>();
		this.start();
	}
	
	public void start() {
		thread = new Thread("AppleGenerator");
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
		if(this.apples.size() == 0) {
			int x = Randomizer(xList, 39);
			int y = Randomizer(yList, 39);
			apple = new Apple(x, y, 10);
			apples.add(apple);
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
		for(int i = 0; i < apples.size(); i++) {
			if(apples.get(i).Collision(s.x, s.y)) {
				s.AddBodyPart();
				apples.remove(i);
				i--;
			}
		}
	}
	
	public void DrawApples(Graphics g) {
		for(int i = 0; i < apples.size(); i++) {
			apples.get(i).draw(g);
		}
	}
}
