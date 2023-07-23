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

    //Metodo constructor
    //Cuando creamos el obj, se crea la lista y se la asigna a la variable
    public EventService(){
        this.events = new ArrayList<>();
    }


    //Encontrar el evento por id
    private Optional<Event> findEventById (int id){
        Optional<Event> result = Optional.empty();
        for (Event e:events){
            if(e.getId() == id){
                result = Optional.of(e);
            }
        }
        return result;
    }


    //Funciones de los eventos
    public void createEvent(String json) throws JsonProcessingException {
        Event event = MAPPER.readValue(json, Event.class);
        events.add(event);
    }


    public ArrayList<Event> getAllEvents() {
        return events;
    }


    public Response getEventById(int id){
        Optional<Event> foundEvent = findEventById(id);

        return foundEvent.isPresent()
                ? new EventResponse(200,"OK",foundEvent.get())
                : new Response(404, "Event Not Found");

        /*if(foundEvent.isPresent()){
            return foundEvent.get();
        } else {
            return ...
        }
        return foundEvent.isPresent() ? foundEvent.get() : null;
        */
        /*
        Event foundEvent = new Event();
        for (Event e: events){
            if(e.getId() == id){
                foundEvent = e;
            }
        }
        return foundEvent;
        */
    }


    public void updateEvent(int id, String json) throws JsonProcessingException {
        Event newEventData = MAPPER.readValue(json, Event.class);
        for (Event e: events){
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
        //Otra forma
        //events.removeIf(e -> e.getId() == id);
    }
}
