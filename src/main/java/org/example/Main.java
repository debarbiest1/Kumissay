package org.example;

import java.sql.*;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ElectronicsShop shop = ElectronicsShop.getInstance();
        try {
            Connection cn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
            Statement statement = cn.createStatement();
            String createProductTable = "CREATE TABLE IF NOT EXISTS Products " +
                    "(product_id INTEGER NOT NULL PRIMARY KEY, " +
                    "product_name VARCHAR(255) NOT NULL, " +
                    "product_category VARCHAR(255) NOT NULL, "+
                    "product_year INTEGER NOT NULL, " +
                    "price INTEGER NOT NULL);" +
                    "CREATE TABLE IF NOT EXISTS Clients" +
                    "(client_id INTEGER NOT NULL PRIMARY KEY, " +
                    "client_name VARCHAR(255) NOT NULL, " +
                    "client_surname VARCHAR(255) NOT NULL, " +
                    "client_address VARCHAR(255) NOT NULL, " +
                    "client_number VARCHAR(255) NOT NULL, " +
                    "email VARCHAR(255) NOT NULL);" +
                    "CREATE TABLE IF NOT EXISTS Orders" +
                    "(order_id SERIAL PRIMARY KEY, " +
                    "client_id INTEGER REFERENCES Clients(client_id), " +
                    "product_id INTEGER REFERENCES Products(product_id));";
            statement.executeUpdate(createProductTable);
            statement.close();
            cn.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        while(true){
            System.out.println("Shop System Menu:");
            System.out.println("1) Add a new product");
            System.out.println("2) Show all available products");
            System.out.println("3) Add a new client");
            System.out.println("4) Give a gadget to specific client");
            System.out.println("5) Update product's information");
            System.out.println("6) Delete a product");
            System.out.println("7) Exit");
            System.out.println("Enter your choice:");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter product category:");
                    String category = scanner.nextLine();
                    System.out.println("Enter product model");
                    String name = scanner.nextLine();
                    System.out.println("Enter product id:");
                    int id = scanner.nextInt();
                    System.out.println("Enter product year:");
                    int year = scanner.nextInt();
                    System.out.println("Enter product price:");
                    int price = scanner.nextInt();
                    scanner.nextLine();
                    Product newProduct = new Product(id, name, category, year, price);
                    shop.addProduct(newProduct);
                    System.out.println("Product added successfully.");
                    break;
                case 2:
                    List<Product> products = shop.showAllProducts();
                    System.out.println("Products:");
                    for (Product product : products) {
                        System.out.println(product.getId() + ": " + product.getName() + " from category " + product.getCategory() + " is year of " + product.getYear() + " and costs "+product.getPrice());
                    }


                    break;
                case 3:
                    System.out.println("Enter client ID:");
                    int clientId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter client name:");
                    String clientName = scanner.nextLine();
                    System.out.println("Enter client surname:");
                    String clientSurname = scanner.nextLine();
                    System.out.println("Enter client address:");
                    String clientAddress = scanner.nextLine();
                    String clientNumber;
                    while(true){
                        System.out.println("Enter client number(+7*** *** ** **):");
                        clientNumber = scanner.nextLine();
                        if(clientNumber.charAt(0)=='+' && clientNumber.charAt(1)=='7' && clientNumber.length()==12){
                            break;
                        }
                        else{
                            System.out.println("Enter again!");
                        }
                    }
                    System.out.println("Enter client email:");
                    String clientEmail = scanner.nextLine();

                    Client newClient = new Client(clientId, clientName, clientSurname, clientAddress, clientNumber, clientEmail);
                    shop.addClient(newClient);
                    System.out.println("Client added successfully.");
                    shop.addSubscriber(newClient);
                    break;
                case 4:
                    System.out.println("Enter client ID:");

                    int clientID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter product ID:");
                    int productID = scanner.nextInt();
                    scanner.nextLine();
                    Client clientToGiveProduct = findClientById(shop, clientID);
                    Product productToGive = findProductById(shop, productID);
                    if (clientToGiveProduct != null && productToGive != null) {
                        shop.giveProductToClient(clientID, productID);
                        shop.deleteProduct(productID);
                    } else {
                        System.out.println("Client or product not found.");
                    }
                    break;
                case 5:
                    System.out.println("Enter product ID to edit it's characteristics:");
                    int new_id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter a new name:");
                    String new_name = scanner.nextLine();
                    System.out.println("Enter a new category:");
                    String new_category = scanner.nextLine();
                    System.out.println("Enter a new year:");
                    int new_year = scanner.nextInt();
                    System.out.println("Enter a new price:");
                    int new_price = scanner.nextInt();
                    scanner.nextLine();
                    Product product1 = new Product(new_id, new_name, new_category, new_year, new_price);
                    shop.updateProduct(product1);
                    System.out.println("Product updated successfully.");
                    break;
                case 6:
                    System.out.println("Enter product ID:");
                    int productId = scanner.nextInt();
                    shop.deleteProduct(productId);
                    System.out.println("Product deleted successfully.");
                    break;
                case 7:
                    System.out.println("Exiting the Shop Management System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");

            }

        }

    }
    private static Client findClientById(ElectronicsShop shop, int clientID) {
        for (Client client : shop.showAllClients()) {
            if (client.getClient_id() == clientID) {
                return client;
            }
        }
        return null;
    }
    private static Product findProductById(ElectronicsShop shop, int productID) {
        for (Product product : shop.showAllProducts()) {
            if (product.getId()==productID) {
                return product;
            }
        }
        return null;
    }

}