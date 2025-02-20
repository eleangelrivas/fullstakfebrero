package com.elengel.api.fullstack.config.rabbit;

import com.elengel.api.fullstack.dto.VentaDTO;
import com.elengel.api.fullstack.persistence.entity.Venta;
import com.elengel.api.fullstack.service.VentaService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VentaConsumer {

    @Autowired
    private VentaService ventaService;

    @RabbitListener(queues = "ventaQueue")
    public void receiveVenta(VentaDTO ventaDTO) {
        try {
            System.out.println("Recibiendo venta desde la cola: " + ventaDTO);

            // Asegurarnos de que el mensaje no esté nulo
            if (ventaDTO == null) {
                System.err.println("El mensaje recibido es nulo.");
                return;
            }

            // Almacenar la venta usando el servicio
            Venta ventaGuardada = ventaService.guardarVenta(ventaDTO);
            System.out.println("Venta almacenada con ID: " + ventaGuardada.getId());
        } catch (Exception e) {
            System.err.println("Error al procesar la venta desde la cola: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
