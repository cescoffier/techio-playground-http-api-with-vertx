package io.vertx.playground.techio;

/**
 * @author <a href="http://escoffier.me">Clement Escoffier</a>
 */
public class Techio {

    private static String PROMPT = "TECHIO> ";

    public static void success(boolean success) {
        System.out.println(PROMPT + "success " + success);
    }

    public static void open(int port, String path) {
        System.out.println(PROMPT + "open --port " + port + " " + path);

    }

}
