import java.util.*;
import java.util.PriorityQueue;

public class Graph {
	public DistanceComparator comparator;//Declaration of used structures
	public PriorityQueue<Node> pQueue;
	public HashMap<String, Integer> visitMap;
	public  HashMap<String, Node> graphMap;	
	public Node currentNode;
	public Path rPath;

	public Graph() {
		graphMap = new HashMap<String, Node>();//Hashmap to store name of node and corresponding node
	}

	public void addVertex(String label) {
		Node node = new Node(label);
		graphMap.put(label, node);
	}

	public void addEdge(String node1, String node2, int dist) {
		graphMap.get(node1).neighbours.put(node2, dist);		// Distance |A-B|=|B-A| -> graphMap is updated twice
		graphMap.get(node2).neighbours.put(node1, dist);
	}

	public static class Path {
		public int totalDist;
		public List<String> vertices;

		public Path(int totalDist, List<String> vertices) {
			this.totalDist = totalDist;
			this.vertices = vertices;
		}

		@Override
		public String toString() {
			return "totalDist: " + totalDist + ", vertices: " + vertices.toString();
		}
	}

	public Path shortestPath(String start, String dest) {
		comparator = new DistanceComparator();			//Comparator from separate file
		pQueue = new PriorityQueue<Node>(comparator); //Distance based priorityqueue
		visitMap = new HashMap<String, Integer>();	//Keep track of visited nodes via map
		currentNode = graphMap.get(start);			//Get first node based on start input
		currentNode.distance = 0;					//Set starting distance to 0     			
		currentNode.prevNode = "startNode";			//Set start to execute while loop for final path
		Map<String, Integer> neighbourMap;			//Map to keep track of neighbours of node
		pQueue.add(currentNode);
		
		while(pQueue.size()>0) {			
			currentNode = pQueue.poll();				//examine currentNode and remove from priorityqueue						
			
			if(visitMap.containsKey(currentNode.getName())) {				// Skip to next node if currentNode is visited
			    continue;
			}
			graphMap.put(currentNode.getName(), currentNode);				//Store name and corresponding node in graphmap
			visitMap.put(currentNode.getName(), 0);							//Declare currentNode visited	

			
			if(currentNode.getName().equals(dest)) {					//break loop if we have reached target, commence distance calculation and path declaration
			    List<String> resultPath = new ArrayList<String>();
				resultPath.add(currentNode.getName());
				int totalDist = currentNode.getDistance();
				
				while(graphMap.get(currentNode.getName()).getPrev().equals("startNode")==false) {			// Loops until startnode is reached
					resultPath.add(graphMap.get(currentNode.getName()).getPrev());				// Add prev node to path
					currentNode = graphMap.get(graphMap.get(currentNode.getName()).getPrev());	// Set prev node to current node to loop to start
				}
				String tmp;
				int size=resultPath.size();
					for(int i=0;i<size/2;i++){//Reverse arraylist of strings to get A -> B representation instead of B -> A
						tmp=resultPath.get(i);
						resultPath.set(i,resultPath.get(size-i-1));
						resultPath.set(size-i-1,tmp);
					}
				rPath = new Path(totalDist, resultPath);
				return rPath;
			}
			neighbourMap = currentNode.neighbours;												//Create and add new node to priorityqueue
			for (Map.Entry<String, Integer> entry: neighbourMap.entrySet()) {
				String key = entry.getKey();
				int value = entry.getValue();
				if(visitMap.containsKey(key)==false) {
				    Node newNode = new Node(key, value + currentNode.getDistance(), currentNode.getName(), graphMap.get(key).neighbours);
				    pQueue.add(newNode);
				}
			
			}
		}
		return null;
	} 
}
