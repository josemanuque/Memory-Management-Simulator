package memorymanagementsimulator.backend;

import static memorymanagementsimulator.backend.DocumentGenerator.generateDocument2;
import static memorymanagementsimulator.backend.MMU.startSimulation;

public class main {
    public static void main(String[] args) {
        startSimulation("instructions/instructions.txt");
    }
}
