package com.dev.suacomanda.config.aws;

import com.amazonaws.auth.BasicAWSCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSBasicCredentialsConfig {

    @Value("${aws.accessKeyId}")
    private String accessKeyId;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Bean
    public BasicAWSCredentials credentials() {
        return new BasicAWSCredentials(accessKeyId, secretKey);
    }
}
