package org.metcamp.web.service;

import org.junit.jupiter.api.*;
import org.metcamp.web.exceptions.ValidationException;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ValidationServiceTest {
    //given - dado    --> configurar datos
    //when - cuando   --> ejecutar el metodo
    //then - entonces --> validar el resultado

    private static final ValidationService service = new ValidationService();

    //**Tests id**//
    @Test
    @DisplayName("Probando el metodo validateId con 0")
    void validateIdTestWithIdZero(){
        int id = 0;
        //assertThrows(ValidationException.class, () -> service.validateId(id));

        //Asi valido dos cosas
        //Que se lance el tipo de excepcion adecuado
        //Y que el mensaje sea el correcto
        ValidationException e = assertThrows(ValidationException.class, () -> service.validateId(id));
        assertEquals("id must not be zero", e.getMessage());
    }

    @Test
    @DisplayName("Probando el metodo validateId con -1")
    void validateIdTestWithIdNegative(){
        int id = -1;
        assertThrows(ValidationException.class, () -> service.validateId(id));
    }

    @Test
    @DisplayName("Probando el metodo validateId con 1 - Happy path")
    void validateIdTestWithIdNotZero(){
        int id = 1;
        assertDoesNotThrow(() -> service.validateId(id));
    }

    //**Tests attendeess**//
    @Test
    @DisplayName("Probando el metodo validateAttendeess con 1 - Happy path")
    void validateAttendeessTestWithCantPositiva(){
        //given
        int cantidad = 1;
        //when
        boolean resultado = service.validateAttendeess(cantidad);
        //then
        assertTrue(resultado);
    }

    @Test
    @DisplayName("Probando el metodo validateAttendeess con -1")
    void validateAttendeessTestWithCantNegativa(){
        //given
        int cantidad = -1;
        //when
        boolean resultado = service.validateAttendeess(cantidad);
        //then
        assertFalse(resultado);
    }

    @Test
    @DisplayName("Probando el metodo validateAttendeess con 0")
    void validateAttendeessTestWithCantZero(){
        //given
        int cantidad = 0;
        //when
        boolean resultado = service.validateAttendeess(cantidad);
        //then
        assertFalse(resultado);
    }

    //**Tests names**//
    @Test
    @DisplayName("Probando el metodo validateNames - Happy path")
    void validateNameTestWithNombreCorrecto(){
        //given
        String name = "Presentacion";
        //when
        //ValidationException e = assertThrows(ValidationException.class, () -> service.validateName(name));
        //then
        assertDoesNotThrow(() -> service.validateName(name));

        //???????????
    }

    @Test
    @DisplayName("Probando el metodo validateNames sin nombre")
    void validateNameTestWithNoName(){
        //given
        String name = "";
        //when
        ValidationException e = assertThrows(ValidationException.class, () -> service.validateName(name));
        //then
        assertEquals("name is required", e.getMessage());
    }

    @Test
    @DisplayName("Probando el metodo validateNames nombre corto")
    void validateNameTestWithShortName(){
        //given
        String name = "abc";
        //when
        ValidationException e = assertThrows(ValidationException.class, () -> service.validateName(name));
        //then
        assertEquals("name is too short", e.getMessage());
    }

    //**Tests dates**//
    @Test
    @DisplayName("Probando el metodo validateDates con start despues que end")
    void validateDatesTestWithstartDateAfterendDate(){
        //given
        LocalDateTime startDate = LocalDateTime.of(2023,07,02,00,00,00);
        LocalDateTime endDate = LocalDateTime.of(2023,07,01,00,00,00);
        //when
        ValidationException e = assertThrows(ValidationException.class,
                () -> service.validateDates(startDate,endDate));
        //then
        assertEquals("startDate must be before endDate", e.getMessage());
    }

    @Test
    @DisplayName("Probando el metodo validateDates - Happy path")
    void validateDatesTestWithCorrectDate(){
        //given
        LocalDateTime startDate = LocalDateTime.of(2023,07,01,00,00,00);
        LocalDateTime endDate = LocalDateTime.of(2023,07,02,00,00,00);

        assertDoesNotThrow(() -> service.validateDates(startDate,endDate));
    }


    //**TESTS EJEMPLOS**//
    /*
    //Se ejecuta una vez antes de todos los test
    @BeforeAll
    static void beforeAll(){
        System.out.println("Ejecutando before all");
    }

    //Se ejecuta antes de cada test
    @BeforeEach
    void beforeEach(){
        System.out.println("Ejecutando before each");
    }
     */

    /*
    //Con disabled no se ejecuta
    @Test @Disabled
    @DisplayName("Probando assertEquals")
    void helloJunit5(){
        assertEquals(10,5+5);
        System.out.println("Ejecutando assertEquals");
    }

    @Test
    @DisplayName("Probando tests")
    void helloJunit5_2(){
        assertNotEquals(10,5+4);
        System.out.println("Ejecutando assertNotEquals");
        assertTrue(1>0);
        assertFalse(-1>0);
    }


    @Test
    @DisplayName("Probando assert null")
    void helloJunit5_3(){
        String value = null;
        String value2 = ""; //Esta vacio pero no es null
        assertNull(value);
        assertNotNull(value2);
    }


    //Usamos assertj para usar estas tests
    @Test
    @DisplayName("Probando assert that")
    void helloJunit5_4(){
        String value1 = "casa";
        String value2 = "cas";
        String value3 = "Met";
        assertThat(value3).isNotEmpty()
                          .isNotBlank()
                          .hasSize(3)
                          .contains("M");

        assertThat(100).isGreaterThan(90)
                .isLessThan(500)
                .isGreaterThanOrEqualTo(100);


        List<Integer> numeros = List.of(1, 4, 7, 2, 10);
        assertThat(numeros).isNotNull()
                .hasSize(5)
                .isSorted()
                .contains(7)
                .doesNotContain(200)
                .anyMatch(i -> i < 5)
                .allMatch(i -> i > 0);
    }
     */

    /*
    @AfterEach
    void afterEach(){
        System.out.println("Ejecutando after each");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("Ejecutando after all");
    }
     */

}
