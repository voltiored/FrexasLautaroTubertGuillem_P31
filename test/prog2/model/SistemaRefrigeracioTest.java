package prog2.model;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.vista.CentralUBException;

import static org.junit.jupiter.api.Assertions.*;

class SistemaRefrigeracioTest {
    private SistemaRefrigeracio sistema;
    private BombaRefrigerant b1;
    private BombaRefrigerant b2;
    private VariableUniforme variable;

    @BeforeEach
    void setup(){
        sistema = new SistemaRefrigeracio();
        variable = new VariableUniforme(Dades.VAR_UNIF_SEED);
        b1 = new BombaRefrigerant(variable, 1);
        b2 = new BombaRefrigerant(variable, 2);

    }

    @Test
    void testAfegirBomba() throws CentralUBException {

        sistema.afegirBomba(b1);

        assertEquals(sistema.getBombesRefrigerants().get(0).getId(), 1);
    }

    @Test
    void testGetActivat() throws CentralUBException {
        b1.setEstaActiva(true);
        b2.setEstaActiva(true);
        sistema.afegirBomba(b1);
        sistema.afegirBomba(b2);
        assertTrue(sistema.getActivat());

        b1.setEstaActiva(false);
        assertTrue(sistema.getActivat());

        b2.setEstaActiva(false);
        assertFalse(sistema.getActivat());
    }

    @Test
    void testGetCostOperatiu() throws CentralUBException {
        assertEquals(sistema.getCostOperatiu(), 0);

        b1.setEstaActiva(true);
        sistema.afegirBomba(b1);
        assertEquals(sistema.getCostOperatiu(), 130);

        b2.setEstaActiva(false);
        sistema.afegirBomba(b2);
        assertEquals(sistema.getCostOperatiu(), 130);

        b2.setEstaActiva(true);
        assertEquals(sistema.getCostOperatiu(), 260);
    }

    @Test
    void testCalculaOutput() throws CentralUBException {
        int input = 400;

        assertEquals(sistema.calculaOutput(input), 0);

        b1.setEstaActiva(true);
        sistema.afegirBomba(b1);
        assertEquals(sistema.calculaOutput(input), 250);

        b2.setEstaActiva(false);
        sistema.afegirBomba(b2);
        assertEquals(sistema.calculaOutput(input), 250);

        b2.setEstaActiva(true);
        assertEquals(sistema.calculaOutput(input), 400);

        input = 600;
        assertEquals(sistema.calculaOutput(input), 500);
    }
}