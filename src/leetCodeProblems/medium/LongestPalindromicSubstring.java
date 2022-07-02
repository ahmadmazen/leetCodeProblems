package leetCodeProblems.medium;

import java.util.Map;

/*
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

	Example 1:
	
	Input: "babad"
	Output: "bab"
	Note: "aba" is also a valid answer.
	Example 2:
	
	Input: "cbbd"
	Output: "bb"
 */
public class LongestPalindromicSubstring {
	// this is my solution but the bad news it's brute force
	/*
	 * pick all possible starting and ending positions for a substring, and verify
	 * if it is a palindrome. time complexity I think in the worst case will be
	 * O(n^3), and this is not efficient, space complexity is O(1)
	 */
	/*
	 * another way brute-force https://www.youtube.com/watch?v=UflHuQj6MVA
	 */
	public String longestPalindrome_bruteforce(String s) {
		if (s.length() == 0) {
			return s;
		}
		int lastIndex = s.length() - 1;

		String longestSubstring = "";
		for (int i = 0; i < s.length(); i++) {
			int windowStart = i;
			int windowEnd = lastIndex;
			while (windowStart <= windowEnd) {
				String currentSubstring = s.substring(windowStart, windowEnd + 1);
				if (isPalindromic(currentSubstring)) {
					longestSubstring = longestSubstring.length() < currentSubstring.length() ? currentSubstring
							: longestSubstring;
					break;
				}
				windowEnd--;

			}
			if (lastIndex - i < longestSubstring.length()) {
				break;
			}
		}

		return longestSubstring;

	}

	boolean isPalindromic(String s) {
		if (s.length() == 0) {
			return true;
		}
		int left = 0;
		int right = s.length() - 1;
		while (left <= right) {
			if (s.charAt(left) != s.charAt(right)) {
				return false;
			}
			right--;
			left++;
		}
		return true;
	}

	/*
	 * another approach: recursive yet it's not efficient
	 */
	// Function to find the length of Longest Palindromic Subsequence
	// of substring X[i..j]
	public static int longestPalindrome_recursive(String X, int i, int j) {
		// base condition
		if (i > j) {
			return 0;
		}

		// if String X has only one character, it is palindrome
		if (i == j) {
			return 1;
		}

		// if last character of the string is same as the first character
		if (X.charAt(i) == X.charAt(j)) {
			// include first and last characters in palindrome
			// and recur for the remaining substring X[i+1, j-1]
			return longestPalindrome_recursive(X, i + 1, j - 1) + 2;
		}

		// if last character of string is different to the first character

		// return maximum of -
		// 1. Remove last character & recur for the remaining
		// substring X[i, j-1]
		// 2. Remove first character & recur for the remaining
		// substring X[i+1, j]
		return Integer.max(longestPalindrome_recursive(X, i, j - 1), longestPalindrome_recursive(X, i + 1, j));
	}

	/*
	 * Second approach: Dynamic programming Time complexity O(n^2) and auxiliary
	 * space O(n^2)
	 */
	public static int longestPalindrome_dp(String X, int i, int j, Map<String, Integer> lookup) {
// base condition
		if (i > j) {
			return 0;
		}

// if String X has only one character, it is palindrome
		if (i == j) {
			return 1;
		}

// construct an unique map key from dynamic elements of the input
		String key = i + "|" + j;

// if sub-problem is seen for the first time, solve it and
// store its result in a map
		if (!lookup.containsKey(key)) {
			/*
			 * if last character of the string is same as the first character include first
			 * and last characters in palindrome and recur for the remaining subString
			 * X[i+1, j-1]
			 */

			if (X.charAt(i) == X.charAt(j)) {
				lookup.put(key, longestPalindrome_dp(X, i + 1, j - 1, lookup) + 2);
			} else {

				/*
				 * if last character of string is different to the first character
				 * 
				 * 1. Remove last char & recur for the remaining subString X[i, j-1] 2. Remove
				 * first char & recur for the remaining subString X[i+1, j] 3. Return maximum of
				 * the two values
				 */

				lookup.put(key, Integer.max(longestPalindrome_dp(X, i, j - 1, lookup),
						longestPalindrome_dp(X, i + 1, j, lookup)));
			}
		}

// return the sub-problem solution from the map
		return lookup.get(key);
	}

	/*
	 * Approach 4: Expand Around Center e observe that a palindrome mirrors around
	 * its center. Therefore, a palindrome can be expanded from its center, and
	 * there are only 2n - 1 such centers. You might be asking why there are 2n - 1
	 * but not n centers? The reason is the center of a palindrome can be in between
	 * two letters. Such palindromes have even number of letters (such as "abba")
	 * and its center are between the two 'b's.
	 * more explanation by me===============
	 * abba you will need for each letter to expand once for the odd center possibilities (i, i) 
	 * and once more for the even center possibilities (i, i + 1)
	 * calculate by muliply the n(length) * 2 you will get 2n, 
	 * but if you notice that the last character in the string will have one and one only expansion 
	 * because
	 * there won't be any i + 1 (even expansion possibilities), and that what resulted the
	 * 2n - 1 centers mentioned earlier.   
	 * ==========================================
	 * Complexity Analysis
	 * 
	 * Time complexity : O(n^2). Since expanding a palindrome around its center
	 * could take O(n) time, the overall complexity is O(n^2).
	 * 
	 * Space complexity : O(1).
	 */
	public String longestPalindrome(String s) {
		if (s == null || s.length() < 1)
			return "";
		int start = 0, end = 0;
		for (int i = 0; i < s.length(); i++) {
			// Find the longest odd length palindrome with center point as i
			int len1 = expandAroundCenter(s, i, i);  
			// Find the longest even length palindrome with center points as i + 1 and i. 
			int len2 = expandAroundCenter(s, i, i + 1);
			int len = Math.max(len1, len2);
			if (len > end - start) {
				start = i - (len - 1) / 2;
				end = i + len / 2;
			}
		}
		return s.substring(start, end + 1);
	}

	private int expandAroundCenter(String s, int left, int right) {
		int L = left, R = right;
		while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
			L--;
			R++;																																																																	
		}
		return R - L - 1;
	}

	/*
	 * Approach 5: Manacher's Algorithm There is even an O(n) algorithm called
	 * Manacher's algorithm, explained here in detail. However, it is a non-trivial
	 * algorithm, and no one expects you to come up with this algorithm in a 45
	 * minutes coding session. But, please go ahead and understand it, I promise it
	 * will be a lot of fun.
	 */
	
	public static void main(String[] args) {
		LongestPalindromicSubstring lp = new LongestPalindromicSubstring();
		System.out.println(lp.longestPalindrome("babad"));
		System.out.println(lp.longestPalindrome("cbbd"));
		System.out.println(lp.longestPalindrome("a"));
	}

}																			
