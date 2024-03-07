    /*
    graph: set of vertices and set of edges between pair of vertices
        - both sets must be finite or empty (if vertices is empty, edges must be empty)
        - uses
            - determine if one node in a network is connected to all the others
            - map out multiple course prerequisites (if graph is directed with no cycles)
            - find shortest route from one city to another (least cost/shortest path in weighted graph)
    nodes/vertices: points/labeled circles
    edges/relations: paths/connections between vertices (line segments joining vertices)
        - at least one edge from a given vertex to another
        - each edge represented by connection of two vertices
    weights: associated values on the edges in a graph
    one or two-way relationship

    G = { V, E}
    V = {Alice, Carol, Ben}
    E = {(Alice, Ben)} // Alice and Ben have some kind of relationship
        - simple graphs: one ege from a given vertex to another vertex

     Weighted Graph: graph with weighted edges
     Undirected Graph: graph with undirected edges
        - represented as lines with no arrowheads
        - connected graph: a path from every vertex to every other vertex
     Directed Graphs: graph with directed edges
        - directed edges: an edge from A to B does not necessarily guarantee there is a path in both directions
            - one-way street; represented as lines with an arrowhead on one end
            - represented by ordered paris of vertices {source, destination};
        - adjacent vertex: when you can move from one vertex to another moving one edge
        - path: sequence of vertices where each successive vertex is adjacent to its predecessor
        - simple path: vertices and edges are distinct except first and last vertex may be the same
            - cycle: type of simple path where first and final vertices are the same
     Tree: special case of a graph
        - connected and contains no cycles
        - one vertex is the root

     Implementation
        - edges represented by array of lists called adjacency lists where each list stores
            vertices adjacent to a particular vertex
        - OR edges represented by 2D array called adjacency matrix with |V| rows and |V| columns
            - for unweighted graphs: entries can be boolean/int values (use int for matrix multiplication)
                - true/1 if edge exists; false/0 if no edge
                - matrix is symmetric
                - contains value 1.0 for presence of an edge
            - for weighted graphs: matrix contains weights
                - 0 is valid weight (Double.POSITIVE_INFINITY represents absence of an edge)

    Time Efficiency: depends on algorithm and density of graph
        - density is the ratio of |E| to |V|^2
            - dense graph: |E| close to, but less than |V|^2
            - sparse graph: |E| is much less than |V|^2
            - |E|: O(|V|^2) for a dense graph; O(|V|) for a sparse graph

    implementation
    for each vertex u in graph
        for each vertex v adjacent to u
            do something with edge(u,v)
     for an adjacency list: combining both steps represents examining each edge in graph, giving O(|E|)
        step 1: O(|V|)
        step 2: O(|Eu|)
            - Eu: number of edges originate at vertex u
     for an adjacency matrix: combining both steps represents examing each edge in graph, giving O(|V|^2)
        - gives better performance in sparse graph (whereas dense graphs have same performance in both representations)

     OR

     for each vertex u in some subset of vertices
        for each vertex v in some subset of vertices
            if (u,v) is an edge
                do something with edge(u,v)
     for an adjacency matrix: if(u,v) is an edge tests a matrix value and is O(1)
        - overall algorithm is O(|V|^2)
        - better performance for a dense graph; same performance for both forms in a sparse graph
     for an adjacency list: if(u,v) is an edge searches a list and is O(|Eu|)
        - combining steps 2 and 3 is O(|E|)
        - overall algorithm is O(|V||E|)

     Time efficiency:
     - dense graph: adjacency matrix is better
     - sparse graph: adjacency list is better
        - leads to sparse matrix or one with many POSTIVE_INFINITY entries
            - those entries are not included in list representation and dont effect processing time
     - adjacency matrix: storage allocated for all vertex combinations (or atleast half)
        - storage required is proportional to |V|^2
        - for a sparse graph there is a lot of wasted space
     - adjacency list: each edge represented by reference to an Edge object containing data about
        the source, destination, and weight
            - reference to next edge in the list
            - has four times as much information as is stored in matrix representation (only stores weight)
     - break-even point in terms of storage efficiency: 25% of adjacency matrix is filled with meaningful data

     - directed acyclic graph (DAG): directed graphs with no cycles
     - topological sort: vertices of DAG ordered such that (u,v) is an edge, u appears before v
        - must be true for all edges
        - many valid paths through DAG and many valid topographical sorts of DAG
     traversals: visiting each vertex in systematic order
        breadth-first search: visit all nodes for which shortest path from start node is length k
         before we visit any node for which the shortest path from start node is length k+1
         - no special start vertex: arbitrarily choose vertex with label 0
         1. visit start node first
         2. visit all noes adjacent to start node
         3. visit all nodes reached by path from start node containing two edges
         4. visit all nodes reached by path from start node containing three edges and so on...
        depth-first search:
         1. start at a vertex
         2. visit it
         3. choose one adjacent vertex to visit
         4. choose vertex adjacent to that vertex to visit and so on... until you go not further
         5. back up and see whether a new vertex can be found
         */

        /*
        BFS
        take arbitrary start vertex and mark it identified (color light blue)
        place start vertex in queue
        while (queue is not empty)
                take vertex u out of queue and visit u
                for all vertices v adjacent to vertex u
                        if v has not been identified or visited
                                mark as identified (Color light blue)
                                insert vertex v in queue
                finished visiting u (Color dark blue)

        DFS
        discovery order: order in which vertices are discovered
        finish order: order in which vertices are finishes
        back edges: connect vertex with its ancestors in DFS tree

        mark current vertex u visited (color light blue)
        enter vertex in discovery order list
        for each vertex v adjacent to current vertex u
                if v has not been visited
                        set parent of v to u
                        recursively apply this algorithm starting at v
         mark u finished (color dark blue)
         enter u into finish order list
     */
    // Dijkstra's algorithm: finds shortest path in weighted directed graph

import java.awt.Dimension;
        import java.io.BufferedReader;
        import java.io.FileNotFoundException;
        import java.io.FileReader;
        import java.util.ArrayList;
        import java.util.Random;
        import javax.swing.JFrame;
        import edu.uci.ics.jung.algorithms.layout.CircleLayout;
        import edu.uci.ics.jung.algorithms.layout.Layout;
        import edu.uci.ics.jung.graph.DirectedSparseGraph;
        import edu.uci.ics.jung.graph.Graph;
        import edu.uci.ics.jung.visualization.BasicVisualizationServer;
        import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
        import java.awt.Dimension;
        import java.io.BufferedReader;
        import java.io.FileNotFoundException;
        import java.io.FileReader;
        import java.util.ArrayList;
        import java.util.Random;
        import javax.swing.JFrame;
/*
Solve problem on 79 Project Euler. This is a Graph problem in disguise, so
the key is to figure out what is node and what is an edge. In other words, what
are the things that have relationships in this problem and what defines their
relationships?
Once you figure that out, you can use topological sort to solve the problem.
Create an account on the site to confirm your answer.
You can do this in any language, but a key to solving the problem is finding
the indegree of each node. The indegree of a node in a directed graph is the
number of edges that have that node as a destination,1 or the number of edges
that lead into the node. JUNG has a built in method for graphs to do this.
The other thing to keep in mind for JUNG is that you can’t make two edges
with the same value. So if you’re using a String to represent edges, you can’t
have two edges with the value of "A".
 */
public class Graphs {
    ArrayList<String> list = new ArrayList<String>();
    Graph<String, Integer> g = new DirectedSparseGraph<String, Integer>();

    public static void main(String[] args) {
        Graphs graph = new Graphs();
        graph.Read();
        String pass = graph.Solve();
        System.out.println("The passcode:" + pass);
    }

    //takes the number of verteces in the graph and creates a string from least to greatest incoming verteces
    //example: the first node would have no incoming vertices so it is the first in the string and so on
    public String Solve() {
        StringBuilder b =  new StringBuilder();
        int totalvertex = g.getVertexCount();
        String vertices = vertices();

        for(int i = 0; i < totalvertex; i++) {
            for(int j = 0; j < vertices.length(); j++) {
                String f = Character.toString(vertices.charAt(j));
                int x = g.getPredecessorCount(f);
                if(x == i) {
                    b.append(f);
                }
            }
        }
        return b.toString();
    }

    //creates string of the vertices
    public String vertices() {
        StringBuilder b =  new StringBuilder();
        for(int i = 0; i < g.getVertexCount(); i++) {
           // String vertices = (String) graph1.g().toArray()[i];
           // b.append(vertices);
        }

        return b.toString();
    }

    //called on by read
    //adds each line into the graph
    public void MergeGraphs(String line) {

        //random weights generator
        Random generator = new Random();
        int y = -1;
        int z = -1;
        int x = -1;

        while(x <= 0 && y <= 0 && z<=0) {
            x = generator.nextInt();
            y = generator.nextInt();
            z = generator.nextInt();
        }

        //adding to graph
        //index 0 -> indexes 1,2 and index 1 -> index 2
        String indexzero = Character.toString(line.charAt(0));
        String indexone = Character.toString(line.charAt(1));
        String indextwo = Character.toString(line.charAt(2));

        //graph1.addEdge(x, indexzero, indexone);
        //graph1.addEdge(y, indexzero, indextwo);
       // graph1.addEdge(z, indexone, indextwo);



    }

    //reads in the file
    public void Read() {
        String fileName = "key.txt";
        String line = "";
        BufferedReader bufferedReader;

        try {
            bufferedReader= new BufferedReader(new FileReader(fileName));
            while(bufferedReader.ready())
            {
                line = bufferedReader.readLine();
                //adds line to a list
                list.add(line);
                //calls mergegraphs to add the line into the graph
                MergeGraphs(line);
            }

            bufferedReader.close();
        }
        catch(FileNotFoundException e) {
            System.err.println("File not found");
        }
        // catch any other exception
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}

