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
}
