package controllers;

import akka.actor.ActorSystem;
import models.Item;
import models.LoginData;
import play.*;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;

import scala.concurrent.duration.Duration;
import util.Secured;
import views.html.*;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class Application extends Controller {

    public static final Form<LoginData> loginForm = Form.form(LoginData.class);

    @Inject
    public Application(ActorSystem system) {
        system.scheduler().scheduleOnce(
                Duration.create(10, TimeUnit.MILLISECONDS),
                initData(),
                system.dispatcher()
        );
    }

    private Runnable initData() {
        if (Item.find.all().isEmpty()) {
            Item dellStreak = new Item("Dell Streak 7", "dell-streak-7.0.jpg", new BigDecimal("1"));
            dellStreak.save();
            Item dellVenue = new Item("Dell Venue", "dell-venue.0.jpg", new BigDecimal("1"));
            dellVenue.save();
            Item lgAxis = new Item("LG Axis", "lg-axis.0.jpg", new BigDecimal("1"));
            lgAxis.save();
            Item motorolaAtrix = new Item("Motorola Atrix 4G", "motorola-atrix-4g.0.jpg", new BigDecimal("1"));
            motorolaAtrix.save();
        }
        return null;
    }

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
