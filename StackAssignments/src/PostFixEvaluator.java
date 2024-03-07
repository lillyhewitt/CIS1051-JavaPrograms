import java.util.Scanner;

public class PostFixEvaluator {
    // 2+3 == 2 3 +
    // 1+(1+3)*4 == 1 3 +  4 * 1 +
    //              1 + 3 = 4
    //              4 * 4 = 16
    //              16 + 1 = 17
    /*
       3*6+2*4 == 3 6 * 2 4 * +
                3 * 6 = 18
                2 * 4 = 8
                18 + 8 = 26
     */

    public static void main(String[] args) {
        String expression = "3 6 * 2 4 * +";
        Stack<Integer> stack = new Stack<>();
        Scanner scanner = new Scanner(expression);
        while(scanner.hasNext()){
            String chunk = scanner.next();
            if(chunk.equals("+")) {
                int right = stack.pop();
                int left = stack.pop();
                stack.push(left + right);
            } else if(chunk.equals("-")) {
                int right = stack.pop();
                int left = stack.pop();
                stack.push(left - right);
            } else if(chunk.equals("*")){
                int right = stack.pop();
                int left = stack.pop();
                stack.push(left * right);
            } else if(chunk.equals("/")){
                int right = stack.pop();
                int left = stack.pop();
                stack.push(left / right);
            } else if(chunk.equals('%')){
                int right = stack.pop();
                int left = stack.pop();
                stack.push(left % right);
            }
            else{
                stack.push(Integer.parseInt(chunk));
            }
            System.out.println(stack.peek());
        }
    }
}
