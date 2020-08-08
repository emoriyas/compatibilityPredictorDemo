package dh.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import dh.demo.service.AverageCompatibilityPredictor;
import dh.demo.service.CompatibilityPredictor;

/**
 * Service that listens for a POST request with a JSON object including an array of team members and
 * applicants and returns an array of scored applicants. Only looks at four attributes, intelligence,
 * strength, endurance, and spicy food tolerance.
 */
@SpringBootApplication
public class DemoApplication {

    @Bean
    public CompatibilityPredictor compatibilityPredictorBean() {
        return new AverageCompatibilityPredictor();
    }

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
