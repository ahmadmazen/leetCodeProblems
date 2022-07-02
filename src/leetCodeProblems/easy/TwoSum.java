package leetCodeProblems.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TwoSum {
/*
 * Given an array of integers nums and an integer target, return indices of the
 * two numbers such that they add up to target. You may assume that each input
 * would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order Example 1:
 * 
 * Input: nums = [2,7,11,15], target = 9 //Output: [0,1] 
 * //Explanation:
 * Because nums[0] + nums[1] == 9, 
 * we return [0, 1]. //Example 2: // //Input:
 * nums = [3,2,4], target = 6 //Output: [1,2] /
 * /Example 3: Input: nums =
 * [3,3], target = 6 //Output: [0,1]
 */
	
	public int[] twoSum(int[] nums, int target) {
	    Map<Integer, Integer> map = new HashMap<>();
	    for (int i = 0; i < nums.length; i++) {
	        int complement = target - nums[i];
	        if (map.containsKey(complement)) {
	            return new int[] { map.get(complement), i };
	        }
	        map.put(nums[i], i);
	    }
	    throw new IllegalArgumentException("No two sum solution");
	}

	 //this solution will work only with sorted series
	public int[] twoSum_sorted(int[] nums, int target) {
     int left = 0;
     int right = nums.length - 1;
     while(left < right) {
     	if(nums[left] + nums[right] > target) {
     		right--;
     	}else if(nums[left] + nums[right] < target) {
     		left++;
     	}else {
     		return new int[]{++left, ++right};
     	}
     }
     throw new IllegalArgumentException("No two sum solution");
	}
	
	public static void main(String[] args) {
		int[] arr = new int[] {2, 7, 11, 15};
		TwoSum ts = new TwoSum();
		System.out.println(Arrays.toString(ts.twoSum_sorted(arr, 26)));
		
		int[] arr1 = new int[] {1,2,3,9};
		System.out.println(findSumOfTwo(arr1, 5));
//		int[] arr = new int[] {1,2,4, 4};
		
		
//		System.out.println(Arrays.toString(ts.twoSum(new int[] {3,2,4}, 6)));
	
	}
	
	/*
	 * another variation of this problem 
	 * Given an array of integers and a value, 
	 * determine if there are any two integers in the array whose sum is equal to the given value.
	 *  Return true if the sum exists and return false if it does not.
	 * Consider this array and the target sums:
	 * 
	 * Runtime Complexity
	 * The runtime complexity of this solution is linear, O(n).
	 * Memory Complexity
	 * The memory complexity of this solution is linear, O(n).


	 */
	
	public static boolean findSumOfTwo(int[] A, int val) {
		Set<Integer> set = new HashSet<Integer>();
		for(int i = 0; i < A.length; i++) {
			if(set.contains(val - A[i])) {
				return true;
			}
			set.add(A[i]);
		}
		return false;
		
	}
}
