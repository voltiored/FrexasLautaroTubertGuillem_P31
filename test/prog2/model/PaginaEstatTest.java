package prog2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaginaEstatTest {

    private PaginaEstat pagina;
    private int numDia = 1;
    private float insercioBarres = 90;
    private float outputReactor = 125;
    private float outputSistemaRefrigeracio = 125;
    private float outputGeneradorVapor = 112.5f;
    private float outputTurbina = 225;

    @BeforeEach
    void setUp(){
        pagina = new PaginaEstat(numDia, insercioBarres, outputReactor, outputSistemaRefrigeracio, outputGeneradorVapor, outputTurbina);
    }

    @Test
    void testConstructor(){
        assertEquals(pagina.getInsercioBarres(), insercioBarres);
        assertEquals(pagina.getOutputReactor(), outputReactor);
        assertEquals(pagina.getOutputSistemaRefrigeracio(), outputSistemaRefrigeracio);
        assertEquals(pagina.getOutputGeneradorVapor(), outputGeneradorVapor);
        assertEquals(pagina.getOutputTurbina(), outputTurbina);
    }
    @Test
    void testToString(){
        String s = pagina.toString();

        assertTrue(s.contains("Dia: " + numDia));
        assertTrue(s.contains("Inserció Barres: " + insercioBarres));
        assertTrue(s.contains("Output Reactor: " + outputReactor));
        assertTrue(s.contains("Output Sistema de Refrigeració: " + outputSistemaRefrigeracio));
        assertTrue(s.contains("Output Generador de Vapor: " + outputGeneradorVapor));
        assertTrue(s.contains("Output Turbina: " + outputTurbina));

    }

}