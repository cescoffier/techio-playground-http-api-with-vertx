## Retrieving parameters

Let's now extend our code to retrieve a parameter passed in the url (query).

You can retrieve query parameters using the `getParam` method:

@[Retrieving query parameters]({"stubs": ["src/main/java/io/vertx/playground/HttpServerQueryExample.java"], "command": "io.vertx.playground.HttpServerQueryExampleTest#test"})

Running this code invokes the server twice. Once without a parameter, and once with the name set to `vert.x`.

