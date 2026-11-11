package storeinventory;

public class InventoryDemo {
    public static void main(String[] args) {
        Store store = new Store();

        Product headphones =
                new ElectronicsProduct("E1", "Headphones", 49.99, 10, 12);
        store.addProduct(headphones);

        Order order = store.sellProduct("E1", 2);

        System.out.println("Order total: $" + order.getTotal());
        System.out.println("Stock remaining: "
                + store.findProduct("E1").getQuantityInStock());
    }
}