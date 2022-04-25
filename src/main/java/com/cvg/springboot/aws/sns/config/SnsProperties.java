package com.cvg.springboot.aws.sns.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class SnsProperties {

    @Autowired
    private AwsProperties awsProperties;

    @Bean
    public SnsClient getSnsClient() {
        return SnsClient.builder()
                .region( Region.of(awsProperties.getRegion()) )
                .build();
    }

}
