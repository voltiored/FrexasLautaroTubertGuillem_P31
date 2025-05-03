package prog2.model;

import prog2.vista.CentralUBException;

public class BombaRefrigerant implements InBombaRefrigerant{
    private int id; //identficador numèric
    private boolean estaActiva; //Indica si està activada o no
    private boolean foraDeServei; //Indica si esta fora de servei

    public BombaRefrigerant(int id, VariableUniforme variable) {
        this.id=id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void activa() throws CentralUBException {
        if (!estaActiva){
            throw new CentralUBException("La bomba refrigerant esta fora de servei");
        }
        estaActiva=true;
    }

    @Override
    public void desactiva() {
        estaActiva=false;
    }

    @Override
    public boolean getActivat() {
        return estaActiva;
    }
    /**
     * Revisa la bomba refrigerant. Es farà servir l'objecte de tipus
     * VariableUniforme per determinar si la bomba es queda fora de servei. En
     * cas afirmatiu, s'haurà de registrar la situació dins d'una pàgina
     * d'incidències.
     * @param p Objecte de tipus PaginaIncidencies per a registrar si la bomba
     * es queda fora de servei.
     */
    @Override
    public void revisa(PaginaIncidencies p) {

    }

    @Override
    public boolean getForaDeServei() {
        return foraDeServei;
    }

    @Override
    public float getCapacitat() {
        if(estaActiva){
            return 250f;
        }
        else{
            return 0f;
        }
    }

    @Override
    public float getCostOperatiu() {
        if(estaActiva){
            return 130f;
        }
        else{
            return 0f;
        }
    }

    public String toString(){
        return "id=" + getId() +
                ", Activitat=" + getActivat() +
                ", Fora de Servei='" + getForaDeServei();
    }
}
