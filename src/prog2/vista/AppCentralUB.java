package prog2.vista;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import prog2.adaptador.Adaptador;
import prog2.model.VariableNormal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppCentralUB extends JFrame {
    private Adaptador adaptador;
    private int dia = 1;
    private float demandaPotencia;
    private float guanysAcumulats;
    public final static float DEMANDA_MAX = 1800;
    public final static float DEMANDA_MIN = 250;
    public final static float VAR_NORM_MEAN = 1000;
    public final static float VAR_NORM_STD = 800;
    public final static long VAR_NORM_SEED = 123;
    private VariableNormal variableNormal;


    private JPanel panel1;
    private JButton btnGestioComponentsCentral;
    private JButton btnVisualitzarInformacio;
    private JLabel lblDia;
    private JLabel lblDemanda;
    private JLabel lblGuanys;
    private JButton btnFinalitzaDia;
    private JPanel panelIzq;
    private JPanel panelInfo;
    private JLabel logoCentralUB;
    private JLabel iconoEngranaje;
    private JPanel panelBotons;


    public AppCentralUB() throws CentralUBException {
        this.adaptador = new Adaptador();
        variableNormal = new VariableNormal(VAR_NORM_MEAN, VAR_NORM_STD, VAR_NORM_SEED);
        demandaPotencia = generaDemandaPotencia();
        $$$setupUI$$$(); // Afegeix-ho si no hi és explícitament

        setTitle("Central UB");
        setSize(770, 572);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(panel1);

        // Inicialitza les etiquetes amb valors actuals
        lblDia.setText("Dia: " + this.dia);
        lblGuanys.setText("Guanys: " + guanysAcumulats);
        lblDemanda.setText("Demanda: " + demandaPotencia);

        JMenu menuArxiu = new JMenu("Arxiu");
        JMenuItem menuArxiuGuardar = new JMenuItem("Guardar", 'S');
        JMenuItem menuArxiuCarregar = new JMenuItem("Carregar", 'C');

        menuArxiu.add(menuArxiuGuardar);
        menuArxiuGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmGuardarDades dialog = new FrmGuardarDades(AppCentralUB.this, adaptador);
                dialog.setVisible(true);
            }
        });

        menuArxiu.add(menuArxiuCarregar);
        menuArxiuCarregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmCarregarDades dialog = new FrmCarregarDades(AppCentralUB.this, adaptador);
                dialog.setVisible(true);
            }
        });

        JMenuBar barraMenu = new JMenuBar();
        barraMenu.add(menuArxiu);
        this.getContentPane().add(barraMenu, BorderLayout.NORTH);

        logoCentralUB.setText(null);
        logoCentralUB.setIcon(new ImageIcon("src/Resources/centralub_logo_pequena.png"));

        // Botó per obrir la finestra de gestió de components
        btnGestioComponentsCentral.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmGestioComponentsCentral dialog = new FrmGestioComponentsCentral(AppCentralUB.this, adaptador);
                dialog.setVisible(true);
            }
        });
        btnVisualitzarInformacio.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                FrmVisualitzarInformacio dialog = new FrmVisualitzarInformacio(AppCentralUB.this, adaptador);
                dialog.setVisible(true);
            }
        });
        btnFinalitzaDia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(AppCentralUB.this, adaptador.finalitzaDia(demandaPotencia), "Bitacola del dia:", JOptionPane.INFORMATION_MESSAGE);
                if (reactorIncidencia()) {
                    JOptionPane.showMessageDialog(null,
                            "Atenció: El reactor ha quedat fora de servei!",
                            "Incidència detectada",
                            JOptionPane.WARNING_MESSAGE);
                }

                demandaPotencia = generaDemandaPotencia();
                dia +=1;
                actualitzaLables();
            }
        });
    }
    public void actualitzaLables() {
        guanysAcumulats +=obtenirGuanysDesdeBitacola();
        lblDia.setText("Dia: " + this.dia);
        lblDemanda.setText("Demanda: " + generaDemandaPotencia());
        lblGuanys.setText("Guanys: " + guanysAcumulats);
    }

    private boolean reactorIncidencia() {
        String incidencia = adaptador.mostraIncidencies();
        String[] linies = incidencia.split("\n");

        for (int i = linies.length - 1; i >= 0; i--) {
            String linia = linies[i].trim().toLowerCase();
            if (linia.contains("el reactor ha quedat fora de servei")) {
                return true;
            }
        }
        return false;
    }

    private float obtenirGuanysDesdeBitacola(){
        String bitacola = adaptador.mostraBitacola();
        String[] linies = bitacola.split("\n");

        for (int i = linies.length - 1; i >= 0; i--) {
            String linia = linies[i].trim();
            if(linia.toLowerCase().contains("guanys acumulats")){
                try{
                    String[] parts = linia.split(":");
                    if(parts.length > 1){
                        String valor = parts[1].trim().split(" ")[0];
                        return Float.parseFloat(valor);
                    }
                } catch (Exception e){
                    System.err.println("Error analitzant els guanys acumulats");
                }
            }
        }
        return 0f;
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

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(10, 10, 10, 10), -1, -1));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(5, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        btnGestioComponentsCentral = new JButton();
        btnGestioComponentsCentral.setText("Gestionar Central");
        panel2.add(btnGestioComponentsCentral, new GridConstraints(0, 0, 5, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, 60), new Dimension(600, 60), 0, false));
        lblDia = new JLabel();
        lblDia.setText("Dia: " + this.dia);
        panel2.add(lblDia, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblDemanda = new JLabel();
        lblDemanda.setText("Demanda: ");
        panel2.add(lblDemanda, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblGuanys = new JLabel();
        lblGuanys.setText("Guanys:");
        panel2.add(lblGuanys, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel2.add(spacer1, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        btnFinalitzaDia = new JButton();
        btnFinalitzaDia.setText("Finalitza Dia");
        panel2.add(btnFinalitzaDia, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }


}
