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
        return TODO;
    }
}
