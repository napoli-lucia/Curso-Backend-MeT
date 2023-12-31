package org.metcamp.web.entities.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Event {

    //Todos los atributos van a ser privados
    private int id;
    private EventType eventType;
    private String name;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "start_date")
    private LocalDateTime startDateTime;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "end_date")
    private LocalDateTime endDateTime;

    private int attendees;
    private String organizer;
    private List<Price> prices;

    public void update(Event newEventData){
        this.eventType = newEventData.getEventType();
        this.name = newEventData.getName();
        this.startDateTime = newEventData.getStartDateTime();
        this.endDateTime = newEventData.getEndDateTime();
        this.attendees = newEventData.getAttendees();
        this.organizer = newEventData.getOrganizer();
        this.prices = newEventData.getPrices();
        //Lo unico que no cambia es el id
    }

    /*
    public String getAsJson() throws JsonProcessingException {
        //MAPPER.writeValueAsString(newEvent)
        //private static final ObjectMapper MAPPER = new ObjectMapper();
        //Yo quiero que se imprima a si mismo => this
        return new ObjectMapper().writeValueAsString(this);

    }*/

}
