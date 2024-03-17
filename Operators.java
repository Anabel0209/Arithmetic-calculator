//---------------------------------------------------------------------------------------------
//Assignment 2
//written by: Anabel Prévost 40265371
//For COMP 352 - fall 2024
//---------------------------------------------------------------------------------------------
//Class that contains static method giving informations about different types of operators
//such as their precedence and the operations they should execute

public class Operators {
	
	//return the precedence of an operator 
	public static int getPrecedence(String anOperator)
	{
		if (anOperator.equals("$") || anOperator.equals(")"))
		{
			return 10;
		}
		else if (anOperator.equals("("))
		{
			return 1;
		}
		else if(anOperator.equals("^"))
		{
			return 2;
		}
		else if (anOperator.equals("*") || anOperator.equals("/"))
		{
			return 3;
		}
		else if(anOperator.equals("+") || anOperator.equals("-"))
		{
			return 4;
		}
		else if(anOperator.equals(">") || anOperator.equals(">=") || anOperator.equals("<") || anOperator.equals("<="))
		{
			return 5;
		}
		else if(anOperator.equals("==") || anOperator.equals("!="))
		{
			return 6;
		}
		else 
			return 10;
	}
	
	//return a string of the resulting operation done on x and y
	public static String doOperation(String x, String y, String op)
	{

		String result = "";
		
		//equations resulting in an integer 
		if (op.equals("^"))
		{
			result = "" + Math.pow(Double.parseDouble(y),Double.parseDouble(x));
		}
		else if (op.equals("*"))
		{
			result = "" + (Double.parseDouble(y) * Double.parseDouble(x));
		}
		else if (op.equals("/"))
		{
			result = "" + (Double.parseDouble(y) / Double.parseDouble(x));
		}
		else if (op.equals("+"))
		{
			result = "" + (Double.parseDouble(y) + Double.parseDouble(x));
		}
		else if (op.equals("-"))
		{
			result = "" + ((Double.parseDouble(y) - Double.parseDouble(x)));
		}
		
		//equations resulting in a boolean comparing two integers
		else if (op.equals("<"))
		{
			result = "" + (Double.parseDouble(y) < Double.parseDouble(x));
		}
		else if (op.equals("<="))
		{
			result = "" + (Double.parseDouble(y) <= Double.parseDouble(x));
		}
		else if (op.equals(">"))
		{
			result = "" + (Double.parseDouble(y) > Double.parseDouble(x));
		}
		else if (op.equals(">="))
		{
			result = "" + (Double.parseDouble(y) >= Double.parseDouble(x));
		}
		
		//equations resulting in a boolean comparing two boolean or two numbers
		else if (op.equals("=="))
		{
			//if we compare 2 numbers (start by a number or a negative sign)
			if((y.substring(0,1).matches("\\d+") || y.substring(0,1).equals("-") )&& (x.substring(0,1).matches("\\d+") || x.substring(0,1).equals("-")))
			{
				result = "" + (Double.parseDouble(y) == Double.parseDouble(x));
			}
			
			//if we compare 2 boolean
			else
			{
				result = "" + (Boolean.parseBoolean(y) == Boolean.parseBoolean(x));
			}
			
		}
		else if (op.equals("!="))
		{
			//if we compare 2 numbers
			if((y.substring(0,1).matches("\\d+") || y.substring(0,1).equals("-") )&& (x.substring(0,1).matches("\\d+") || x.substring(0,1).equals("-")))
			{
				result = "" + (Double.parseDouble(y) == Double.parseDouble(x));
			}
			
			//if we compare 2 boolean
			else
			{
				result = "" + (Boolean.parseBoolean(y) != Boolean.parseBoolean(x));
			}
		}
		else
			result = "";
		
		return result;
	}
	
}
