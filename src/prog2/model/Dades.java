/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import prog2.vista.CentralUBException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe per la qual es modifiquen les dades de la central
 * @author Daniel Ortiz
 * @author Frexas Lautaro
 * @author Tubert Guillem
 */


public class Dades implements InDades, Serializable {
    /**
     * La variable usada per a calcular la variable uniforme
     */
    public final static long  VAR_UNIF_SEED = 123;
    /**
     * Els guanys amb els que comença la central
     */
    public final static float GUANYS_INICIALS = 0;
    /**
     * El preu (en unitats economiques) que cada unitat de potencia costa
     */
    public final static float PREU_UNITAT_POTENCIA = 1;
    /**
     * La penalitzacio (en unitats economiques) per excedir la potencia demanada
     */
    public final static float PENALITZACIO_EXCES_POTENCIA = 250;
    // Afegir atributs:
    /**
     * La variable uniforme usada per calcular si les Bombes Refrigerants paren de funcionar
     */
    private VariableUniforme variableUniforme;
    /**
     * El grau d'inserció de les barres de control al reactor
     */
    private float insercioBarres;
    /**
     * Objecte de tipus Reactor
     */
    private Reactor reactor;
    /**
     * Objecte de tipus SistemaRefrigeracio
     */
    private SistemaRefrigeracio sistemaRefrigeracio;
    /**
     * Objecte de tipus GeneradorVapor
     */
    private GeneradorVapor generadorVapor;
    /**
     * Objecte de tipus turbina
     */
    private Turbina turbina;
    /**
     * Objecte de tipus Bitacola, on es registrara la informacio de la central
     */
    private Bitacola bitacola;
    /**
     * El dia actual
     */
    private int dia = 1;
    /**
     * La quantitat de guanys (en unitats economiques) que ha acumulat la facultat
     */
    private float guanysAcumulats = 0f;

    /**
     * Constructor de la classe Dades
     */
    public Dades() throws CentralUBException {
        // Inicialitza Atributs
        this.variableUniforme = new VariableUniforme(VAR_UNIF_SEED);
        this.insercioBarres = 100f;
        this.reactor = new Reactor();
        this.reactor.desactiva();
        this.sistemaRefrigeracio = new SistemaRefrigeracio();
        this.generadorVapor = new GeneradorVapor();
        this.generadorVapor.activa();
        this.turbina = new Turbina();
        this.turbina.activa();
        this.bitacola = new Bitacola();
        this.dia = 1;
        this.guanysAcumulats = GUANYS_INICIALS;
        
        // Afegeix bombes refrigerants
        BombaRefrigerant b0 = new BombaRefrigerant(variableUniforme, 0);
        BombaRefrigerant b1 = new BombaRefrigerant(variableUniforme, 1);
        BombaRefrigerant b2 = new BombaRefrigerant(variableUniforme, 2);
        BombaRefrigerant b3 = new BombaRefrigerant(variableUniforme, 3);
        
        this.sistemaRefrigeracio.afegirBomba(b0);
        this.sistemaRefrigeracio.afegirBomba(b1);
        this.sistemaRefrigeracio.afegirBomba(b2);
        this.sistemaRefrigeracio.afegirBomba(b3);

        this.sistemaRefrigeracio.desactiva();
    }
    
    /**
     * Actualitza l'economia de la central. Genera una pàgina econòmica a 
     * partir de la demanda de potencia actual. Aquesta pàgina econòmica inclou 
     * beneficis, penalització per excès de potència, costos operatius y 
     * guanys acumulats.
     * @param demandaPotencia Demanda de potència actual.
     */
    private PaginaEconomica actualitzaEconomia(float demandaPotencia){
        float potenciaGenerada = calculaPotencia();
        float beneficis = potenciaGenerada * PREU_UNITAT_POTENCIA;

        float penalitzacio = 0;
        if (potenciaGenerada > demandaPotencia) {
            penalitzacio = PENALITZACIO_EXCES_POTENCIA;
        }

        float costosOperatius = reactor.getCostOperatiu() +
                sistemaRefrigeracio.getCostOperatiu() +
                generadorVapor.getCostOperatiu() +
                turbina.getCostOperatiu();

        guanysAcumulats += beneficis - (penalitzacio + costosOperatius);

        return new PaginaEconomica(
                dia,
                demandaPotencia,
                beneficis,
                penalitzacio,
                potenciaGenerada,
                costosOperatius,
                guanysAcumulats
        );
    }

    /**
     * Aquest mètode ha d’establir la nova temperatura del reactor.
     */
    private void refrigeraReactor() {
        float outputReactor = reactor.calculaOutput(getInsercioBarres());
        float outputRefrigeracio = sistemaRefrigeracio.calculaOutput(outputReactor);
        float novaTemperatura = outputReactor - outputRefrigeracio;

        // La temperatura no pot ser inferior a 25 graus
        if (novaTemperatura < 25.0f) {
            novaTemperatura = 25.0f;
        }

        reactor.setTemperatura(novaTemperatura);
    }

    /**
     * Aquest mètode ha de revisar els components de la central. Si
     * es troben incidències, s'han de registrar en la pàgina d'incidències
     * que es proporciona com a paràmetre d'entrada.
     * @param paginaIncidencies Pàgina d'incidències.
     */
    private void revisaComponents(PaginaIncidencies paginaIncidencies) {
        reactor.revisa(paginaIncidencies);
        sistemaRefrigeracio.revisa(paginaIncidencies);
        generadorVapor.revisa(paginaIncidencies);
        turbina.revisa(paginaIncidencies);
    }

    /**
     *
     * @return el grau d'insercio de les barres
     */
    @Override
    public float getInsercioBarres() {
        return this.insercioBarres;
    }

    /**
     * Estableix el grau d'inserció de les barres de control en percentatge.
     * @param _insercioBarres Percentatge d'inserció de les barres de control.
     * @throws CentralUBException si el grau passat com a parametre es menor que 0 o major que 100
     */
    @Override
    public void setInsercioBarres(float _insercioBarres) throws CentralUBException {
        if (_insercioBarres < 0 || _insercioBarres > 100) {
            throw new CentralUBException("Introdueix un percentatge entre 0 i 100");
        }
        this.insercioBarres = _insercioBarres;
    }

    /**
     * Activa el reactor de la central
     * @throws CentralUBException si la temperatura del reactor supera el 1000 graus
     */
    @Override
    public void activaReactor() throws CentralUBException {
        reactor.activa();
    }


    /**
     * Retorna la temperatura del reactor
     * @throws CentralUBException si la temperatura del reactor supera el 1000 graus
     */
    public float reactorEnServei() throws CentralUBException {
        return reactor.getTemperatura();
    }


    /**
     * Desactiva el reactor
     */
    @Override
    public void desactivaReactor() {
        reactor.desactiva();
    }

    /**
     * Retorna l'objecte que contè el reactor de la central.
     * @return l'objecte que contè el reactor de la central.
     */
    @Override
    public Reactor mostraReactor() {
        return reactor;
    }

    /**
     * Activa una bomba refrigerant amb Id donat com a paràmetre.
     * @param id Identificador de la bomba que es vol activar.
     * @throws CentralUBException si la bomba esta fora de servei
     */
    @Override
    public void activaBomba(int id) throws CentralUBException {
        for(BombaRefrigerant b : sistemaRefrigeracio.bombesRefrigerants){
            if(b.getId() == id){
                b.activa();
            }
        }
    }

    /**
     * Desactiva una bomba refrigerant amb Id donat com a paràmetre.
     * @param id Identificador de la bomba que es vol activar.
     */
    @Override
    public void desactivaBomba(int id) {
        for(BombaRefrigerant b : sistemaRefrigeracio.bombesRefrigerants){
            if(b.getId() == id){
                b.desactiva();
                return;
            }
        }
        System.out.println("No s'ha trobat cap bomba amb aquest id.");
    }

    /**
     * Retorna l'objecte que contè el sistema de refrigeració de la central.
     * @return l'objecte que contè el sistema de refrigeració de la central.
     */
    @Override
    public SistemaRefrigeracio mostraSistemaRefrigeracio() {
        return sistemaRefrigeracio;
    }

    /**
     * Retorna la potència generada per la central. Aquesta potència es l'output de la turbina. Es pot consultar la Figura 2 a l'enunciat per veure els detalls.
     * @return la potència generada per la central.
     */
    @Override
    public float calculaPotencia() {
        return turbina.calculaOutput(generadorVapor.calculaOutput(sistemaRefrigeracio.calculaOutput(reactor.calculaOutput(getInsercioBarres()))));
    }

    /**
     * Retorna els guanys acumulats actuals.
     * @return els guanys acumulats actuals.
     */
    @Override
    public float getGuanysAcumulats() {
        return guanysAcumulats;
    }

    /**
     * Retorna una pàgina de estat per a la configuració actual de la central.
     * @return una pàgina de estat per a la configuració actual de la central.
     */
    @Override
    public PaginaEstat mostraEstat() {
        float outputReactor = reactor.calculaOutput(getInsercioBarres());
        float outputRef = sistemaRefrigeracio.calculaOutput(outputReactor);
        float outputGenerador = generadorVapor.calculaOutput(outputRef);
        float outputTurbina = turbina.calculaOutput(outputGenerador);
        return new PaginaEstat(dia, getInsercioBarres(), outputReactor, outputRef, outputGenerador, outputTurbina);
    }

    /**
     * Retorna la bitacola de la central.
     * @return la bitacola de la central.
     */
    @Override
    public Bitacola mostraBitacola() {
        return bitacola;
    }

    /**
     * Retorna una llista amb totes les pàgines d'incidències de la bitàcola de la central.
     * @return una llista amb totes les pàgines d'incidències de la bitàcola de la central.
     */
    @Override
    public List<PaginaIncidencies> mostraIncidencies() {
        return bitacola.getIncidencies();
    }

    /**
     * Duu a terme les accions relacionades amb la finalització d'un dia. Per això és necessari coneixer la demanda de potència actual per al dia en curs.
     * @param demandaPotencia Demanda de potència actual de la central.
     * @return una Bitacola amb les pagines del dia que acaba
     */
    public Bitacola finalitzaDia(float demandaPotencia) {
        // Actualitza economia
        PaginaEconomica paginaEconomica = actualitzaEconomia(demandaPotencia);
        
        // Genera pàgina d'estat amb la configuració escollida (la nova pàgina
        // d'estat inclou la nova configuració escollida pel operador abans de
        // refrigerar el reactor)
        PaginaEstat paginaEstat = mostraEstat();

        // Actualitza estat de la central...

        // Refrigera el reactor
        refrigeraReactor();

        // Revisa els components de la central i registra incidències
        PaginaIncidencies paginaIncidencies = new PaginaIncidencies(dia);
        revisaComponents(paginaIncidencies);
        
        // Incrementa dia
        dia += 1;
        
        // Guarda pàgines de bitacola
        bitacola.afegeixPagina(paginaEconomica);
        bitacola.afegeixPagina(paginaEstat);
        bitacola.afegeixPagina(paginaIncidencies);
        
        // Retorna pàgines
        Bitacola bitacolaDia = new Bitacola();
        bitacolaDia.afegeixPagina(paginaEconomica);
        bitacolaDia.afegeixPagina(paginaEstat);
        bitacolaDia.afegeixPagina(paginaIncidencies);

        if (reactor.getTemperatura()>1000f) {
            System.out.println("ATENCIÓ: El reactor ha estat desactivat per sobretemperatura!");
        }
        return bitacolaDia;
    }
    /**
     * Retorna el dia actual.
     * @return el dia actual.
     */
    public int getDia(){return dia;}

}
