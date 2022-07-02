package leetCodeProblems.easy;

import java.util.Arrays;

public class FindMissingNumber {
	/*
	 * time complexity : O(n)
	 * space complexity: O(1)
	 * this approach though it's better in time complexity: linear time but it can produce overflow error with the
	 * large numbers
	 */
	
	private static int findMissingNumber(int[] arr) {
		
		// the actual size is `n+1` since a number is missing from the array
       int actual_length = arr.length + 1;
       // get a sum of integers between 1 and `n+1`
       int expected_sum = (actual_length * (actual_length + 1)) / 2;
       // get an actual sum of integers in the array
	   int actual_sum = Arrays.stream(arr).sum();	
	   // the missing number is the difference between the expected sum
       // and the actual sum
		return expected_sum - actual_sum;
		
	}
	private static int findMissingNumberXor(int[] arr) {
		//compute the xor of the array elements
	       int xor = 0;
	       for(int e : arr) {
	    	   xor = xor ^ e;
	       }
	       
	       //compute the xor of all elements of array from 1 to n + 1
	       for(int x = 0; x <= arr.length + 1; x++) {
	    	   xor = xor ^ x;
	       }
	       
	      return xor;
			
		}
	
	
	public static void main(String[] args) {
		 int[] arr = { 1, 2, 3, 4, 5, 7, 8, 9, 10 };
		 
	        System.out.println("The missing number is : " + findMissingNumber(arr));
	        System.out.println("The missing number is : " + findMissingNumberXor(arr));
	}

}
