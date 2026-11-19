package storeinventory;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class InventoryGUI extends JFrame {
    private final Store store;
    private final JTextField productIdField = new JTextField(10);
    private final JTextField quantityField = new JTextField(6);
    private final JTextArea inventoryDisplay = new JTextArea(12, 40);

    public InventoryGUI() {
        super("Store Inventory");
        store = createSampleStore();

        inventoryDisplay.setEditable(false);

        JButton sellButton = new JButton("Sell");
        JButton restockButton = new JButton("Restock");

        sellButton.addActionListener(event -> sellProduct());
        restockButton.addActionListener(event -> restockProduct());

        JPanel controls = new JPanel(new FlowLayout());
        controls.add(new JLabel("Product ID:"));
        controls.add(productIdField);
        controls.add(new JLabel("Quantity:"));
        controls.add(quantityField);
        controls.add(sellButton);
        controls.add(restockButton);

        setLayout(new BorderLayout(8, 8));
        add(controls, BorderLayout.NORTH);
        add(new JScrollPane(inventoryDisplay), BorderLayout.CENTER);

        refreshInventory();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private static Store createSampleStore() {
        Store sampleStore = new Store();
        sampleStore.addProduct(
                new ElectronicsProduct("E1", "Headphones", 49.99, 10, 12));
        sampleStore.addProduct(
                new GroceryProduct("G1", "Apples", 1.25, 30, "2026-12-31"));
        return sampleStore;
    }

    private void sellProduct() {
        try {
            String id = productIdField.getText().trim();
            int quantity = readQuantity();

            Order order = store.sellProduct(id, quantity);
            JOptionPane.showMessageDialog(
                    this,
                    String.format("Sale complete. Total: $%.2f", order.getTotal()),
                    "Sale",
                    JOptionPane.INFORMATION_MESSAGE);

            refreshInventory();
        } catch (IllegalArgumentException exception) {
            showError(exception.getMessage());
        }
    }

    private void restockProduct() {
        try {
            String id = productIdField.getText().trim();
            int quantity = readQuantity();

            store.restockProduct(id, quantity);
            JOptionPane.showMessageDialog(
                    this, "Restock complete.", "Restock",
                    JOptionPane.INFORMATION_MESSAGE);

            refreshInventory();
        } catch (IllegalArgumentException exception) {
            showError(exception.getMessage());
        }
    }

    private int readQuantity() {
        try {
            return Integer.parseInt(quantityField.getText().trim());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(
                    "Enter a whole number for quantity.");
        }
    }

    private void refreshInventory() {
        StringBuilder text = new StringBuilder("Current inventory:\n\n");
        Collection<Product> products = store.getProducts();

        for (Product product : products) {
            text.append(String.format(
                    "%s — %s | $%.2f | In stock: %d%n",
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getQuantityInStock()));
        }

        inventoryDisplay.setText(text.toString());
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(
                this, message, "Unable to complete", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InventoryGUI gui = new InventoryGUI();
            gui.setVisible(true);
        });
    }
}