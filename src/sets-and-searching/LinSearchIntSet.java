public class LinSearchIntSet implements IntSet{
	int[] myArray ;
	int counter;
	public LinSearchIntSet(){
		myArray = new int[0];
		counter=0;
		
	}
	
	public void add(int element){
		if(myArray.length==0){
			myArray=new int[]{element};
			counter++;
		}
		if(counter==0){
			myArray[0]=element;
			counter ++;
		}
		if(contains(element)==false){
			if(myArray.length>counter){
				myArray[counter]=element;
				counter ++;
			}
			
			else{
				int[] addArray=new int[myArray.length*2];
				for(int i=0;i<myArray.length;i++)
					addArray[i]=myArray[i];
				myArray=addArray;
				myArray[counter]=element;
				counter ++;
				
			}

	}
	}
	
	public void remove(int element){
		if(contains(element)==true){
			int[] newArray=new int[myArray.length-1];
			for(int i=indexOf(element); i<counter-1;i++){
				myArray[i]=myArray[i+1];
			}
				
			counter --;
		}
		
	}
	public int indexOf(int element){
		int ind=-1;
		for(int i=0;i<counter;i++){
			if(myArray[i]==element)
				ind=i;
		}
		return ind;
	}
	public boolean contains(int element){
		if(indexOf(element)!=-1)
			return true;
		else
			return false;
	}
	
}