/*
 * CSC172 lab14
 * Xiaoyu Zhang
 * xzhang81@u.rochester.edu
 */
 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class lab14 {
	
	
	public static int hash( String key, int tableSize )
	{
		int hashVal = 0;
	
		for( int i = 0; i < key.length( ); i++ )
			hashVal = 37 * hashVal + key.charAt( i );
	
		hashVal %= tableSize;
		if( hashVal < 0 )
			hashVal += tableSize;
		return hashVal;
	}
	


	public static void main(String[] args) {
		HashTable myhash = new HashTable();
		String[] name = {"gray","anna","eugene","daniel","emma"};	
		System.out.println("Part 1-4, start with insert these name:");
		for(int i = 0; i<name.length; i++)
		{
			System.out.print("   "+ name[i]);;
			myhash.insert(name[i]);
		}
		System.out.println("\n");

		System.out.println("  Capacity of this hashtable is: "+ myhash.capacity());
		System.out.println("  The number of unique items contained: " + myhash.NumOfItems());
		System.out.println("  Load factor: " + myhash.LoadFactor());
		System.out.println();
		System.out.println("  Print out non-null string from hash table: ");
		myhash.print();
		
		System.out.println("\n");
		System.out.println("Part 5, read Lorem Ipsum text and enter the words into hash table");
		HashTable myhash2 = new HashTable(13);
		int i;
		int Totalwords = 0;
		File InputFile = new File(args[0]);

		 try{  // read from file 
				
				BufferedReader reader= new BufferedReader(new FileReader(InputFile));//pass the file into the bufferedreader
				 
				for(int k=0; k<10;k++)
				{
					String temp = reader.readLine();

					temp=temp.replace("\n", "");
					temp=temp.replace(",", " ");
					temp=temp.replace(".", " ");

					String temp2[]=temp.split(" ");

					for(i =0; i<temp2.length;i++)
					{
						Totalwords++;
						myhash2.insert(temp2[i]);
					}
					if(k!=9)//skip space between two paragraph
						reader.readLine();	 
					
				}
			    reader.close();
			}catch(FileNotFoundException e){
				System.out.println("File not found");//if the fild doesn't exist then tell the user
			}catch(IOException e){
				System.out.println("Error");
			}
		 System.out.println("  Print out unique words from hash table: ");
		 myhash2.print();
		 System.out.println("  The number of unique words:       "+ myhash2.NumOfItems());
		 System.out.println("  The total count of words read in: " + Totalwords);
		 System.out.println("  The full size of hash table:      " + myhash2.capacity());
		 
		 return;
	}
}	
	
	class HashTable{
		
		private int currentSize;      // Number of elements in heap
	    private String [ ] HashArray; // The heap array
	    private static final int DefaultCapacity = 13;
	    private int HashCapacity;
	    
	    HashTable(int capacity){
	    	currentSize = 0;
	    	HashCapacity = capacity;
			HashArray = new String[capacity];
		}
	    
	    HashTable(){
	    	this(DefaultCapacity);
		}
	    
		public void insert(String x)
		{
			for(int i = 0; i< this.HashCapacity ;i++)
			{
				if( (HashArray[i]!=null))
				{
					if( (HashArray[i].compareTo(x) == 0))
					return;
				}
			}
			
			if(LoadFactor() > 0.5) //check load factor expand and rehash if larger than 0.5
			{
				rehash();
			}
			

			int temp = lab14.hash(x,this.HashCapacity);
			if( HashArray[temp] == null)
			{
					HashArray[temp] = x;
					currentSize++;	
			}
			else
			{
					//System.out.println(temp);
				while(HashArray[temp] != null)
				{
					temp++;
					if (temp > (HashCapacity-1)) temp = 0;	
				}
				HashArray[temp] = x;
				currentSize++;		
			}
			return;	
		}
	    
		public void print() 
		{
			for(int i = 0; i< this.HashCapacity ;i++)
			{
				if(HashArray[i] != null)
					System.out.println("	"+HashArray[i]+" ");
			}			
		}
		public int capacity()
		{
			return this.HashCapacity;
		}
		public int NumOfItems()
		{
			return this.currentSize;
		}
		public float LoadFactor()
		{
			return (float)this.currentSize/(float)this.HashCapacity;
		}
		
		public void rehash()
		{
			String[] temp =new String[this.HashArray.length * 2 ];
			for(int i = 0; i <HashArray.length; i++)
			{  
				  if(HashArray[i] != null)
				  {			  
					  int temp2 = lab14.hash(HashArray[i],this.HashCapacity);
					  temp[temp2] = this.HashArray[i]; 
				  }// Copy all the references
			}
		    this.HashCapacity = this.HashArray.length * 2;
			this.HashArray = temp;  		
		}
		
	}


