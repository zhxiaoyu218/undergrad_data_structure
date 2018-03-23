/****************************
   CSC172 Proj2
   Xiaoyu Zhang
   xzhang81@u.rochester.edu
****************************/

import java.io.FileNotFoundException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class InfixCalculator
{
	private static boolean ExpressionError;
	public static MyQueue<String> Postfix(String expression){
			expression=expression.replaceAll("\\(", "\\( ");
			expression=expression.replaceAll("\\)", "\\ )");
			expression=expression.replaceAll("!", "! ");
			expression=expression.replaceAll("\n", "");
			expression=expression.replaceAll("\r", "");
			expression=expression.replaceAll("cos", "cos ");
			expression=expression.replaceAll("sin", "sin ");
			expression=expression.replaceAll("tan", "tan ");

	        MyQueue<String> Queue=new MyQueue<>();
	        MyStack<String> Stack =new MyStack<>();
			String token[]=expression.split(" ");
			 // System.out.println("1111111111");
	        int i;
	        String temp1;
	        //priority=0;
	          // System.out.println("length : "+token.length);
	        for(i=0;i<token.length;i++)
	        {
	         //  System.out.println("element : "+token[i]);
	            if(isNum(token[i]))
				{
	            	Queue.enqueue(token[i]);
	            }
	            else if (token[i].equals("(") )
	            {           
	            	Stack.push(token[i]);	
	            }
	            else if (token[i].equals(")"))
	            {  
	            	while(!((Stack.peek()).equals("(")) )
	            	{
	            		Queue.enqueue( Stack.pop());
	            	}
	            	Stack.pop()	;		
	            }

	            else
	            {
	            	if(Stack.isEmpty())
	            	{
	            		Stack.push(token[i]);
	            	}
	            	else if( getPriority(token[i]) >= getPriority( Stack.peek()))
	            	{
	            		Stack.push(token[i]);
	            	}
	            	else if (getPriority(token[i]) < getPriority( Stack.peek()))
	            	{
	            		do{            			
	            			temp1= Stack.pop() ;
	            			Queue.enqueue( temp1 );		
	            		}while( getPriority( Stack.peek() ) >= getPriority(  temp1)  );           	
	            		Stack.push(token[i]);
	            	}
	            }
	        }
	          
	        while(!Stack.isEmpty())
	        {
	        	Queue.enqueue( Stack.pop()); 	
	        }
	        return Queue;
	}
    public static int getPriority(String op)
    {	 
    	if(op.equals("(") || op.equals(")")|| op.equals("!")|| op.equals("cos")|| op.equals("sin")|| op.equals("tan"))
    			return -1;
    	else if(op.equals("=") || op.equals(">")|| op.equals("<"))
    			return 1;
    	else if(op.equals("+") || op.equals("-"))
            	return 2;
        else if(op.equals("*") || op.equals("/")|| op.equals("%"))
        		return 3;
        else if(op.equals("^") || op.equals("&") || op.equals("|"))
        		return 4;
        else
            	return 5;
    }
	public static boolean isNum(String str){
		 return str.matches("-?\\d+(\\.\\d+)?");  
	}
	public static double PostFixCalculation( MyQueue<String> Queue)
	{
		
	     MyStack<String> Stack=new MyStack<>();
	     double temp1,temp2,temp3;
	     String temp4,temp5;
	     ExpressionError =false;
	     while(!Queue.isEmpty())
	     {
	            if(isNum(Queue.peek()))
				{
	            	Stack.push(  Queue.dequeue()  );
	            }
	            else
	            {
	            	
	            	switch(Queue.dequeue()){
	            		case "+":
	            			temp2 = Double.parseDouble(Stack.pop());
	    	            	temp1 = Double.parseDouble(Stack.pop());
	            			temp3 = temp1 + temp2;
	            			Stack.push(Double.toString(temp3));
	            		break;
	            		case "-":
	            			temp2 = Double.parseDouble(Stack.pop());
	    	            	temp1 = Double.parseDouble(Stack.pop());
	            			temp3 = temp1 - temp2;
	            			Stack.push(Double.toString(temp3));
	            		break;	            		
	            		case "*":
	            			temp2 = Double.parseDouble(Stack.pop());
	    	            	temp1 = Double.parseDouble(Stack.pop());
	            			temp3 = temp1 * temp2;
	            			Stack.push(Double.toString(temp3));
	            		break;	            	
	            		case "/":
	            			temp2 = Double.parseDouble(Stack.pop());
	    	            	temp1 = Double.parseDouble(Stack.pop());
	    	            	if(temp2!=0)
	    	            	{
	    	            		temp3 = temp1 / temp2;
	    	            		Stack.push(Double.toString(temp3));
	    	            	}
	    	            	else
	    	            	{
	    	            		//System.out.println("     divisor should not be zero,So ");
	    	            		ExpressionError =true;
	    	            		return 0;
	    	            	}
	            		break;	            	
	            		case "&":
	            			temp4 = Stack.pop();
	            			temp5 = Stack.pop();
	            			if((temp4.equals("1") || temp4.equals("0"))&&(temp5.equals("1") || temp5.equals("0")))
	            			{	
	            				if (temp4.equals("1") && temp5.equals("1")  )
	            					Stack.push("1");
	            				else 
	            					Stack.push("0");
	            			}
	            			else
	            			{
	            				//System.out.println("     The opread of logic expression NOT is not valid,So ");
	            				ExpressionError =true;
	            				return 0;
	            			}
	            		break;	            	
	            		case "|":
	            			temp4 = Stack.pop();
	            			temp5 = Stack.pop();
	            			if((temp4.equals("1") || temp4.equals("0"))&&(temp5.equals("1") || temp5.equals("0")))
		            		{
	            				if (temp4.equals("0") && temp5.equals("0")  )
	            					Stack.push("0");
	            				else 
	            					Stack.push("1");
		            		}
	            			else
	            			{
		            			//System.out.println("     The opread of logic expression NOT is not valid,So ");
		            			ExpressionError =true;
		            			return 0;
		            		}
	            		break;
	            		case "<":
	            			temp2 = Double.parseDouble(Stack.pop());
	    	            	temp1 = Double.parseDouble(Stack.pop());
	            				if(temp1 <temp2)
	            					Stack.push("1");
	            				else
	            					Stack.push("0");
	            		break;
	            		case ">":
	            			temp2 = Double.parseDouble(Stack.pop());
	    	            	temp1 = Double.parseDouble(Stack.pop());
	            				if(temp1 >temp2)
	            					Stack.push("1");
	            				else
	            					Stack.push("0");
	            		break;	
	            		case "=":
	            			temp2 = Double.parseDouble(Stack.pop());
	    	            	temp1 = Double.parseDouble(Stack.pop());
	            				if(temp1 ==temp2)
	            					Stack.push("1");
	            				else
	            					Stack.push("0");
	            		break;	
	            		case "!":
	            			
	            			temp4 = Stack.pop();
	            			if (temp4.equals("1")  )
	            				Stack.push("0");
	            			else if (temp4.equals("0"))
	            				Stack.push("1");
	            			else{
	            				//System.out.println("     The opread of logic expression NOT is not valid,So ");
	            				ExpressionError =true;
	            				return 0;
	            			}

	            		break;
	            		case "^":
	            			temp2 = Double.parseDouble(Stack.pop());
	    	            	temp1 = Double.parseDouble(Stack.pop());
	            			temp3 = Math.pow(temp1,temp2);
	            			Stack.push(Double.toString(temp3));
	            		break;
	            		case "%":
	            			temp2 = Double.parseDouble(Stack.pop());
	    	            	temp1 = Double.parseDouble(Stack.pop());
	            			temp3  =temp1%temp2;
	            			Stack.push(Double.toString(temp3));
	            		break;	
	            		case "cos":
	            			temp2 = Double.parseDouble(Stack.pop());
	            			temp2 = Math.cos(Math.toRadians(temp2));
	            			Stack.push(Double.toString(temp2));
	            		break;
	            		case "sin":
	            			temp2 = Double.parseDouble(Stack.pop());
	            			temp2 = Math.sin(Math.toRadians(temp2));
	            			Stack.push(Double.toString(temp2));

	            		break;
	            		case "tan":
	            			temp2 = Double.parseDouble(Stack.pop());
	            			temp2 = Math.tan(Math.toRadians(temp2));
	            			Stack.push(Double.toString(temp2));

	            		break;
	            	}
	            }
	        }  

		return Double.parseDouble(Stack.pop());
	}
	
    public static void main(String[] args)
    {
        MyQueue<String> postfix = new MyQueue<>();

        File Inputfile = new File(args[0]);
        File Outputfile = new File(args[1]);
		// this is test my own extended expression and extra credit

       int i;
	   double temp;
       ExpressionError = false;
        try{
			
			BufferedReader reader= new BufferedReader(new FileReader(Inputfile));//pass the file into the bufferedreader
			PrintWriter printer = new PrintWriter(new FileWriter(Outputfile));
			  
			for(i=0;i<25;i++)
			{			
				temp = PostFixCalculation(Postfix( reader.readLine()));
				if(ExpressionError==true)
					printer.println("invalid expression!!!");
				else
					printer.println(temp);	
			}

		    printer.close();
		    reader.close();

			
		}catch(FileNotFoundException e){
			System.out.println("File not found");//if the fild doesn't exist then tell the user
		}catch(IOException e){
			System.out.println("Error");
		}

    }
}





class MyStack<AnyType> implements Stack<AnyType>{
	private MyStackNode<AnyType> head;
	private int size;
	MyStack()
	{
		head = new MyStackNode<>();
		head = null;
		size =0;		
	}
	
	
	@Override
	public boolean isEmpty() {
			return head==null;
	}

	@Override
	public void push(AnyType x) {
		MyStackNode<AnyType> newLink = new MyStackNode<>();
		newLink.data = x;
		newLink.next = head;
		head = newLink;	
		size ++;
	}

	@Override
	public AnyType pop() {
		if (isEmpty() != true)
		{
			MyStackNode<AnyType> temp = new MyStackNode<>();
			temp= head;
			head =head.next;
			size --;
			return temp.data;	
		}else{
			return null;			
		}
	}

	@Override
	public AnyType peek() {
		MyStackNode<AnyType> temp = new MyStackNode<>();
		temp= head;	
		return temp.data;
	}
	public int size(){
		
		return size;
	}
	
}
class MyQueue<AnyType> implements Queue<AnyType>{
	private MyQueneNode<AnyType> head;
	private MyQueneNode<AnyType> tail;
	private int size;
	MyQueue()
	{
		head = new MyQueneNode<>();      
		tail = new MyQueneNode<>();      
		head.prev = null;
		head.next = tail;
		tail.prev = head;
		tail.next = null;
		size = 0;
	}

	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {	
		return head == tail.prev;
	}

	@Override
	public void enqueue(AnyType x) 
	{
		MyQueneNode<AnyType> newNode = new MyQueneNode<AnyType>();	
		newNode.data = x;
		newNode.next = tail;
		newNode.prev = tail.prev;
		(newNode.prev).next = newNode;
		(newNode.next).prev = newNode;	
		size ++;
	}

	@Override
	public AnyType dequeue() {
		
		
		if (isEmpty() != true)
		{
			MyQueneNode<AnyType> temp = new MyQueneNode<AnyType>();
			temp= head.next;
			head.next.next.prev =head; 	
			head.next =head.next.next;
			size --;
			return temp.data;	
		}else{
			return null;			
		}
	
	}

	@Override
	public AnyType peek()
	{
		MyQueneNode<AnyType> temp = new MyQueneNode<AnyType>();
		temp= head.next;
		return temp.data;
	}	
}

class MyStackNode<AnyType>{
	public AnyType data;
	public MyStackNode<AnyType> next;	
}
class MyQueneNode<AnyType> {
	public AnyType data;
	public MyQueneNode<AnyType> next;
	public MyQueneNode<AnyType> prev;
} 

interface Stack<AnyType> {
	public boolean isEmpty();
	public void push(AnyType x);
	public AnyType pop();
	public AnyType peek();
} 
interface Queue<AnyType> {
	public boolean isEmpty();
	public void enqueue(AnyType x);
	public AnyType dequeue();
	public AnyType peek();
}

