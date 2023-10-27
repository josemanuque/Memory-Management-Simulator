package memorymanagementsimulator.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import memorymanagementsimulator.frontend.*;

public class SimulationController {
	private OptMMUController optMmuController;
	private AlgMMUController algMMUController;

	//private WelcomeScreen welcomeScreen;
	private SimulationWindow simulationWindow;
        private ConfigurationWindow configurationWindow;
	private Thread optThread;
	private Thread algThread;
        private String fileName;
        private int seed;
	private boolean isRunning = false;

	public SimulationController(){
            configurationWindow = new ConfigurationWindow();
            simulationWindow = new SimulationWindow();
            initListeners();
            configurationWindow.setVisible(true);
	}
        
        private void initListeners(){
            configurationWindow.getBtnGenerateRandomNumber().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    generateRandomNumber();
                }
            });
            
            configurationWindow.getBtnGenerate().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // To be implemented
                }
            });
            
            configurationWindow.getBtnImportFile().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    importFile();
                }
            });
            
            configurationWindow.getBtnStart().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    startSimulation();
                }
            });
        }
        
	public void startSimulation() {
            String selectedAlgorithm = (String)configurationWindow.getDropdownAlgorithm().getSelectedItem();
            
            optMmuController = new OptMMUController(simulationWindow.getOptTable(), simulationWindow.getOptRam());
            algMMUController = new AlgMMUController(simulationWindow.getAlgTable(), simulationWindow.getAlgRam(), selectedAlgorithm);
            configurationWindow.dispose();
            optMmuController.storeOnlineInfoOpt();
            algMMUController.setProcessColor(optMmuController.getProcessColor());
            simulationWindow.setVisible(true);
            simulationWindow.getLblTitleAlg().setText("RAM - " + selectedAlgorithm);
            System.out.println("Started simulation");
            this.isRunning = true;
            //optThread = new Thread(() -> mmuOpt.startSimulation(""));
            
            algThread = new Thread(() -> algMMUController.startSimulation());
            optThread = new Thread(() -> optMmuController.startSimulationOPT());

            optThread.start();
            algThread.start();

            

            /*try {
                    optThread.join();
                    userThread.join();
            } catch (InterruptedException e) {
                    e.printStackTrace();
            }*/
	}
        
        
        public void importFile(){
            fileName = null;
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text file (*.txt)", "txt"));

            int seleccion = fileChooser.showOpenDialog(configurationWindow);

            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                fileName = file.getAbsolutePath();

                JOptionPane.showMessageDialog(configurationWindow, "Archivo seleccionado: " + fileName);
                configurationWindow.getLblImportedFile().setText(file.getName());
                configurationWindow.getBtnGenerate().setEnabled(false);
                configurationWindow.getBtnGenerateRandomNumber().setEnabled(false);
                configurationWindow.getDropdownOperations().setEnabled(false);
                configurationWindow.getDropdownProcesses().setEnabled(false);
                configurationWindow.getBtnStart().setEnabled(true);
            } else {
                fileName = null;
                configurationWindow.getLblImportedFile().setText("");
                configurationWindow.getBtnGenerate().setEnabled(false);
                configurationWindow.getBtnGenerateRandomNumber().setEnabled(false);
                configurationWindow.getDropdownOperations().setEnabled(false);
                configurationWindow.getDropdownProcesses().setEnabled(false);
                configurationWindow.getBtnStart().setEnabled(true);
            }
        } 
        
        public void generateRandomNumber(){
            Random rand = new Random();
            seed = rand.nextInt(100) + 1;
            configurationWindow.getTxfSeed().setText(String.valueOf(seed));
        }


}