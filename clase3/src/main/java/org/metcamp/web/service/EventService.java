package org.metcamp.web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.metcamp.web.entities.model.Event;
import org.metcamp.web.entities.response.EventListResponse;
import org.metcamp.web.entities.response.EventResponse;
import org.metcamp.web.entities.response.Response;
import org.metcamp.web.exceptions.ConvertionException;
import org.metcamp.web.repository.EventRepository;
import org.metcamp.web.utils.MapperUtils;

import java.util.ArrayList;
import java.util.Optional;

public class EventService {

    //private static final ObjectMapper MAPPER = new ObjectMapper();
    private final MapperUtils mapperUtils = new MapperUtils();

    private final EventRepository repository = new EventRepository();

    //Metodo constructor
    /*public EventService(){
        this.events = new ArrayList<>();
    }*/


    //Encontrar el evento por id
    /* Ya no lohago aca, sino que en EventRepository
    private Optional<Event> findEventById (int id){
        Optional<Event> result = Optional.empty();
        for (Event e:events){
            if(e.getId() == id){
                result = Optional.of(e);
            }
        }
        return result;
    }*/


    //Funciones de los eventos
    public Response createEvent(String json) {
        try {
            //Event event = MAPPER.readValue(json, Event.class);
            Event event = mapperUtils.mapToEvent(json);

            //Validar si ya existe un evento con mismo id
            //Optional<Event> foundEvent = findEventById(event.getId());
            Optional<Event> foundEvent = repository.find(event.getId());
            if (foundEvent.isPresent()) {
                //Devolver error
                return new Response(400, "Event already exists");
            } else {
                //Agregar evento a la lista
                //events.add(event);
                repository.add(event);
                return new EventResponse(201, "Event Created", event);
            }
        } catch (ConvertionException e){
            return new Response(400, "Malformed event");
        }
    }


    public Response getAllEvents() {
        ArrayList<Event> temporalList = repository.getEvents();
        //return events;
        //return events.isEmpty()
        return temporalList.isEmpty()
                ? new Response(204,"Event list empty")
                : new EventListResponse(200,"OK",temporalList);
    }


    public Response getEventById(int id){
        //Optional<Event> foundEvent = findEventById(id);
        Optional<Event> foundEvent = repository.find(id);
        return foundEvent.isPresent()
                ? new EventResponse(200,"OK",foundEvent.get())
                : new Response(404, "Event Not Found");
    }


    public Response updateEvent(int id, String json) {
        //Optional<Event> foundEvent = findEventById(id);
        Optional<Event> foundEvent = repository.find(id);
        if (foundEvent.isPresent()){
            /*ANTES DE REPOSITORY
            //Extraer datos del evento encontrado
            Event event = foundEvent.get();
            //Removerlo de la lista
            events.remove(event);
            //Mapear los nuevos datos
            //Event newEventData = MAPPER.readValue(json, Event.class);
            Event newEventData = mapperUtils.mapToEvent(json);
            //Actualizar el evento encontrado con los nuevos datos
            event.update(newEventData);
            //Volver a agregar el evento a la lista
            events.add(event);*/

            Event newEventData = mapperUtils.mapToEvent(json);
            repository.update(id,newEventData);
            return new EventResponse(200,"Event updated", newEventData);
        }else {
            //Devolver error
            return new Response(404,"Event Not Found");
        }
    }


    public Response deleteEvent(int id) {
        //Optional<Event> foundEvent = findEventById(id);
        Optional<Event> foundEvent = repository.find(id);
        if (foundEvent.isPresent()){
            //events.remove(foundEvent.get());
            repository.delete(foundEvent.get().getId());
            return new Response(204,"Event Deleted");
        } else{
            return new Response(404,"Event Not Found");
        }
    }
}
