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
 *
 * @author Daniel Ortiz
 */
public class Dades implements InDades, Serializable {
    public final static long  VAR_UNIF_SEED = 123;
    public final static float GUANYS_INICIALS = 0;
    public final static float PREU_UNITAT_POTENCIA = 1;
    public final static float PENALITZACIO_EXCES_POTENCIA = 250;
    // Afegir atributs:
    private VariableUniforme variableUniforme;
    private float insercioBarres;
    private Reactor reactor;
    private SistemaRefrigeracio sistemaRefrigeracio;
    private GeneradorVapor generadorVapor;
    private Turbina turbina;
    private Bitacola bitacola;
    private int dia = 1;
    private float guanysAcumulats = 0f;

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

        guanysAcumulats = GUANYS_INICIALS + beneficis - penalitzacio - costosOperatius;

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
     * Aquest mètode ha de establir la nova temperatura del reactor.
     */
    private void refrigeraReactor() {
          reactor.setTemperatura(Math.min(25f, reactor.calculaOutput(getInsercioBarres())-sistemaRefrigeracio.calculaOutput(reactor.calculaOutput(getInsercioBarres()))));
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

    @Override
    public float getInsercioBarres() {
        return insercioBarres;
    }

    @Override
    public void setInsercioBarres(float _insercioBarres) throws CentralUBException {
        if (_insercioBarres < 0 || _insercioBarres > 100) {
            throw new CentralUBException("Introdueix un percentatge entre 0 i 100");
        }
        insercioBarres = _insercioBarres;
    }

    @Override
    public void activaReactor() throws CentralUBException {
        reactor.activa();
    }

    @Override
    public void desactivaReactor() {
        reactor.desactiva();
    }

    @Override
    public Reactor mostraReactor() {
        return reactor;
    }

    @Override
    public void activaBomba(int id) throws CentralUBException {
        for(BombaRefrigerant b : sistemaRefrigeracio.bombesRefrigerants){
            if(b.getId() == id){
                b.activa();
            }
        }
    }

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

    @Override
    public SistemaRefrigeracio mostraSistemaRefrigeracio() {
        return sistemaRefrigeracio;
    }

    @Override
    public float calculaPotencia() {
        return turbina.calculaOutput(generadorVapor.calculaOutput(sistemaRefrigeracio.calculaOutput(reactor.calculaOutput(getInsercioBarres()))));
    }

    @Override
    public float getGuanysAcumulats() {
        return guanysAcumulats;
    }

    @Override
    public PaginaEstat mostraEstat() {
        return new PaginaEstat(dia, getInsercioBarres(),
                reactor.calculaOutput(getInsercioBarres()),
                sistemaRefrigeracio.calculaOutput(reactor.calculaOutput(getInsercioBarres())),
                generadorVapor.calculaOutput(sistemaRefrigeracio.calculaOutput(reactor.calculaOutput(getInsercioBarres()))),
                turbina.calculaOutput(generadorVapor.calculaOutput(sistemaRefrigeracio.calculaOutput(reactor.calculaOutput(getInsercioBarres())))));
    }

    @Override
    public Bitacola mostraBitacola() {
        return bitacola;
    }

    @Override
    public List<PaginaIncidencies> mostraIncidencies() {
        return bitacola.getIncidencies();
    }

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
        return bitacolaDia;
    }
}
