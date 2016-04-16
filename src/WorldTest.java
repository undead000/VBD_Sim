import static org.junit.Assert.*;
import java.util.Vector;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This class tests methods in the World class
 */
public class WorldTest {

	/**
	 * Before any tests occur, sets the random number generator seed so that each sequence of events
	 * will run identically.
	 */
	@BeforeClass
	public static void setUpBeforeClass() {  			
		World.seedRand(55);
	}
	
	/**
	 * Will test if the world's random number generator dependencies can be seeded. 
	 * The same number-pair of iterations and countRecovered should be seen every run.
	 */
	@Test
	public void testRNG() {
		World.seedRand(55);
		int iterations = 0; //number of iterations until disease is extinct

        //hardcoded values dependent on the seed(55), however it also seems to be dependent on
		//computer time as well, changing every hour. Remains constant for 1hr periods, but changes
		//the next hour
		int knownIterations = 169;
		int knownCountRecovered = 1;
		
		//create the world
		World theWorld = new World(50, 50, 3600.0, 0.5, 2);
        
        //add 1 infectious human agent to the world
		Environment groundZero = World.getRandomLocation();
		Disease newInfection = new Disease("ACGT");
		Human patientZero = new Human(groundZero.getRow(), groundZero.getColumn());
		patientZero.recieveDisease(newInfection.getStrain());
		groundZero.enter(patientZero);
		groundZero.doEntrances();
		
		//pass time aslong as there is someone infected
		while(theWorld.countInfections() > 0){
			iterations++;
			theWorld.tick();
		}
		//check if both number of iterations until disease went extinct and the number
		//of recovered agents are as expected for the given seed
		assertEquals(knownIterations, iterations);
		assertEquals(knownCountRecovered, theWorld.countRecovered());
	}
	
	/**
	 * Tests countInfections() method by entering numInfected number of infected agents
	 * into the world and comparing that number to the output of countInfections()
	 */
	@Test
	public void testCountInfected(){
        World theWorld = new World(50, 50, 3600.0, 0.5, 2);
        int numInfected = 100;
        
        //add numInfected number of infected individuals
		Environment groundZero = World.getRandomLocation();
		Disease newInfection = new Disease("ACGT");
		Human patientZero = new Human(groundZero.getRow(), groundZero.getColumn());
		patientZero.recieveDisease(newInfection.getStrain());
		
		for(int i=0; i<numInfected; i++){
			groundZero.enter(patientZero);
		}
		groundZero.doEntrances();
		
		//check if the number of infected individuals inserted equals the countInfections() output
		assertEquals(numInfected, theWorld.countInfections());
	}
	
	/**
	 * Tests countRecovered() method by entered numInfected number of infected agents
	 * then setting their status to recovered.
	 */
	@Test
	public void testCountRecovered(){
        World theWorld = new World(50, 50, 3600.0, 0.5, 2);
        int numInfected = 100;
        int expectedRecovered = numInfected;
        
        //add numInfected number of infected individuals
		Environment groundZero = World.getRandomLocation();
		Disease newInfection = new Disease("ACGT");
		Human patientZero = new Human(groundZero.getRow(), groundZero.getColumn());
		patientZero.recieveDisease(newInfection.getStrain());
		
		for(int i=0; i<numInfected; i++){
			groundZero.enter(patientZero);
		}
		groundZero.doEntrances();
		
		//set all infected individuals inserted into theWorld to recovered
		Vector<Agent> inhabitants = groundZero.getAll("HUMAN");
		for(Agent agent:inhabitants){
			for(Disease disease: agent.infections){
				disease.setState(Disease.State.RECOVERED);
			}
		}
		
		//check if the number of individuals expected to be recovered matches the output of countRecovered()
		assertEquals(expectedRecovered, theWorld.countRecovered());
	}
}
