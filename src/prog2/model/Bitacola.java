package prog2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bitacola implements InBitacola, Serializable {
    private ArrayList<PaginaBitacola> bitacolas;

    @Override
    public void afegeixPagina(PaginaBitacola p) {

    }

    @Override
    public List<PaginaIncidencies> getIncidencies() {
        return List.of();
    }

    public String toString(){
        return "";
    }
}
