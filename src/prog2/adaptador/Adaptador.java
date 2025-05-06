package prog2.adaptador;

import prog2.model.Dades;
import prog2.model.PaginaIncidencies;
import prog2.vista.CentralUBException;

import java.io.*;
import java.util.List;

public class Adaptador {
    private Dades dades;

    public Adaptador() throws CentralUBException {
        dades = new Dades();
    }

    /**
     * @return el grau d'inserció de les barres, en percentatge
     */
    public float getInsercioBarres(){
        return dades.getInsercioBarres();
    }

    /**
     * Metode per a canviar el percentatge d'insercio
     * @param percentatge el nou percentatge
     * @throws CentralUBException si el percentatge esta per sota o per sobre dels valors establerts
     */
    public void setInsercioBarres(float percentatge) throws CentralUBException {
        dades.setInsercioBarres(percentatge);
    }



    /**
     * Retorna totes les incidències com un únic String, amb cada incidència
     * separada per un salt de línia.
     * @return String amb les incidències separades per línies.
     */
    public String mostraIncidencies() {
        List<PaginaIncidencies> incidencies = dades.mostraIncidencies();
        StringBuilder resultat = new StringBuilder();

        for (PaginaIncidencies incidencia : incidencies) {
            resultat.append(incidencia).append("\n");
        }

        return resultat.toString();
    }

    public void guardaDades(String camiDesti) throws CentralUBException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(camiDesti))) {
            oos.writeObject(dades);
        } catch (IOException e) {
            throw new CentralUBException("Error en guardar les dades: " + e.getMessage());
        }
    }

    public void carregaDades(String camiOrigen) throws CentralUBException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(camiOrigen))) {
            dades = (Dades) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new CentralUBException("Error en carregar les dades: " + e.getMessage());
        }
    }
}
