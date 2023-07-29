package org.metcamp.web.service;

import org.metcamp.web.exceptions.ValidationException;

public class ValidationServiceTest {
    //given - dado    --> configurar datos
    //when - cuando   --> ejecutar el metodo
    //then - entonces --> validar el resultado

    @Test
    @DisplayName("Probando el metodo validateId con 0")
    void validateIdTestWithIdZero(){
        //service.validateId(0);

        int id = 0;
        assertThrows(ValidationException.class, () -> service.validateId(id));
    }

    @Test
    @DisplayName("Probando el metodo validateId con 1 - Happy path")
    void validateIdTestWithIdNotZero(){
        //service.validateId(1);

        int id = 1;
        assertDoesNotThrows(() -> service.validateId(id));
    }

    @Test
    @DisplayName("Probando el metodo validateId con -1")
    void validateIdTestWithIdNegative(){
        //service.validateId(1);

        int id = -1;
        assertThrows(ValidationException.class, () -> service.validateId(id));
    }


    @Test
    @DisplayName("Probando el metodo validateAttendeess con -1")
    void validateAttendeessTestWithCantNegativa(){
        //given
        int cantidad = -1;
        //when
        boolean resultado = service.validaAttendeess(cantidad);
        //then
        assertFalse(resultado);
    }

    /*
    //Se ejecuta una vez antes de todos los test
    @BeforeAll
    static void beforeEach(){
        System.out.println("Ejecutando before all");
    }

    @BeforeEach
    void beforeEach(){
        System.out.println("Ejecutando before each");
    }

    @Test
    @DisplayName("Probando assertEquals")
    void helloJunit5(){
        assertEquals(10,5+5);
        System.out.println("Ejecutando assertEquals");
    }

    @Test
    @DisplayName("Probando assertEquals")
    void helloJunit5(){
        assertNotEquals(10,5+4);
        System.out.println("Ejecutando assertNotEquals");
    }

    @Test
    @DisplayName("Probando assert true y false")
    void helloJunit5(){
        assertTrue(1>0);
        assertFalse(1>0);
    }

    @Test
    @DisplayName("Probando assert null")
    void helloJunit5(){
        String value = null;
        String value2 = ""; //Esta vacio pero no es null
        assertNull(value);
        assertNull(value2);
    }

    @Test
    @DisplayName("Probando assert that")
    void helloJunit5(){
        String value1 = "casa"
        String value2 = "cas";
        String value3 = "met";
        assertThat(value2).isNotEmpty()
                          .isNotBlank
                          .hasSize(3)
                          .contains("M");

        assertThat(100).isGreatertThan(90)
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

    @AfterEach
    void afterEach(){
        System.out.println("Ejecutando after each");
    }

    @AfterAll
    static void afterEach(){
        System.out.println("Ejecutando after all");
    }

     */
}
