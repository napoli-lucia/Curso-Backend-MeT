package org.metcamp.web.entities.response;

import org.metcamp.web.entities.model.Event;

public class EventResponse extends Response{

    private Event event;


    public EventResponse(int code, String message, Event event) {
        super(code, message);
        this.event = event;
    }
}
