/****************************
   CSC172 lab6 
   Xiaoyu Zhang
   xzhang81@u.rochester.edu
****************************/
public class lab6 {

	public static void main(String[] argv){
		MyStack<String> stack = new MyStack<>();		
		
		System.out.println("The stack is empty?: "+stack.isEmpty());
			
		System.out.println("  push 'A' into stack");
		stack.push("A");
		System.out.println("  push 'B' into stack");
		stack.push("B");
		System.out.println("  push 'C' into stack");
		stack.push("C");
		System.out.println("  push 'D' into stack");
		stack.push("D");
		System.out.println("  push 'E' into stack");
		stack.push("E");
		System.out.println();
		System.out.println("Top of stack is " + stack.peek());
		System.out.println();
		System.out.println("  pop 1: " + stack.pop());
		System.out.println("  pop 2: " + stack.pop());
		System.out.println("  pop 3: " + stack.pop());
		System.out.println("  pop 4: " + stack.pop());
		System.out.println("  pop 5: " + stack.pop());
		System.out.println("  pop 6: " + stack.pop());
		

		
	return;
	}
	
	
}



class MyStack<AnyType> implements Stack<AnyType>{
	private MyNode<AnyType> head;
	
	MyStack()
	{
		head = new MyNode<>();
		head = null;	
	}
	
	
	@Override
	public boolean isEmpty() {
			return head==null;
	}

	@Override
	public void push(AnyType x) {
		MyNode<AnyType> newLink = new MyNode<>();
		newLink.data = x;
		newLink.next = head;
		head = newLink;	
	}

	@Override
	public AnyType pop() {
		if (isEmpty() != true)
		{
			MyNode<AnyType> temp = new MyNode<>();
			temp= head;
			head =head.next;
			return temp.data;	
		}else{
			return null;			
		}
	}

	@Override
	public AnyType peek() {
		MyNode<AnyType> temp = new MyNode<>();
		temp= head;	
		return temp.data;
	}
	
	
}

class MyNode<AnyType>
{
	public AnyType data;
	public MyNode<AnyType> next;	
}


interface Stack<AnyType> {
	public boolean isEmpty();
	public void push(AnyType x);
	public AnyType pop();
	public AnyType peek();
} 
