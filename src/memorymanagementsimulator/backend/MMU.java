package memorymanagementsimulator.backend;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MMU {
	private ArrayList<Page> pagesInVirtualMemory;
	private ArrayList<Page> pagesToReplace;
	private RAM ram;
	private List<Process> processes;
	private Map<Integer, Process> symbolTable;
	private Map<Integer, ArrayList<Page>> pagesInfo; //Pointers relation with pages
	private int ptrId;
	private int pageId;
	private Random random;
	private String algName;

	public MMU(String algName){
		this.ram = new RAM();
		this.processes = new ArrayList<>();
		this.pagesToReplace = new ArrayList<>();
		this.symbolTable = new HashMap<>();
		this.pagesInVirtualMemory = new ArrayList<>();
		this.pagesInfo = new HashMap<>();
		this.random = new Random();
		this.algName = algName;
	}

	public void startSimulation(String fileName) {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = reader.readLine()) != null) {
				// Utilizamos expresiones regulares para extraer las partes clave y valores
				Pattern pattern = Pattern.compile("(new|use|delete|kill)\\((\\d+)(?:,(\\d+))?\\)");
				Matcher matcher = pattern.matcher(line);

				if (matcher.find()) {
					String keyword = matcher.group(1);
					int firstParameter = Integer.parseInt(matcher.group(2));
					int secondParameter = 0; // Inicializamos value a 0

					if (matcher.group(3) != null) {
						secondParameter = Integer.parseInt(matcher.group(3));
					}

					switch (keyword) {
						case "new":
							// Procesar una operación "new"
							System.out.println("Operación 'new', pid: " + firstParameter + ", value: " + secondParameter);
							newProcess(firstParameter,secondParameter);
							break;
						case "use":
							// Procesar una operación "use"
							System.out.println("Operación 'use', pid: " + firstParameter);
							use(firstParameter);
							break;
						case "delete":
							// Procesar una operación "delete"
							System.out.println("Operación 'delete', pid: " + firstParameter);
							delete(firstParameter);
							break;
						case "kill":
							// Procesar una operación "kill"
							System.out.println("Operación 'kill', pid: " + firstParameter);
							kill(firstParameter);
							break;
						default:
							// La línea no corresponde a una operación conocida
							System.out.println("Línea no reconocida: " + line);
					}
				} else {
					// La línea no tiene el formato esperado
					System.out.println("Línea mal formateada: " + line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void runAlgorithm(String name, Page page){
		name.toLowerCase();
		if (name == "opt"){

		}if (name == "fifo"){
			fifoReplacement(page);
		}if (name == "lru"){
			lruReplacement(page);
		}if (name == "mru"){
			mruReplacement(page);
		}if (name == "secondchance"){
			secondChanceReplacement(page);
		}if (name == "random"){
			randomReplacement(page);
		}
	}

	private boolean processExist(int processId){
		for (int i = 0; i<processes.size(); i++){
			if (processId == processes.get(i).getProcessID()){
				return true;
			}
		}
		return false;
	}

	private Process findProcess(int pId){
		for (int i=0; i<processes.size(); i++){
			if (processes.get(i).getProcessID() == pId){
				return processes.get(i);
			}
		}
		return null;
	}

	private int calculatePages(int size){
		int numPages = size / 4000;
		int remainder = size % 4000;
		if (remainder != 0){
			numPages+=1;
			return numPages;
		}
		return numPages;
	}


	/**
	 * 
	 * @param processId
	 * @param size
	 */
	public int newProcess(int processId, int size) {
		ptrId += 1;
		int numberOfPages = calculatePages(size);
		ArrayList<Page> processPages = new ArrayList<>();
		for (int i=0; i<numberOfPages; i++){
			pageId +=1;
			Page page = new Page(pageId);
			processPages.add(page);
		}
		if (processExist(processId)){
			symbolTable.get(processId).addPointer(ptrId,size);
			Process process = symbolTable.get(processId);
			symbolTable.put(ptrId,process);
		} else {
			Process process = new Process(processId, ptrId, size);
			processes.add(process);
			symbolTable.put(ptrId,process);
		}
		pagesInfo.put(ptrId,processPages);
		use(ptrId);
		return ptrId;
	}



	/**
	 * 
	 * @param ptr
	 */
	public void use(int ptr) {
		int spaceLeft = ram.pagesLeft();
		ArrayList<Page> ptrPages = pagesInfo.get(ptr);

		for (Page page : ptrPages) {
			if (!page.isLoaded()){
				if (!this.ram.isFull()){
					if (spaceLeft >= ptrPages.size()){
						ram.addPages(page);
						page.setLoaded(true, String.valueOf(ram.getPages().size()-1));
					} else {
						ArrayList<Page> pagesToAllocate = (ArrayList<Page>) ptrPages.subList(0,spaceLeft);
						for (int i=0; i<spaceLeft; i++){
							ram.addPages(pagesToAllocate.get(i));
							ptrPages.get(i).setLoaded(true, String.valueOf(ram.getPages().size()-1));
						}
					}
				} else {
					runAlgorithm(algName, page);
				}
			}
		}
	}

	/**
	 * 
	 * @param ptr
	 */
	public void delete(int ptr) {
		//Pages to delete
		ArrayList<Page> pagesToDelete = pagesInfo.get(ptr);
		ArrayList<Page> pagesInRAM = ram.getPages();

		for (int i=0; i<pagesToDelete.size(); i++){
			Page page = pagesToDelete.get(i); //page to delete
			for (int j=0; j<pagesInRAM.size();j++){
				Page ramPage = pagesInRAM.get(j); //page to see if it needs to be deleted
				if (page.equals(ramPage)){ //if they are the same the page is deleted
					ram.getPages().remove(ramPage);
				}
			}
		}

		// Use retainAll to find and remove common elements
		//pagesInRAM.retainAll(pagesToDelete);
		//pagesToDelete.retainAll(pagesInRAM);
		for (Page page: pagesToDelete){
			if (pagesInVirtualMemory.contains(page)){
				pagesInVirtualMemory.remove(page);
			}
		}
		pagesInfo.remove(ptr);
		symbolTable.remove(ptr);
	}

	/**
	 * 
	 * @param processId
	 */
	public void kill(int processId) {
		Process processToDelete = findProcess(processId);
		Map<Integer, Process> pairToDelete = new HashMap<>();
		for (Map.Entry<Integer, Process> entry : symbolTable.entrySet()) {
			if (entry.getValue().equals(processToDelete)) {
				pairToDelete.put(entry.getKey(),entry.getValue());
			}
		}

		for (Integer key : pairToDelete.keySet()) {
			symbolTable.remove(key);
		}
		for(int i=0; i< processes.size(); i++){
			if (processes.get(i).getProcessID() == processId){
				processes.remove(processes.get(i));
			}
		}
	}

	public void fifoReplacement(Page newPage){
		ArrayList<Page> pagesInRam = ram.getPages();

		Page pageToReplace = pagesInRam.get(0);
		pagesInRam.remove(pageToReplace);
		pagesInRam.add(newPage);
		newPage.setLoaded(true, String.valueOf(ram.getPages().size()));

		pageToReplace.setLoaded(false, "");
		pagesInVirtualMemory.add(pageToReplace);
	}

	public void secondChanceReplacement(Page newPage){
		Page pageToReplace = null;
		ArrayList<Page> pagesInRam = ram.getPages();
		boolean found = false;

		while(!found) {
			for (int i = 0; i < pagesInRam.size(); i++) {
				Page p = pagesInRam.get(i);
				if (p.getReferenceBit() == 0) {
					pageToReplace = p;
					found = true;
					break;
				} else {
					p.setReferenceBit(0);
				}
			}
		}
		int pos = Integer.parseInt(pageToReplace.getLogicalAddress());
		pagesInRam.remove(pageToReplace);
		pageToReplace.setLoaded(false, "");
		pagesInVirtualMemory.add(pageToReplace);
		pagesInRam.add(pos, newPage);
	}

	public void mruReplacement(Page newPage){
		ArrayList<Page> pagesInRam = ram.getPages();
		Page pageToReplace = null;
		long maxTimestamp = Long.MIN_VALUE;

		for(Page p : pagesInRam){
			if(p.getTimestamp() > maxTimestamp){
				maxTimestamp = p.getTimestamp();
				pageToReplace = p;
			}
		}
		int pos = Integer.parseInt(pageToReplace.getLogicalAddress());
		pagesInRam.remove(pageToReplace);
		pageToReplace.setLoaded(false, "");
		pagesInVirtualMemory.add(pageToReplace);
		pagesInRam.add(pos, newPage);
	}

	public void lruReplacement(Page newPage){
		ArrayList<Page> pagesInRam = ram.getPages();
		Page pageToReplace = null;
		long minTimestamp = Long.MAX_VALUE;

		for(Page p : pagesInRam){
			if(p.getTimestamp() < minTimestamp){
				minTimestamp = p.getTimestamp();
				pageToReplace = p;
			}
		}
		int pos = Integer.parseInt(pageToReplace.getLogicalAddress());
		pagesInRam.remove(pageToReplace);
		pageToReplace.setLoaded(false, "");
		pagesInVirtualMemory.add(pageToReplace);
		pagesInRam.add(pos, newPage);
	}

	public void randomReplacement(Page newPage){
		ArrayList<Page> pagesInRam = ram.getPages();

		int randIndex = random.nextInt(pagesInRam.size());
		Page pageToReplace = pagesInRam.get(randIndex);
		pagesInRam.remove(pageToReplace);
		pagesInRam.add(randIndex, newPage);

		pageToReplace.setLoaded(false, "");
		pagesInVirtualMemory.add(pageToReplace);
	}
}