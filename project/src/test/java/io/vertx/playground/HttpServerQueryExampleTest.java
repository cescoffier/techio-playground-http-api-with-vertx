package io.vertx.playground;

import org.junit.After;
import org.junit.Test;

public class HttpServerQueryExampleTest {

    @Test
    public void test() throws Exception {
        HttpServerQueryExample.main();

        TestUtils.awaitForServerStartup();
        TestUtils.viewer("4-invocation-with-json.html");
    }

    @After
    public void tearDown() {
        TestUtils.shutdown();
    }

}