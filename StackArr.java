//---------------------------------------------------------------------------------------------
//Assignment 2
//written by: Anabel Prévost 40265371
//For COMP 352 - fall 2024
//---------------------------------------------------------------------------------------------
//This class defines the Stack data structure that is implemented with expandable arrays
//it is a generic class that let the user create stacks of any types

public class StackArr<T> {
	
	//attributes
	private Object[] array;
	private int indexLastValue = -1;	//keep track of the index of the last element added to the stack
	
	//constructor 
	StackArr(int size)
	{
		this.array = new Object[size];
		this.indexLastValue = -1;
	}
	
	//pop the last value of the stackArr and update the index of the last element
	public T pop()
	{
		final T valueInArr = (T)array[indexLastValue];
		indexLastValue--;
		return valueInArr;
	}
	
	//push a value into the stackArr and update the index of the last element
	public void push(T aValue) 
	{
		//if the position to be of the element to add is out of bound of our current array
		if (indexLastValue + 1 >= array.length)
		{
			//create an array double the size of the current one
			Object[] tempArray = new Object[2 * array.length];
			
			//copy each elements of the old array into the new one
			for(int i=0; i<= array.length-1; i++)
			{
				tempArray[i] = array[i];
			}
			
			//assign the new array to the old pointer
			array = tempArray;
			
			//add to the new array the desired value
			array[indexLastValue + 1] = aValue;
			
			//update the pointer of the last element added in the array 
			indexLastValue ++;
		}
		
		//if there is still space in our current array
		else 
		{
			//add the desired value in the array
			array[indexLastValue + 1] = aValue;
			
			//update the index of the last element added in the array
			indexLastValue ++;
		}
	}
	
	//return the length of the array
	public int getLength()
	{
		return array.length;
	}

	//display the elements in the array (used for debugging purpose)
	public void display()
	{
		for(int i=0; i<=indexLastValue; i++)
		{
			System.out.println((T)array[i]);
		}
	}
	
	//return the index of the last value
	public int getIndexLastVal()
	{
		return indexLastValue;
	}
	
	//return the value of the last element added (on top of the "stack")
	public T peek()
	{
		return (T)array[indexLastValue];
	}

}
