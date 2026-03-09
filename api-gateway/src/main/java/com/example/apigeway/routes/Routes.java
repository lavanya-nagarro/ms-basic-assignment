package com.example.apigeway.routes;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;

@Configuration
public class Routes {

   /* @Bean
    public RouterFunction<ServerResponse> customerServiceRoute() {
        return route("customer-service")
                .route(RequestPredicates.path("/api/customers/**"),
                        HandlerFunctions.http()).before(uri("lb://CUSTOMER-SERVICE")).build();

    }

    @Bean
    public  RouterFunction<ServerResponse> accountServiceRoute(){
         return route("account-service")
                .route(RequestPredicates.path("/api/account/**"),HandlerFunctions.http())
                .before(uri("lb://ACCOUNT-SERVICE"))
                .build();
    }*/
}
