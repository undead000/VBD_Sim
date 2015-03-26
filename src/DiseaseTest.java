import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class DiseaseTest {

	/**
	 * this will run before any of your tests in this file
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * this will run after all of the tests in this file
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * this will run before each test
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * this will run after each test
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * this will test the instantiation of my Disease class
	 */
	@Test
	public void testCreation() {
		String seq = "ACTG";
		Disease testDisease = new Disease(seq);
		
	}
	
	/**
	 * this will test whether getting the disease strain works
	 */
	@Test
	public void testGetStrain(){
		String seq = "ACTG";
		Disease testDisease = new Disease(seq);
		assertEquals("ACTG", testDisease.getStrain());

	}
	/**
	 * test whether the getTimeSinceInfection works correctly
	 */
	@Test
	public void testTimeInfected(){
		String seq = "ACTG";
		Disease testDisease = new Disease(seq);
		//this first part tests whether it initializes correctly
		assertEquals(0.0,testDisease.getTimeSinceInfection(),0.0000001);
		//now test to see whether time advances correctly
		//advance the time by an hour
		testDisease.tick(3600.0);
		//check to see whether the Disease's getTimeSinceInfection give the right answer
		assertEquals(3600.0,testDisease.getTimeSinceInfection(),0.000001);
		testDisease.tick(60.0);
		assertEquals(3660.0,testDisease.getTimeSinceInfection(),0.000001);

		
	}

}
