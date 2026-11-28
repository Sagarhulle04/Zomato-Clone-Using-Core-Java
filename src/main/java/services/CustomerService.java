package services;

import entity.Customer;
import org.zomato.config.DbConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerService {
    private Customer currentCustomer = null;

    public void registerCustomer(String customerName, String customerEmail, String customerPassword, String customerContact, String customerAddress) {
        try {
            Connection connection = DbConfig.getConnection();

            PreparedStatement checkEmail = connection.prepareStatement("SELECT * FROM CUSTOMER WHERE customerEmail = ?");
            checkEmail.setString(1, customerEmail);
            ResultSet resultSet = checkEmail.executeQuery();

            if(resultSet.next()) {
                System.out.println("Email already exists");
                return;
            }

            PreparedStatement addCustomer = connection.prepareStatement("INSERT INTO CUSTOMER (customerName, customerEmail, customerPassword, customerContact, customerAddress) VALUES (?,?,?,?,?)");
            addCustomer.setString(1, customerName);
            addCustomer.setString(2, customerEmail);
            addCustomer.setString(3, customerPassword);
            addCustomer.setString(4, customerContact);
            addCustomer.setString(5, customerAddress);

            int row = addCustomer.executeUpdate();
            if(row==1) {
                System.out.println("Profile Created Successfully");
            }
            else {
                System.out.println("Something Went Wrong. Please Try Again");
            }

        }
        catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    public void loginCustomer(String customerEmail, String customerPassword) {
        try {
            Connection connection = DbConfig.getConnection();
            PreparedStatement checkEmail = connection.prepareStatement("SELECT * FROM CUSTOMER WHERE customerEmail = ?");
            checkEmail.setString(1, customerEmail);

            ResultSet resultSet = checkEmail.executeQuery();
            if(!resultSet.next()) {
                System.out.println("Invalid Email");
                return;
            }

            Customer customer = new Customer(resultSet.getString("customerName"), resultSet.getString("customerEmail"), resultSet.getString("customerPassword"), resultSet.getString("customerContact"), resultSet.getString("customerAddress"));
            if(!customer.getCustomerPassword().equals(customerPassword)) {
                System.out.println("Invalid Password");
                return;
            }

            System.out.println("Welcome : " + customer.getCustomerName());

            currentCustomer = customer;
        }
        catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    public boolean isLoggedIn() {
        return currentCustomer!=null;
    }

    public Customer loggedInUser() {
        return currentCustomer;
    }

    public void logoutCustomer() {
        currentCustomer = null;
    }

}
