package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TableCreator {

    public TableCreator(){
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
                    "client_id INTEGER NOT NULL,  " +
                    "product_id INTEGER NOT NULL);";
            statement.executeUpdate(createProductTable);
            statement.close();
            cn.close();
        }
        catch (
                SQLException e){
            e.printStackTrace();
        }
    }

}
