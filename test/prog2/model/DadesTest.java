package prog2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.vista.CentralUBException;

import static org.junit.jupiter.api.Assertions.*;

class DadesTest {
    private Dades dades;

    @BeforeEach
    void setUp() throws CentralUBException {
        dades = new Dades();
    }

    /* Comento este, de momento no lo hago porque testRefrigeraReactor() es privado
    @Test
    void testRefrigeraReactor() throws CentralUBException {

        dades.setInsercioBarres(50);
        dades.mostraSistemaRefrigeracio().getBombesRefrigerants().get(0).setEstaActiva(true);
        dades.mostraSistemaRefrigeracio().getBombesRefrigerants().get(1).setEstaActiva(true);
        dades.mostraSistemaRefrigeracio().getBombesRefrigerants().get(2).setEstaActiva(false);
        dades.mostraSistemaRefrigeracio().getBombesRefrigerants().get(3).setEstaActiva(false);

        assertEquals(dades.refrigeraReactor());
    }
*/
    @Test
    void testSetInsercioBarres() throws CentralUBException {
        dades.setInsercioBarres(50);
        assertEquals(dades.getInsercioBarres(), 50);

        assertThrows(CentralUBException.class, () ->{
           dades.setInsercioBarres(-1);
        });

        assertThrows(CentralUBException.class, () ->{
            dades.setInsercioBarres(101);
        });
    }

    @Test
    void testActivaBomba() throws CentralUBException {
        dades.mostraSistemaRefrigeracio().desactiva();
        dades.mostraSistemaRefrigeracio().getBombesRefrigerants().get(0).setForaDeServei(false);
        dades.mostraSistemaRefrigeracio().getBombesRefrigerants().get(1).setForaDeServei(false);

        assertFalse(dades.mostraSistemaRefrigeracio().getBombesRefrigerants().get(0).getActivat());
        assertFalse(dades.mostraSistemaRefrigeracio().getBombesRefrigerants().get(1).getActivat());

        dades.activaBomba(0);
        assertTrue(dades.mostraSistemaRefrigeracio().getBombesRefrigerants().get(0).getActivat());
        assertFalse(dades.mostraSistemaRefrigeracio().getBombesRefrigerants().get(1).getActivat());

        dades.activaBomba(1);
        assertTrue(dades.mostraSistemaRefrigeracio().getBombesRefrigerants().get(0).getActivat());
        assertTrue(dades.mostraSistemaRefrigeracio().getBombesRefrigerants().get(1).getActivat());
    }

    @Test
    public void testDesactivaBomba() throws CentralUBException {
        dades.mostraSistemaRefrigeracio().getBombesRefrigerants().get(0).setForaDeServei(false);
        dades.mostraSistemaRefrigeracio().getBombesRefrigerants().get(1).setForaDeServei(false);
        dades.mostraSistemaRefrigeracio().activa();

        assertTrue(dades.mostraSistemaRefrigeracio().getBombesRefrigerants().get(0).getActivat());
        assertTrue(dades.mostraSistemaRefrigeracio().getBombesRefrigerants().get(1).getActivat());

        dades.desactivaBomba(1);
        assertTrue(dades.mostraSistemaRefrigeracio().getBombesRefrigerants().get(0).getActivat());
        assertFalse(dades.mostraSistemaRefrigeracio().getBombesRefrigerants().get(1).getActivat());

        dades.desactivaBomba(0);
        assertFalse(dades.mostraSistemaRefrigeracio().getBombesRefrigerants().get(0).getActivat());
        assertFalse(dades.mostraSistemaRefrigeracio().getBombesRefrigerants().get(1).getActivat());
    }
    @Test
    void testCalculaPotencia(){
        //Falta
    }
}