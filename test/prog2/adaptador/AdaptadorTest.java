package prog2.adaptador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.vista.CentralUBException;

import static org.junit.jupiter.api.Assertions.*;

class AdaptadorTest {
    Adaptador adaptador;

    @BeforeEach
    void setUp() throws CentralUBException {
        adaptador = new Adaptador();
    }

    @Test
    void testSetInsercioBarres() throws CentralUBException {
        adaptador.setInsercioBarres(100);
        assertEquals(adaptador.getInsercioBarres(), 100);
        adaptador.setInsercioBarres(0);
        assertEquals(adaptador.getInsercioBarres(), 0);

        assertThrows(CentralUBException.class, () -> adaptador.setInsercioBarres(-1));
        assertThrows(CentralUBException.class, () -> adaptador.setInsercioBarres(101));
    }

    @Test
    void testMostraReactor() throws CentralUBException {
        adaptador.activaReactor();
        assertTrue(adaptador.mostraReactor().contains("està activat"));

        adaptador.desactivaReactor();
        assertTrue(adaptador.mostraReactor().contains("està desactivat"));
    }
}