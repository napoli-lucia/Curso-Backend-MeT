package org.metcamp.web.service;

import org.metcamp.web.entities.model.Event;
import org.metcamp.web.exceptions.ValidationException;

import java.time.LocalDateTime;

public class ValidationService {

    public void validateCreateEvent(Event event){
        validateId(event.getId());
        validateName(event.getName());
        //validateDates(event.getStartDateTime(), event.getEndDateTime());
    }

    public void validateName (String name) {
        //Validar si el nombre no esta vacio y que tenga al menos 5 caracteres
        if (name.isEmpty()){
            throw new ValidationException("name is required");
        }
        if (name.length() < 5){
            throw new ValidationException("name is too short");
        }
    }

    public void validateId(int id){
        if (id==0){
            throw new ValidationException("id must not be zero");
        } else if (id < 0){
            throw new ValidationException("id must be positive");
        }
    }

    public void validateDates(LocalDateTime startDate, LocalDateTime endDate){
        //Verificar que start sea antes que end
        if (startDate.isAfter(endDate)){
            throw new ValidationException("startDate must be before endDate");
        }
    }

    public boolean validateAttendeess(int cantidad){
        return cantidad > 0;
        /*
        if (cantidad > 0){
            return true;
        }
        return false;
         */
    }
}
