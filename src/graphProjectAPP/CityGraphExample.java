package graphProjectAPP;

import City.City;
import Model.*;
import algorithm.AlgorithemsResults.AlgorithmResult;
import service.RandomizeEdgesDirections;
import service.UndirectedEdges;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CityGraphExample extends JPanel {
    private City myCity;
    private boolean kruskal = false;
    private boolean dijkstra = false;
    private boolean floydWarshall = false;
    private boolean drawEdges = true;
    private Node startNode = null;
    private Node endNode = null;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Initialize City here when we know the actual size
        myCity = City.getCityInstance();

        // Enable anti-aliasing for smoother graphics
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if(drawEdges){
            // Draw Road Edges
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2)); // ← thickness in pixels
            ArrayList<Edge> roadEdges =  myCity.getGraph().getRoadEdges();
            for (Edge e : roadEdges) {
                g2d.drawLine(e.getX1(), e.getY1(), e.getX2(), e.getY2());

                // If the edge is directed, draw an arrow
                if (e.isDirected()) {
                    // Save the current stroke to restore later
                    Stroke originalStroke = g2d.getStroke();

                    // Use a thicker stroke for the arrow
                    g2d.setStroke(new BasicStroke(2));

                    // Use red color for arrows to make them visible
                    g2d.setColor(Color.RED);

                    drawArrow(g2d, e, Color.LIGHT_GRAY, Color.DARK_GRAY);

                    // Restore original stroke and color
                    g2d.setStroke(originalStroke);
                    g2d.setColor(Color.BLACK);
                }
            }
        }

        if (kruskal) {
            AlgorithmResult res = myCity.runKruskals();
            double distance = res.getTotalCost();
            drawText(g2d, distance, "kruskal`s");

            // ----------------
            g2d.setColor(new Color(0, 119, 190));
            g2d.setStroke(new BasicStroke(3));


            for (Edge e : res.getEdges()) {
                g2d.drawLine(e.getX1(), e.getY1(), e.getX2(), e.getY2());
            }
        } else if (dijkstra) {
            //   Draw Road to the shortest distance between two randomly chosen nodes
            AlgorithmResult res = myCity.runDijkstra();
            double distance = res.getTotalCost();
            startNode = res.getStartNode();
            endNode = res.getEndNode();

            drawText(g2d, distance, "Dijkstra");

            g2d.setStroke(new BasicStroke(3)); // ← thickness in pixels
            g2d.setColor(Color.red);
            for (Edge e : res.getEdges()) {
                g2d.drawLine(e.getX1(), e.getY1(), e.getX2(), e.getY2());
                if (e.isDirected()) {
                    drawArrow(g2d, e, Color.green, Color.green);
                }
            }
        } else if(floydWarshall){
            //   Draw Road to the shortest distance between two randomly chosen nodes
            AlgorithmResult res = myCity.runFloydWarshall();
            double distance = 0;
            ArrayList<Edge> roadEdges = null;
            if(res != null){
                distance = res.getTotalCost();
                startNode = res.getStartNode();
                endNode = res.getEndNode();
                roadEdges = res.getEdges();
            }

            drawText(g2d, distance, "Floyd Warshall");

            g2d.setStroke(new BasicStroke(3)); // ← thickness in pixels
            g2d.setColor(Color.red);
            if(roadEdges != null){
                for (Edge e : roadEdges) {
                    g2d.drawLine(e.getX1(), e.getY1(), e.getX2(), e.getY2());
                    if (e.isDirected()) {
                        drawArrow(g2d, e, Color.green, Color.green);
                    }
                }
            }
        }

        // Draw city nodes with names
        ArrayList<Node> data = myCity.getGraph().getCities();
        for (Node city : data) {
            // Draw larger circle for city node
            int nodeSize = 40; // Larger size for the circle
            paintCircle(g2d, city);

            g2d.fillOval(city.getX() - nodeSize/2, city.getY() - nodeSize/2, nodeSize, nodeSize);

            // Draw border for the circle
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawOval(city.getX() - nodeSize/2, city.getY() - nodeSize/2, nodeSize, nodeSize);

            // Draw city name inside the circle
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 14));

            // Center the text
            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(city.getName());
            int textHeight = fm.getHeight();
            int textX = city.getX() - textWidth/2;
            int textY = city.getY() + textHeight/4; // Adjust for baseline

            g2d.drawString(city.getName(), textX, textY);
        }

        // Draw Road nodes
        ArrayList<Node> data2 = myCity.getGraph().getRoadNodes();
        for (Node city : data2) {
            int nodeSize = 20;
            paintCircle(g2d, city);

            g2d.fillOval(city.getX() - nodeSize/2, city.getY() - nodeSize/2, nodeSize, nodeSize);

            // Draw border for the circle
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawOval(city.getX() - nodeSize/2, city.getY() - nodeSize/2, nodeSize, nodeSize);

            g2d.drawString(city.getName(), city.getX(), city.getY());
//            g2d.drawString("", city.x, city.y);
        }

    }

    private void paintCircle(Graphics2D g2d, Node node) {
        if(node == startNode){
            g2d.setColor(Color.blue);
        } else if(node == endNode){
            g2d.setColor(Color.pink);
        } else {
            g2d.setColor(Color.GRAY); // Steel blue color
        }
    }

    private void drawArrow(Graphics2D g2d, Edge edge, Color fColor, Color bColor) {
        // Determine which coordinates correspond to fromV and toV
        int fromX, fromY, toX, toY;

        // Check which endpoint matches fromV
        Node fromNode = myCity.getGraph().getNode(edge.getFromV());
        Node toNode = myCity.getGraph().getNode(edge.getToV());

        if (fromNode != null && toNode != null) {
            fromX = fromNode.getX();
            fromY = fromNode.getY();
            toX = toNode.getX();
            toY = toNode.getY();
        } else {
            // Fallback to original coordinates
            fromX = edge.getX1();
            fromY = edge.getY1();
            toX = edge.getX2();
            toY = edge.getY2();
        }

        // Now draw arrow from fromX,fromY to toX,toY
        double dx = toX - fromX;
        double dy = toY - fromY;
        double length = Math.sqrt(dx * dx + dy * dy);

        if (length == 0) return; // Avoid division by zero

        // Normalize direction vector
        dx = dx / length;
        dy = dy / length;

        // Arrow parameters
        int arrowSize = 15;
        double arrowPosition = 0.7; // Position from start (70%)

        // Calculate arrow position
        int arrowX = (int)(fromX + (toX - fromX) * arrowPosition);
        int arrowY = (int)(fromY + (toY - fromY) * arrowPosition);

        // Calculate perpendicular vector for arrow wings
        double perpX = -dy;
        double perpY = dx;

        // Create arrowhead polygon
        Polygon arrow = new Polygon();

        // Tip of arrow
        arrow.addPoint(arrowX, arrowY);

        // Left wing
        int leftX = (int)(arrowX - arrowSize * dx + (double) arrowSize /2 * perpX);
        int leftY = (int)(arrowY - arrowSize * dy + (double) arrowSize/2 * perpY);
        arrow.addPoint(leftX, leftY);

        // Right wing
        int rightX = (int)(arrowX - arrowSize * dx - (double) arrowSize/2 * perpX);
        int rightY = (int)(arrowY - arrowSize * dy - (double) arrowSize/2 * perpY);
        arrow.addPoint(rightX, rightY);

        // Save original color
        Color originalColor = g2d.getColor();

        // Draw filled arrow
        g2d.setColor(fColor);
        g2d.fill(arrow);

        // Draw outline
        g2d.setColor(bColor);
        g2d.draw(arrow);

        // Restore color
        g2d.setColor(originalColor);
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("City Simulation Graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // ================= HEADER =================
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ---------- Row 1 : Kruskal ----------
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row1.setOpaque(false);

        JLabel headerLabel1 = new JLabel(
                "Kruskal's Minimum Spanning Tree (MST) Algorithm");
        headerLabel1.setFont(new Font("Arial", Font.BOLD, 16));
        headerLabel1.setForeground(Color.BLUE);

        JButton runButton = new JButton("Run");

        row1.add(headerLabel1);
        row1.add(runButton);

        // ---------- Row 2 : Edges ----------
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row2.setOpaque(false);

        JLabel edgesLabel = new JLabel("Road Edges:");
        edgesLabel.setFont(new Font("Arial", Font.BOLD, 15));

        JButton directedButton = new JButton("Directed Edges");
        JButton undirectedButton = new JButton("Undirected Edges");

        row2.add(edgesLabel);
        row2.add(directedButton);
        row2.add(undirectedButton);

        // ---------- Row 3 : Dijkstra ----------
        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row3.setOpaque(false);

        JLabel headerLabel2 = new JLabel(
                "Dijkstra's Algorithm — shortest path (Start: Blue, Destination: Pink)");
        headerLabel2.setFont(new Font("Arial", Font.BOLD, 16));

        JButton shortestPathButton = new JButton("Shortest Distance");

        row3.add(headerLabel2);
        row3.add(shortestPathButton);

        // ---------- Row 4 : Floyd Warshall ----------
        JPanel row4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row4.setOpaque(false);

        JLabel headerLabel3 = new JLabel(
                "<html>Floyd Warshall Algorithm — "
                        + "<span style='color:red;'>Pre-computed</span> "
                        + "shortest paths</html>"
        );
        headerLabel3.setFont(new Font("Arial", Font.BOLD, 16));

        JButton floydWarshallButton = new JButton("Shortest Distance");

        row4.add(headerLabel3);
        row4.add(floydWarshallButton);

        // Add rows to header
        headerPanel.add(row1);
        headerPanel.add(row2);
        headerPanel.add(row3);
        headerPanel.add(row4);

        // ================= MAIN PANEL =================
        CityGraphExample panel = new CityGraphExample();

        // ---------- Actions ----------
        runButton.addActionListener(e -> panel.runKruskals());
        directedButton.addActionListener(e -> panel.randomizeEdges());
        undirectedButton.addActionListener(e -> panel.removeRandomizeEdges());
        shortestPathButton.addActionListener(e -> panel.dijkstraShortestPath());
        floydWarshallButton.addActionListener(e -> panel.floydShortestPath());

        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public void resetAllAlgorithms() {
        kruskal = false;
        dijkstra = false;
        drawEdges = false;
        floydWarshall = false;
        myCity.deleteCachedPreComputedData();
    }

    // Method to run the kruskals Algorithm
    public void runKruskals() {
        resetAllAlgorithms();
        kruskal = true;
        repaint();  // This will trigger paintComponent() again
    }

    // Method to randomize edges and repaint
    private void randomizeEdges() {
        resetAllAlgorithms();
        if (myCity != null) {
            drawEdges = true;
            RandomizeEdgesDirections randomizeEdgesDirections = new RandomizeEdgesDirections();
            randomizeEdgesDirections.makeRandomEdgesDirections();
            repaint();  // This will trigger paintComponent() again
        }
    }

    // Method to remove randomized edges and repaint
    private void removeRandomizeEdges() {
        resetAllAlgorithms();
        if (myCity != null) {
            drawEdges = true;
            UndirectedEdges undirectedEdges = new UndirectedEdges();
            undirectedEdges.makeAllEdgesUndirected();
            repaint();  // This will trigger paintComponent() again
        }
    }

    public void dijkstraShortestPath() {
        if (myCity != null) {
            resetAllAlgorithms();
            dijkstra = true;
            drawEdges = true;
            repaint();  // This will trigger paintComponent() again
        }
    }

    public void floydShortestPath() {
        if (myCity != null) {
            resetAllAlgorithms();
            floydWarshall = true;
            drawEdges = true;
            repaint();  // This will trigger paintComponent() again
        }
    }

    public void drawText(Graphics2D g2d, double distance, String str) {
        String text;
        if(distance == 0 || distance == Double.POSITIVE_INFINITY){
            text = str + " Distance: Unreachable";
        } else {
            text = String.format(str + " Distance: %.2f", distance);
        }

        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.setColor(Color.BLACK);

        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(text);

        int padding = 15;
        int x = getWidth() - textWidth - padding; // RIGHT
        int y = padding + fm.getAscent();          // TOP

        // Optional background box
        g2d.setColor(new Color(255, 255, 255, 200));
        g2d.fillRoundRect(
                x - 10,
                y - fm.getAscent(),
                textWidth + 20,
                fm.getHeight(),
                10,
                10
        );

        g2d.setColor(Color.BLACK);
        g2d.drawString(text, x, y);
    }

}
