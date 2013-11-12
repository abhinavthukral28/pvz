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
	
	
	/**
	 * update gathers solar power and advances zombies onto the grid
	 * this tests to see that solar power grows at the appropriate rate
	 */
	@Test
	public void testUpdate(){
		int factor = scaleModel.getSolarRate();
		assertTrue(scaleModel.getSolarPower() == 10);
		scaleModel.update();
		assertTrue(scaleModel.getSolarPower() == factor + 10);
		for(int i = 0; i < 100; i++){
			scaleModel.update();
		}
		assertTrue(scaleModel.getZombies().isEmpty());
		assertTrue(scaleModel.getSolarPower() == 101*factor + 10);
	}
	
	/**
	 * getTile looks up a tile based on its co-ordinates.
	 * this tests that tiles are accessible and initialised as expected by the constructor
	 */
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
	
	/**
	 * state returns -1 if the player has lost, 1 if they have won, 0 otherwise
	 * tests that the player has lost when there is a zombie in their home row, and has won when there are no zombies active or waiting
	 */
	@Test
	public void testState(){	
		DefZombie dummy = new DefZombie(1);
		assertTrue(scaleModel.state() == 0);
		scaleModel.getTile(0, 0).setOccupant(dummy);
		assertTrue(scaleModel.state() == -1);
		scaleModel.getTile(0, 0).setOccupant(null);
		
		scaleModel.setActorList(new ArrayList<Actor>());
		scaleModel.setWaitingZombiesList(new ArrayList<Actor>());
		assertTrue(scaleModel.state() == 1);
	}
	
	/**
	 * Tests that actorList is populated with living, unfriendly zombies when Model is initialised
	 */
	@Test
	public void testGetZombies(){
		ArrayList<Actor> dummies = scaleModel.getZombies();
		assertTrue(dummies != null);
		for(Actor a: dummies){
			assertTrue(a.isAlive());
			assertTrue(!a.isFriendly());
		}
	}
	
	/**
	 * Tests the gameGrid, traverses the linked list, verifying that it is initialised properly
	 */
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
	
	/**
	 * Verifies that a plant is properly placed and paid for.
	 */
	@Test
	public void testPlacePlant(){
		int firstVal = scaleModel.getSolarPower();
		scaleModel.placePlant(scaleModel.getTile(0, 0), "sunflower");
		assertTrue(scaleModel.getSolarPower() < firstVal);
		assertTrue(scaleModel.getTile(0, 0).getOccupant() != null);
	}

}
