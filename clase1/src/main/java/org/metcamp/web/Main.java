package org.metcamp.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.metcamp.web.entities.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final String WELCOME_MSG = "Bienvenidx al sistema de eventos. Qué acción deseas realizar?";
    public static final String OPTIONS = "\n1 -> Crear un evento" +
            "; 2 -> Conocer los eventos disponibles" +
            "; 3 -> Encontrar un evento" +
            "; 4 -> Modificar un evento" +
            "; 5 -> Borrar un evento" +
            "; 0 -> Salir";

    public static final String INVALID_OPTION_MSG = "La opción ingresada no es válida";
    public static final String GOOD_BYE_MSG = "------> Gracias por usar el sistema de eventos";


    public static void main(String[] args) throws JsonProcessingException {

        System.out.println(WELCOME_MSG);
        int option = 1;

        /*
        Event event = new Event();
        event.setId(1);
        event.setName("Clase 1 MetCamp Web");
        event.setEventType(EventType.CLASE_METCAMP);
        event.setStartDateTime(LocalDateTime.of(2023,7,1,10,0,0));
         */


        while(option != 0){

            System.out.println(OPTIONS);
            option = Integer.parseInt(SCANNER.nextLine());


            switch (option){
                case 1:
                    System.out.println("Creando evento");
                    System.out.println("Ingrese los datos del evento: ");
                    String input = SCANNER.nextLine();
                    createEvent(input);
                    break;
                case 2:
                    System.out.println("Lista de eventos");
                    getAllEvents();
                    break;
                case 3:
                    System.out.println("Ingrese el evento a buscar");
                    getEventById(Integer.parseInt(SCANNER.nextLine()));
                    break;
                case 4:
                    System.out.println("Modificar un evento");
                    updateEvent(Integer.parseInt(SCANNER.nextLine()));
                    break;
                case 5:
                    System.out.println("Borrar evento");
                    deleteEvent(Integer.parseInt(SCANNER.nextLine()));
                    break;
                case 0:
                    System.out.println(GOOD_BYE_MSG);
                    break;
                default:
                    System.out.println(INVALID_OPTION_MSG);
            }
        }
    }

    public static void createEvent(String input) throws JsonProcessingException {
        //Deserializamos
        //Lo guardamos en var de tipo evento
        Event newEvent = MAPPER.readValue(input, Event.class);

        newEvent.setAttendees(10);
        newEvent.setOrganizer("Met");

        //Serializamos
        //System.out.println("event = "+ MAPPER.writeValueAsString(newEvent));
        System.out.println("event = "+ newEvent.printJson());
    }

    public static void getAllEvents() throws JsonProcessingException {
        List<Event> events = List.of(
                new Event(1, EventType.ANIVERSARIO, "Aniversario MeT",
                        LocalDateTime.of(2023, 7, 1, 0, 0, 0),
                        LocalDateTime.of(2023, 7, 31, 23, 59, 59),
                        2000, "MeT",null),
                new Event(2, EventType.CLASE_METCAMP, "Clase 1",
                        LocalDateTime.of(2023, 7, 1, 10, 0, 0),
                        LocalDateTime.of(2023, 7, 1, 14, 0, 0),
                        200, "MetCamp Web",List.of(new Price(TicketType.REGULAR_FULL_PASS, Currency.ARS, 2500)))
        );
        for (Event e: events) {
            System.out.println(e.printJson());
        }
    }

    public static void getEventById(int id) throws JsonProcessingException {
        Event event = new Event();
        event.setId(1);
        event.setName("Clase 1 MetCamp Web");
        event.setEventType(EventType.CLASE_METCAMP);
        event.setStartDateTime(LocalDateTime.of(2023,7,1,10,0,0));

        //System.out.println("event = "+ MAPPER.writeValueAsString(event));
        System.out.println("event = "+ event.printJson());
    }

    public static void updateEvent(int id) {
        if (id == 20){
            System.out.println("Modificando evento " + id);
        } else {
            System.out.println("El evento solicitado no existe");
        }
    }

    public static void deleteEvent(int id) {
        if (id == 30){
            System.out.println("Borrando evento " + id);
        } else {
            System.out.println("El evento solicitado no existe");
        }
    }

}