package prog2.model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Classe que representa una pagina d'incidencies de la bitacola
 * @author Lautaro Frexas
 * @author Guillem Tubert
 */
public class PaginaIncidencies extends PaginaBitacola {
    /**
     * Llista amb totes les incidencies del dia
     */
    private ArrayList<String> llistaIncidencies;

    /**
     * El constructor de la classe PaginaIncidencies
     * @param numDia el nombre del dia
     */
    public PaginaIncidencies(int numDia){
        super(numDia);
        llistaIncidencies = new ArrayList<>();
    }

    /**
     * Metode que afegeix una incidencia a la llista
     * @param descIncidencia la descripcio de la incidencia
     */
    public void afegeixIncidencia(String descIncidencia){
        llistaIncidencies.add(descIncidencia);
    }
    /**
     * Metode que retorna una cadena de caracters amb tota la informacio de la pagina
     * @return la informacio de la pagina
     */
    public String toString(){
        String resultat = "# Pàgina Incidències\n- Dia: " + getNumDia();

        Iterator<String> it = llistaIncidencies.iterator();

        while(it.hasNext()){
            resultat += "\n Descripció Incidència: " + it.next();
        }

        return resultat;
    }
}
