package prog2.model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Classe que representa una pagina d'incidencies de la bitacola
 * @author Lautaro Frexas
 * @author Guillem Tubert
 */
public class PaginaIncidencies extends PaginaBitacola {

    private ArrayList<String> llistaIncidencies;

    public PaginaIncidencies(int numDia){
        super(numDia);
        llistaIncidencies = new ArrayList<>();
    }

    public void afegeixIncidencia(String descIncidencia){
        llistaIncidencies.add(descIncidencia);
    }

    public String toString(){
        String resultat = "# Pàgina Incidències\n- Dia: " + getNumDia();

        Iterator<String> it = llistaIncidencies.iterator();

        while(it.hasNext()){
            resultat += "\n Descripció Incidència: " + it.next();
        }

        return resultat;
    }
}
