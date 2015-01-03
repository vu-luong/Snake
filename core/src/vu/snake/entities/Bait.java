package vu.snake.entities;

import java.util.Random;

import vu.snake.Snake;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Bait {
	private final float timeTurn = 0.3f;
	private float x, y;
	private int row, col;	
	private TextureRegion head;
	private boolean canDraw;
	private float time;
	private Random random;
	private SnakePlayer snakePlayer;
	
	public Bait(SnakePlayer sp) {
		this.snakePlayer = sp;
		head = Snake.res.getTextureAtlas("snake").findRegion("head");
		random = new Random();
		
		int r, c;
		while (true) {
			r = random.nextInt(Snake.board.getNumRow());
			c = random.nextInt(Snake.board.getNumCol());
			if (!snakePlayer.contain(r, c))	break;
		}
		this.x = Snake.board.getX(r, c);
		this.y = Snake.board.getY(r, c);
		this.row = r;
		this.col = c;
		canDraw = true;
		
	}
	
	public void remake() {
		int r, c;
		while (true) {
			r = random.nextInt(Snake.board.getNumRow());
			c = random.nextInt(Snake.board.getNumCol());
			if (!snakePlayer.contain(r, c))	break;
		}
		this.x = Snake.board.getX(r, c);
		this.y = Snake.board.getY(r, c);
		this.row = r;
		this.col = c;
		
	}
	
	public int getRow(){
		return row;
	}
	
	public int getCol(){
		return col;
	}
	
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	
	public void update(float dt) {
		time += dt;
		if (time > timeTurn) { 
			canDraw = !canDraw;
			time = 0;
		}
	}
	
	public void render(SpriteBatch sb) {
		if (canDraw) {
			sb.draw(head, 
				this.x - SnakePlayer.TILE_SIZE / 2, 
				this.y - SnakePlayer.TILE_SIZE / 2, 
				SnakePlayer.TILE_SIZE - 2, SnakePlayer.TILE_SIZE - 2);
		}
	}
	
}
