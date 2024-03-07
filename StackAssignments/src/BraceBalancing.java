public class BraceBalancing {

    public static boolean isBalanced(String expression) {
        Stack<Character> stack = new Stack<>();
        for (char c : expression.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }
            else if(c == ')' || c == '}' || c == ']'){
                if(stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if( (top == '(' && c != ')') || (top == '{' && c != '}') || (top == '[' && c != ']')){
                    return false;
                }
            }
        }
        if(!stack.isEmpty()){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String expression = "1+(2+3)+(5+7)";
        System.out.println(isBalanced(expression));
    }
}
