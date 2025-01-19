package com.example.pathforge;

import com.example.pathforge.run.Run;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import static com.example.pathforge.run.Location.INDOOR;
import static java.time.temporal.ChronoUnit.HOURS;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.pathforge.repo")
@EntityScan(basePackages = "com.example.pathforge.model")
public class PathForgeApplication {

    private static final Logger log = Logger.getLogger(PathForgeApplication.class.getName());

    public static void main(String[] args) {
      SpringApplication.run(PathForgeApplication.class, args);
      log.info("Application Started Successfully");
    }

    @Bean
    CommandLineRunner runner(){
        return args -> {
            Run run = new Run(2,"First Run", LocalDateTime.now(),LocalDateTime.now().plus(5,HOURS),5,INDOOR);
            log.info(run.toString());
        };
    }
}


