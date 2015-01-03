package vu.snake.states;

import vu.snake.Snake;
import vu.snake.entities.Bait;
import vu.snake.entities.SnakePlayer;
import vu.snake.handler.Board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayState extends State {
	
	private SnakePlayer snakePlayer;
	private Bait bait;
	private float time;
	
	public PlayState(GSM gsm) {
		super(gsm);
		Snake.board = new Board(Snake.FRAME_WIDTH, Snake.FRAME_HEIGHT);
		snakePlayer = new SnakePlayer(4, 4);
		bait = new Bait(snakePlayer);
		snakePlayer.setBait(bait);
	}

	@Override
	public void handleInput(float dt) {
		
//		if (Gdx.input.isTouched()) {
//			mouse.x = Gdx.input.getX();
//			mouse.y = Gdx.input.getY();
//			cam.unproject(mouse);
//			System.out.println("" + mouse.x + " " + mouse.y);
//			
//		}
		time += dt;
		
		if (time > SnakePlayer.TIME_TURN) {
			time = 0;
			if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
				if (snakePlayer.getDirection() != 3) snakePlayer.setDirection(1);
			} else
			if (Gdx.input.isKeyPressed(Keys.UP)) {
				if (snakePlayer.getDirection() != 4) snakePlayer.setDirection(2);
			} else
			if (Gdx.input.isKeyPressed(Keys.LEFT)) {
				if (snakePlayer.getDirection() != 1) snakePlayer.setDirection(3);
			} else
			if (Gdx.input.isKeyPressed(Keys.DOWN)) {
				if (snakePlayer.getDirection() != 2) snakePlayer.setDirection(4);
			}
		}
		
//		if (MyInput.isPressed(MyInput.BUTTON1)) {
//			snakePlayer.add();
//		}
		
	}

	@Override
	public void update(float dt) {
		handleInput(dt);
		snakePlayer.update(dt);
		bait.update(dt);
		
		if (snakePlayer.inTile()) {
			snakePlayer.printResult();
			
			gsm.set(new PlayState(gsm));
		}
		
	}

	@Override
	public void render(SpriteBatch sb) {
		TextureRegion tile = Snake.res.getTextureAtlas("snake").findRegion("tile");
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		
		Snake.board.render(sb);
		snakePlayer.render(sb);
		bait.render(sb);
		
		sb.end();
	}
		
}
