package storeinventory;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Store {
    private final Map<String, Product> products = new LinkedHashMap<>();

    public void addProduct(Product product) {
        if (products.containsKey(product.getId())) {
            throw new IllegalArgumentException("That product ID already exists.");
        }
        products.put(product.getId(), product);
    }

    public Product findProduct(String id) {
        return products.get(id);
    }

    public Collection<Product> getProducts() {
        return products.values();
    }

    public void restockProduct(String id, int quantity) {
        Product product = findProduct(id);
        if (product == null) {
            throw new IllegalArgumentException("Product not found.");
        }
        product.restock(quantity);
    }

    public Order sellProduct(String id, int quantity) {
        Product product = findProduct(id);
        if (product == null) {
            throw new IllegalArgumentException("Product not found.");
        }

        product.sell(quantity);

        Order order = new Order();
        order.addItem(new OrderItem(product.getName(), quantity, product.getPrice()));
        return order;
    }
}