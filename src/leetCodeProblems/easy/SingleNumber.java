package leetCodeProblems.easy;

import java.util.HashMap;
import java.util.Map;

public class SingleNumber {
	public static int singleNumber(int[] nums) {
		if (nums == null || nums.length == 0) {
			return -1;
		}
		/*
		 * using hashtable : time complexity is O(n) space complexity : O(n) the space
		 * required for hashtable equivalent to the input array
		 */
		Map<Integer, Integer> hashTable = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {
			hashTable.put(nums[i], hashTable.getOrDefault(nums[i], 0) + 1);
		}
		int singleOne = -1;
		for (Integer num : hashTable.keySet()) {
			if (hashTable.get(num) == 1) {
				singleOne = num;
			}
		}
		return singleOne;
	}
	/*
	 * Concept
	 * 
	 * If we take XOR of zero and some bit, it will return that bit a ^ 0 = a 
	 *  If we take XOR of two same bits, it will return 0 
	 *  a ^ a = 0,  a ^ b ^ a = (a ^ a) ^ b = 0 ^ b = b 
	 *  So we can XOR all bits together to find the unique
	 * number. Time complexity : O(n). We only iterate through nums,
	 * so the time complexity is the number of elements in nums.
	 * 
	 * Space complexity : O(1).
	 */

	public static int singleNumber_bitwise(int[] nums) {
		int a = 0;
		for (int i : nums) {
			a ^= i;
		}
		return a;
	}
	

	public static void main(String[] args) {
		// System.out.println(singleNumber(new int[] { 4, 1, 2, 1, 2 }));
		System.out.println(singleNumber_bitwise(new int[] { 4, 1, 2, 1, 2 }));
	}
}
