package org.example;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
@ComponentScan(basePackages = {"org.example"})
@EnableAutoConfiguration
@EnableJpaRepositories
public class Main {
    public static void main(String[] args) throws SQLException {
        SpringApplication.run(Main.class, args);

        try (Connection conn = DriverManager.getConnection(
                "jdbc:h2:mem:RECEIPTS;INIT=RUNSCRIPT FROM 'classpath:schema.sql';DB_CLOSE_DELAY=0",
                "sa", "")) {

        }catch (Exception e) {
            System.err.println("Database initialization failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

}

