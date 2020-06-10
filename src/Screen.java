import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.JPanel;

public class Screen extends JPanel implements Runnable{
	public static final int WIDTH = 400, HEIGHT = 400;
	private Thread thread;
	private int ticks = 0;
	private boolean running = false;
	private Snake player;
	private Key key;
	private AppleGenerator appleGen;
	
	public Screen()
	{
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		key = new Key();
		addKeyListener(key);
		this.start();
	}
	
	public void start() {
		this.player = new Snake(10, 10);
		this.appleGen = new AppleGenerator();
		this.running = true;
		thread = new Thread(this, "GameLoop");
		thread.start();
	}
	
	public void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
		if(running) {
			g.clearRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.LIGHT_GRAY);
			
			for(int i = 0; i < WIDTH / 10; i++) {
				g.drawLine(i * 10, 0, i * 10, HEIGHT);
			}
			for(int i = 0; i < HEIGHT / 10; i++) {
				g.drawLine(0, i * 10, WIDTH, i * 10);
			}
		
			this.player.DrawSnake(g);
			this.appleGen.Paint(g);
		} else {
			GameOver(g);
		}
	}

	private void tick() {
		this.ticks++;
		if(ticks > 500000) {
			this.player.Move();
			this.ticks = 0;
		}
		this.appleGen.Generate();
		this.appleGen.tick(this.player);
		
		if(player.Collision()) {
			stop();
		}
	}
	
	private void GameOver(Graphics g) {
		String msg = "GAME OVER!";
		String msg2 = "Press Enter to restart";
		Font font = new Font("SAN_SERIF", Font.BOLD, 30);
		Font font2 = new Font("SAN_SERIF", Font.BOLD, 20);
		FontMetrics metrices = getFontMetrics(font);
		FontMetrics metrices2 = getFontMetrics(font2);
		g.setColor(Color.BLACK);
		g.setFont(font);
		g.drawString(msg, 200 - metrices.stringWidth(msg) / 2, 50);
		g.setFont(font2);
		g.drawString(msg2, 200 - metrices2.stringWidth(msg2) / 2, 70);		
	}
	
	
	private class Key implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyPressed(KeyEvent e) {
			String key = KeyEvent.getKeyText(e.getKeyCode());
			player.Turn(key);
			if(!running && key == "Enter") {
				start();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
		
	}
}
