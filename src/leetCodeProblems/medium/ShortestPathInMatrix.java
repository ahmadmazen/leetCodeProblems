package leetCodeProblems.medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import common.CommonUtils;
import common.Pair;
/*
 * Expected time complexity is O(MN).
 * MxN matrix
 */
public class ShortestPathInMatrix {
	public int shortestPathBinaryMatrix(int[][] grid) {
		// starting at 0,0 explore each of the 8 neigbours, and only add to queue
        // for processing if the neigbour is a valid cell, the value is 0 and 
        // we have not already processed that cell
		int rMax = grid.length;
		int cMax = grid[0].length;

		if (grid[0][0] == 1 || grid[rMax - 1][cMax - 1] == 1)
			return -1;

		boolean[][] visited = new boolean[rMax][cMax];
		visited[0][0] = true;

		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { 0, 0 });

		int pathSize = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				int[] cur = queue.remove();
				int r = cur[0];
				int c = cur[1];

				// check whether the cell is the bottom right hand corner
				if (r == rMax - 1 && c == cMax - 1) {
					return pathSize + 1;
				}

				// evaluate each of the 8 cells
				isCellValid(grid, visited, r - 1, c, queue);
				isCellValid(grid, visited, r + 1, c, queue);
				isCellValid(grid, visited, r, c - 1, queue);
				isCellValid(grid, visited, r, c + 1, queue);
				isCellValid(grid, visited, r - 1, c - 1, queue);
				isCellValid(grid, visited, r + 1, c + 1, queue);
				isCellValid(grid, visited, r - 1, c + 1, queue);
				isCellValid(grid, visited, r + 1, c - 1, queue);
			}

			pathSize++;
		}

		return -1;
	}

	private boolean isCellValid(int[][] grid, boolean[][] visited, int r, int c, Queue<int[]> queue) {
		if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || visited[r][c] || grid[r][c] == 1) {
			return false;
		}

		visited[r][c] = true;
		queue.add(new int[] { r, c });

		return true;
	}

	public int shortestPathBinaryMatrix_without_visited(int[][] grid) {

		int[] dx = { 0, 0, -1, -1, 1, 1, 1, -1 };
		int[] dy = { -1, 1, 0, 1, 1, 0, -1, -1 };
		int n = grid.length;
		if (n == 0 || grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
			return -1;
		}
		Queue<Pair> q = new LinkedList<Pair>();
		q.add(new Pair(0, 0, 0));
		// Instead of using boolean visited array we can modify the grid itself by
		// changing a valid cell into a blocked cell
		grid[0][0] = 1;
		while (!q.isEmpty()) {
			Pair p = q.poll();
			int cr = p.x, cc = p.y;
			//reached the bottom right corner -- the destination
			if (cr == n - 1 && cc == n - 1) {
				return p.cost + 1;
			}
			for (int i = 0; i < 8; i++) {
				int nr = cr + dx[i], nc = cc + dy[i];
				if (nr >= 0 && nr < n && nc >= 0 && nc < n && grid[nr][nc] == 0) {
					q.add(new Pair(nr, nc, p.cost + 1));
					grid[nr][nc] = 1;
				}
			}
		}
		return -1;
	}

	public int shortestPathBinaryMatrix_dir(int[][] grid) {

		if (grid == null || grid.length <= 0 || grid[0].length <= 0 || grid[0][0] != 0) {
			return -1;
		}

		int[][] offset = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 }, { -1, -1 }, { 1, -1 }, { -1, 1 }, { 1, 1 } };

		Queue<int[]> possiblePath = new LinkedList<>();

		possiblePath.add(new int[] { 0, 0 });
		grid[0][0] = 2;
		int count = Integer.MIN_VALUE;
		while (!possiblePath.isEmpty()) {
			int[] current = possiblePath.poll();

			for (int i = 0; i < offset.length; i++) {
				int[] currentOffset = offset[i];
				int newX = current[0] + currentOffset[0];
				int newY = current[1] + currentOffset[1];
				if (newX < 0 || newX >= grid.length || newY < 0 || newY >= grid[0].length || grid[newX][newY] != 0) {
					continue;
				}
				grid[newX][newY] = grid[current[0]][current[1]] + 1;
				
				possiblePath.add(new int[] { newX, newY });
				count = Math.max(count, grid[newX][newY]);
			}

		}

		return grid[grid.length - 1][grid[0].length - 1] > 1 ? grid[grid.length - 1][grid[0].length - 1] - 1 : -1;
	}

	public int shortestPathBinaryMatrix_my_version(int[][] grid) {
        //sanity check
		
		if (grid == null || grid.length == 0 || grid[0].length == 0 || grid[0][0] != 0) {
			return -1;
		}

		int rowLength = grid.length;
		int columnLength = grid[0].length;
		// using bfs with queue to explore the neighbours in 8 directions
		Queue<int[]> possiblePaths = new LinkedList<>();
		// add the first position we gonna start from to the queue
		possiblePaths.add(new int[] { 0, 0 });
		// mark this possition as visited by changing its value to 1
		// that's instead of using visited boolean array, to save the more space
		grid[0][0] = 1;
		// the adjacent 8 cells including the diagonal cells
		int[][] directions = new int[][] { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 }, { -1, -1 }, { -1, 1 }, { 1, -1 },
				{ 1, 1 } };
		int pathSize = 0;
		while (!possiblePaths.isEmpty()) {
			// explore the neighbours of each cell on the queue , mark it to blocked
			int queueSize = possiblePaths.size();
			for (int i = 0; i < queueSize; i++) {
				int[] current = possiblePaths.poll();
				// check if we reached bottom right corner
				if (current[0] == rowLength - 1 && current[1] == columnLength - 1) {
					return pathSize + 1;					
				}

              
				for (int[] direction : directions) {
					int newX = direction[0] + current[0];
					int newY = direction[1] + current[1];
					if (isValidCell(grid, newX, newY)) {
						possiblePaths.add(new int[] { newX, newY });
						grid[newX][newY] = 1; // mark it to blocked
					}

				}

			}
			pathSize++; //increment the step
		}

		return -1;
	}
	private static boolean isValidCell(int[][] grid, int r, int c) {
		if( r < 0 || r >= grid.length ||  c < 0 || c >= grid[r].length || grid[r][c] != 0) {
			return false;
		}
		return true;
	}
	 
	public static void main(String[] args) {
		ShortestPathInMatrix sp = new ShortestPathInMatrix();
		
		 int[][] matrix = new int[][] {
			 {0, 1},
			 {1, 0}
		   };
		   int[][] matrix2 = new int[][] {
			   {0,0,0},
			   {1,1,0},
			   {1,1,0}
		   }; 
		   
		   
		    int[][] grid = new int[][] {
		    	{0, 1, 0, 0, 0, 0, 1, 0, 0, 0}, 
		    	{0, 1, 0, 1, 0, 0, 0, 1, 0, 0}, 
		    	{0, 0, 0, 1, 0, 0, 1, 0, 1, 0}, 
		    	{1, 1, 1, 1, 0, 1, 1, 1, 1, 0}, 
		    	{0, 0, 0, 1, 0, 0, 0, 1, 0, 1}, 
		    	{0, 1, 0, 0, 0, 0, 1, 0, 1, 1}, 
		    	{0, 1, 1, 1, 1, 1, 1, 1, 1, 0}, 
		    	{0, 1, 0, 0, 0, 0, 1, 0, 0, 0}, 
		    	{0, 0, 1, 1, 1, 1, 0, 1, 1, 0}
		   };
		   System.out.println(sp.shortestPathBinaryMatrix_my_version(matrix));	
		   System.out.println(sp.shortestPathBinaryMatrix_my_version(matrix2));
		   
//		   System.out.println(sp.shortestPathBinaryMatrix(matrix));	
//		   System.out.println(sp.shortestPathBinaryMatrix(matrix2));	
		   
//		   System.out.println(sp.shortestPathBinaryMatrix_without_visited(matrix));	
//		   System.out.println(sp.shortestPathBinaryMatrix_without_visited(matrix2));	
	}

}
