package prog2.adaptador;

import prog2.model.Dades;
import prog2.model.PaginaIncidencies;
import prog2.model.Reactor;
import prog2.model.SistemaRefrigeracio;
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
     * Metode que activa el reactor
     * @throws CentralUBException si la temperatura del reactor és superior als 1000º C
     */
    public void activaReactor() throws CentralUBException {
        dades.activaReactor();
    }

    /**
     * Metode que desactiva el reactor
     */
    public void desactivaReactor(){
        dades.desactivaReactor();
    }

    /**
     * Metode que mostra l'estat del reactor
     */
    public String mostraReactor(){
        String estat;
        estat = (dades.mostraReactor().getActivat()) ? "activat":"desactivat";
        return "El reactor està " + estat + " i té una temperatura de " + dades.mostraReactor().getTemperatura() + "ºC.";
    }

    /**
     * Metode que activa una bomba refrigerant
     * @param id l'id de la bomba a activar
     * @throws CentralUBException si la bomba esta fora de servei
     */
    public void activaBomba(int id) throws CentralUBException {
        dades.activaBomba(id);
    }

    /**
     * Metode que desactiva una bomba refrigerant
     * @param id l'id de la bomba a desactivar
     */
    public void desactivaBomba(int id){
        dades.desactivaBomba(id);
    }

    /**
     * Metode que mostra l'estat del sistema de refrigeracio
     */
    public void mostrarSistemaRefrigeracio(){
            SistemaRefrigeracio sistema = dades.mostraSistemaRefrigeracio();
    }

    /**
     * Metode que mostra la pagina d'estat (provisional) del dia actual
     */
    public void mostraEstat(){
        System.out.println(dades.mostraEstat().toString());
    }

    /**
     * Metode que mostra tota la informacio acumulada a la bitacola
     */
    public void mostraBitacola(){
    System.out.println(dades.mostraBitacola().toString());
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
            resultat.append(incidencia.toString()).append("\n");
        }

        return resultat.toString();
    }
    public float calculaPotencia(){
            return dades.calculaPotencia();
    }

    public String finalitzaDia(float demandaPotencia){
        return dades.finalitzaDia(demandaPotencia).toString();
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
