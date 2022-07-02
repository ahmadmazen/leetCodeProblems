package leetCodeProblems.topamazonproblemsmedium;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TopkFrequentWords {
	/*
	 * O(n log n)
	 */
	public static List<String> topKFrequent(String[] words, int k) {
		Map<String, Integer> count = new HashMap<>();
		for (String word : words) {
			count.put(word, count.getOrDefault(word, 0) + 1);
		}
		List<String> candidates = new ArrayList<>(count.keySet());

		Collections.sort(candidates,
				(w1, w2) -> count.get(w1).equals(count.get(w2)) ? w1.compareTo(w2) : count.get(w2) - count.get(w1));

		return candidates.subList(0, k);

	}


	// Amazon version of the problem
	// https://medium.com/@7anac/cs-interview-question-top-k-frequently-mentioned-keywords-1543648a5fc4

	public static String[] getMostFrequentCommonwords(String[] reviews, String[] keywords, int k) {

		Map<String, Integer> frequences = new HashMap<>();

		// build a list of keywords
		List<String> keywordsList = Arrays.asList(keywords);

		// set the count of occurences
		for (String review : reviews) {
			review = review.toLowerCase();
			String[] reviewWords = review.split(" ");
			for (String word : reviewWords) {
				Set<String> seen = new HashSet<>();
				if (!seen.contains(word) && keywordsList.contains(word)) {
					frequences.put(word, frequences.getOrDefault(word, 0) + 1);
					seen.add(word);
				}
			}
		}
		String[] frequencesArray = frequences.keySet().toArray(new String[k]);

		Arrays.sort(frequencesArray,
				(w1, w2) -> frequences.get(w1).equals(w2) ? w1.compareTo(w2) : frequences.get(w2) - frequences.get(w1));

		return Arrays.copyOfRange(frequencesArray, 0, k);

	}

	public static void main(String[] args) {
//		topKFrequent(new String[] { "i", "love", "leetcode", "i", "love", "coding" }, 2)
//				.forEach(s -> System.out.println(s));

//		topKFrequent(2, new String[] { "i", "love", "leetcode", "i", "love", "coding" })
//				.forEach(s -> System.out.println(s));
		
		System.out.println(Arrays.toString(getMostFrequentCommonwords(new String[] { "I love anacell Best services; Best services provided by anacell",
				  "betacellular has great services",
				  "deltacellular provides much better services than betacellular",
				  "cetracular is worse than anacell",
				  "Betacellular is better than deltacellular."}, new String[] {"anacell", "betacellular", "cetracular", "deltacellular", "eurocell"}
		          ,2))) ;

	}

}
