package org.metcamp.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.metcamp.web.entities.model.Event;
import org.metcamp.web.entities.response.Response;
import org.metcamp.web.service.EventService;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    private static final EventService eventService = new EventService();

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


        while(option != 0){

            System.out.println(OPTIONS);
            option = Integer.parseInt(SCANNER.nextLine());


            switch (option){
                case 1:
                    System.out.println("Creando evento");
                    System.out.println("Ingrese los datos del evento: ");
                    eventService.createEvent(SCANNER.nextLine());
                    break;
                case 2:
                    System.out.println("Lista de eventos");
                    ArrayList<Event> events = eventService.getAllEvents();
                    for(Event e:events){
                        System.out.println(e.getAsJson());;
                    }
                    break;
                case 3:
                    System.out.println("Ingrese el evento a buscar");
                    //Event foundEvent = eventService.getEventById(Integer.parseInt(SCANNER.nextLine()));
                    //System.out.println(foundEvent.getAsJson());
                    //Event foundEvent = eventService.getEventById(scannerNextInt());

                    Response response = eventService.getEventById(Integer.parseInt(SCANNER.nextLine()));
                    if(response.getCode() == 200){
                        //imprimir el evento + respuesta
                        System.out.println(response.getE);
                    } else{
                        //imprimir respuesta
                        System.out.println(response);
                    }
                    break;
                case 4:
                    System.out.println("Modificar un evento");
                    System.out.println("--->Ingrese el ID del evento a modificar");
                    int id = Integer.parseInt(SCANNER.nextLine());
                    System.out.println("--->Ingrese los datos a modificar");
                    String json = SCANNER.nextLine();
                    eventService.updateEvent(id,json);
                    break;
                case 5:
                    System.out.println("Borrar evento");
                    eventService.deleteEvent(Integer.parseInt(SCANNER.nextLine()));
                    break;
                case 0:
                    System.out.println(GOOD_BYE_MSG);
                    break;
                default:
                    System.out.println(INVALID_OPTION_MSG);
            }
        }
    }



}