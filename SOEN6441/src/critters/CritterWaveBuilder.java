package critters;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A builder class to corporate different types critters into a wave.
 * 
 * @author Yichen LI
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

	public void buildRoute(LinkedList<Point> route)
	{
		
		for (CritterBase critter : wave.getCritterWave())
		{	
		
			critter.setCorrectRouteCopy(route); // set route of each critter
		
		}
	}

	
	public void buildStartPoint()
	{
		for (CritterBase critter : wave.getCritterWave())
		{
			critter.setStartPoint(critter.correctRouteCopy);
		}
	}
	
	public void buildXAndY()
	{
		for (CritterBase critter : wave.getCritterWave())
		{
			critter.setX();
			critter.setY();
		}
	}
	
	public void buildNextPoint()
	{
		for (CritterBase critter : wave.getCritterWave())
		{
			critter.setNextPoint(critter.getCorrectRouteCopy());
		}
	}
	
	public void buildMovePoint(int movePoint)
	{
		int point = movePoint;
		for (CritterBase critter : wave.getCritterWave())
		{
			
			critter.setMovePoint( point); // set movePoint
			point = point + 20;
			
		}
		
	}
	
	public ArrayList<CritterBase> getWave() 
	{
		return wave.getCritterWave();
	}
	
}
 