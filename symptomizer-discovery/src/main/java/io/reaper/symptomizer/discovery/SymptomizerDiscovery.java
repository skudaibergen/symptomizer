package io.reaper.symptomizer.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SymptomizerDiscovery {

    public static void main(String... args) {
        SpringApplication.run(SymptomizerDiscovery.class, args);
    }

}
