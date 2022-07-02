package leetCodeProblems.topamazonproblemsmedium;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OptimalUtilization {

	private static List<int[]> getPairs(List<int[]> a, List<int[]> b, int target) {
		// sort based on the values not key
		Collections.sort(a, (i, j) -> i[1] - j[1]); //o(n log n)
		Collections.sort(b, (i, j) -> i[1] - j[1]);
		List<int[]> result = new ArrayList<>();
		int currentMax = Integer.MIN_VALUE;
		int m = a.size();
		int n = b.size();
		int left = 0;
		int right = n - 1;
		// after sorting ascending -- lower values first, greates last
		// set two pointers one on the start of the first list, second on the last
		// element of second list
		// while sum greater than target move the right pointer backward
		// while sum lesser than target move left pointer right
		while (left < m && right >= 0) {
			int sum = a.get(left)[1] + b.get(right)[1];
			// we are not yet in the right range, so move b to the left
			if (sum > target) {
				--right;
			} else { // we have a candidate solution
				// while we should add the values less or equal to the target

				if (sum >= currentMax) {
					if (sum > currentMax) { // we have found a better solution, clear previous one
						currentMax = sum;
						result.clear();
					}
					result.add(new int[] { a.get(left)[0], b.get(right)[0] });
					// move right pointer step left
					// while moving to left in b list(lesser values direction)
					// check if there are values in b equal to the already added one to the list
					// if found add it to the result list and go one step more..etc
					int prev = right - 1;
					while (prev >= 0 && b.get(prev)[1] == b.get(right)[1]) {
						result.add(new int[] { a.get(left)[0], b.get(prev)[0] });
						prev--;
					}
				}
				++left;
			}
		}
		return result;
	}

	public static void main(String[] args) {

		List<int[]> a = new ArrayList<>();
		List<int[]> b = new ArrayList<>();
		// ex1
		a.add(new int[] { 1, 2 });
		a.add(new int[] { 2, 4 });
		a.add(new int[] { 3, 6 });

		b.add(new int[] { 1, 2 });
		// String res = "";
		getPairs(a, b, 7).forEach(elm -> System.out.println(Arrays.toString(elm)));
		System.out.println("=======================");

		// ex2
		a.clear();
		a.add(new int[] { 1, 3 });
		a.add(new int[] { 2, 5 });
		a.add(new int[] { 3, 7 });
		a.add(new int[] { 4, 10 });

		b.clear();
		b.add(new int[] { 1, 2 });
		b.add(new int[] { 2, 3 });
		b.add(new int[] { 3, 4 });
		b.add(new int[] { 4, 5 });
		System.out.println("==============ex2=========");
		getPairs(a, b, 10).forEach(elm -> System.out.println(Arrays.toString(elm)));
		// ex3
		a.clear();
		a.add(new int[] { 1, 8 });
		a.add(new int[] { 2, 7 });
		a.add(new int[] { 3, 14 });

		b.clear();
		b.add(new int[] { 1, 5 });
		b.add(new int[] { 2, 10 });
		b.add(new int[] { 3, 14 });

		System.out.println("==============ex3=========");
		getPairs(a, b, 20).forEach(elm -> System.out.println(Arrays.toString(elm)));

		// ex4
		a.clear();
		a.add(new int[] { 1, 8 });
		a.add(new int[] { 2, 15 });
		a.add(new int[] { 3, 9 });

		b.clear();
		b.add(new int[] { 1, 8 });
		b.add(new int[] { 2, 11 });
		b.add(new int[] { 3, 12 });

		System.out.println("==============ex4=========");
		getPairs(a, b, 20).forEach(elm -> System.out.println(Arrays.toString(elm)));
	}
}
