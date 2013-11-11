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
	private Tile tileInline;
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
			tempTile.getRight(new Tile());	
			tempTile.getRight().setLeft(tempTile);
		}
		shooter.setTile(tile);
		tile.setOccupant(shooter);
		tileInline = tile.getRight().getRight();
		zombie.setTile(tileInline);
		tileInline.setOccupant(zombie);
	}

	/**
	 * Test method for {@link pvz.Shooter#act()}.
	 */
	@Test
	public void testAct() {
		assertTrue(shooter.act() == 2);
		assertTrue(zombie.getCurrHealth() < zombie.getMaxHealth());
		//removimg zombie and adding another plant to test friendly fire
		tileInline.setOccupant(new SunFlower(1));
		tileInline.getOccupant().setTile(tileInline);
		assertTrue(shooter.act() == 0);
		//removing both zombie and flower
		tileInline.setOccupant(null);
		assertTrue(shooter.act() == 0);
	}

}