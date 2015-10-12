/*
Name: Aamir Mansoor
MacID: mansoa4
Student Number: 1406581
Description: This program finds all non-contiguius substrings of a string
*/

public class HWK3_2_mansoa4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Calls with nCont function with the string provided
		nCont(args[0]);
	}
	
	public static void nCont(String in) {
		// Gets the length of elements to sort through
		int n = in.length();
		
		// When n is equal to two there are no more possible
		// non-contiguius substrings so the function returns
		if (n == 2) {
			return;
		}
		
		// i identifies the index right before the space between substrings and j is the location of the space
		// Because the first and last indices cannot be spaces the loops start at 1 and terminate at length -1 
		for (int i = 1; i <= (n-1); i++) {
			//  Moves the location of the space everywhere it can to satisfy being non-contiguius substring 
			for (int j = 1; j <= (n-1)-i; j++) {
				// Prints the non-contiguius substrings in the correct format 
				System.out.println("{" + in.substring(0,j) + ", " + in.substring(i+j, n)+ "}");
			}
		}
		
		//  Recursively calls the function branching between chopping off the first and last elements until length is two
		nCont(in.substring(1,n));
		nCont(in.substring(0,n-1));
	}
}