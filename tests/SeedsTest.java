/**
 * 
 */
package tests;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pvz.*;

/**
 * @author Abhinav Thukral
 *
 */
public class SeedsTest {

	private Seeds seed;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.seed = new Seeds(1);
	}

	/**
	 * Test method for {@link pvz.Seeds#getPlant(java.lang.String, int)}.
	 */
	@Test
	public void testGetPlant() {
		Actor actor = seed.getPlant("sunflower", 7);
		assertFalse(actor == null);
		assertTrue(actor instanceof SunFlower);
		
		actor = seed.getPlant("sunflower", -1);
		assertTrue(actor == null);
		
		actor = seed.getPlant("sdfad", 7);
		assertTrue(actor == null);
		
		actor = seed.getPlant("shooter", 7);
		assertFalse(actor == null);
		assertTrue(actor instanceof Shooter);
		
		actor = seed.getPlant("shooter", 0);
		assertTrue(actor == null);
	}

}
