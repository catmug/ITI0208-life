
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class EndTest extends AbstractTest {

    private final String BASE_URL = "http://localhost:8080/api/up";

    @Test
    public void baseUrlResponds() {
        boolean isSuccess = sendRequest(getBaseUrl());

        assertThat(isSuccess, is(true));
    }


    @Override
    protected String getBaseUrl() {
        return BASE_URL;
    }
}
