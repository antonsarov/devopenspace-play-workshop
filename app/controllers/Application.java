package controllers;

import models.LoginData;
import play.*;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static final Form<LoginData> loginForm = Form.form(LoginData.class);

    public Result index() {
        return ok();
    }

    public Result login() {
        return ok(login.render("DevBay"));
    }

    public Result doLogin() {
        Form<LoginData> submittedForm = loginForm.bindFromRequest();
        if (submittedForm.hasErrors()) {
            return badRequest("Username and password validation failed.");
        } else {
            String username = submittedForm.get().getUsername();
            return ok("Hello, " + username);
        }
    }
}
