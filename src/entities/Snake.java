package entities;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import parts.SnakePart;

/**
 * Snake class
 */
public class Snake {
	public SnakePart bodyPart;
	public ArrayList<SnakePart> body = new ArrayList<SnakePart>();
	public int x, y, size;
	private Color color;
	private boolean right = false, left = false, up = false, down = true; 
	
	/**
	 * Snake class constructor
	 * @param x - x Coordinate of snake at game start
	 * @param y - y Coordinate of snake at game start
	 * @param color - snake color
	 */
	public Snake(int x, int y, Color color) {
		this.x = y;
		this.y = y;
		this.size = 10;
		this.color = color;
		AddBodyPart();
	}
	
	/**
	 * Snake class constructor with default snake color: green
	 * @param x - x Coordinate of snake at game start
	 * @param y - y Coordinate of snake at game start
	 */	
	public Snake(int x, int y) {
		this.x = y;
		this.y = y;
		this.size = 10;
		this.color = Color.green;
		AddBodyPart();
	}
	
	/**
	 * Adding body part to snake body
	 */
	public void AddBodyPart() {
		SnakePart b = new SnakePart(x, y, size, color);
		this.body.add(b);
	}
	
	/**
	 * Drawing snake body on the screen
	 */
	public void DrawSnake(Graphics g) {
		for(int i = 0; i < this.body.size(); i++) {
			this.body.get(i).draw(g);
		}
	}
	
	/**
	 * Moving snake body accordingly
	 */
	public void Move() {
		if(this.up) this.y--;
		else if(this.down) this.y++;
		else if(this.left) this.x--;
		else if(this.right) this.x++;
		SnakePart b = new SnakePart(this.x, this.y, this.size, this.color);
		this.body.add(b);
		if(this.body.size() > 1) {
			this.body.remove(0);
		}
	}
	
	/**
	 * Turning snake in given direction
	 * @param direction - direction to turn snake
	 */
	public void Turn(String direction) {		
		if(direction == "Up" && !this.down) {
			this.right = false;
			this.left = false;
			this.up = true;
		}
		if(direction == "Down" && !this.up) {
			this.right = false;
			this.left = false;
			this.down = true;
		}
		if(direction == "Left" && !this.right) {
			this.up = false;
			this.down = false;
			this.left = true;
		}
		if(direction == "Right" && !this.left) {	
			this.up = false;
			this.down = false;
			this.right = true;
		}
	}
	
	/**
	 * Getting snake head
	 * @return Snake Head
	 */
	private SnakePart Head() {
		return this.body.get(this.body.size() - 1);
	}
	
	/**
	 * Checks for collisions
	 * @return true - if collision occurs 
	 * @return false - if collision does not occur 
	 */
	public boolean Collision() {
		if(SelfCollision()) {
			return true;
		} else return false;
	}
	
	/**
	 * Check for tail eating
	 * @return true - if collision occurs 
	 * @return false - if collision does not occur 
	 */
	private boolean SelfCollision() {
		if(this.body.size() > 4) {
			for(int i = 0; i < body.size() - 2; i++) {	
				if(body.get(i).Collision(Head().getX(), Head().getY())) {
					return true;
				}
			}
			return false;
		} else return false;
	}
	
	/**
	 * Check collision with certain (given) point
	 * @param x - x coordinate of point
	 * @param y - y coordinate of point
	 * @return true - if collision occurs 
	 * @return false - if collision does not occur 
	 */
	public boolean Collision(int x, int y) {
		for(int i = 0; i < body.size(); i++) {
			if(body.get(i).Collision(x, y)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Get array list of snakes x positions
	 * @return xCoords - x coordinates of snakes body  
	 */
	public ArrayList<Integer> PositionsX(){
		ArrayList<Integer> xCoords = new ArrayList<Integer>();
		for(int i = 0; i < this.size; i++) {
			xCoords.add(this.body.get(i).getX());
		}
		return xCoords;
	}

	/**
	 * Get array list of snakes y positions
	 * @return yCoords - y coordinates of snakes body  
	 */
	public ArrayList<Integer> PositionsY(){
		ArrayList<Integer> yCoords = new ArrayList<Integer>();	
		for(int i = 0; i < this.size; i++) {
			yCoords.add(this.body.get(i).getY());
		}
		return yCoords;
	}
}
