package generators;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import entities.Snake;
import parts.Wall;

public class WallsGenerator {
	public ArrayList<Wall> walls;
	private Random r;
	public int size;
	public ArrayList<Integer> xList;
	public ArrayList<Integer> yList;
	
	public WallsGenerator(int size) {
		this.size = size;
		walls = new ArrayList<Wall>();
		this.xList = new ArrayList<Integer>();
		this.yList = new ArrayList<Integer>();
	}
	
	public void Generate() {
		if(this.walls.size() == 0) {
			int x;
			int y;
			Wall wall;
			for(int i = 0; i < this.size; i++) {
				r = new Random();
				x = r.nextInt(39);
				y = r.nextInt(39);
				this.xList.add(x);
				this.yList.add(y);
				wall = new Wall(x, y, 10);
				walls.add(wall);
			}
			GenerateFrames();
		}
	}
	
	private void GenerateFrames() {
		Wall wall;
		for(int i = 0; i < 40; i++) {
			wall = new Wall(i, -1, 10);
			walls.add(wall);
		}
		for(int i = 0; i < 40; i++) {
			wall = new Wall(i, 40, 10);
			walls.add(wall);
		}
		for(int i = 0; i < 40; i++) {
			wall = new Wall(-1, i, 10);
			walls.add(wall);
		}
		for(int i = 0; i < 40; i++) {
			wall = new Wall(40, i, 10);
			walls.add(wall);
		}
	}
	
	public boolean tick(Snake s) {
		for(int i = 0; i < walls.size(); i++) {
			if(walls.get(i).Collision(s.x, s.y)) {
				return true;
			} 
		} 
		return false;
	}
	
	public void DrawWalls(Graphics g) {
		for(int i = 0; i < walls.size(); i++) {
			walls.get(i).draw(g);
		}
	}
	
	public boolean Collision(Snake s) {
		for(int i = 0; i < walls.size(); i++) {
			if(walls.get(i).Collision(s.x, s.y)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean Collision(int x, int y) {
		for(int i = 0; i < walls.size(); i++) {
			if(walls.get(i).Collision(x, y)) {
				return true;
			}
		}
		return false;
	}
}

