package prog2.model;

import prog2.vista.CentralUBException;

import java.io.Serializable;
/**
 * Classe que representa un reactor
 * @author Frexas Lautaro
 * @author Guillem Tubert
 */
public class Reactor implements InComponent, Serializable {
    /**
     * Si el reactor esta actiu o no
     */
    private boolean estaActivat;
    /**
     * La temperatura del reactor
     */
    private float temperatura;

    /**
     * Retorna la temperatura del reactor
     * @return la temperatura del reactor
     */
    public float getTemperatura() {
        return temperatura;
    }

    /**
     * Estableix la temperatura del reactor
     * @param temperatura la temperatura que volem assignar
     */
    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    /**
     * Activa el reactor
     * @throws CentralUBException si la temperatura del reactor supera els 1000º
     */
    @Override
    public void activa() throws CentralUBException {
        if (temperatura > 1000f){
            throw new CentralUBException("La temperatura del reactor supera els 1000ºC");
        }
        else {
            estaActivat = true;
        }
    }

    /**
     * Desactiva el reactor
     */
    @Override
    public void desactiva() {
        estaActivat = false;
    }

    /**
     * Retorna si el reactor està activat o no.
     * @return si el reactor està activat o no.
     */
    @Override
    public boolean getActivat() {
        return estaActivat;
    }

    /**
     * Revisa el component. Afegeix una incidencia a la bitacola si la temperatura supera els 1000 graus
     * @param p Objecte de tipus PaginaIncidencies.
     */
    @Override
    public void revisa(PaginaIncidencies p) {
        if (temperatura > 1000f) {
            estaActivat = false;
            p.afegeixIncidencia("El reactor ha quedat fora de servei.");
        }
        else{
            System.out.println("El reactor no presenta cap anormalitat, esta activat");
        }
    }

    /**
     * Obté el cost operatiu del reactor. El cost operatiu depèn de si el component està activat. Si no està activat el cost és zero.
     * @return  el cost operatiu del reactor.
     */
    @Override
    public float getCostOperatiu() {
        if (estaActivat) {
            return 35f;
        }
        return 0;
    }

    /**
     * Calcula l'output del reactor donat l'input.
     * @param input Input que rep el reactor.
     * @return l'output del reactor donat l'input.
     */
    @Override
    public float calculaOutput(float input) {
        if (!estaActivat) {
            return temperatura;
        }
        return temperatura + 10*(100-input);
    }
}
