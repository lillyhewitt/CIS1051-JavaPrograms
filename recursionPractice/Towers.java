import java.util.Stack;

public class Towers<E> {
    /* goal: move pile from first to third peg
    rules: move one at a time; cannot place bigger on smaller
    base case: 1

    amount of steps taken for each n (1,2,3,4): 1,3,7,15
    # of moves need to make: (2^n)-1
    Big O Notation: O(2^n)*/
    static Stack<Integer> A = new Stack<>();
    static Stack<Integer> B = new Stack<>();
    static Stack<Integer> C = new Stack<>();

    public static void move(int n, Stack<Integer> start, Stack<Integer> dest, Stack<Integer> aux) {
        if(n >= 1) {
            // move n-1 disks from source to auxiliary, so they are out of way
            move(n-1, start, aux, dest);

            // move the nth disk from source to target
            dest.push(start.pop());

            // print out stacks (progress)
            System.out.println("Stack 1: " + A.toString());
            System.out.println("Stack 2: " + B.toString());
            System.out.println("Stack 3: " + C.toString());
            System.out.println();

            // move n-1 disks that we left on auxiliary onto target
            move(n-1, aux, dest, start);
        }

    }

    public static void main(String[] args) {
        int n = 3;
        for (int i = 1; i < n+1; i++) {
            A.push(i);
        }

        System.out.println("Stack 1: " + A.toString());
        System.out.println("Stack 2: " + B.toString());
        System.out.println("Stack 3: " + C.toString());
        System.out.println();

        move(3, A, C, B);
    }
}
