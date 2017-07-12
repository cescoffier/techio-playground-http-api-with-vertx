## Create a HTTP server

Enough taking, time for action... First we need an instance of Vert.x. You can build one using `Vertx.vertx()`:

@[Vert.x instance creation]({"stubs": ["src/main/java/io/vertx/playground/VertxCreationExample.java"], "command": "io.vertx.playground.VertxCreationExampleTest#test"})

Ok, now that we have our `vertx` instance, let's create a HTTP server. This is fairly easy:

@[Http Server Creation]({"stubs": ["src/main/java/io/vertx/playground/HttpServerExample.java"], "command": "io.vertx.playground.HttpServerExampleTest#test"})

Running this code also call it, so you can see what's returned by the server.

Let's look at the server creation. We attach a request handler. a `Handler` is a method taking as parameter an event (here the HTTP request) and reacting to it (here we write the message to the response). A `Handler` does not _return_ a result, it provides its result in an asynchronous way. For instance, in our example, writing the HTTP response is handled asynchronously by Vert.x. 

The request handler is called for each incoming HTTP request. We start seeing emerging the reactive flavor of this application: for every request, we react. 


