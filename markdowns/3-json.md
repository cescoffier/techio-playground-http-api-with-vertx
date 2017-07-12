## Producing JSON

JSON is everywhere Today. So let's jump into the train too and produce a JSON result. To do this, Vert.x provides:

1. the `Json` class providing object mapping methods,
2. the `JsonObject` and `JsonArray` allowing to create and manipulate JSON structures.

We are going to use this second approach to return something like:

```javascript
{
    "message": "hello"
}
```

This object can be easily created using: `new JsonObject().put("message", "hello")`. Then we just need to _encode_ this JSON structure into the HTTP response:

@[Producing JSON]({"stubs": ["src/main/java/io/vertx/playground/HttpServerJsonExample.java"], "command": "io.vertx.playground.HttpServerJsonExampleTest#test"})

