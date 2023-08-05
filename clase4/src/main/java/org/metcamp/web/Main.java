package org.metcamp.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.metcamp.web.entities.response.EventListResponse;
import org.metcamp.web.entities.response.EventResponse;
import org.metcamp.web.entities.response.Response;
import org.metcamp.web.repository.EventRepository;
import org.metcamp.web.service.EventService;
import org.metcamp.web.service.ValidationService;
import org.metcamp.web.utils.MapperUtils;

import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final MapperUtils mapperUtils = new MapperUtils();

    private static final EventRepository repository = new EventRepository(mapperUtils);
    private static final ValidationService validationService = new ValidationService();
    //private static final EventService eventService = new EventService();
    private static final EventService eventService = new EventService(mapperUtils, repository, validationService);




    private static final String WELCOME_MSG = "Bienvenidx al sistema de eventos. Qué acción deseas realizar?";
    public static final String OPTIONS = "\n1 -> Crear un evento" +
            "; 2 -> Conocer los eventos disponibles" +
            "; 3 -> Encontrar un evento" +
            "; 4 -> Modificar un evento" +
            "; 5 -> Borrar un evento" +
            "; 0 -> Salir";

    public static final String INVALID_OPTION_MSG = "La opción ingresada no es válida";
    public static final String GOOD_BYE_MSG = "------> Gracias por usar el sistema de eventos";


    public static void main(String[] args){

        System.out.println(WELCOME_MSG);
        int option = 1;


        while(option != 0){

            System.out.println(OPTIONS);
            option = scannerNextInt();
            Response response;

            switch (option){
                case 1:
                    System.out.println("------> Creando evento");
                    System.out.println("Ingrese los datos del evento: ");

                    response = eventService.createEvent(SCANNER.nextLine());
                    if(response.getCode() == 201){
                        System.out.println(response);
                        EventResponse eventResponse = (EventResponse) response;
                        //System.out.println(MAPPER.writeValueAsString(eventResponse.getEvent()));
                        System.out.println(mapperUtils.mapToJson(eventResponse.getEvent()));
                    } else{
                        System.out.println(response);
                    }
                    break;
                case 2:
                    //System.out.println("------> Lista de todos los eventos");

                    response = eventService.getAllEvents();
                    if(response.getCode() == 200){
                        //response --> EventListResponse
                        System.out.println(response);
                        EventListResponse eventListResponse = (EventListResponse) response;
                        System.out.println(mapperUtils.mapToJson(eventListResponse.getEvents()));
                    } else{
                        ////response --> Response
                        System.out.println(response);
                    }
                    break;
                case 3:
                    System.out.println("------> Ingrese el evento a buscar");

                    response = eventService.getEventById(scannerNextInt());
                    if(response.getCode() == 200){
                        System.out.println(response);
                        EventResponse eventResponse = (EventResponse) response;
                        //System.out.println(MAPPER.writeValueAsString(eventResponse.getEvent()));
                        System.out.println(mapperUtils.mapToJson(eventResponse.getEvent()));
                    } else{
                        System.out.println(response);
                    }
                    break;
                case 4:
                    System.out.println("------> Modificar un evento");
                    System.out.println("Ingrese el ID del evento a modificar");
                    int id = scannerNextInt();
                    System.out.println("Ingrese los datos a modificar");
                    String json = SCANNER.nextLine();

                    response = eventService.updateEvent(id,json);
                    if (response.getCode() == 200){
                        System.out.println(response);
                        EventResponse eventResponse = (EventResponse) response;
                        //System.out.println(MAPPER.writeValueAsString(eventResponse.getEvent()));
                        System.out.println(mapperUtils.mapToJson(eventResponse.getEvent()));
                    } else{
                        System.out.println(response);
                    }
                    break;
                case 5:
                    System.out.println("------> Borrar evento");
                    System.out.println("Ingrese el ID del evento a modificar");
                    response = eventService.deleteEvent(scannerNextInt());
                    System.out.println(response);
                    break;
                case 0:
                    System.out.println(GOOD_BYE_MSG);
                    break;
                default:
                    System.out.println(INVALID_OPTION_MSG);
            }
        }
    }

    public static int scannerNextInt() {
        try {
            return Integer.parseInt(SCANNER.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }


}