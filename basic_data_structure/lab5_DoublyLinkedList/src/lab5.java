/****************************
   CSC172 lab5 : test program
   Xiaoyu Zhang
   xzhang81@u.rochester.edu
****************************/


public class lab5 {
	public static void main(String[] argv){
		 DLinkedList<String> List = new  DLinkedList<String>();
	
		if(List.isEmpty()==true);
			System.out.println("The list is empty");
		List.insert("A");
		List.insert("B");
		List.insert("1");
		List.insert("test");
		System.out.println("The element in my List are printed in forward order: ");
		List.printList();
		System.out.println();
		System.out.println("The element in my List are printed in reverse order: ");
		List.printListRev();
		System.out.println();
		System.out.println("If I wanna insert element ('B') already in list : ");
		List.insert("B");
		System.out.println();
		System.out.println("If I wanna delete element ('C') not in list : ");
		List.delete("C");
		System.out.println();
		List.delete("B");
		System.out.println("The printlist after element 'B' is deleted : ");
		List.printList();
		System.out.println();
		System.out.println("The lookup for element '1' is :   " +  List.lookup("1"));
		System.out.println("The lookup for element 'Z' which is not in list :   " +  List.lookup("Z"));
		
		if(List.isEmpty()==false);
			System.out.println("The list is not empty");
		
	return;
	}

}
