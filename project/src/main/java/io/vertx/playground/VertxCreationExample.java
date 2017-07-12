package io.vertx.playground;

import io.vertx.core.Vertx;

public class VertxCreationExample {

    public static void main(String... args) {
        Vertx vertx = Vertx.vertx();
        System.out.println("Vert.x instance: " + vertx);
    }

}