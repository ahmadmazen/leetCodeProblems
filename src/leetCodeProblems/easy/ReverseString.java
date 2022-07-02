package leetCodeProblems.easy;

public class ReverseString {
	/*
	 * Write a function that reverses a string. The input string is given as an
	 * array of characters char[].
	 * 
	 * Do not allocate extra space for another array, you must do this by modifying
	 * the input array in-place with O(1) extra memory.
	 * 
	 * You may assume all the characters consist of printable ascii characters.
	 * 
	 * 
	 * 
	 * Example 1:
	 * 
	 * Input: ["h","e","l","l","o"] Output: ["o","l","l","e","h"] Example 2:
	 * 
	 * Input: ["H","a","n","n","a","h"] Output: ["h","a","n","n","a","H"]
	 */

	static char[] reverse(char[] originalSequence) {

		int length = originalSequence.length;
		int iterations = length / 2;
		for (int i = 0; i < iterations; i++) {
			char temp = originalSequence[length - i - 1];
			// System.out.println(i + " temp =" + temp);
			originalSequence[length - i - 1] = originalSequence[i];
			originalSequence[i] = temp;
		}
		// System.out.println(originalSequence);
		return originalSequence;
	}

	public static void main(String[] args) {
//		System.out.println(reverse(new char[] {'h','e','l','l','o'}));
//		System.out.println(reverse(new char[] {'H','a','n','n','a','h'}));
		System.out.println(reverse(new char[] { 'A', ' ', 'm', 'a', 'n', ',', ' ', 'a', ' ', 'p', 'l', 'a', 'n', ',',
				' ', 'a', ' ', 'c', 'a', 'n', 'a', 'l', ':', ' ', 'P', 'a', 'n', 'a', 'm', 'a' }));

	}

	/*
	 * Analysis of my solution Complexity Analysis
	 * 
	 * Time complexity : O(N) to swap N/2 element.
	 * 
	 * Space complexity :O(1), it's a constant space solution.
	 */
	// Another good approach in solving ..
	/*
	 * Algorithm
	 * 
	 * Set pointer left at index 0, and pointer right at index n - 1, where n is a
	 * number of elements in the array.
	 * 
	 * While left < right:
	 * 
	 * Swap s[left] and s[right].
	 * 
	 * Move left pointer one step right, and right pointer one step left.
	 */
	public void reverseString(char[] s) {
		int left = 0, right = s.length - 1;
		while (left < right) {
			char tmp = s[left];
			s[left++] = s[right];
			s[right--] = tmp;
		}
	}
	
}
