package prog2.model;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
class BitacolaTest {
    private Bitacola bitacola;
    private PaginaEconomica pe;
    private PaginaIncidencies p1;
    private PaginaIncidencies p2;

    private float demandaPotencia = 250;
    private float potenciaGenerada = 225;
    private float demandaSatisfeta;
    private float beneficis = 225;
    private float penalitzacioExces = 0;
    private float costosOperatius = 215;
    private float guanysAcumulats = 10;

    @BeforeEach
    void setUp(){
        bitacola = new Bitacola();
        pe = new PaginaEconomica(0, demandaPotencia, beneficis, penalitzacioExces, potenciaGenerada, costosOperatius, guanysAcumulats);
        p1 = new PaginaIncidencies(1);
        p2 = new PaginaIncidencies(2);
    }

    @Test
    void testAfegeixPagina(){
        assertFalse(bitacola.toString().contains("Dia: 0"));
        bitacola.afegeixPagina(pe);
        assertTrue(bitacola.toString().contains("Dia: 0"));
    }

    @Test
    void testGetIncidencies(){
        ArrayList<PaginaIncidencies> incidencies = new ArrayList<>();
        incidencies.add(p1);
        incidencies.add(p2);

        bitacola.afegeixPagina(pe);
        bitacola.afegeixPagina(p1);
        bitacola.afegeixPagina(p2);

        assertEquals(bitacola.getIncidencies(), incidencies);
    }

    @Test
    void testToString(){
        assertFalse(bitacola.toString().contains("Dia: 0"));
        assertFalse(bitacola.toString().contains("Dia: 1"));
        assertFalse(bitacola.toString().contains("Dia: 2"));

        bitacola.afegeixPagina(pe);
        assertTrue(bitacola.toString().contains("Dia: 0"));
        assertFalse(bitacola.toString().contains("Dia: 1"));
        assertFalse(bitacola.toString().contains("Dia: 2"));

        bitacola.afegeixPagina(p1);
        assertTrue(bitacola.toString().contains("Dia: 0"));
        assertTrue(bitacola.toString().contains("Dia: 1"));
        assertFalse(bitacola.toString().contains("Dia: 2"));

        bitacola.afegeixPagina(p2);
        assertTrue(bitacola.toString().contains("Dia: 0"));
        assertTrue(bitacola.toString().contains("Dia: 1"));
        assertTrue(bitacola.toString().contains("Dia: 2"));
    }
  
}