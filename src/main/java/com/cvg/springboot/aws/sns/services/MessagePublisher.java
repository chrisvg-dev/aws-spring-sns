package com.cvg.springboot.aws.sns.services;

import com.cvg.springboot.aws.sns.dto.Message;
import com.cvg.springboot.aws.sns.dto.SubscriptorProtocol;
import software.amazon.awssdk.services.sns.model.SnsResponse;

public interface MessagePublisher {
    SnsResponse publish(Message message);
    String addSubscriptor(SubscriptorProtocol subscriptor);
}