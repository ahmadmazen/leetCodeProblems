package leetCodeProblems.easy;

/*
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/solution/
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), 
 * design an algorithm to find the maximum profit.
 * Note that you cannot sell a stock before you buy one.
 */
public class MaxProfit {
	public int maxProfit(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		// naive solution
		/*
		 * Complexity Analysis
		 * 
		 * Time complexity : O(n^2). times.
		 * 
		 * Space complexity : O(1). Only two variables
		 */

		/*
		 * int max_profit = 0; for (int i = 0; i < prices.length - 1; i++) { for (int j
		 * = i + 1; j < prices.length; j++) { int profit = prices[j] - prices[i]; if
		 * (profit > max_profit) { max_profit = profit; } } }
		 */
		/*
		 * Better approach: Time complexity : O(n)O(n). Only a single pass is needed.
		 * 
		 * Space complexity : O(1)O(1). Only two variables are used.
		 */
		int minPrice = Integer.MAX_VALUE;
		int max_profit = 0;
		for (int i = 0; i < prices.length; i++) {
			//each day we check if the current price of that day is lesser of what we've seen, if so update minprice
			// if not, we check our max_profit we can make till that day
			if (prices[i] < minPrice) {
				minPrice = prices[i];
			} else { 
				 max_profit = Math.max(max_profit, prices[i] - minPrice);
			}
		}

		return max_profit;
	}

	public static void main(String[] args) {
		MaxProfit mp = new MaxProfit();
		System.out.println(mp.maxProfit(new int[] { 7, 1, 5, 3, 6, 4 }));
		System.out.println(mp.maxProfit(new int[] { 7, 6, 4, 3, 1 }));

	}
}
