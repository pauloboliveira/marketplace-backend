package com.dev.suacomanda.service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.Topic;
import com.dev.suacomanda.domain.aws.MessageDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SNSService {

    private final AmazonSNS snsClient;

    private final Topic catalogTopic;

    public SNSService(AmazonSNS snsClient, @Qualifier("catalogEventsTopic") Topic catalogTopic) {
        this.snsClient = snsClient;
        this.catalogTopic = catalogTopic;
    }


    public void sendMessage(MessageDTO messageData) {
        snsClient.publish(catalogTopic.getTopicArn(), messageData.message());
    }

}
