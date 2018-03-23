/****************************
   CSC172 lab5 : myDDlinkedList
   Xiaoyu Zhang
   xzhang81@u.rochester.edu
****************************/


public class DLinkedList<AnyType> implements DoublyLinkedList<AnyType>
{
	private MyDoubleNode<AnyType> head;
	private MyDoubleNode<AnyType> tail;
	

	DLinkedList()
	{
		head = new MyDoubleNode<>();      
		tail = new MyDoubleNode<>();      
		head.prev = null;
		head.next = tail;
		tail.prev = head;
		tail.next = null;

	}

	@Override
	public void insert(AnyType x)
	{
		if(contains(x)==false)
		{
		//	MyDoubleNode<AnyType> n= new MyDoubleNode<AnyType>();
			MyDoubleNode<AnyType> newNode = new MyDoubleNode<AnyType>();	
			newNode.data = x;
			newNode.prev = head;
			newNode.next = head.next;
			(newNode.prev).next = newNode;
			(newNode.next).prev = newNode;
		}else{
			System.out.println("   ---The element "+ x +" is already contained in list");	
		}
		return;

	}

	@Override
	public void delete(AnyType x)
	{
		if(contains(x)==true)
		{
			MyDoubleNode<AnyType> current = head;
			while (current != tail) 
			{
				if (x.equals(current.data)) 
				{
					(current.next).prev = current.prev;
					(current.prev).next = current.next;
					return;
				}
				current = current.next;
			}
		}else{
			System.out.println("   ---The element "+ x +" is not contained in list");
		}
		return;
	
	}

	@Override
	public boolean contains(AnyType x)
	{
		MyDoubleNode<AnyType> current =head;
		while(current != null)
		{
			if(current.data==x)
			{	return true;	}
			else
			{	current = current.next;		}
		}
		return false;
	}

	@Override
	public AnyType lookup(AnyType x) 
	{
		MyDoubleNode<AnyType> current = head;
		while (current != tail)
		{
		    if (x.equals(current.data))
		    {   return (current.data); }
		    else{ current = current.next ;}
		}
		return null; // not found

	}

	@Override
	public boolean isEmpty() 
	{		
		return head.next == tail.prev;
	}

	@Override
	public void printListRev() //runtiime is O(n)
	{
		MyDoubleNode<AnyType> current =head.next;
		while (current.next != null)
		{
			System.out.println("        "+(current.data).toString());
			current = current.next;
		}
	
	}

	@Override
	public void printList()  //runtiime is O(n)
	{
		MyDoubleNode<AnyType> current =tail.prev;
		while (current.prev != null)
		{
			System.out.println("        "+(current.data).toString());
			current = current.prev;
		}	
	}

}
