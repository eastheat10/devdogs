package net.skhu.devdogs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DevdogsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevdogsApplication.class, args);
    }

}
