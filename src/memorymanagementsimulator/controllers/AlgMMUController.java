package memorymanagementsimulator.controllers;

import memorymanagementsimulator.backend.*;
import memorymanagementsimulator.backend.Process;
import memorymanagementsimulator.frontend.RamComponent;
import memorymanagementsimulator.frontend.TableMMU;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlgMMUController {
    MMU mmu = new MMU();
    int pointerSequence = 0;
    int pageSequence = 0;
    int vRamSequence = 100;
    int rowIndexSequence = 0;
    int useNewInstructionCount = 0;
    private Map<Integer, Color> processColor = new HashMap<>();
    private String fileName = "instructions/minimalInstructions.txt";
    private TableMMU tableMMU;
    private  RamComponent ramComponent;
    private String selectedAlgorithm;
    private int clock = 0;
    private int thrashing = 0;
    private boolean isPaused = false;
    Queue<Page> fifoQueue = new LinkedList<>();
    SimulationController simulationController;
    
    public AlgMMUController(SimulationController simulationController, TableMMU tableMMU, RamComponent ramComponent, String selectedAlgorithm, String fileName){
        this.tableMMU = tableMMU;
        this.ramComponent = ramComponent;
        this.selectedAlgorithm = selectedAlgorithm;
        this.fileName = fileName;
        this.simulationController = simulationController;
    }
    public void joinRow(Object[] obj) {
        tableMMU.addRow(obj, null);
    }
    public void joinRow(Object[] obj, Color c) {
        tableMMU.addRow(obj, c);
    }

    public void updateRow(int index, Object[] obj) {
        tableMMU.updateRow(index, obj);
    }

    public void updateCell(int rowIndex, int columnIndex, Object obj) {
        tableMMU.updateRow(rowIndex, columnIndex, obj);
    }

    public void deleteRow(int rowIndex){
        tableMMU.deleteRow(rowIndex);
        mmu.rearrangePagesRowIndex(rowIndex);
        if (rowIndexSequence > 0)
            rowIndexSequence--;
    }

    public void startSimulation() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                while (isPaused) {
                    try {
                        Thread.sleep(1000); // Pausa el hilo hasta que se llame a notify()
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // Utilizamos expresiones regulares para extraer las partes clave y valores
                Pattern pattern = Pattern.compile("(new|use|delete|kill)\\((\\d+)(?:,(\\d+))?\\)");
                Matcher matcher = pattern.matcher(line);

                if (matcher.find()) {
                    String keyword = matcher.group(1);
                    int pid = Integer.parseInt(matcher.group(2));
                    int value = 0; // Inicializamos value a 0

                    if (matcher.group(3) != null) {
                        value = Integer.parseInt(matcher.group(3));
                    }

                    switch (keyword) {
                        case "new":
                            // Procesar una operación "new"
                            System.out.println("Operación 'new', pid: " + pid + ", value: " + value);
                            newProcess(pid, value);
                            useNewInstructionCount++;
                            break;
                        case "use":
                            // Procesar una operación "use"
                            System.out.println("Operación 'use', ptr: " + pid);
                            use(pid);
                            useNewInstructionCount++;
                            break;
                        case "delete":
                            // Procesar una operación "delete"
                            System.out.println("Operación 'delete', ptr: " + pid);
                            delete(pid);
                            break;
                        case "kill":
                            // Procesar una operación "kill"
                            System.out.println("Operación 'kill', pid: " + pid);
                            kill(pid);
                            break;
                        default:
                            // La línea no corresponde a una operación conocida
                            System.out.println("Línea no reconocida: " + line);
                    }
                } else {
                    // La línea no tiene el formato esperado
                    System.out.println("Línea mal formateada: " + line);
                }
                int ramSize = mmu.getRam().getPagesQuantity();
                simulationController.getSimulationWindow().getLblSimulationTimeAlg().setText(clock + "s");
                simulationController.getSimulationWindow().getLblProcessAlg().setText(mmu.getProcesses().size() + "");
                simulationController.getSimulationWindow().getLblRamKbAlg().setText(mmu.getRam().getUsedSpace() * 4 + " KB");
                simulationController.getSimulationWindow().getLblRamPercentageAlg().setText(mmu.getRam().getUsedPercentage() + "%");
                int vRamKbSize = mmu.getPagesInVRam().size() * 4;
                simulationController.getSimulationWindow().getLblVRamKbAlg().setText(vRamKbSize + " KB");
                simulationController.getSimulationWindow().getLblVRamPercentageAlg().setText(vRamKbSize * 100 / (ramSize * 4)  + "%");
                simulationController.getSimulationWindow().getLblLoadedAlg().setText(mmu.getRam().getUsedSpace() + "");
                simulationController.getSimulationWindow().getLblUnloadedAlg().setText(mmu.getPagesInVRam().size() + "");
                simulationController.getSimulationWindow().getLblThrashingSecondsAlg().setText(this.thrashing + "s");
                DecimalFormat df = new DecimalFormat("0.00");
                simulationController.getSimulationWindow().getLblFragmentationAlg().setText(df.format(mmu.calculateInternalFragmentation()) + " KB");
                int thrashingPercentage = 0;
                if (clock > 0) {
                    thrashingPercentage = thrashing * 100 / clock;
                }
                simulationController.getSimulationWindow().getLblThrashingPercentageAlg().setText(thrashingPercentage + "%");
                if (thrashingPercentage > 50)
                    simulationController.getSimulationWindow().getLblThrashingPercentageAlg().setForeground(Color.red);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            simulationController.setHasSimulationEnded(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pauseThread() {
        isPaused = true;
    }

    public void resumeThread() {
        isPaused = false;
    }


    public int newProcess(int processID, int size){
        int pagesQuantity = mmu.getHowManyPages(size);
        pointerSequence++;

        List<Page> pagesToCreate = new ArrayList<>();
        for (int i = 0; i < pagesQuantity; i++){
            pageSequence++;
            Page page = new Page(pageSequence, rowIndexSequence);
            pagesToCreate.add(page);
            mmu.getPages().add(page);
            rowIndexSequence++;
        }

        Process process = mmu.findProcess(processID);

        if (process == null){
            process = new Process(processID, pointerSequence, size);
            mmu.addProcess(process);
        } else {
            process.addPointer(pointerSequence, size);
        }
        mmu.addToSymbolTable(pointerSequence, process);
        mmu.addToPointersPages(pointerSequence, pagesToCreate);
        //addToProcessColor(processID);
        useNew(pointerSequence);
        return pointerSequence;
    }

    public void useNew(int pointer){
        RAM ram = mmu.getRam();
        int freePages = ram.getFreeSpacesSize();

        List<Page> pagesFromPointer = mmu.getPointersPages().get(pointer);

        for (Page page : pagesFromPointer){
            if(!page.isLoaded()) {
                int ramAddress = ram.addToEmptySpace(page);
                if (ramAddress == -1) {
                    Page pageToReplace = algorithmRouter();
                    if(pageToReplace == null){
                        throw new RuntimeException("No replacement page was found");
                    }
                    updateCell(pageToReplace.getRowIndex(), 2, null);
                    pageToReplace.setVRamAddress(vRamSequence);
                    updateCell(pageToReplace.getRowIndex(), 2, null);
                    updateCell(pageToReplace.getRowIndex(), 4, null);
                    updateCell(pageToReplace.getRowIndex(), 5, pageToReplace.getVRamAddress());
                    ramAddress = pageToReplace.getRamAddress();
                    vRamSequence++;
                    clock += 4;
                    thrashing += 5;
                }
                page.setRamAddress(ramAddress);
                ram.setPage(ramAddress, page);
                page.setLoaded(true);
                fifoQueue.add(page);
                int processID = mmu.getSymbolTable().get(pointer).getProcessID();
                Object[] obj = new Object[]{
                        page.getPageID(),
                        processID,
                        "X",
                        page.getLogicalAddress(),
                        page.getRamAddress(),
                        null,
                        0,
                        null
                };
                Color c = processColor.get(processID);
                joinRow(obj, c);
                updateRamDisplay(page, c);
                clock += 1;
            }else{
                clock += 1;
            }
            page.updateTimestamp();
            page.updateReferenceBit();
        }
    }

    public void use(int pointer){
        RAM ram = mmu.getRam();
        int freePages = ram.getFreeSpacesSize();

        List<Page> pagesFromPointer = mmu.getPointersPages().get(pointer);

        for (Page page : pagesFromPointer){
            if(!page.isLoaded()) {
                int ramAddress = ram.addToEmptySpace(page);
                if (ramAddress == -1) {
                    Page pageToReplace = algorithmRouter();
                    if(pageToReplace == null){
                        throw new RuntimeException("No replacement page was found");
                    }
                    pageToReplace.setVRamAddress(vRamSequence);
                    updateCell(pageToReplace.getRowIndex(), 2, null);
                    updateCell(pageToReplace.getRowIndex(), 4, null);
                    updateCell(pageToReplace.getRowIndex(), 5, pageToReplace.getVRamAddress());
                    ramAddress = pageToReplace.getRamAddress();
                    mmu.getPagesInVRam().remove(pageToReplace);
                    vRamSequence++;
                    clock += 5;
                    thrashing += 5;
                }
                page.setRamAddress(ramAddress);
                ram.setPage(ramAddress, page);
                page.setLoaded(true);
                int processID = mmu.getSymbolTable().get(pointer).getProcessID();
                Object[] obj = new Object[]{
                        page.getPageID(),
                        processID,
                        "X",
                        page.getLogicalAddress(),
                        page.getRamAddress(),
                        null,
                        0,
                        null
                };
                Color c = processColor.get(processID);
                updateRow(page.getRowIndex(), obj);
                updateRamDisplay(page, c);
            }
            else{
                clock += 1;
            }
            page.updateTimestamp();
            page.updateReferenceBit();
        }
    }

    public void delete(int pointer){
        RAM ram	= mmu.getRam();
        Process process = mmu.getSymbolTable().get(pointer);
        process.removePointer(pointer);
        mmu.getSymbolTable().remove(pointer);
        Map<Integer, List<Page>> pointersPages = mmu.getPointersPages();
        List<Page> pages = mmu.getPointersPages().get(pointer);
        for (Page page : pages){
            if (page.isLoaded()){
                page.setLoaded(false);
                ram.removePage(page.getRamAddress());
                updateRamDisplay(page, null);
            }
            mmu.getPages().remove(page);
            deleteRow(page.getRowIndex());
        }
        pages.clear();
        pointersPages.remove(pointer);
    }

    public void kill(int processID){
        Process process = mmu.findProcess(processID);
        List<Integer> pointers = new ArrayList<>(process.getPointers().keySet());
        for(int i = pointers.size() - 1; i >= 0; i--){
            delete(pointers.get(i));
        }
        mmu.getProcesses().remove(process);
    }

    public Page algorithmRouter(){
        Page pageToReplace = null;
        switch (this.selectedAlgorithm){
            case "FIFO": // FIFO
                pageToReplace = fifoReplacement();
                break;
            case "Second Chance": // SC
                pageToReplace = secondChanceReplacement();
                break;
            case "MRU": //
                pageToReplace = mruReplacement();
                break;
            case "LRU":
                pageToReplace = lruReplacement();
                break;
            case "RND":
                pageToReplace = randomReplacement();
                break;
            default:
                throw new RuntimeException("Invalid algorithm name");
        }
        return pageToReplace;
    }
    public Page fifoReplacement(){
        Page pageToReplace = fifoQueue.poll();

        pageToReplace.setLoaded(false);
        mmu.addPageToVRam(pageToReplace);
        return pageToReplace;
    }
    public Page secondChanceReplacement(){
        Page pageToReplace = null;
        RAM ram = mmu.getRam();
        Page[] pagesInRam = ram.getPages();
        boolean found = false;

        while(!found) {
            for (Page p : pagesInRam) {
                if (p.getReferenceBit() == 0) {
                    pageToReplace = p;
                    found = true;
                    break;
                } else {
                    p.setReferenceBit(0);
                }
            }
        }
        pageToReplace.setLoaded(false);
        mmu.addPageToVRam(pageToReplace);
        return pageToReplace;
    }

    public Page mruReplacement(){
        RAM ram = mmu.getRam();
        Page[] pagesInRam = ram.getPages();
        Page pageToReplace = null;
        long maxTimestamp = Long.MIN_VALUE;

        for(Page p : pagesInRam){
            if(p.getTimestamp() > maxTimestamp){
                maxTimestamp = p.getTimestamp();
                pageToReplace = p;
            }
        }
        if (pageToReplace == null){
            throw new RuntimeException("[MRU] PAGE NOT FOUND");
        }
        pageToReplace.setLoaded(false);
        mmu.addPageToVRam(pageToReplace);
        return pageToReplace;
    }

    public Page lruReplacement(){
        RAM ram = mmu.getRam();
        Page[] pagesInRam = ram.getPages();
        Page pageToReplace = null;
        long minTimestamp = Long.MAX_VALUE;

        for(Page p : pagesInRam){
            if(p.getTimestamp() < minTimestamp){
                minTimestamp = p.getTimestamp();
                pageToReplace = p;
            }
        }
        if (pageToReplace == null){
            throw new RuntimeException("[LRU] PAGE NOT FOUND");
        }
        pageToReplace.setLoaded(false);
        mmu.addPageToVRam(pageToReplace);
        return pageToReplace;
    }

    public Page randomReplacement(){
        RAM ram = mmu.getRam();
        Random random = new Random();

        int randIndex = random.nextInt(ram.getPagesQuantity());
        Page pageToReplace = ram.getPages()[randIndex];

        pageToReplace.setLoaded(false);
        mmu.addPageToVRam(pageToReplace);
        return pageToReplace;
    }

    public Color generateRandomColor() {
        Random random = new Random();

        // Parámetros para limitar el rango de colores
        float minSaturation = 0.5f;
        float maxSaturation = 0.8f;
        float minLightness = 0.8f;
        float maxLightness = 1.0f;

        // Generar un nuevo color aleatorio
        Color randomColor;
        do {
            float hue = random.nextFloat();
            float saturation = minSaturation + random.nextFloat() * (maxSaturation - minSaturation);
            float lightness = minLightness + random.nextFloat() * (maxLightness - minLightness);
            randomColor = Color.getHSBColor(hue, saturation, lightness);
        } while (isColorTooCloseToExisting(randomColor));

        return randomColor;
    }

    private boolean isColorTooCloseToExisting(Color newColor) {
        // Comprobar si el nuevo color es demasiado cercano a los colores existentes en el mapa
        for (Color existingColor : processColor.values()) {
            double colorDistance = getColorDistance(newColor, existingColor);
            if (colorDistance < 0.2) { // Ajusta este valor según tus preferencias
                return true;
            }
        }
        return false;
    }

    private double getColorDistance(Color c1, Color c2) {
        // Calcular la distancia euclidiana entre dos colores en el espacio RGB
        double redDiff = c1.getRed() - c2.getRed();
        double greenDiff = c1.getGreen() - c2.getGreen();
        double blueDiff = c1.getBlue() - c2.getBlue();
        return Math.sqrt(redDiff * redDiff + greenDiff * greenDiff + blueDiff * blueDiff) / Math.sqrt(195075);
    }

    public void addToProcessColor(int processId) {
        // Generar un nuevo color y agregarlo al mapa
        if(processColor.containsKey(processId))
            return;
        Color newColor = generateRandomColor();
        processColor.put(processId, newColor);
    }

    public void setProcessColor(Map<Integer, Color> processColor) {
        this.processColor = processColor;
    }

    public void updateRamDisplay(Page page, Color c){
        ramComponent.setPageColor(page.getRamAddress(), c);
    }

    public int getClock() {
        return clock;
    }

    public int getThrashing() {
        return thrashing;
    }

    public double thrashingPercentage(){
        return (double) thrashing / clock * 100;
    }
}