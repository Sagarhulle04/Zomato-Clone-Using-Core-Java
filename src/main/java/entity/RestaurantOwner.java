package entity;

public class RestaurantOwner {
    private int restaurantId;
    private String restaurantOwnerName ;
    private String  restaurantName;
    private String  restaurantEmail;
    private String restaurantPassword;
    private String restaurantContact;
    private String restaurantAddress;

    public RestaurantOwner(String restaurantOwnerName, String restaurantName, String restaurantEmail, String restaurantPassword, String restaurantContact, String restaurantAddress) {
        this.restaurantOwnerName = restaurantOwnerName;
        this.restaurantName = restaurantName;
        this.restaurantEmail = restaurantEmail;
        this.restaurantPassword = restaurantPassword;
        this.restaurantContact = restaurantContact;
        this.restaurantAddress = restaurantAddress;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantOwnerName() {
        return restaurantOwnerName;
    }

    public void setRestaurantOwnerName(String restaurantOwnerName) {
        this.restaurantOwnerName = restaurantOwnerName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantEmail() {
        return restaurantEmail;
    }

    public void setRestaurantEmail(String restaurantEmail) {
        this.restaurantEmail = restaurantEmail;
    }

    public String getRestaurantPassword() {
        return restaurantPassword;
    }

    public void setRestaurantPassword(String restaurantPassword) {
        this.restaurantPassword = restaurantPassword;
    }

    public String getRestaurantContact() {
        return restaurantContact;
    }

    public void setRestaurantContact(String restaurantContact) {
        this.restaurantContact = restaurantContact;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    @Override
    public String toString() {
        return restaurantId + " | " + restaurantOwnerName + " | " + restaurantName + " | " + restaurantEmail + " | " + restaurantContact + " | " + restaurantAddress;
    }
}
