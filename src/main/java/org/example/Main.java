package org.example;

import java.awt.*;
import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Repository<Client> clientRepository = new ClientRepository();
        Repository<Product> productRepository = new ProductRepository();
        Scanner scanner = new Scanner(System.in);
        ElectronicsShop shop = ElectronicsShop.getInstance();
        TableCreator tableCreator = new TableCreator();
        Menu menu = new Menu();

        menu.execute(scanner, shop, clientRepository, productRepository);
    }
}