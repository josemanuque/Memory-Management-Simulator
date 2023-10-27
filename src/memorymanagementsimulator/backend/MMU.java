package memorymanagementsimulator.backend;

import java.lang.reflect.Array;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MMU {
    List<Process> processes;
    List<Page> pages;
    Map<Integer, List<Page>> pointersPages; // Links the pages associated with each pointer.
    Map<Integer, Process> symbolTable; // Links each pointer with its process for ease of access.
    List<Page> pagesInVRam;
    RAM ram;
    public MMU(){
        this.processes = new ArrayList<Process>();
        this.pages = new ArrayList<Page>();
        this.ram = new RAM();
        this.pointersPages = new HashMap<>();
        this.symbolTable = new HashMap<>();
        this.pagesInVRam = new ArrayList<>();
    }

    public List<Process> getProcesses() {
        return processes;
    }

    public void addProcess(Process process) {
        this.processes.add(process);
    }

    public List<Page> getPages() {
        return pages;
    }

    public void addPage(Page page) {
        this.pages.add(page);
    }

    public Map<Integer, List<Page>> getPointersPages() {
        return pointersPages;
    }

    public void addToPointersPages(int pointerID, List<Page> pages) {
        this.pointersPages.put(pointerID, pages);
    }

    public Map<Integer, Process> getSymbolTable() {
        return symbolTable;
    }

    public void addToSymbolTable(int pointerID, Process process) {
        this.symbolTable.put(pointerID, process);
    }

    public RAM getRam() {
        return ram;
    }

    public void setPageInRam(int index, Page page) {
        this.ram.setPage(index, page);
    }

    public int getHowManyPages(int size){
        int numPages = size / 4000;
        int remainder = size % 4000;
        if (remainder != 0){
            numPages+=1;
            return numPages;
        }
        return numPages;
    }

    public Process findProcess(int processID){
        for(Process process : processes){
            if (processID == process.getProcessID())
                return process;
        }
        return null;
    }

    public boolean arePagesInVRam(int pointerID){
        List<Page> pages = getPointersPages().get(pointerID);
        for (Page page : pages){
            if (page.isLoaded()){
                return false;
            }
        }
        return true;
    }

    public void rearrangePagesRowIndex(int index){
        for(int i = index; i < pages.size(); i++){
            Page page = pages.get(i);
            page.setRowIndex(page.getRowIndex() - 1);
            System.out.println(page.getRowIndex());
        }
    }

    public void addPageToVRam(Page page){
        pagesInVRam.add(page);
    }

    public int getPagesInVRamQuantity(){
        return pagesInVRam.size();
    }
    public List<Page> getPagesInVRam(){
        return this.pagesInVRam;
    }

    public int ramUsageKB(){
        int usedRam = 0;
        for (Page page : ram.getPages()){
            if(page != null && page.isLoaded()){
                usedRam++;
            }
        }
        return usedRam * 4;
    }

    public double RamUsagePercentage(){
        int totalRamPages = ram.getPagesQuantity() ;
        int usedRamPages = ramUsageKB();
        double ramUsagePercentage = (double) usedRamPages / (totalRamPages * 4) * 100;

        return ramUsagePercentage;
    }

    public int getRunningProcesses(){
        int count = 0;
        for(Process p : processes){
            if(!getPointersPages().get(p.getProcessID()).isEmpty()){
                count++;
            }
        }
        return count;
    }

    public int VRamUsageKB(){
        int usedRam = 0;
        for (int i = 0; i < pagesInVRam.size(); i++){
            usedRam++;
        }
        return usedRam * 4;
    }

    public double VRamUsagePercentage(){
        int totalRamSize= ram.getPagesQuantity() * 4;
        int VRamSize = VRamUsageKB();
        double VRamUsagePercentage = (double) VRamSize / totalRamSize * 100;

        return VRamUsagePercentage;
    }

    public float calculateInternalFragmentation(){
        float totalFragmentation = 0;

        for (Process process : getProcesses()){
            int processSize = process.getSize();
            float pagesForProcess = getHowManyPages(processSize);
            float spaceAssigned = pagesForProcess * 4;
            float processSizeKb = ((float) processSize) / 1024;

            float internalFragmentation = spaceAssigned - (processSizeKb);
            if (internalFragmentation > 0) {
                totalFragmentation += internalFragmentation;
            }
        }
        return totalFragmentation;
    }
}