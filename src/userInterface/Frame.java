package userInterface;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class Frame extends JFrame{
	/**
	 * Error handling
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor to create frame.
	 * Define on close operation, sets title and resize ability.
	 * Initializing frame
	 * @see init()
	 */
	public Frame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Snake");
		setResizable(false);
		init();
	}
	
	/**
	 * Initializing screen
	 */
	public void init()
	{
		setLayout(new GridLayout(1,1,0,0));
		Screen s = new Screen();
		add(s);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
