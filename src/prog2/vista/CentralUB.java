/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.vista;
import prog2.model.VariableNormal;
import prog2.adaptador.Adaptador;
import java.util.Scanner;

/**
 * Classe que conte els diferents menus del programa
 * @author Daniel Ortiz
 */
public class CentralUB {
    public final static float DEMANDA_MAX = 1800;
    public final static float DEMANDA_MIN = 250;
    public final static float VAR_NORM_MEAN = 1000;
    public final static float VAR_NORM_STD = 800;
    public final static long VAR_NORM_SEED = 123;
    
    /** Generador aleatori de la demanda de potència **/
    private VariableNormal variableNormal;
    
    /** Demanda de potència del dia actual **/
    private float demandaPotencia;

    /**
     * Objecte de la classe adaptador que serveix per accedir a les funcions de model
     */
    Adaptador adaptador;

    /**
     * Les opcions del menu principal
     */
    private enum opcionsMenuPrincipal {BARRES_CONTROL, REACTOR, SISTEMA_REFREIGERACIO, ESTAT_CENTRAL, BITACOLA, INCIDENCIES, DEMANDA_SATISFETA_ACTUAL, FIN_DIA, GUARDAR_DADES, CARREGA_DADES, SORTIR}

    /**
     * Les opcions del menu de barres de control
     */
    private enum opcionsMenuBarresControl {OBTENIR_INSERCIO, ESTABLIR_INSERCIO, SORTIR}

    /**
     * Les opcions del menu del reactor
     */
    private enum opcionsMenuReactor {ACTIVAR, DESACTIVAR, ESTAT, SORTIR}

    /**
     * Les opcions del menu del sistema de refrigeracio
     */
    private enum opcionsMenuSistemaRefrigeracio {ACTIVAR_TOTES, DESACTIVAR_TOTES, ACTIVAR_UNA, DESACTIVAR_UNA, ESTAT, SORTIR}

    /**
     * Les opcions del menu principal en string
     */
    private String[] opcionsMenuPrincipalS = {"Gestió Barres de Control", "Gestió Reactor", "Gestió Sistema de Refrigeració", "Mostrar Estat Central", "Mostrar Bitàcola", "Mostrar Incidències", "Obtenir Demanda Satisfeta amb Configuració Actual", "Finalitzar Dia", "Guardar Dades", "Carrega Dades", "Sortir"};
    /**
     * Les opcions del menu de barres de control en string
     */
    private String[] opcionsMenuBarresControlS = {"Obtenir Inserció Barres", "Establir Inserció Barres", "Sortir"};
    /**
     * Les opcions del menu del reactor en string
     */
    private String[] opcionsMenuReactorS = {"Activar Reactor", "Desactivar Reactor", "Mostrar Estat", "Sortir"};
    /**
     * Les opcions del menu del sistema de refrigeracio en string
     */
    private String[] opcionsMenuSistemaRefrigeracioS = {"Activar Totes les Bombes", "Desactivar Totes les Bombes", "Activar Bomba", "Desactivar Bomba", "Mostrar Estar", "Sortir"};

    /**
     *  Constructor
     *  */
    public CentralUB() throws CentralUBException {
        variableNormal = new VariableNormal(VAR_NORM_MEAN, VAR_NORM_STD, VAR_NORM_SEED);
        demandaPotencia = generaDemandaPotencia();

        // Afegir codi adicional si fos necessari:
        adaptador = new Adaptador();
    }

    /**
     * Menu principal de l'aplicacio
     */
    public void gestioCentralUB() {
        // Mostrar missatge inicial
        System.out.println("Benvingut a la planta PWR de la UB");
        System.out.println("La demanda de potència elèctrica avui es de " + demandaPotencia + " unitats");

        // Completar
        Scanner sc = new Scanner(System.in);
        Menu menuPrincipal = new Menu<>("Menú Principal", opcionsMenuPrincipal.values());
        menuPrincipal.setDescripcions(opcionsMenuPrincipalS);
        int opcio;
        String cami;

        do{
            menuPrincipal.mostrarMenu();
            opcio = sc.nextInt();
            sc.nextLine();
            try {
                switch (opcio) {
                    case 1:
                        gestioMenuBarresControl(sc);
                        break;
                    case 2:
                        gestioMenuReactor(sc);
                        break;
                    case 3:
                        gestioSistemaRefrigeracio(sc);
                        break;
                    case 4:
                        adaptador.mostraEstat();
                        break;
                    case 5:
                        adaptador.mostraBitacola();
                        break;
                    case 6:
                        adaptador.mostraIncidencies();
                        break;
                    case 7:
                        float demandaSatisfeta = adaptador.calculaPotencia();

                        System.out.println("La demanda de potència del dia és de: " + demandaPotencia + " unitats de potencia." +
                                "\nLa potència generada amb la configuració actual és de: " + demandaSatisfeta + " unitats de potència." +
                                "\nEl percentatge de la demanda satisfeta és de: " + demandaSatisfeta / demandaPotencia * 100 + "%.");
                        break;
                    case 8:
                        finalitzaDia();
                        break;
                    case 9:
                        System.out.println("Introdueix el camí per a guardar les dades.");
                        cami = sc.nextLine();
                        adaptador.guardaDades(cami);
                        break;
                    case 10:
                        System.out.println("Introdueix el camí per a carregar les dades.");
                        cami = sc.nextLine();
                        adaptador.carregaDades(cami);
                        break;
                    case 11:
                        System.out.println("Adeu");
                        break;
                    default:
                        System.out.println("Aquesta entrada no és vàlida. Introdueix un nombre entre el 1 i el 11.");
                }
            }catch(CentralUBException e){
                System.out.println(e.getMessage());
            }

        }while(opcio != 11);


    }

    /**
     * Submenu de les barres de control
     * @param sc objecte de tipus scanner
     */
    private void gestioMenuBarresControl(Scanner sc){
        Menu menuBarresControl = new Menu<>("Gestió Barres de Control", opcionsMenuBarresControl.values());
        menuBarresControl.setDescripcions(opcionsMenuBarresControlS);
        int opcio;
        float percentatge;

        do{
            menuBarresControl.mostrarMenu();

            opcio = sc.nextInt();

            switch(opcio){
                case 1:
                    System.out.println("L'inserció de les barres és de " + adaptador.getInsercioBarres() + "%.");
                    break;
                case 2:
                    System.out.println("Quin percentage vols inserir?");
                    percentatge = sc.nextFloat();
                    try {
                        adaptador.setInsercioBarres(percentatge);
                    }catch(CentralUBException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Aquesta entrada no és vàlida. Introdueix un nombre entre el 1 i el 3.");
            }
        }while(opcio != 3);
    }

    /**
     * Submenu del reactor
     * @param sc objecte del tipus Scanner
     */
    private void gestioMenuReactor(Scanner sc){
        Menu menuReactor = new Menu<>("Gestió Reactor", opcionsMenuReactor.values());
        menuReactor.setDescripcions(opcionsMenuReactorS);
        int opcio;

        do{
            menuReactor.mostrarMenu();

            opcio = sc.nextInt();
            try {
                switch (opcio) {
                    case 1:
                        adaptador.activaReactor();
                        break;
                    case 2:
                        adaptador.desactivaReactor();
                        break;
                    case 3:
                        System.out.println(adaptador.mostraReactor());
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("Aquesta entrada no és vàlida. Introdueix un nombre entre el 1 i el 4.");
                }
            }catch(CentralUBException e){
                System.out.println(e.getMessage());
            }
        }while(opcio != 4);
    }

    /**
     * Submenu del sistema de refrigeracio
     * @param sc objecte de tipus scanner
     */
    private void gestioSistemaRefrigeracio(Scanner sc){
        Menu menuSistemaRefrigeracio = new Menu<>("Gestió Sistema Refrigeració", opcionsMenuSistemaRefrigeracio.values());
        menuSistemaRefrigeracio.setDescripcions(opcionsMenuSistemaRefrigeracioS);
        int opcio;
        int idBomba;
        do{
            menuSistemaRefrigeracio.mostrarMenu();

            opcio = sc.nextInt();
        try {

            switch (opcio) {
                case 1:
                    adaptador.activaRefrigeracio();
                    break;
                case 2:
                    adaptador.desactivaRefrigeracio();
                    break;
                case 3:
                    System.out.println("Introdueix l'id de la bomba que vols activar.");
                    idBomba = sc.nextInt();
                    adaptador.activaBomba(idBomba);
                    break;
                case 4:
                    System.out.println("Introdueix l'id de la bomba que vols desactivar.");
                    idBomba = sc.nextInt();
                    adaptador.desactivaBomba(idBomba);
                    break;
                case 5:
                    adaptador.mostrarSistemaRefrigeracio();
                case 6:
                    break;
                default:
                    System.out.println("Aquesta entrada no és vàlida. Introdueix un nombre entre el 1 i el 6.");
            }
        }catch(CentralUBException e){
            System.out.println(e.getMessage());
        }
        }while(opcio != 6);
    }

    /**
     * Metode que genera la demanda de potencia del dia actual
     * @return la demanda de potencia del dia actual
     */
    private float generaDemandaPotencia(){
        float valor = Math.round(variableNormal.seguentValor());
        if (valor > DEMANDA_MAX)
            return DEMANDA_MAX;
        else
            if (valor < DEMANDA_MIN)
                return DEMANDA_MIN;
            else
                return valor;
    }

    /**
     * Metode que finalitza el dia
     */
    private void finalitzaDia() {
        // Finalitzar dia i imprimir informacio de la central
        String info = new String();
        info = adaptador.finalitzaDia(demandaPotencia);
        System.out.println(info);
        System.out.println("Dia finalitzat\n");
        
        // Generar i mostrar nova demanda de potencia
        demandaPotencia = generaDemandaPotencia();
        System.out.println("La demanda de potència elèctrica avui es de " + demandaPotencia + " unitats");
    }
}
