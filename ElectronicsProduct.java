package storeinventory;

public class ElectronicsProduct extends Product {
    private final int warrantyMonths;

    public ElectronicsProduct(String id, String name, double price,
                              int quantityInStock, int warrantyMonths) {
        super(id, name, price, quantityInStock);

        if (warrantyMonths < 0) {
            throw new IllegalArgumentException("Warranty cannot be negative.");
        }
        this.warrantyMonths = warrantyMonths;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }
}