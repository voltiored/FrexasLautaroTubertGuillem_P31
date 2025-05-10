package prog2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.vista.CentralUBException;

import static org.junit.jupiter.api.Assertions.*;

class BombaRefrigerantTest {
    private BombaRefrigerant bomba;
    private VariableUniforme variableUniforme;
    private int id = 23;

    @BeforeEach
    void setUp(){;
        bomba = new BombaRefrigerant(variableUniforme, id);
    }

    @Test
    void testConstructor(){
        int idDiff = 45;

        BombaRefrigerant bombaNova = new BombaRefrigerant(variableUniforme, idDiff);

        assertEquals(bombaNova.getId(), idDiff);
        assertEquals(bomba.getId(), id);
    }

    @Test
    void testActiva() throws CentralUBException {
        bomba.setForaDeServei(false);
        bomba.activa();
        assertTrue(bomba.getActivat());

        //Cas d'excepcio
        bomba.setForaDeServei(true);
        assertThrows(CentralUBException.class, () ->{
            bomba.activa();
        });
    }

    @Test
    void testDesactiva(){
        bomba.desactiva();
        assertFalse(bomba.getActivat());
    }

    @Test
    void testRevisa(){
        PaginaIncidencies pagina = new PaginaIncidencies(0);

        variableUniforme = new VariableUniforme(Dades.VAR_UNIF_SEED){
            @Override
            public int seguentValor(){
                return 100;
            }
        };
        bomba = new BombaRefrigerant(variableUniforme, id);
        bomba.setForaDeServei(false);
        bomba.revisa(pagina);
        assertFalse(bomba.getForaDeServei());
        assertFalse(pagina.toString().contains("Bomba refrigerant amb id " + id + " ha quedat fora de servei."));

        variableUniforme = new VariableUniforme(Dades.VAR_UNIF_SEED){
            @Override
            public int seguentValor(){
                return 0;
            }
        };
        bomba = new BombaRefrigerant(variableUniforme, id);
        bomba.setForaDeServei(false);
        bomba.revisa(pagina);
        assertTrue(bomba.getForaDeServei());
        assertTrue(pagina.toString().contains("Bomba refrigerant amb id " + id + " ha quedat fora de servei."));
    }

    @Test
    void testGetCapacitat(){
        bomba.setEstaActiva(true);
        assertEquals(bomba.getCapacitat(), 250);

        bomba.setEstaActiva(false);
        assertEquals(bomba.getCapacitat(), 0);
    }

    @Test
    void testGetCostOperatiu(){
        bomba.setEstaActiva(true);
        assertEquals(bomba.getCostOperatiu(), 130);

        bomba.setEstaActiva(false);
        assertEquals(bomba.getCostOperatiu(), 0);
    }

    @Test
    void testToString(){
        bomba.setEstaActiva(false);
        bomba.setForaDeServei(false);
        assertTrue(bomba.toString().contains("id=" + id));
        assertTrue(bomba.toString().contains("Activitat=false"));
        assertTrue(bomba.toString().contains("Fora de Servei='false"));

        bomba.setEstaActiva(true);
        assertTrue(bomba.toString().contains("id=" + id));
        assertTrue(bomba.toString().contains("Activitat=true"));
        assertTrue(bomba.toString().contains("Fora de Servei='false"));

        bomba.setForaDeServei(true);
        assertTrue(bomba.toString().contains("id=" + id));
        assertTrue(bomba.toString().contains("Activitat=true"));
        assertTrue(bomba.toString().contains("Fora de Servei='true"));
    }


}