package models;

import play.Logger;
import play.data.validation.Constraints.*;
import play.libs.ws.WS;
import play.libs.ws.WSRequest;
import play.libs.ws.WSResponse;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;

public class LoginData {

    @Required
    @MinLength(4)
    private String username;

    @Required
    @MinLength(4)
    private String password;

    public static final String AUTH_URL = "http://devbay-auth.herokuapp.com/auth";
    public static final String AUTH_URL_LOCAL = "http://localhost:9001/auth";

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
        try {
            final String usernameToSend = URLEncoder.encode(username, "UTF-8");
            final String passwordToSend = URLEncoder.encode(password, "UTF-8");
            WSResponse wsResponse = WS.url(AUTH_URL_LOCAL).
                    setContentType("application/x-www-form-urlencoded").
                    post("username=" + usernameToSend + "&password=" + passwordToSend).
                    get(10, TimeUnit.SECONDS);
            if (wsResponse.getStatus()==200) {
                return null;
            } else {
                return "Could not find a valid username / password combination";
            }
        } catch (UnsupportedEncodingException e) {
            Logger.error("Very very bad", e);
            return "Could not find a valid username / password combination";
        }

    }
}
