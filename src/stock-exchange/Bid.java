public class Bid {
	final public String name;
	final public int bid;

	public Bid(String name, int bid) {
		this.name = name;
		this.bid = bid;
	}
	public int hashCode() {
		return 1 + 23*bid + 31*name.hashCode();
	}
	/* 
	 * Överskuggad equals för att vara kompatibel med hashcode.
	Jämför två bud och returnerar true om de är identiska, annars false.
	*/
	public boolean equals(Object obj){
		if (obj == null || !(obj instanceof Bid)) return false;

		Bid bid = (Bid) obj;
		if( this.bid == bid.bid && this.name.equals(bid.name))
			return true;
		else
			return false;
	}
	

	public String toString(){
		// Returns a description of the bid
		return this.name + " " + String.valueOf(this.bid);
	}
}
