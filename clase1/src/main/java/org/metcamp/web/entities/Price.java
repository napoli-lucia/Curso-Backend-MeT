package org.metcamp.web.entities;

//En java hay una clase currency que se puede importar, pero aca creamos nuestra propia clase enum

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Price {

    private TicketType type;
    private Currency currency;
    private double value;
}
