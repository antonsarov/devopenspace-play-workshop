package util;

import play.Configuration;
import play.Environment;
import play.api.OptionalSourceMapper;
import play.api.routing.Router;
import play.http.DefaultHttpErrorHandler;
import play.libs.F;
import play.mvc.Http;
import play.mvc.Result;
import static play.mvc.Results.badRequest;

import javax.inject.Inject;
import javax.inject.Provider;

public class ErrorHandler extends DefaultHttpErrorHandler {
  // Execute default error handling
  @Inject
  public ErrorHandler(Configuration configuration, Environment environment, OptionalSourceMapper sourceMapper, Provider<Router> routes) {
    super(configuration, environment, sourceMapper, routes);
  }

  // Override page not found behavior
  @Override
  protected F.Promise<Result> onNotFound(Http.RequestHeader requestHeader, String s) {
    return F.Promise.pure(badRequest(
            views.html.notFound.render(requestHeader.uri())));
  }
}