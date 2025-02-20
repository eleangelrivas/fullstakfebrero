package com.elengel.api.fullstack.config.rabbit;

import com.elengel.api.fullstack.dto.VentaDTO;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VentaProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue ventaQueue;

    public void sendVentaToQueue(VentaDTO ventaDTO) {
        try {
            // Log para ver el contenido del mensaje
            System.out.println("Enviando venta a la cola: " + ventaDTO);
            // Enviar el DTO a la cola
            rabbitTemplate.convertAndSend(ventaQueue.getName(), ventaDTO);
            System.out.println("Venta enviada a la cola: " + ventaDTO.getId());
        } catch (Exception e) {
            System.err.println("Error al enviar la venta a la cola: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
