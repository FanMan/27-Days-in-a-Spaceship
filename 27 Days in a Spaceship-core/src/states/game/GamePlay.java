package states.game;


import java.util.ArrayList;

import manage.sprites.GameplayHud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.SpaceshipGame;

import entity.*;

public class GamePlay extends States {
	
	private long start, end, delay;
	private int prev_hour, curr_hour, prev_min, curr_min;
	private boolean wait, switchShipY;
	
	private int counter;
	
	private Music music;
	
	private ArrayList<Player> crew;
	private PlayerState pState;
	private Spaceship shuttle;
	private Clock clock;
	private GameplayHud hud;
	
	private TextureRegion oxygenLevel, energyLevel, foodLevel, waterLevel;
	private int oxygenSwitch, energySwitch, foodSwitch, waterSwitch;
	private Texture ship;
	private float shipY;
	
	public GamePlay(StateManager sm) {
		super(sm);
		music = Gdx.audio.newMusic(Gdx.files.internal("Odyssey.mp3"));
		music.setLooping(true);
		music.setVolume(0.3f);
		music.play();
		
		clock = new Clock();
		hud = new GameplayHud(clock);
		hud.updateTime();
		
		// timer variables
		start = System.nanoTime();
		end = start;
		delay = 0;
		wait = false;
		
		oxygenLevel = new TextureRegion(new Texture("OxygenLevel.png"), 0, 0, 100, 256);
		energyLevel = new TextureRegion(new Texture("EnergyAtlas.png"), 0, 0, 100, 256);
		foodLevel = new TextureRegion(new Texture("Food Supply.png"), 0, 0, 100, 256);
		waterLevel = new TextureRegion(new Texture("Water Supply.png"), 0, 0, 100, 256);
		
		shuttle = new Spaceship();
		
		oxygenSwitch = (int) shuttle.getOxygen();
		energySwitch = (int) shuttle.getEnergy();
		foodSwitch = (int) shuttle.getFoodSupply();
		waterSwitch = (int) shuttle.getWaterSupply();
		
		ship = new Texture("Shuttle.png");
		shipY = 200f;
		
		// creates the 5 crew members
		crew = new ArrayList<Player>();
		for(int i = 0; i < 5; i++) {
			crew.add(new Player(pState));
		}
		
		prev_hour = clock.getHour();
		prev_min = clock.getMinute();
		curr_hour = prev_hour;
		curr_min = prev_min;
		
		counter = 0;
		
		hud.addAction();
	}
	
	private void gameOver() {
		// if the ship runs out of oxygen, the crew runs out of oxygen quickly
		if(shuttle.getOxygen() <= 0) {
			for(int i = 0; i < crew.size(); i++) {
				crew.get(i).lowerOxygen();
			}
		}
		// if the ship runs out of energy, oxygen decreases
		if(shuttle.getEnergy() <= 0) {
			shuttle.reduceOxygen();
		}
		// if all crew members are dead, then its game over
		if(!crew.get(0).isAlive() && !crew.get(1).isAlive() && !crew.get(2).isAlive() && 
				!crew.get(3).isAlive() && !crew.get(4).isAlive()) {
			sm.switchtState(new GameOver(sm));
		}
		
		/*if((clock.getDay() == 0 && clock.getHour() == 0 && clock.getMinute() == 0) &&
				crew.get(0).isAlive() || crew.get(1).isAlive() || crew.get(2).isAlive() ||
				crew.get(3).isAlive() || crew.get(4).isAlive()) {
			sm.switchtState(new GameOver(sm));
		}*/
	}
	
	private void updateCrewStates(int id) {
		if(wait == false) {
			if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
				crew.get(id).setActivity(PlayerState.Drinking);
				shuttle.reduceWaterSupply(0.5f);
				crew.get(id).lowerThirst();
				wait = true;
			}
			else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
				crew.get(id).setActivity(PlayerState.Eating);
				shuttle.reduceFoodSupply(0.7f);
				crew.get(id).lowerHunger();
				wait = true;
			}
			else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
				crew.get(id).setActivity(PlayerState.Sleeping);
				wait = true;
			}
			else {
				crew.get(id).setActivity(PlayerState.Inactive);
				//System.out.println(crew[1]);
			}
		}
	}
	
	public void updateShipStatus() {
		oxygenSwitch = (int) shuttle.getOxygen();
		energySwitch = (int)  shuttle.getEnergy();
		foodSwitch = (int) shuttle.getFoodSupply();
		waterSwitch = (int) shuttle.getWaterSupply();
		
		// switch the design of the oxygen meter based upon the amount left
		switch(oxygenSwitch) {
		case 84: oxygenLevel.setRegion(100, 0, 100, 256);
			break;
		case 68: oxygenLevel.setRegion(200, 0, 100, 256);
			break;
		case 52: oxygenLevel.setRegion(300, 0, 100, 256);
			break;
		case 36: oxygenLevel.setRegion(0, 256, 100, 256);
			break;
		case 20: oxygenLevel.setRegion(100, 256, 100, 256);
			break;
		case 0: oxygenLevel.setRegion(200, 256, 100, 256);
			break;
		}
		
		switch(energySwitch) {
		case 84: energyLevel.setRegion(100, 0, 100, 256);
			break;
		case 68: energyLevel.setRegion(200, 0, 100, 256);
			break;
		case 52: energyLevel.setRegion(300, 0, 100, 256);
			break;
		case 36: energyLevel.setRegion(0, 256, 100, 256);
			break;
		case 20: energyLevel.setRegion(100, 256, 100, 256);
			break;
		case 0: energyLevel.setRegion(200, 256, 100, 256);
			break;
		}
		
		switch(foodSwitch) {
		case 84: foodLevel.setRegion(100, 0, 100, 256);
			break;
		case 68: foodLevel.setRegion(200, 0, 100, 256);
			break;
		case 52: foodLevel.setRegion(300, 0, 100, 256);
			break;
		case 36: foodLevel.setRegion(0, 256, 100, 256);
			break;
		case 20: foodLevel.setRegion(100, 256, 100, 256);
			break;
		case 0: foodLevel.setRegion(200, 256, 100, 256);
			break;
		}
		
		switch(waterSwitch) {
		case 84: waterLevel.setRegion(100, 0, 100, 256);
			break;
		case 68: waterLevel.setRegion(200, 0, 100, 256);
			break;
		case 52: waterLevel.setRegion(300, 0, 100, 256);
			break;
		case 36: waterLevel.setRegion(0, 256, 100, 256);
			break;
		case 20: waterLevel.setRegion(100, 256, 100, 256);
			break;
		case 0: waterLevel.setRegion(200, 256, 100, 256);
			break;
		}
	}
	
	@Override
	public void input() {
		// TODO Auto-generated method stub
		//if(Gdx.input.justTouched()) {
		//	sm.switchtState(new Menu(sm));
		//}
		if(hud.getBtnOne() == 1) {
			updateCrewStates(0);
		}
		else if(hud.getBtnTwo() == 1) {
			updateCrewStates(1);
		}
		else if(hud.getBtnThree() == 1) {
			updateCrewStates(2);
		}
		else if(hud.getBtnFour() == 1) {
			updateCrewStates(3);
		}
		else if(hud.getBtnFive() == 1) {
			updateCrewStates(4);
		}
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		//clock.updateTime();
		input();
		gameOver();
		updateShipStatus();
		
		if(switchShipY == false) {
			shipY = shipY + (10 * dt);
			if(shipY >= 210f)
				switchShipY = true;
		}
		else{
			shipY = shipY - (10 * dt);
			if(shipY <= 190f)
				switchShipY = false;
		}
		
		end = System.nanoTime();
		delay = (end - start) / 1000000000;
		
		for(int i = 0; i < crew.size(); i++) {
			crew.get(i).updateLife();
		}
		
		if(delay == 1.0) {
			clock.updateTime();
			hud.updateTime();
			
			counter++;
			
			// if 100 seconds pass, lower thirst
			if(counter % 100 == 0) {
				for(int i = 0; i < crew.size(); i++) {
					crew.get(i).lowerThirst();
				}
			}
			
			// if 130 seconds pass, lower hunger
			if(counter % 130 == 0) {
				for(int i = 0; i < crew.size(); i++) {
					crew.get(i).lowerHunger();
				}
			}
			
			// if 150 seconds pass, reduce oxygen on the ship
			if(counter % 150 == 0) {
				shuttle.reduceOxygen();
			}
			
			// if 170 seconds pass, reduce energy on the ship
			if(counter % 170 == 0) {
				shuttle.reduceEnergy();
			}
			
			curr_hour = clock.getHour();
			curr_min = clock.getMinute();
		//	System.out.println(wait);
			if(curr_hour != prev_hour && curr_min == prev_min && wait == true) {
				wait = false;
				//System.out.println("An exact hour has passed");
				prev_hour = curr_hour;
			}
			
			start = System.nanoTime(); // reset time
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		// TODO Auto-generated method stub
		//Gdx.gl.glClearColor(0, 0, 0, 1);
		sb.begin();
		
		sb.draw(ship, 10, shipY);
		
		sb.draw(oxygenLevel, 10, SpaceshipGame.HEIGHT - 260);
		sb.draw(energyLevel, 120, SpaceshipGame.HEIGHT - 260);
		sb.draw(foodLevel, 230, SpaceshipGame.HEIGHT - 260);
		sb.draw(waterLevel, 340, SpaceshipGame.HEIGHT - 260);
		
		if(hud.getBtnOne() == 1 && hud.getBtnTwo() == 0 && hud.getBtnThree() == 0
				&& hud.getBtnFour() == 0 && hud.getBtnFive() == 0) {
			sb.draw(hud.getUI(), 140, 170, 700, 600);
			hud.updateStats(crew.get(0));
		}
		else if(hud.getBtnOne() == 0 && hud.getBtnTwo() == 1 && hud.getBtnThree() == 0
				&& hud.getBtnFour() == 0 && hud.getBtnFive() == 0) {
			sb.draw(hud.getUI(), 140, 170, 700, 600);
			hud.updateStats(crew.get(1));
		}
		else if(hud.getBtnOne() == 0 && hud.getBtnTwo() == 0 && hud.getBtnThree() == 1
				&& hud.getBtnFour() == 0 && hud.getBtnFive() == 0) {
			sb.draw(hud.getUI(), 140, 170, 700, 600);
			hud.updateStats(crew.get(2));
		}
		else if(hud.getBtnOne() == 0 && hud.getBtnTwo() == 0 && hud.getBtnThree() == 0
				&& hud.getBtnFour() == 1 && hud.getBtnFive() == 0) {
			sb.draw(hud.getUI(), 140, 170, 700, 600);
			hud.updateStats(crew.get(3));
		}
		else if(hud.getBtnOne() == 0 && hud.getBtnTwo() == 0 && hud.getBtnThree() == 0
				&& hud.getBtnFour() == 0 && hud.getBtnFive() == 1) {
			sb.draw(hud.getUI(), 140, 170, 700, 600);
			hud.updateStats(crew.get(4));
		}
		
		sb.end();
		hud.displayTime();
		hud.updateShipStats(shuttle);
	}
	
	@Override
	public void dispose() {
		music.dispose();
		hud.dispose();
	}
}
