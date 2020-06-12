package userInterface;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import entities.Snake;
import generators.AppleGenerator;
import generators.FrogGenerator;
import generators.WallsGenerator;

/**
 * Screen class extending JPanel implementing Runnable.
 * Basic game screen
 */
public class Screen extends JPanel implements Runnable{
	public static final int WIDTH = 400, HEIGHT = 400;
	private Thread thread;
	private int ticks = 0, bestScore = 1;
	private boolean running = false;
	private Snake player;
	private Key key;
	private AppleGenerator appleGen;
	private WallsGenerator wallsGen;
	private FrogGenerator frogGen;
	
	/**
	 * Screen constructor.
	 * Setting size, adding key listener, starting game
	 */
	public Screen()
	{
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		key = new Key();
		addKeyListener(key);
		this.start();
	}
	
	/**
	 * Start method - initialize game parts. 
	 * Generate entities, start basic game thread
	 */
	public void start() {
		this.player = new Snake(10, 10);
		this.wallsGen = new WallsGenerator(10);
		this.wallsGen.Generate();
		this.frogGen = new FrogGenerator();
		this.appleGen = new AppleGenerator();

		this.frogGen.Generate(wallsGen.xList, wallsGen.yList);
		this.appleGen.Generate(wallsGen.xList, wallsGen.yList);
		this.running = true;
		thread = new Thread(this, "GameLoop");
		thread.start();
	}
	
	/**
	 * stops game and basic game thread
	 */
	public void stop() {
		running = false;
		this.appleGen.stop();
		this.frogGen.stop();
	}

	/**
	 * run - game loop
	 */
	@Override
	public void run() {
		while(running) {
			tick();
			repaint();
		}
		
	}
	
	/**
	 * Painting everything on the screen
	 */
	@Override
	public void paint(Graphics g){
		if(running) {
			DrawBackground(g);
			this.player.DrawSnake(g);
			this.appleGen.DrawApples(g);
			this.wallsGen.DrawWalls(g);
			this.frogGen.DrawFrog(g);
		} else {
			this.player.DrawSnake(g);
			GameOver(g);
		}
	}

	/**
	 * Creating and drawing background
	 * @param g
	 */
	private void DrawBackground(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
		DrawGrid(g);
	}
	
	/**
	 * Create grid used in background
	 * @param g
	 */
	private void DrawGrid(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		for(int i = 0; i < WIDTH / 10; i++) {
			g.drawLine(i * 10, 0, i * 10, HEIGHT);
		}
		for(int i = 0; i < HEIGHT / 10; i++) {
			g.drawLine(0, i * 10, WIDTH, i * 10);
		}
	}
	
	/**
	 * Every frame checks 
	 */
	private void tick() {
		this.ticks++;
		if(ticks > 300000) {
			this.player.Move();
			this.frogGen.FrogAI(this.player, this.wallsGen);
			this.ticks = 0;
		}
		this.appleGen.Generate(this.wallsGen.xList, this.wallsGen.yList);
		this.appleGen.tick(this.player);
		
		this.frogGen.Generate(this.wallsGen.xList, this.wallsGen.yList);
		this.frogGen.tick(this.player);
		
		
		if(player.Collision() || this.wallsGen.Collision(this.player)) {
			stop();
		}
	}
	
	/**
	 * Game over menu - player can see best result, current points and restart the game
	 * @param g
	 */
	private void GameOver(Graphics g) {
		String msg = "GAME OVER!";
		String record = "NEW RECORD!";
		String msg2 = "Press Enter to restart";
		String bestScoreMsg = "Best score: " + this.bestScore;
		String scoreMsg = "Your score: " + this.player.body.size();
		Font font = new Font("SAN_SERIF", Font.BOLD, 30);
		Font font2 = new Font("SAN_SERIF", Font.BOLD, 20);
		FontMetrics metrices = getFontMetrics(font);
		FontMetrics metrices2 = getFontMetrics(font2);
		g.setColor(Color.BLACK);
		g.setFont(font);
		if(this.player.body.size() > this.bestScore) {
			g.drawString(record, 200 - metrices.stringWidth(record) / 2, 50);
			this.bestScore = this.player.body.size();
		} else {
			g.drawString(msg, 200 - metrices.stringWidth(msg) / 2, 50);	
		}
		g.setFont(font2);
		g.drawString(msg2, 200 - metrices2.stringWidth(msg2) / 2, 70);		
		g.setFont(font2);
		g.drawString(bestScoreMsg, 200 - metrices2.stringWidth(scoreMsg) / 2, 90);		
		g.drawString(scoreMsg, 200 - metrices2.stringWidth(scoreMsg) / 2, 110);		
		
	}
	
	/**
	 * Key class implementing KeyLustener.
	 * Used to get key events from keyboard
	 *
	 */
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
