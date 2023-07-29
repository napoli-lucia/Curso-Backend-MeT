package org.metcamp.web.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.metcamp.web.entities.model.Event;
import org.metcamp.web.exceptions.ConvertionException;

import java.util.ArrayList;


public class MapperUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public Event mapToEvent(String input){
        //return MAPPER.readValue(input, Event.class);

        try {
            return MAPPER.readValue(input, Event.class);
        } catch (JsonProcessingException e) {
            throw new ConvertionException(e);
        }
    }

    public ArrayList<Event> mapToEventList(String input){
        //TypeReference<ArrayList<Event>> typeRef = new TypeReference<>() {};
        //return MAPPER.readValue(input, typeRef);

        try {
            TypeReference<ArrayList<Event>> typeRef = new TypeReference<>() {};
            return MAPPER.readValue(input, typeRef);
        } catch (JsonProcessingException e) {
            throw new ConvertionException(e);
        }
    }

    //Estos dos me devuelven un String
    //Lo que estan haciendo es serializar
    //Dos metodos que se llaman igual y el tipo de retorno es igual
    //pero recibe distintos parametros

    public String mapToJson(Event event) {
        //return MAPPER.writeValueAsString(event);

        try {
            return MAPPER.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new ConvertionException(e);
        }
    }

    public String mapToJson(ArrayList<Event> eventList){
        //return MAPPER.writeValueAsString(eventList);

        try {
            return MAPPER.writeValueAsString(eventList);
        } catch (JsonProcessingException e) {
            throw new ConvertionException(e);
        }
    }
}
