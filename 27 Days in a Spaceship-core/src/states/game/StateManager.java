package states.game;

import java.util.Stack;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StateManager {
	
	private Stack<States> state;
	
	public StateManager() {
		state = new Stack<States>();
	}
	
	public void push(States states) {
		state.push(states);
	}
	
	public void pop() {
		state.pop().dispose();
	}
	
	// switch between states
	public void switchtState(States s) {
		state.pop().dispose();
		state.push(s);
	}
	
	public States peek() {
		return state.peek();
	}
	
	public void update(float dt) {
		state.peek().update(dt);
	}
	
	public void render(SpriteBatch sb) {
		//Gdx.gl.glClearColor(0, 1, 0, 1);
		state.peek().render(sb);
	}
}
