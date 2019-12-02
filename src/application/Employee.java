package application;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Creates employee using regex to create usernames, emails, passwords, etc.
 */
public class Employee {

    StringBuilder name = new StringBuilder();
    String username;
    String myPassword;
    String email;
    String enteredName;
    String firstName;
    String lastName;

    /**
     * Constructs employee
     *
     * @param name
     * @param password
     */
    public Employee(String name, String password) {

        enteredName = name;
        myPassword = password;
        if (checkName()) {
            setUsername(name);
            setEmail(name);
        } else {
            username = "default";
            email = "user@oracleacademy.Test";
        }
        if (!isValidPassword()) {
            myPassword = "pw";
        }
    }

    /**
     * Sets employee username
     *
     * @param name
     */
    private void setUsername(String name) {

        username = name.substring(0, 1).toLowerCase() + lastName.toLowerCase();
    }

    /**
     * Checks entered name with regex
     *
     * @return true or false for "is the name valid?"
     */
    private boolean checkName() {

        // Must contain 2 words with space in between
        final String regex = "([a-zA-Z]+) ([a-zA-Z]+)";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(enteredName);

        while (matcher.find()) {
            //System.out.println("Full match: " + matcher.group(0));
            for (int i = 1; i <= matcher.groupCount(); i++) {
                firstName = matcher.group(1);
                lastName = matcher.group(2);
                //System.out.println("First name = " + firstName);
                //System.out.println("Last name = " + lastName);
                return true;
            }
        }
        return false;
    }

    /**
     * Sets employee email
     *
     * @param name
     */
    private void setEmail(String name) {
        email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@oracleacademy.Test";
        //System.out.println("Email = " + email);
    }

    /**
     * Checks entered password with regex
     *
     * @return true or false for "is the password valid?"
     */
    private boolean isValidPassword() {

        // Must contain at least 5 characters
        // At least one of each(lowerCase, upperCase, number, specialChar)
        final String regex = "^(?=.{5,}$)(?=.*[a-z])(?=.*[A-Z])(?=.*)(?=.*\\W).*$";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(myPassword);

        if (matcher.find()) {
            //System.out.println("Full match: " + matcher.group(0));
            for (int i = 1; i <= matcher.groupCount(); i++) {
                //System.out.println("Group " + i + ": " + matcher.group(i));
                return true;
            }
        } else {
            //System.out.println("Failed isValidPassword!");
            return false;
        }
        return true;
    }

    /**
     * Prints employee details
     *
     * @return employee details
     */
    public String toString() {
        return "Employee Details\n"
                + "Name : " + enteredName + "\n"
                + "Username : " + username + "\n"
                + "Email : " + email + "\n"
                + "Initial Password : " + myPassword;
    }

}


