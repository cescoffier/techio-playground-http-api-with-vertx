package io.vertx.playground;

import org.junit.After;
import org.junit.Test;

public class HttpServerEventLoopExampleTest {

    @Test
    public void test() throws Exception {
        HttpServerEventLoopExample.main();

        TestUtils.awaitForServerStartup();
        TestUtils.viewer("2-invocation.html");
    }

    @After
    public void tearDown() {
        TestUtils.shutdown();
    }

}