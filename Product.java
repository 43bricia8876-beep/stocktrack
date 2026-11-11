package storeinventory;

public abstract class Product {
    private final String id;
    private final String name;
    private final double price;
    private int quantityInStock;

    public Product(String id, String name, double price, int quantityInStock) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Product ID is required.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name is required.");
        }
        if (price < 0 || quantityInStock < 0) {
            throw new IllegalArgumentException("Price and stock cannot be negative.");
        }

        this.id = id;
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void restock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Restock quantity must be positive.");
        }
        quantityInStock += quantity;
    }

    public void sell(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Sale quantity must be positive.");
        }
        if (quantity > quantityInStock) {
            throw new IllegalArgumentException("Not enough stock.");
        }
        quantityInStock -= quantity;
    }
}