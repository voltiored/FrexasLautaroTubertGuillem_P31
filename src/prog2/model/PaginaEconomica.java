package prog2.model;

/**
 * Classe que representa una pagina economica de la bitacola
 * @author Lautaro Frexas
 * @author Guillem Tubert
 */

public class PaginaEconomica extends PaginaBitacola{

    /**
     * La demanda de potencia electrica del dia
     */
    private float demandaPotencia;
    /**
     * La potencia electrica generada del dia
     */
    private float potenciaGenerada;
    /**
     * El percentatge de la demanda de potencia satisfeta del dia
     */
    private float demandaSatisfeta;
    /**
     * Els beneficis generats del dia
     */
    private float beneficis;
    /**
     * Penalització per excés de potència del dia
     */
    private float penalitzacioExces;
    /**
     * Costos operatius del dia
     */
    private float costosOperatius;
    /**
     * Els guanys acumulats del dia
     */
    private float guanysAcumulats;

    /**
     * Constructor de la classe PaginaEconomica
     * @param numDia el nombre del dia
     * @param demandaPotencia la demanda de potencia del dia
     * @param potenciaGenerada la potencia generada del dia
     * @param costosOperatius els costos operatius del dia
     * @param guanysAcumulats els guanys acumulats del dia
     */
    public PaginaEconomica(int numDia, float demandaPotencia, float beneficis, float penalitzacioExces, float potenciaGenerada, float costosOperatius, float guanysAcumulats){
        super(numDia);
        this.demandaPotencia = demandaPotencia;
        this.potenciaGenerada = potenciaGenerada;
        this.demandaSatisfeta = potenciaGenerada/demandaPotencia * 100;
        this.beneficis = beneficis;
        this.penalitzacioExces = penalitzacioExces;
        this.costosOperatius = costosOperatius;
        this.guanysAcumulats = guanysAcumulats;
    }

    /**
     * @return la demanda de potencia del dia
     */
    public float getDemandaPotencia() {
        return demandaPotencia;
    }
    /**
     * @return la potencia generada del dia
     */
    public float getPotenciaGenerada() {
        return potenciaGenerada;
    }

    /**
     * @return la demanda satisfeta del dia
     */
    public float getDemandaSatisfeta() {
        return demandaSatisfeta;
    }
    /**
     * @return els beneficis del dia
     */
    public float getBeneficis() {
        return beneficis;
    }
    /**
     * @return la penalitzacio d'exces del dia
     */
    public float getPenalitzacioExces() {
        return penalitzacioExces;
    }
    /**
     * @return els costos operatius del dia
     */
    public float getCostosOperatius() {
        return costosOperatius;
    }
    /**
     * @return els guanys acumulats del dia
     */
    public float getGuanysAcumulats() {
        return guanysAcumulats;
    }

    /**
     * Metode que retorna una cadena de caracters amb tota la informacio de la pagina
     * @return la informacio de la pagina
     */
    public String toString(){
        return ("# Pàgina Econòmica\n" +
                "- Dia: " + getNumDia() + "\n" +
                "- Demanda de Potència: " + getDemandaPotencia() + "\n" +
                "- Potència Generada: " + getPotenciaGenerada() + "\n" +
                "- Demanda de Potència Satisfeta: "+ getDemandaSatisfeta() + " %\n" +
                "- Beneficis: " + getBeneficis() + " Unitats Econòmiques\n" +
                "- Penalització Excés Producció: " + getPenalitzacioExces() + " Unitats Econòmiques\n" +
                "- Cost Operatiu: "+ getCostosOperatius() + " Unitats Econòmiques\n" +
                "- Guanys acumulats: "+ getGuanysAcumulats() + " Unitats Econòmiques");
    }
}
