package io.vertx.playground;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class HttpServerEventLoopExample {

    public static void main(String... args) {
       Vertx vertx = Vertx.vertx(); 

       vertx.createHttpServer()
        .requestHandler(req -> {
            String name = req.getParam("name");
            String message = "hello " + (name != null ? name : "world") + "!";
            JsonObject json = new JsonObject()
                .put("message", message)
                .put("thread", Thread.currentThread().getName());
           req.response()
                .putHeader("Content-Type", "application/json; charset=UTF8")
                .end(json.encodePrettily());
        })
        .listen(8080);
    }

}