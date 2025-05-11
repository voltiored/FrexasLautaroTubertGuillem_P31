package prog2.model;

import prog2.vista.CentralUBException;

import java.io.Serializable;
/**
 * Classe que representa un generador de vapor
 * @author Frexas Lautaro
 * @author Guillem Tubert
 */
public class GeneradorVapor implements InComponent, Serializable {
    /**
     * Indica si esta activat
     */
    private boolean estaActivat;

    /**
     * Activa el generador.
     */
    @Override
    public void activa() throws CentralUBException {
        estaActivat = true;
    }

    /**
     * Desactiva el generador
     */
    @Override
    public void desactiva() {
        estaActivat = false;
    }

    /**
     * Retorna si el generador està activat o no.
     * @return si el generador està activat o no.
     */
    @Override
    public boolean getActivat() {
        return estaActivat;
    }

    /**
     * Revisa el generador.
     * @param p Objecte de tipus PaginaIncidencies.
     */
    @Override
    public void revisa(PaginaIncidencies p) {
    }

    /**
     * Obté el cost operatiu del generador. El cost operatiu depèn de si el component està activat. Si no està activat el cost és zero.
     * @return el cost operatiu del component
     */
    @Override
    public float getCostOperatiu() {
        if (estaActivat) {
            return 25f;
        }
        return 0;
    }

    /**
     * Calcula l'output del generador donat l'input
     * @param input Input que rep el component.
     * @return  l'output del generador
     */
    @Override
    public float calculaOutput(float input) {
        if (estaActivat) {
            return input * 0.9f;
        }
        return 25f;
    }
}
