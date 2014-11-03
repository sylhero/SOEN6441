package entity;
/**
 * This the interface of Tower.
 * It only contains one method now. 
 * <code>
 * void upgrade()
 * </code>
 * since every kind of tower can be upgrade as long as the coin is enough. 
 * 
 * @author Xunrong Xia
 *
 */
public interface TowerInterface {
	
	/**
	 * in the upgrade method, some attributes's values of a tower may be increased. 
	 */
	void upgrade();

}
