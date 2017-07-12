package io.vertx.playground;

import org.junit.After;
import org.junit.Test;

public class HttpServerJsonExampleTest {

    @Test
    public void test() {
     HttpServerJsonExample.main();   

     TestUtils.awaitForServerStartup();
     TestUtils.invoke();
     
    }

    @After
    public void tearDown() { 
        TestUtils.shutdown();
    }

}