package leetCodeProblems.topamazonproblemsmedium;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchSuggestion {
	public static List<List<String>> suggestedProducts(String[] products, String searchWord) {
		List<List<String>> suggestedList = new ArrayList<>();
		// sort the products lexographically in order to cut the first 3 if they are
		// more..
		Arrays.sort(products); // O(n log n)

		// iterate through the letters of search word and list the words that contain
		// the prefix..

		for (int i = 0; i < searchWord.length(); i++) {
			String prefix = searchWord.substring(0, i + 1); // end index is exclusive
			List<String> prefixMatchedProducts = new ArrayList<String>();
			// look for the matching products
			for (String product : products) {
				// check if the length of substring is valid inside the product bound
				// that's in case of the search word is larger than the current product in the
				// iteration
				// in this case shouldn't be included in the list and of course it's gonna throw
				// exception
				if (i + 1 <= product.length() && product.substring(0, i + 1).equals(prefix)) {
					prefixMatchedProducts.add(product); // Add the matched product to prefix
				}

			}
			// add the matched prefix products, in case if they are more than three,
			// will cut the first three words only
			suggestedList.add(
					prefixMatchedProducts.size() > 3 ? prefixMatchedProducts.subList(0, 3) : prefixMatchedProducts);

		}

		return suggestedList;

	}

	public static void main(String[] args) {

		String[] products = new String[] { "mobile", "mouse", "moneypot", "monitor", "mousepad" };
		String searchWord = "mouse";
		List<List<String>> res = suggestedProducts(products, searchWord);
		res.stream().forEach(l -> System.out.println(l.toString()));
		
	   System.out.println(1 ^ 2 ^ 3 ^ 5 ^ 1 ^ 2 ^ 3 ^ 4 ^ 5);

	}

}
