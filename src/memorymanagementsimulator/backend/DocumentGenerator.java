package memorymanagementsimulator.backend;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DocumentGenerator {
    private Map<Integer, Integer> symbolTable; // ptr -> process
    private ArrayList<Integer> activePids;
    private ArrayList<Integer> activePtrs;
    private int ptrCounter;
    public DocumentGenerator() {
        this.symbolTable = new HashMap<>();
        this.activePids = new ArrayList<>();
        this.activePtrs = new ArrayList<>();
        this.ptrCounter = 0;
    }

    private void newPtr(int pid){
        ptrCounter+=1;
        symbolTable.put(ptrCounter,pid);
        activePtrs.add(ptrCounter);
    }

    public void generateDocument(int nProcesses, int nLines,  long seed) {
        try (FileWriter writer = new FileWriter("instructions.txt")) {
            int pid = 0;
            int ptrid = 0;
            int pSize = 0;
            int correctLines = nProcesses;

            Random randomPid = new Random(seed);
            Random randomPtr = new Random(seed);
            Random randomSize = new Random(seed);
            Random probabilityRandom = new Random(seed);

            for (int i = 1;i<nProcesses+1;i++){
                pSize = randomSize.nextInt(16000) + 1; //generate nProcesses of 1-2-3 pages
                newPtr(i);
                activePids.add(i);
                writer.write("new(" + i + "," + pSize + ")\n");
            }

            while (correctLines<=nLines-1 && !activePtrs.isEmpty()){
                int operation = 0;
                int probability = probabilityRandom.nextInt(101);
                if (probability >= 0 && probability <= 25) {
                    operation = 0;
                } else if (probability >= 26 && probability <= 60) {
                    operation = 1;
                } else if (probability >= 61 && probability <= 85) {
                    operation = 2;
                } else if (probability >= 86 && probability <= 100) {
                    operation = 3;
                }

                switch (operation) {
                    case 0:
                        pid = randomPid.nextInt(nProcesses);
                        if (activePids.contains(pid)){
                            pSize = randomSize.nextInt(16000) + 1;
                            newPtr(pid);
                            writer.write("new(" + pid + "," + pSize + ")\n");
                            correctLines+=1;
                        }
                        break;
                    case 1:
                        if (!activePtrs.isEmpty()){
                            ptrid = activePtrs.get(randomPtr.nextInt(activePtrs.size()));
                            writer.write("use(" + ptrid + ")\n");
                            correctLines+=1;
                        }
                        break;
                    case 2:
                        if (!activePtrs.isEmpty()){
                            ptrid = activePtrs.get(randomPtr.nextInt(activePtrs.size()));
                            Iterator<Map.Entry<Integer, Integer>> iterator = symbolTable.entrySet().iterator();
                            while (iterator.hasNext()) {
                                Map.Entry<Integer, Integer> entry = iterator.next();
                                if (entry.getKey() == ptrid) {
                                    activePtrs.remove(Integer.valueOf(entry.getKey()));
                                    iterator.remove(); // Remove the entry using the iterator
                                }
                            }
                            writer.write("delete(" + ptrid + ")\n");
                            correctLines+=1;
                        }
                        break;
                    case 3:
                        pid = randomPid.nextInt(nProcesses)+1;
                        if (activePids.contains(pid)){
                            Iterator<Map.Entry<Integer, Integer>> iterator = symbolTable.entrySet().iterator();
                            while (iterator.hasNext()) {
                                Map.Entry<Integer, Integer> entry = iterator.next();
                                if (entry.getValue() == pid) {
                                    activePtrs.remove(Integer.valueOf(entry.getKey()));
                                    iterator.remove(); // Remove the entry using the iterator
                                }
                            }
                            activePids.remove(Integer.valueOf(pid));
                            writer.write("kill(" + pid + ")\n");
                            correctLines+=1;
                        }
                        break;
                }
            }
            if (!activePids.isEmpty()){
                for (int i=0;i<activePids.size();i++){
                    writer.write("kill(" + activePids.get(i) + ")\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

