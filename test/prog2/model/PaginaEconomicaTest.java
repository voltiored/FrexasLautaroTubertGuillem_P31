package prog2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaginaEconomicaTest {

    private PaginaEconomica pagina;
    private int numDia = 1;
    private float demandaPotencia = 250;
    private float potenciaGenerada = 225;
    private float demandaSatisfeta;
    private float beneficis = 225;
    private float penalitzacioExces = 0;
    private float costosOperatius = 215;
    private float guanysAcumulats = 10;

    @BeforeEach
    void setUp(){
        pagina = new PaginaEconomica(numDia, demandaPotencia, beneficis, penalitzacioExces, potenciaGenerada, costosOperatius, guanysAcumulats);
    }

    @Test
    void testConstructor(){
        assertEquals(pagina.getDemandaPotencia(), demandaPotencia);
        assertEquals(pagina.getPotenciaGenerada(), potenciaGenerada);
        assertEquals(pagina.getDemandaSatisfeta(), potenciaGenerada/demandaPotencia * 100);
        assertEquals(pagina.getBeneficis(), beneficis);
        assertEquals(pagina.getPenalitzacioExces(), penalitzacioExces);
        assertEquals(pagina.getCostosOperatius(), costosOperatius);
    }
    @Test
    void testToString(){
        String s = pagina.toString();

        assertTrue(s.contains("Dia: " + numDia));
        assertTrue(s.contains("Demanda de Potència: " + demandaPotencia));
        assertTrue(s.contains("Potència Generada: " + potenciaGenerada));
        assertTrue(s.contains("Demanda de Potència Satisfeta: " + pagina.getDemandaSatisfeta()));
        assertTrue(s.contains("Beneficis: " + beneficis));
        assertTrue(s.contains("Penalització Excés Producció: " + penalitzacioExces));
        assertTrue(s.contains("Cost Operatiu: " + costosOperatius));
        assertTrue(s.contains("Guanys acumulats: " + guanysAcumulats));

    }

}