package io.vertx.playground;

import static org.awaitility.Awaitility.await;


import io.vertx.core.Vertx;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.hamcrest.core.Is.is;

public class TestUtils {

    protected static Vertx vertx = Vertx.vertx();
    private static VertxGateway gateway;

    public static void awaitForServerStartup() {
        await().atMost(5, TimeUnit.SECONDS).catchUncaughtExceptions().untilAsserted(() -> connect(8080));
        System.out.println("Server started...");
    }

    public static void invoke() {
        invoke("");
    }

    public static void viewer(String path) {
        gateway = new VertxGateway(vertx, 8080);
        await().atMost(5, TimeUnit.SECONDS).catchUncaughtExceptions().untilAsserted(() -> connect(9000));

        try {
            new ProcessBuilder()
                .command("open", "--port", "9000", "assets/" + path).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void invoke(String suffix) {
        AtomicBoolean called = new AtomicBoolean();
        vertx.createHttpClient()
            .get(8080, "localhost", "/" + suffix)
            .handler(resp -> {
                System.out.println("HTTP Response >> " + resp.statusMessage());
                resp.bodyHandler(buffer -> {
                    System.out.println("Body >> " + buffer.toString());
                    called.set(true);
                });
            })
            .end();
        await().untilAtomic(called, is(true));    
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