package entity;

public class Spaceship {
	private float Oxygen, Energy, WaterSupply, FoodSupply;
	
	public Spaceship() {
		Oxygen = 95.0f;
		Energy = 95.0f;
		WaterSupply = 100.0f;
		FoodSupply = 100.0f;
	}
	
	// reduce the supply on the spaceship
	public void reduceOxygen() {
		if(Oxygen <= 0f) {
			Oxygen = 0f;
		}
		else {
			Oxygen = Oxygen - 0.5f;
		}
	}
	
	public void reduceEnergy() { 
		if(Energy <= 0f) {
			Energy = 0f;
		}
		else {
			Energy = Energy - 0.5f;
		}
	}
	
	public void reduceWaterSupply(float scale) { 
		if(WaterSupply <= 0f) {
			WaterSupply = 0f;
		}
		else {
			WaterSupply = WaterSupply - (0.5f * scale);
		}
	}
	
	public void reduceFoodSupply(float scale) { 
		if(FoodSupply <= 0) {
			FoodSupply = 0f;
		}
		else {
			FoodSupply = FoodSupply - (0.5f * scale);
		}
	}
	
	
	// get the supply amount data on the spaceship
	public float getOxygen() { return Oxygen; }
	public float getEnergy() { return Energy; }
	public float getWaterSupply() { return WaterSupply; }
	public float getFoodSupply() { return FoodSupply; }
}
