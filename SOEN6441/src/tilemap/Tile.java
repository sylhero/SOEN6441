package tilemap;



import java.awt.Image;


public class Tile {
	protected int tileType;
	protected Image tileImage;
	protected int tileX;
	protected int tileY;
	protected int tileWidth;
	protected int tileHeight;
	
	public Tile(){
		
	}
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
	

	public int getTileType() {
		return tileType;
	}


	public void setTileType(int tileType) {
		this.tileType = tileType;	
		
	}


	public Image getTileImage() {
		return tileImage;
	}


	public void setTileImage(Image tileImage) {
		this.tileImage = tileImage;
	}


	public int getTileX() {
		return tileX;
	}


	public void setTileX(int tileX) {
		this.tileX = tileX;
	}


	public int getTileY() {
		return tileY;
	}


	public void setTileY(int tileY) {
		this.tileY = tileY;
	}


	public int getTileWidth() {
		return tileWidth;
	}


	public void setTileWidth(int tileWidth) {
		this.tileWidth = tileWidth;
	}


	public int getTileHeight() {
		return tileHeight;
	}


	public void setTileHeight(int tileHeight) {
		this.tileHeight = tileHeight;
	}

}
