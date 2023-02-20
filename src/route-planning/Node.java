import java.util.*;

public class Node{
	HashMap<String, Integer> neighbours;
	String prevNode, name;
	int distance;
	
	public String getName(){//Getters
		return name;
	}
	public String getPrev(){
		return prevNode;
	}
	public int getDistance(){
		return distance;
	}
	
	public boolean equals(Node node) {//boolean to see if objects are equal
		if(this == node) {
			return true;
		} else if(node == null) {
			return false;
		}
		return this.name.equals(node.getName()) && this.prevNode.equals(node.getPrev()) && this.distance == node.distance;

	}
	//Various constructors for different parts of the code, i.e. addVertex, addEdge, map operations 
	public Node(String name) {
		this.name = name;
		neighbours =  new HashMap<String, Integer>();
	}

	public Node(String name, String prev) {
		this.name = name;		
		neighbours =  new HashMap<String, Integer>();
		prevNode = prev;
	}
	public Node(String name, int dist, String prev) {
		this.name = name;
		neighbours =  new HashMap<String, Integer>();
		distance = dist;
		prevNode = prev;
	}
    public Node(String name, int dist, String prev, HashMap<String, Integer> newMap) {
		this.name = name;
		neighbours = newMap;
		distance = dist;
		prevNode = prev;
	}
}
