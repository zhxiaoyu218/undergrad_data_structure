/*
 * CSC172 lab16
 * Xiaoyu Zhang
 * xzhang81@u.rochester.edu
 */
import java.util.Arrays;

public class SortTest {
		private static long count;
		//private static long count_insetion;
		//private static long count_shell;
		public static void main(String args[]) {
				//long startTime, endTime, elapsedTime;
				//int size = Integer.parseInt(args[0]);
				int size = 30;
				Integer [] a = new Integer[size];
				Integer [] b = new Integer[size];
				long time = 0;
				long TotalCount = 0;
				
				System.out.println("Check all sort method working ");
				for (int i = 0; i < size; i++) {
					a[i] = b[i] = (int)(Math.random() * 100);
				}
				System.out.println("Original array is ");
				PrintArray(a);

				
				System.out.println("After Bubble sort  is ");
				bubblesort(a);
				PrintArray(a);
				a = b;
				
				System.out.println("After Insertion sort  is ");
				insertionSort(a);
				PrintArray(a);
				a = b;
				
				System.out.println("After Shell sort  is ");
				 shellsort(a);
				PrintArray(a);
				a = b;
				
				System.out.println("After sort() method is ");
				Arrays.sort(a);
				PrintArray(a);
				a = b;
				
				System.out.println("After Quick sort  is ");
				QuickSort(a,0,a.length-1);
				PrintArray(a);
				a = b;
				
				System.out.println();
				System.out.println();
				
				
				
				count = 0;
				int[] sizeAry = {100, 1000, 5000,10000,20000, 30000,40000, 50000,60000,70000,100000};
				System.out.println("******************  Bubble Sort  ******************");
				for(int i = 0;i < sizeAry.length; i++)
				{
					time = (SortTester(sizeAry[i],1)  + SortTester(sizeAry[i],1) + SortTester(sizeAry[i],1))/3;
					TotalCount = count/3;
					System.out.println("	took "+ TotalCount + " moves to sort "+ sizeAry[i] + " items in " + time+" millesec");
					count = 0;
					time = 0;
					TotalCount = 0;
				}
				count = 0;time = 0;TotalCount = 0;
				System.out.println("******************  Insertion Sort  ******************");				
				for(int i = 0;i < sizeAry.length; i++)
				{
					time = (SortTester(sizeAry[i],2)  + SortTester(sizeAry[i],2) + SortTester(sizeAry[i],2))/3;
					TotalCount = count/3;
					System.out.println("	 took "+ TotalCount + " moves to sort "+ sizeAry[i] + " items in " + time+" millesec");
					count = 0;
					time = 0;
					TotalCount = 0;
				}
				count = 0;time = 0;TotalCount = 0;
				System.out.println("******************  shell Sort  ******************");
				for(int i = 0;i < sizeAry.length; i++)
				{
					time = (SortTester(sizeAry[i],3)  + SortTester(sizeAry[i],3) + SortTester(sizeAry[i],3))/3;
					TotalCount = count/3;
					System.out.println("	took "+ TotalCount + " moves to sort "+ sizeAry[i] + " items in " + time+" millesec");
					count = 0;
					time = 0;
					TotalCount = 0;
				}
				
				count = 0;time = 0;TotalCount = 0;
				System.out.println("******************  Sort() method  ******************");
				for(int i = 0;i < sizeAry.length; i++)
				{
					time = (SortTester(sizeAry[i],4)  + SortTester(sizeAry[i],4) + SortTester(sizeAry[i],4))/3;
					TotalCount = count/3;
					System.out.println("	sort "+ sizeAry[i] + " items in " + time+" millesec");
					count = 0;
					time = 0;
					TotalCount = 0;
				}
				
				count = 0;time = 0;TotalCount = 0;
				System.out.println("******************  Quick Sort  ******************");
				for(int i = 0;i < sizeAry.length; i++)
				{
					time = (SortTester(sizeAry[i],5)  + SortTester(sizeAry[i],5) + SortTester(sizeAry[i],5))/3;
					TotalCount = count/3;
					System.out.println("	took "+ TotalCount + " moves to sort "+ sizeAry[i] + " items in " + time+" millesec");
					count = 0;
					time = 0;
					TotalCount = 0;
				}


		}
		public static long SortTester(int size,int sel)
		{
			long startTime, endTime, elapsedTime;
			Integer [] a = new Integer[size];
			for (int i = 0; i < size; i++) 
			{
				a[i] = (int)(Math.random() * 100);
			}
			switch(sel)
			{
				case 1:		//bubble
					startTime = System.currentTimeMillis();
					bubblesort(a);
					endTime = System.currentTimeMillis();	
					elapsedTime = endTime - startTime;
				break;
				
				case 2:   //insetion
					startTime = System.currentTimeMillis();
					insertionSort(a);
					endTime = System.currentTimeMillis();	
					elapsedTime = endTime - startTime;
				break;
				
				case 3:  ///shell
					startTime = System.currentTimeMillis();
					 shellsort(a);
					endTime = System.currentTimeMillis();
					elapsedTime = endTime - startTime;
				break;
				
				case 4:  // sort() method
					startTime = System.currentTimeMillis();
					Arrays.sort(a);
					endTime = System.currentTimeMillis();
					elapsedTime = endTime - startTime;
				break;
				
				case 5:  // sort() method
					startTime = System.currentTimeMillis();
					QuickSort(a,0,a.length-1);
					endTime = System.currentTimeMillis();
					elapsedTime = endTime - startTime;
				break;
	
				
				default :
					elapsedTime = 0;
		            System.out.println("Invalid grade");
			}
				
		
			
			return elapsedTime;
			
		}
		public static <AnyType> void PrintArray(AnyType[] a)
		{
			int i;
			//System.out.println();
			System.out.print("		[");
			for(i=0;i<a.length -1;i++)
			{
				System.out.print(a[i]+", ");
				
			}
			System.out.print(a[a.length -1]);
			System.out.print("]");
			System.out.println();
		}
		public static <AnyType extends Comparable<? super AnyType>> void bubblesort(AnyType[] a) {
				for (int i = 0; i < a.length; i++) {
						for (int j = 0; j < a.length - 1; j++) {
							if (a[j].compareTo(a[j + 1]) > 0) {
								AnyType tmp = a[j];
								count++;
								a[j] = a[j + 1];
								count++;
								a[j + 1] = tmp;
								count++;
							}
						}
				}
		}
		
		public static <AnyType extends Comparable<? super AnyType>>void insertionSort( AnyType []a){
				int j;
				
				for( int p = 1; p < a.length; p++ )
				{
					AnyType tmp = a[ p ];
					count++;
					for( j = p; j > 0 && tmp.compareTo( a[ j - 1 ] ) < 0; j-- )
					{
						a[ j ] = a[ j - 1 ];
						count++;
					}
					a[ j ] = tmp;
					count++;
				}
		 }
		
		public static <AnyType extends Comparable<? super AnyType>>void shellsort( AnyType [ ] a )
		{
				int j;
		
				for( int gap = a.length / 2; gap > 0; gap /= 2 )
					for( int i = gap; i < a.length; i++ )
					{
						AnyType tmp = a[ i ];
						count++;
						for( j = i; j >= gap && tmp.compareTo( a[ j - gap ] ) < 0; j -= gap )
						{
							a[ j ] = a[ j - gap ];
							count++;
						}
						a[ j ] = tmp;
						count++;
					}
		 }
		
		
		public static void QuickSort(Integer[] a, int low, int high) {
			if (a == null || a.length == 0)
				return;
	 
			if (low >= high)
				return;
	 
			// pick the pivot
			int middle = low + (high - low) / 2;
			int pivot = a[middle]; count ++;
	 
			// make left < pivot and right > pivot
			int i = low, j = high;
			while (i <= j) {
				while (a[i] < pivot) {
					i++;
				}
	 
				while (a[j] > pivot) {
					j--;
				}
	 
				if (i <= j) {
					int temp = a[i];
					a[i] = a[j];
					count ++;
					a[j] = temp;
					count ++;
					i++;
					j--;
				}
			}
	 
			// recursively sort two sub parts
			if (low < j)
				QuickSort(a, low, j);
	 
			if (high > i)
				QuickSort(a, i, high);
		}
		
		
}

