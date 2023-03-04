package com.lti.service;

import com.azure.storage.queue.QueueClient;
import com.azure.storage.queue.QueueClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class QueueService {

    @Value("${azure.storage.storageconnectionstring}")
    private String storageconnectionstring;

    @Value("${azure.storage.queuename}")
    private String queuename;

    public void insertMessage(String orderId){

        QueueClient queueClient=new QueueClientBuilder()
                .connectionString(storageconnectionstring)
                .queueName(queuename)
                .buildClient();

        queueClient.sendMessage(orderId);
    }
}
