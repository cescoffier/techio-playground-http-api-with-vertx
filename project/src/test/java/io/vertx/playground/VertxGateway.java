package io.vertx.playground;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.playground.techio.Log;

import java.util.Objects;

/**
 * @author <a href="http://escoffier.me">Clement Escoffier</a>
 */
public class VertxGateway {


    private final Vertx vertx;
    private final WebClient client;

    public VertxGateway(Vertx vertx, int port) {
        this.vertx = Objects.requireNonNull(vertx);
        this.client = WebClient.create(vertx, new WebClientOptions().setDefaultHost("localhost").setDefaultPort(port));
        Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create());
        router.post("/gateway").handler(this::delegate);
        router.get("ready").handler(rc -> rc.response().end("OK"));

        router.route().handler(rc -> {
            Log.out("Got request: <blue>%s</blue> <red>%s</red>", rc.request().rawMethod(), rc.request().path());
            rc.next();
        });

        router.get("/assets/*").handler(StaticHandler.create("assets"));

        this.vertx.createHttpServer()
            .requestHandler(router::accept)
            .listen(9000, ar -> {
                Log.out("Gateway ready on port <blue>%d</blue>", ar.result().actualPort());
            });
    }

    private void delegate(RoutingContext rc) {
        JsonObject json = rc.getBodyAsJson();


        // Method, Path
        String method = json.getString("method", "GET").toUpperCase();
        String path = json.getString("path", "/");

        String query = json.getString("query");

        JsonObject body = json.getJsonObject("body");

        HttpRequest<Buffer> request = client.request(HttpMethod.valueOf(method), path + (query == null ? "" : "?" + query));

        Handler<AsyncResult<HttpResponse<Buffer>>> handler = (resp -> {
            HttpServerResponse response = rc.response();
            if (resp.failed()) {
                response.setStatusCode(400).end(
                    new JsonObject()
                        .put("success", false)
                        .put("error", "invocation failed")
                        .put("reason", resp.cause().getMessage())
                        .encodePrettily());
            } else {
                Buffer res = resp.result().body();
                JsonObject payload = new JsonObject()
                    .put("success", true)
                    .put("body", res)
                    .put("status-code", resp.result().statusCode())
                    .put("status-message", resp.result().statusMessage())
                    .put("http-version", resp.result().version())
                    .put("headers", Json.encode(resp.result().headers()));
                response
                    .setStatusCode(200)
                    .end(payload.encodePrettily());
            }
        });

        if (body != null) {
            request.sendJsonObject(body, handler);
        } else {
            request.send(handler);
        }
    }

    public void close() {
        if (client != null) {
            client.close();
        }
    }
}
