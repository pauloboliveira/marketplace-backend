package com.dev.suacomanda.config.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.Subscription;
import com.amazonaws.services.sns.model.Topic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSSnsConfig {

    @Value("${aws.region}")
    private String region;

    @Value("${aws.sns.topic.catalog.arn}")
    private String snsCatalogArn;

    private final BasicAWSCredentials basicAWSCredentials;

    public AWSSnsConfig(BasicAWSCredentials basicAWSCredentials) {
        this.basicAWSCredentials = basicAWSCredentials;
    }

    @Bean
    public AmazonSNS amazonSNSBuilder() {
        return AmazonSNSClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials)).withRegion(region).build();
    }

    @Bean(name = "catalogEventsTopic")
    public Topic awsCatalogTopicBuilder() {
        return new Topic().withTopicArn(snsCatalogArn);
    }

}
