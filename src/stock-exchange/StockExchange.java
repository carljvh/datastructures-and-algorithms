import java.io.*;
import java.util.*;

public class StockExchange {
	public static String pureMain(String[] commands) {
		// Declaration of two priority queues
		Comparator<Bid> sellComparator = new SellComparator();
		Comparator<Bid> buyComparator = new BuyComparator();
		PriorityQueue<Bid> sell_pq = new PriorityQueue<Bid>(sellComparator);//minheap prio queue 
		PriorityQueue<Bid> buy_pq = new PriorityQueue<Bid>(buyComparator);//MaxHeap prio queue

		StringBuilder sb = new StringBuilder();//gör en string
		for(int line_no=0;line_no<commands.length;line_no++){//input kommer vara Ada K 70, Bengt S 71, Bengt NS 70 71, Ada NK 71 70
			String line = commands[line_no];//Ta element line_no (ökande) av command string
			if( line.equals("") )continue;// om vi har mellanslag skippar vi mellanslag sätter line_no+1

			String[] parts = line.split("\\s+");//dela upp input "Ada K 70" till parts[0]=Ada, parts[1]=K, parts[2]="70"
			if( parts.length != 3 && parts.length != 4)//om vi får konstig input som ej är 3 eller 4 gör vi exception
				throw new RuntimeException("line " + line_no + ": " + parts.length + " words");
			String name = parts[0];
			if( name.charAt(0) == '\0' )
				throw new RuntimeException("line " + line_no + ": invalid name");
			String action = parts[1];//Köp eller sälj
			int price;
			try {
				price = Integer.parseInt(parts[2]);//parse från string till int 
			} catch(NumberFormatException e){
				throw new RuntimeException(
						"line " + line_no + ": invalid price");
			}
			if( action.equals("K") ) {
				Bid kingBid= new Bid(name, price);//gör nytt bid-objekt, stoppa i buyHeap
				buy_pq.add(kingBid);
			} else if( action.equals("S") ) {
				Bid kingBid= new Bid(name, price);//gör nytt bid-objekt, stoppa i sellHeap
				sell_pq.add(kingBid);
			} else if( action.equals("NK") ){
				int newPrice= Integer.parseInt(parts[3]);
				Bid mapBid = new Bid(parts[0],price);//skapa objekt identiskt med det vi letar efter i hashmap
				int mapIndex = buy_pq.mapIndex(mapBid);
					Bid kingBid = new Bid(parts[0], newPrice);//Skapa nytt objekt 
					buy_pq.pq_replace(mapIndex,kingBid);//byter ut det objekt vi har matchat med med det nya objekt vi precis har skapat
			}			else if( action.equals("NS") ){
				int newPrice= Integer.parseInt(parts[3]);
				Bid mapBid = new Bid(parts[0],price);//skapa objekt identiskt med det vi letar efter i hashmap
				int mapIndex = sell_pq.mapIndex(mapBid);
					Bid kingBid = new Bid(parts[0], newPrice);//Skapa nytt objekt 
					sell_pq.pq_replace(mapIndex,kingBid);//byter ut det objekt vi har matchat med med det nya objekt vi precis har skapat
			} else {
				throw new RuntimeException(
						"line " + line_no + ": invalid action");
			}

			if( sell_pq.size() == 0 || buy_pq.size() == 0 )continue;//if the priority queues are empty, continue to the next index
			else if(sell_pq.get(0).bid<=buy_pq.get(0).bid){	//if the bid request to sell is lower than that to buy, print transaction and remove the matched bids from their respective queues
				System.out.println(buy_pq.get(0).name + "köper från " + sell_pq.get(0).name + "för " + Integer.toString(buy_pq.get(0).bid) + "kr");
				sell_pq.deleteMinimum();
				buy_pq.deleteMinimum();
			}

		}
		//adds the greatest bid in the sell priority queue to the sellers in the order book
		sb.append("Order book:\n");
		sb.append("Sellers: ");
		while(sell_pq.size()>0){
			sb.append(sell_pq.get(0).toString() + ", ");
			sell_pq.deleteMinimum();
		}
		//adds the smallest bid in the buy priority queue to the buyers in the order book
		sb.append("\n");
		sb.append("Buyers: ");
			while(buy_pq.size()>0){
			sb.append(buy_pq.get(0).toString() + ", ");
			buy_pq.deleteMinimum();
		}
		return sb.toString(); //returns the updated order book
	}

	public static void main(String[] args) throws IOException {
		final BufferedReader actions;
		if( args.length != 1 ){
			actions = new BufferedReader(new InputStreamReader(System.in));
		} else {
			actions = new BufferedReader(new FileReader(args[0]));
		}

		List<String> lines = new LinkedList<String>();
		while(true){
			String line = actions.readLine();
			if( line == null)break;
			lines.add(line);
		}
		actions.close();

		System.out.println(pureMain(lines.toArray(new String[lines.size()])));
	}
}