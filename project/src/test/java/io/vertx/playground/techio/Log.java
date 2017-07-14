package io.vertx.playground.techio;

/**
 * @author <a href="http://escoffier.me">Clement Escoffier</a>
 */
public class Log {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void out(String msg, Object... params) {
        String colored = msg
            .replace("<black>", "")
            .replace("<blue>", ANSI_BLUE)
            .replace("<cyan>", ANSI_CYAN)
            .replace("<green>", ANSI_GREEN)
            .replace("<purple>", ANSI_PURPLE)
            .replace("<red>", ANSI_RED)
            .replace("<white>", ANSI_WHITE)
            .replace("<yellow>", ANSI_YELLOW)

            .replace("</black>", ANSI_RESET)
            .replace("</blue>", ANSI_RESET)
            .replace("</cyan>", ANSI_RESET)
            .replace("</green>", ANSI_RESET)
            .replace("</purple>", ANSI_RESET)
            .replace("</red>", ANSI_RESET)
            .replace("</white>", ANSI_RESET)
            .replace("</hello>", ANSI_RESET);

        System.out.println(String.format(colored, params));
    }
}
