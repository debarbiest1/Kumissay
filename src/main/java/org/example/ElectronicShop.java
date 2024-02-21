package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class ElectronicsShop {
    public List<Client> clients;
    public List<Product> products;
    private List<ShopSubscriber> subscribers;
    public ElectronicsShop(){
        clients=new ArrayList<>();
        products=new ArrayList<>();
        subscribers = new ArrayList<>();

    }
    public void addSubscriber(ShopSubscriber subscriber){
        subscribers.add(subscriber);
    }
    public void removeSubscriber(ShopSubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void addProduct(Product newProduct) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Products (product_id, product_name, product_category, product_year, price) VALUES (?, ?, ?, ?, ?)")) {
            preparedStatement.setInt(1, newProduct.getId());
            preparedStatement.setString(2, newProduct.getName());
            preparedStatement.setString(3, newProduct.getCategory());
            preparedStatement.setInt(4, newProduct.getYear());
            preparedStatement.setInt(5, newProduct.getPrice());
            preparedStatement.executeUpdate();
            for (ShopSubscriber subscriber : subscribers) {
                subscriber.add(newProduct);
            }
            //notifying
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Product> showAllProducts() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM products")) {

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("product_id"));
                product.setName(resultSet.getString("product_name"));
                product.setCategory(resultSet.getString("product_category"));
                product.setYear(resultSet.getInt("product_year"));
                product.setPrice(resultSet.getInt("price"));

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public void addClient(Client client) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO clients (client_id, client_name, client_surname, client_address, client_number, email) VALUES (?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setInt(1, client.getClient_id());
            preparedStatement.setString(2, client.getName());
            preparedStatement.setString(3, client.getSurname());
            preparedStatement.setString(4, client.getAddress());
            preparedStatement.setString(5, client.getNumber());
            preparedStatement.setString(6, client.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> showAllClients() {
        List<Client> clients = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM clients")) {

            while (resultSet.next()) {
                Client client = new Client();
                client.setClient_id(resultSet.getInt("client_id"));
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
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
    public void updateProduct(Product product) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE products SET product_name=?, product_category=?, product_year=?, price=? WHERE product_id=?")) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getCategory());
            preparedStatement.setInt(3, product.getYear());
            preparedStatement.setInt(4, product.getPrice());
            preparedStatement.setInt(5, product.getId());
            preparedStatement.executeUpdate();
            for (ShopSubscriber subscriber : subscribers) {
                subscriber.update(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteProduct(int productId) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM products WHERE product_id=?")) {
            preparedStatement.setInt(1, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

}
