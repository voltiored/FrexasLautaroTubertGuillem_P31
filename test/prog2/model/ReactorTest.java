package prog2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.vista.CentralUBException;

import static org.junit.jupiter.api.Assertions.*;

class ReactorTest {
    Reactor reactor;

    @BeforeEach
    void setUp(){
        reactor = new Reactor();
    }

    @Test
    void testActiva() throws CentralUBException {
        reactor.setTemperatura(1000);
        reactor.activa();
        assertTrue(reactor.getActivat());

        reactor.setTemperatura(1001);
        assertThrows(CentralUBException.class, () -> {
            reactor.activa();
        });
    }

    @Test
    void testRevisa(){
        PaginaIncidencies p = new PaginaIncidencies(1);

        reactor.setTemperatura(1000);
        reactor.revisa(p);
        assertFalse(p.toString().contains("El reactor ha quedat fora de servei."));

        reactor.setTemperatura(1001);
        reactor.revisa(p);
        assertTrue(p.toString().contains("El reactor ha quedat fora de servei."));

    }

    @Test
    void testGetCostOperatiu() throws CentralUBException {
        reactor.setTemperatura(0);
        reactor.activa();
        assertEquals(reactor.getCostOperatiu(), 35f);

        reactor.desactiva();
        assertEquals(reactor.getCostOperatiu(), 0);
    }

    @Test
    void testCalculaOutput() throws CentralUBException {
        float temperatura = 50;
        float input = 10;
        reactor.setTemperatura(temperatura);

        reactor.activa();
        assertEquals(reactor.calculaOutput(input), temperatura + 10*(100-input));

        reactor.desactiva();
        assertEquals(reactor.calculaOutput(input), temperatura);
    }

}