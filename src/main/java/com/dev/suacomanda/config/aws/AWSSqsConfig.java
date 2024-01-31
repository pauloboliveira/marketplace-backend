package com.dev.suacomanda.config.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

}
