public class BinSearchIntSet implements IntSet{
	private int[] myArray ;
	private int counter;
	public BinSearchIntSet(){
		myArray = new int[]{};
		counter=0;
		
	}
	
	public void add(int element){//funkar
		if(myArray.length==0){
			myArray=new int[]{element};
			counter++;
		}
		if(counter==0){
			myArray[0]=element;
			counter ++;
		}
		if(contains(element)==false){//Kanske this.contains?
			if(myArray.length>counter){
				myArray[counter]=element;
				binaryAdd(elementIndex(element), element);
				counter ++;
			}
			else{
				int[] addArray=new int[myArray.length*2];
				for(int i=0;i<myArray.length;i++)
					addArray[i]=myArray[i];
				myArray=addArray;
				binaryAdd(elementIndex(element), element);
				counter ++;
				
			}
		}

	}
	
	
	public void remove(int element){//funkar
		if(contains(element)==true){
			int[] newArray=new int[myArray.length-1];
			for(int i=indexOf(element); i<counter-1;i++){
				myArray[i]=myArray[i+1];
			}
				
			counter --;
		}
		
	}
	public int indexOf(int element){//funkar
		int ind=-1;
		for (int i = 0; i < counter; i++) {
            if (myArray[i] == element) {
                ind = i;
            }
        }
        return ind;
	}
	
	 public void binaryAdd(int elementIndex,int element) { 
      int[] tmp= new int[myArray.length];
	  for(int i=0;i<elementIndex;i++){
		  tmp[i]=myArray[i];
	  }
	  tmp[elementIndex]=element;
	  for(int i=elementIndex;i<counter;i++){
		  tmp[i+1]=myArray[i];
	  }
	   myArray=tmp;
	 }
	
	public int elementIndex(int element) { 
	   	if (myArray[counter-1]<element) {
    		return counter;
    	}
    	if(myArray[0] > element) {
    		return 0;
    	}
    	for(int i = 1; i < counter; i++){
    		if(myArray[i] > element && myArray[i-1]< element) {
    			return i;
    		}
    	}
    	return -1;
	
	}
	public boolean contains(int element){//funkar
		int hi=counter-1, lo=0, mid=(lo+hi)/2;
		/*System.out.println("Array:");
		
		for(int i=0;i<myArray.length;i++){
			System.out.println(myArray[i]);
		}
		*/
		
		while(hi>=lo){
			mid=(hi+lo)/2;

			if(myArray[mid]<element){
			lo=mid+1;
			}
			else if(myArray[mid]>element){
				hi=mid-1;
			}
			else{
				return true;
			}
		}
			return false;
	}
}