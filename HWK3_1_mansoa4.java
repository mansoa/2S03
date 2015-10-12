/*
Name: Aamir Mansoor
MacID: mansoa4
Student Number: 1406581
Description: This program calculates binomial coefficients
*/

public class HWK3_1_mansoa4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Assigns a the value of the binomial coefficient
		int a = binomialCo(Integer.parseInt(args[0]),
				Integer.parseInt(args[1]));
		// Prints the coefficient to the console
		System.out.println(a);
		
	}
	public static java.lang.Integer binomialCo(double n,double k) {
		// Using the formula for binomial coefficients
		if (1 <= k && k <= n-1) {
			//  If k is  between 1 and n-1 then it recursively
			//  calls the binaomalCO function and adds it
			//  according to the  binomial formula
			return binomialCo(n-1,k-1) + binomialCo(n-1,k);
		}
		else if (n == k || k == 0) {
			// If n = k or 0 then the coefficient is 1
			return 1;
		}
		else {
			// If no condition is  satisfied then null is 
			// returned
			return null;
		}
	}

}
