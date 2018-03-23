/*
 * CSC172 lab12
 * Xiaoyu Zhang
 * xzhang81@u.rochester.edu
 */

import java.util.Random;
public class lab12 {
	public static void main(String[] args)
	{
		myHeap<Integer> heap = new myHeap<Integer>(4);
		Integer[] myNum = {13,100,68,16,32,31,21,24,65,1,19};
		
		myHeap<Integer> heap2 = new myHeap<Integer>();
// testing default heap
		Integer[] myNum2 = {31,100,86,16,32,31,21,24,65,1};
		for(int i= 0; i<myNum2.length;i++)
		{
			try {
				heap2.insert(myNum2[i]);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System .out.println("The size of default heap is  " + heap2.size());
		System.out.println();	
		
// testing when pass capacity
		System .out.println("The capacity of heap start with 4, I will insert "+myNum.length+" numbers. So heap have to expand 2 times ");
		System.out.println("The size of heap is "+heap.size()+ ", heap is empty? "+heap.isEmpty());
		for(int i= 0; i<myNum.length;i++)
		{
			try {
				heap.insert(myNum[i]);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("The number that inserted are : ");
		heap.printHeap();
		System.out.println("The size of heap is "+heap.size()+ ", heap is empty?   "+heap.isEmpty());
		System.out.println();
		System.out.println("delete minimum " + heap.deleteMin());
		heap.printHeap();
		System.out.println("delete minimum " + heap.deleteMin());

		heap.printHeap();
		
		
//heapify a ary		
		System.out.println("\nTesting heapify array with array of random number:");	
		Integer[] myRandNum = new Integer[14];
		Random ra = new Random();
		System.out.print("   ");
		for(int i = 0 ; i< 14; i++)
		{
			myRandNum[i] = ra.nextInt(100)+1;  	   //generate random number from 1 to 100
			System.out.print(myRandNum[i]+ " ");
		}
		System.out.println();
	
		myHeap<Integer> heap3 = new myHeap<Integer>(myRandNum);
		System.out.println("The re-arranging result is: ");
		heap3.printHeap();
		
		return;
	}
}

interface Heap<T extends Comparable<T> > 
{
	public void insert(T item) throws Exception;
	public boolean isEmpty();
	public int size();
	public T deleteMin();
	public void printHeap();
} 

class myHeap<T extends Comparable<T> > implements Heap<T> {

	private int currentSize;      // Number of elements in heap
    private T [] HeapArray; // The heap array
    private static final int DefaultCapacity = 10;
    
	myHeap(int capacity)
	{
		currentSize = 0;
		this.HeapArray = (T[])new Comparable[capacity];
	}
	
	myHeap()
	{
		this(DefaultCapacity);
	}
	myHeap(Comparable[] ary)
	{
		this(ary.length);
		//this.HeapArray = (T[])new Comparable[ary.length];
		this.currentSize = ary.length;
		
		//for(int i = 0; i<)
		this.HeapArray = (T[]) ary;
		this.heapify();
	}
	
	@Override
	public void insert(T item) throws Exception 
	{
		  if( this.currentSize == this. HeapArray.length - 1 )
			  expandHeap();                      // Create new array with double capacity
		  
		  HeapArray[this.size()] = item;         // Add the element at the rightmost leaflet
		  bubbleUp(this.size());       			 // Move it to its proper place
		  currentSize++;	
	}
	private void expandHeap()
	{        		
		T[] temp =(T[])new Comparable[this. HeapArray.length *2 ];
		  for(int i = 0; i <HeapArray.length; i++)
		  {  
			  temp[i] = this.HeapArray[i];          // Copy all the references
		  }
		  this.HeapArray = temp;                    // Update instance backing array
	}

	private void bubbleUp(int child)
	{
		  int parent = (child - 1) / 2;            // Compute the parent index
		  if( HeapArray[child].compareTo(HeapArray[parent]) < 0 ) // If child < parent
		  {  
			  T temp = HeapArray[parent];             // Store the parent
			  HeapArray[parent] = HeapArray[child];        // Replace parent element with child
			  HeapArray[child] = temp;                // Swap them
		      bubbleUp(parent);               // parent is now the old child
		  } 
	}

	@Override
	public boolean isEmpty() 
	{
		return currentSize == 0;
	}

	@Override
	public int size() 
	{
		return currentSize;
	}

	@Override
	public T deleteMin() 
	{
        if( isEmpty( ) )
            return null;

        T minItem = HeapArray[0];
        HeapArray[ 0 ] = HeapArray[ this.currentSize -1 ];
        HeapArray[this.currentSize - 1] = null;
        bubbledown( 0 );
        currentSize--;
        return minItem;
	}
    private void bubbledown( int parent )
    {
    	int left = (2 * parent) + 1;       // Compute Left/Right child indices
    	int right = (2 * parent) + 2;
    	if(left >= currentSize - 1 )               // Base case: end of the heap
    		return;

    // If right child is null or left child >= right child, compare left child to parent
    	if(HeapArray[right] == null || HeapArray[left].compareTo(HeapArray[right]) <= 0)
    	{
    			if(HeapArray[left].compareTo(HeapArray[parent]) < 0)
    			{
    				 T temp = HeapArray[parent];             // Store the parent
    				  HeapArray[parent] = HeapArray[left];        // Replace parent element with child
    				  HeapArray[left] = temp;                // Replace child with the parent
    				//swap(parent, left);            // If the left child is > parent swap
    				bubbledown(left);             // Left index is now the parent index
    			}
    	}                                  // If the right child > parent
    	else if(HeapArray[right].compareTo(HeapArray[parent]) < 0)
    	{
    		 T temp = HeapArray[parent];             // Store the parent
   		    HeapArray[parent] = HeapArray[right];        // Replace parent element with child
   		     HeapArray[right] = temp;                // Replace child with the parent            // Swap the parent and right child
    		bubbledown(right);              // Right index is now the parent index
    	}  

    }
	@Override
	public void printHeap() 
	{
		System.out.print("   ");
		for(int i = 0; i< currentSize ;i++)
			System.out.print(HeapArray[i]+" ");
		System.out.println();
		
	}
	
	private void heapify()
	{
		for(int i = 1; i<this.size();i++)
			 bubbleUp(i);  
		
	}
	
}



