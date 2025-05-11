package prog2.adaptador;

import prog2.model.Dades;
import prog2.model.PaginaIncidencies;
import prog2.vista.CentralUBException;

import java.io.*;
import java.util.List;

/**
 * Classe que serveix per a accedir a model des de vista
 * @author Frexas Lautaro
 * @author Guillem Tubert
 */
public class Adaptador {
    private Dades dades;

    /**
     * Constructor de la classe Adaptador
     */
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
        String estat = (dades.mostraReactor().getActivat()) ? "activat":"desactivat";
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
     * Metode que activa totes les bombes de refrigeracio
     */
    public void activaRefrigeracio() throws CentralUBException {
        dades.mostraSistemaRefrigeracio().activa();
    }
    /**
     * Metode que desactiva una bomba refrigerant
     * @param id l'id de la bomba a desactivar
     */
    public void desactivaBomba(int id){
        dades.desactivaBomba(id);
    }

    /**
     * Metode que desactiva totes les bombes refrigerants
     */
    public void desactivaRefrigeracio(){
        dades.mostraSistemaRefrigeracio().desactiva();
    }
    /**
     * Metode que mostra l'estat del sistema de refrigeracio
     */
    public void mostrarSistemaRefrigeracio(){
        System.out.println(dades.mostraSistemaRefrigeracio().getBombesRefrigerants().get(0).toString());
        System.out.println(dades.mostraSistemaRefrigeracio().getBombesRefrigerants().get(1).toString());
        System.out.println(dades.mostraSistemaRefrigeracio().getBombesRefrigerants().get(2).toString());
        System.out.println(dades.mostraSistemaRefrigeracio().getBombesRefrigerants().get(3).toString());

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

    /**
     * Retorna la potència generada per la central. Aquesta potència es l'output de la turbina.
     * @return la potència generada per la central. Aquesta potència es l'output de la turbina.
     */
    public float calculaPotencia(){
            return dades.calculaPotencia();
    }

    /**
     * Duu a terme les accions relacionades amb la finalització d'un dia.
     * @param demandaPotencia la demanda de potencia del dia
     * @return un String amb la informacio de la pagina economica i la d'estat del dia
     */
    public String finalitzaDia(float demandaPotencia){
        return dades.finalitzaDia(demandaPotencia).toString();
    }

    /**
     * Guarda les dades actuals de la central en un arxiu
     * @param camiDesti on s'han de guardar les dades
     * @throws CentralUBException si es produeix un error en guardar les dades
     */
    public void guardaDades(String camiDesti) throws CentralUBException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(camiDesti))) {
            oos.writeObject(dades);
        } catch (IOException e) {
            throw new CentralUBException("Error en guardar les dades: " + e.getMessage());
        }
    }

    /**
     * Carrega les dades d'una central previament desada
     * @param camiOrigen d'on s'han de carregar les dades
     * @throws CentralUBException si hi ha algun error en carregar les dades
     */
    public void carregaDades(String camiOrigen) throws CentralUBException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(camiOrigen))) {
            dades = (Dades) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new CentralUBException("Error en carregar les dades: " + e.getMessage());
        }
    }
}
