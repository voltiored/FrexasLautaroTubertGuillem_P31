package prog2.model;

import prog2.vista.CentralUBException;

import java.io.Serializable;

public class GeneradorVapor implements InComponent, Serializable {
    private boolean estaActivat;

    @Override
    public void activa() throws CentralUBException {
        estaActivat = true;
    }

    @Override
    public void desactiva() {
        estaActivat = false;
    }

    @Override
    public boolean getActivat() {
        return estaActivat;
    }

    @Override
    public void revisa(PaginaIncidencies p) {
    }

    @Override
    public float getCostOperatiu() {
        if (estaActivat) {
            return 25f;
        }
        return 0;
    }

    @Override
    public float calculaOutput(float input) {
        if (estaActivat) {
            return input * 0.9f;
        }
        return 25f;
    }
}
