package prog2.model;

import prog2.vista.CentralUBException;

import java.io.Serializable;

public class Reactor implements InComponent, Serializable {
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
        if (temperatura > 1000f) {
            p.afegeixIncidencia("El reactor ha quedat fora de servei.");
        }
        else{
            System.out.println("El reactor no presenta cap anormalitat, esta activat");
        }
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
