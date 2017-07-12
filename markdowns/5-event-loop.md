## Observing the event loop

Things are going to be a bit more interesting now... Let's modify our code to print in the JSON response the name of the thread handling the request:

@[Observing the event loop]({"stubs": ["src/main/java/io/vertx/playground/HttpServerEventLoopExample.java"], "command": "io.vertx.playground.HttpServerEventLoopExampleTest#test"})

When running this code, it invokes the server 10 times. Notice that the thread name is always the same. This is an illustration of the Vert.x execution model. 

Vert.x uses very few threads, and some of them are event loops. The only purpose of the event loop is to get incoming "events" (here HTTP request), find the interested `Handler` (here the request handler) and invoke it. This is a very simple execution model simplifying concurrency (you are always called by the same thread). However... it comes with a golden rule: **Never block the event loop**. The reason is simple. If a `Handler` blocks the event loop, the incoming events would be enqueued in a buffer and would only be processed when the blocking handler release the thread. You would loose the advantage of the event loop and making you application sloppy. So **don't block the event loop**. 

How to achieve this? Vert.x provides a large set of API all asynchronous and non-blocking. It also provides constructs to handle blocking code, but this would be covered in another tutorial.