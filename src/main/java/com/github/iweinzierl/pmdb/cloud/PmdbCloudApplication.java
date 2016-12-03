package com.github.iweinzierl.pmdb.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication(scanBasePackages = {"com.github.iweinzierl.pmdb.cloud", "com.github.iweinzierl.springbootlogging"})
public class PmdbCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(PmdbCloudApplication.class, args);
    }
}
