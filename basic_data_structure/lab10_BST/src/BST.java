/****************************
   CSC172 lab10 : BST
   Xiaoyu Zhang
   xzhang81@u.rochester.edu
****************************/
public interface BST<T extends Comparable<T> > 
{
	public void insert(T x);
	public void delete(T x);
	public boolean lookup(T x);
	public void printPreOrder();
	public void printInOrder();
	public void printPostOrder();
} 

