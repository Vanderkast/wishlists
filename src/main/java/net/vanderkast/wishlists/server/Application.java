package net.vanderkast.wishlists.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableConfigurationProperties
@EnableJpaRepositories
public class Application {
    public static void main(String[] args) {
        var application = new SpringApplication(Application.class);
        application.run(args);
    }
}
