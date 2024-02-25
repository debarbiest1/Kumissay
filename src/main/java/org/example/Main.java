package org.example;

import java.awt.*;
import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ElectronicsShop shop = ElectronicsShop.getInstance();
        TableCreator tableCreator = new TableCreator();
        Menu menu = new Menu(scanner, shop);
    }
}