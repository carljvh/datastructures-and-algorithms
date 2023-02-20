import java.util.Comparator;

public class DistanceComparator implements Comparator<Node> {//custom comparator for distance between nodes
	public int compare(Node node1, Node node2) {
		if (node1.equals(node2)) {
			return 0;
		}
		else if(node1.distance < node2.distance) {
			return -1;
		}
		else {
			return 1;
		}

	}
}
