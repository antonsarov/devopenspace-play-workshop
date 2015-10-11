package controllers;

import models.LoginData;
import play.*;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;

import util.Secured;
import views.html.*;

public class Application extends Controller {

    public static final Form<LoginData> loginForm = Form.form(LoginData.class);

    @Security.Authenticated(Secured.class)
    public Result index() {
        return ok(index.render(session()));
    }

    public Result login() {
        return ok(login.render(loginForm));
    }

    public Result doLogin() {
        Form<LoginData> submittedForm = loginForm.bindFromRequest();
        if (submittedForm.hasErrors()) {
            return badRequest(login.render(submittedForm));
        } else {
            session().put(Secured.USERNAME_SESSION_KEY, submittedForm.get().getUsername());
            return redirect(routes.Application.index());

        }
    }

    public Result logout() {
        session().clear();
        return redirect(routes.Application.index());
    }
}
