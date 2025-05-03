package prog2.model;

import prog2.vista.CentralUBException;

import java.io.Serializable;
import java.util.ArrayList;

public class SistemaRefrigeracio implements InComponent, Serializable {
    ArrayList<BombaRefrigerant> bombesRefrigerants;
    private boolean estaActivat;

    public void afegirBomba(BombaRefrigerant b) throws CentralUBException {
        bombesRefrigerants.add(b);
    }

    @Override
    public void activa() throws CentralUBException {
        for (BombaRefrigerant b : bombesRefrigerants) {
            b.activa();
        }
    }

    @Override
    public void desactiva() {
        for (BombaRefrigerant b : bombesRefrigerants) {
            b.desactiva();
        }
    }

    @Override
    public boolean getActivat() {
        for (BombaRefrigerant b : bombesRefrigerants) {
            if (b.getActivat()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void revisa(PaginaIncidencies p) {
        for (BombaRefrigerant b : bombesRefrigerants) {
            b.revisa(p);
        }
    }

    @Override
    public float getCostOperatiu() {
        float cost = 0;
        for (BombaRefrigerant b : bombesRefrigerants) {
            cost += b.getCostOperatiu();
        }
        return cost;
    }

    @Override
    public float calculaOutput(float input) {
        int bombesActives = 0;
        for (BombaRefrigerant b : bombesRefrigerants) {
            if (b.getActivat()) {
                bombesActives++;
            }
        }
        return Math.min(input, 250*bombesActives);
    }
}
