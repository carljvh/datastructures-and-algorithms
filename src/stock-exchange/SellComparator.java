import java.util.Comparator;

public class SellComparator implements Comparator<Bid>{
	@Override
	// Jämför två olika bud och returnerar -1 ifall den första parametern är mindre än den sista, 0 ifall buden är identiska, annars 1.
	// O(1) complexity.
	public int compare(Bid bid1, Bid bid2) {
		if(bid1.bid < bid2.bid) 
			return -1;
		else if (bid1.equals(bid2))
			return 0;
		else
			return 1;
	}
}