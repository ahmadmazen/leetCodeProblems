package leetCodeProblems.topamazonproblemsmedium;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

//minmum cost to connect ropes or sticks
public class ConnectRopes {
/*
 * Time complexity: O(nlogn).
 *  Space complexity: O(n).
 */
	public static int connectSticks(int[] sticks) {
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int cost = 0;
        //insert the sticks into priority Queue
        for(int stick : sticks) {
        	minHeap.add(stick);
        }
        //start connecting and calculate the cost -- PriorityQueeu gives priority to numbers 
        //least to greatest -- so it's going to remove the lowest first, since we want to start connecting
        //the lowest cost
        while(minHeap.size() > 1) {
        	//add the lowest 2 sticks, then add the sum back to the heap
        	int sum = minHeap.poll() + minHeap.poll();
        	cost  += sum;
        	minHeap.add(sum);
        }
        
        return cost;
	}
	
	public static void main(String[] args) {
		//System.out.println(connectSticks(new int[] {2,4,3}));
		System.out.println(connectSticks(new int[] {1,8,3,5}));
	}

}
