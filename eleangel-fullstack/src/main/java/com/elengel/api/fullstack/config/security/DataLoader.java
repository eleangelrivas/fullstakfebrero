package com.elengel.api.fullstack.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        Resource resource = new ClassPathResource("import.sql");  // Asegúrate de que el archivo esté en /resources

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            // Lee el archivo SQL y ejecuta las sentencias
            String script = new String(resource.getInputStream().readAllBytes());
            for (String sql : script.split(";")) {
                sql = sql.trim();
                if (!sql.isEmpty()) {
                    try {
                        statement.execute(sql);
                    } catch (Exception e) {
                        // Aquí se captura el error y se sigue con la siguiente sentencia
                        System.err.println("Error al ejecutar la sentencia: " + sql);
                        e.printStackTrace();  // Imprimir el error para debugging
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error de conexión o al procesar el script");
            e.printStackTrace();
        }
    }
}
