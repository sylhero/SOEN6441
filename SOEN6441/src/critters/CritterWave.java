package critters;

import java.util.ArrayList;

/**
 * A class has a arrayList to store critters.
 * 
 * @author Yichen LI
 * @version 1.2.0
 *
 */
public class CritterWave {

	// the arrayList to put critters in a wave
	private ArrayList<CritterBase> wave= new ArrayList<CritterBase>();
	
	/**
	 * Add critters to a wave.
	 * 
	 * @param critter to add in a wave
	 */
	public void addCritter(CritterBase critter)
	{
		wave.add(critter);
	}
	
	/**
	 * Return an arrayList of wave.
	 * 
	 * @return a wave of critters.
	 */
	public ArrayList<CritterBase> getCritterWave()
	{
		return wave;
	}
		

}
