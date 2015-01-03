package vu.snake;

import vu.snake.handler.Board;
import vu.snake.handler.Content;
import vu.snake.handler.MyInput;
import vu.snake.handler.MyInputProcessor;
import vu.snake.states.GSM;
import vu.snake.states.PlayState;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Snake extends ApplicationAdapter {
	
	public static final String TITLE = "Snake";
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final int CONTROL_SIZE = 320;
	public static final int WALL_SIZE = 20;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int LEFT = 3;
	public static final int DOWN = 4;
	public static final int FRAME_WIDTH = 440;
	public static final int FRAME_HEIGHT = 300;
	
	public static Board board;
	
	public static Content res;
	
	private SpriteBatch sb;
	private GSM gsm;
	
	@Override
	public void create () {
		Gdx.gl.glClearColor(0.4f, 0.6f, 0.2f, 0.2f);
		Gdx.input.setInputProcessor(new MyInputProcessor());
		
		res = new Content();
		res.loadAtlas("snake.pack", "snake");
		
		sb = new SpriteBatch();
		gsm = new GSM();
		gsm.push(new PlayState(gsm));
		
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(sb);
		MyInput.update();
		
	}
}
