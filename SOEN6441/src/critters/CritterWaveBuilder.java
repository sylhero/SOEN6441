package critters;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A builder class to corporate different types critters into a wave.
 * 
 * @author Yichen LI
 * @version 1.2.0
 *
 */
public class CritterWaveBuilder {

	CritterWave wave;
	
	
	/**
	 * Get a wave.
	 * 
	 * @param CorrectRoute to lead critter to the exit
	 * @param movePoint to keep some space between critters
	 * @return a critter wave
	 */
	public void prepareCritterWave(String buildType)
	{
		wave = new CritterWave();
		
		
		if(buildType.equalsIgnoreCase("pureNormal"))
		{
			for (int i = 0; i < 5; i++) 
			{
				wave.addCritter(new NormalCritter());
			}
						
		}else if(buildType.equalsIgnoreCase("pureArmed")){
			
			for (int i = 0; i < 5; i++) 
			{
				wave.addCritter(new HeavilyArmedCritter());
			}
			
		}else if(buildType.equalsIgnoreCase("mixed")){
			
			for (int i = 0; i < 3; i++)
			{
				wave.addCritter(new NormalCritter());
			}
			
			for (int i = 0; i < 2; i++)
			{
				wave.addCritter(new HeavilyArmedCritter());
			}
		}
			
	}

	
	/**
	 * Build the route of each critter.
	 * 
	 * @param route of a critter
	 */
	public void buildRoute(LinkedList<Point> route)
	{
		
		for (CritterBase critter : wave.getCritterWave())
		{	
		
			critter.setCorrectRouteCopy(route); // set route of each critter
		
		}
	}

	/**
	 * Build start point of a critter.
	 */
	public void buildStartPoint()
	{
		for (CritterBase critter : wave.getCritterWave())
		{
			critter.setStartPoint(critter.correctRouteCopy);
		}
	}
	
	
	/**
	 * Build x and y axis.
	 */
	public void buildXAndY()
	{
		for (CritterBase critter : wave.getCritterWave())
		{
			critter.setX();
			critter.setY();
		}
	}
	
	/**
	 * Build next point after start point.
	 */
	public void buildNextPoint()
	{
		for (CritterBase critter : wave.getCritterWave())
		{
			critter.setNextPoint(critter.getCorrectRouteCopy());
		}
	}
	
	/**
	 * Keep some spaces between critters.
	 * 
	 * @param movePoint of each critter
	 */
	public void buildMovePoint(int movePoint)
	{
		ArrayList<CritterBase> waveTemp = wave.getCritterWave();
		
		for(int i = 0; i < waveTemp.size(); i++){
			waveTemp.get(i).setMovePoint(i * movePoint);
			
		}
		
	}
	
	/**
	 * Get a wave of critters.
	 * 
	 * @return a wave of critters
	 */
	public ArrayList<CritterBase> getWave() 
	{
		return wave.getCritterWave();
	}
	
}
 