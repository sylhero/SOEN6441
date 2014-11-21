package critters;

import java.awt.Point;
import java.util.LinkedList;

/**
 * A builder class to corporate different types critters into a wave.
 * 
 * @author Yichen LI
 *
 */
public class CritterWaveBuilder {

	/**
	 * Get a wave.
	 * 
	 * @param CorrectRoute to lead critter to the exit
	 * @param movePoint to keep some space between critters
	 * @return a critter wave
	 */
	public CritterWave prepareCritterWave(LinkedList<Point> CorrectRoute, int movePoint)
	{
		CritterWave wave = new CritterWave();
		
		for (int i = 0; i < 3; i++)
		{
			wave.addCritter(new NormalCritter(CorrectRoute, i * movePoint));
		}
		
		for (int i = 0; i < 2; i++)
		{
			wave.addCritter(new HeavilyArmedCritter(CorrectRoute, (i + 3) * movePoint));
		}
		
		return wave;
	}

}
 