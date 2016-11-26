package com.github.iweinzierl.pmdb.cloud;

import com.github.iweinzierl.pmdb.cloud.properties.ProjectProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
public class PmdbCloudApplication extends WebSecurityConfigurerAdapter {

    @Autowired
    ProjectProperties projectProperties;

    public static void main(String[] args) {
        SpringApplication.run(PmdbCloudApplication.class, args);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }
}
