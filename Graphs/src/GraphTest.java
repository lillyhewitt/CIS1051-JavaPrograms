import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;

import java.util.*;

public class GraphTest {
        // traversal through all nodes on one level before the next
        public static <V,E> Set<V> BreadthFirstSearch(Graph<V, E> g, V start) {
                Queue<V> q =  new LinkedList<V>();
                ArrayList<Integer> visited = new ArrayList<>();
                Set<V> identified =  new HashSet();

                if(start == null) {
                        start = (V) g.getVertices().toArray()[0];
                }
                q.add(start);
                identified.add(start);

                // looks at one level at a time
                while(!q.isEmpty()){
                        V vertex = q.poll();
                        visited.add((Integer) vertex);
                        for(V neighbor : g.getNeighbors(vertex)) {
                                if (!identified.contains(neighbor) && !visited.contains(neighbor)) {
                                        identified.add(neighbor);
                                        q.add(neighbor);
                                }
                        }
                }
                return identified;
        }

        // traversal through the root node then others until no univisited nearby nodes
        public static <V,E> Set<V> dfs(Graph<V,E> g, V start){
                LinkedList<V> discoveryOrder = new LinkedList<>();
                LinkedList<V> finishOrder = new LinkedList<>();
                Set<V> visited = new HashSet();
                Set<V> finished = new HashSet<>();

                if(start == null) {
                        start = (V) g.getVertices().toArray()[0];
                }
                DepthFirstSearch(g, start, discoveryOrder, finishOrder, visited, finished);
                return finished;
        }

        public static <V,E> void DepthFirstSearch(Graph<V, E> g, V current, LinkedList<V> discoveryOrder, LinkedList<V> finishOrder, Set<V> visited, Set<V> finished){
                visited.add(current);
                discoveryOrder.add(current);
                for (V neighbor : g.getNeighbors(current)) {
                        if (!visited.contains(neighbor)) {
                                DepthFirstSearch(g, neighbor, discoveryOrder, finishOrder, visited, finished);
                        }
                }
                finished.add(current);
                finishOrder.add(current);
        }

        public static void main(String[] args) {
                Graph<Integer, String> g = new SparseGraph<>();
                g.addEdge("A",0, 1);
                g.addEdge("A2",0, 3);
                g.addEdge("A3", 0, 4);
                g.addEdge("E",4, 5);
                g.addEdge("D",3, 5);
                g.addEdge("B",1, 2);
                g.addEdge("B2",1, 0);
                g.addEdge("C",2, 1);
                g.addEdge("E2",4, 1);
                g.addEdge("D2", 3, 1);
                g.addEdge("F",5, 4);
                g.addEdge("F2",5, 3);

                System.out.println("Oringinal graph\n" + g + "\n");

                Set<Integer> bfsGraph = BreadthFirstSearch(g, null);
                System.out.println("Sorted using Breadth First Search: " + bfsGraph + "\n");

                Set<Integer> dfsGraph = dfs(g, null);
                System.out.println("Sorted using Depth First Search: " + dfsGraph + "\n");
        }
}
