package io.vertx.playground;

import io.vertx.core.Vertx;

public class HttpServerExample {

    public static void main(String... args) {
       Vertx vertx = Vertx.vertx(); 

       vertx.createHttpServer()
        .requestHandler(req -> {
           req.response().end("hello !");
        })
        .listen(8080);
    }

}