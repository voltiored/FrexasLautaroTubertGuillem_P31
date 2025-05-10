package prog2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.vista.CentralUBException;

import static org.junit.jupiter.api.Assertions.*;

class GeneradorVaporTest {
    private GeneradorVapor generador;

    @BeforeEach
    void setUp(){
        generador = new GeneradorVapor();
    }
    @Test
    void testActiva() throws CentralUBException {
        generador.activa();
        assertTrue(generador.getActivat());
    }

    @Test
    void testDesactiva(){
        generador.desactiva();
        assertFalse(generador.getActivat());
    }

    @Test
    void testGetCostOperatiu() throws CentralUBException {
        generador.activa();
        assertEquals(generador.getCostOperatiu(), 25f);
        generador.desactiva();
        assertEquals(generador.getCostOperatiu(), 0);

    }

    @Test
    void testCalculaOutput() throws CentralUBException {
        float input = 2025f;
        generador.activa();
        assertEquals(generador.calculaOutput(input), input*0.9f);

        generador.desactiva();
        assertEquals(generador.calculaOutput(input), 25f);

    }
}