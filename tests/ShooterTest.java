/**
 * 
 */
package tests;
import pvz.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Abhinav Thukral
 *
 */
public class ShooterTest {
	private DefZombie zombie;
	private Tile tile;
	private Shooter shooter;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		zombie = new DefZombie(1);
		shooter = new Shooter(1);
		tile = new Tile();
		for(int i = 0; i < 6; i++){
			Tile tempTile = tile;
			while(tempTile.getRight() != null){
				tempTile = tempTile.getRight();
			}
			tempTile.setRight(new Tile());	
			tempTile.getRight().setLeft(tempTile);
		}
		tile.setOccupant(shooter);
		tile.getRight().getRight().setOccupant(zombie);
	}

	/**
	 * Test method for {@link pvz.Shooter#act()}.
	 */
	@Test
	public void testAct() {
		assertTrue(shooter.act() == 2);
		//removimg zombie and adding another plant to test friendly fire
		tile.getRight().getRight().setOccupant(new SunFlower(1));
		assertTrue(shooter.act() == 0);
		//removing both zombie and flower
		tile.getRight().getRight().setOccupant(null);
		assertTrue(shooter.act() == 0);
	}

	/**
	 * Test method for {@link pvz.Shooter#getCost()}.
	 */
	@Test
	public void testGetCost() {
		fail("Not yet implemented");
	}

}
