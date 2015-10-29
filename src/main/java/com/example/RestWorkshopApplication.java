package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestWorkshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestWorkshopApplication.class, args);
    }
    
    /*
     * to configure the embedded Tomcat runtime environment, 
     * e.g. adjusting the max number of threads in the request thread pool. 
     */
    @Bean
    public EmbeddedServletContainerCustomizer embeddedServletCustomizer() {
      return new MyEmbeddedServletContainerCustomizer();
    }
}
