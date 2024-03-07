import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

// Your class. Notice how it has no generics.
// This is because we use generics when we have no idea what kind of data we are getting
// Here we know we are getting two pieces of data:  a string and a line number
public class IndexTree {

	// This is your root again, your root does not use generics because you know your
	// nodes hold strings, an int, and a list of integers
	private IndexNode root;

	// Make your constructor; It doesn't need to do anything
	public IndexTree() {
		this.root = null;
	}

	// complete the methods below

	// this is your wrapper method; it takes in two pieces of data rather than one
	// call your recursive add method
	public void add(String word, int lineNumber){
		this.root = add(this.root, word, lineNumber);
	}

	// your recursive method for add
	// Think about how this is slightly different the the regular add method
	// When you add the word to the index, if it already exists, 
	// you want to  add it to the IndexNode that already exists
	// otherwise make a new indexNode
	private IndexNode add(IndexNode root, String word, int lineNumber){
		if(root == null) {
			return new IndexNode(word, lineNumber);
		}
		int comparison = word.compareTo(root.word);
		if(comparison == 0) {
			root.occurences++;
			root.list.add(lineNumber);
		}
		else if(comparison < 0) {
			root.left = add(root.left, word, lineNumber);
		}
		else {
			root.right = add(root.right, word, lineNumber);
		}
		return root;
	}

	// returns true if the word is in the index
	public boolean contains(String word){
		if(root == null) {
			return false;
		}
		IndexNode current = root;
		if (current.word == word) {
			return true;
		}
		return false;
	}

	// call your recursive method; use book as guide
	public void delete(String word){
		if(root == null) {
			System.out.println("Can't Remove: " + word + " because it is an Empty Tree");
		}
		else {
			this.delete(this.root, word);
		}
	}

	// your recursive case
	// remove the word and all the entries for the word
	// This should be no different than the regular technique.
	private IndexNode delete(IndexNode root, String word){
		if(root == null) {
			return null;
		}
		int comparison = word.compareTo(root.word);
		if(comparison < 0) {
			root.left = delete(root.left, word);
			return root;
		}
		else if(comparison > 0) {
			root.right = delete(root.right, word);
			return root;
		}
		else {
			if(root.left == null && root.right == null) {
				return null;
			}
			else if(root.left != null && root.right == null) {
				return root.left;
			}
			else if(root.left == null && root.right != null) {
				return root.right;
			}
			else {
				if (root.left.right == null) {
					root.word = root.left.word;
					root.left = root.left.left;
					return root;
				} else {
					IndexNode first = root.left;
					IndexNode second = root.left.right;
					while (second.right != null) {
						first = first.right;
						second = second.right;
					}
					root.word = second.word;
					first.right = second.left;
					return root;
				}
			}
			}
		}

		public void printIndex() {
			printIndex(root);
		}

		// prints all the words in the index in inorder order
		// To successfully print it out
		// this should print out each word followed by the number of occurrences and the list of all occurrences
		// each word and its data gets its own line
		public void printIndex(IndexNode root) {
			if (root != null) {
				printIndex(root.left);
				System.out.println(root);
				printIndex(root.right);
			}
		}

		public static void main(String[] args) {
			IndexTree index = new IndexTree();

			// File output = New File(filename)
			// PrintStream ps = new PrintStream(out);
			//try {
			//
			//}
			// ps. print
	}
