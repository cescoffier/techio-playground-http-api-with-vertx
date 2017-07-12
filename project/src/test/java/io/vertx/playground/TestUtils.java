package io.vertx.playground;

import static org.awaitility.Awaitility.await;


import io.vertx.core.Vertx;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.hamcrest.core.Is.is;

public class TestUtils {

    public static void awaitForServerStartup() {
        await().atMost(5, TimeUnit.SECONDS).catchUncaughtExceptions().untilAsserted(() -> connect());
        System.out.println("Server started...");
    }

    public static void invoke() {
        invoke("");
    }
    public static void invoke(String suffix) {
        AtomicBoolean called = new AtomicBoolean();
        Vertx vertx = Vertx.vertx();
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

	public static void connect() throws Exception {
        URL url = new URL("http://localhost:8080");
        URLConnection connection = url.openConnection();
        assert connection != null;
        assert connection instanceof HttpURLConnection;
    }
}