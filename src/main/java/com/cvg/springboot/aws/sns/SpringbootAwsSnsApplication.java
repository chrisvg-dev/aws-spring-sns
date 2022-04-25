package com.cvg.springboot.aws.sns;

import com.cvg.springboot.aws.sns.config.SnsApplicationListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootAwsSnsApplication {


    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringbootAwsSnsApplication.class);
        app.addListeners(new SnsApplicationListener());
        app.run(args);
    }
}
