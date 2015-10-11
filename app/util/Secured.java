package util;

import play.mvc.*;
import play.mvc.Http.*;

public class Secured extends Security.Authenticator {

  public static final String USERNAME_SESSION_KEY = "username";

  @Override
  public String getUsername(Context ctx) {
    return ctx.session().get(USERNAME_SESSION_KEY);
  }

  @Override
  public Result onUnauthorized(Context ctx) {
    return redirect(controllers.routes.Application.login());
  }
}