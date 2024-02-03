package com.dev.suacomanda.config.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Configuration
@Data
public class AWSSqsConfig {

    @Value("${aws.region}")
    private String region;

    @Value("${aws.sqs.queue.url}")
    private String queueURL;

    private final BasicAWSCredentials basicAWSCredentials;

    public AWSSqsConfig(BasicAWSCredentials basicAWSCredentials) {
        this.basicAWSCredentials = basicAWSCredentials;
    }

    @Bean
    public AmazonSQS amazonSQSBuilder() {
        return AmazonSQSClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials)).withRegion(region).build();
    }

    @Bean
    public ReceiveMessageResult consumeFactory() {
        AmazonSQSAsync asyncClient  = AmazonSQSAsyncClient.asyncBuilder().withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials)).withRegion(region).build();
        ReceiveMessageRequest messageRequest = new ReceiveMessageRequest(queueURL);
        Future<ReceiveMessageResult> future = asyncClient.receiveMessageAsync(messageRequest);
        try {
            ReceiveMessageResult result = future.get();
            System.out.println(result.getMessages());
            return result;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
