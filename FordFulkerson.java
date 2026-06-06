import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FordFulkerson {
    
    int Vertex;

    // Constructor
    public FordFulkerson(int v) {
        this.Vertex = v;
    }
    
    boolean BFS(int rGraph[][], int s, int t, int parent[]) {
        boolean visited[] = new boolean[Vertex];

        for(int i=0;i<Vertex;i++) {
            visited[i] = false;
        }

        // Create Queue for BFS
        Queue<Integer> queue = new LinkedList<>();
        
        // Insert Source node
        queue.add(s);

        // Make source is visited
        visited[s] = true;

        // Source has no parent
        parent[s] = -1;

        // BFS Loop
        while(!queue.isEmpty()) {
            int u = queue.poll();
            for(int v=0;v<Vertex;v++) {
                if(!visited[v] && rGraph[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
        return visited[t];
    }

    int fordFulkerson(int graph[][], int s, int t) {
        int u,v;
        int rGraph[][] = new int[Vertex][Vertex];
        for(u=0;u<Vertex;u++) {
            for(v=0;v<Vertex;v++) {
                rGraph[u][v] = graph[u][v];
            }
        }
        int parent[] = new int[Vertex];

        int max_flow = 0;

        while(BFS(rGraph, s, t, parent)) {
            // Find minimum capacity of a path
            int path_flow = Integer.MAX_VALUE;

            // Find Bottleneck Capacity
            for(v=t;v!=s;v=parent[v]) {
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);
            }

            // Update Residual Capacity
            for( v=t;v!=s;v=parent[v]) {
                u = parent[v];
                // Reduce forward edge capacity
                rGraph[u][v] -= path_flow;

                // Increace reverse edge capacity
                rGraph[v][u] += path_flow;
            }
            // Add path flow to max flow
            max_flow += path_flow;
        }

        return max_flow;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int vertices = sc.nextInt();

        int[][] graph = new int[vertices][vertices];

        // Input capacity matrix
        System.out.println("Enter capacity matrix:");

        for(int i = 0; i < vertices; i++) {
            for(int j = 0; j < vertices; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        System.out.print("Enter Source Vertex: ");
        int source = sc.nextInt();
        
        System.out.print("Enter Sink Vertex: ");
        int sink = sc.nextInt();

        sc.close();

        // Create Object
        FordFulkerson m = new FordFulkerson(vertices);

        int result = m.fordFulkerson(graph, source, sink);
        System.out.println("Maximum Flow is: "+ result);
    }
}