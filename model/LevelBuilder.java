package model;

import java.util.ArrayList;
import java.util.Observable;

/**
 * 
 * @author AlhetiMamoon
 * @author Abhinav Thukral
 * this class is responsible for the level building process
 *
 */

public class LevelBuilder extends Observable{

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
		this.setChanged();
		notifyObservers();
	}

		public void removeZombie(){
			zombies.remove(zombies.size() - 1);
			this.setChanged();
			notifyObservers();
		}

		public LevelData getLevel(){
			LevelData leveldata = new LevelData(level);
			leveldata.setWaitingZombiesList(zombies);
			return leveldata;
		}


	}
