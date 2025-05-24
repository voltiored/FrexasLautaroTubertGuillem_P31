package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class FrmVisualitzarInformacio extends JDialog {
    private Adaptador adaptador;
    private JComboBox cmboxOpcionsVisualitzar;
    private JButton btnVisualitzar;
    private JTextArea textVisualitzar;
    private JPanel contentPane;
    private JScrollPane scroller;

    public FrmVisualitzarInformacio(Frame parent, Adaptador adaptador){
        super(parent, "Visualitzar Informació de la Central", true);
        this.adaptador = adaptador;

        setContentPane(contentPane);
        setModal(true);
        setSize(800, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);


        btnVisualitzar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (Objects.equals(cmboxOpcionsVisualitzar.getSelectedItem(), "Estat de la central")){
                    textVisualitzar.setText(adaptador.mostraEstat());
                }
                if (Objects.equals(cmboxOpcionsVisualitzar.getSelectedItem(), "Quadern de bitàcola")){
                    textVisualitzar.setText(adaptador.mostraBitacola());
                }
                if (Objects.equals(cmboxOpcionsVisualitzar.getSelectedItem(), "Incidències")){
                    textVisualitzar.setText(adaptador.mostraIncidencies());
                }
            }
        });
    }


}
