package org.example;

import java.util.List;

class Client implements ShopSubscriber{
    private int client_id;
    private String name;
    private String surname;
    private String address;
    private String number;
    private String email;


    public Client(){

    }
    public Client(int client_id, String name, String surname, String address, String number, String email){
        setClient_id(client_id);
        setName(name);
        setSurname(surname);
        setAddress(address);
        setNumber(number);
        setEmail(email);
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void add(Product product) {
        System.out.println("Notification: New product added - " + product.getName());
    }
    @Override
    public void update(Product product) {
        System.out.println("Notification: The new price for - " + product.getName());
    }


}