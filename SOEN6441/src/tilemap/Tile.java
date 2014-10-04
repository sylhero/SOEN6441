package tilemap;



import java.awt.Image;
/**
 * 
 * @author yulongsong
 * this is the tile class
 * 
 */

public class Tile {
	protected int tileType;
	protected Image tileImage;
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
	 * @param tileType
	 * @param image
	 * @param tileX
	 * @param tileY
	 * @param tileWidth
	 * @param tileHeight
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
	
	public Tile(int tileType){
		this.tileType = tileType;
		
		
	}
	
	/**
	 * get the tile type
	 * @return
	 */
	public int getTileType() {
		return tileType;
	}
	/**
	 * set the tile type
	 * @param tileType
	 */

	public void setTileType(int tileType) {
		this.tileType = tileType;	
		
	}

	/**
	 * get the image
	 * @return
	 */
	public Image getTileImage() {
		return tileImage;
	}

	/**
	 * set the tile image
	 * @param tileImage
	 */
	public void setTileImage(Image tileImage) {
		this.tileImage = tileImage;
	}

	/**
	 * get the tile x
	 * @return
	 */
	public int getTileX() {
		return tileX;
	}

	/**
	 * set the tile x
	 * @param tileX
	 */
	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	/**
	 * get tile y
	 * @return
	 */
	public int getTileY() {
		return tileY;
	}

	/**
	 * set the tile y
	 * @param tileY
	 */
	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	/**
	 * get the tile width
	 * @return
	 */
	public int getTileWidth() {
		return tileWidth;
	}

	/**
	 * set the tile width
	 * @param tileWidth
	 */
	public void setTileWidth(int tileWidth) {
		this.tileWidth = tileWidth;
	}

	/**
	 * get the tile height
	 * @return
	 */
	public int getTileHeight() {
		return tileHeight;
	}

	/**
	 * set the tile height
	 * @param tileHeight
	 */
	public void setTileHeight(int tileHeight) {
		this.tileHeight = tileHeight;
	}

}
