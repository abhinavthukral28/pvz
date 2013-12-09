package model;

/**
 * Contains data associated with the player.
 * The player has solar power, chooses a plant, and has seeds.
 * @author StuartMacdonald
 *
 */

public class PlayerData implements Cloneable {
	private String choice;
	private SeedPacket seeds;
	public int solarPower;
	public int solarRate;

	public PlayerData(int level) {
		seeds = new SeedPacket(level);
		solarPower = 10;
		solarRate = 5;
	}
	
	void update(){
		solarPower += solarRate;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public SeedPacket getSeeds() {
		return seeds;
	}

	public int getSolarPower() {
		return solarPower;
	}
	
	public Plant getPlant(String type){
		return seeds.getPlant(type, solarPower);
	}
	
	public Object clone() throws CloneNotSupportedException{
		PlayerData clone = (PlayerData)super.clone();
		clone.choice = new String(this.choice);
		clone.seeds = (SeedPacket)seeds.clone();
		clone.solarPower = this.solarPower;
		clone.solarRate = this.solarRate;
		return clone;
	}
}