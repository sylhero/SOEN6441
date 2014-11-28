package tilemap;



import java.awt.Image;
import java.io.Serializable;
/**
 * 
 * @author yulongsong
 * this is the tile class
 * 
 */

public class Tile implements Serializable{
	
	private static final long serialVersionUID = 3049009049133952587L;
	
	protected int tileType;
	protected transient Image tileImage;
	protected int tileX;
	protected int tileY;
	protected int tileWidth;
	protected int tileHeight;
	
	/**
	 * default constructor for inheritance purpose
	 */
	public Tile(){
		
	}
	/**
	 * constructor
	 * @param tileType This is the tile type
	 * @param image This is the image of the tile.
	 * @param tileX This is the x-coordinate of tile.
	 * @param tileY This is the y-coordinate of tile.
	 * @param tileWidth This is the width of the tile.
	 * @param tileHeight This is the height of the tile.
	 */
	public Tile(int tileType,Image image, int tileX, int tileY, 
			int tileWidth, int tileHeight){
		this.setTileImage(image);
		this.setTileX(tileX);
		this.setTileY(tileY);
		this.setTileWidth(tileWidth);
		this.setTileHeight(tileHeight);
		this.tileType = tileType;
	}
	
	/**
	 * This is the constructor.
	 * @param tileType This is the tile type.
	 */
	public Tile(int tileType){
		this.tileType = tileType;
		
		
	}
	
	/**
	 * get the tile type
	 * @return The tile's type.
	 */
	public int getTileType() {
		return tileType;
	}
	/**
	 * set the tile type
	 * @param tileType The tile's type
	 */

	public void setTileType(int tileType) {
		this.tileType = tileType;	
		
	}

	/**
	 * get the image
	 * @return The tile's image.
	 */
	public Image getTileImage() {
		return tileImage;
	}

	/**
	 * set the tile image
	 * @param tileImage The tile's image.
	 */
	public void setTileImage(Image tileImage) {
		this.tileImage = tileImage;
	}

	/**
	 * get the tile x
	 * @return The x-coordinate of tile.
	 */
	public int getTileX() {
		return tileX;
	}

	/**
	 * set the tile x
	 * @param tileX The x-coordinate of tile.
	 */
	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	/**
	 * get tile y
	 * @return The y-coordinate of tile.
	 */
	public int getTileY() {
		return tileY;
	}

	/**
	 * set the tile y
	 * @param tileY The y-coordinate of tile.
	 */
	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	/**
	 * get the tile width
	 * @return The tile's width.
	 */
	public int getTileWidth() {
		return tileWidth;
	}

	/**
	 * set the tile width
	 * @param tileWidth The tile's width.
	 */
	public void setTileWidth(int tileWidth) {
		this.tileWidth = tileWidth;
	}

	/**
	 * get the tile height
	 * @return The tile's height.
	 */
	public int getTileHeight() {
		return tileHeight;
	}

	/**
	 * set the tile height
	 * @param tileHeight The tile's height.
	 */
	public void setTileHeight(int tileHeight) {
		this.tileHeight = tileHeight;
	}

}
