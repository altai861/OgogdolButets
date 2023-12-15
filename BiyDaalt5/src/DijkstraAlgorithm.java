import java.util.*;

public class DijkstraAlgorithm {
	private int[][] graph;
	private int[] dist;
	private boolean[] visited;
	private int[] previous;
	
    public DijkstraAlgorithm(int[][] graph) {
    	this.graph = graph;
    	this.dist = new int[graph.length];
    	this.visited = new boolean[graph.length];
    	this.previous = new int[graph.length];
    	Arrays.fill(dist, Integer.MAX_VALUE);
    	Arrays.fill(previous, -1);
    }
    
    
    public void runDijkstra(int start) {
    	Arrays.fill(visited, false);
    	dist[start] = 0;
    	
    	
    	for (int i = 0; i < graph.length - 1; i++) {
    		int u = minDistance();
    		visited[u] = true;
    		
    		for (int v = 0; v < graph.length; v++) {
    			if (!visited[v] && graph[u][v] != 0 && 
    					dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
    				dist[v] = dist[u] + graph[u][v];
    				previous[v] = u;
    			}
    		}
    	}
    }
    
    public Stack<Integer> getShortestPath(int start, int end) {
        Stack<Integer> path = new Stack<>();
        int current = end;

        while (current != start) {
            path.push(current);
            current = previous[current];
        }
        path.push(start);

        return path;
    }
    
    
    private int minDistance() {
    	int min = Integer.MAX_VALUE;
    	int minIndex = -1;
    	
    	for (int v = 0; v < graph.length; v++) {
    		if (!visited[v] && dist[v] <= min) {
    			min = dist[v];
    			minIndex = v;
    		}
    	}
    	
    	return minIndex;
    }
}
