package io.vertx.playground;

import org.junit.After;
import org.junit.Test;

public class HttpServerJsonExampleTest {

    @Test
    public void test() throws Exception {
     HttpServerJsonExample.main();   

     TestUtils.awaitForServerStartup();
     TestUtils.viewer("2-invocation.html");

    }

    @After
    public void tearDown() { 
        TestUtils.shutdown();
    }

}