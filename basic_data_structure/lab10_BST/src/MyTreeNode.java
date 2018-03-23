/****************************
   CSC172 lab10 : MyTreeNode
   Xiaoyu Zhang
   xzhang81@u.rochester.edu
****************************/
public class MyTreeNode<T extends Comparable<T>> 
{
	public T data;
	public MyTreeNode<T> leftChild;
	public MyTreeNode<T> rightChild;
	public MyTreeNode<T> parent;
	
	public MyTreeNode<T> insert_rec(T x, MyTreeNode<T> current )
	{
		if( current == null )
		{
			MyTreeNode<T> newNode = new MyTreeNode<T>();
			newNode.data = x;
			return newNode;
		}
		int compareResult = x.compareTo(current.data );
		if( compareResult < 0 )
			current.leftChild = insert_rec( x, current.leftChild );
		else if( compareResult > 0 )
			current.rightChild = insert_rec( x, current.rightChild );
		else
			; // Duplicate; do nothing
		return current;
	 }

	public void printPreOrder(MyTreeNode<T> t)
	{
		if( t != null )
		{
			System.out.print(" "+t.data );
			printPreOrder( t.leftChild );
			printPreOrder( t.rightChild );
		}
	}
	public void printInOrder(MyTreeNode<T> t)
	{
		if( t != null )
		{
			
			printInOrder( t.leftChild );
			System.out.print(" "+t.data );
			printInOrder( t.rightChild );
		}
	}
	public void printPostOrder(MyTreeNode<T> t)
	{
		if( t != null )
		{
			
			printPostOrder( t.leftChild );
			printPostOrder( t.rightChild );
			System.out.print(" "+t.data );
		}
	}
	public boolean lookup(T x,MyTreeNode<T> t) 
	{
		if( t == null )
			return false;
			
		int compareResult = x.compareTo(t.data );
		if( compareResult < 0 )
			return lookup( x, t.leftChild );
		else if( compareResult > 0 )
			return lookup( x, t.rightChild );
		else
			return true; // Match
	 }
	public MyTreeNode<T> delete(T x, MyTreeNode<T> t )
	{

		if( t == null )
			 return t; // Item not found; do nothing

		 int compareResult = x.compareTo( t.data );
		 if( compareResult < 0 )
			 t.leftChild = delete( x, t.leftChild );
		 else if( compareResult > 0 )
			 t.rightChild = delete( x, t.rightChild );
		 else if( t.leftChild != null && t.rightChild != null ) // Two children
		{
			 t.data = findMin( t.rightChild ).data;
			 t.rightChild = delete( t.data, t.rightChild );
	 }
		 else
			 t = ( t.leftChild != null ) ? t.leftChild : t.rightChild;
		 return t;
	}
	private MyTreeNode<T> findMin( MyTreeNode<T> t )
	{
	 if( t == null )
		 return null;
	 else if( t.leftChild == null )
		 return t;
	 return findMin( t.leftChild );
	 }
}
