package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private List<ShopSubscriber> subscribers;
    public static void addProduct(Product newProduct) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Products (product_id, product_name, product_category, product_year, price) VALUES (?, ?, ?, ?, ?)")) {
            preparedStatement.setInt(1, newProduct.getId());
            preparedStatement.setString(2, newProduct.getName());
            preparedStatement.setString(3, newProduct.getCategory());
            preparedStatement.setInt(4, newProduct.getYear());
            preparedStatement.setInt(5, newProduct.getPrice());
            preparedStatement.executeUpdate();
            ElectronicsShop.addnotify(newProduct);
            //notifying
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void updateProduct(Product product) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE products SET product_name=?, product_category=?, product_year=?, price=? WHERE product_id=?")) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getCategory());
            preparedStatement.setInt(3, product.getYear());
            preparedStatement.setInt(4, product.getPrice());
            preparedStatement.setInt(5, product.getId());
            preparedStatement.executeUpdate();
            ElectronicsShop.updatenotify(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void deleteProduct(int productId) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM products WHERE product_id=?")) {
            preparedStatement.setInt(1, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    public static List<Product> showAllProducts() {
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
}
