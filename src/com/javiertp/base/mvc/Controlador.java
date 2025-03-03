package com.javiertp.base.mvc;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.javiertp.base.Mision;
import com.javiertp.base.NaveEspacial;
import com.javiertp.base.Tripulante;
import jasper.ReportGenerator;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import util.HibernateUtil;
import util.Util;

public class Controlador extends WindowAdapter implements ActionListener, ListSelectionListener, WindowListener {

    private Vista vista;
    private Modelo modelo;
    boolean conectado = false;
    boolean modoOscuro = false;

    public Controlador(Vista vista, Modelo modelo) {

        this.vista = vista;
        this.modelo = modelo;

        addActionListener(this);
        addListSelecctionListener(this);
        vista.addWindowListener(this);

        addMouseListeners(vista);

        try {
            if (conectado) {
                refrescarSeccionNaves();
                refrescarSeccionTripulantes();
                refrescarSeccionMisiones();
            }
        }catch (Exception e){
            System.out.println("Inicando el programa");
        }
    }

    private static void addMouseListeners(Vista vista) {
        vista.listNaves.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int index = vista.listNaves.locationToIndex(e.getPoint());
                if (index >= 0) {
                    Object info = vista.listNaves.getModel().getElementAt(index);
                    vista.listNaves.setToolTipText(info.toString());
                }
            }
        });

        vista.listTripulantes.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int index = vista.listTripulantes.locationToIndex(e.getPoint());
                if (index >= 0) {
                    Object info = vista.listTripulantes.getModel().getElementAt(index);
                    vista.listTripulantes.setToolTipText(info.toString());
                }
            }
        });

        vista.listMisiones.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int index = vista.listMisiones.locationToIndex(e.getPoint());
                if (index >= 0) {
                    Object info = vista.listMisiones.getModel().getElementAt(index);
                    vista.listMisiones.setToolTipText(info.toString());
                }
            }
        });
    }

    private void addActionListener(ActionListener listener){
        vista.nuevaNaveBtn.addActionListener(listener);
        vista.nuevaNaveBtn.setActionCommand("NuevaNave");
        vista.modificarNaveBtn.addActionListener(listener);
        vista.modificarNaveBtn.setActionCommand("ModificarNave");
        vista.eliminarNaveBtn.addActionListener(listener);
        vista.eliminarNaveBtn.setActionCommand("EliminarNave");
        vista.nuevoTripulanteBtn.addActionListener(listener);
        vista.nuevoTripulanteBtn.setActionCommand("NuevoTripulante");
        vista.modificarTripulanteBtn.addActionListener(listener);
        vista.modificarTripulanteBtn.setActionCommand("ModificarTripulante");
        vista.eliminarTripulanteBtn.addActionListener(listener);
        vista.eliminarTripulanteBtn.setActionCommand("EliminarTripulante");
        vista.nuevaMisionBtn.addActionListener(listener);
        vista.nuevaMisionBtn.setActionCommand("NuevaMision");
        vista.modificarMisionBtn.addActionListener(listener);
        vista.modificarMisionBtn.setActionCommand("ModificarMision");
        vista.eliminarMisionBtn.addActionListener(listener);
        vista.eliminarMisionBtn.setActionCommand("EliminarMision");
        vista.conexionItem.addActionListener(listener);
        vista.salirItem.addActionListener(listener);
        vista.modoOscuroItem.addActionListener(listener);
        vista.ayudaItem.addActionListener(listener);

       vista.informe1Btn.addActionListener(listener);
       vista.informe1Btn.setActionCommand("Informe1");
       vista.informe2btn.addActionListener(listener);
       vista.informe2btn.setActionCommand("Informe2");
       vista.informe3btn.addActionListener(listener);
       vista.informe3btn.setActionCommand("Informe3");
       vista.informe4btn.addActionListener(listener);
       vista.informe4btn.setActionCommand("Informe4");
       vista.informe5btn.addActionListener(listener);
       vista.informe5btn.setActionCommand("Informe5");
       vista.informe6btn.addActionListener(listener);
       vista.informe6btn.setActionCommand("Informe6");
       vista.grafico1btn.addActionListener(listener);
       vista.grafico1btn.setActionCommand("Grafico1");
       vista.grafico2btn.addActionListener(listener);
       vista.grafico2btn.setActionCommand("Grafico2");
       vista.ayudaInformesBtn.addActionListener(listener);
       vista.ayudaInformesBtn.setActionCommand("AyudaInformes");
       
    }

    private void addListSelecctionListener(ListSelectionListener listener){
        vista.listNaves.addListSelectionListener(listener);
        vista.listTripulantes.addListSelectionListener(listener);
        vista.listMisiones.addListSelectionListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String comando = actionEvent.getActionCommand();
        try {

            switch(comando){
                case "Conectar":{
                    modelo.conectar();
                    conectado = true;
                }
                break;
                case "Salir":{
                    int resp = Util.showConfirmDialog("¿Desea salir de la aplicación?", "Salir");
                    if (resp == JOptionPane.OK_OPTION) {
                        modelo.desconectar();
                        System.exit(0);
                    }
                }
                break;
                case "NuevaNave":
                {
                    NaveEspacial nave = new NaveEspacial(vista.nombreNavetxt.getText(), vista.claseNaveComboBox.getSelectedItem().toString(),
                            Integer.parseInt(vista.capacidadNaveTxt.getText()));
                    modelo.guardarNaveEspacial(nave);
                }
                break;
                case "ModificarNave": {
                    NaveEspacial naveSeleccionada = vista.listNaves.getSelectedValue();
                    if (naveSeleccionada != null) {
                        naveSeleccionada.setNombre(vista.nombreNavetxt.getText());
                        naveSeleccionada.setClase(vista.claseNaveComboBox.getSelectedItem().toString());
                        naveSeleccionada.setCapacidad(Integer.parseInt(vista.capacidadNaveTxt.getText()));
                        modelo.modificarNaveEspacial(naveSeleccionada);
                    } else {
                        JOptionPane.showMessageDialog(vista, "Seleccione una nave para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
                case "EliminarNave":{
                    int resp = Util.showConfirmDialog("¿Desea eliminar la nave?", "Eliminar Nave Espacial");
                    if (resp == JOptionPane.OK_OPTION) {
                        NaveEspacial nave = vista.listNaves.getSelectedValue();
                        modelo.eliminarNaveEspacial(nave);
                    }
                }
                break;
                case "NuevoTripulante":
                {
                    Tripulante tripulante = new Tripulante(vista.nombreTripulanteTxt.getText(), (String) vista.rangoTripulanteComboBox.getSelectedItem(),
                            (NaveEspacial) vista.naveTripulanteComboBox.getSelectedItem());
                    modelo.guardarTripulante(tripulante);
                }
                break;
                case "ModificarTripulante": {
                    Tripulante tripulanteSeleccionado = vista.listTripulantes.getSelectedValue();
                    if (tripulanteSeleccionado != null) {
                        tripulanteSeleccionado.setNombre(vista.nombreTripulanteTxt.getText());
                        tripulanteSeleccionado.setRango((String) vista.rangoTripulanteComboBox.getSelectedItem());
                        tripulanteSeleccionado.setNave((NaveEspacial) vista.naveTripulanteComboBox.getSelectedItem());
                        modelo.modificarTripulante(tripulanteSeleccionado);
                    } else {
                        JOptionPane.showMessageDialog(vista, "Seleccione un tripulante para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
                case "EliminarTripulante":{
                    int resp = Util.showConfirmDialog("¿Desea eliminar el tripulante?", "Eliminar Tripulante");
                    if (resp == JOptionPane.OK_OPTION) {
                        Tripulante tripulante = vista.listTripulantes.getSelectedValue();
                        modelo.eliminarTripulante(tripulante);
                    }
                }
                break;
                case "NuevaMision":
                {
                    Mision mision = new Mision(vista.descripcionMisionTxt.getText(), (NaveEspacial) vista.naveMisionComboBox.getSelectedItem(),
                            (String) vista.estadoMisionComboBox.getSelectedItem());
                    modelo.guardarMision(mision);
                }
                break;
                case "ModificarMision": {
                    Mision misionSeleccionada = vista.listMisiones.getSelectedValue();
                    if (misionSeleccionada != null) {
                        misionSeleccionada.setDescripcion(vista.descripcionMisionTxt.getText());
                        misionSeleccionada.setNave((NaveEspacial) vista.naveMisionComboBox.getSelectedItem());
                        misionSeleccionada.setEstado((String) vista.estadoMisionComboBox.getSelectedItem());
                        modelo.modificarMision(misionSeleccionada);
                    } else {
                        JOptionPane.showMessageDialog(vista, "Seleccione una mision para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
                case "EliminarMision":{
                    int resp = Util.showConfirmDialog("¿Desea eliminar la misión?", "Eliminar Misión");
                    if (resp == JOptionPane.OK_OPTION) {
                        Mision mision = vista.listMisiones.getSelectedValue();
                        modelo.eliminarMision(mision);
                    }
                }
                break;
                case "Informe1":
                    if (avisoBBDD()) break;
                    JasperPrint informeLleno = ReportGenerator.generarInformeListadoNaves();
                    mostrarInforme(informeLleno);
                    break;
                case "Informe2":
                    if (avisoBBDD()) break;
                    informeLleno = ReportGenerator.generarInformeListadoTripulantes();
                    mostrarInforme(informeLleno);
                    break;
                case "Informe3":
                    if (avisoBBDD()) break;
                    informeLleno = ReportGenerator.generarInformeTripulacionPorNave();
                    mostrarInforme(informeLleno);
                    break;
                case "Informe4":
                    if (avisoBBDD()) break;
                    informeLleno = ReportGenerator.generarInformeMisionesPorEstado();
                    mostrarInforme(informeLleno);
                    break;
                case "Informe5":
                    if (avisoBBDD()) break;
                    String input = JOptionPane.showInputDialog(null,
                            "Introduce el ID de la nave:",
                            "Generar Informe",
                            JOptionPane.QUESTION_MESSAGE);

                    if (input != null && !input.trim().isEmpty()) {
                        try {
                            int idNave = Integer.parseInt(input.trim());
                            informeLleno = ReportGenerator.generarInformeMisionesPorNave(idNave);
                            mostrarInforme(informeLleno);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null,
                                    "El ID debe ser un número entero válido.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "No se ingresó un ID válido.",
                                "Error",
                                JOptionPane.WARNING_MESSAGE);
                    }
                    break;

                case "Informe6":
                    if (avisoBBDD()) break;

                    String rangoSeleccionado = seleccionarRango();

                    if (rangoSeleccionado != null) {
                        informeLleno = ReportGenerator.generarInformeTripulacionPorRango(rangoSeleccionado);
                        mostrarInforme(informeLleno);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "No se seleccionó un rango.",
                                "Aviso",
                                JOptionPane.WARNING_MESSAGE);
                    }
                    break;
                case "Grafico1":
                    if (avisoBBDD()) break;
                    informeLleno = ReportGenerator.generarGrafico1();
                    mostrarInforme(informeLleno);
                    break;
                case "Grafico2":
                    if (avisoBBDD()) break;
                    informeLleno = ReportGenerator.generarGrafico2();
                    mostrarInforme(informeLleno);
                    break;
                case "AyudaInformes":
                case "Ayuda":
                    vista.helpBroker.setDisplayed(true);
                    break;
                case "Modo Claro":
                case "Modo Oscuro":
                    cambiarTema();
                    break;
            }
            if (conectado) {
                refrescarSeccionNaves();
                refrescarSeccionTripulantes();
                refrescarSeccionMisiones();
            }
        }catch (Exception e){
            if (!conectado) {
                Util.showWarningAlert("No estás conectado a la BBDD");
            } else {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
        }
    }

    private static String seleccionarRango() {
        String[] opciones = {"Capitán", "Ingeniero", "Piloto"};
        String rangoSeleccionado = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione un rango de tripulación:",
                "Generar Informe",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );
        return rangoSeleccionado;
    }

    private boolean avisoBBDD() {
        if (!conectado) {
            JOptionPane.showMessageDialog(vista, "No se ha conectado a la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }
        return false;
    }

    private void cambiarTema() {
        try {
            if (modoOscuro) {
                UIManager.setLookAndFeel(new FlatLightLaf());
                vista.modoOscuroItem.setText("Modo Oscuro");
            } else {
                UIManager.setLookAndFeel(new FlatDarkLaf());
                vista.modoOscuroItem.setText("Modo Claro");
            }
            modoOscuro = !modoOscuro;
            SwingUtilities.updateComponentTreeUI(vista);
            vista.iniciarAyuda();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        if (listSelectionEvent.getSource() == vista.listNaves && vista.listNaves.getSelectedValue() != null) {
            NaveEspacial nave = vista.listNaves.getSelectedValue();
            vista.nombreNavetxt.setText(nave.getNombre());
            vista.claseNaveComboBox.setSelectedItem(nave.getClase());
            vista.capacidadNaveTxt.setText(String.valueOf(nave.getCapacidad()));
        } else if (listSelectionEvent.getSource() == vista.listTripulantes && vista.listTripulantes.getSelectedValue() != null) {
            Tripulante tripulante = vista.listTripulantes.getSelectedValue();
            vista.nombreTripulanteTxt.setText(tripulante.getNombre());
            vista.rangoTripulanteComboBox.setSelectedItem(tripulante.getRango());
            vista.naveTripulanteComboBox.setSelectedItem(tripulante.getNave());
        } else if (listSelectionEvent.getSource() == vista.listMisiones && vista.listMisiones.getSelectedValue() != null) {
            Mision mision = vista.listMisiones.getSelectedValue();
            vista.descripcionMisionTxt.setText(mision.getDescripcion());
            vista.naveMisionComboBox.setSelectedItem(mision.getNave());
            vista.estadoMisionComboBox.setSelectedItem(mision.getEstado());
        }
    }

    private void listarNaves(){
        List<NaveEspacial> naves = modelo.obtenerNaves();
        vista.dlmNaves.clear();
        for(NaveEspacial nave : naves){
            vista.dlmNaves.addElement(nave);
        }
    }

    public void refrescarSeccionNaves(){
        vista.nombreNavetxt.setText("");
        vista.claseNaveComboBox.setSelectedIndex(-1);
        vista.capacidadNaveTxt.setText("");
        listarNaves();
        listarComboNaveTripulantes();
        listarComboNaveMisiones();
    }

    private void listarTripulantes(){
        List<Tripulante> tripulantes = modelo.obtenerTripulantes();
        vista.dlmTripulantes.clear();
        for(Tripulante tripulante : tripulantes){
            vista.dlmTripulantes.addElement(tripulante);
        }
    }

    public void refrescarSeccionTripulantes(){
        vista.nombreTripulanteTxt.setText("");
        vista.rangoTripulanteComboBox.setSelectedIndex(-1);
        vista.naveTripulanteComboBox.setSelectedIndex(-1);
        listarTripulantes();
    }

    public void listarMisiones(){
        List<Mision> misiones = modelo.obtenerMisiones();
        vista.dlmMisiones.clear();
        for(Mision mision : misiones){
            vista.dlmMisiones.addElement(mision);
        }
    }

    public void refrescarSeccionMisiones(){
        vista.descripcionMisionTxt.setText("");
        vista.naveMisionComboBox.setSelectedIndex(-1);
        vista.estadoMisionComboBox.setSelectedIndex(-1);
        listarMisiones();
    }

    private void listarComboNaveTripulantes(){
        List<NaveEspacial> naves = modelo.obtenerNaves();
        vista.dcbNaveTripulante.removeAllElements();

        for (NaveEspacial nave: naves) {
            vista.dcbNaveTripulante.addElement(nave);
        }
    }

    private void listarComboNaveMisiones(){
        List<NaveEspacial> naves = modelo.obtenerNaves();
        vista.dcbNaveMision.removeAllElements();

        for (NaveEspacial nave: naves) {
            vista.dcbNaveMision.addElement(nave);
        }
    }

    public void mostrarInforme(JasperPrint informe){
        if (informe != null) {
            try {
                JasperExportManager.exportReportToPdfFile(informe, "Informe_Generado.pdf");
                java.awt.Desktop.getDesktop().browse(new java.io.File("Informe_Generado.pdf").toURI());
            } catch (JRException | IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void windowClosing(WindowEvent windowEvent) {
        int resp = Util.showConfirmDialog("¿Desea salir de la aplicación?", "Salir");
        if (resp == JOptionPane.OK_OPTION) {
            modelo.desconectar();
            System.exit(0);
        }
    }
}