package junittest;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import tilemap.Tile;
import tilemap.TileMap;

public class TestAlgo {
	private int mapRow;
	private int mapCol;
	private Node startPoint;
	private Node endPoint;
	LinkedList<Node> BFSPath;
	private Node[][] nodeMap;
	private Tile[][] map;
	private static final int ENTRANCE = TileMap.ENTRANCE;
	private static final int PAVEMENT = TileMap.PAVEMENT;
	private static final int EXIT     = TileMap.EXIT;
	public TestAlgo(TileMap tileMap){
		this.mapRow  = tileMap.getMapRow();
		this.mapCol  = tileMap.getMapCol();
		BFSPath      = new LinkedList<Node>();
		nodeMap      = new Node[mapRow][mapCol];
		map          = tileMap.getMap();
		
		tileToNode(tileMap.getMap());
	}
	//convert Tile[][] to Node[][]
	private void tileToNode(Tile[][] map){
		for(int i =0; i < this.mapRow; i++){
			for (int j = 0; j<this.mapCol; j++){
				nodeMap[i][j] = new Node(map[i][j].getTileX(),
						map[i][j].getTileY(),map[i][j].getTileType());
				System.out.printf("[%d][%d]\n",map[i][j].getTileX(),map[i][j].getTileY());
				if(map[i][j].getTileType()== ENTRANCE){
					startPoint = nodeMap[i][j];
					
				}else if(map[i][j].getTileType() == EXIT){
					endPoint = nodeMap[i][j];
				}
			}
		}
		
	}
	
	public Node BFS(){
		Node result  = null;
		Node temp    = null;
		
		BFSPath.add(startPoint);
		while(!BFSPath.isEmpty()){
			temp = BFSPath.pollFirst();
			System.out.printf("[%d][%d]\n",temp.getX(),temp.getY());
			if(temp.getValue() == EXIT){
				result = temp;
				break;
			}
			//check cells within the border
			boolean withinBorder = checkBorder(temp);
			if(withinBorder){
				checkWest(temp);
				checkEast(temp);
				checkNorth(temp);
				checkSouth(temp);
				temp.setIsVisited(true);
			// check the left top corner
			}else{
				if(temp.getX() == 0 && temp.getY() == 0){
			
				checkEast(temp);
				checkSouth(temp);
				temp.setIsVisited(true);
				}
			// check the left bottom corner
				else if(temp.getX() == mapRow -1 && temp.getY() == 0){
				checkEast(temp);
				checkNorth(temp);
				temp.setIsVisited(true);
			// check the right top corner 
			}
				else if(temp.getX() == 0 && temp.getY() == mapCol - 1){
				checkWest(temp);
				checkSouth(temp);
				temp.setIsVisited(true);
				
			}
			//check the right bottom corner
				else if(temp.getX() == mapRow -1 && temp.getY() == mapCol -1){
				checkWest(temp);
				checkNorth(temp);
				temp.setIsVisited(true);
				
			}
			//check the top border
				else if(temp.getX() == 0 && temp.getY() > 0){
				checkWest(temp);
				checkEast(temp);
				checkSouth(temp);
				temp.setIsVisited(true);
			}
			//check the bottom border
				else if(temp.getX() == mapRow -1 && temp.getY() > 0){
				checkWest(temp);
				checkEast(temp);
				checkNorth(temp);
				temp.setIsVisited(true);
			}
			//check the left border
				else if(temp.getX() >0 && temp.getY() == 0){
				checkEast(temp);
				checkSouth(temp);
				checkNorth(temp);
				temp.setIsVisited(true);
			}
			//check the right border
				else if(temp.getX() > 0 && temp.getY() == mapCol - 1){
				checkWest(temp);
				checkSouth(temp);
				checkNorth(temp);
				temp.setIsVisited(true);
			}
		
			}
		}
		
		
		
		return result;
			
		
	}
	public void printResultPath(){
		Node root = BFS();
		//printResultPath(root);
		
	}
	public void printResultPath(Node node){
		if(node.getParentNode() == null){
			System.out.printf("[%d][%d]",node.getX(),node.getY());
		}else{
			printResultPath(node.getParentNode());
		}
	}
	
	private boolean checkBorder(Node node){
		//-1 because of the index
		return node.getX() > 0 && node.getX() < mapRow-1 && 
				node.getY() > 0 && node.getY() <mapCol-1;
	}
	
	private void checkWest(Node node){
		int x        = node.getX();
		int y        = node.getY();
		if(nodeMap[x][y-1].getValue() == PAVEMENT &&
				nodeMap[x][y-1].getIsVisited()==false){
				nodeMap[x][y-1].setParentNode(node);
				BFSPath.add(nodeMap[x+1][y]);
		}else if(nodeMap[x][y-1].getValue() == EXIT &&
				nodeMap[x][y-1].getIsVisited()==false){
				nodeMap[x][y-1].setParentNode(node);
				BFSPath.add(nodeMap[x][y-1]);
			}
		
		
	}
	private void checkEast(Node node){
		int x        = node.getX();
		int y        = node.getY();
		if(nodeMap[x][y+1].getValue() == PAVEMENT &&
				nodeMap[x][y+1].getIsVisited()==false){
				nodeMap[x][y+1].setParentNode(node);
				BFSPath.add(nodeMap[x][y+1]);
		}else if(nodeMap[x][y+1].getValue() == EXIT &&
				nodeMap[x][y+1].getIsVisited()==false){
				nodeMap[x][y+1].setParentNode(node);
				BFSPath.add(nodeMap[x][y+1]);
			}
		
	}
	private void checkNorth(Node node){
		int x        = node.getX();
		int y        = node.getY();
		if(nodeMap[x-1][y].getValue() == PAVEMENT &&
				nodeMap[x-1][y].getIsVisited()==false){
				nodeMap[x-1][y].setParentNode(node);
				BFSPath.add(nodeMap[x-1][y]);
		}else if(nodeMap[x-1][y].getValue() == EXIT &&
				nodeMap[x-1][y].getIsVisited()==false){
				nodeMap[x-1][y].setParentNode(node);
				BFSPath.add(nodeMap[x-1][y]);
			}
		}
		
		
	
	private void checkSouth(Node node){
		int x        = node.getX();
		int y        = node.getY();
		
		if(nodeMap[x+1][y].getValue() == PAVEMENT &&
				nodeMap[x+1][y].getIsVisited()==false){
				nodeMap[x+1][y].setParentNode(node);
				BFSPath.add(nodeMap[x+1][y]);
		}else if(nodeMap[x+1][y].getValue() == EXIT &&
				nodeMap[x+1][y].getIsVisited()==false){
				nodeMap[x+1][y].setParentNode(node);
				BFSPath.add(nodeMap[x+1][y]);
			}
		
		
		
	}
	
    
	
	public static void main(String[] Args){
		
		
	}

}
