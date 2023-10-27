package memorymanagementsimulator.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.BufferedReader;

import memorymanagementsimulator.backend.DocumentGenerator;
import memorymanagementsimulator.frontend.*;

public class SimulationController {
	private OptMMUController optMmuController;
	private AlgMMUController algMMUController;

	//private WelcomeScreen welcomeScreen;
	private SimulationWindow simulationWindow;
    private ConfigurationWindow configurationWindow;
	private Thread optThread;
	private Thread algThread;
    Thread simulationTimeHandler;
    private String fileName;
    private int seed;
	private boolean isRunning = false;
    private int simulationTime = 0;
    private boolean hasSimulationEnded;
    private DocumentGenerator documentGenerator;

	public SimulationController(){
        configurationWindow = new ConfigurationWindow();
        simulationWindow = new SimulationWindow();
        documentGenerator = new DocumentGenerator();
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
                seed = Integer.parseInt(configurationWindow.getTxfSeed().getText());
                documentGenerator.generateDocument(Integer.parseInt((String)configurationWindow.getDropdownProcesses().getSelectedItem()),Integer.parseInt((String)configurationWindow.getDropdownOperations().getSelectedItem()), seed);
                configurationWindow.getTxaReadFile().setText(readFile("instructions.txt"));
                fileName = "instructions.txt";
                configurationWindow.getLblImportedFile().setText(fileName);
                configurationWindow.getBtnStart().setEnabled(true);
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

        simulationWindow.getBtnResumePause().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resumePauseThreads();
            }
        });
    }
        
	public void startSimulation() {
            String selectedAlgorithm = (String)configurationWindow.getDropdownAlgorithm().getSelectedItem();
            
            optMmuController = new OptMMUController(this, simulationWindow.getOptTable(), simulationWindow.getOptRam(), fileName);
            algMMUController = new AlgMMUController(this, simulationWindow.getAlgTable(), simulationWindow.getAlgRam(), selectedAlgorithm, fileName);
            configurationWindow.dispose();
            optMmuController.storeOnlineInfoOpt();
            algMMUController.setProcessColor(optMmuController.getProcessColor());
            simulationWindow.setVisible(true);
            simulationWindow.getLblTitleAlg().setText("RAM - " + selectedAlgorithm);
            simulationWindow.getLblAlgMMU().setText("MMU - " + selectedAlgorithm);
            System.out.println("Started simulation");
            this.isRunning = true;
            //optThread = new Thread(() -> mmuOpt.startSimulation(""));
            
            algThread = new Thread(() -> algMMUController.startSimulation());
            optThread = new Thread(() -> optMmuController.startSimulationOPT());
            simulationTimeHandler = new Thread(() -> {
                while(!hasSimulationEnded) {
                    while(!isRunning){
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    simulationTime++;
                    simulationWindow.getLblSimulationTimeAlg().setText(simulationTime + "s");
                    simulationWindow.getLblSimulationTimeOPT().setText(simulationTime + "s");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            //simulationTimeHandler.start();
            optThread.start();
            algThread.start();


	}

    public void resumePauseThreads(){
        if(isRunning){
            algMMUController.pauseThread();
            optMmuController.pauseThread();
            isRunning = false;
            simulationWindow.getBtnResumePause().setText("Resume");
            return;
        }
        algMMUController.resumeThread();
        optMmuController.resumeThread();
        isRunning = true;
        simulationWindow.getBtnResumePause().setText("Pause");
    }


    public void importFile(){
        fileName = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text file (*.txt)", "txt"));

        int selection = fileChooser.showOpenDialog(configurationWindow);

        if (selection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            fileName = file.getAbsolutePath();

            JOptionPane.showMessageDialog(configurationWindow, "Selected file: " + fileName);
            configurationWindow.getLblImportedFile().setText(file.getName());
            configurationWindow.getBtnGenerate().setEnabled(false);
            configurationWindow.getBtnGenerateRandomNumber().setEnabled(false);
            configurationWindow.getDropdownOperations().setEnabled(false);
            configurationWindow.getDropdownProcesses().setEnabled(false);
            configurationWindow.getBtnStart().setEnabled(true);

            configurationWindow.getTxaReadFile().setText(readFile(file.getPath()));
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

    public SimulationWindow getSimulationWindow() {
        return simulationWindow;
    }

    public void setHasSimulationEnded(boolean hasSimulationEnded) {
        this.hasSimulationEnded = hasSimulationEnded;
    }

    public String readFile(String filePath)
    {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null)
            {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        }
        catch (IOException e)
        {
            //System.out.println("File not found");
            //e.printStackTrace();
        }
        return contentBuilder.toString();
    }
}