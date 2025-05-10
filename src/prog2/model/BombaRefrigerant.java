package prog2.model;

import prog2.vista.CentralUBException;

import java.io.Serializable;

public class BombaRefrigerant implements InBombaRefrigerant, Serializable {
    private int id; //identficador numèric
    private boolean estaActiva; //Indica si està activada o no
    private boolean foraDeServei; //Indica si esta fora de servei
    private VariableUniforme variable;

    public BombaRefrigerant(VariableUniforme variable, int id) {
        this.id=id;
        this.variable = variable;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void activa() throws CentralUBException {
        if (foraDeServei) {
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

    @Override
    public void revisa(PaginaIncidencies p) {
        int valor = variable.seguentValor();
        if (valor < 25) {
            foraDeServei = true;
            p.afegeixIncidencia("Bomba refrigerant amb id " + id + " ha quedat fora de servei.");
        }
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

    /**
     * Setter creat per a fer tests
     * @param foraDeServei
     */
    public void setForaDeServei(boolean foraDeServei){
        this.foraDeServei = foraDeServei;
    }

    /**
     * Setter creat per a fer tests
     * @param estaActiva
     */

    public void setEstaActiva(boolean estaActiva){
        this.estaActiva = estaActiva;
    }
}
