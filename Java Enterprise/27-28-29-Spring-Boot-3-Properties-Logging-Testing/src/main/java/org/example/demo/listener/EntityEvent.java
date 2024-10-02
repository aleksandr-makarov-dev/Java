package org.example.demo.listener;

import org.springframework.context.ApplicationEvent;

public class EntityEvent extends ApplicationEvent {
    private AccessType accessType;

    public EntityEvent(Object source, AccessType accessType) {
        super(source);
    }

    public AccessType getAccessType(){
        return  accessType;
    }
}
