package leetCodeProblems.topamazonproblemsmedium;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PartitionLabels {
	public static List<Integer> partitionLabels(String S) {

		String s = S;
		if (s == null || s.length() == 0) {
			return null;
		}

		// we will keep track of last occurence of each char in our string
		// 26 the maximum alphapet in english letters
		int[] lastIndex = new int[26];
		for (int i = 0; i < s.length(); i++) {
			// 'a' - 'a' will give 0, 'b' - 'a' will give 1, so on, it gives the position of
			// letter in alphabet series
			// so it's like map between the letter and the last occurences of it in our string
			// so in String like aabaa the last occurence of a at first iteration will be 0
			// then 1,3 and finally 4
			lastIndex[s.charAt(i) - 'a'] = i;
		}
        
		List<Integer> partitionLengths = new ArrayList<>(); 
		int start = 0, end = 0; // to keep track of the first index and last index of the partition
		for (int i = 0; i < s.length(); i++) {
			//to keep track of the highest last occurenc, 
            //for example: ababcbacadefegdehijhkli 
			// first iteration is end = 8, second with letter b end = 5
			// so we should cut until the highest last index in order for  not appearing in the next partition
			//ababcb aca  so here a appeared in two partions 
			
	         end = Math.max(end, lastIndex[s.charAt(i) - 'a']);	
	         //if you reached the last letter of your first partition,
	         //adjust the  start boundary
	         //and add the partitions length to your result list
	         if(end == i) {
	        	 //i - start + 1 because the index starts with 0
	        	 partitionLengths.add( i - start + 1);
	        	 //adjust the start to be next to end letter
	        	 start = i + 1;
	         }
	
		}

		return partitionLengths;
	}

	public static void main(String[] args) {
		 System.out.println(partitionLabels("ababcbacadefegdehijhklij"));

	}

}
