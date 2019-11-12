package com.nagp.assgnmnt.mservices;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * The application is a Spring Cloud Config Server, which provides an HTTP resource-based API for accessing
 * configuration (name-value pairs or equivalent YAML content) maintained as a Git repository.
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServer
{
    @Value("${spring.cloud.config.server.git.password}")
    private static String defaultDb;

    public static void main(String[] args)
    {
        SpringApplication.run(ConfigServer.class, args);
    }
}
