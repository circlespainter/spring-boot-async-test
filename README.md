Spring Boot Web Async Sample
============================

Includes synchronous controller methods, deferred dispatched to new threads and deferred dispatched to new [Quasar](https://github.com/puniverse/quasar) fibers.

Interestingly, under Tomcat without an exception handler the error behaviour of sync and async methods is different: in the latter case Spring doesn't use the default view rendering and the container error page is returned instead.

URLs:

* http://localhost:8080/myHello
* http://localhost:8080/myError
* http://localhost:8080/myHelloDeferredThread
* http://localhost:8080/myErrorDeferredThread
* http://localhost:8080/myHelloDeferredFiber
* http://localhost:8080/myErrorDeferredFiber
