/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.vista;
import prog2.model.VariableNormal;
import prog2.adaptador.Adaptador;
import java.util.Scanner;

/**
 *
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

    private enum opcionsMenuPrincipal {BARRES_CONTROL, REACTOR, SISTEMA_REFREIGERACIO, ESTAT_CENTRAL, BITACOLA, INCIDENCIES, DEMANDA_SATISFETA_ACTUAL, FIN_DIA, GUARDAR_DADES, CARREGA_DADES, SORTIR}
    private enum opcionsMenuBarresControl {OBTENIR_INSERCIO, ESTABLIR_INSERCIO, SORTIR}
    private enum opcionsMenuReactor {ACTIVAR, DESACTIVAR, ESTAT, SORTIR}
    private enum opcionsMenuSistemaRefrigeracio {ACTIVAR_TOTES, DESACTIVAR_TOTES, ACTIVAR_UNA, DESACTIVAR_UNA, ESTAT, SORTIR}

    private String[] opcionsMenuPrincipalS = {"Gestió Barres de Control", "Gestió Reactor", "Gestió Sistema de Refrigeració", "Mostrar Estat Central", "Mostrar Bitàcola", "Mostrar Incidències", "Obtenir Demanda Satisfeta amb Configuració Actual", "Finalitzar Dia", "Guardar Dades", "Carrega Dades", "Sortir"};
    private String[] opcionsMenuBarresControlS = {"Obtenir Inserció Barres", "Establir Inserció Barres", "Sortir"};
    private String[] opcionsMenuReactorS = {"Activar Reactor", "Desactivar Reactor", "Mostrar Estat", "Sortir"};
    private String[] opcionsMenuSistemaRefrigeracioS = {"Activar Totes les Bombes", "Desactivar Totes les Bombes", "Activar Bomba", "Desactivar Bomba", "Mostrar Estar", "Sortir"};

    /* Constructor*/
    public CentralUB() throws CentralUBException {
        variableNormal = new VariableNormal(VAR_NORM_MEAN, VAR_NORM_STD, VAR_NORM_SEED);
        demandaPotencia = generaDemandaPotencia();

        // Afegir codi adicional si fos necessari:
        adaptador = new Adaptador();
    }
    
    public void gestioCentralUB() {
        // Mostrar missatge inicial
        System.out.println("Benvingut a la planta PWR de la UB");
        System.out.println("La demanda de potència elèctrica avui es de " + demandaPotencia + " unitats");

        // Completar
        
    }

    public void gestioMenuPrincipal(Scanner sc){
        Menu menuPrincipal = new Menu<>("Menú Principal", opcionsMenuPrincipal.values());
        menuPrincipal.setDescripcions(opcionsMenuPrincipalS);
        int opcio;

        do{
            menuPrincipal.mostrarMenu();
            opcio = sc.nextInt();

            switch(opcio){
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
                    //Mostrar estat central
                    break;
                case 5:
                    //Mostrar bitacola
                    break;
                case 6:
                    //Mostrar incidencies
                    break;
                case 7:
                    //Obtenir demanda satis amb conf actual
                    break;
                case 8:
                    //Finalitzar dia
                    break;
                case 9:
                    //Guardar dades
                    break;
                case 10:
                    //Carregar dades
                    break;
                case 11:
                    System.out.println("Adeu");
                    break;
                default:
                    System.out.println("Aquesta entrada no és vàlida. Introdueix un nombre entre el 1 i el 11.");
            }

        }while(opcio != 11);


    }

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
                    adaptador.getInsercioBarres();
                    break;
                case 2:
                    System.out.println("Quin percentage vols inserir?");
                    percentatge = sc.nextFloat();
                    try {
                        adaptador.setInsercioBarres(percentatge);
                    }catch(CentralUBException e){
                        e.getMessage();
                    }
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Aquesta entrada no és vàlida. Introdueix un nombre entre el 1 i el 3.");
            }
        }while(opcio != 3);
    }

    private void gestioMenuReactor(Scanner sc){
        Menu menuReactor = new Menu<>("Gestió Reactor", opcionsMenuReactor.values());
        menuReactor.setDescripcions(opcionsMenuReactorS);
        int opcio;

        do{
            menuReactor.mostrarMenu();

            opcio = sc.nextInt();

            switch(opcio){
                case 1:
                    //Activar reactor
                    break;
                case 2:
                    //Desactivar reactor
                    break;
                case 3:
                    //Mostrar estat
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Aquesta entrada no és vàlida. Introdueix un nombre entre el 1 i el 4.");
            }
        }while(opcio != 4);
    }

    private void gestioSistemaRefrigeracio(Scanner sc){
        Menu menuSistemaRefrigeracio = new Menu<>("Gestió Sistema Refrigeració", opcionsMenuSistemaRefrigeracio.values());
        menuSistemaRefrigeracio.setDescripcions(opcionsMenuSistemaRefrigeracioS);
        int opcio;

        do{
            menuSistemaRefrigeracio.mostrarMenu();

            opcio = sc.nextInt();

            switch(opcio){
                case 1:
                    //Activar totes les bombes
                    break;
                case 2:
                    //Desactivar totes les bombes
                    break;
                case 3:
                    //Activar bomba
                    break;
                case 4:
                    //Desactivar bomba
                    break;
                case 5:
                    //Mostrar estat
                case 6:
                    break;
                default:
                    System.out.println("Aquesta entrada no és vàlida. Introdueix un nombre entre el 1 i el 6.");
            }
        }while(opcio != 6);
    }

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
