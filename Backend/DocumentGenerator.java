package Backend;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class DocumentGenerator {

    public static void generateDocument(int lines) {
        try (FileWriter writer = new FileWriter("instructions.txt")) {
            int pidCounter = 1;
            int ptrCounter = 1;
            ArrayList<Integer> activePids = new ArrayList<Integer>();
            ArrayList<Integer> activePtrs = new ArrayList<Integer>();
            Random random = new Random();

            for (int i = 0; i < lines - 1; i++) {
                int operation = random.nextInt(4); // Incluyendo "kill" en las operaciones
                int pid = 0 ;

                switch (operation) {
                    case 0:
                        int size = random.nextInt(1000);
                        writer.write("new(" + pid + ", " + size + ")\n");
                        break;
                    case 1:
                        writer.write("use(" + pid + ")\n");
                        break;
                    case 2:
                            writer.write("delete(" + pid + ")\n");
                        break;
                    case 3:
                            writer.write("kill(" + pid + ")\n");
                        break;
                }
            }
            writer.write("kill(" + pidCounter + ")\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateDocument2(int lines) {
        try (FileWriter writer = new FileWriter("instructions.txt")) {
            int pidCounter = 1;
            Random random = new Random();
            boolean[] pidsInUse = new boolean[lines]; // Utilizamos un arreglo para llevar un seguimiento de los pids en uso
            boolean[] pidHasPointer = new boolean[lines]; // Utilizamos otro arreglo para llevar un seguimiento de si un pid tiene un puntero

            for (int i = 0; i < lines - 1; i++) {
                int operation = random.nextInt(4); // Incluyendo "kill" en las operaciones
                int pid;

                // Si no hay pids en uso, solo se permite crear uno nuevo
                if (operation == 0 || !hayPidsEnUso(pidsInUse)) {
                    pid = pidCounter;
                    pidCounter++;
                    pidsInUse[pid - 1] = true;
                } else {
                    do {
                        pid = random.nextInt(pidCounter) + 1;
                    } while (!pidsInUse[pid - 1]);
                }

                switch (operation) {
                    case 0:
                        // New operation
                        int size = random.nextInt(1000) + 1;
                        writer.write("new(" + pid + ", " + size + ")\n");
                        pidHasPointer[pid - 1] = true;
                        break;
                    case 1:
                        // Use operation (solo después de "new" y si el pid tiene un puntero)
                        if (pidHasPointer[pid - 1]) {
                            writer.write("use(" + pid + ")\n");
                        }
                        break;
                    case 2:
                        // Delete operation (solo después de "use")
                        if (pidHasPointer[pid - 1]) {
                            writer.write("delete(" + pid + ")\n");
                            pidHasPointer[pid - 1] = false;
                        }
                        break;
                    case 3:
                        // Kill operation (solo después de "use" o "new")
                        if (pidHasPointer[pid - 1] || pidsInUse[pid - 1]) {
                            writer.write("kill(" + pid + ")\n");
                            pidsInUse[pid - 1] = false;
                        }
                        break;
                }
            }

            // La última línea debe ser una operación "kill"
            writer.write("kill(" + pidCounter + ")\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean hayPidsEnUso(boolean[] pidsInUse) {
        for (boolean inUse : pidsInUse) {
            if (inUse) {
                return true;
            }
        }
        return false;
    }
}
