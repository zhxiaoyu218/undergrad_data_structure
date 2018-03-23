****************************
   CSC172 proj2
   README file
   Xiaoyu Zhang
   xzhang81@u.rochester.edu
****************************

This proj2 zip file should including InfixCalculator.java,README.txt,OUTPUT.txt, infix_expr_short.txt, postfix_eval_short.txt, myinfix.txt
in order to run this project 

>>OUTPUT.txt
	This file include the output of lab3.java that pasted from command prompt
>>infix_expr_short.txt
	This file contain infix expression
	in order to run this project, this file need to be in same folder with InfixCalculator.java
>>postfix_eval_short.txt
	This file contain correct expression evaluation

>>myinfix.txt
	This file contain the extened test(fisrt five row) and extra credit
	
>>InfixCalculator.java
	1.  This java file include all section for project 2
	
	2.  this code should be compile like : javac InfixCalculator.java
					then: java InfixCalculator infix_expr_short.txt my_eval.txt          (output file could be different txt file)
					usage    java InfixCalculator [input] [output]
					
	3.  How is code work: 
	    except stack and queue class, this code contain two mean method: Postfix method is used to convert infix expression to post expression(input is string expression;output is queue contain post-fix),
	    PostFixCalculation method is used to evaluate the post-fix expression( input is queue;output is a double number ).  
	    this code all logical operator is for 1 bit operand (only 1 or 0). it means that decimal number 8 cannot have logical calculation by convert to binary number 1000.
	
	4.  How Postfix method work: 
		In oreder to use .split() method separate the expression string into string array "Token[]" that contain opread and opreator,
		I need to replace "(" and ")" to "( " and " )"(same idea for "!","tan","cos","sin"). Then, loop string array Token[], see if :
			(1) Token[i] is a number, push into queue. 
			(2) if Token[i] is "(",enqueue it.
			(3) if Token[i] is ")", pop stack and enqueue them until meet "(" . However, both "("and")" will never be enqueued.
			(4) other case of Token[i] should only be operator, then if stack is empty, push it; if token[i] have higher precedence then top of stack,push it;
				else will pop stack until find out lower precedence, then push token[i].
		I feel obstacle should be precedence of operator, then I just use other mehtod to value the operator.
	
	5.  How PostFixCalculation method work: 
		In this method, if what dequeued is number, push to stack. if I dequeued is operator, it will pop number from stack and have corresponding calculate, then push into stack.
	
	6.  extra cradit just tested by use expression string rather than read file.
			1. [^],[%],[sin],[cos],[tan] are evaluated and printed out. sin,cos and tan calculate angle uaing degree rather radian. could try different number. 
			   e.g. "2 ^ 2 ^ 3 " for 2^2^3; "2 % 1" for 2%1 ; sin30 for sin(30)

			2. As I mentioned, logical calculation could only calculate 1 or 0. if operand for logcial operator is not 1 or 0, expression cannot be evaluated and print error message.
			   If divisor is zero,expression cannot be evaluated and print error message. it is checked by using flag, if expression cannot be evaluated, flag will set to be 1 and return 0 from PostFixCalculation method 
			   and print corresponding error message.
				
