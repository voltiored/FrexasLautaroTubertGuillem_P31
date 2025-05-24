package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FrmCarregarDades extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textCami;
    private JButton btnTriarArxiu;

    public FrmCarregarDades(Frame parent, Adaptador adaptador) {

        super(parent, "Carregar arxiu", true);
        setContentPane(contentPane);
        setSize(500, 200);
        setResizable(false);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getRootPane().setDefaultButton(buttonOK);
        setLocationRelativeTo(parent);

        btnTriarArxiu.setText(null);
        btnTriarArxiu.setIcon(new ImageIcon((new ImageIcon("src/Resources/Generic_Folder.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));

        btnTriarArxiu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File fitxer;
                JFileChooser seleccio = new JFileChooser();
                int resultat = seleccio.showOpenDialog(FrmCarregarDades.this);
                if (resultat == JFileChooser.APPROVE_OPTION) {
                    fitxer=seleccio.getSelectedFile();
                    textCami.setText(fitxer.toString());
                }
            }
        });
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    adaptador.carregaDades(textCami.getText());
                    dispose();
                } catch (CentralUBException ex) {
                    JOptionPane.showMessageDialog(FrmCarregarDades.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonCancel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        });
    }
}
