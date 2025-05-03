package prog2.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Bitacola implements InBitacola{
    private ArrayList<PaginaBitacola> paginesBitacola;

    public Bitacola(){
        paginesBitacola = new ArrayList<>();
    }

    /**
     * Afegeix una pagina a la bitàcola.
     * @param p Objecte de tipus PaginaBitacola
     */
    public void afegeixPagina(PaginaBitacola p){
        paginesBitacola.add(p);
    }

    /**
     * Obté una llista amb totes les pàgines d'incidències contingudes dins
     * de la bitàcola
     */
    public List<PaginaIncidencies> getIncidencies(){
        Iterator<PaginaBitacola> it = paginesBitacola.iterator();
        PaginaBitacola paginaActual;
        List<PaginaIncidencies> paginesIncidencies = new ArrayList<>();

        while(it.hasNext()){
            paginaActual = it.next();

            if (paginaActual instanceof PaginaIncidencies)
                paginesIncidencies.add((PaginaIncidencies) paginaActual);

        }

        return paginesIncidencies;
    }

    public String toString(){
        Iterator<PaginaBitacola> it = paginesBitacola.iterator();
        String resultat = "";

        while(it.hasNext()){
            resultat += it.next().toString() + "\n";
        }

        return resultat;
    }

}
