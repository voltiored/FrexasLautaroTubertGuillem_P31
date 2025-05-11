package prog2.model;

import prog2.vista.CentralUBException;

import java.io.Serializable;
/**
 * Classe que representa una bomba refrigerant
 * @author Frexas Lautaro
 * @author Guillem Tubert
 */
public class BombaRefrigerant implements InBombaRefrigerant, Serializable {
    /**
     * L'identificador numèric
     */
    private int id;
    /**
     * Indica si està activada o no
     */
    private boolean estaActiva;
    /**
     * Indica si esta fora de servei
     */
    private boolean foraDeServei;
    /**
     * Variable usada per a calcular si les bombes estan fora de servei
     */
    private VariableUniforme variable;

    /**
     * El construtor de la classe bomba refrigerant
     * @param variable  Variable usada per a calcular si les bombes estan fora de servei
     * @param id L'identificador numèric
     */
    public BombaRefrigerant(VariableUniforme variable, int id) {
        this.id=id;
        this.variable = variable;
    }

    /**
     * Retorna l'id de la bomba
     * @return l'id de la bomba
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Activa la bomba
     * @throws CentralUBException si esta fora de servei
     */
    @Override
    public void activa() throws CentralUBException {
        if (foraDeServei) {
            throw new CentralUBException("La bomba refrigerant esta fora de servei");
        }
        estaActiva=true;
    }

    /**
     * Desactiva una bomba
     */
    @Override
    public void desactiva() {
        estaActiva=false;
    }

    /**
     * Retorna l'activitat de la bomba
     * @return l'activitat de la bomba
     */
    @Override
    public boolean getActivat() {
        return estaActiva;
    }

    /**
     * Revisa la bomba refrigerant. Es farà servir l'objecte de tipus VariableUniforme per determinar si la bomba es queda fora de servei. En cas afirmatiu, s'haurà de registrar la situació dins d'una pàgina d'incidències.
     * @param p Objecte de tipus PaginaIncidencies per a registrar si la bomba
     * es queda fora de servei.
     */
    @Override
    public void revisa(PaginaIncidencies p) {
        int valor = variable.seguentValor();
        if (valor < 25) {
            foraDeServei = true;
            p.afegeixIncidencia("Bomba refrigerant amb id " + id + " ha quedat fora de servei.");
        }
    }

    /**
     * Retorna true si la bomba refrigerant està fora de servei.
     * @return true si la bomba refrigerant està fora de servei, false si no
     */
    @Override
    public boolean getForaDeServei() {
        return foraDeServei;
    }

    /**
     * Retorna la capacitat de refrigeració en graus. Si la bomba no està activada, retorna zero.
     * @return la capacitat de refrigeració en graus. Si la bomba no està activada, retorna zero.
     */
    @Override
    public float getCapacitat() {
        if(estaActiva){
            return 250f;
        }
        else{
            return 0f;
        }
    }

    /**
     * Retorna el cost operatiu de la bomba. Si la bomba no està activada, retorna zero.
     * @return el cost operatiu de la bomba. Si la bomba no està activada, retorna zero.
     */
    @Override
    public float getCostOperatiu() {
        if(estaActiva){
            return 130f;
        }
        else{
            return 0f;
        }
    }

    /**
     * Retorna la informacio de la bomba en forma de String
     * @return la informacio de la bomba en forma de String
     */
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
