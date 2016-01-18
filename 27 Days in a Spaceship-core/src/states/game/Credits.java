package states.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Credits extends States{
	
	private Texture credits;
	
	private float y;
	private boolean switchY;
	
	public Credits(StateManager sm) {
		super(sm);
		// TODO Auto-generated constructor stub
		credits =  new Texture("CreditsImage.png");
		
		y = 460;
		switchY = false;
	}

	@Override
	public void input() {
		// TODO Auto-generated method stub
		if(Gdx.input.justTouched()) {
			sm.switchtState(new Menu(sm));
		}
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		input();
		
		if(switchY == false) {
			y = y + 0.5f;
			if(y >= 480f)
				switchY = true;
		}
		else{
			y = y - 0.5f;
			if(y <= 440f)
				switchY = false;
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		// TODO Auto-generated method stub
		sb.begin();
		sb.draw(credits, 180, y);
		sb.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		credits.dispose();
	}
}
