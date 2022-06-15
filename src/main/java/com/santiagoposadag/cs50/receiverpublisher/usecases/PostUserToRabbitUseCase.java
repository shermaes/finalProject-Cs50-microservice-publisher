package com.santiagoposadag.cs50.receiverpublisher.usecases;


import com.google.gson.Gson;
import com.santiagoposadag.cs50.receiverpublisher.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class PostUserToRabbitUseCase implements Function<UserDto, Mono<UserDto>> {

    private RabbitTemplate rabbitTemplate;
    private Gson gson = new Gson();

    public PostUserToRabbitUseCase(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    
    @Override
    public Mono<UserDto> apply(@Validated UserDto userDto) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, userDto.getRoutingKey(), gson.toJson(userDto));
        return Mono.just(userDto);
    }
}
