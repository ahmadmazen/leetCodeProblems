package leetCodeProblems.topamazonproblemsmedium;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/*
 * Given a 2D grid, each cell is either a zombie 1 or a human 0. 
 * Zombies can turn adjacent (up/down/left/right) 
 * human beings into zombies every hour. 
 * Find out how many hours does it take to infect all humans?
 */
public class ZombieInMatrix {
	/*
	 * There are two basic graph traversal algorithms: Breadth-First Search (BFS)
	 * and Depth-First Search (DFS). For this case we should choose BFS algorithm
	 * because DFS will not allows so easily process all zombies in parallel. Thus
	 * the algorithm looks like this: 1. Collect all positions of the zombies into a
	 * queue. Then we will use this queue for collecting positions of humans only.
	 * 2. Find adjacent humans around each enqueued position. 3. Add their positions
	 * into the queue. 4. Convert them into zombies. 5. Increase number of the
	 * hours. 6. Repeat from 2 until all humans on the matrix will be found and
	 * processed.
	 */
	private static int minDays(int[][] matrix) {
		if (matrix == null || matrix.length == 0) {
			return -1;
		}
		Queue<int[]> queue = new LinkedList<>();
		int days = 0;
		int humanCount = 0;
		// * 1. Collect all positions of the zombies into a queue. and the number of
		// humans

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					humanCount++;
				} else {
					queue.offer(new int[] { i, j });
				}
			}
		}
		// directions around cell, right, left, up , down
		int[][] directions = new int[][] { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
		while (!queue.isEmpty() && humanCount > 0) {
			int queueSize = queue.size();
			for (int i = 0; i < queueSize; i++) {
				int[] zombie = queue.poll();
				for (int[] dir : directions) {
					int newX = zombie[0] + dir[0];
					int newY = zombie[1] + dir[1];

					if (newX >= 0 && newX < matrix.length && newY >= 0 && newY < matrix[0].length
							&& matrix[newX][newY] == 0) { // when the new coordinates doesn't exceed the boundaries of
															// the matrix or the new valid coordinate is a human, only
															// then turn that to a zombie
						matrix[newX][newY] = 1;
						queue.offer(new int[] { newX, newY }); // now that new coordinate is a zombie, add that to the
																// queue so it can be processed in the next level
						humanCount--;
					}
				}
			}
			days++;
		}
		return humanCount == 0 ? days : -1;
	}

	private static int minDays(List<ArrayList<Integer>> grid) {
		
		if(grid == null || grid.size() == 0) {
			return -1;
		}
		
		 int[][] newGrid = grid.stream()
		            .map(l -> l.stream().mapToInt(Integer::intValue).toArray())
		            .toArray(int[][]::new);
		// collect the positions of zombies into queu, and count the no of humans
		int days = 0;
		int humanCount = 0;
		Queue<int[]> zombiesQueu = new LinkedList<>();
		
		for(int i = 0; i < newGrid.length; i++) {
			for(int j = 0; j < newGrid[i].length; j++) {
				if(newGrid[i][j] == 0) { // this is a human
					humanCount++;
				}else {
					zombiesQueu.offer(new int[] {i, j});
				}	
			}	
		}
		
		// go through the queue to infect human neighbours(right, left, up, down) 
		//and push the new zombies(just infected) to 
		//the queue untill no more zombies
		//each infection by zombier we decrement the no of humans to check at the end 
		//if there will be alive humans return -1 otherwise return the no of days 
		int[][] directions = new int[][] {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
		while(!zombiesQueu.isEmpty() && humanCount > 0) {
			int zombieQueueSize = zombiesQueu.size();
			//iterate zombies to infect neighbours human
			for(int i = 0; i < zombieQueueSize; i ++) {
				int [] zombie = zombiesQueu.poll();
				//infect the neighbours
				for(int[] dir : directions) {
					int newX = dir[0] + zombie[0];
					int newY = dir[1] + zombie[1];
					
					if(newX >= 0 && newX < newGrid.length && newY >= 0 && newY < newGrid[0].length &&
					   newGrid[newX][newY] == 0) {
						
						newGrid[newX][newY] = 1;
						zombiesQueu.offer(new int[] {newX, newY});
						humanCount--;		
					}
				}
			}
			days++;
		}
		return humanCount > 0 ? -1 : days;
	}

	public static void main(String[] args) {
//		System.out.println(
//				minDays(new int[][] { { 0, 1, 1, 0, 1 }, { 0, 1, 0, 1, 0 }, { 0, 0, 0, 0, 1 }, { 0, 1, 0, 0, 0 } }));

		int [][] input = new int[][] { { 0, 1, 1, 0, 1 }, { 0, 1, 0, 1, 0 }, { 0, 0, 0, 0, 1 }, { 0, 1, 0, 0, 0 } };
		List<ArrayList<Integer>> l = twoDArrayToList(input);
		System.out.println(l.toString());
		 System.out.println(minDays(l));
		

		
	}
	public static  List<ArrayList<Integer>> twoDArrayToList(int[][] input) {
	    List<ArrayList<Integer>> listOfList = new ArrayList<ArrayList<Integer>>();
	    
	    for ( int[] array :  input) {
	    	ArrayList<Integer> l = (ArrayList<Integer>) Arrays.stream(array).boxed().collect(Collectors.toList());
	    	//System.out.println(l.toString());
	       listOfList.add(l);
	    }
	    return listOfList;
	}

}
