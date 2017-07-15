package io.vertx.playground;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class HttpServerQueryExample {

    public static void main(String... args) {
       Vertx vertx = Vertx.vertx(); 

       vertx.createHttpServer()
        .requestHandler(req -> {
            String name = req.getParam("name");
            String message = "hello " + (name != null && ! name.trim().isEmpty() ? name : "world") + "!";
            JsonObject json = new JsonObject()
                .put("message", message);
           req.response()
                .putHeader("Content-Type", "application/json; charset=UTF8")
                .end(json.encodePrettily());
        })
        .listen(8080);
    }

}