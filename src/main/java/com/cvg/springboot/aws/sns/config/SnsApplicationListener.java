package com.cvg.springboot.aws.sns.config;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;

public class SnsApplicationListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        System.setProperty("aws.accessKeyId", "TU_ACCESS_KEY_ID");
        System.setProperty("aws.secretAccessKey", "TU_SECRET_KEY");
    }
}
