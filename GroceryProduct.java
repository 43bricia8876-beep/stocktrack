package storeinventory;

public class GroceryProduct extends Product {
    private final String expirationDate;

    public GroceryProduct(String id, String name, double price,
                          int quantityInStock, String expirationDate) {
        super(id, name, price, quantityInStock);
        this.expirationDate = expirationDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }
}