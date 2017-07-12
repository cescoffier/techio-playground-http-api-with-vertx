package io.vertx.playground;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class HttpServerJsonExample {

    public static void main(String... args) {
       Vertx vertx = Vertx.vertx(); 

       vertx.createHttpServer()
        .requestHandler(req -> {
            JsonObject json = new JsonObject()
                .put("message", "hello");
           req.response()
                .putHeader("content-type", "application/json; charset=UTF8")           
                .end(json.encodePrettily());
        })
        .listen(8080);
    }

}