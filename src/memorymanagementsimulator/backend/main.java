package memorymanagementsimulator.backend;

import memorymanagementsimulator.controllers.SimulationController;


public class main {
    public static void main(String[] args) {
        SimulationController simulationController = new SimulationController();
        //simulationController.startSimulation("C:\\Projects\\SO2\\Memory-Management-Simulator\\instructions\\instructions.txt");
        DocumentGenerator dc = new DocumentGenerator();
        long seed = 1234589;
        dc.generateDocument(10,70, seed);
        System.out.println("done");
    }
}
