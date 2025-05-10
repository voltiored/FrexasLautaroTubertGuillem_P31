package prog2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaginaBitacolaTest {

    private PaginaBitacola pagina;
    private int numDia = 23;

    @BeforeEach
    void setUp(){
        pagina = new PaginaBitacola(numDia);
    }

    @Test
    void testConstructor(){
        int altreNum = 5;
        PaginaBitacola altraPagina = new PaginaBitacola(altreNum);

        assertEquals(pagina.getNumDia(), numDia);
        assertEquals(altraPagina.getNumDia(), altreNum);
    }

}