package gameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import entity.MonsterTest;
import tilemap.Tile;
import tilemap.TileMap;
/*
 * not implement yet just ignore
 */
public class PlayState extends GameState{
	private TileMap  tileMap;

	private Tile[][] map;
	
	private String mapPath;
	//draw boolean
	private boolean isArrowTower;
	private boolean isArrowTowerEntered;
	private boolean isDrawTowerDetail;

	//monster

	private MonsterTest[] monster;

	//pause

	private boolean isPaused;

	//control the speed

	private int speed;


	public PlayState(GameStateManager gsm){

		this.gsm = gsm;
		
		isPaused = false;

		tileMap = new TileMap();

	}

	//initialize the monsters


	private void initializeMonsters(){

	for(int i = 0; i<10; i++){

	monster[i] = new MonsterTest(tileMap);

	}

	}



	@Override

	public void mouseDragged(MouseEvent e) {

	// TODO Auto-generated method stub


	}

	//detect mouse enter the arrowTower icon zone
	private void detectArrowTowerMoved(MouseEvent e){
		int x = e.getX();

		int y = e.getY();
		
		if(x >= 1 && x<=41 && y >= 5 && y <= 45){
			this.isArrowTowerEntered = true;
		}else{
			this.isArrowTowerEntered = false;
		}
		
	}

	@Override

	public void mouseMoved(MouseEvent e) {
		detectArrowTowerMoved(e);    

	}



	@Override

	public void mouseClicked(MouseEvent e) {

	// TODO Auto-generated method stub


	}
	//detect arrowTower
	private void arrowTowerPressed(MouseEvent e){
		int x = e.getX();

		int y = e.getY();
		
		if(x >= 1 && x<=41 && y >= 5 && y <= 45){
			this.isArrowTower = true;
		}else{
			this.isArrowTower = false;
		}
		
		
		
	}

	//detect pause
	private void pausePressed(MouseEvent e){

		int x = e.getX();

		int y = e.getY();

		System.out.println(x);

		System.out.println(y);

		if(x >= 0 && x <= 30 && y >=0 && y<=30 ){

		this.pause(true);

		}

		if(x > 30 && x <= 60 && y >=0 && y<=30 ){

		this.pause(false);

		}


		if(isPaused == false){

		tileMap.mousePressed(e);

		}

	}
	@Override

	public void mousePressed(MouseEvent e) {
		
		arrowTowerPressed(e);
	

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void pause(boolean result) {
		
		this.isPaused = result;
	}
	
	@Override
	
	public boolean pause(){
		return this.isPaused;
	}

	

	@Override
	public void init(String path) {
		//initialize the map based on the previous map selection
		this.mapPath = path;
		this.map = tileMap.loadMap(path);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	private void drawArrowTower(Graphics2D g){
		g.setColor(Color.red);
		g.drawRect(1, 5, 41, 43);
	}
	private void drawArrowTowerDescription(Graphics2D g){
		g.setColor(Color.WHITE);
		g.fillRect(100, 0, 400, 100);
		g.setColor(Color.BLACK);
		String description = "this is arrow tower";
		g.drawString(description, 105, 20);
	}

	@Override
	public void draw(Graphics2D g) {
		//first draw map
		tileMap.draw(g);
		if(isArrowTower){
			drawArrowTower(g);
		}
		if(isArrowTowerEntered){
			drawArrowTowerDescription(g);
		}
		
	}

	@Override
	public void resume() {
	}

}
