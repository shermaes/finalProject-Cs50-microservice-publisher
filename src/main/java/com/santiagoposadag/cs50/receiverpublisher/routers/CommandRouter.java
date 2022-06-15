package com.santiagoposadag.cs50.receiverpublisher.routers;


import com.santiagoposadag.cs50.receiverpublisher.dto.CryptoCurrencyDto;
import com.santiagoposadag.cs50.receiverpublisher.usecases.PostMessageToRabbitUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
public class CommandRouter {


    @Bean
    public RouterFunction<ServerResponse> postActionRoute(PostMessageToRabbitUseCase postMessageToRabbit){
        Function<CryptoCurrencyDto, Mono<ServerResponse>> executor =
                cryptoCurrencyDto -> postMessageToRabbit.apply(cryptoCurrencyDto)
                        .flatMap(result -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(result));

        return route(POST("/SendAction")
                .and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(CryptoCurrencyDto.class).flatMap(executor));

    }
}
