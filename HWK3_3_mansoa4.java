/*
Name: Aamir Mansoor
MacID: mansoa4
Student Number: 1406581
Description: This program solves the subset sum problem
*/


// Imports all java utilities (needed for stacks to work)
import java.util.*;

public class HWK3_3_mansoa4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Assigns the variable n to the length of arguments
		int n = args.length;
		// Gets the value of the last argument converts it to double and saves it to last
		int last = Integer.parseInt(args[n-1]);
		// Converts every argument but the last one to double and saves it to douArgs
		int[] intArgs = new int[n-1];
		for (int i = 0; i < n-1; i++) {
			intArgs[i] = Integer.parseInt(args[i]);
		}
		//  Creates initSum and assigns it the value 0
		int initSum = 0;
		// Creates an empty stack called sumStack
		Stack<Integer> sumStack = new Stack<Integer>();
		// Sets initial position to 0
		int pos = 0;
		
		// Calls the function biSum that prints all of the appropriate  subsets to console
		biSum(intArgs,sumStack,last,initSum,pos);
	}
	
	// Function biSum that solves subset problem
	public static void biSum(int[] args,Stack<Integer> sumStack, 
			int desiredSum, int subSum,int j) {
		
		// If the subset matches the desired sum is uses the print function (declared later) to  print subset to console
		if (subSum == desiredSum) {
			print(sumStack);
		}
		
		// Uses a for loop along with recursion to iterate through every combination of numbers to see if base case is satisfied
		// For loop that runs for  the index number of the value being added to the subset to go though every combo
		for (int i = j; i < args.length; i++) {
			// If initSum is less than or equal to desired sum
			if (subSum <= desiredSum) {
				// Pushes the next number is set to the subset stack
				sumStack.push(args[i]);
				// Adds the value of this number to sum of subset
				subSum += args[i];
				// Calls the function again moving the index up one place
				biSum(args,sumStack,desiredSum,subSum,i+1);
				// Removes the last number in stack and subtracts it from initSum to further test all possible cases
				subSum -= (Integer) sumStack.pop();
			}
		}
		
	}
	
	// Print function that prints the subsets to console
	public static void print(Stack<Integer> toPrint) {
		// Tells java to ignore any compile issues at this point, as java is unsure whether this will work at runtime
		@SuppressWarnings("unchecked")
		// Clones the subset to be printed to avoid referencing errors
		Stack<Integer> temp = (Stack<Integer>)toPrint.clone();
		// Starts by creating string with open curly brackets
		String printing = "{";
		// For the length of the subset
		
		for (int i = 0; i < toPrint.size(); i++) {
			// Gets value of stack by popping clone and adds it to what will be printed along with comma
			printing += String.valueOf(temp.pop()) + ", ";;
		}
		
		// Gets the length of the string to be printed
		int len = printing.length();
		// Clones printing but removes last two characters to remove last comma and adds close bracket
		String finalPrint = printing.substring(0,len-2) + "}";;
		
		// Prints the final print to console
		System.out.println(finalPrint);
	}

}