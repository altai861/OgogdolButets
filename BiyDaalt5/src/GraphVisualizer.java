import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Stack;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphVisualizer extends JFrame {
	private int[][] adjacencyMatrix;
	private int startNode;
	private int endNode;
	public Stack<Integer> shortestPath;
	
	public GraphVisualizer(int[][] adjacencyMatrix) {
		this.adjacencyMatrix = adjacencyMatrix;
		
		//this.startNode = 4;
		//this.endNode = 6;
		this.shortestPath = new Stack<Integer>();
		//this.shortestPath.push(4);
		//this.shortestPath.push(5);
		//xsthis.shortestPath.push(6);
		
		setTitle("Dijkstra's Algorithm");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		GraphPanel p = new GraphPanel();
		add(p);
		
		JPanel inputPanel = new JPanel();
		JTextField startTextField = new JTextField(5);
		JTextField endTextField = new JTextField(5);
		JButton calculateButton = new JButton("Calculate Shortest Path");
		
		calculateButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try {
		            startNode = Integer.parseInt(startTextField.getText());
		            endNode = Integer.parseInt(endTextField.getText());

		            if (startNode < 0 || startNode >= adjacencyMatrix.length || endNode < 0 || endNode >= adjacencyMatrix.length) {
		                JOptionPane.showMessageDialog(GraphVisualizer.this, "Invalid input");
		                startNode = -1;
		                endNode = -1;
		            } else {
		                System.out.println("Nice inputs");
		                DijkstraAlgorithm dk = new DijkstraAlgorithm(adjacencyMatrix);
		                dk.runDijkstra(startNode);
		                shortestPath = dk.getShortestPath(startNode, endNode);
		                System.out.println("asdas");
		                System.out.println(shortestPath.toString());
		                Stack<Integer> c = new Stack<Integer>();
		                c = shortestPath;
		                int pathLength = shortestPathLength(c);
		                JOptionPane.showMessageDialog(GraphVisualizer.this, "The length of the path: " + pathLength);
		                System.out.println(shortestPath.toString());
		                // Repaint on the Event Dispatch Thread
		                SwingUtilities.invokeLater(() -> p.repaint());
		                System.out.println(c.toString());
		                //ShortestPathPanel shortestPathPanel = new ShortestPathPanel(adjacencyMatrix, shortestPath);
		                
		                //JFrame sf = new JFrame("Shortest path");
		                //sf.setSize(600, 600);
		                //sf.setLocationRelativeTo(null);
		                //sf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		                
		                //sf.add(shortestPathPanel);
		                //sf.setVisible(true);
		                
		            }

		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(GraphVisualizer.this, "Invalid input");
		            startNode = -1;
		            endNode = -1;
		        }
		    }
		});

		
		inputPanel.add(new JLabel("Start node: "));
		inputPanel.add(startTextField);
		inputPanel.add(new JLabel("End node: "));
		inputPanel.add(endTextField);
		
		inputPanel.add(calculateButton);
		
		add(inputPanel, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	public int shortestPathLength(Stack<Integer> st) {
	    Stack<Integer> stackCopy = new Stack<>();
	    stackCopy.addAll(st); // Create a copy of the original stack

	    int[] a = new int[stackCopy.size()];
	    int len = 0;
	    int i = 0;

	    while (!stackCopy.isEmpty()) {
	        a[i] = stackCopy.pop();
	        i++;
	    }

	    for (int j = 1; j < a.length; j++) {
	        System.out.println(a[j]);
	        len += adjacencyMatrix[a[j]][a[j - 1]];
	    }

	    return len;
	}

	
	private class GraphPanel extends JPanel {
		private static final int NODE_RADIUS = 30;
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			Graphics2D g2d = (Graphics2D) g;
			
			int numNodes = adjacencyMatrix.length;
			int xCenter = getWidth() / 2;
			int yCenter = getHeight() / 2;
			

			
			
			// draw edges
			
			for (int i = 0; i < numNodes; i++) {
				for (int j = i + 1; j < numNodes; j++) {
					if (adjacencyMatrix[i][j] != 0) {
						double angle1 = 2 * Math.PI * i / numNodes;
						double angle2 = 2 * Math.PI * j / numNodes;
						
						int x1 = (int) (xCenter + Math.cos(angle1) * xCenter * 0.8);
						int y1 = (int) (yCenter + Math.sin(angle1) * yCenter * 0.8);
						int x2 = (int) (xCenter + Math.cos(angle2) * xCenter * 0.8);
						int y2 = (int) (yCenter + Math.sin(angle2) * yCenter * 0.8);
						
						
						g2d.setColor(Color.BLACK);
						g2d.drawLine(x1, y1, x2, y2);
						
						String weight = Integer.toString(adjacencyMatrix[i][j]);
						int weightX = (x1 + x2) / 2;
						int weightY = (y1 + y2) / 2;
						g2d.drawString(weight, weightX, weightY);

						
					}
				}
			}
			
			// draw nodes
			for (int i = 0; i < numNodes; i++) {
				double angle  = 2 * Math.PI * i / numNodes;
				int x = (int) (xCenter + Math.cos(angle) * xCenter * 0.8);
				int y = (int) (yCenter + Math.sin(angle) * yCenter * 0.8);
				
				Ellipse2D node = new Ellipse2D.Double(x - NODE_RADIUS, y - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);
				g2d.setColor(Color.cyan);
				g2d.fill(node);
				g2d.setColor(Color.darkGray);
				g2d.draw(node);
				
				
				// Draw node label
				String label = Integer.toString(i);
				FontMetrics metrics = g.getFontMetrics();
				int labelWidth = metrics.stringWidth(label);
				int labelHeight = metrics.getHeight();
				g2d.drawString(label, x - labelWidth / 2, y + labelHeight / 2);
			}
			
			if (startNode != -1 && endNode != -1 && shortestPath != null) {
	            g2d.setStroke(new BasicStroke(3));
	            g2d.setColor(Color.BLUE);

	            int prevNode = -1;
	            while (!shortestPath.isEmpty()) {
	                int currentNode = shortestPath.pop();
	                if (prevNode != -1) {
	                    int startX = (int) (xCenter + Math.cos(2 * Math.PI * prevNode / numNodes) * xCenter * 0.8);
	                    int startY = (int) (yCenter + Math.sin(2 * Math.PI * prevNode / numNodes) * yCenter * 0.8);

	                    int endX = (int) (xCenter + Math.cos(2 * Math.PI * currentNode / numNodes) * xCenter * 0.8);
	                    int endY = (int) (yCenter + Math.sin(2 * Math.PI * currentNode / numNodes) * yCenter * 0.8);

	                    g2d.drawLine(startX, startY, endX, endY);
	                }
	                prevNode = currentNode;
	            }

	            g2d.setStroke(new BasicStroke(1));
	        }
			
			
		}
	}
	
	public static void main(String[] args) {
		int[][] adjacencyMatrix = {
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
		SwingUtilities.invokeLater(() -> new GraphVisualizer(adjacencyMatrix));
	}
	
	
}