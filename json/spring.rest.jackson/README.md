# REST example using @JsonView
> @JsonIgnore is not always the best to use in your REST service. Still @JsonIgnore is the first choise of most developers. This takes a lot of flexibility. As a solution developers come up with different solution making code more complicated then it should be.

## Description
> This project has two entities (User, UserAction) having a one2many relation. We use two REST controller to query data. We do not use @JsonIgnore to avoid endless cycle loop. Instead we are using @JsonView to explicitly define the granularity we want to display.

## URL's
http://localhost:8080/h2-console/ </br>
http://localhost:8080/api/kleenxcoder/user/generate/ </br>
http://localhost:8080/api/kleenxcoder/user/ </br>
http://localhost:8080/api/kleenxcoder/useraction/ </br>

# References
https://www.baeldung.com/building-a-restful-web-service-with-spring-and-java-based-configuration </br>

# JPQL Constructor Expressions
https://docs.oracle.com/html/E13946_05/ejb3_langref.html#ejb3_langref_constructor </br>
https://stackoverflow.com/questions/36328063/how-to-return-a-custom-object-from-a-spring-data-jpa-group-by-query </br>
