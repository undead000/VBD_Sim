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
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {  			
		World.num.setSeed(55);
	}
	
	/**
	 * Will test if the world's random number generator dependencies can be seeded. 
	 * The same number of iterations and agents recovered should be seen every run.
	 */
	@Test
	public void testRNG() {
		int iterations = 0;

        World theWorld = new World(50, 50, 3600.0, 0.5, 2);
        
        //add 1 infectious human agent
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
		System.out.println("\n---Final Stats---");
		System.out.println("Number of iterations until disease was wiped out: "+iterations);
		System.out.println("Total amount of agents recovered: "+theWorld.countRecovered()+"\n\n");
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
