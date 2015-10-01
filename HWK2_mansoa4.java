//  THIS PROGRAM DOES INVERSION ITERATIVELY AND QUALIFIES FOR EXTRA CREDIT

/*
Name: Aamir Mansoor
MacID: mansoa4
Student Number: 1406581
Description: This program multiplies matrices together and finds the inverse
			 ... of that product.
*/

public class HWK2_mansoa4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Finds the inverse of all the matrices multiplied together
		matrixInverter(args);
		
	}
	
	public static double[][] matrixMaker(int matNum, String[] args){
		
		// Assigns the number of rows to M and columns to N
		int M = Integer.parseInt(args[matNum*2+1]);
		int N = Integer.parseInt(args[matNum*2+2]);
		
		// Determines how many arguments to skip to get to the right values
		// ... to populate the matrix desired
		
		// Skips the arguments that contain the size of matrices 
		int initSkip = Integer.parseInt(args[0]) * 2 + 1; 
		for (int k = 0; k < matNum; k++){
			//  Multiplies M*N and skips all those values (the values that would
			// ... populate the desired matrix)
			initSkip = initSkip + Integer.parseInt(args[k * 2 + 1]) *
					Integer.parseInt(args[k * 2 + 2]);
		}
		
		// Creates 2D array with the desired dimensions
		double [][] matrix = new double [M][N];
		
		// For loop that iterates for the number of rows of matrix
		for (int i = 0; i < M; i++){
			//  Nested loop that iterates through the columns of matrix
			for (int j = 0; j < N; j++) {
				// Populates the matrix with the values listed in args
				matrix[i][j] = Double.parseDouble(args[initSkip + i * N + j]);
			}
		}
		// Returns matrix that has just been made
		return matrix;
	}
	
	public static double[][] matrixMultiplier(String[] args){
		
		// Uses matrixMaker to make the first matrix to be multiplied
		double [][] a = matrixMaker(0,args);
		
		//  Multiplies matrices  together for the total amount of matrices
		for (int i = 0; i < Integer.parseInt(args[0]) - 1;i++) {
			// Creates the second matrix to be multiplied
			double [][] b = matrixMaker(i + 1,args);
			// Creates an empty matrix with the dimensions of the product
			double[][] c = new double[a.length][b[0].length];
			// Checks if N of the first matrix is the same as M of the second
			if (a[0].length != b.length){
				// If not the matrices cannot be multiplied and it prints error
				System.out.println("Multiplication error.");
				// Creates a 2D array with length 0
				double[][] temp = new double [0][0];
				// Returns blank array to stop rest of function from running
				return temp;
			}
			else {
				// Iterates for the # of rows of the first matrix
				for (int j = 0; j < a.length; j++) {
					// Iterates for the # of columns of matrix two
					for (int k = 0; k < b[0].length; k++){
						// Iterates for the # of rows of matrix one
						for (int l = 0; l < a[0].length; l++) {
							// Performs the operation to multiply the matrices
							c[j][k] += a[j][l] * b[l][k];
						}	
					}
				}
				// Assigns the product of the multiplication to the first matrix
				// ... to be multiplied in the subsequent iteration
				a = c;
			}
		}
		
		// USED FOR DEBUG PLEASE IGNORE
		
		//System.out.println(a[0][0] + " " + a[0][1]);
		//System.out.println(a[1][0] + " " + a[1][1]);
		
		// Returns the  final product of all matrices
		return a;
	}
	
	//  Creates a function that takes in the row to be multiplied...
	// ... and the scaler to multiply the row by.
	public static double[] multiplyRow(double[] a, double scaler) {

		// Iterates through all the elements in a row and multiplies by scaler
		for (int i = 0; i < a.length; i++) {
			a[i] = a[i]*scaler;
		}
		
		// Returns the multiplied row 
		return a;
	}
	
	// Function that performs row addition that takes in the row to be added (b)
	// ... the row being used to add to a (b) and the scaler that makes it so
	// ... you zero out the desired term when solving for RREF
	public static double[] addRow(double[] a, double[] b, double scaler) {

		//  Clones a and b to avoid referencing issues
		double[] aCop = a.clone();
		double[] bCop = b.clone();
		
		// Uses multiply row to multiply a by the scaler
		aCop = multiplyRow(aCop,scaler);
		
		// Iterates through every element in b and subtracts (adds a negative)
		// ... accordingly to provide the desired row addition
		for (int i=0; i < a.length; i++) {
			bCop[i] = bCop[i] - aCop[i];
		}
		
		// Returns the added row
		return bCop;
	}
	
	// Function that inverts a matrix that takes in only arguments.
	public static double[][] matrixInverter(String[] args) {
		// Multiples all the matrices together using matrixMultiplier
		double[][] a = matrixMultiplier(args);
		// If the length of multiplied matrix is 0 (empty) then it was not
		// ... multiplied successfully
		if (a.length == 0) {
			// Returns to stop function from completing
			return a;
		}
		// Creates a new blank matrix with the same dimensions as a
		double[][] b = new double[a.length][a.length];
		
		// Only square matrices can be inverted, so if a is not square a message
		// ... goes to the console saying the Matrix is not Invertible 
		if (a[0].length != a.length) {
			System.out.println("Matrix not invertible");
			return a;
		}
		
		//  If it is invertible this nested for loop creates an identity matrix
		// ... with the same dimensions as a
		else {
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < a.length; j++) {
					if (i == j) {
						b[i][j] = 1;
					}
					else {
						b[i][j] = 0;
					}
				}
			}
		}
		
		// Begins to perform RREF one column at a time
		for (int i = 0; i < a.length; i++) {
			
			// If there is a zero about the main diagonal then this swaps rows
			// ... with the row below 
			if (a[i][i] == 0) {
				
				// Performs swap by saving to temp. then swapping (same for b)
				double[] temp = a[i];
				a[i] = a[i+1];
				a[i+1] = temp;
				
				double[] bTemp = b[i];
				b[i] = b[i+1];
				b[i+1] = bTemp;
			}
			
			// Saves the divisor to make main diagonal 1 to a variable
			double divisor = 1/a[i][i];
			
			// Multiplies both a and b by the divisor
			a[i] = multiplyRow(a[i],divisor);
			b[i] = multiplyRow(b[i],divisor);
			
			// Runs through every column of the code making everything not along
			// .. main diagonal 0 (by using addRow)
			for (int j = 0; j < a.length; j++) {
				// Skips making this element 0 if its on the main diagonal
				if (i != j) {
					// Determines the saler to use to add rows
					double scaler = a[j][i];
					
					// Performs the same addition operation on a as b
					a[j] = addRow(a[i],a[j],scaler);
					b[j] = addRow(b[i],b[j],scaler);
					
				}
			}	
		}
		
		// Checks to see of any row sum = 0, if so the matrix is singular and 
		// ... the correct message is printed to the console
		for (int i = 0; i < b.length; i++) {
			double sum = 0;
			for (int j = 0; j < b.length; j++) {
				//  Rounds values to two decimal places
				b[i][j] = (double)Math.round(b[i][j]*100)/100;
				
				// Checks to see if the value of b is 0
				if (b[i][j] == 0) {
					// If so then it adds one to the sum
					sum += 1;
				}
			}
			// If the sum is the same as the length of b (row of zeros)
			if (sum == b.length) {
				// Prints the matrix is not invertible
				System.out.println("Matrix not invertible");
				// Returns a to stop function running
				return a;
			}
		}

		// For loops that runs for the amount of rows in b
		for (int i = 0; i < b.length; i ++) {
			// For loop that runs for the amount of columns in b
			for (int j = 0; j < b.length; j++) {
				// Prints every value to the console separated by a space
				System.out.print(b[i][j] + " ");
			}
		}
		
		// Returns the inverted matrix
		return b;
	}	
}