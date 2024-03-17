//---------------------------------------------------------------------------------------------
//Assignment 2
//written by: Anabel Prévost 40265371
//For COMP 352 - fall 2024
//---------------------------------------------------------------------------------------------
//This program calculates arithmetic expression by using stacks implemented by expandable array

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class ArithmeticCalculator {
	
	//main
	public static void main(String[] args) {
		
		//delcare the Scanner and PrintWriter objects
		Scanner read = null;
		PrintWriter pw = null;;
		
		//variables
		String expression = "";

		
		//try to open a stream with the file to read
		try
		{
			read = new Scanner(new FileInputStream("arithmeticExpression.txt"));
			pw = new PrintWriter(new FileOutputStream("arithmeticOutput.txt"));
		}
		catch(FileNotFoundException e)
		{
			//display message if there was an error establishing the stream
			System.out.println("the file could not be found/open");
		}
		
		//read each lines of the file
		while (read.hasNextLine())
		{
			//extract the expression of the line
			expression = read.nextLine();
			
			//print the expression and the solution in a file
			pw.printf("%-50s%s%n", expression, doArithmeticCalc(expression));
	
		}		
		
		//close the PwintWriter object
		pw.close();
		
	}//end main
	
	
	//Method that do the arithmetic calculation
	public static String doArithmeticCalc(String expression)
	{
		String partOfEx;

			//create a stack for the values and the operators using the stackArr data structure
			StackArr<String> valStack = new StackArr<String>(1);
			StackArr<String> opStack= new StackArr<String>(1);
			
			//declare variables
			String finalAnswer = "";
			int counter = 0;	//keep count of the number of operators/ values extracted from the expression
			int counter2 = 0;	//keep track of the index in the string we are at
			
			//Create a Scanner to read from the arithmetic expression
			Scanner readExpression = new Scanner(expression);
			
			//delimit the expression at spaces character
			readExpression.useDelimiter(" ");
			
			//while the expression has not done being evaluated 
			while(readExpression.hasNext())
			{
				//read a part, value or operator
				partOfEx = readExpression.next();
				
				//increment the counter2 (add the length of the expression read + 1 to count for the space that follows it)
				counter2 += partOfEx.length() + 1;
				
				//if what is being read is a value 
				if (partOfEx.substring(0,1).matches("\\d+"))
				{
					//increment the counter that keep track of the number of values/ operators seen 
					counter++;
					
					//push the value in the value stack
					valStack.push(partOfEx);
				}
				
				//if what is being read is an operator
				else
				{
					//increment the counter that keep track of the number of values/ operators seen 
					counter ++;
					
					//if the operator being passed is an open parenthesis
					if (partOfEx.equals("("))
					{
						//recursive call on the substring of the original expression starting counter2
						String resultParentheses = doArithmeticCalc(expression.substring(counter2));
						
						//split the string returned
						String[] mySplitString = resultParentheses.split(" "); 
						
						//extract the result
						String resultToPush = mySplitString[0];
						
						//extract the counter
						int startFrom = Integer.parseInt(mySplitString[1]);
						
						//extract the counter2
						counter2 += Integer.parseInt(mySplitString[2]);
						
						//push the result into the original stack
						valStack.push(resultToPush);
						
						//loop the nb of time an operator and value have been seen in the recursive call and read those values,
						//so that they are not evaluated them in the main while loop
						for (int i = 0; i < startFrom; i++)
						{
							readExpression.next();
						}
						
						//increment counter adding it the counter from the recursive call
						counter = counter + startFrom;
		
					}
					
					//if the operator being evaluated is not a close parenthesis
					else if(!partOfEx.equals(")"))
					{
						//do operation if applicable then push the operator in the operator stack
						finalAnswer = repeatAndDoOps(partOfEx, opStack, valStack);
						opStack.push(partOfEx);

					}
					//if the operator is a close parenthesis
					else
					{
						//do the remaining operations
						finalAnswer = repeatAndDoOps(partOfEx, opStack, valStack);

						//if the final answer is not empty (the process worked correctly) return a string with
						//information about the result, and what the 2 counters contain
						if (!finalAnswer.equals("")) 
						{
							return "" + finalAnswer + " "  + counter + " " + counter2;
						}
						//will trigger an error message in console
						else 
						{
							System.err.println("There was an error in the calculation of an expression inside the parenthesis");
							return "--";
						}
					}
				}
			}

			//when there is no more part of the expression to read, push the $ symbol into the operator stack to empty it
			finalAnswer = repeatAndDoOps("$", opStack, valStack);

			if (!finalAnswer.equals("")) 
			{
				return "" + finalAnswer;
			}
			else 
				return "--";
	}
	
	//return the sole value in the stack if the index of the last value in the value stack is at 0 and the ref op is $ or )
	//else return an empty string if the conditions are not respected
	public static String repeatAndDoOps(String refOp, StackArr<String> opStack, StackArr<String> valStack)
	{
		String x = "";
		String y = "";
		String op = "";
		String finalResult = "";
		
		//execute as long as an operator is in the op stack and that the ref op has a higher precedence than the other op in the stack
		while(opStack.getIndexLastVal() >= 0 && Operators.getPrecedence(refOp) >= Operators.getPrecedence (opStack.peek()))
		{
			//if one operator is in the opStack, two values in the valStack and the refOp is either $ or )
			if(opStack.getIndexLastVal() == 0 && (refOp.equals("$") || refOp.equals(")")) && valStack.getIndexLastVal() == 1)
			{
				//pop the last and the before last value
				//pop an operator and do the operation on the 2 values
				x = valStack.pop();
				y = valStack.pop();
				op = opStack.pop();
					
				//return the final answer to the process/ subprocess of the arithmetic calculation
				return "" + Operators.doOperation(x, y, op);

			}
			
			//do op on x and y
			x = valStack.pop();
			y = valStack.pop();
			op = opStack.pop();
			
			//push the result of the operation in the value stack 
			valStack.push(Operators.doOperation(x, y, op));
			finalResult =  "";

		}

		return finalResult;
	}

	
}//end class



