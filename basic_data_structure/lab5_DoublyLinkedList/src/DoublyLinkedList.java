/****************************
   CSC172 lab5 : LinkedList interface
   Xiaoyu Zhang
   xzhang81@u.rochester.edu
****************************/


public interface DoublyLinkedList<AnyType>
{
	public void insert(AnyType x);
	public void delete(AnyType x);
	public boolean contains(AnyType x);
	public AnyType lookup(AnyType x);
	public boolean isEmpty();
	public void printList();
	public void printListRev();
}


