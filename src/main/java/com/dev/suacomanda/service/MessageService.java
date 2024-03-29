package com.dev.suacomanda.service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import com.amazonaws.services.sqs.AmazonSQS;
import com.dev.suacomanda.domain.aws.MessageDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final AmazonSNS snsClient;

    private final Topic catalogTopic;

    public MessageService(AmazonSNS snsClient, @Qualifier("catalogEventsTopic") Topic catalogTopic) {
        this.snsClient = snsClient;
        this.catalogTopic = catalogTopic;
    }


    public void sendMessage(MessageDTO messageData) {
        snsClient.publish(catalogTopic.getTopicArn(), messageData.message());
    }
}
