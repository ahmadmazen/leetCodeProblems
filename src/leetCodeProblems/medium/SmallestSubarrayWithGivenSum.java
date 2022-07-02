package leetCodeProblems.medium;

public class SmallestSubarrayWithGivenSum {

	private static int smallestSubarray(int target, int[] arr) {
	   int minWindowSize = Integer.MAX_VALUE;
	   int currentRunningSum = 0;
	   int windowStart = 0;
	   for(int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
		   currentRunningSum+= arr[windowEnd];
		   while(currentRunningSum >= target) {
			   minWindowSize = Math.min(minWindowSize, (windowEnd - windowStart) + 1);
			currentRunningSum -= arr[windowStart];
			windowStart++;
		   }
		   
	   }
		
		return minWindowSize;
	}

	public static void main(String[] args) {
		System.out.println(smallestSubarray(8, new int[] { 4, 2, 2, 7, 8, 1, 2, 8, 10 }));
		System.out.println(smallestSubarray(4, new int[] { 4, 2, 2, 7, 8, 1, 2, 8, 10 }));
		System.out.println(smallestSubarray(10, new int[] { 4, 2, 2, 7, 8, 1, 2, 8, 10 }));

	}

}
