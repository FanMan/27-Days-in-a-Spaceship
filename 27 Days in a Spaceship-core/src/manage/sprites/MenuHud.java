package manage.sprites;

import states.game.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.*;
import com.mygdx.game.SpaceshipGame;

public class MenuHud {
	private StateManager sm;
	
	private Table menuTable;
	private Skin skin;
	private ButtonStyle playStyle, creditStyle, exitStyle;
	private Button playBtn, creditsBtn, exitBtn;
	public Stage stage;
	private Viewport viewport;
	
	public MenuHud(StateManager sm) {
		this.sm = sm;
		viewport = new FitViewport(SpaceshipGame.WIDTH, SpaceshipGame.HEIGHT, new OrthographicCamera());
		stage = new Stage(viewport);
		
		// the texture regions for each of the buttons
		skin = new Skin();
		skin.add("playUpBtn", new TextureRegion(new Texture("ButtonAtlas.png"), 0, 0, 256, 126));
		skin.add("playDownBtn", new TextureRegion(new Texture("ButtonAtlas.png"), 0, 126, 256, 126));
		skin.add("creditsUpBtn", new TextureRegion(new Texture("ButtonAtlas.png"), 256, 0, 256, 126));
		skin.add("creditsDownBtn", new TextureRegion(new Texture("ButtonAtlas.png"), 256, 126, 256, 126));
		skin.add("exitUpBtn", new TextureRegion(new Texture("ButtonAtlas.png"), 512, 0, 256, 126));
		skin.add("exitDownBtn", new TextureRegion(new Texture("ButtonAtlas.png"), 512, 126, 256, 126));
		
		// the styles (designs) of the buttons created here
		
		playStyle = new ButtonStyle();
		playStyle.up = skin.getDrawable("playUpBtn");
		playStyle.down = skin.getDrawable("playDownBtn");
		
		creditStyle = new ButtonStyle();
		creditStyle.up = skin.getDrawable("creditsUpBtn");
		creditStyle.down = skin.getDrawable("creditsDownBtn");
		
		exitStyle = new ButtonStyle();
		exitStyle.up = skin.getDrawable("exitUpBtn");
		exitStyle.down = skin.getDrawable("exitDownBtn");
		
		// creating the buttons
		
		playBtn = new Button();
		playBtn.setSkin(skin);
		playBtn.setStyle(playStyle);
		
		creditsBtn = new Button();
		creditsBtn.setSkin(skin);
		creditsBtn.setStyle(creditStyle);
		
		exitBtn = new Button();
		exitBtn.setSkin(skin);
		exitBtn.setStyle(exitStyle);
		
		
		menuTable = new Table();
		menuTable.setPosition(SpaceshipGame.WIDTH - playBtn.getWidth() - 200, 
				SpaceshipGame.HEIGHT - playBtn.getHeight() - 300);
		menuTable.setDebug(false);
		
		menuTable.add(playBtn).pad(20);
		menuTable.row();
		menuTable.add(creditsBtn).pad(20);
		menuTable.row();
		menuTable.add(exitBtn).pad(20);
	}
	
	public void addAction() {
		playBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				sm.switchtState(new GamePlay(sm));
			}
		});
		
		creditsBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				sm.switchtState(new Credits(sm));
			}
		});
		
		exitBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});
	}
	
	public void showMenu() {
		stage.addActor(menuTable);
		Gdx.input.setInputProcessor(stage);
		stage.draw();
	}
	
	public void dispose() {
		menuTable.clear();
		stage.clear();
		skin.dispose();
	}
}
