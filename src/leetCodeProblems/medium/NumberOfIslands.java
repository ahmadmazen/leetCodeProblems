package leetCodeProblems.medium;

public class NumberOfIslands {

	/*
	 * Given a 2d grid map of '1's (land) and '0's (water), count the number of
	 * islands. An island is surrounded by water and is formed by connecting
	 * adjacent lands horizontally or vertically. You may assume all four edges of
	 * the grid are all surrounded by water.
	 * 
	 * Time complexity : O(M×N) where M is the number of rows and N
	 * is the number of columns.
	 * 
	 * Space complexity : worst case O(MN) in case that the grid map
	 * is filled with lands where DFS goes by M×N deep.
	 */
	public int numIslands(char[][] grid) {

		if (grid.length == 0 || grid == null) {
			return 0;
		}
		int islandsCount = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == '1') {
					islandsCount += dfs(grid, i, j);
				}
			}
		}
		return islandsCount;

	}

	private int dfs(char[][] grid, int i, int j) {
		if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == '0') {
			return 0;
		}
		// dfs the row and column updward, downward, rightside, leftside,
		// each direction we stop once encounter 0
		System.out.println("row =" + i + " | column = " + j);
		grid[i][j] = '0'; // mark the visited island for not visiting again (convert it into water)
		dfs(grid, i + 1, j); // go down --- since it depth first we gonna go down to the deep 1 value
		dfs(grid, i - 1, j); // go up until reaching the first 1 in same column upward
		dfs(grid, i, j + 1); // go right until reaching the first 1 in same row to the right
		dfs(grid, i, j - 1); // go left
		return 1; // increment the counter after finishing traversal...
	}

	public static void main(String[] args) {
		char grid[][] = new char[][] { { '1', '1', '1', '1', '0' }, 
			                           { '1', '1', '0', '1', '0' },
									   { '1', '1', '0', '0', '0' },
									   { '0', '0', '0', '0', '0' } };
		NumberOfIslands noIslands = new NumberOfIslands();
		System.out.println("Number of islands in grid0 is: " + noIslands.numIslands(grid));

		char grid2[][] = new char[][] { { '1', '1', '0', '0', '0' }, 
									    { '1', '1', '0', '0', '0' },
										{ '0', '0', '1', '0', '0' }, 
										{ '0', '0', '0', '1', '1' } };

	//	System.out.println("Number of islands in grid1 is: " + noIslands.numIslands(grid2));

	}

}
