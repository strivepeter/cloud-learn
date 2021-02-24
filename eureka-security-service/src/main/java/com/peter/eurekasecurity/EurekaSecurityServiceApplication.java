package com.peter.eurekasecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Peter
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaSecurityServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaSecurityServiceApplication.class, args);
    }

}
