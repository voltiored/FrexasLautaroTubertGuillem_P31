package prog2.model;

import prog2.vista.CentralUBException;

import java.io.Serializable;

public class Turbina implements InComponent, Serializable {
    /**
     * Si la turbina esta activada
     */
    private boolean estaActivat;

    /**
     * Activa la turbina
     */
    @Override
    public void activa() throws CentralUBException {
        estaActivat = true;
    }

    /**
     * Desactiva la turbina
     */
    @Override
    public void desactiva() {
        estaActivat = false;
    }

    /**
     * Retorna si el component està activat o no.
     * @return si el component està activat o no.
     */
    @Override
    public boolean getActivat() {
        return estaActivat;
    }

    /**
     * Revisa la turbina. No fa res
     * @param p Objecte de tipus PaginaIncidencies.
     */
    @Override
    public void revisa(PaginaIncidencies p) {
    }

    /**
     * Obté el cost operatiu de la turbina. El cost operatiu depèn de si el component està activat. Si no està activat el cost és zero.
     * @return el cost operatiu
     */
    @Override
    public float getCostOperatiu() {
        if (estaActivat) {
            return 20;
        }
        return 0;
    }

    /**
     * Calcula l'output de la turbina donat l'input
     * @param input Input que rep la turbina.
     * @return l'output de la turbina donat l'input
     */
    @Override
    public float calculaOutput(float input) {
        if (!estaActivat || input < 100f) {
            return 0;
        }
        return input * 2;
    }
}
