package com.dev.suacomanda.config.aws;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.services.sqs.AmazonSQS;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.JMSException;

@Configuration
public class JMSConnectionConfig {


    private final AmazonSQS sqsClient;

    public JMSConnectionConfig(AmazonSQS sqsClient) {
        this.sqsClient = sqsClient;
    }

    @Bean
    public SQSConnection connectionFatory() throws JMSException {
        SQSConnectionFactory connectionFactory = new SQSConnectionFactory(
                new ProviderConfiguration(),
                sqsClient
        );

        return connectionFactory.createConnection();
    }


}
