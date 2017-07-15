package io.vertx.playground;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.vertx.core.Vertx;
import io.vertx.playground.gateway.VertxGateway;
import io.vertx.playground.techio.Log;
import io.vertx.playground.techio.Techio;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public class TestUtils {

    protected static Vertx vertx = Vertx.vertx();
    private static VertxGateway gateway;

    public static void awaitForServerStartup() {
        await().atMost(5, TimeUnit.SECONDS).catchUncaughtExceptions().untilAsserted(() -> connect(8080));
        System.out.println("Server started...");
    }

    public static void viewer(String path) throws Exception {
        gateway = new VertxGateway(vertx, 8080);

        await().atMost(5, TimeUnit.SECONDS).catchUncaughtExceptions().untilAsserted(() -> {
            // TODO it would be interesting at this point to get the external url.
            Response response = RestAssured.get("http://localhost:9000/ready").andReturn();
            assert response.getStatusCode() == 200;
        });

        Techio.open(9000, "assets/" + path);
        Techio.success(true);

        Thread.sleep(1000 * 60 * 2);
        
        Log.out("<red>Stopping the gateway</red>");
    }

    public static void connect(int port) throws Exception {
        URL url = new URL("http://localhost" + port);
        URLConnection connection = url.openConnection();
        assert connection != null;
        assert connection instanceof HttpURLConnection;
    }

    public static void shutdown() {
        if (gateway != null) {
            gateway.close();
        }
        if (vertx != null) {
            vertx.close();
            vertx = null;
        }
    }
}