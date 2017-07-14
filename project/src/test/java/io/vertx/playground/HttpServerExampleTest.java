package io.vertx.playground;

import org.junit.After;
import org.junit.Test;

public class HttpServerExampleTest {

    @Test
    public void test() {
     HttpServerExample.main();   

     TestUtils.awaitForServerStartup();
     TestUtils.viewer("2-invocation.html");
    }

    @After
    public void tearDown() { 
        TestUtils.shutdown();
    }

}