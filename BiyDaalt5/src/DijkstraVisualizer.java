import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class DijkstraVisualizer extends JFrame {

    private static final int INF = Integer.MAX_VALUE;

    private int[][] graph = {
            {0, 4, 0, 0, 0, 0, 0, 8, 0},
            {4, 0, 8, 0, 0, 0, 0, 11, 0},
            {0, 8, 0, 7, 0, 4, 0, 0, 2},
            {0, 0, 7, 0, 9, 14, 0, 0, 0},
            {0, 0, 0, 9, 0, 10, 0, 0, 0},
            {0, 0, 4, 14, 10, 0, 2, 0, 0},
            {0, 0, 0, 0, 0, 2, 0, 1, 6},
            {8, 11, 0, 0, 0, 0, 1, 0, 7},
            {0, 0, 2, 0, 0, 0, 6, 7, 0}
    };

    private int[] dist;
    private boolean[] visited;
    private int startNode;

    public DijkstraVisualizer() {
        setTitle("Dijkstra's Algorithm Visualizer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        dist = new int[graph.length];
        visited = new boolean[graph.length];
        startNode = 0;

        Arrays.fill(dist, INF);

        JButton runButton = new JButton("Run Dijkstra");
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runDijkstra();
                repaint();
            }
        });

        add(runButton, BorderLayout.NORTH);

        setVisible(true);
    }

    private void runDijkstra() {
        Arrays.fill(visited, false);
        dist[startNode] = 0;

        for (int i = 0; i < graph.length - 1; i++) {
            int u = minDistance();
            visited[u] = true;

            for (int v = 0; v < graph.length; v++) {
                if (!visited[v] && graph[u][v] != 0 &&
                        dist[u] != INF && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }
    }

    private int minDistance() {
        int min = INF, minIndex = -1;

        for (int v = 0; v < graph.length; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int vertexRadius = 20;

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                if (graph[i][j] != 0) {
                    drawEdge(g, i, j, graph[i][j]);
                }
            }
        }

        for (int i = 0; i < graph.length; i++) {
            drawVertex(g, i, dist[i], visited[i], vertexRadius);
        }
    }

    private void drawEdge(Graphics g, int start, int end, int weight) {
        g.setColor(Color.BLACK);
        g.drawLine(start * 50 + 30, 100, end * 50 + 30, 100);
        g.drawString(Integer.toString(weight), (start * 50 + end * 50) / 2 + 20, 80);
    }

    private void drawVertex(Graphics g, int vertex, int distance, boolean isVisited, int radius) {
        if (isVisited) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.RED);
        }

        g.fillOval(vertex * 50, 80 - radius / 2, radius, radius);
        g.setColor(Color.BLACK);
        g.drawString(Integer.toString(distance), vertex * 50 + 20, 80);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DijkstraVisualizer());
    }
}
