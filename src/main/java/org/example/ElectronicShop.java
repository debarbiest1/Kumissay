package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class ElectronicsShop {
    private static ElectronicsShop instance;
    public List<Client> clients;
    public List<Product> products;
    private static List<ShopSubscriber> subscribers;
    private ElectronicsShop(){
        clients=new ArrayList<>();
        products=new ArrayList<>();
        subscribers = new ArrayList<>();

    }
    public static ElectronicsShop getInstance() {
        if (instance == null) {
            instance = new ElectronicsShop();
        }
        return instance;
    }

    public static void addnotify(Product newProduct) {
        for (ShopSubscriber subscriber : subscribers) {
            subscriber.add(newProduct);
        }
    }

    public static void updatenotify(Product product) {
        for (ShopSubscriber subscriber : subscribers) {
            subscriber.update(product);
        }
    }

    public void addSubscriber(ShopSubscriber subscriber){
        subscribers.add(subscriber);
    }
    public void removeSubscriber(ShopSubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void giveProductToClient(int clientID, int productID) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO orders (client_id,product_id) VALUES (?, ?)")) {
            preparedStatement.setInt(1, clientID);
            preparedStatement.setInt(2, productID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Product given to client successfully.");
    }
}
