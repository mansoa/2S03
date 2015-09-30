
public class HWK2_mansoa4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Multiplies all the matrices together
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
			// Creates an empty matric with the dimensions of the product
			double[][] c = new double[a.length][b[0].length];
			// Checks if N of the first matrix is the same as M of the second
			if (a[0].length != b.length){
				// If not the matrices cannot be multiplied and it prints error
				System.out.println("Multiplication error.");
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

		for (int i = 0; i < a.length; i++) {
			a[i] = a[i]*scaler;
		}
		return a;
	}
	
	public static double[] addRow(double[] a, double[] b, double scaler) {
		
		for (int j = 0; j < a.length; j++) {
		}
		
		double[] aCop = a.clone();
		double[] bCop = b.clone();
		aCop = multiplyRow(aCop,scaler);
		
		for (int i=0; i < a.length; i++) {
			bCop[i] = bCop[i] - aCop[i];
		}
		return bCop;
	}
	
	public static double[][] matrixInverter(String[] args) {
		double[][] a = matrixMultiplier(args);
		double[][] b = new double[a.length][a.length];
		
		if (a[0].length != a.length) {
			System.out.println("Matrix not invertible");
		}
		
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
		
		for (int i = 0; i < a.length; i++) {
			
			if (a[i][i] == 0) {
				double[] temp = a[i];
				a[i] = a[i+1];
				a[i+1] = temp;
				
				double[] bTemp = b[i];
				b[i] = b[i+1];
				b[i+1] = bTemp;
			}
			
			double divisor = 1/a[i][i];
			
			a[i] = multiplyRow(a[i],divisor);
			b[i] = multiplyRow(b[i],divisor);
			
			for (int j = 0; j < a.length; j++) {
				if (i != j) {
					double scaler = a[j][i];
					
					a[j] = addRow(a[i],a[j],scaler);
					b[j] = addRow(b[i],b[j],scaler);
					
				}
			}	
		}
		
		
		for (int i = 0; i < b.length; i++) {
			int sum = 0;
			for (int j = 0; j < b.length; j++) {
				sum += b[i][j];
			}
			if (sum == 0) {
				System.out.println("Matrix not invertible");
			}
		}
		
		System.out.println(b[0][0] + " " + b[0][1]);
		System.out.println(b[1][0] + " " + b[1][1]);
		return b;
	}	
}