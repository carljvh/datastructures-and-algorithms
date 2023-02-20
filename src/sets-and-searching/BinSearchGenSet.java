import java.util.ArrayList;
public class BinSearchGenSet<E extends Comparable<? super E>> implements GenSet<E>{
private ArrayList<E> myArray = new ArrayList<E>(){} ;
	private int counter;
	public BinSearchGenSet(){
		ArrayList<E> myArray = new ArrayList<E>(){};
		counter=0;
		
	}
	@Override
	public void add(E element){
		if(!contains(element)){
			int i = myArray.size()-1;
			while(i >= 0 && element.compareTo(myArray.get(i)) <=0){
				i--;
			}
			myArray.add(i+1,element);
		}
	}

	
	@Override
	public void remove(E element){
		if(contains(element)==true){
				myArray.remove(element);
			counter --;
		}
		
	}
	public int indexOf(E element){
		int ind=-1;
		for (int i = 0; i < counter; i++) {
            if (myArray.get(i).compareTo(element) == 0) {
                ind = i;
            }
        }
        return ind;
	}
	
	 public void binaryAdd(int elementIndex,E element) { 
	for(int i = myArray.size(); i > elementIndex+1; i--) {
    		myArray.set(i+1,myArray.get(i));
    	}
    	myArray.set(elementIndex, element);
    	counter++;
	 }
	public int elementIndex(E element) { 
	   	if (myArray.get(counter-1).compareTo(element)<0) {
    		return counter;
    	}
    	if(myArray.get(0).compareTo(element) > 0) {
    		return 0;
    	}
    	for(int i = 1; i < counter; i++){
    		if(myArray.get(i).compareTo(element) > 0 && myArray.get(i-1).compareTo(element)< 0) {
    			return i;
    		}
    	}
    	return -1;
	
	}
	public boolean contains(E element){
		int hi=myArray.size()-1, lo=0, mid=(lo+hi)/2;
		while(hi>=lo){
			mid=(hi+lo)/2;

			if(myArray.get(mid).compareTo(element)<0){
			lo=mid+1;
			}
			else if(myArray.get(mid).compareTo(element)>0){
				hi=mid-1;
			}
			else{
				return true;
			}
		}
			return false;
	}
}