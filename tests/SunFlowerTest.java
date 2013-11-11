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
public class SunFlowerTest {
	private SunFlower sunflower;
	

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		sunflower = new SunFlower(1);
		
	}

	/**
	 * Test method for {@link pvz.SunFlower#act()}.
	 */
	@Test
	public void testAct() {
		assertTrue(sunflower.act() == 5);
		assertTrue(sunflower.act() == 0);
		assertTrue(sunflower.act() == 0);
		assertTrue(sunflower.act() == 5);
	}

}
