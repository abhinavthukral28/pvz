package model;

import java.util.ArrayList;

/**
 * 
 * @author AlhetiMamoon
 * @author Abhinav Thukral
 * this class is responsible for the level building process
 *
 */

public class LevelBuilder {

	private ArrayList<Actor> zombies;
	private int level;
	public LevelBuilder(int level){

		zombies = new ArrayList<Actor>();
		this.level = level;

	}

	public void addZombie(String string){
		Zombie z = null;
		if(string.equals("defzombie")){
			z = new DefZombie(level);
		}
		else if (string.equals("explosivezombie")){
			z = new ExplosiveZombie(level);
		}
		else if(string.equals("polezombie")){
			z = new PoleZombie(level);
		}
		if(z != null){
			zombies.add(z);
		}
	}

		public void removeZombie(Zombie z){
			zombies.remove(zombies.size() - 1);
		}

		public LevelData getZombies(){
			LevelData leveldata = new LevelData(level);
			leveldata.setWaitingZombiesList(zombies);
			return leveldata;
		}


	}
