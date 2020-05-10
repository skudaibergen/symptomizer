package io.reaper.symptomizer.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients
@EnableEurekaClient
@ComponentScan("io.reaper.symptomizer")
@SpringBootApplication
public class SymptomizerCore {

    public static void main(String... args) {
        SpringApplication.run(SymptomizerCore.class, args);
    }

}
