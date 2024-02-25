package org.example;

public class Finder {
    public static Client findClientById( int clientID) {
        for (Client client : ClientRepository.showAllClients()) {
            if (client.getClient_id() == clientID) {
                return client;
            }
        }
        return null;
    }
    static Product findProductById(int productID) {
        for (Product product : ProductRepository.showAllProducts()) {
            if (product.getId()==productID) {
                return product;
            }
        }
        return null;
    }
}
