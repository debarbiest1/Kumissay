package org.example;

public class Finder {
    public static Client findClientById(int clientID, Repository<Client> clientRepository) {
        for (Client client : clientRepository.showAll()) {
            if (client.getClient_id() == clientID) {
                return client;
            }
        }
        return null;
    }
    static Product findProductById(int productID, Repository<Product> productRepository ) {
        for (Product product : productRepository.showAll()) {
            if (product.getId()==productID) {
                return product;
            }
        }
        return null;
    }
}
