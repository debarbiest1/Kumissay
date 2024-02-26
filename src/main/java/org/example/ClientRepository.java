package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository implements Repository<Client>{
    public void add(Client client) {
    try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
         PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO clients (client_id, client_name, client_surname, client_address, client_number, email) VALUES (?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setInt(1, client.getClient_id());
            preparedStatement.setString(2, client.getName());
            preparedStatement.setString(3, client.getSurname());
            preparedStatement.setString(4, client.getAddress());
            preparedStatement.setString(5, client.getNumber());
            preparedStatement.setString(6, client.getEmail());
            preparedStatement.executeUpdate();
    }   catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> showAll() {
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






































































    public void delete(int id){

    }
    public void update(Client client){

    }
}
