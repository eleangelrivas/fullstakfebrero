package com.elengel.api.fullstack.config.rabbit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitConfig {

    @Bean
    public Queue ventaQueue() {
        return new Queue("ventaQueue", false);  // Definir la cola de RabbitMQ
    }

    // Configuración del convertidor JSON
    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper) {
        System.out.println("Configurando el convertidor JSON...");
        objectMapper.registerModule(new JavaTimeModule());  // Registra el módulo para manejar LocalDateTime
        return new Jackson2JsonMessageConverter(objectMapper);  // Usamos Jackson para la conversión a JSON
    }

    // Configuración del RabbitTemplate
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        System.out.println("Configurando RabbitTemplate con convertidor...");
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);  // Asignar el convertidor JSON
        return rabbitTemplate;
    }
}
