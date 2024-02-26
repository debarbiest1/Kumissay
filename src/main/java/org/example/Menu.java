package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
interface MenuOption {
    void execute(Scanner scanner, ElectronicsShop shop, Repository<Client> clientRepository, Repository<Product> productRepository);
}
class AddProductOption implements MenuOption {
    @Override
    public void execute(Scanner scanner, ElectronicsShop shop, Repository<Client> clientRepository, Repository<Product> productRepository) {
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
        productRepository.add(newProduct);
        System.out.println("Product added successfully.");
    }
}
class ShowProductOption implements MenuOption {
    public void execute(Scanner scanner, ElectronicsShop shop, Repository<Client> clientRepository, Repository<Product> productRepository) {
        List<Product> products = productRepository.showAll();
        System.out.println("Products:");
        for (Product product : products) {
            System.out.println(product.getId() + ": " + product.getName() + " from category " + product.getCategory() + " is year of " + product.getYear() + " and costs "+product.getPrice());
        }

    }
}
class AddClientOption implements MenuOption{
    @Override
    public void execute(Scanner scanner, ElectronicsShop shop, Repository<Client> clientRepository, Repository<Product> productRepository) {
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
        clientRepository.add(newClient);
        System.out.println("Client added successfully.");
        shop.addSubscriber(newClient);

    }


}
class SellOption implements MenuOption {
    @Override
    public void execute(Scanner scanner, ElectronicsShop shop, Repository<Client> clientRepository, Repository<Product> productRepository) {
        System.out.println("Enter client ID:");

        int clientID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter product ID:");
        int productID = scanner.nextInt();
        scanner.nextLine();
        Client clientToGiveProduct = Finder.findClientById(clientID, clientRepository);
        Product productToGive = Finder.findProductById(productID, productRepository);
        if (clientToGiveProduct != null && productToGive != null) {
            shop.giveProductToClient(clientID, productID);
            productRepository.delete(productID);
        } else {
            System.out.println("Client or product not found.");
        }
    }
}
class UpdateProductOption implements MenuOption {
    @Override
    public void execute(Scanner scanner, ElectronicsShop shop, Repository<Client> clientRepository, Repository<Product> productRepository) {
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
        productRepository.update(product1);
        System.out.println("Product updated successfully.");
    }
}
class DeleteProductOption implements MenuOption {
    @Override
    public void execute(Scanner scanner, ElectronicsShop shop, Repository<Client> clientRepository, Repository<Product> productRepository) {
        System.out.println("Enter product ID:");
        int productId = scanner.nextInt();
        productRepository.delete(productId);
        System.out.println("Product deleted successfully.");
    }
}
class ExitOption implements MenuOption {
    @Override
    public void execute(Scanner scanner, ElectronicsShop shop, Repository<Client> clientRepository, Repository<Product> productRepository) {
        System.out.println("Exiting the Shop Management System. Goodbye!");
        System.exit(0);
    }
}


public class Menu implements MenuOption{

    public void execute(Scanner scanner, ElectronicsShop shop, Repository<Client> clientRepository, Repository<Product> productRepository){

        final Map<Integer, MenuOption> menuOptions;
        while(true){
            menuOptions = new HashMap<>();
            menuOptions.put(1, new AddProductOption());
            menuOptions.put(2, new ShowProductOption());
            menuOptions.put(3, new AddClientOption());
            menuOptions.put(4, new SellOption());
            menuOptions.put(5, new UpdateProductOption());
            menuOptions.put(6, new DeleteProductOption());
            menuOptions.put(7, new ExitOption());


            while (true) {
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
                MenuOption option = menuOptions.get(choice);
                if (option != null) {
                    option.execute(scanner, shop, clientRepository, productRepository);
                } else {
                    System.out.println("Invalid choice. Please enter a valid option.");
                }
            }

        }

    }
}
