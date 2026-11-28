package services;

import entity.RestaurantOwner;
import org.zomato.config.DbConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class RestaurantOwnerService {

    private RestaurantOwner currentRestaurantOwner = null;

    public void registerRestaurantOwner(String restaurantOwnerName, String restaurantName, String restaurantEmail, String restaurantPassword, String restaurantContact, String restaurantAddress) {
        try {
            Connection connection = DbConfig.getConnection();
            PreparedStatement checkEmail = connection.prepareStatement("SELECT * FROM restaurantOwner WHERE restaurantEmail = ?");
            checkEmail.setString(1, "restaurantEmail");

            ResultSet resultSet = checkEmail.executeQuery();

            if(resultSet.next()) {
                System.out.println("Email already exists");
                return;
            }

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO restaurantOwner (restaurantOwnerName, restaurantName, restaurantEmail, restaurantPassword, restaurantContact, restaurantAddress) VALUES (?,?,?,?,?,?)");
            preparedStatement.setString(1, restaurantOwnerName);
            preparedStatement.setString(2, restaurantName);
            preparedStatement.setString(3, restaurantEmail);
            preparedStatement.setString(4, restaurantPassword);
            preparedStatement.setString(5, restaurantContact);
            preparedStatement.setString(6, restaurantAddress);

            int row = preparedStatement.executeUpdate();

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

    public void loginRestaurantOwner(String restaurantEmail, String restaurantPassword) {
        try{
            Connection connection = DbConfig.getConnection();
            PreparedStatement checkEmail = connection.prepareStatement("SELECT * FROM restaurantOwner WHERE restaurantEmail = ?");
            checkEmail.setString(1, restaurantEmail);

            ResultSet resultSet = checkEmail.executeQuery();

            if(!resultSet.next()) {
                System.out.println("Invalid Email");
                return;
            }

            RestaurantOwner restaurantOwner = new RestaurantOwner(resultSet.getString("restaurantOwnerName"), resultSet.getString("restaurantName"), resultSet.getString("restaurantEmail"), resultSet.getString("restaurantPassword"), resultSet.getString("restaurantContact"), resultSet.getString("restaurantAddress"));

            if(!restaurantOwner.getRestaurantPassword().equals(restaurantPassword)) {
                System.out.println("Invalid Password");
                return;
            }
            currentRestaurantOwner = restaurantOwner;

            System.out.println("Welcome : " + restaurantOwner.getRestaurantOwnerName());
        }
        catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    public boolean isLoggedRestaurantOwner() {
        return currentRestaurantOwner!=null;
    }

    public RestaurantOwner restaurantOwnerDetails() {
        return currentRestaurantOwner;
    }

    public void logoutRestaurantOwner() {
        currentRestaurantOwner = null;
    }

    public void searchRestaurant(String restaurantName) {
        try {
            Connection connection = DbConfig.getConnection();
            PreparedStatement checkRestaurant = connection.prepareStatement("select *, lower(restaurantName) from restaurantOwner where lower(restaurantName) = ?");
            checkRestaurant.setString(1, restaurantName);
            ResultSet resultSet = checkRestaurant.executeQuery();

            ArrayList<RestaurantOwner> al = new ArrayList<>();

            while (resultSet.next()) {

                RestaurantOwner restaurantOwner = new RestaurantOwner(
                        resultSet.getString("restaurantOwnerName"),
                        resultSet.getString("restaurantName"),
                        resultSet.getString("restaurantEmail"),
                        resultSet.getString("restaurantPassword"),
                        resultSet.getString("restaurantContact"),
                        resultSet.getString("restaurantAddress")
                );

                al.add(restaurantOwner);
            }

            if (al.isEmpty()) {
                System.out.println("No restaurants found.");
            } else {
                for (RestaurantOwner restaurantOwner : al) {
                    System.out.println(restaurantOwner);
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    public void seeAllRestaurants() {
        try {
            Connection connection = DbConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT restaurantName from restaurantOwner");

            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<String> listOfRestaurants = new ArrayList<>();

            while(resultSet.next()) {
                listOfRestaurants.add(resultSet.getString("restaurantName"));
            }

            for(String listOfRestaurant : listOfRestaurants) {
                System.out.println(listOfRestaurant);
            }


        }
        catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}
