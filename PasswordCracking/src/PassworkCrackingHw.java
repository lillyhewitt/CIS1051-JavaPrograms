import edu.uci.ics.jung.graph.DirectedSparseGraph;
import java.io.*;
import java.util.Scanner;

public class PasswordCrackingHW {
    // solves Problem Euler 79
    public static void main(String[] args) {
        DirectedSparseGraph<Integer, String> g = new DirectedSparseGraph<>();
        String passcode = "";

        String fileName = "keylog.txt";
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while(scanner.hasNextLine()) {
                // reads each line in file
                String line = scanner.nextLine();
                // splits the integer into three integers (chars)
                int firstDig = Integer.parseInt(String.valueOf(line.charAt(0)));
                int secondDig = Integer.parseInt(String.valueOf(line.charAt(1)));
                int thirdDig = Integer.parseInt(String.valueOf(line.charAt(2)));
                // connect the values using a graph structure
                g.addEdge(firstDig + "" + secondDig, firstDig, secondDig);
                g.addEdge(secondDig + "" + thirdDig, secondDig, thirdDig);
            }
            scanner.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        // when there are no more incoming vertices, add the last one
        while (g.getEdgeCount() != 1) {
            for (int v: g.getVertices()) {
                if (g.getInEdges(v).size() == 0) {
                    passcode += v;
                    break;
                }
            }
            // removes the value from the graph
            g.removeVertex(Integer.parseInt(String.valueOf(passcode.charAt(passcode.length()-1))));
        }
        passcode += g.getEdges().toArray()[0];
        System.out.println("The shortest passcode is: " + passcode); // 73162890
    }
}
