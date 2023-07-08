package org.metcamp.web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.metcamp.web.entities.model.Event;
import org.metcamp.web.entities.response.EventResponse;
import org.metcamp.web.entities.response.Response;

import java.util.ArrayList;
import java.util.Optional;

public class EventService {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private ArrayList<Event> events;

    public EventService(){
        this.events = new ArrayList<>();
    }

    private Optional<Event> findEventById (int id){
        Optional<Event> result = Optional.empty();
        for (Event e:events){
            if(e.getId() == id){
                result = Optional.of(e);
            }
        }
        return result;
    }

    public void createEvent(String json) throws JsonProcessingException {

        Event event = MAPPER.readValue(json, Event.class);
        events.add(event);
    }

    public ArrayList<Event> getAllEvents() {
        return events;
    }

    public Event getEventById(int id){
        Optional<Event> foundEvent = findEventById(id);

        /*if(foundEvent.isPresent()){
            return foundEvent.get();
        } else {
            return ...
        }*/

        return foundEvent.isPresent()
                ? new EventResponse(200,"OK",foundEvent.get())
                : new Response(404, "Event Not Found");
    }

    public void updateEvent(int id, String json) throws JsonProcessingException {
        Event newEventData = MAPPER.readValue(json, Event.class);
        for (Event e:events){
            if(e.getId() == id){
                //Cambiar datos de e con info de newEventData
                e.update(newEventData);
            }
        }
    }

    public void deleteEvent(int id) {
        for (Event e:events){
            if(e.getId() == id){
                events.remove(e);
            }
        }

        //events.removeIf(e -> e.getId() == id);
    }
}
