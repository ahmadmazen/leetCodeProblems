package leetCodeProblems.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FizzBuzzJazz {
	/*
	 * This approach is an optimization over approach 2. When the number of mappings
	 * are limited, approach 2 looks good. But what if you face a tricky interviewer
	 * and he decides to add too many mappings?
	 * 
	 * Having a condition for every mapping is not feasible or may be we can say the
	 * code might get ugly and tough to maintain.
	 * 
	 * What if tomorrow we have to change a mapping or may be delete a mapping? Are
	 * we going to change the code every time we have a modification in the
	 * mappings?
	 * 
	 * We don't have to. We can put all these mappings in a Hash Table.
	 * 
	 * Algorithm
	 * 
	 * Put all the mappings in a hash table. The hash table fizzBuzzHash would look
	 * something like { 3: 'Fizz', 5: 'Buzz' } Iterate on the numbers from 1
	 * ...N1...N. For every number, iterate over the fizzBuzzHash keys and check for
	 * divisibility. If the number is divisible by the key, concatenate the
	 * corresponding hash value to the answer string for current number. We do this
	 * for every entry in the hash table. Add the answer string to the answer list.
	 * This way you can add/delete mappings to/from to the hash table and not worry
	 * about changing the code.
	 * 
	 * So, for FizzBuzzJazz the hash table would look something like { 3: 'Fizz', 5:
	 * 'Buzz', 7: 'Jazz' }
	 */
	public List<String> fizzBuzz(int n) {
		List<String> output = new ArrayList<String>();
		HashMap<Integer, String> fizzBuzzDict = new HashMap<Integer, String>() {
			{
				put(3, "Fizz");
				put(5, "Buzz");
				put(7, "Jazz");
			}
		};
		for (int num = 1; num <= n; num++) {
			String numAsString = "";

			// If the num is divisible by key,
			// then add the corresponding string mapping to current numAsString
			for (Integer key : fizzBuzzDict.keySet()) {
				if (num % key == 0) {
					numAsString += fizzBuzzDict.get(key);
				}
			}

			if (numAsString.equals("")) {
				numAsString += Integer.toString(num);
			}
			output.add(numAsString);
		}

		return output;

	}

	public static void main(String[] args) {
		FizzBuzzJazz fb = new FizzBuzzJazz();
		System.out.println(fb.fizzBuzz(15));
	}

}
