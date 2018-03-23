/****************************
   CSC172 lab10 
   Xiaoyu Zhang
   xzhang81@u.rochester.edu
****************************/
public class lab10 {
	public static void main(String[] argv)
	{
		myBST<Integer> tree = new myBST<Integer>();
		System.out.println("50 75 37 25 61 55 30 15 68 36 28 59 are inserted in order");
		Integer[] ary = {50,75,37,25,61,55,30,15,68,32,36,28,59};
		for(Integer i:ary)
			tree.insert(i);	
		System.out.println();
		
		System.out.println("If want to insert 50 again (which is contained in tree)");
		tree.insert(50);
		System.out.println();
		
		System.out.println("Print tree preorder");
		tree.printPreOrder();
		System.out.println(); 
		System.out.println();
		
		System.out.println("Print tree inorder");
		tree.printInOrder();
		System.out.println();
		System.out.println();
		
		System.out.println("Print tree postorder");
		tree.printPostOrder();
		System.out.println();
		System.out.println();
		
		System.out.println("Delete element that not in tree e.g. 10000");
		tree.delete(10000);
		System.out.println();
		
		System.out.println("Delete a leaf on the tree which is 15 :");
		tree.delete(15);
		System.out.println(" Print tree preorder");
		tree.printPreOrder();
		System.out.println(); 
		System.out.println();
		
		System.out.println("Delete item with single child on the tree which is 75 :");
		tree.delete(75);
		System.out.println(" Print tree preorder");
		tree.printPreOrder();
		System.out.println(); 
		System.out.println();
		
		System.out.println("Delete item with two children on the tree which is 50 :");
		tree.delete(50);
		System.out.println(" Print tree preorder");
		tree.printPreOrder();
		System.out.println(); 
		System.out.println();
	}
}



