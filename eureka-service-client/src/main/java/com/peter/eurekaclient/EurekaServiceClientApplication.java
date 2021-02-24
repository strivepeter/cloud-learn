package com.peter.eurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class EurekaServiceClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServiceClientApplication.class, args);
    }

}
