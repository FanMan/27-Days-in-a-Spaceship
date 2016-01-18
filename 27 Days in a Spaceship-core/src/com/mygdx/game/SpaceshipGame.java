package com.mygdx.game;

import states.game.*;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class SpaceshipGame extends ApplicationAdapter {
	SpriteBatch batch;
	public StateManager sm;
	Texture layerOne, layerTwo;
	Vector2 layerOnePos1, layerOnePos2, layerTwoPos1, layerTwoPos2;
	
	// these will be loaded on start-up
	public static final int WIDTH = 960; // 640
	public static final int HEIGHT = 960;  // 960
	public static final String TITLE = "27 Days in a Spaceship";
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		layerOne = new Texture("Stars Layer 1.png");
		layerTwo = new Texture("Stars Layer 2.png");
		//Gdx.gl.glClearColor(1, 0, 0, 1);
		
		layerOnePos1 = new Vector2(0f, 0f);
		layerOnePos2 = new Vector2(-960f, 0f);
		
		layerTwoPos1 = new Vector2(0f, 0f);
		layerTwoPos2 = new Vector2(-960f, 0f);
		
		sm = new StateManager();
		sm.push(new Menu(sm));
	}
	
	public void backgroundStars() {
		if(layerOnePos1.x > WIDTH) {
			layerOnePos1.x = -960;
		}
		if(layerOnePos2.x > WIDTH) {
			layerOnePos2.x = -960f;
		}
		
		if(layerTwoPos1.x > WIDTH) {
			layerTwoPos1.x = -960;
		}
		if(layerTwoPos2.x > WIDTH) {
			layerTwoPos2.x = -960f;
		}
		
		layerOnePos1.x+=0.1f;
		layerOnePos2.x+=0.1f;
		layerTwoPos1.x+=0.3f;
		layerTwoPos2.x+=0.3f;
	}
	
	public void input() {
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
	}
	
	@Override
	public void render () {
		input();
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		backgroundStars();
		
		batch.begin();
		batch.draw(layerOne, layerOnePos1.x, layerOnePos1.y);
		batch.draw(layerOne, layerOnePos2.x, layerOnePos2.y);
		batch.draw(layerTwo, layerTwoPos1.x, layerTwoPos1.y);
		batch.draw(layerTwo, layerTwoPos2.x, layerTwoPos2.y);
		batch.end();
		
		sm.update(Gdx.graphics.getDeltaTime());
		sm.render(batch);
	}
}
