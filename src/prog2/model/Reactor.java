package prog2.model;

import prog2.vista.CentralUBException;

public class Reactor implements InComponent {
    private boolean estaActivat;
    private float temperatura;

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    @Override
    public void activa() throws CentralUBException {
        if (temperatura > 1000f){
            throw new CentralUBException("La temperatura del reactor supera els 1000ºC");
        }
        else {
            estaActivat = true;
        }
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
        //p.AfegeixIncidencia("El reactor ha quedat fora de servei.");
    }

    @Override
    public float getCostOperatiu() {
        if (estaActivat) {
            return 35f;
        }
        return 0;
    }

    @Override
    public float calculaOutput(float input) {
        if (!estaActivat) {
            return temperatura;
        }
        return temperatura + 10*(100-input);
    }
}
