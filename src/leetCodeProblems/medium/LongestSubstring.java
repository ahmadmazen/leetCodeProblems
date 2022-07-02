package leetCodeProblems.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstring {
//this solution didn't pass all the 900s test cases (only 400) less than half
	public int lengthOfLongestSubstring_bad(String s) {

		StringBuilder sub = new StringBuilder();
		Set<String> set = new HashSet<>();

		if (s == null || s.isEmpty()) {
			return 0;
		}
		char[] array = s.toCharArray();
		String prvChar = "";
		int maxSub = 0;
		for (int x = 0; x < array.length; x++) {
			String currChar = String.valueOf(array[x]);

			if (!currChar.equals(prvChar) && !set.contains(currChar)) {
				sub.append(array[x]);
				set.add(currChar);

			} else {

				sub.delete(0, (sub.length()));
				sub.append(currChar);
				set.clear();
				set.add(currChar);
			}
			maxSub = Math.max(sub.length(), maxSub);
			prvChar = currChar;

		}
		return maxSub;
	}

	// brute force solution
	/*
	 * O(n^3)
	 * 
	 * Space complexity : O(min(n, m)). We need O(k) space for
	 * checking a substring has no duplicate characters, where k is the size of the
	 * Set. The size of the Set is upper bounded by the size of the string n and
	 * the size of the charset/alphabet m.
	 */
	public int lengthOfLongestSubstring_1(String s) {
		int n = s.length();
		int ans = 0;
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j <= n; j++)
				if (allUnique(s, i, j))
					ans = Math.max(ans, j - i);
		return ans;
	}

	public boolean allUnique(String s, int start, int end) {
		Set<Character> set = new HashSet<>();
		for (int i = start; i < end; i++) {
			Character ch = s.charAt(i);
			if (set.contains(ch))
				return false;
			set.add(ch);
		}
		return true;
	}

	/*
	 * sliding window solution
	 * 
	 */
	 public int lengthOfLongestSubstring_2(String s) {
	        int n = s.length();
	        Set<Character> set = new HashSet<>();
	        int ans = 0, i = 0, j = 0;
	        while (i < n && j < n) {
	            // try to extend the range [i, j]
	            if (!set.contains(s.charAt(j))){
	                set.add(s.charAt(j++));
	                ans = Math.max(ans, j - i);
	            }
	            else {
	                set.remove(s.charAt(i++));
	            }
	        }
	        return ans;
	    }
	 
	 public int lengthOfLongestSubstring(String s) {
	        int n = s.length(), ans = 0;
	        Map<Character, Integer> map = new HashMap<>(); // current index of character
	        // try to extend the range [i, j]
	        for (int j = 0, i = 0; j < n; j++) {
	            if (map.containsKey(s.charAt(j))) {
	                i = Math.max(map.get(s.charAt(j)), i);
	            }
	            ans = Math.max(ans, j - i + 1);
	            map.put(s.charAt(j), j + 1);
	        }
	        return ans;
	    }
	public static void main(String[] args) {
		LongestSubstring ls = new LongestSubstring();
//		System.out.println(ls.lengthOfLongestSubstring_2("abcabcbb"));
//		System.out.println(ls.lengthOfLongestSubstring_2("bbbbb"));
//		System.out.println(ls.lengthOfLongestSubstring_2("pwwkew"));
//		System.out.println(ls.lengthOfLongestSubstring_2("dvdf"));
		System.out.println(ls.lengthOfLongestSubstring("abcabcbb"));
//		System.out.println(ls.lengthOfLongestSubstring("bbbbb"));
//		System.out.println(ls.lengthOfLongestSubstring("pwwkew"));
//		System.out.println(ls.lengthOfLongestSubstring("dvdf"));
		
	}

}
