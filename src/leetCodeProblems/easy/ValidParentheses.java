package leetCodeProblems.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', 
 * determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.

 */
public class ValidParentheses {
	private Map<Character, Character> mapping;

	public ValidParentheses() {
		// Initialize a stack to be used in the algorithm.
		mapping = new HashMap<Character, Character>();
		this.mapping.put('}', '{');
		this.mapping.put(')', '(');
		this.mapping.put(']', '[');

	}
/*
 * 
 * Time complexity : O(n) because we simply traverse the given string one character at a 
 * time and push and pop operations on a stack take O(1) time.
   Space complexity : O(n) as we push all opening brackets onto the stack and in the worst case,
   we will end up pushing all the brackets onto the stack. e.g. ((((((((((.
 */
	public boolean isValid(String s) {
		if (s.trim().isEmpty()) {
			return true;
		}
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			// If the current character is a closing bracket.
			if (mapping.containsKey(c)) {
				// Get the top element of the stack. If the stack is empty, set a dummy value of
				// '#'
				char topElement = stack.empty() ? '#' : stack.pop();

				// If the mapping for this bracket doesn't match the stack's top element, return
				// false.
				if (topElement != mapping.get(c)) {
					return false;

				}
			} else {
				// If it was an opening bracket, push to the stack.
				stack.push(c);
			}

		}
		// If the stack still contains elements, then it is an invalid expression.
		return stack.isEmpty();

	}

	public static void main(String[] args) {
		ValidParentheses vp = new ValidParentheses();
		System.out.println(vp.isValid("()"));
		System.out.println(vp.isValid("()[]{}"));
		System.out.println(vp.isValid("(]"));
		System.out.println(vp.isValid("([)]"));
		System.out.println(vp.isValid("{[]}"));
		System.out.println(vp.isValid("]"));
		System.out.println(vp.isValid("(((((())))))"));
	}
}
