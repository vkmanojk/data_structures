import java.util.ArrayList;
import java.util.Scanner;

class heapfull<E> {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int size=0;
		Scanner scan=new Scanner(System.in);
		int inputs = Integer.parseInt(scan.nextLine());
		IHeap<Integer> heap1 = new PriorityQueue<Integer>(); 

		while (inputs > 0) {
			String operation = scan.nextLine();
			String[] temp = operation.split(" ");
			if (temp[0].equals("I")) {
				heap1.insert(Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
				heap1.printHeap();
			}
			if (temp[0].equals("R"))
			{
				Pair<Integer> temp1 = heap1.removeMin();
				if (temp1!=null)
				{
					System.out.println("Min"+temp1.getKey()+":"+temp1.getValue());
					heap1.printHeap();
				}
			
			}
			if (temp[0].equals("MK")) {
				System.out.println("Min Key"+heap1.minKey());

			}
			if (temp[0].equals("MV")) {
				System.out.println("Min Value"+heap1.minValue());
			}
			inputs--;
		}

	}

}

class Pair< E> {                          
	private int key;                                            
	private E value;                                     
	                                             
	public Pair(int key1, E val1){                     
		key = key1;                                  
		value = val1;                               
	}                                              
	                                                  
	public E getValue(){                                    
		return value;                                   
	}                                        
	                                                       
	public int getKey(){                                 
		return key;                                  
	}                                                    
}     
interface IHeap<E> {
	public boolean isEmpty();
	public int size();
	public void insert(int key, E value);
	public Pair<E> removeMin();
	public int minKey();
	public E minValue();
	public void printHeap();

}
class PriorityQueue<E> implements IHeap<E>{
	
	private ArrayList<Pair<E>> heap = new ArrayList(); // arraylist in inbuilt Java list, you can use array
	 
	
	public PriorityQueue() {
		
	}
	
	protected int parent(int j){
		if(j==0)
			return -2;
		return heap.get((Integer)(j-1)/2).getKey(); 
	}
	
	protected int left(int j) { 
		return heap.get((2*j)+1).getKey(); 
	} 
	
	protected int right(int j) {
		return heap.get((2*j)+2).getKey(); 
	} 
	
	protected boolean hasLeft(int j) { 
		
		return ((2*j)+1)<heap.size(); 
	}
	
	protected boolean hasRight(int j) { 
		return ((2*j)+2)<heap.size(); 
	}
	protected boolean hasParent(int j) { 
		return j>0; 
	}
	
	
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (heap.size()==0);
	}
	public int size() {
		// TODO Auto-generated method stub
		return heap.size();
	}

	public void insert(int key, E value) {
		// TODO Auto-generated method stub
		/*
		 * Add element using heap.add() function, it will add it to end of list
		 * Now do upheap
		 */
		Pair<E> temp=new Pair<E>(key, value);
		heap.add(heap.size(),temp);
		upheap(heap.size()-1);
	}
	
	public Pair<E> removeMin() {
		// TODO Auto-generated method stub
		/*
		 * First replace last element with root using swap function
		 * then call downheap
		 */
		if(heap.get(0).getValue()==null)
		    return null;
    	swap(heap.size()-1,0);
    	Pair<E> temp=heap.remove(heap.size()-1);
    	downheap(0);
    	return temp;
	}
	
	public int minKey() {
		// TODO Auto-generated method stub
		if (heap.size() <= 0)
			return -1;
		else
			return heap.get(0).getKey();
	}
	
	public E minValue() {
		// TODO Auto-generated method stub
		if (heap.size() <= 0)
			return null;
		else
			return heap.get(0).getValue();
	}
	
	protected void swap(int i, int j){
		
	Pair<E> temp=new Pair<E>(0,(E)(Integer)0);
	temp=heap.get(i);
	heap.set(i,heap.get(j));
	heap.set(j,temp);
	
	}
	
	//moves entry at j higher 
	protected void upheap(int j){
	     if(heap.size()==0||heap.size()==1)
 	        return;
	    while(hasParent(j)&&parent(j) >=(Integer) heap.get(j).getKey())
	        {
	            swap(((j-1)/2), j);
	            j= ((j-1)/2);
	        }	
	}
	
	protected void downheap(int j){
	     if(heap.size()==0||heap.size()==1)
 	        return;
		while(hasLeft(j)) {
            int smallerChildIndex = ((2*j)+1);
            
            if(hasRight(j)&&right(j)<left(j)) 
            {
                smallerChildIndex = ((2*j)+2);
            }
            
            if((Integer)heap.get(j).getKey() <(Integer) heap.get(smallerChildIndex).getKey()) 
            {
                break;
            }
            else 
            {
            	//System.out.println("Swap from downheap");
            	swap(j, smallerChildIndex);
            }
            j = smallerChildIndex;
        }
    
		
	}
	
	public void printHeap(){
		for (int i=0;i<heap.size(); i++){
			System.out.println("Key"+heap.get(i).getKey() + "Value"+ heap.get(i).getValue());
			
		}
	}

	
}
