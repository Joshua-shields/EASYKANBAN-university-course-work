package Login;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Login {
    public static void main(String[] args) {
        List<User> registeredUsers = new ArrayList<>(); // Holds instances of the User class.
        boolean loggedIn = false;

        JOptionPane.showMessageDialog(null, "Welcome to EasyKanban");

        while (!loggedIn) {
            int option;
            try {
                option = Integer.parseInt(JOptionPane.showInputDialog(
                        "Choose an option:\n" +
                                "1. Register\n" +
                                "2. Login"));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid option. Please enter a number.");
                continue;
            }

            if (option == 1) {
                // User registration
                String username; // Holds the username entered by the user.
                String password; // Holds the password entered by the user.
                String firstName; // Holds the first name entered by the user.
                String lastName; // Holds the last name entered by the user.

                do {
                    username = JOptionPane.showInputDialog(
                            "Enter a username (must contain an underscore and be no more than 5 characters long):");

                    if (!checkUserName(username)) {
                        JOptionPane.showMessageDialog(null, "Username is not correctly formatted. Please ensure that your username contains an underscore and is no more than 5 characters in length.");
                    } else {
                        final String finalUsername = username;
                        boolean usernameExists = false;
                        for (User user : registeredUsers) {
                            if (user.getUsername().equalsIgnoreCase(finalUsername)) {
                                usernameExists = true;
                                break;
                            }
                        }
                        if (usernameExists) {
                            JOptionPane.showMessageDialog(null, "Username is already taken. Please choose a different username.");
                            username = null; // Reset the username if it's already taken
                        }
                    }
                } while (!checkUserName(username) || username == null);

                do {
                    password = JOptionPane.showInputDialog(
                            "Enter a password (at least 8 characters long and contains at least one uppercase letter, one number, and one character that is not a letter or number):");

                    if (!checkPasswordComplexity(password)) {
                        JOptionPane.showMessageDialog(null, "Password is not correctly formatted. Please ensure that the password contains at least 8 characters, a capital letter, a number, and a special character.");
                    }
                } while (!checkPasswordComplexity(password));

                firstName = JOptionPane.showInputDialog("Enter your first name:");
                lastName = JOptionPane.showInputDialog("Enter your last name:");

                registeredUsers.add(new User(username, password, firstName, lastName));
                JOptionPane.showMessageDialog(null, "Registration successful. Please login.");
            } else if (option == 2) {
                // User login
                String username; // Holds the username entered by the user during login.
                String password; // Holds the password entered by the user during login.

                username = JOptionPane.showInputDialog("Enter your username:");
                password = JOptionPane.showInputDialog("Enter your password:");

                boolean loginSuccessful = false;
                User loggedInUser = null;

                for (User user : registeredUsers) {
                    if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                        loginSuccessful = true;
                        loggedInUser = user;
                        break;
                    }
                }

                if (loginSuccessful) {
                    String welcomeMessage = "Welcome " + loggedInUser.getFirstName() + " " + loggedInUser.getLastName() + ", it is great to see you again."; // Greet the user if they logged in successfully
                    JOptionPane.showMessageDialog(null, welcomeMessage);
                    loggedIn = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Login failed. Please try again.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");
            }
        }
    }

    /**
     * The User class represents a user object with username, password, first name, and last name.
     * It provides methods to access and retrieve user information.
     */
    public static class User {
        private String username; // Holds the username of the user.
        private String password; // Holds the password of the user.
        private String firstName; // Holds the first name of the user.
        private String lastName; // Holds the last name of the user.

        /**
         * Constructs a User object with the provided username, password, first name, and last name.
         *
         * @param username  The username of the user.
         * @param password  The password of the user.
         * @param firstName The first name of the user.
         * @param lastName  The last name of the user.
         */
        public User(String username, String password, String firstName, String lastName) {
            this.username = username;
            this.password = password;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        /**
         * Retrieves the username of the user.
         *
         * @return The username of the user.
         */
        public String getUsername() {
            return username;
        }

        /**
         * Retrieves the password of the user.
         *
         * @return The password of the user.
         */
        public String getPassword() {
            return password;
        }

        /**
         * Retrieves the first name of the user.
         *
         * @return The first name of the user.
         */
        public String getFirstName() {
            return firstName;
        }

        /**
         * Retrieves the last name of the user.
         *
         * @return The last name of the user.
         */
        public String getLastName() {
            return lastName;
        }
    }

    /**
     * Checks if the provided username meets the required format criteria.
     * The username must contain an underscore and be no more than 5 characters long.
     *
     * @param username The username to be checked.
     * @return True if the username is correctly formatted, false otherwise.
     */
    public static boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    /**
     * Checks if the provided password meets the required complexity criteria.
     * The password must be at least 8 characters long, contain at least one uppercase letter, one number,
     * and one character that is not a letter or number.
     *
     *  The password to be checked.
     * @return True if the password is correctly formatted, false otherwise.
     */
    public static boolean checkPasswordComplexity(String password) {
        return password.length() >= 8 && password.matches(".*[A-Z].*") && password.matches(".*[0-9].*") && password.matches(".*[^A-Za-z0-9].*");
    }
}


//****************************************************************************END OF FILE ******************************************************//
