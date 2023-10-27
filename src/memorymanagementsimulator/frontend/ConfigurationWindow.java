/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package memorymanagementsimulator.frontend;

import java.io.File;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import memorymanagementsimulator.controllers.SimulationController;

/**
 *
 * @author josemanuque
 */
public class ConfigurationWindow extends javax.swing.JFrame {
    private SimulationController simulationController;
    private int seed = -1;
    private String selectedAlgorithm;
    private String fileLocation;
    /**
     * Creates new form ConfigurationWindow
     */
    public ConfigurationWindow() {
        initComponents();
        btnStart.setEnabled(false);
        this.simulationController = simulationController;
    }

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public String getSelectedAlgorithm() {
        return selectedAlgorithm;
    }

    public void setSelectedAlgorithm(String selectedAlgorithm) {
        this.selectedAlgorithm = selectedAlgorithm;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public JButton getBtnGenerate() {
        return btnGenerate;
    }

    public void setBtnGenerate(JButton btnGenerate) {
        this.btnGenerate = btnGenerate;
    }

    public JButton getBtnGenerateRandomNumber() {
        return btnGenerateRandomNumber;
    }

    public void setBtnGenerateRandomNumber(JButton btnGenerateRandomNumber) {
        this.btnGenerateRandomNumber = btnGenerateRandomNumber;
    }

    public JButton getBtnImportFile() {
        return btnImportFile;
    }

    public void setBtnImportFile(JButton btnImportFile) {
        this.btnImportFile = btnImportFile;
    }

    public JButton getBtnStart() {
        return btnStart;
    }

    public void setBtnStart(JButton btnStart) {
        this.btnStart = btnStart;
    }

    public JComboBox<String> getDropdownAlgorithm() {
        return dropdownAlgorithm;
    }

    public void setDropdownAlgorithm(JComboBox<String> dropdownAlgorithm) {
        this.dropdownAlgorithm = dropdownAlgorithm;
    }

    public JComboBox<String> getDropdownOperations() {
        return dropdownOperations;
    }

    public void setDropdownOperations(JComboBox<String> dropdownOperations) {
        this.dropdownOperations = dropdownOperations;
    }

    public JComboBox<String> getDropdownProcesses() {
        return dropdownProcesses;
    }

    public void setDropdownProcesses(JComboBox<String> dropdownProcesses) {
        this.dropdownProcesses = dropdownProcesses;
    }

    public JTextArea getTxaReadFile() {
        return txaReadFile;
    }

    public void setTxaReadFile(JTextArea txaReadFile) {
        this.txaReadFile = txaReadFile;
    }

    public JTextField getTxfSeed() {
        return txfSeed;
    }

    public void setTxfSeed(JTextField txfSeed) {
        this.txfSeed = txfSeed;
    }

    public JLabel getLblImportedFile() {
        return lblImportedFile;
    }

    public void setLblImportedFile(String text) {
        lblImportedFile.setText(text);
    }
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dropdownAlgorithm = new javax.swing.JComboBox<>();
        dropdownProcesses = new javax.swing.JComboBox<>();
        dropdownOperations = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaReadFile = new javax.swing.JTextArea();
        lblAlgorithm = new javax.swing.JLabel();
        btnImportFile = new javax.swing.JButton();
        txfSeed = new javax.swing.JTextField();
        lblTitle = new javax.swing.JLabel();
        btnGenerateRandomNumber = new javax.swing.JButton();
        lblProcess = new javax.swing.JLabel();
        lblOps = new javax.swing.JLabel();
        lblSeed = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(50, 100), new java.awt.Dimension(50, 100), new java.awt.Dimension(50, 100));
        btnGenerate = new javax.swing.JButton();
        lblImportedFile = new javax.swing.JLabel();
        btnStart = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        dropdownAlgorithm.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        dropdownAlgorithm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FIFO", "Second Chance", "MRU", "LRU", "RND" }));

        dropdownProcesses.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        dropdownProcesses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10", "50", "100" }));

        dropdownOperations.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        dropdownOperations.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "500", "1000", "5000" }));

        txaReadFile.setColumns(20);
        txaReadFile.setRows(5);
        jScrollPane1.setViewportView(txaReadFile);

        lblAlgorithm.setText("Select the algorithm to use:");

        btnImportFile.setText("Import File");

        lblTitle.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblTitle.setText("Memory Management Unit Simulator");

        btnGenerateRandomNumber.setText("Random Seed");

        lblProcess.setText("Processes:");

        lblOps.setText("Operations:");

        lblSeed.setText("Seed:");

        btnGenerate.setText("Generate");

        btnStart.setText("Start Simulation");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAlgorithm)
                            .addComponent(lblImportedFile)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnStart, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnImportFile, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dropdownAlgorithm, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 188, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnGenerateRandomNumber)
                                .addGap(18, 18, 18)
                                .addComponent(btnGenerate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblOps, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(33, 33, 33)
                                    .addComponent(dropdownOperations, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblSeed)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txfSeed, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dropdownProcesses, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTitle)
                        .addGap(94, 94, 94)))
                .addGap(98, 98, 98))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(lblTitle)
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dropdownProcesses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProcess)
                    .addComponent(lblAlgorithm))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dropdownOperations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblOps)
                        .addComponent(dropdownAlgorithm, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfSeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSeed))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnImportFile)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGenerateRandomNumber)
                            .addComponent(btnGenerate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(lblImportedFile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnStart)))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ConfigurationWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConfigurationWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConfigurationWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfigurationWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConfigurationWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerate;
    private javax.swing.JButton btnGenerateRandomNumber;
    private javax.swing.JButton btnImportFile;
    private javax.swing.JButton btnStart;
    private javax.swing.JComboBox<String> dropdownAlgorithm;
    private javax.swing.JComboBox<String> dropdownOperations;
    private javax.swing.JComboBox<String> dropdownProcesses;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAlgorithm;
    private javax.swing.JLabel lblImportedFile;
    private javax.swing.JLabel lblOps;
    private javax.swing.JLabel lblProcess;
    private javax.swing.JLabel lblSeed;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTextArea txaReadFile;
    private javax.swing.JTextField txfSeed;
    // End of variables declaration//GEN-END:variables
}
