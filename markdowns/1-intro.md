# Building a HTTP endpoint with Vert.x

Let's say you heard about [Eclipse Vert.x](http://vertx.io) and you want to start right away to better understand what's this _beast_. This tutorial is a good start. In this tutorial, you learn:

* what's vert.x briefly
* how to create a HTTP server
* how to produce JSON responses
* how to get parameters
* what's an event loop

It's going to take 10 minutes to complete, so better start now!

## What's Vert.x

We will attempt to explain Vert.x in just a few lines. As written on the Vert.x [web site](http://vertx.io), Vert.x is "a toolkit for building reactive applications on the JVM".

There are a three important points in this description: toolkit, reactive and "on the JVM".

Firstly, Vert.x is a toolkit. Meaning, Vert.x is not an application server, a container nor a framework. It’s not a JavaScript library either. Vert.x is a plain old jar file, so a Vert.x application is an application that uses this jar file. Vert.x does not define a packaging model, all Vert.x components are plain boring jar files. How does this impact you and your application? Let’s imagine you are using a build tool such as Maven or Gradle, to make your application a Vert.x application just add the `vertx-core` dependency. Wanna use another Vert.x components, just add it as a dependency. It’s simple, burden-less. Starting the application is a simple class with the `public static void main(String[] args)` entry point. No specific IDE or plugin to install to start using Vert.x.

Therefore, to use the awesomeness provided by Vert.x, you just need to use it in your code, but be patient, this will be covered later.

Then, Vert.x is reactive. Don't be confused by _Reactive_. They are many _Reactive_ things Today. To make things clearer let's distinguish reactive programming - APIs to build asynchronous, data-oriented programs, and reactive systems - an architectural style to build better distributed system. Vert.x marries both. Vert.x is specifically made to build reactive applications, or more appropriately, systems. Reactive systems has been defined in the [Reactive Manifesto](http://www.reactivemanifesto.org). Although, it’s not long document to read, we will further reduce it to these 4 bullet points:

* Responsive: a reactive system needs to handle requests in a reasonable time (I let you define reasonable).
* Resilient: a reactive system must stay responsive in the face of failures (crash, timeout, 500 errors…​), so it must be designed for failures and deal with them appropriately.
* Elastic: a reactive system must stay responsive under various loads. As a consequence, it must scale up and down, and be able to handle the load with minimal resources.
* Message driven: components from a reactive system interacts using asynchronous message-passing.


Also, Vert.x is event-driven and also non-blocking. Events are delivered in an event loop that must **never** be blocked. We will see then event loop in action in this tutorial.

Finally, Vert.x applications runs "on the JVM" the Java Virtual Machine (8+). This means Vert.x applications can be developed using any language that runs on the JVM. Including Java(of course), Groovy, Ceylon, Ruby, JavaScript, Kotlin and Scala. We can even mix and match any combination of all these languages. The polyglot nature of Vert.x application allows you use the most appropriate language for the task.

Vert.x lets you implement distributed applications, either by using the built-in TCP and HTTP server and client, but also using the Vert.x event bus, a lightweight mechanism to send and receive messages. The Vert.x ecosystem also provides modules for AMQP, RabbitMQ, MQTT... More importantly, with Vert.x your are **back in charge**, no need to follow obscure framework rules. Vert.x is all about freedom!

Wao!, that’s a lot of information to process…​ However, you might still want to ask: What kind of applications can I use Vert.x for? We say, Vert.x is incredibly flexible - whether it’s simple network utilities, sophisticated modern web applications, HTTP/REST microservices, high volume event processing or a full blown backend message-bus application, Vert.x is a great fit. It’s fast, and does not constraint you. Last but not least, Vert.x provides appropriate tools to build reactive systems; systems that are: responsive, elastic, resilient and asynchronous!

