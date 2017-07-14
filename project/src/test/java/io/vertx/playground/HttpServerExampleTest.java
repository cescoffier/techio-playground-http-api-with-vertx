package io.vertx.playground;

import org.junit.After;
import org.junit.Test;

import java.net.MalformedURLException;

public class HttpServerExampleTest {

    @Test
    public void test() throws MalformedURLException {
     HttpServerExample.main();   

     TestUtils.awaitForServerStartup();
     TestUtils.viewer("2-invocation.html");
    }

    @After
    public void tearDown() { 
        TestUtils.shutdown();
    }

}