package prog2.model;

import prog2.vista.CentralUBException;

public class Turbina implements InComponent {
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
            return 20;
        }
        return 0;
    }

    @Override
    public float calculaOutput(float input) {
        if (!estaActivat || input < 100f) {
            return 0;
        }
        return input * 2;
    }
}
