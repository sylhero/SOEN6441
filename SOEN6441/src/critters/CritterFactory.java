package critters;

import java.awt.Point;
import java.util.LinkedList;

/**
 * Critter factory that creates different kinds of critters when given certain type of parameters by calling 
 * a static function.
 * 
 * @author Yichen LI
 * @version 1.1.1
 *
 */

public class CritterFactory {

	/**
	 * Constructor
	 */
	private CritterFactory() {}

	
	/**
	 * To return object according to the given parameters.
	 * 
	 * @param criType is the type of critter
	 * @param correctRoute is the correct route
	 * @param movePoint the space between critters
	 * @return certain type of critters
	 */
	public static CritterBase getCritter(String criType, LinkedList<Point> correctRoute, int movePoint)
	{
		if(criType.equalsIgnoreCase("Normal"))
			return new NormalCritter(correctRoute, movePoint);
		
		return null;
	}
	
}
