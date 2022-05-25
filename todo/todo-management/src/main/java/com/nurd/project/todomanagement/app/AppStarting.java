package com.nurd.project.todomanagement.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoTemplate;

public class AppStarting {
    @Autowired
    private MongoTemplate mongoTemplate;

    @EventListener(ApplicationStartedEvent.class)
    public void doSomethingAfterStartup() {
        mongoTemplate.createCollection("ToDo");
    }
}
