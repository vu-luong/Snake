package vu.snake.entities;

import vu.snake.Snake;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class SnakePlayer {
	
	public static final int TILE_SIZE = 20;
	public static final int MAX_LENGTH = 70;
	public static final float TIME_TURN = .05f;
	
	private TextureRegion head;
	private TextureRegion tile;
	public Array<Vector2> coordinates;
	
	private int direction;
	private float time;
	private Bait bait;
	
	
	public SnakePlayer(int r, int c) {
		direction = Snake.RIGHT;
		head = Snake.res.getTextureAtlas("snake").findRegion("head");
		tile = Snake.res.getTextureAtlas("snake").findRegion("tile");
		coordinates = new Array<Vector2>();
		Vector2 v1 = new Vector2(Snake.board.getX(r, c), Snake.board.getY(r, c));
		Vector2 v2 = new Vector2(Snake.board.getX(r + 1, c), Snake.board.getY(r + 1, c));
		coordinates.add(v1);
		coordinates.add(v2);
		
	}
	
	public void setBait(Bait b){
		this.bait = b;
	}
	
	public void goAhead(int d) {
		time = 0;
		
		for (int i = coordinates.size - 1; i > 0; i--) {
			float x = coordinates.get(i - 1).x;
			float y = coordinates.get(i - 1).y;
			coordinates.removeIndex(i);
			coordinates.insert(i, new Vector2(x, y));
		}
		float x = coordinates.get(0).x;
		float y = coordinates.get(0).y;
		Vector2 v;
		switch (d) {
		case 1:
			v = Snake.board.getXYRightCell(x, y);
			break;
		case 2:
			v = Snake.board.getXYUpCell(x, y);
			break;
		case 3:
			v = Snake.board.getXYLeftCell(x, y);
			break;
		default:
			v = Snake.board.getXYDownCell(x, y);
			break;
		}
		coordinates.removeIndex(0);
		coordinates.insert(0, v);
	}
	
	public boolean contain(int r, int c) {
		for (int i = 0; i < coordinates.size; i++) {
			float x = coordinates.get(i).x;
			float y = coordinates.get(i).y;
			int row = Snake.board.getRow(x, y);
			int col = Snake.board.getCol(x, y);
			if (r == row && c == col) return true;
		}
		return false;		
	}
	
	public boolean inTile() {
		float xHead = coordinates.get(0).x;
		float yHead = coordinates.get(0).y;
		int rowHead = Snake.board.getRow(xHead, yHead);
		int colHead = Snake.board.getCol(xHead, yHead);		
		
		for (int i = 1; i < coordinates.size; i++) {
			float x = coordinates.get(i).x;
			float y = coordinates.get(i).y;
			int row = Snake.board.getRow(x, y);
			int col = Snake.board.getCol(x, y);
			if (rowHead == row && colHead == col) return true;
		}
		return false;		
	}
	
	public void add() {
//		Vector2 v = new Vector2(Snake.board.getX(r, c), Snake.board.getY(r, c));
//		coordinates.insert(0, v);
		float x = coordinates.get(coordinates.size - 1).x;
		float y = coordinates.get(coordinates.size - 1).y;
		goAhead(direction);
		coordinates.add(new Vector2(x, y));
	}
	
	public void setDirection(int d) {
		direction = d;
	}
	
	public Vector2 getCoordinate(int i) {
		return coordinates.get(i);
	}
	
	public int getDirection() {
		return direction;
	}
	
	public int getLength() {
		return coordinates.size;
	}
	
	public void printResult(){
		System.out.println(" " + coordinates.size);
		for (int i = 0; i < coordinates.size; i++) {
			float x = coordinates.get(i).x;
			float y = coordinates.get(i).y;
			int row = Snake.board.getRow(x, y);
			int col = Snake.board.getCol(x, y);
			System.out.print(x + " " + y + ",");
		}
		System.out.println();
		
	}
	
	
	public void update(float dt) {
		time += dt;
		if (time >= TIME_TURN) goAhead(direction);
		float x = coordinates.get(0).x;
		float y = coordinates.get(0).y;
		int row = Snake.board.getRow(x, y);
		int col = Snake.board.getCol(x, y);
		
		if (row == bait.getRow() && col == bait.getCol()) {
			bait.remake();
			add();
		}
				
	}
	
	public void render(SpriteBatch sb) {
		for (int i = 0; i < coordinates.size; i++) {
			float x = coordinates.get(i).x - TILE_SIZE / 2;
			float y = coordinates.get(i).y - TILE_SIZE / 2;
			if (i != 0) sb.draw(tile, x, y, TILE_SIZE - 2, TILE_SIZE - 2);
			else sb.draw(tile, x, y, TILE_SIZE - 2, TILE_SIZE - 2);
//			sb.draw(tile, x, y, TILE_SIZE, TILE_SIZE);
		}
	}
	
}
