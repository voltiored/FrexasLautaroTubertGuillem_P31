package prog2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaginaIncidenciesTest {
    PaginaIncidencies pagina;
    int numDia = 4;
    String i1 = "El reactor es va desactivar per superar la temperatura màxima";
    String i2 = "La bomba refrig. 2 està fora de servei";

    @BeforeEach
    void setUp(){
        pagina = new PaginaIncidencies(numDia);
    }

    @Test
    void testConstructor(){
        assertEquals(pagina.getNumDia(), numDia);
    }

    @Test
    void testToString(){
        assertFalse(pagina.toString().contains(i1));
        assertFalse(pagina.toString().contains(i2));

        pagina.afegeixIncidencia(i1);
        assertTrue(pagina.toString().contains(i1));
        assertFalse(pagina.toString().contains(i2));

        pagina.afegeixIncidencia(i2);
        assertTrue(pagina.toString().contains(i1));
        assertTrue(pagina.toString().contains(i2));
    }

}