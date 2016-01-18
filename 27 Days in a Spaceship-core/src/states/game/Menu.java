package states.game;

import manage.sprites.MenuHud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceshipGame;

public class Menu extends States{
	
	
	
	private Texture logo, astronaut;
	private Sprite astro;
	
	private float x, y, astroX, astroY;
	private boolean switchY;
	private MenuHud hud;
	private Music menuSound;
	
	public Menu(StateManager sm) {
		super(sm);
		hud = new MenuHud(sm);
		hud.addAction();
		//hud.showMenu();
		x = 10f;
		y = 100f;
		astroX = SpaceshipGame.WIDTH + 100;
		astroY = 700;
		switchY = false;
		
		menuSound = Gdx.audio.newMusic(Gdx.files.internal("Calmant.mp3"));
		menuSound.setLooping(true);
		menuSound.setVolume(0.3f);
		menuSound.play();
		
		logo = new Texture("Logo.png");
		astronaut = new Texture("Astronaut.png");
		astro = new Sprite(astronaut);
		astro.setOrigin(astro.getWidth() / 2, astro.getHeight() / 2);
		astro.setPosition(astroX, astroY);
	}
	
	@Override
	public void input() {
		// TODO Auto-generated method stub
		if(Gdx.input.justTouched()) {
			//sm.switchtState(new GamePlay(sm));
		}
	}
	
	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		input();
		
		if(switchY == false) {
			y = y + 0.5f;
			if(y >= 130f)
				switchY = true;
		}
		else{
			y = y - 0.5f;
			if(y <= 100f)
				switchY = false;
		}
		
		if(astroX < -astro.getWidth() - 200) {
			astroX = SpaceshipGame.WIDTH;
		}
		else{
			astroX--;
		}
		
		astro.rotate(20 * dt);
	}

	@Override
	public void render(SpriteBatch sb) {
		// TODO Auto-generated method stub
		
		sb.begin();
		// need to call the draw method of the sprite in order to get the sprite to rotate
		astro.draw(sb);
		astro.setPosition(astroX, astroY);
		
		sb.draw(logo, x, y);
		sb.end();
		
		hud.showMenu();
	}
	
	@Override
	public void dispose() {
		menuSound.dispose();
		astronaut.dispose();
		hud.dispose();
	}
}
