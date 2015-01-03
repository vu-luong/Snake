package vu.snake.handler;


import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;

public class MyInputProcessor extends InputAdapter {
	
	public boolean keyDown(int k) {
		if(k == Keys.RIGHT) {
			MyInput.setKey(MyInput.RIGHT, true);
		}
		if(k == Keys.UP) {
			MyInput.setKey(MyInput.UP, true);
		}
		if(k == Keys.LEFT) {
			MyInput.setKey(MyInput.LEFT, true);
		}
		if(k == Keys.DOWN) {
			MyInput.setKey(MyInput.DOWN, true);
		}
		return true;
	}
	
	public boolean keyUp(int k) {
		if(k == Keys.RIGHT) {
			MyInput.setKey(MyInput.RIGHT, false);
		}
		if(k == Keys.UP) {
			MyInput.setKey(MyInput.UP, false);
		}
		if(k == Keys.LEFT) {
			MyInput.setKey(MyInput.LEFT, false);
		}
		if(k == Keys.DOWN) {
			MyInput.setKey(MyInput.DOWN, false);
		}
		
		return true;
	}
	
}
