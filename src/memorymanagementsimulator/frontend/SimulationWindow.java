/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package memorymanagementsimulator.frontend;

import memorymanagementsimulator.controllers.AlgMMUController;
import memorymanagementsimulator.controllers.OptMMUController;

import java.awt.Color;
import javax.swing.*;

/**
 *
 * @author josemanuque
 */
public class SimulationWindow extends javax.swing.JFrame {
    private RamComponent optRam;
    private RamComponent algRam;
    private OptMMUController optMmuController;
    private AlgMMUController algMmuController;

    /**
     * Creates new form SimulationWindow
     */
    public SimulationWindow() {
        initComponents();
        //addRowsTest();
        setRams();
        //optMmuController = new OptMMUController(optTable, optRam);
        //algMmuController = new AlgMMUController(algTable, algRam, "FIFO");
        //startSimulationOptTest();
    }
    

    public void startSimulationOptTest(){
        optMmuController.startSimulationOPT();
        algMmuController.setProcessColor(optMmuController.getProcessColor());
    }

    public void startSimulationAlgTest(){
        algMmuController.startSimulation();
    }
    private void setRams(){
        optRam = new RamComponent(jPanelOPT);
        algRam = new RamComponent(jPanelAlg);
    }
    private void addRowsTest(){
        for (int i = 0; i < 50; i++){
            if(i == 2){
                optTable.addRow(new Object[]{null, null, null, null, null, null, null, null}, Color.RED);
                algTable.addRow(new Object[]{null, null, null, null, null, null, null, null}, Color.RED);
            }
            else {
                optTable.addRow(new Object[]{null, null, null, null, null, null, null, null}, null);
            }
        }
    }

    public RamComponent getOptRam() {
        return optRam;
    }

    public RamComponent getAlgRam() {
        return algRam;
    }

    public TableMMU getAlgTable() {
        return algTable;
    }

    public TableMMU getOptTable() {
        return optTable;
    }

    public JLabel getLblTitleOpt() {
        return labelOpt;
    }

    public void setLblTitleOpt(JLabel labelOpt) {
        this.labelOpt = labelOpt;
    }

    public JLabel getLblTitleAlg() {
        return labelAlg;
    }

    public void setLblTitleAlg(JLabel labelAlg) {
        this.labelAlg = labelAlg;
    }

    public JLabel getLblAlgMMU() {
        return lblAlgMMU;
    }

    public void setLblAlgMMU(JLabel lblAlgMMU) {
        this.lblAlgMMU = lblAlgMMU;
    }

    public JLabel getLblOptMMU() {
        return lblOptMMU;
    }

    public void setLblOptMMU(JLabel lblOptMMU) {
        this.lblOptMMU = lblOptMMU;
    }

    public JButton getBtnResumePause() {
        return btnResumePause;
    }

    public void setBtnResumePause(JButton btnResumePause) {
        this.btnResumePause = btnResumePause;
    }

    public JLabel getLabelAlg() {
        return labelAlg;
    }

    public void setLabelAlg(JLabel labelAlg) {
        this.labelAlg = labelAlg;
    }

    public JLabel getLabelOpt() {
        return labelOpt;
    }

    public void setLabelOpt(JLabel labelOpt) {
        this.labelOpt = labelOpt;
    }

    public JLabel getLblFragmentationAlg() {
        return lblFragmentationAlg;
    }

    public void setLblFragmentationAlg(JLabel lblFragmentationAlg) {
        this.lblFragmentationAlg = lblFragmentationAlg;
    }

    public JLabel getLblFragmentationOPT() {
        return lblFragmentationOPT;
    }

    public void setLblFragmentationOPT(JLabel lblFragmentationOPT) {
        this.lblFragmentationOPT = lblFragmentationOPT;
    }

    public JLabel getLblLoadedAlg() {
        return lblLoadedAlg;
    }

    public void setLblLoadedAlg(JLabel lblLoadedAlg) {
        this.lblLoadedAlg = lblLoadedAlg;
    }

    public JLabel getLblLoadedOPT() {
        return lblLoadedOPT;
    }

    public void setLblLoadedOPT(JLabel lblLoadedOPT) {
        this.lblLoadedOPT = lblLoadedOPT;
    }

    public JLabel getLblProcessAlg() {
        return lblProcessAlg;
    }

    public void setLblProcessAlg(JLabel lblProcessAlg) {
        this.lblProcessAlg = lblProcessAlg;
    }

    public JLabel getLblProcessOPT() {
        return lblProcessOPT;
    }

    public void setLblProcessOPT(JLabel lblProcessOPT) {
        this.lblProcessOPT = lblProcessOPT;
    }

    public JLabel getLblRamKbAlg() {
        return lblRamKbAlg;
    }

    public void setLblRamKbAlg(JLabel lblRamKbAlg) {
        this.lblRamKbAlg = lblRamKbAlg;
    }

    public JLabel getLblRamKbOPT() {
        return lblRamKbOPT;
    }

    public void setLblRamKbOPT(JLabel lblRamKbOPT) {
        this.lblRamKbOPT = lblRamKbOPT;
    }

    public JLabel getLblRamPercentageAlg() {
        return lblRamPercentageAlg;
    }

    public void setLblRamPercentageAlg(JLabel lblRamPercentageAlg) {
        this.lblRamPercentageAlg = lblRamPercentageAlg;
    }

    public JLabel getLblRamPercentageOPT() {
        return lblRamPercentageOPT;
    }

    public void setLblRamPercentageOPT(JLabel lblRamPercentageOPT) {
        this.lblRamPercentageOPT = lblRamPercentageOPT;
    }

    public JLabel getLblSimulationTimeAlg() {
        return lblSimulationTimeAlg;
    }

    public void setLblSimulationTimeAlg(JLabel lblSimulationTimeAlg) {
        this.lblSimulationTimeAlg = lblSimulationTimeAlg;
    }

    public JLabel getLblSimulationTimeOPT() {
        return lblSimulationTimeOPT;
    }

    public void setLblSimulationTimeOPT(JLabel lblSimulationTimeOPT) {
        this.lblSimulationTimeOPT = lblSimulationTimeOPT;
    }

    public JLabel getLblThrashingPercentageAlg() {
        return lblThrashingPercentageAlg;
    }

    public void setLblThrashingPercentageAlg(JLabel lblThrashingPercentageAlg) {
        this.lblThrashingPercentageAlg = lblThrashingPercentageAlg;
    }

    public JLabel getLblThrashingPercentageOPT() {
        return lblThrashingPercentageOPT;
    }

    public void setLblThrashingPercentageOPT(JLabel lblThrashingPercentageOPT) {
        this.lblThrashingPercentageOPT = lblThrashingPercentageOPT;
    }

    public JLabel getLblThrashingSecondsAlg() {
        return lblThrashingSecondsAlg;
    }

    public void setLblThrashingSecondsAlg(JLabel lblThrashingSecondsAlg) {
        this.lblThrashingSecondsAlg = lblThrashingSecondsAlg;
    }

    public JLabel getLblThrashingSecondsOPT() {
        return lblThrashingSecondsOPT;
    }

    public void setLblThrashingSecondsOPT(JLabel lblThrashingSecondsOPT) {
        this.lblThrashingSecondsOPT = lblThrashingSecondsOPT;
    }

    public JLabel getLblUnloadedAlg() {
        return lblUnloadedAlg;
    }

    public void setLblUnloadedAlg(JLabel lblUnloadedAlg) {
        this.lblUnloadedAlg = lblUnloadedAlg;
    }

    public JLabel getLblUnloadedOPT() {
        return lblUnloadedOPT;
    }

    public void setLblUnloadedOPT(JLabel lblUnloadedOPT) {
        this.lblUnloadedOPT = lblUnloadedOPT;
    }

    public JLabel getLblVRamKbAlg() {
        return lblVRamKbAlg;
    }

    public void setLblVRamKbAlg(JLabel lblVRamKbAlg) {
        this.lblVRamKbAlg = lblVRamKbAlg;
    }

    public JLabel getLblVRamKbOPT() {
        return lblVRamKbOPT;
    }

    public void setLblVRamKbOPT(JLabel lblVRamKbOPT) {
        this.lblVRamKbOPT = lblVRamKbOPT;
    }

    public JLabel getLblVRamPercentageAlg() {
        return lblVRamPercentageAlg;
    }

    public void setLblVRamPercentageAlg(JLabel lblVRamPercentageAlg) {
        this.lblVRamPercentageAlg = lblVRamPercentageAlg;
    }

    public JLabel getLblVRamPercentageOPT() {
        return lblVRamPercentageOPT;
    }

    public void setLblVRamPercentageOPT(JLabel lblVRamPercentageOPT) {
        this.lblVRamPercentageOPT = lblVRamPercentageOPT;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        algTable = new TableMMU();
        jScrollPane2 = new javax.swing.JScrollPane();
        optTable = new TableMMU();
        jPanelOPT = new javax.swing.JPanel();
        jPanelAlg = new javax.swing.JPanel();
        labelOpt = new javax.swing.JLabel();
        labelAlg = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblVRamPercentageOPT = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblProcessOPT = new javax.swing.JLabel();
        lblSimulationTimeOPT = new javax.swing.JLabel();
        lblRamKbOPT = new javax.swing.JLabel();
        lblRamPercentageOPT = new javax.swing.JLabel();
        lblVRamKbOPT = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblFragmentationOPT = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblThrashingPercentageOPT = new javax.swing.JLabel();
        lblThrashingSecondsOPT = new javax.swing.JLabel();
        lblUnloadedOPT = new javax.swing.JLabel();
        lblLoadedOPT = new javax.swing.JLabel();
        lblRamPercentageAlg = new javax.swing.JLabel();
        lblLoadedAlg = new javax.swing.JLabel();
        lblVRamKbAlg = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblFragmentationAlg = new javax.swing.JLabel();
        lblVRamPercentageAlg = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lblProcessAlg = new javax.swing.JLabel();
        lblThrashingPercentageAlg = new javax.swing.JLabel();
        lblThrashingSecondsAlg = new javax.swing.JLabel();
        lblSimulationTimeAlg = new javax.swing.JLabel();
        lblRamKbAlg = new javax.swing.JLabel();
        lblUnloadedAlg = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblOptMMU = new javax.swing.JLabel();
        lblAlgMMU = new javax.swing.JLabel();
        btnResumePause = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(algTable);

        jScrollPane2.setViewportView(optTable);

        javax.swing.GroupLayout jPanelOPTLayout = new javax.swing.GroupLayout(jPanelOPT);
        jPanelOPT.setLayout(jPanelOPTLayout);
        jPanelOPTLayout.setHorizontalGroup(
                jPanelOPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelOPTLayout.setVerticalGroup(
                jPanelOPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 35, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelAlgLayout = new javax.swing.GroupLayout(jPanelAlg);
        jPanelAlg.setLayout(jPanelAlgLayout);
        jPanelAlgLayout.setHorizontalGroup(
                jPanelAlgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelAlgLayout.setVerticalGroup(
                jPanelAlgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 35, Short.MAX_VALUE)
        );

        labelOpt.setText("RAM - OPT");

        labelAlg.setText("RAM - ALG");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Processes");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Simulation-Time");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("RAM KB");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("RAM %");
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblVRamPercentageOPT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVRamPercentageOPT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("V-RAM KB");
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Pages");
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Thrashing");
        jLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Fragmentation");
        jLabel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblProcessOPT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProcessOPT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblSimulationTimeOPT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSimulationTimeOPT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblRamKbOPT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRamKbOPT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblRamPercentageOPT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRamPercentageOPT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblVRamKbOPT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVRamKbOPT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Loaded");
        jLabel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Unloaded");
        jLabel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblFragmentationOPT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFragmentationOPT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("V-RAM %");
        jLabel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblThrashingPercentageOPT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThrashingPercentageOPT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblThrashingSecondsOPT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThrashingSecondsOPT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblUnloadedOPT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUnloadedOPT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblLoadedOPT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLoadedOPT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblRamPercentageAlg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRamPercentageAlg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblLoadedAlg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLoadedAlg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblVRamKbAlg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVRamKbAlg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Loaded");
        jLabel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("RAM KB");
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Unloaded");
        jLabel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("RAM %");
        jLabel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblFragmentationAlg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFragmentationAlg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblVRamPercentageAlg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVRamPercentageAlg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("V-RAM KB");
        jLabel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Pages");
        jLabel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("V-RAM %");
        jLabel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Thrashing");
        jLabel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Fragmentation");
        jLabel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblProcessAlg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProcessAlg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblThrashingPercentageAlg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThrashingPercentageAlg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblThrashingSecondsAlg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThrashingSecondsAlg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblSimulationTimeAlg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSimulationTimeAlg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblRamKbAlg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRamKbAlg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblUnloadedAlg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUnloadedAlg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Process");
        jLabel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Simulation-Time");
        jLabel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblOptMMU.setText("MMU - OPT");

        lblAlgMMU.setText("MMU - ALG");

        btnResumePause.setText("Pause");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jPanelOPT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(12, 12, 12)
                                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(6, 6, 6)
                                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(12, 12, 12)
                                                                .addComponent(lblProcessOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(6, 6, 6)
                                                                .addComponent(lblSimulationTimeOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(12, 12, 12)
                                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(6, 6, 6)
                                                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(12, 12, 12)
                                                                .addComponent(lblRamKbOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(lblRamPercentageOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(lblVRamKbOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(lblVRamPercentageOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                                .addGap(2, 2, 2)
                                                                                .addComponent(lblLoadedOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(lblUnloadedOPT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(6, 6, 6)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(lblThrashingSecondsOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(lblThrashingPercentageOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(8, 8, 8)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(lblFragmentationOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(6, 6, 6)
                                                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(lblProcessAlg, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(6, 6, 6)
                                                                .addComponent(lblSimulationTimeAlg, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(6, 6, 6)
                                                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(lblRamKbAlg, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(lblRamPercentageAlg, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(lblVRamKbAlg, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(lblVRamPercentageAlg, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                                .addGap(2, 2, 2)
                                                                                .addComponent(lblLoadedAlg, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(lblUnloadedAlg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(6, 6, 6)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(lblThrashingSecondsAlg, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(lblThrashingPercentageAlg, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(8, 8, 8)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(lblFragmentationAlg, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(491, 491, 491)
                                                                .addComponent(labelAlg))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(12, 12, 12)
                                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(36, 36, 36)
                                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(btnResumePause)
                                                                .addGap(415, 415, 415)
                                                                .addComponent(labelOpt)))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(0, 12, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanelAlg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(227, 227, 227)
                                .addComponent(lblOptMMU)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblAlgMMU)
                                .addGap(215, 215, 215))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnResumePause, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelOpt))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanelOPT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(labelAlg)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanelAlg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblOptMMU)
                                        .addComponent(lblAlgMMU))
                                .addGap(4, 4, 4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(6, 6, 6)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblProcessOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblSimulationTimeOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(6, 6, 6)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblVRamPercentageOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblRamKbOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblRamPercentageOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblVRamKbOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(12, 12, 12)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(6, 6, 6)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jLabel11)
                                                                                        .addComponent(jLabel12))
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                        .addComponent(lblUnloadedOPT, javax.swing.GroupLayout.DEFAULT_SIZE, 15, Short.MAX_VALUE)
                                                                                        .addComponent(lblLoadedOPT, javax.swing.GroupLayout.DEFAULT_SIZE, 15, Short.MAX_VALUE)))
                                                                        .addComponent(lblThrashingPercentageOPT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(lblFragmentationOPT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(lblThrashingSecondsOPT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(6, 6, 6)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblProcessAlg, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblSimulationTimeAlg, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(6, 6, 6)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblVRamPercentageAlg, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblRamKbAlg, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblRamPercentageAlg, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblVRamKbAlg, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(12, 12, 12)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(6, 6, 6)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jLabel13)
                                                                                        .addComponent(jLabel15))
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                        .addComponent(lblUnloadedAlg, javax.swing.GroupLayout.DEFAULT_SIZE, 15, Short.MAX_VALUE)
                                                                                        .addComponent(lblLoadedAlg, javax.swing.GroupLayout.DEFAULT_SIZE, 15, Short.MAX_VALUE)))
                                                                        .addComponent(lblThrashingPercentageAlg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(lblFragmentationAlg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(lblThrashingSecondsAlg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SimulationWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SimulationWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SimulationWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SimulationWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SimulationWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private TableMMU algTable;
    private TableMMU optTable;
    private javax.swing.JPanel jPanelAlg;
    private javax.swing.JPanel jPanelOPT;
    private javax.swing.JButton btnResumePause;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelAlg;
    private javax.swing.JLabel labelOpt;
    private javax.swing.JLabel lblAlgMMU;
    private javax.swing.JLabel lblFragmentationAlg;
    private javax.swing.JLabel lblFragmentationOPT;
    private javax.swing.JLabel lblLoadedAlg;
    private javax.swing.JLabel lblLoadedOPT;
    private javax.swing.JLabel lblOptMMU;
    private javax.swing.JLabel lblProcessAlg;
    private javax.swing.JLabel lblProcessOPT;
    private javax.swing.JLabel lblRamKbAlg;
    private javax.swing.JLabel lblRamKbOPT;
    private javax.swing.JLabel lblRamPercentageAlg;
    private javax.swing.JLabel lblRamPercentageOPT;
    private javax.swing.JLabel lblSimulationTimeAlg;
    private javax.swing.JLabel lblSimulationTimeOPT;
    private javax.swing.JLabel lblThrashingPercentageAlg;
    private javax.swing.JLabel lblThrashingPercentageOPT;
    private javax.swing.JLabel lblThrashingSecondsAlg;
    private javax.swing.JLabel lblThrashingSecondsOPT;
    private javax.swing.JLabel lblUnloadedAlg;
    private javax.swing.JLabel lblUnloadedOPT;
    private javax.swing.JLabel lblVRamKbAlg;
    private javax.swing.JLabel lblVRamKbOPT;
    private javax.swing.JLabel lblVRamPercentageAlg;
    private javax.swing.JLabel lblVRamPercentageOPT;
    // End of variables declaration//GEN-END:variables
}
