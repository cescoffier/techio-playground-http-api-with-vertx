package io.vertx.playground;

import org.junit.Test;

public class HttpServerExampleTest {

    @Test
    public void test() {
     HttpServerExample.main();   

     TestUtils.awaitForServerStartup();
     TestUtils.invoke();
     
    }

}