package se.daga;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RunApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(RunApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RunApplication.class, args);

    }
}
