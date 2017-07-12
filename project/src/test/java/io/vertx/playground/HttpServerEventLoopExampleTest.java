package io.vertx.playground;

import org.junit.Test;

public class HttpServerEventLoopExampleTest {

    @Test
    public void test() {
     HttpServerEventLoopExample.main();   

     TestUtils.awaitForServerStartup();
     for (int i = 0; i < 10; i++) {
         System.out.println("Invocation " + i + " :");
         TestUtils.invoke();
     }
    }

}