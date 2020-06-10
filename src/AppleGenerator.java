import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

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
	
	public void Generate() {
		if(this.apples.size() == 0) {
			r = new Random();
			int x = r.nextInt(39);
			int y = r.nextInt(39);
			apple = new Apple(x, y, 10);
			apples.add(apple);
		}
	}
	
	public void tick(Snake s) {
		for(int i = 0; i < apples.size(); i++) {
			if(s.x == apples.get(i).getX() && s.y == apples.get(i).getY()) {
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
