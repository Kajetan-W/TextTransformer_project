package pl.put.poznan.transformer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the TextTransformer Spring Boot application.
 * Bootstraps the application context and scans all underlying packages.
 *
 * @author Kajetan Wojnicki
 * @version 1.0
 */
@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer"})
public class TextTransformerApplication {

    /**
     * Launches the Spring Boot application.
     *
     * @param args command-line arguments passed during startup
     */
    public static void main(String[] args) {

        SpringApplication.run(TextTransformerApplication.class, args);
    }
}
