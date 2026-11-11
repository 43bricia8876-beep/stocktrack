package storeinventory;

public class ClothingProduct extends Product {
    private final String size;

    public ClothingProduct(String id, String name, double price,
                           int quantityInStock, String size) {
        super(id, name, price, quantityInStock);
        this.size = size;
    }

    public String getSize() {
        return size;
    }
}