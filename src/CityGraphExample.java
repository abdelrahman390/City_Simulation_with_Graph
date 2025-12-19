import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CityGraphExample extends JPanel {
    City myCity;
    boolean returnShortesPath = false;
//    ArrayList<Edge> path;
    boolean kruskal = false;
    boolean dijkstra = false;
    boolean drawEdges = true;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Initialize City here when we know the actual size
//        int width = getWidth();
//        int height = getHeight();
        myCity = City.getCityInstance();

        // Enable anti-aliasing for smoother graphics
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (kruskal) {
            returnTwoValuesEdgesAndDouble res = myCity.buildPipLinesWithKruskalsAlgo();
            double distance = res.doubleValue;
            drawT(g2d, distance);

            // ----------------
            g2d.setColor(new Color(0, 119, 190));
            g2d.setStroke(new BasicStroke(3));


            for (Edge e : res.arrayList) {
                g2d.drawLine(e.x1, e.y1, e.x2, e.y2);
            }
        } else if (dijkstra || drawEdges) {
            // Draw Road Edges
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2)); // ← thickness in pixels
            ArrayList<Edge> roadEdges =  myCity.getRoadEdges();
            for (Edge e : roadEdges) {
                g2d.drawLine(e.x1, e.y1, e.x2, e.y2);

                // If the edge is directed, draw an arrow
                if (e.isDirected) {
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

            //   Draw Road to the shortest distance between two randomly chosen nodes
            if (returnShortesPath) {
                returnTwoValuesEdgesAndDouble res = myCity.GetSmallestDistanceBetweenTwoNodesRandomly();
                double distance = res.doubleValue;
                drawT(g2d, distance);

                g2d.setStroke(new BasicStroke(3)); // ← thickness in pixels
                g2d.setColor(Color.red);
                for (Edge e : res.arrayList) {
                    g2d.drawLine(e.x1, e.y1, e.x2, e.y2);
                    if (e.isDirected) {
                        drawArrow(g2d, e, Color.green, Color.green);
                    }
                }
            }
        }



        // Draw city nodes with names
        ArrayList<Node> data = myCity.getCities();
        for (Node city : data) {
            // Draw larger circle for city node
            int nodeSize = 40; // Larger size for the circle
            if(city.isStartNode){
                g2d.setColor(Color.blue);
            } else if(city.isEndNode){
                g2d.setColor(Color.pink);
            } else {
                g2d.setColor(Color.GRAY); // Steel blue color
            }
            g2d.fillOval(city.x - nodeSize/2, city.y - nodeSize/2, nodeSize, nodeSize);

            // Draw border for the circle
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawOval(city.x - nodeSize/2, city.y - nodeSize/2, nodeSize, nodeSize);

            // Draw city name inside the circle
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 14));

            // Center the text
            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(city.name);
            int textHeight = fm.getHeight();
            int textX = city.x - textWidth/2;
            int textY = city.y + textHeight/4; // Adjust for baseline

            g2d.drawString(city.name, textX, textY);
        }

        // Draw Road nodes
        ArrayList<Node> data2 = myCity.getRoadNodes();
        for (Node city : data2) {
            int nodeSize = 20;
            g2d.setColor(Color.GRAY);
            g2d.fillOval(city.x - nodeSize/2, city.y - nodeSize/2, nodeSize, nodeSize);

            // Draw border for the circle
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawOval(city.x - nodeSize/2, city.y - nodeSize/2, nodeSize, nodeSize);

            g2d.drawString(city.name, city.x, city.y);
//            g2d.drawString("", city.x, city.y);
        }

    }

    private void drawArrow(Graphics2D g2d, Edge edge, Color fColor, Color bColor) {
        // Determine which coordinates correspond to fromV and toV
        int fromX, fromY, toX, toY;

        // Check which endpoint matches fromV
        Node fromNode = myCity.getNode(edge.fromV);
        Node toNode = myCity.getNode(edge.toV);

        if (fromNode != null && toNode != null) {
            fromX = fromNode.x;
            fromY = fromNode.y;
            toX = toNode.x;
            toY = toNode.y;
        } else {
            // Fallback to original coordinates
            fromX = edge.x1;
            fromY = edge.y1;
            toX = edge.x2;
            toY = edge.y2;
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
        int tipX = arrowX;
        int tipY = arrowY;
        arrow.addPoint(tipX, tipY);

        // Left wing
        int leftX = (int)(arrowX - arrowSize * dx + arrowSize/2 * perpX);
        int leftY = (int)(arrowY - arrowSize * dy + arrowSize/2 * perpY);
        arrow.addPoint(leftX, leftY);

        // Right wing
        int rightX = (int)(arrowX - arrowSize * dx - arrowSize/2 * perpX);
        int rightY = (int)(arrowY - arrowSize * dy - arrowSize/2 * perpY);
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

        // Create header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Use GridBagLayout for better control
        headerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // First label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel headerLabel1 = new JLabel(
                "Kruskal's Minimum Spanning Tree (MST) Algorithm");
        headerLabel1.setFont(new Font("Arial", Font.BOLD, 16));
        headerLabel1.setForeground(Color.BLUE);
        headerPanel.add(headerLabel1, gbc);

        gbc.gridx = 2;                 // next column
        gbc.gridwidth = 1;             // reset span
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;

        JPanel runPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        runPanel.setOpaque(false);     // keeps header background
        JButton runButton = new JButton("Run");
        runPanel.add(runButton);


        headerPanel.add(runPanel, gbc);

        frame.add(headerPanel, BorderLayout.NORTH);

        // RESET GridBagConstraints after commented sections
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        // Second label and button
        gbc.gridy = 1;
        gbc.gridwidth = 3;

        //  second label
        JLabel headerLabel2 = new JLabel(
                "Black lines are the road by Dijkstra's Algorithm, start city: Blue, Destination city: pink"
        );
        headerLabel2.setFont(new Font("Arial", Font.BOLD, 16));
        headerLabel2.setForeground(Color.BLACK);

        headerPanel.add(headerLabel2, gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        JButton randomizeButton = new JButton("Directed Edges");
        headerPanel.add(randomizeButton, gbc);

        // Button 2
        gbc.gridx = 1;
        JButton removeRandomizeButton = new JButton("Undirected Edges");
        headerPanel.add(removeRandomizeButton, gbc);

        // Button 3
        gbc.gridx = 2;
        JButton shortestDistanceRandomizeButton = new JButton("Shortest Distance");
        headerPanel.add(shortestDistanceRandomizeButton, gbc);


        // Add action listener to the button
        CityGraphExample panel = new CityGraphExample();
        runButton.addActionListener(e -> panel.runKruskals());
        randomizeButton.addActionListener(e -> panel.randomizeEdges());
        removeRandomizeButton.addActionListener(e -> panel.removeRandomizeEdges());
        shortestDistanceRandomizeButton.addActionListener(e -> panel.dijkstraShortestPath());

        frame.add(panel);

        frame.setVisible(true);
    }

    public void recetAllAlgorithems() {
        kruskal = false;
        dijkstra = false;
        drawEdges = false;
        returnShortesPath = false;
//        path = null;
    }

    // Method to run the kruskals Algorithm
    public void runKruskals() {
        recetAllAlgorithems();
        kruskal = true;
        repaint();  // This will trigger paintComponent() again
    }

    // Method to randomize edges and repaint
    private void randomizeEdges() {
        recetAllAlgorithems();
        if (myCity != null) {
            drawEdges = true;
            myCity.makeٌRandomDirectedEdges();  // Call your method
            repaint();  // This will trigger paintComponent() again
        }
    }

    // Method to remove randomized edges and repaint
    private void removeRandomizeEdges() {
        recetAllAlgorithems();
        if (myCity != null) {
            drawEdges = true;
            myCity.makeEdgesUndirected();  // Call your method
            repaint();  // This will trigger paintComponent() again
        }
    }

    public void dijkstraShortestPath() {
        if (myCity != null) {
            recetAllAlgorithems();
            dijkstra = true;
            returnShortesPath = true;
            drawEdges = true;
            repaint();  // This will trigger paintComponent() again
        }
    }

    public void drawT(Graphics2D g2d, double distance) {
        String text;
        if(distance == 0){
            text = "kruskal Distance: Unreachable";
        } else {
            text = String.format("kruskal Distance: %.2f", distance);
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

class ColorCircleIcon implements Icon {
    private final int size;
    private final Color color;

    public ColorCircleIcon(int size, Color color) {
        this.size = size;
        this.color = color;
    }

    @Override
    public int getIconWidth() {
        return size;
    }

    @Override
    public int getIconHeight() {
        return size;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);
        g2.fillOval(x, y, size, size);
    }
}

