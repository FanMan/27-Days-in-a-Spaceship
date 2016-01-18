package states.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.SpaceshipGame;

public class GameOver extends States{
	
	private Texture logo;
	private Skin skin;
	private ButtonStyle playStyle, exitStyle;
	private Button playBtn, exitBtn;
	private Stage stage;
	private Table btnTable;
	
	private Menu menu;
	
	private Music music;
	
	private float x, y;
	private boolean switchY;
	
	public GameOver(StateManager sm) {
		super(sm);
		// TODO Auto-generated constructor stub
		stage = new Stage();
		
		music = Gdx.audio.newMusic(Gdx.files.internal("Mesmerize.mp3"));
		music.setLooping(true);
		music.setVolume(0.3f);
		music.play();
		
		logo = new Texture("Game Over.png");
		
		x = (SpaceshipGame.WIDTH / 2) - (logo.getWidth() / 2);
		y = SpaceshipGame.HEIGHT - 300;
		switchY = false;
		
		skin = new Skin();
		skin.add("playUpBtn", new TextureRegion(new Texture("ButtonAtlas.png"), 0, 0, 256, 126));
		skin.add("playDownBtn", new TextureRegion(new Texture("ButtonAtlas.png"), 0, 126, 256, 126));
		skin.add("exitUpBtn", new TextureRegion(new Texture("ButtonAtlas.png"), 512, 0, 256, 126));
		skin.add("exitDownBtn", new TextureRegion(new Texture("ButtonAtlas.png"), 512, 126, 256, 126));
		
		playStyle = new ButtonStyle();
		playStyle.up = skin.getDrawable("playUpBtn");
		playStyle.down = skin.getDrawable("playDownBtn");
		
		exitStyle = new ButtonStyle();
		exitStyle.up = skin.getDrawable("exitUpBtn");
		exitStyle.down = skin.getDrawable("exitDownBtn");
		
		playBtn = new Button();
		playBtn.setSkin(skin);
		playBtn.setStyle(playStyle);
		playBtn.setPosition(100, 100);
		
		exitBtn = new Button();
		exitBtn.setSkin(skin);
		exitBtn.setStyle(exitStyle);
		
		btnTable = new Table();
		btnTable.setPosition(x + 110, y - 350);
		btnTable.add(playBtn).pad(20);
		btnTable.row();
		btnTable.add(exitBtn).pad(20);
		btnTable.setDebug(false);
		
		stage.addActor(btnTable);
		
		addAction();
	}
	
	public void addAction() {
		playBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				//sm.switchtState(new GamePlay(sm));
				sm.switchtState(new GamePlay(sm));
			}
		});
		
		exitBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});
	}

	@Override
	public void input() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		input();
		
		if(switchY == false) {
			y = y + 0.5f;
			if(y >= 650f)
				switchY = true;
		}
		else{
			y = y - 0.5f;
			if(y <= 580f)
				switchY = false;
		}
		
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(SpriteBatch sb) {
		// TODO Auto-generated method stub
		stage.draw();
		sb.begin();
		sb.draw(logo, x, y);
		sb.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		menu.dispose();
		stage.dispose();
		btnTable.clear();
		logo.dispose();
	}

}
