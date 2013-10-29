/**
 * 
 */
package pvz.tests;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author abhinav
 *
 */
public class DefZombieTest {
    public void setUp(){
        DefZombie zom = new DefZombie(1);
        Tile tile = new Tile();
        tile.setLeft = new Tile();
        tile.setRight = new Tile();
        zom.setTile(tile);
	/**
	 * Test method for {@link pvz.DefZombie#act()}.
	 */
	@Test
	public void testAct() {
        DefZombie zom = new DefZombie(1);
        

		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link pvz.DefZombie#getCost()}.
	 */
	@Test
	public void testGetCost() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link pvz.DefZombie#DefZombie(int)}.
	 */
	@Test
	public void testDefZombie() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link pvz.Actor#takeDamage(int)}.
	 */
	@Test
	public void testTakeDamage() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link pvz.Actor#setTile(pvz.Tile)}.
	 */
	@Test
	public void testSetTile() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link pvz.Actor#isAlive()}.
	 */
	@Test
	public void testIsAlive() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link pvz.Actor#toString()}.
	 */
	@Test
	public void testToString() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link pvz.Actor#isFriendly()}.
	 */
	@Test
	public void testIsFriendly() {
		fail("Not yet implemented"); // TODO
	}

}
