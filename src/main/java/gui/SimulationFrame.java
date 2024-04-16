package gui;

import model.Server;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class SimulationFrame extends JFrame {

    private final JTextField clientTextField;
    private final JTextField serverTextField;
    private final JTextField intervalTextField;
    private final JTextField minArrivalTextField;
    private final JTextField maxArrivalTextField;
    private final JTextField minServiceTextField;
    private final JTextField maxServiceTextField;

    private final JButton validateInput;

    private final JPanel serverPanel; // Panel for server squares

    public SimulationFrame() {
        super("Simulation Frame");

        // Create label
        JLabel clientLabel = new JLabel("Number of clients");
        JLabel serverLabel = new JLabel("Number of queues");
        JLabel intervalLabel = new JLabel("Simulation interval");
        JLabel minArrivalLabel = new JLabel("Minimum arrival time");
        JLabel maxArrivalLabel = new JLabel("Maximum arrival time");
        JLabel minServiceLabel = new JLabel("Minimum service time");
        JLabel maxServiceLabel = new JLabel("Maximum service time");

        // Create text field
        clientTextField = new JTextField(20);
        serverTextField = new JTextField(20);
        intervalTextField = new JTextField(20);
        minArrivalTextField = new JTextField(20);
        maxArrivalTextField = new JTextField(20);
        minServiceTextField = new JTextField(20);
        maxServiceTextField = new JTextField(20);

        validateInput = new JButton("Validate input");

        Border customBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#0D1321"), 15), // Outer border color and width
                BorderFactory.createEmptyBorder(0, 0, 0, 0) // Inner padding
        );

        JPanel inputPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        inputPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        inputPanel.add(clientLabel);
        inputPanel.add(serverLabel);
        inputPanel.add(clientTextField);
        inputPanel.add(serverTextField);
        inputPanel.add(minArrivalLabel);
        inputPanel.add(maxArrivalLabel);
        inputPanel.add(minArrivalTextField);
        inputPanel.add(maxArrivalTextField);
        inputPanel.add(minServiceLabel);
        inputPanel.add(maxServiceLabel);
        inputPanel.add(minServiceTextField);
        inputPanel.add(maxServiceTextField);
        inputPanel.add(intervalLabel);
        inputPanel.add(new JLabel());
        inputPanel.add(intervalTextField);
        inputPanel.add(validateInput);

        clientLabel.setFont(new Font("Comic sans", Font.BOLD, 18));
        clientLabel.setForeground(Color.decode("#CCE6F4"));
        serverLabel.setFont(new Font("Comic sans", Font.BOLD, 18));
        serverLabel.setForeground(Color.decode("#CCE6F4"));
        intervalLabel.setFont(new Font("Comic sans", Font.BOLD, 18));
        intervalLabel.setForeground(Color.decode("#CCE6F4"));
        minArrivalLabel.setFont(new Font("Comic sans", Font.BOLD, 18));
        minArrivalLabel.setForeground(Color.decode("#CCE6F4"));
        maxArrivalLabel.setFont(new Font("Comic sans", Font.BOLD, 18));
        maxArrivalLabel.setForeground(Color.decode("#CCE6F4"));
        minServiceLabel.setFont(new Font("Comic sans", Font.BOLD, 18));
        minServiceLabel.setForeground(Color.decode("#CCE6F4"));
        maxServiceLabel.setFont(new Font("Comic sans", Font.BOLD, 18));
        maxServiceLabel.setForeground(Color.decode("#CCE6F4"));

        clientTextField.setForeground(Color.decode("#CCE6F4"));
        clientTextField.setFont(new Font("Comic sans", Font.BOLD, 18));
        clientTextField.setCaretColor(Color.decode("#CCE6F4"));
        clientTextField.setBackground(Color.decode("#0D1321"));
        clientTextField.setBorder(new EmptyBorder(0, 5, 0, 0));

        serverTextField.setForeground(Color.decode("#CCE6F4"));
        serverTextField.setFont(new Font("Comic sans", Font.BOLD, 18));
        serverTextField.setCaretColor(Color.decode("#CCE6F4"));
        serverTextField.setBackground(Color.decode("#0D1321"));
        serverTextField.setBorder(new EmptyBorder(0, 5, 0, 0));

        minArrivalTextField.setForeground(Color.decode("#CCE6F4"));
        minArrivalTextField.setFont(new Font("Comic sans", Font.BOLD, 18));
        minArrivalTextField.setCaretColor(Color.decode("#CCE6F4"));
        minArrivalTextField.setBackground(Color.decode("#0D1321"));
        minArrivalTextField.setBorder(new EmptyBorder(0, 5, 0, 0));

        maxArrivalTextField.setForeground(Color.decode("#CCE6F4"));
        maxArrivalTextField.setFont(new Font("Comic sans", Font.BOLD, 18));
        maxArrivalTextField.setCaretColor(Color.decode("#CCE6F4"));
        maxArrivalTextField.setBackground(Color.decode("#0D1321"));
        maxArrivalTextField.setBorder(new EmptyBorder(0, 5, 0, 0));

        minServiceTextField.setForeground(Color.decode("#CCE6F4"));
        minServiceTextField.setFont(new Font("Comic sans", Font.BOLD, 18));
        minServiceTextField.setCaretColor(Color.decode("#CCE6F4"));
        minServiceTextField.setBackground(Color.decode("#0D1321"));
        minServiceTextField.setBorder(new EmptyBorder(0, 5, 0, 0));

        maxServiceTextField.setForeground(Color.decode("#CCE6F4"));
        maxServiceTextField.setFont(new Font("Comic sans", Font.BOLD, 18));
        maxServiceTextField.setCaretColor(Color.decode("#CCE6F4"));
        maxServiceTextField.setBackground(Color.decode("#0D1321"));
        maxServiceTextField.setBorder(new EmptyBorder(0, 5, 0, 0));

        intervalTextField.setForeground(Color.decode("#CCE6F4"));
        intervalTextField.setFont(new Font("Comic sans", Font.BOLD, 18));
        intervalTextField.setCaretColor(Color.decode("#CCE6F4"));
        intervalTextField.setBackground(Color.decode("#0D1321"));
        intervalTextField.setBorder(new EmptyBorder(0, 5, 0, 0));

        validateInput.setFocusable(false);
        validateInput.setFocusPainted(false);
        validateInput.setBackground(Color.decode("#CCE6F4"));
        validateInput.setForeground(Color.decode("#0D1321"));
        validateInput.setFont(new Font("Comic sans", Font.BOLD, 16));

        inputPanel.setBackground(Color.decode("#1D2D44"));
        inputPanel.setBorder(customBorder);

        // Panel for server squares and client queues
        serverPanel = new JPanel(new GridBagLayout());
        //serverPanel.setBorder(customBorder);

        // Constraints for serverSquaresPanel
        GridBagConstraints serverSquaresConstraints = new GridBagConstraints();
        serverSquaresConstraints.gridx = 0;
        serverSquaresConstraints.gridy = 0;
        serverSquaresConstraints.weightx = 0.25; // One quarter of the width
        serverSquaresConstraints.weighty = 1.0; // Fill the height
        serverSquaresConstraints.fill = GridBagConstraints.BOTH;
        serverSquaresConstraints.insets = new Insets(5, 5, 5, 5);

        // Constraints for clientQueuesPanel
        GridBagConstraints clientQueuesConstraints = new GridBagConstraints();
        clientQueuesConstraints.gridx = 1;
        clientQueuesConstraints.gridy = 0;
        clientQueuesConstraints.weightx = 0.75; // Three quarters of the width
        clientQueuesConstraints.weighty = 1.0; // Fill the height
        clientQueuesConstraints.fill = GridBagConstraints.BOTH;
        clientQueuesConstraints.insets = new Insets(5, 5, 5, 5);

        // Left panel for server squares
        JPanel serverSquaresPanel = new JPanel();
        serverSquaresPanel.setLayout(new BoxLayout(serverSquaresPanel, BoxLayout.Y_AXIS));
        serverSquaresPanel.setBackground(Color.decode("#1D2D44"));

        // Right panel for client queues
        JPanel clientQueuesPanel = new JPanel();
        clientQueuesPanel.setLayout(new BoxLayout(clientQueuesPanel, BoxLayout.Y_AXIS));
        clientQueuesPanel.setBackground(Color.decode("#1D2D44"));

        serverPanel.add(serverSquaresPanel, serverSquaresConstraints);
        serverPanel.add(clientQueuesPanel, clientQueuesConstraints);
        serverPanel.setBackground(Color.decode("#1D2D44"));

        // Add scroll pane to serverPanel
        JScrollPane scrollPane = new JScrollPane(serverPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(customBorder);
        scrollPane.setBackground(Color.decode("#1D2D44"));

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);

        setSize(800, 700); // Adjusted size for better viewing
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void drawServerSquares(int numberOfServers) {
        JPanel serverSquaresPanel = (JPanel) serverPanel.getComponent(0); // Get the left panel for servers

        serverSquaresPanel.removeAll(); // Clear previous squares

        for (int i = 0; i < numberOfServers; i++) {
            int currentCounter = i + 1;
            JPanel serverSquare = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Color customColor = Color.decode("#27476E");
                    g.setColor(customColor);
                    g.fillRect(0, 0, getWidth(), getHeight());
                }

                @Override
                public Dimension getPreferredSize() {
                    return new Dimension(50, 50);
                }

                @Override
                protected void paintChildren(Graphics g) {
                    super.paintChildren(g);
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setColor(Color.decode("#CCE6F4"));

                    Font font = new Font("Comic sans", Font.BOLD, 18);
                    g2.setFont(font);

                    FontMetrics fm = g2.getFontMetrics();
                    String serverLabel = "Server " + currentCounter;
                    int x = (getWidth() - fm.stringWidth(serverLabel)) / 2;
                    int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
                    g2.drawString(serverLabel, x, y);
                    g2.dispose();
                }
            };

            serverSquare.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50)); // Fixed height
            serverSquaresPanel.add(serverSquare);
            serverSquaresPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Add space between squares
        }

        serverSquaresPanel.revalidate();
        serverSquaresPanel.repaint();
    }

    public void drawQueues(List<Server> servers) {
        JPanel queuesPanel = (JPanel) serverPanel.getComponent(1);
        queuesPanel.removeAll();
        queuesPanel.setLayout(new BoxLayout(queuesPanel, BoxLayout.Y_AXIS)); // Set layout to vertical

        for (Server server : servers) {
            JPanel queuePanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Color customColor = Color.decode("#1D2D44");
                    g.setColor(customColor);
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
                @Override
                public Dimension getPreferredSize() {
                    return new Dimension(50, 50); // Adjust size as needed
                }

                @Override
                protected void paintChildren(Graphics g) {
                    super.paintChildren(g);
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setColor(Color.decode("#CCE6F4"));

                    Font font = new Font("Segoe UI Emoji", Font.BOLD, 18); // Change font to support emojis
                    g2.setFont(font);

                    FontMetrics fm = g2.getFontMetrics();
                    String serverLabel = server.displayInInterface();
                    int x = 5;
                    int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
                    g2.drawString(serverLabel, x, y);
                    g2.dispose();
                }
            };

            queuePanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(5, 0, 5, 0, Color.BLACK), // Outer border
                    BorderFactory.createEmptyBorder(5, 5, 5, 5) // Inner padding
            ));

            queuePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50)); // Set maximum width
            queuesPanel.add(queuePanel);
            queuesPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Add space between queues
        }

        queuesPanel.revalidate();
        queuesPanel.repaint();
    }


    public JTextField getClientTextField() {
        return clientTextField;
    }

    public JTextField getServerTextField() {
        return serverTextField;
    }

    public JTextField getIntervalTextField() {
        return intervalTextField;
    }

    public JTextField getMinArrivalTextField() {
        return minArrivalTextField;
    }

    public JTextField getMaxArrivalTextField() {
        return maxArrivalTextField;
    }

    public JTextField getMinServiceTextField() {
        return minServiceTextField;
    }

    public JTextField getMaxServiceTextField() {
        return maxServiceTextField;
    }

    public JButton getValidateInput() {
        return validateInput;
    }
}
