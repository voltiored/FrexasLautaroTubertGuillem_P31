package prog2.model;

import prog2.vista.CentralUBException;

public class BombaRefrigerant implements InBombaRefrigerant{
    private int id; //identficador numèric
    private boolean estaActiva; //Indica si està activada o no
    private boolean foraDeServei; //Indica si esta fora de servei
    private VariableUniforme variable;

    public BombaRefrigerant(int id, VariableUniforme variable) {
        this.id=id;
        this.variable = variable;
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

    @Override
    public void revisa(PaginaIncidencies p) {
        int valor = variable.seguentValor();
        if (valor < 25) {
            foraDeServei = true;
            p.registra("Bomba refrigerant amb id " + id + " ha quedat fora de servei.");
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
}
