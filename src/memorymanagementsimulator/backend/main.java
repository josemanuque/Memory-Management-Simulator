package memorymanagementsimulator.backend;

import memorymanagementsimulator.controllers.SimulationController;

import static memorymanagementsimulator.backend.DocumentGenerator.generateDocument2;

public class main {
    public static void main(String[] args) {
        SimulationController simulationController = new SimulationController();
        simulationController.startSimulation("C:\\Projects\\SO2\\Memory-Management-Simulator\\instructions\\instructions.txt");
    }
}
