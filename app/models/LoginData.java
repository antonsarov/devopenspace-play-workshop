package models;

import play.data.validation.Constraints.*;

public class LoginData {

    @Required
    @MinLength(4)
    private String username;

    @Required
    @MinLength(4)
    private String password;

    public LoginData() { }

    public LoginData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Verifying the login data
     *
     * @return null if validation has passed. Otherwise return the error message as String
     */
    public String validate() {
        if (username.equals("user1") && password.equals("pass")) {
            return null;
        }

        return "Could not find a valid username / password combination";
    }
}
