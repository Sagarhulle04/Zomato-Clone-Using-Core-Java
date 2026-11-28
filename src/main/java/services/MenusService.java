package services;

import entity.Menus;
import org.zomato.config.DbConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class MenusService {
    private Menus menus;

    /*
    menuId int primary key auto_increment,
    restaurantId int,
    menuName varchar(100) not null,
    menuDescription varchar(255),
    menuPrice double not null,
    menuType varchar(20),
     */

    public void addMenus(int restaurantId, String menuName, String menuDescription, double menuPrice, String menuType) {
        try {
            Connection connection = DbConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO MENUS (restaurantId, menuName, menuDescription, menuPrice, menuType) VALUES (?,?,?,?,?)");
            preparedStatement.setInt(1, restaurantId);
            preparedStatement.setString(2, menuName);
            preparedStatement.setString(3, menuDescription);
            preparedStatement.setDouble(4, menuPrice);
            preparedStatement.setString(5, menuType);

            int row = preparedStatement.executeUpdate();

            if(row == 1) {
                System.out.println("Menu Added Successfully.");
            }
            else {
                System.out.println("Something Went Wrong. Please Try Again.");
            }

        }
        catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}
