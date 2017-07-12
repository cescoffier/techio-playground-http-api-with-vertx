package io.vertx.playground;

import org.junit.Test;

public class HttpServerQueryExampleTest {

    @Test
    public void test() {
     HttpServerQueryExample.main();   

     TestUtils.awaitForServerStartup();
     System.out.println("Invocation without parameter (http://localhost:8080)");
     TestUtils.invoke();
     System.out.println("Invocation with parameter (http://localhost:8080?name=vert.x)");
    TestUtils.invoke("?name=vertx");
    }

}