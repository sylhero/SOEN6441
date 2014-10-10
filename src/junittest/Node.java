package junittest;

import java.util.ArrayList;

public class Node {
	private int x;
	private int y;
	private int value;
	private boolean isVisited;
	private Node parentNode;
	public Node(int x, int y, int value){
		this.x = x;
		this.y = y;
		this.value = value;
		this.isVisited = false;
		this.parentNode = null;
	}
	public Node(int x, int y, int value, Node parent){
		this.x = x;
		this.y = y;
		this.value = value;
		this.isVisited = false;
		this.parentNode = parent;
		
	}
	
	public void setIsVisited(boolean result){
		this.isVisited = result;
	}
	public boolean getIsVisited(){
		return this.isVisited;
	}
	
	public int getX() {
		return x;
	}



	public void setX(int x) {
		this.x = x;
	}



	public int getY() {
		return y;
	}



	public void setY(int y) {
		this.y = y;
	}



	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Node getParentNode() {
		return parentNode;
	}
	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}
	
	
}
