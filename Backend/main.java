package Backend;

import static Backend.DocumentGenerator.generateDocument2;
import static Backend.MMU.startSimulation;

public class main {
    public static void main(String[] args) {
        startSimulation("instructions.txt");
    }
}
