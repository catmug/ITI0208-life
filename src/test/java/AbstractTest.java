import ee.expensetracker.model.Expense;
import model.Result;
import model.ValidationErrors;
import org.junit.Rule;
import org.junit.rules.Timeout;
import util.ContentTypeFilter;
import util.LoggingFilter;
import util.NopX509TrustManager;
import util.Parameter;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.SecureRandom;
import java.util.List;

public abstract class AbstractTest {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(10);

    protected abstract String getBaseUrl();

    private static boolean isDebug = false;

    protected static void setDebug(boolean debug) {
        isDebug = debug;
    }

    private static Client getClient() {
        try {
            SSLContext sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(null, new TrustManager[] {new NopX509TrustManager()}, new SecureRandom());
            return ClientBuilder.newBuilder()
                    .register(new LoggingFilter(isDebug))
                    .register(ContentTypeFilter.class)
                    .sslContext(sslcontext).hostnameVerifier((s1, s2) -> true).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public WebTarget getTarget() {
        return getClient().target(getBaseUrl());
    }

    protected List<Expense> getList(String path, Parameter... parameters) {
        WebTarget target = getTarget().path(path);

        for (Parameter p : parameters) {
            target = target.queryParam(p.getKey(), p.getValue());
        }

        return target
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Expense>>() {});
    }

    protected Expense getOne(String path, Parameter... parameters) {
        return getOne(path, Expense.class, parameters);
    }

    protected <T> T getOne(String path, Class<T> clazz, Parameter ... parameters) {
        WebTarget target = getTarget().path(path);

        for (Parameter p : parameters) {
            target = target.queryParam(p.getKey(), p.getValue());
        }

        return target
                .request(MediaType.APPLICATION_JSON)
                .get(clazz);
    }

    protected Parameter param(String key, Object value) {
        return new Parameter(key, String.valueOf(value));
    }

    protected Result<Expense> postExpense(String path, Expense data) {
        Response response = getTarget()
                .path(path)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(data, MediaType.APPLICATION_JSON));

        Result<Expense> result = new Result<>();

        if (response.getStatus() == Response.Status.BAD_REQUEST.getStatusCode()) {
            result.setErrors(response.readEntity(ValidationErrors.class).getErrors());
        } else {
            result.setValue(response.readEntity(Expense.class));
        }

        return result;
    }

    protected boolean sendRequest(String path) {
        Response response = getClient()
                .target(path)
                .request()
//                .header("x-is-base-url-request", "true")
                .get();
        System.out.println(response.getStatus());

        return Response.Status.OK.getStatusCode() == response.getStatus();
    }

    protected Result<Expense> postOrderFromJsonString(String path, String data) {
        Response response = getTarget()
                .path(path)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(data, MediaType.APPLICATION_JSON));

        Result<Expense> result = new Result<>();

        if (response.getStatus() == Response.Status.BAD_REQUEST.getStatusCode()) {
            result.setErrors(response.readEntity(ValidationErrors.class).getErrors());
        } else {
            result.setValue(response.readEntity(Expense.class));
        }

        return result;
    }

    protected void delete(String path, Parameter ... parameters) {
        WebTarget target = getTarget().path(path);

        for (Parameter p : parameters) {
            target = target.queryParam(p.getKey(), p.getValue());
        }

        target.request().delete();
    }

}
