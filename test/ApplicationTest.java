import org.junit.*;

import play.mvc.*;
import play.mvc.Http.RequestBuilder;
import play.test.*;
import play.libs.F.*;
import play.twirl.api.Content;

import static play.test.Helpers.*;
import static org.junit.Assert.*;

import static org.fluentlenium.core.filter.FilterConstructor.*;

public class ApplicationTest extends WithApplication {

    @Test
    public void testCallIndex() {
        Result result = route(controllers.routes.Application.index());
        assertEquals(OK, result.status());
    }

    @Test
    public void testRequestIndex() {
        RequestBuilder request = new RequestBuilder().method(GET).uri("/");
        Result result = route(request);
        assertEquals(OK, result.status());
    }

    @Test
    public void testRequestLogin() {
        RequestBuilder request = new RequestBuilder().method(GET).uri("/login");
        Result result = route(request);
        assertEquals(OK, result.status());
        assertTrue(contentAsString(result).contains("Login"));
    }

    @Test
    public void testRequestLoginView() {
        Content html = views.html.login.render("DevBay");
        assertEquals("text/html", html.contentType());
        assertTrue(contentAsString(html).contains("DevBay"));
        assertTrue(contentAsString(html).contains("<li>77</li>"));
    }
}
