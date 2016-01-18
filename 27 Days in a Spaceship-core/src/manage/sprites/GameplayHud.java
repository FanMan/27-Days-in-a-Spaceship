package manage.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.SpaceshipGame;

import entity.*;

public class GameplayHud {
	private Clock clock;
	private Label time;
	
	private Label health, hunger, thirst, oxygen, id;
	private Table statusTable;
	
	private Label oxygenStatus, energyStatus, foodStatus, waterStatus;
	private Stage stage;
	private Table crewTable;
	
	private Label crewStatus;
	private Button crewBtnOne, crewBtnTwo, crewBtnThree, crewBtnFour, crewBtnFive;
	private int BtnOneState, BtnTwoState, BtnThreeState, BtnFourState, BtnFiveState;
	private ButtonStyle crewBtnStyle;
	private Skin skin;
	private Texture crewUI;
	
	private Table energyTable;
	
	
	public GameplayHud(Clock c) {
		clock = c;
		stage = new Stage(new ScreenViewport());
		skin = new Skin();
		skin.add("crewButton", new Texture("Crew Face.png"));
		skin.add("downButton", new Texture("badlogic.jpg"));
		
		crewUI = new Texture("Crew Status.png");
		
		time = new Label("", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		time.setFontScale(2f);
		time.setX(SpaceshipGame.WIDTH - 350);
		time.setY(SpaceshipGame.HEIGHT - 50);
		
		oxygenStatus = new Label("", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		oxygenStatus.setFontScale(1.5f);
		oxygenStatus.setX(45);
		oxygenStatus.setY(SpaceshipGame.HEIGHT - 34);
		
		energyStatus = new Label("", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		energyStatus.setFontScale(1.5f);
		energyStatus.setX(155);
		energyStatus.setY(SpaceshipGame.HEIGHT - 34);
		
		foodStatus = new Label("", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		foodStatus.setFontScale(1.5f);
		foodStatus.setX(255);
		foodStatus.setY(SpaceshipGame.HEIGHT - 34);
		
		waterStatus = new Label("", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		waterStatus.setFontScale(1.5f);
		waterStatus.setX(365);
		waterStatus.setY(SpaceshipGame.HEIGHT - 34);
		
		crewBtnStyle = new ButtonStyle();
		crewBtnStyle.up = skin.getDrawable("crewButton");
		crewBtnStyle.down = skin.getDrawable("downButton");
		
		createButtons();
		createStats();
		designStats();
		
		BtnOneState = 0;
		BtnTwoState = 0;
		BtnThreeState = 0;
		BtnFourState = 0;
		BtnFiveState = 0;
		
		crewTable = new Table();
		crewTable.setDebug(true);
		crewTable.setPosition(100, 200);
		crewTable.add(crewBtnOne).width(100f).height(100f);
		crewTable.add(crewBtnTwo).width(100f).height(100f);
		crewTable.add(crewBtnThree).width(100f).height(100f);
		crewTable.add(crewBtnFour).width(100f).height(100f);
		crewTable.add(crewBtnFive).width(100f).height(100f);
		crewTable.setPosition(300f, 75f);
	}
	
	public Texture getUI() {
		return crewUI;
	}
	
	public int getBtnOne() { return BtnOneState; }
	public int getBtnTwo() { return BtnTwoState; }
	public int getBtnThree() { return BtnThreeState; }
	public int getBtnFour() { return BtnFourState; }
	public int getBtnFive() { return BtnFiveState; }
	
	/////////////////////////////
	// creates the buttons to display each crew member's stats
	
	private void createButtons() {
		crewBtnOne = new Button();
		crewBtnOne.setSkin(skin);
		crewBtnOne.setStyle(crewBtnStyle);
		
		crewBtnTwo = new Button();
		crewBtnTwo.setSkin(skin);
		crewBtnTwo.setStyle(crewBtnStyle);
		
		crewBtnThree = new Button();
		crewBtnThree.setSkin(skin);
		crewBtnThree.setStyle(crewBtnStyle);
		
		crewBtnFour = new Button();
		crewBtnFour.setSkin(skin);
		crewBtnFour.setStyle(crewBtnStyle);
		
		crewBtnFive = new Button();
		crewBtnFive.setSkin(skin);
		crewBtnFive.setStyle(crewBtnStyle);
	}
	
	/////////////////////////////
	// creates the stats of each crew member
	
	private void createStats() {
		health = new Label("Health: 100%", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		health.setFontScale(3f);
		hunger = new Label("Hunger: 0%", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		hunger.setFontScale(3f);
		thirst = new Label("Thirst: 0%", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		thirst.setFontScale(3f);
		oxygen = new Label("Oxygen: 100%", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		oxygen.setFontScale(3f);
		id = new Label("ID: 5", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		id.setFontScale(3f);
		crewStatus = new Label("Status: Inactive", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		crewStatus.setFontScale(3f);
	}
	
	private void designStats() {
		statusTable = new Table();
		statusTable.setPosition(600, 550);
		statusTable.add(health);
		statusTable.row();
		statusTable.add(hunger);
		statusTable.row();
		statusTable.add(thirst);
		statusTable.row();
		statusTable.add(oxygen);
		statusTable.row();
		statusTable.add(id);
		statusTable.row();
		statusTable.add(crewStatus);
	}
	
	///////////////////////////
	// create the actions for each button
	
	public void addAction() {
		crewBtnOne.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				switch (BtnOneState) {
				case 1:
					BtnOneState = 0;
					stage.getActors().removeValue(statusTable, true);
					break;
				default:
					BtnOneState = 1;
					stage.addActor(statusTable);
					break;
				}
			}
		});
		
		crewBtnTwo.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				switch (BtnTwoState) {
				case 1:
					BtnTwoState = 0;
					stage.getActors().removeValue(statusTable, true);
					break;
				default:
					BtnTwoState = 1;
					stage.addActor(statusTable);
					break;
				}
			}
		});
		
		crewBtnThree.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				switch (BtnThreeState) {
				case 1:
					BtnThreeState = 0;
					stage.getActors().removeValue(statusTable, true);
					break;
				default:
					BtnThreeState = 1;
					stage.addActor(statusTable);
					break;
				}
			}
		});
		
		crewBtnFour.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				switch (BtnFourState) {
				case 1:
					BtnFourState = 0;
					stage.getActors().removeValue(statusTable, true);
					break;
				default:
					BtnFourState = 1;
					stage.addActor(statusTable);
					break;
				}
			}
		});
		
		crewBtnFive.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				switch (BtnFiveState) {
				case 1:
					BtnFiveState = 0;
					stage.getActors().removeValue(statusTable, true);
					break;
				default:
					BtnFiveState = 1;
					stage.addActor(statusTable);
					break;
				}
			}
		});
	}
	
	////////////////////////////////////////////////////
	// updating and displaying the status of the crew //
	////////////////////////////////////////////////////
	
	public void updateStats(Player crewmate) {
		health.setText("Health: " + (int) crewmate.getHealth());
		hunger.setText("Hunger: " + (int) crewmate.getHunger() + "%");
		thirst.setText("Thirst: " + (int) crewmate.getThirst() + "%");
		oxygen.setText("Oxygen: " + (int) crewmate.getOxygen() + "%");
		id.setText("ID: " + crewmate.getID());
		crewStatus.setText("Status: " + crewmate.currState());
	}
	
	public void displayStatus() {
		stage.addActor(statusTable);
	}
	
	/////////////////////////////////////////////////
	// updating and displaying the countdown timer //
	/////////////////////////////////////////////////
	
	// display the countdown timer to the player
	public void displayTime() {
		stage.addActor(time);
		stage.addActor(crewTable);
		stage.addActor(oxygenStatus);
		stage.addActor(energyStatus);
		stage.addActor(waterStatus);
		stage.addActor(foodStatus);
		Gdx.input.setInputProcessor(stage);
		stage.draw();
	}
	
	// display and update the countdown timer's appearance
	public void updateTime() {
		if(clock.getMinute() < 10) {
			time.setText("Days till rescue:\n" + clock.getDay() + " days : " + 
					clock.getHour() + " hrs : 0" + clock.getMinute() + " mins");
		}
		else {
			time.setText("Days till rescue:\n" + clock.getDay() + " days : " + 
				clock.getHour() + " hrs : " + clock.getMinute() + " mins");
		}
	}
	
	// update ship status
	public void updateShipStats(Spaceship ship) {
		/*oxygenStatus.setText("Oxygen: " + (int) ship.getOxygen() + "%\nFood Supply: " + (int) ship.getFoodSupply()
				+ "%\nWater Supply: " + (int) ship.getWaterSupply() + "%\nEnergy: " + (int) ship.getEnergy()
				+ "%");
		*/
		oxygenStatus.setText((int) ship.getOxygen() + "%");
		energyStatus.setText((int) ship.getEnergy() + "%");
		waterStatus.setText((int) ship.getWaterSupply() + "%");
		foodStatus.setText((int) ship.getFoodSupply() + "%");
		
	}
	
	public void dispose() {
		stage.dispose();
		crewUI.dispose();
	}
}
