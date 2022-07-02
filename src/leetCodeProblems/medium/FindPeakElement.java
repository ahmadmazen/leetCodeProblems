package leetCodeProblems.medium;

public class FindPeakElement {
	/*
	 * Time complexity : O\big(log_2(n)\big)O(log 2 ​ (n)). We reduce the search
	 * space in half at every step. Thus, the total search space will be consumed in
	 * log_2(n)log 2 ​ (n) steps. Here, n refers to the size of nums array.
	 * 
	 * Space complexity : O(1). Constant extra space is used.
	 */
	public static int findPeakElement(int[] nums) {
		int l = 0, r = nums.length - 1;
		while (l < r) {
			int mid = (l + r) / 2;
			if (nums[mid] > nums[mid + 1])
				r = mid;
			else
				l = mid + 1;
		}
		return l;
	}

	public static void main(String[] args) {
		int[] array = { 15, 20, 25, 35, 45, 50, 60 };
		int[] array1 = { 1, 2, 1, 3, 5, 6, 4 };
		System.out.println(findPeakElement(array1));
	}

}
