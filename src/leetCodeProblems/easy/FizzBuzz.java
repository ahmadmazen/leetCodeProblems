package leetCodeProblems.easy;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {
	/*
	 * Write a program that outputs the string representation of numbers from 1 to
	 * n.
	 * 
	 * But for multiples of three it should output “Fizz” instead of the number and
	 * for the multiples of five output “Buzz”. For numbers which are multiples of
	 * both three and five output “FizzBuzz”.
	 * 
	 * Example:
	 * 
	 * n = 15,
	 * 
	 * Return: [ "1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz",
	 * "11", "Fizz", "13", "14", "FizzBuzz" ] Accepted
	 */

	/*
	 * Complexity Analysis Time Complexity: O(N) Space Complexity: O(1)
	 */
	public List<String> fizzBuzz(int n) {
		if (n == 0) {
			return null;
		}
		List<String> output = new ArrayList<String>();
		int i = 1;
		while (i <= n) {
			String str = "";
			if (i % 3 == 0 && i % 5 == 0) {
				str = "FizzBuzz";
			} else if (i % 3 == 0) {
				str = "Fizz";
			} else if (i % 5 == 0) {
				str = "Buzz";
			} else {
				str = i + "";
			}
			output.add(str);

			i++;
		}
		return output;
	}
	/*
	 * Approach 2: String Concatenation Intuition
	 * 
	 * This approach won't reduce the asymptotic complexity, but proves to be a
	 * neater solution when FizzBuzz comes with a twist. What if FizzBuzz is now
	 * FizzBuzzJazz i.e.
	 * 
	 * 3 ---> "Fizz" , 5 ---> "Buzz", 7 ---> "Jazz"
	 */

	public List<String> fizzBuzz_neater(int n) {
		if (n == 0) {
			return null;
		}
		List<String> output = new ArrayList<String>();
		for(int num = 1; num <= n; num++) {
			String numAsString = "";
			boolean divisableBy3 = num % 3 == 0;
			boolean divisableBy5 = num % 5 == 0;
			
			if(divisableBy3) {
				numAsString += "Fizz";
			}
            if(divisableBy5) {
				numAsString += "Buzz";
			}
            if(numAsString.equals("")) {
            	numAsString += Integer.toString(num);
            }
            output.add(numAsString);
		}
		return output;
	}

	public static void main(String[] args) {
		FizzBuzz fb = new FizzBuzz();
		// System.out.println(fb.fizzBuzz(15));
		System.out.println(fb.fizzBuzz_neater(15));
	}
}
