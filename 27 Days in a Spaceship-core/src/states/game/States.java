package states.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.SpaceshipGame;

public abstract class States {
	protected OrthographicCamera cam;
	protected Vector3 mouse;
	protected StateManager sm;
	
	public States(StateManager sm) {
		this.sm = sm;
		cam = new OrthographicCamera();
		cam.setToOrtho(false, SpaceshipGame.WIDTH, SpaceshipGame.HEIGHT);
		mouse = new Vector3();
	}
	
	public abstract void input();
	public abstract void update(float dt);
	public abstract void render(SpriteBatch sb);
	public abstract void dispose();
}
