package model;

public class PlayerData {
	public String choice;
	public SeedPacket seeds;
	public int solarPower;
	public int solarRate;

	public PlayerData(int level) {
		seeds = new SeedPacket(level);
		solarPower = 10;
		solarRate = 5;
	}
}