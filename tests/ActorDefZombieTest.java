package tests;
import java.util.Random;

import static org.junit.Assert.*;
import model.DefZombie;
import model.SunFlower;
import model.Tile;

import org.junit.Before;
import org.junit.Test;

import controller.*;

/**
 * Tests DefZombie class and Actor class
 * @author Abhinav Thukral
 *
 */
public class ActorDefZombieTest {
	private DefZombie actor;
	private Tile tile;

	@Before
	public void setUp() throws Exception{
		actor = new DefZombie(1);
		Tile prevTile = new Tile();
		Tile nextTile = new Tile();
		tile = new Tile();
		tile.setLeft(prevTile);
		tile.setRight(nextTile);
		actor.setTile(tile);
		tile.setOccupant(actor);
		
	}
	/**
	 * Tests Act() in DefZombie and also private methods
	 * move() and attack()
	 */
	@Test
	public void testAct() {
		Tile leftTile = tile.getLeft();
		//Testing attack
		leftTile.setOccupant(new DefZombie(1));
		leftTile.getOccupant().setTile(leftTile);
		assertTrue(0 == actor.act());
		leftTile.setOccupant(new SunFlower(1));
		leftTile.getOccupant().setTile(leftTile);
		assertTrue(2 == actor.act());
		assertTrue(leftTile.getOccupant().getCurrHealth() < leftTile.getOccupant().getMaxHealth());
		
		//Testing move
		leftTile.setOccupant(null);
		assertTrue(1 == actor.act());
		tile.setLeft(null);
		assertTrue(-1 == actor.act());
		
	}
/**
 * Testing takeDamage inherited from Actor
 * Also tests private methods die() and isAlive()
 */
	@Test 
	public void testTakeDamage() {
		//Also tests is alive
		assertTrue(actor.isAlive());
		// for random int damage
		Random r = new Random();
		int damage = r.nextInt(actor.getMaxHealth());
		assertTrue((actor.getMaxHealth() - damage) == actor.takeDamage(damage));
		//Also tests is alive
		assertTrue(actor.isAlive());
		assertTrue(actor.takeDamage(1000) == 0);
		//Also tests private method die()
		assertFalse(actor.isAlive());
		
	}
}
