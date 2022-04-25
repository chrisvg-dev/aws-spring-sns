package com.cvg.springboot.aws.sns.services;

import com.cvg.springboot.aws.sns.config.AwsProperties;
import com.cvg.springboot.aws.sns.dto.Message;
import com.cvg.springboot.aws.sns.dto.SubscriptorProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.exception.SdkException;
import software.amazon.awssdk.core.exception.SdkServiceException;
import software.amazon.awssdk.http.SdkHttpResponse;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.*;

@Service
public class MessagePublisherImpl implements MessagePublisher {
    private final static Logger LOG = LoggerFactory.getLogger(MessagePublisherImpl.class);

    @Autowired
    private SnsClient snsClient;
    @Autowired
    private AwsProperties awsProperties;

    @Override
    public SnsResponse publish(Message message) {
        SnsResponse response = null;
        Message body = Message.builder().title( message.getTitle() ).content( message.getContent() ).build();
        try {
            PublishRequest request = PublishRequest.builder()
                            .topicArn( awsProperties.getTopicArn() )
                                    .message( body.getContent() )
                                            .subject(body.getTitle())
                                                    .build();

            PublishResponse publishResponse = snsClient.publish(request);
            LOG.info("Publish response: {}", publishResponse);

            SdkHttpResponse httpResponse = publishResponse.sdkHttpResponse();
            /**response = new SnsResponse(
                    httpResponse.statusCode(),
                    httpResponse.statusText().orElse(null),
                    publishResponse.messageId()) {
            };*/
            LOG.info("Response details: {}", httpResponse.statusText().orElse(null));
        } catch (SnsException e) {
            printError(e.statusCode(), e.getClass().getSimpleName() + ": " + e.awsErrorDetails());
        } catch (SdkServiceException e) {
            printError(e.statusCode(), e.getClass().getSimpleName() + ": " + e.getMessage());
        } catch (SdkClientException e) {
            printError(null, e.getClass().getSimpleName() + ": " + e.getMessage());
        } catch (SdkException e) {
            printError(null, e.getClass().getSimpleName() + ": " + e.getMessage());
        }
        return response;
    }

    @Override
    public String addSubscriptor(SubscriptorProtocol subscriptor) {
        SubscribeRequest request =  SubscribeRequest.builder()
                .topicArn(awsProperties.getTopicArn())
                .protocol( subscriptor.getProtocol().toString().toLowerCase() )
                .endpoint( subscriptor.getEndpoint() )
                .build();
        snsClient.subscribe(request);
        return "Subscripción exitosa. Para poder usar su cuenta por favor confirme su email (" +
                subscriptor.getEndpoint() + ") a través del correo electrónico que le fue enviado...";
    }

    private void printError(Integer statusCode, String detailedMessage) {
        throw new RuntimeException(
                "Error => StatusCode: " + statusCode + ", detailedMessage: " + detailedMessage
        );
    }
}
