package leetCodeProblems.topamazonproblemsmedium;

import java.util.HashSet;
import java.util.Set;

public class StringSegmentation {
	/*
	 * https://www.educative.io/m/string-segmentation You are given a dictionary of
	 * words and a large input string. You have to find out whether the input string
	 * can be completely segmented into the words of a given dictionary. The
	 * following two examples elaborate on the problem further.
	 * 
	 * The runtime complexity of this solution is exponential, O(2^n), if we use only recursion.
	 * With memoization, the runtime complexity of this solution can be improved to be polynomial, O(n^2)
	 */
	public static boolean canSegmentString(String s, Set<String> dictionary) {

//		Solution Breakdown
//		You can solve this problem by segmenting the large string at each possible position to see 
//		if the string can be completely segmented to words in the dictionary. 
//		If you write the algorithm in steps it will be as follows:
		
//		The algorithm will compute two strings from scratch in each iteration of the loop.
//		Worst case scenario, there would be a recursive call of the second_word each time. 
//		This shoots the time complexity up to O(2^n)
//		You can see that you may be computing the same substring multiple times, 
//		even if it doesn’t exist in the dictionary. This redundancy can be fixed by memoization,
//		where you remember which substrings have already been solved.
//
//		To achieve memoization, you can store the second string in a new set each time. 
//		This will reduce both time and memory complexities.
		
		int len = s.length();
		for (int i = 1; i <= len; i++) {
			String firstWord = s.substring(0, i);
			if (dictionary.contains(firstWord)) {
				String secondWord = s.substring(i);
				if (secondWord == null || secondWord.length() == 0 || dictionary.contains(secondWord)
						|| canSegmentString(secondWord, dictionary)) {
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Set<String> dictionary = new HashSet<String>();
		String s = new String();
		s = "hellonow";

		dictionary.add("hello");
		dictionary.add("hell");
		dictionary.add("on");
		dictionary.add("now");
		if (canSegmentString(s, dictionary)) {
			System.out.println("String Can be Segmented");
		} else {
			System.out.println("String Can NOT be Segmented");
		}
	}
}
