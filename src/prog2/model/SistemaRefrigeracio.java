package prog2.model;

import prog2.vista.CentralUBException;

import java.io.Serializable;
import java.util.ArrayList;

public class SistemaRefrigeracio implements InComponent, Serializable {
    /**
     * Llista on guardarem les bombes de refrigeracio
     */
    ArrayList<BombaRefrigerant> bombesRefrigerants;
    /**
     * Si el sistema esta activat o no
     */
    private boolean estaActivat;

    /**
     * Constructor de la classe
     */
    public SistemaRefrigeracio(){
        bombesRefrigerants = new ArrayList<>();
    }

    /**
     * Afegeix una bomba a la llista
     * @param b la bomba que volem afegir
     */
    public void afegirBomba(BombaRefrigerant b) throws CentralUBException {
        bombesRefrigerants.add(b);
    }

    /**
     * Activa el sistema de refrigeracio i les bombes.
     * @throws CentralUBException si alguna de les bombes esta fora de servei
     */
    @Override
    public void activa() throws CentralUBException {
        for (BombaRefrigerant b : bombesRefrigerants) {
            b.activa();
        }
    }

    /**
     * Desactiva el sistema de refrigeracio i les bombes
     */
    @Override
    public void desactiva() {
        for (BombaRefrigerant b : bombesRefrigerants) {
            b.desactiva();
        }
    }

    /**
     * Retorna si el sistema esta activat o no
     * @return si el sistema esta activat o no
     */
    @Override
    public boolean getActivat() {
        for (BombaRefrigerant b : bombesRefrigerants) {
            if (b.getActivat()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Revisa si les bombes estan en funcionament o no
     * @param p Objecte de tipus PaginaIncidencies.
     */
    @Override
    public void revisa(PaginaIncidencies p) {
        for (BombaRefrigerant b : bombesRefrigerants) {
            b.revisa(p);
        }
    }

    /**
     * Obté el cost operatiu del sistema. El cost operatiu depèn de si el component està activat. Si no està activat el cost és zero.
     * @return el cost operatiu del sistema
     */
    @Override
    public float getCostOperatiu() {
        float cost = 0;
        for (BombaRefrigerant b : bombesRefrigerants) {
            cost += b.getCostOperatiu();
        }
        return cost;
    }

    /**
     * Calcula l'output del sistema donat l'input.
     * @param input Input que rep el sistema.
     * @return
     */
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

    /**
     * Retorno la llista amb les bombes
     * @return la llista amb les bombes
     */
    public ArrayList<BombaRefrigerant> getBombesRefrigerants(){
        return bombesRefrigerants;
    }
}
