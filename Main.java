import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class User {
    private String userID;
    private int pin;
    private double accountBalance;

    public User(String userID, int pin, double accountBalance) {
        this.userID = userID;
        this.pin = pin;
        this.accountBalance = accountBalance;
    }

    public String getUserID() {
        return userID;
    }

    public int getPin() {
        return pin;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
}

