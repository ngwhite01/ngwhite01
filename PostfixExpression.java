package cs2321;
/**
 * This program takes in a postfix version of an expression and computes the value of the expression
 * @author Nathan White
 * CS2321
 * R02
 */
public class PostfixExpression {
	
	public static int evaluate(String exp) {
		
		LinkedListStack<Integer> stack = new LinkedListStack<>(exp.length()); //creates a new stack object that has the same length as the input string
		int operand1=0; //creates an int to hold the first operand
		int operand2=0; //creates an int to hold the second operand 
		int result=0; //creates an int to hold the result
		String tokens[] = exp.split(" "); //splits the string based off spaces
		
		for(int i=0;i<tokens.length;i++) { //loops until it reaches the length of tokens-1
			
			if(tokens[i].compareTo("+")==0) { //if tokens at i equals "+"
				
				operand2=stack.pop(); //operand 2 is equal to the value on top
				operand1=stack.pop(); //operand 1 is equal to the next
				result=operand1+operand2; //result is the sum of the two operands
				stack.push(result); //pushes the result onto the stack
			}
			else if(tokens[i].compareTo("-")==0) { //if tokens at i equals "-"
				
				operand2=stack.pop();//operand 2 is equal to the value on top
				operand1=stack.pop(); //operand 1 is equal to the next
				result=operand1-operand2; //result is operand1 minus operand2
				stack.push(result); //pushes the result onto the stack
			}
			else if(tokens[i].compareTo("*")==0) { //if tokens at i equals "*"
				
				operand2=stack.pop();//operand 2 is equal to the value on top
				operand1=stack.pop(); //operand 1 is equal to the next
				result=operand1*operand2; //result equals the product of operand1 and operand2
				stack.push(result); //pushes the result onto the stack
			}
			else if(tokens[i].compareTo("/")==0) { //if tokens at i equals "/"
				
				operand2=stack.pop();//operand 2 is equal to the value on top
				operand1=stack.pop(); //operand 1 is equal to the next
				result=operand1/operand2; //result equals the quotient of operand1 and operand2
				stack.push(result); //pushes the result onto the stack
			}
			else { //if it isn't any of those operations
				stack.push(Integer.parseInt(tokens[i])); //pushes on tokens at i
			}
		}
		return stack.top(); //returns the top value of the stack
	}
}
