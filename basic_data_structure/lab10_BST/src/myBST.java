/****************************
   CSC172 lab10 : myBST
   Xiaoyu Zhang
   xzhang81@u.rochester.edu
****************************/
public class myBST<T extends Comparable<T> > implements BST<T> {
	private MyTreeNode<T> root;	
	myBST()
	{	
		root = new MyTreeNode<T>();
		root.data = null;
		root.parent =null;
		root.leftChild = null;
		root.rightChild = null;

	}
	
	
	public void insert(T x) 
	{
		if(this.root.data == null)
		{
			this.root.data = x;
		}
		else if( (this.root.data != null) && (lookup(x) == false))
		{
			this.root = this.root.insert_rec(x, root);
		}
		else
		{
			System.out.println("  element " + x +" already contained in tree");
		}
		return;
	}
	public void delete(T x) 
	{
		if(lookup(x) == false)
		{
			System.out.println("  element " + x +" is not contained in tree");
		}
		else 
		{
			root = root.delete(x, root);
		}
	}
	public boolean lookup(T x) 
	{
		return root.lookup(x, root); // Match
	}

	public void printPreOrder() 
	{
		root.printPreOrder(root);
	}
	public void printInOrder() 
	{
		root.printInOrder(root);
	}
	public void printPostOrder() 
	{
		root.printPostOrder(root);
	}
	
}
