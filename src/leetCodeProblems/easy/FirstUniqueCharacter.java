package leetCodeProblems.easy;

import java.util.HashMap;
import java.util.Map;

public class FirstUniqueCharacter {
	/*
	 * Time complexity : O(N) since we go through the string of length
	 * N two times. Space complexity : O(1) because English alphabet
	 * contains 26 letters.
	 */
	public int firstUniqChar(String s) {
		if (s == null || s.trim().equals("")) {
			return -1;
		}
		// build hash map of the characters and their occurences
		Map<Character, Integer> count = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			char character = s.charAt(i);
			count.put(character, count.getOrDefault(character, 0) + 1);
		}

		for (int i = 0; i < s.length(); i++) {
			if (count.get(s.charAt(i)) == 1) {
				return i;
			}
		}
		return -1;
	}
}
