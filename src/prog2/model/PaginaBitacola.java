package prog2.model;

public class PaginaBitacola {
    /**
     * El nombre del dia
     */
    private int numDia;

    /**
     * Constructor de la classe PaginaBitacola
     *
     * @param numDia El nombre del dia de la pàgina
     */
    public PaginaBitacola(int numDia) {
        this.numDia = numDia;
    }

    /**
     * @return el nombre del dia
     */
    public int getNumDia() {
        return numDia;
    }
}