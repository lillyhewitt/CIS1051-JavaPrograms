import java.util.ArrayList;
import java.util.List;

public class IndexNode {

	// The word for this entry
	String word;
	// The number of occurrences for this word
	int occurences;
	// A list of line numbers for this word.
	List<Integer> list = new ArrayList<>();
	
	IndexNode left;
	IndexNode right;
	
	// Constructors
	// Constructor should take in a word and a line number
	// it should initialize the list and set occurrences to 1
	public IndexNode() {
		this.word = null;
		this.occurences = 1;
		this.left = null;
		this.right = null;
		list.add(0);
	}
	public IndexNode(String word, int lineNumber) {
		this.word = word;
		this.occurences = 1;
		this.left = null;
		this.right = null;
		list.add(lineNumber);
	}

	// Complete This
	// return the word, the number of occurrences, and the lines it appears on.
	// string must be one line
	public String toString(){
		String words = word;
		words = "Word: " + word + "  Occurences: " + occurences + "  Line: ";
		if(!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				if(list.size() == 1 || i == (list.size()-1)) {
					words = words + list.get(i) + " ";
				}
				else {
					words = words + list.get(i) + ", ";
				}
			}
		}
		return words;
	}
}
