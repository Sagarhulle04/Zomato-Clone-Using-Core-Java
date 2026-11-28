import entity.Customer;
import entity.RestaurantOwner;
import org.zomato.config.DbConfig;
import services.CustomerService;
import services.MenusService;
import services.RestaurantOwnerService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Zomato {

    private final Scanner scanner = new Scanner(System.in);
    private static final CustomerService customerService = new CustomerService();
    private static final RestaurantOwnerService restaurantOwnerService = new RestaurantOwnerService();
    private static final MenusService menuService = new MenusService();
    private static final Customer customer = null;
    private static final RestaurantOwner restaurantOwner = null;

    public static void main(String[] args) {
        new Zomato().start();
    }

    private void start() {
        while (true){
            System.out.println("1. Login As Normal Customer.");
            System.out.println("2. Login As Restaurant Owner.");
            System.out.println("3. Exit");

            System.out.print("Enter the choice : ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> normalCustomerPage();
                case 2 -> restaurantOwnerPage();
                case 3 -> exit();
                default -> System.out.println("Enter Valid Choice.");
            }
        }
    }

    private void normalCustomerPage() {
        while(true) {
            if(!customerService.isLoggedIn()) {
                System.out.println("1. Register Normal Customer.");
                System.out.println("2. Login Normal Customer.");
                System.out.println("3. Exit.");

                System.out.print("Enter the choice : ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> registerAsNormalCustomer();
                    case 2 -> loginAsNormalCustomer();
                    case 3 -> exit();
                    default -> System.out.println("Enter Valid Choice.");
                }
            }
            else {
                System.out.println("1. See All Restaurants.");
                System.out.println("2. See Profile Details.");
                System.out.println("3. Search Restaurant.");
                System.out.println("4. Logout.");
                System.out.println("5. Exit.");

                System.out.print("Enter the choice : ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> seeAllRestaurants();
                    case 2 -> seeCustomerProfileDetails();
                    case 3 -> searchRestaurant();
                    case 4 -> {
                        logoutCustomer();
                        return;
                    }
                    case 5 -> exit();
                    default -> System.out.println("Enter Valid Choice.");
                }

            }
        }
    }

    private void restaurantOwnerPage() {
        while(true) {
            if(!restaurantOwnerService.isLoggedRestaurantOwner()) {
                System.out.println("1. Register Restaurant Owner.");
                System.out.println("2. Login Restaurant Owner.");
                System.out.println("3. Exit.");

                System.out.print("Enter the choice : ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> registerRestaurantOwner();
                    case 2 -> loginRestaurantOwner();
                    case 3 -> exit();
                    default -> System.out.println("Enter Valid Choice.");
                }
            }
            else {
                System.out.println("1. See Profile Details.");
                System.out.println("2. Add Menu.");
                System.out.println("3. Logout.");
                System.out.println("4. Exit.");

                System.out.print("Enter the choice : ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> seeRestaurantOwnerProfileDetails();
                    case 2 -> addMenu();
                    case 3 -> {
                        logoutRestaurantOwner();
                        return;
                    }
                    case 4 -> exit();
                    default -> System.out.println("Enter Valid Choice.");
                }

            }
        }
    }




//    Normal Customer Functionality
    private void registerAsNormalCustomer() {
        System.out.print("Enter Your Name : ");
        String customerName = scanner.nextLine();

        System.out.print("Enter Your Email : ");
        String customerEmail = scanner.nextLine();

        System.out.print("Enter Your Password : ");
        String customerPassword = scanner.nextLine();

        System.out.print("Enter Your Contact : ");
        String customerContact = scanner.nextLine();

        System.out.print("Enter Your Address : ");
        String customerAddress = scanner.nextLine();

        customerService.registerCustomer(customerName, customerEmail ,customerPassword, customerContact, customerAddress);
        System.out.println();
    }

    private void loginAsNormalCustomer() {
        System.out.print("Enter Your Email : ");
        String customerEmail = scanner.nextLine();

        System.out.print("Enter Your Password : ");
        String customerPassword = scanner.nextLine();

        customerService.loginCustomer(customerEmail, customerPassword);
        System.out.println();
    }

    private void seeAllRestaurants() {
        try {
            restaurantOwnerService.seeAllRestaurants();
            System.out.println();

        }
        catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    private void seeCustomerProfileDetails() {
        Customer customer = customerService.loggedInUser();
        System.out.println(customer.getCustomerName() + " | " + customer.getCustomerEmail() + " | " + customer.getCustomerContact() + " | " + customer.getCustomerAddress());
    }

    private void logoutCustomer() {
        customerService.logoutCustomer();
        System.out.println("Logged Out Successfully.");
        System.out.println();
    }

    private void searchRestaurant() {
        System.out.print("Enter the restaurant name : ");
        String restaurantName = scanner.nextLine();

        restaurantOwnerService.searchRestaurant(restaurantName.toLowerCase());
        System.out.println();
    }


//    Restaurant Owner Functionality
    private void registerRestaurantOwner() {
        System.out.print("Enter Your Name : ");
        String restaurantOwnerName = scanner.nextLine();

        System.out.print("Enter Your Restaurant Name : ");
        String restaurantName = scanner.nextLine();

        System.out.print("Enter Your Email : ");
        String restaurantEmail = scanner.nextLine();

        System.out.print("Enter Your Password : ");
        String restaurantPassword = scanner.nextLine();

        System.out.print("Enter Your Contact : ");
        String restaurantContact = scanner.nextLine();

        System.out.print("Enter Your Address : ");
        String restaurantAddress = scanner.nextLine();

        restaurantOwnerService.registerRestaurantOwner(restaurantOwnerName, restaurantName, restaurantEmail, restaurantPassword, restaurantContact ,restaurantAddress);
        System.out.println();
    }

    private void loginRestaurantOwner() {
        System.out.print("Enter Your Email : ");
        String customerEmail = scanner.nextLine();

        System.out.print("Enter Your Password : ");
        String customerPassword = scanner.nextLine();

        restaurantOwnerService.loginRestaurantOwner(customerEmail, customerPassword);
        System.out.println();
    }

    private void seeRestaurantOwnerProfileDetails() {
        RestaurantOwner restaurantOwner = restaurantOwnerService.restaurantOwnerDetails();
        System.out.println(restaurantOwner.getRestaurantOwnerName() +  " | " + restaurantOwner.getRestaurantName() + " | " + restaurantOwner.getRestaurantEmail() + " | " + restaurantOwner.getRestaurantContact() + " | " + restaurantOwner.getRestaurantAddress());
    }

    private void addMenu() {
        RestaurantOwner restaurantOwner = restaurantOwnerService.restaurantOwnerDetails();
        System.out.println(restaurantOwner.getRestaurantEmail());

        try{
            Connection connection = DbConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT restaurantId from restaurantOwner where restaurantEmail = ?");
            preparedStatement.setString(1, restaurantOwner.getRestaurantEmail());

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int id = resultSet.getInt("restaurantId");

                System.out.print("Enter Menu Name : ");
                String menuName = scanner.nextLine();

                System.out.print("Enter Menu Description : ");
                String menuDescription = scanner.nextLine();

                System.out.print("Enter Menu Price : ");
                double menuPrice = scanner.nextDouble();
                scanner.nextLine();

                System.out.print("Enter Menu Type (Veg / Non-Veg) : ");
                String menuType = scanner.nextLine();

                menuService.addMenus(id,menuName, menuDescription, menuPrice, menuType);
            }
        }
        catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }

    }

    private void logoutRestaurantOwner() {
        restaurantOwnerService.logoutRestaurantOwner();
        System.out.println("Logged Out Successfully.");
        System.out.println();
    }

    private void exit() {
        System.out.println("Thank You For Using The App");
        System.exit(0);
    }
}
