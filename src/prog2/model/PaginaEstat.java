package prog2.model;

/**
 * Classe que representa una pagina d'estat de la bitacola
 * @author Lautaro Frexas
 * @author Guillem Tubert
 */
public class PaginaEstat extends PaginaBitacola{

    /**
     * El grau d'insercio del dia de les barres de control
     */
    private float insercioBarres;
    /**
     * La quantitat de graus Celsius del dia que el reactor transmet a l’aigua del circuit primari
     */
    private float outputReactor;
    /**
     * La quantitat de calor que el sistema de refrigeracio és capaç de transportar des del reactor fins al generador de vapor
     */
    private float outputSistemaRefrigeracio;
    /**
     * La quantitat de calor que el generador de vapor transporta al circuit secundari
     */
    private float outputGeneradorVapor;
    /**
     * La quantitat de potencia que la turbina ha generat ha partir de la temperatura del generador
     */
    private float outputTurbina;

    /**
     * Constructor de la classe PaginaEstat
     * @param numDia el nombre del dia
     * @param insercioBarres el grau d'insercio de les barres de control
     * @param outputReactor La quantitat de graus Celsius del dia que el reactor transmet a l’aigua del circuit primari
     * @param outputSistemaRefrigeracio La quantitat de calor que el sistema de refrigeracio és capaç de transportar des del reactor fins al generador de vapor
     * @param outputGeneradorVapor La quantitat de calor que el generador de vapor transporta al circuit secundari
     * @param outputTurbina La quantitat de potencia que la turbina ha generat ha partir de la temperatura del generador
     */
    public PaginaEstat(int numDia, float insercioBarres, float outputReactor, float outputSistemaRefrigeracio, float outputGeneradorVapor, float outputTurbina){
        super(numDia);
        this.insercioBarres = insercioBarres;
        this.outputReactor = outputReactor;
        this.outputSistemaRefrigeracio = outputSistemaRefrigeracio;
        this.outputGeneradorVapor = outputGeneradorVapor;
        this.outputTurbina = outputTurbina;
    }

    /**
     * @return el grau d'insercio de les barres de control
     */
    public float getInsercioBarres() {
        return insercioBarres;
    }
    /**
     * @return La quantitat de graus Celsius del dia que el reactor transmet a l’aigua del circuit primari
     */
    public float getOutputReactor() {
        return outputReactor;
    }
    /**
     * @return La quantitat de calor que el sistema de refrigeracio és capaç de transportar des del reactor fins al generador de vapor
     */
    public float getOutputSistemaRefrigeracio() {
        return outputSistemaRefrigeracio;
    }
    /**
     * @return La quantitat de calor que el generador de vapor transporta al circuit secundari
     */
    public float getOutputGeneradorVapor() {
        return outputGeneradorVapor;
    }
    /**
     * @return La quantitat de potencia que la turbina ha generat ha partir de la temperatura del generador
     */
    public float getOutputTurbina() {
        return outputTurbina;
    }

    public String toString(){
        return ("# Pàgina Estat\n" +
                "- Dia: "+ getNumDia() + "\n" +
                "- Inserció Barres: "+ getInsercioBarres() + " %\n" +
                "- Output Reactor: "+ getOutputReactor() + " Graus\n" +
                "- Output Sistema de Refrigeració: " + getOutputSistemaRefrigeracio() + " Graus\n" +
                "- Output Generador de Vapor: " + getOutputGeneradorVapor() + " Graus\n" +
                "- Output Turbina: " + getOutputTurbina() + " Unitats de Potència");
    }

}
