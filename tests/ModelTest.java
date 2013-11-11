package tests;

/*
 * Model class is poorly designed, and difficult to test/verify.
 */

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import pvz.Actor;
import pvz.DefZombie;
import pvz.Model;
import pvz.Shooter;
import pvz.Tile;

public class ModelTest {
	private Model scaleModel;
	@Before
	public void setUp() throws Exception {
		scaleModel = new Model(1);
	}

	@Test
	public void test() {
		
	}
	
	@Test
	public void testUpdate(){
		int factor = scaleModel.getSolarRate();
		assertTrue(scaleModel.getSolarPower() == 0);
		scaleModel.update();
		assertTrue(scaleModel.getSolarPower() == factor);
		for(int i = 0; i < 100; i++){
			scaleModel.update();
		}
		assertTrue(scaleModel.getZombies().isEmpty());
		assertTrue(scaleModel.getSolarPower() == 100*factor);
		//all update does is move zombies onto the field, and make actors act.
	}
	
	@Test
	public void testGetTile(){
		Tile tempTile;
		for(int i = 0; i < scaleModel.MAX_COLS; i++){
			for(int j = 0; j < scaleModel.MAX_ROWS; j++){
				tempTile = scaleModel.getTile(i, j);
				assertTrue(tempTile != null);
				assertTrue(tempTile.getOccupant() == null);
				if(i == 0){
					assertTrue(tempTile.getLeft() == null);
				}
				else{
					assertTrue(tempTile.getLeft() == scaleModel.getTile((i - 1), j));
				}
				if(i == scaleModel.MAX_COLS){
					assertTrue(tempTile.getRight() == null);
				}
				else{
					assertTrue(tempTile.getRight() == scaleModel.getTile((i + 1), j));
				}
			}
		}
	}
	
	@Test
	public void testState(){
		ArrayList<Actor> plantForceOne = null;
		Tile tempTile = null;
		for(int i = 0; i < scaleModel.MAX_COLS*scaleModel.MAX_ROWS/2; i++){
			plantForceOne.add(new Shooter(10));
		}
		DefZombie dummy = new DefZombie(1);
		assertTrue(scaleModel.state() == 0);
		scaleModel.getTile(0, 0).setOccupant(dummy);
		assertTrue(scaleModel.state() == -1);
		
		for(int i = 0; i < scaleModel.MAX_COLS/2; i++){
			for(int j = 0; j < scaleModel.MAX_ROWS; j++){
				tempTile = scaleModel.getTile(i, j);
				assertTrue(tempTile != null);
				tempTile.setOccupant(plantForceOne.remove(i*scaleModel.MAX_COLS/2 + j));
			}
		}
		for(int i = 0; i < 100; i++){
			scaleModel.update();
		}
		assertTrue(scaleModel.state() == 1);
	}
	
	@Test
	public void testGetZombies(){
		ArrayList<Actor> dummies = scaleModel.getZombies();
		assertTrue(dummies != null);
		for(Actor a: dummies){
			assertTrue(a.isAlive());
			assertTrue(!a.isFriendly());
		}
	}
	
	@Test
	public void testGetGameGrid(){
		ArrayList<Tile> testList = scaleModel.getGameGrid();
		Tile tempTile;
		for(int i = 0; i < scaleModel.MAX_ROWS; i++){
			tempTile = testList.get(i);
			assertTrue(tempTile != null);
			for(int j = 0; j < scaleModel.MAX_COLS; j++){
				tempTile = tempTile.getRight();
				assertTrue(tempTile != null);
			}
		}
	}
	
	@Test
	public void testGetLevel(){
		assertTrue(scaleModel.getLevel() == 1);
	}
	
	@Test
	public void testPlacePlant(){
		int firstVal = scaleModel.getSolarPower();
		scaleModel.placePlant(scaleModel.getTile(0, 0), "sunflower");
		assertTrue(scaleModel.getSolarPower() < firstVal);
		assertTrue(scaleModel.getTile(0, 0).getOccupant() != null);
	}

}
