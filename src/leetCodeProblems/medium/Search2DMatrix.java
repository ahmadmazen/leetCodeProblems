package leetCodeProblems.medium;

public class Search2DMatrix {
	/*
	 * Many approachers to do the search here,
	 * 1. Approach one: naive one which is Brute-force the matrix until you find the target
	 *    this is O(row * col) time complexity
	 * 2. Approach two. is to binary search each row in the matrix,
	 *  O(m log n) m number of rows, n number of columns   
	 */
	/*
	 * approach two implementation
	 */
	public static boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return false;
		}

		for (int i = 0; i < matrix.length; i++) {
			int left = 0;
			int right = matrix[i].length - 1;
			while (left <= right) {
				int midPoint = left + (right - left) / 2;
				if (matrix[i][midPoint] < target) {
					left = midPoint + 1;
				} else if (matrix[i][midPoint] > target) {
					right = midPoint - 1;
				} else {
					return true;
				}
			}
		}

		return false;
	}
	 private boolean binarySearch(int[][] matrix, int target, int start, boolean vertical) {
	        int lo = start;
	        int hi = vertical ? matrix[0].length-1 : matrix.length-1;

	        while (hi >= lo) {
	            int mid = (lo + hi)/2;
	            if (vertical) { // searching a column
	                if (matrix[start][mid] < target) {
	                    lo = mid + 1;
	                } else if (matrix[start][mid] > target) {
	                    hi = mid - 1;
	                } else {
	                    return true;
	                }
	            } else { // searching a row
	                if (matrix[mid][start] < target) {
	                    lo = mid + 1;
	                } else if (matrix[mid][start] > target) {
	                    hi = mid - 1;
	                } else {
	                    return true;
	                }
	            }
	        }

	        return false;
	    }

	    public boolean searchMatrix_1(int[][] matrix, int target) {
	        // an empty matrix obviously does not contain `target`
	        if (matrix == null || matrix.length == 0) {
	            return false;
	        }

	        // iterate over matrix diagonals
	        int shorterDim = Math.min(matrix.length, matrix[0].length);
	        for (int i = 0; i < shorterDim; i++) {
	            boolean verticalFound = binarySearch(matrix, target, i, true);
	            boolean horizontalFound = binarySearch(matrix, target, i, false);
	            if (verticalFound || horizontalFound) {
	                return true;
	            }
	        }
	        
	        return false; 
	    }
	/*
	 * approach three : 
	 */
	
	public static boolean searchMatrix_stair_search(int[][] matrix, int target) {
		

		
		return false;
		
	}
	public static void main(String[] args) {
		int matrix[][] = new int[][] { 
						{ 1,   4,  7, 11, 15 }, 
			            { 2,   5,  8, 12, 19 },
						{ 3,   6,  9, 16, 22 },
						{ 10, 13, 14, 17, 24 } 
					};
			System.out.println(searchMatrix(matrix, 11));		
	}
	

}
