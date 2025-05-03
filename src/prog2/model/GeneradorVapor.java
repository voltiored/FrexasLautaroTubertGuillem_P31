package prog2.model;

import prog2.vista.CentralUBException;

public class GeneradorVapor implements InComponent {
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
        //p.AfegeixIncidencia("El generador de vapor ha quedat fora de servei.");
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
