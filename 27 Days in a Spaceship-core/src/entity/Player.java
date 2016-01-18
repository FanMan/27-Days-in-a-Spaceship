package entity;

import java.util.Random;

public class Player {
	private float Health, Oxygen, Thirst, Hunger;
	private int thirstCounter, hungerCounter;
	private PlayerState state;
	private boolean alive;
	
	private Random rand = new Random();
	private int id;
	
	public Player(PlayerState pState) {
		id = rand.nextInt(100) + 1;
		
		state = PlayerState.Inactive;
		alive = true;
		
		Health = 100.0f;
		Oxygen = 100.0f;
		Thirst = (float) rand.nextInt(30) + 1;
		Hunger = (float) rand.nextInt(35) + 1;
	}
	
	public int getID() { return id; }
	public float getHealth() { return Health; }
	public float getOxygen() { return Oxygen; }
	public float getThirst() { return Thirst; }
	public float getHunger() { return Hunger; }
	
	public void updateHunger() { Hunger++; }
	public void updateThirst() { Thirst++; }
	
	public void lowerThirst() {
		if(Thirst - 15f < 0) {
			Thirst = 0f;
		}
		else {
			Thirst = Thirst - 15f;
		}
	}
	
	public void lowerHunger() {
		if(Hunger - 30f < 0) {
			Hunger = 0f;
		}
		else {
			Hunger = Hunger - 30f;
		}
	}
	
	public void lowerOxygen() {
		if(Oxygen <= 0) {
			Oxygen = 0f;
		}
		else {
			Oxygen--;
		}
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public void updateLife() {
		if(Hunger >= 100 || Thirst >= 100 || Oxygen <= 0) {
			alive = false;
		}
	}
	
	// gets the current state of the player
	public PlayerState currState() {
		return state;
	}
	
	public void setActivity(PlayerState pState) {
		switch(pState) {
			case Drinking : 
				state = pState;
				System.out.println("Drinking water");
				break;
			case Eating :
				state = pState;
				System.out.println("Eating food");
				break;
			case Sleeping :
				state = pState;
				System.out.println("Sleeping");
				break;
			default :
				state = PlayerState.Inactive;
				//System.out.println("Doing nothing");
				break;
		}
	}
}
