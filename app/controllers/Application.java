package controllers;

import play.*;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public Result index() {
        return ok();
    }

    public Result login() {
        return ok(login.render("DevBay"));
    }

    public Result doLogin() {
        DynamicForm dynamicForm = Form.form().bindFromRequest();
        String username = dynamicForm.get("username");
        String password = dynamicForm.get("password");
        if (username!=null && !username.trim().isEmpty()) {
            if (password!=null && !password.trim().isEmpty()) {
                return ok("You may pass!");
            }
        }
        return forbidden("You shall not pass!");
    }
}
