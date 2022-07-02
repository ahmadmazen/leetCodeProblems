package leetCodeProblems.medium;

import java.util.LinkedList;
import java.util.Queue;

/*
 * Design a hit counter which counts the number of hits received in the past 5 minutes.
 * Each function accepts a timestamp parameter (in seconds granularity) 
 * and you may assume that calls are being made to the system in chronological order 
 * (ie, the timestamp is monotonically increasing). 
 * You may assume that the earliest timestamp starts at 1.
 * 
 * It is possible that several hits arrive roughly at the same time.
 * 
 * Example:
 * 
 * HitCounter counter = new HitCounter();
 * // hit at timestamp 1.
 * counter.hit(1);
 * 
 * // hit at timestamp 2.
 * counter.hit(2);
 * 
 * // hit at timestamp 3.
 * counter.hit(3);
 * 
 * // get hits at timestamp 4, should return 3.
 * counter.getHits(4);
 * 
 * // hit at timestamp 300.
 * counter.hit(300);
 * 
 * // get hits at timestamp 300, should return 4.
 * counter.getHits(300);
 * 
 * // get hits at timestamp 301, should return 3.
 * counter.getHits(301);
 *  
 */
public class HitCounter {
	 /** Initialize your data structure here. */
	//approach one
	//Queue<Integer> hits;
	//approach two
	int[] hitCounts;
	int[] times;
	
	/*
	 * Solution Approaches :
	 * Approach one : 
	 * We can use a queue to store the hits and delete the entries in queue which are of no use. It will save our space.
	 * But this approach will work fine assuming the hits come in ordered manner
	 * Time Complexity of getHits will be O(n - 300) 
	 * where n is the timestamp sent to getHits we have to traverse n - 300 each time getHits being called 
	 * 
	 * Space complexity: O(1) after clearing unused timestamp from the queue the queue size will always be of size 300
	 * but before getHits got called the queue size wil be O(n) where n is the number of hits
	 * 
	 * 
	 * Approach two:
	 * two arrays of size 300 one to store the timestamp and the other to store the hits in that timestamp
	 * with mod operation we will determine the location of the passed timestamp
	 * given that you are required to count the hits in the past 5 minutes(300 seconds): 
	 * so we allocate array of size (300) remember this array will hold only the last 300 seconds hits,
	 * 
	 * so now how you locate the position of the timestamp sent to you in this array, 
	 * for that you need to divide the passed timestamp by 300 and the remainder (comes directly from the mod operation ) 
	 * will be the location of the passed timestamp in the last 300 seconds (e.g. hit(timestamp => 62102 ) --> 62102 ?
	 * you need to locate exactly the last 300 seconds)
	 * how many 300 seconds you have in 62102  (62102 / 300 = 207) - > 207 * 300 = 62100 and the remainder is 2 , 
	 * so the location for the timestamp in the array will be in the position of 2.
	 * 
	 * How to handle concurrent requests?
	 * Distribute the counter?
	 * refer to the following link
	 * https://leetcode.com/discuss/interview-question/178662/Design-a-Hit-Counter/
	 */
    public HitCounter() {
       //approach one
    	//hits = new LinkedList<Integer>();
        //approach two
    	times = new int[300];
    	hitCounts = new int[300];
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
       //approach one
    	//hits.add(timestamp);   
     //approach two
    	int indx = timestamp % 300;
    	if(times[indx] != timestamp) {
    		times[indx] = timestamp;
        	hitCounts[indx] = 1;	
    	}else {
    		hitCounts[indx]++;
    	}
    	
    	
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
       //approach one
//    	while(!hits.isEmpty() && timestamp - hits.peek() >= 300) {
//    	   hits.poll();
//       }
//    	return hits.size();
    	//approach two
    	int hits = 0;
    	for(int i = 0; i < 300; i++) {
    		if(timestamp - times[i] < 300) {
    			hits += hitCounts[i];
    		}
    		 
    	}
       return hits;
    }
    
    /**
     * Your HitCounter object will be instantiated and called as such:
     * HitCounter obj = new HitCounter();
     * obj.hit(timestamp);
     * int param_2 = obj.getHits(timestamp);
     */
    public static void main(String[] args) {
		
	}
    
}

