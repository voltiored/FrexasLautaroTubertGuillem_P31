package prog2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.vista.CentralUBException;

import static org.junit.jupiter.api.Assertions.*;

class TurbinaTest {
    private Turbina turbina;

    @BeforeEach
    void setUp(){
        turbina = new Turbina();
    }

    @Test
    void testGetCostOperatiu() throws CentralUBException {
        turbina.activa();
        assertEquals(turbina.getCostOperatiu(), 20);

        turbina.desactiva();
        assertEquals(turbina.getCostOperatiu(), 0);
    }

    @Test
    void testCalculaOutput() throws CentralUBException {
        int input = 100;
        turbina.desactiva();
        assertEquals(turbina.calculaOutput(input), 0);

        input = 101;
        assertEquals(turbina.calculaOutput(input), 0);

        turbina.activa();
        assertEquals(turbina.calculaOutput(input), 2*input);
    }

}