package vu.snake.handler;

import vu.snake.Snake;
import vu.snake.entities.SnakePlayer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Board {
	
	private int numRow;
	private int numCol;
	private TextureRegion tile;
	
	public Board(int w, int h) {
		this.numRow = w / SnakePlayer.TILE_SIZE;
		this.numCol = h / SnakePlayer.TILE_SIZE;
		
		System.out.println("" + w + " " + h + " " + SnakePlayer.TILE_SIZE);
		tile = Snake.res.getTextureAtlas("snake").findRegion("tile");
		
	}
	
	public int getNumRow() {
		return numRow;
	}
	
	public int getNumCol() {
		return numCol;
	}
	
	public int getRow(float x, float y) {
		if (x < Snake.WALL_SIZE) return -1;
		return (int) ((x - Snake.WALL_SIZE) / SnakePlayer.TILE_SIZE);
	}
	
	public int getCol(float x, float y) {
		if (y < Snake.WALL_SIZE + Snake.CONTROL_SIZE) return -1;
		return (int) ((y - Snake.CONTROL_SIZE - Snake.WALL_SIZE) / SnakePlayer.TILE_SIZE);
	}
	
	public float getX(int row, int col) {
		return Snake.WALL_SIZE + SnakePlayer.TILE_SIZE * (row + 1) - SnakePlayer.TILE_SIZE / 2;
	}
	
	public float getY(int row, int col) {
		return Snake.CONTROL_SIZE + Snake.WALL_SIZE 
			+ SnakePlayer.TILE_SIZE * (col + 1) - SnakePlayer.TILE_SIZE / 2;
	}
	
	public Vector2 getXYRightCell(float x, float y) {
		int r = Snake.board.getRow(x, y) + 1;
		int c = Snake.board.getCol(x, y);
		if (r == numRow) r = 0;
		float xx = getX(r, c);
		float yy = getY(r, c);
		return new Vector2(xx, yy);
	}
	
	public Vector2 getXYUpCell(float x, float y) {
		int r = Snake.board.getRow(x, y);
		int c = Snake.board.getCol(x, y) + 1;
		if (c == numCol) c = 0;
		float xx = getX(r, c);
		float yy = getY(r, c);
		return new Vector2(xx, yy);
	}
	public Vector2 getXYLeftCell(float x, float y) {
		int r = Snake.board.getRow(x, y) - 1;
		int c = Snake.board.getCol(x, y);
		if (r < 0) r = numRow - 1;
		float xx = getX(r, c);
		float yy = getY(r, c);
		return new Vector2(xx, yy);
	}
	public Vector2 getXYDownCell(float x, float y) {
		int r = Snake.board.getRow(x, y);
		int c = Snake.board.getCol(x, y) - 1;
		if (c < 0) c = numCol - 1;
		float xx = getX(r, c);
		float yy = getY(r, c);
		return new Vector2(xx, yy);
	}
	
	
	public int getWidth() {
		return numRow;
	}
	
	public int getHeight() {
		return numCol;
	}
	
	public void render(SpriteBatch sb) {
		
		sb.draw(tile, 0, Snake.CONTROL_SIZE, 
				Snake.WALL_SIZE, Snake.FRAME_HEIGHT + Snake.WALL_SIZE * 2);
		sb.draw(tile, Snake.FRAME_WIDTH + Snake.WALL_SIZE, Snake.CONTROL_SIZE, 
				Snake.WALL_SIZE, Snake.FRAME_HEIGHT + Snake.WALL_SIZE * 2);
		sb.draw(tile, 0, Snake.CONTROL_SIZE + Snake.WALL_SIZE + Snake.FRAME_HEIGHT, 
				Snake.WIDTH, Snake.WALL_SIZE);
		sb.draw(tile, 0, Snake.CONTROL_SIZE, 
				Snake.WIDTH, Snake.WALL_SIZE);
		
		
	}
	
	
}
