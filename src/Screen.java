import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Screen extends JPanel implements Runnable{
	public static final int WIDTH = 400, HEIGHT = 400;
	private Thread thread;
	private int ticks = 0;
	private boolean running = false;
	private Snake snake1 = new Snake(10, 10);
	
	public Screen()
	{
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		start();
	}
	
	public void start() {
		running = true;
		thread = new Thread(this, "GameLoop");
		thread.start();
	}

	@Override
	public void run() {
		while(running) {
			tick();
			repaint();
		}
		
	}

	@Override
	public void paint(Graphics g){
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.LIGHT_GRAY);
		
		for(int i = 0; i < WIDTH / 10; i++) {
			g.drawLine(i * 10, 0, i * 10, HEIGHT);
		}
		for(int i = 0; i < HEIGHT / 10; i++) {
			g.drawLine(0, i * 10, WIDTH, i * 10);
		}
		
		this.snake1.DrawSnake(g);
	}

	private void tick() {
		this.ticks++;
		if(this.ticks > 30000) {
			this.snake1.Move();
			this.ticks = 0;
		}
		System.out.println(this.snake1.x);
		System.out.println(this.snake1.y);
	}
}
