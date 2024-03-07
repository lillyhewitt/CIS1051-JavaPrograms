public class Palindrome {

    public static boolean isPalindrome(String word){
        Stack<Character> stack = new Stack<>();
        String cleanedWord = "";
        String reversed = "";

        for (int i = 0; i < word.length(); i++) {
            if(Character.isAlphabetic(word.charAt(i))) {
                cleanedWord += word.charAt(i);
                stack.push(word.charAt(i));
            }
        }

        while(!stack.isEmpty()) {
            reversed += stack.pop();
        }

        return cleanedWord.equals(reversed);
    }

    public static void main(String[] args) {
        String[] words = {"radar", "racecar", "taco cat", "a man, a plan, a canal, panama", "laser", "attack", "dawn"};
        for(String word: words){
            System.out.println(isPalindrome(word));
        }
    }
}
