package com.javiertp.base.mvc;

import com.javiertp.base.Mision;
import com.javiertp.base.NaveEspacial;
import com.javiertp.base.Tripulante;
import com.javiertp.base.enums.Clases;
import com.javiertp.base.enums.Estados;
import com.javiertp.base.enums.Rangos;

import javax.swing.*;
import java.awt.*;

public class Vista extends JFrame {

    JTabbedPane tabbedPane1;
    JPanel panel1;

    //NaveEspacial
    JTextField nombreNavetxt;
    JComboBox claseNaveComboBox;
    JTextField capacidadNaveTxt;
    JButton nuevaNaveBtn;
    JButton modificarNaveBtn;
    JButton eliminarNaveBtn;

    //Tripulante
    JTextField nombreTripulanteTxt;
    JComboBox rangoTripulanteComboBox;
    JComboBox naveTripulanteComboBox;
    JButton nuevoTripulanteBtn;
    JButton modificarTripulanteBtn;
    JButton eliminarTripulanteBtn;

    //Mision
    JTextField descripcionMisionTxt;
    JComboBox naveMisionComboBox;
    JComboBox estadoMisionComboBox;
    JButton nuevaMisionBtn;
    JButton modificarMisionBtn;
    JButton eliminarMisionBtn;

    JList<NaveEspacial> listNaves;
    JList<Tripulante> listTripulantes;
    JList<Mision> listMisiones;

    // Botones
    JButton informe1Btn;
    JButton informe2btn;
    JButton informe3btn;
    JButton informe4btn;
    JButton informe5btn;
    JButton informe6btn;
    JButton grafico1btn;
    JButton grafico2btn;

    DefaultListModel<NaveEspacial> dlmNaves;
    DefaultListModel<Tripulante> dlmTripulantes;
    DefaultListModel<Mision> dlmMisiones;

    DefaultComboBoxModel<Clases> dcbClases;
    DefaultComboBoxModel<Rangos> dcbRangos;
    DefaultComboBoxModel<Estados> dcbEstados;
    DefaultComboBoxModel<NaveEspacial> dcbNaveTripulante;
    DefaultComboBoxModel<NaveEspacial> dcbNaveMision;

    JMenuItem conexionItem;
    JMenuItem salirItem;
    JMenuItem modoOscuroItem;
    JMenuItem ayudaItem;

    public Vista() {
        this.setTitle("App de flota espacial");
        ImageIcon icon = new ImageIcon("logo.png");
        this.setIconImage(icon.getImage());
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setSize(new Dimension(600,450));
        crearMenu();
        iniciarListasYComboBox();
        setEnumComboBox();
    }

    private void iniciarListasYComboBox() {
        dlmNaves = new DefaultListModel<>();
        listNaves.setModel(dlmNaves);

        dlmMisiones = new DefaultListModel<>();
        listMisiones.setModel(dlmMisiones);

        dlmTripulantes = new DefaultListModel<>();
        listTripulantes.setModel(dlmTripulantes);

        dcbClases = new DefaultComboBoxModel<>();
        claseNaveComboBox.setModel(dcbClases);

        dcbRangos = new DefaultComboBoxModel<>();
        rangoTripulanteComboBox.setModel(dcbRangos);

        dcbEstados = new DefaultComboBoxModel<>();
        estadoMisionComboBox.setModel(dcbEstados);

        dcbNaveTripulante = new DefaultComboBoxModel<>();
        naveTripulanteComboBox.setModel(dcbNaveTripulante);

        dcbNaveMision = new DefaultComboBoxModel<>();
        naveMisionComboBox.setModel(dcbNaveMision);

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    private void setEnumComboBox() {

        for (Rangos rango: Rangos.values()) {
            rangoTripulanteComboBox.addItem(rango.getValue());
        }

        rangoTripulanteComboBox.setSelectedIndex(-1);

        for (Clases clase: Clases.values()) {
            claseNaveComboBox.addItem(clase.getValue());
        }

        claseNaveComboBox.setSelectedIndex(-1);

        for (Estados estado: Estados.values()) {
            estadoMisionComboBox.addItem(estado.getValue());
        }

        estadoMisionComboBox.setSelectedIndex(-1);
    }

    private void crearMenu() {
        JMenuBar barra = new JMenuBar();
        JMenu menu = new JMenu("Archivo");

        conexionItem = new JMenuItem("Conectar");
        conexionItem.setActionCommand("Conectar");

        modoOscuroItem = new JMenuItem("Modo Oscuro");
        modoOscuroItem.setActionCommand("Modo Oscuro");

        ayudaItem = new JMenuItem("Ayuda");
        ayudaItem.setActionCommand("Ayuda");

        salirItem = new JMenuItem("Salir");
        salirItem.setActionCommand("Salir");

        menu.add(conexionItem);
        menu.add(ayudaItem);
        menu.add(modoOscuroItem);
        menu.add(salirItem);
        barra.add(menu);
        this.setJMenuBar(barra);
    }
}
