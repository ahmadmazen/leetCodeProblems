package leetCodeProblems.medium;

public class SlidingWindow {

	// brute force technique
	/*
	 * given an array of integers n and a positive number k, find the maximum sum of
	 * any contiguous sub-array of size k
	 * 
	 * Time complexity of this solution is in worst case if k = 1 will have to scan
	 * all the array so o(n*k)
	 */
	private static int maxSumOfSubarray_brutForc(int[] input, int k) {

		int maxSum = 0;
		for (int i = 0; i <= input.length - k; i++) {
			int windowSum = 0;
			for (int j = i; j < i + k; j++) {
				windowSum += input[j];
			}
			// System.out.println(windowSum);
			maxSum = Math.max(maxSum, windowSum);
		}

		return maxSum;
	}

	/*
	 * sliding window technique a bit confusing
	 */
	private static int maxSumOfSubarray_sliding(int[] input, int k) {

		int maxSum = Integer.MIN_VALUE;
		int windowSum = 0;
		for (int i = 0; i < input.length; i++) {
			windowSum += input[i];
			if (i >= k - 1) {
				maxSum = Math.max(windowSum, maxSum);
				windowSum = (windowSum - input[i - (k - 1)]);
			}
		}
		return maxSum;
	}

	/*
	 * other easier solution to understand
	 */
	public static int getMaxSumSubArrayOfSizeKM2(int[] A, int k) {
		int windowSum = 0, maxSum = Integer.MIN_VALUE;

		for (int i = 0; i < k; i++) {
			windowSum += A[i];
		}
		maxSum = Math.max(maxSum, windowSum);
		for (int windowEndIndex = k; windowEndIndex < A.length; windowEndIndex++) {
			windowSum += A[windowEndIndex] - A[windowEndIndex - k];
			maxSum = Math.max(maxSum, windowSum);
		}

		return maxSum;
	}

	public static void main(String[] args) {

//		System.out.println(maxSumOfSubarray_brutForc(new int[] { 1, 9, -1, -2, 7, 3, -1, 2 }, 4));
//		System.out.println(maxSumOfSubarray_brutForc(new int[] { 2, 1, 5, 1, 3, 2 }, 3));

		System.out.println(getMaxSumSubArrayOfSizeKM2(new int[] { 1, 9, -1, -2, 7, 3, -1, 2 }, 4));
		 System.out.println(maxSumOfSubarray_sliding(new int[] { 2, 1, 5, 1, 3, 2 }, 3));
		// 3));
	}

}
