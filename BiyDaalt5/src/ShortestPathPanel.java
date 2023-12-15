
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Stack;

public class ShortestPathPanel extends JPanel {
	private int[][] adjacencyMatrix;
    private Stack<Integer> shortestPath;

    private static final int NODE_RADIUS = 30;
    
    public ShortestPathPanel(int[][] adjacencyMatrix, Stack<Integer> shortestPath) {
        this.adjacencyMatrix = adjacencyMatrix;
        this.shortestPath = shortestPath;
        System.out.println(shortestPath.toString() + "INIT");
    }
    
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
		
		if (shortestPath != null) {
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
