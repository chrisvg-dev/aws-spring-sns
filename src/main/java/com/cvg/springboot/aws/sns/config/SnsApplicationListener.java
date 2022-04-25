package com.cvg.springboot.aws.sns.config;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;

public class SnsApplicationListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        System.setProperty("aws.accessKeyId", "");
        System.setProperty("aws.secretAccessKey", "");
    }
}
