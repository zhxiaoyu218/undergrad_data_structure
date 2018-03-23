/****************************
   CSC172 lab7 
   Xiaoyu Zhang
   xzhang81@u.rochester.edu
****************************/
public class lab7 {

	public static void main(String[] argv){
		MyQueue<String> queue = new MyQueue<>();		
		
		
		System.out.println("The queue is empty?: "+queue.isEmpty());	
		System.out.println();	
		System.out.println("  enqueue 'A' into stack");
		queue.enqueue("A");
		System.out.println("  enqueue 'B' into stack");
		queue.enqueue("B");
		System.out.println("  enqueue 'C' into stack");
		queue.enqueue("C");
		System.out.println("  enqueue 'D' into stack");
		queue.enqueue("D");
		System.out.println("  enqueue 'E' into stack");
		queue.enqueue("E");
		System.out.println();
		System.out.println(" Top of queue is " + queue.peek());
		System.out.println();
		System.out.println("  dequeue 1: " + queue.dequeue());
		System.out.println("  dequeue 2: " + queue.dequeue());
		System.out.println("  dequeue 3: " + queue.dequeue());
		System.out.println("  dequeue 4: " + queue.dequeue());
		System.out.println("  dequeue 5: " + queue.dequeue());
		System.out.println("  dequeue 6: " + queue.dequeue() );
		

		
	return;
	}
	
	
}


class MyQueue<AnyType> implements Queue<AnyType>{
	private MyDoubleNode<AnyType> head;
	private MyDoubleNode<AnyType> tail;
	
	MyQueue()
	{
		head = new MyDoubleNode<>();      
		tail = new MyDoubleNode<>();      
		head.prev = null;
		head.next = tail;
		tail.prev = head;
		tail.next = null;
	}

	@Override
	public boolean isEmpty() {	
		return head == tail.prev;
	}

	@Override
	public void enqueue(AnyType x) 
	{
		MyDoubleNode<AnyType> newNode = new MyDoubleNode<AnyType>();	
		newNode.data = x;
		newNode.next = tail;
		newNode.prev = tail.prev;
		(newNode.prev).next = newNode;
		(newNode.next).prev = newNode;	
	}

	@Override
	public AnyType dequeue() {
		
		
		if (isEmpty() != true)
		{
			MyDoubleNode<AnyType> temp = new MyDoubleNode<AnyType>();
			temp= head.next;
			head.next.next.prev =head; 	
			head.next =head.next.next;
			
			return temp.data;	
		}else{
			return null;			
		}
	}

	@Override
	public AnyType peek()
	{
		MyDoubleNode<AnyType> temp = new MyDoubleNode<AnyType>();
		temp= head.next;
		return temp.data;
	}	
}


class MyDoubleNode<AnyType> 
{
	public AnyType data;
	public MyDoubleNode<AnyType> next;
	public MyDoubleNode<AnyType> prev;
} 


interface Queue<AnyType> {
	public boolean isEmpty();
	public void enqueue(AnyType x);
	public AnyType dequeue();
	public AnyType peek();
}


