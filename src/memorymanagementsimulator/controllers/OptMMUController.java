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
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OptMMUController {
	MMU mmu = new MMU();
	MMU algMMU = new MMU();
	int pointerSequence = 0;
	int pageSequence = 0;
	int vRamSequence = 100;
	int rowIndexSequence = 0;
	int useNewInstructionCount = 0;
	private List<Integer> pointers = new ArrayList<>();
	private Map<Integer, Color> processColor = new HashMap<>();
	private String fileName = "instructions/minimalInstructions.txt";
	private TableMMU tableMMU;
	private  RamComponent ramComponent;
	private int selectedAlgorithm;
	private int clock = 0;
	private int thrashing = 0;
	private boolean isPaused = false;
	private SimulationController simulationController;

	public OptMMUController(SimulationController simulationController, TableMMU tableMMU, RamComponent ramComponent, String fileName){
		this.tableMMU = tableMMU;
		this.ramComponent = ramComponent;
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

	public void startSimulationOPT() {
		//storeOnlineInfoOpt();

		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = reader.readLine()) != null) {
				while (isPaused) {
					try {
						Thread.sleep(1000); // Pausa el hilo hasta que se isPause sea falso
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
				simulationController.getSimulationWindow().getLblSimulationTimeOPT().setText(clock + "s");
				simulationController.getSimulationWindow().getLblProcessOPT().setText(mmu.getProcesses().size() + "");
				simulationController.getSimulationWindow().getLblRamKbOPT().setText(mmu.getRam().getUsedSpace() * 4 + " KB");
				simulationController.getSimulationWindow().getLblRamPercentageOPT().setText(mmu.getRam().getUsedPercentage() + "%");
				int vRamKbSize = mmu.getPagesInVRam().size() * 4;
				simulationController.getSimulationWindow().getLblVRamKbOPT().setText(vRamKbSize + " KB");
				simulationController.getSimulationWindow().getLblVRamPercentageOPT().setText(vRamKbSize * 100 / (ramSize * 4) + "%");
				simulationController.getSimulationWindow().getLblLoadedOPT().setText(mmu.getRam().getUsedSpace() + "");
				simulationController.getSimulationWindow().getLblUnloadedOPT().setText(mmu.getPagesInVRam().size() + "");
				simulationController.getSimulationWindow().getLblThrashingSecondsOPT().setText(this.thrashing + "s");
				DecimalFormat df = new DecimalFormat("0.00");
				simulationController.getSimulationWindow().getLblFragmentationOPT().setText(df.format(mmu.calculateInternalFragmentation()) + " KB");
				int thrashingPercentage = 0;
				if (clock > 0) {
					 thrashingPercentage = thrashing * 100 / clock;
				}
				simulationController.getSimulationWindow().getLblThrashingPercentageOPT().setText(thrashingPercentage + "%");
				if (thrashingPercentage > 50)
					simulationController.getSimulationWindow().getLblThrashingPercentageOPT().setForeground(Color.red);
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
					Page pageToReplace = getReplacementPageOpt();
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
					System.out.println(clock);
				}
				page.setRamAddress(ramAddress);
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
				joinRow(obj, c);
				updateRamDisplay(page, c);
				clock += 1;
			}else {
				clock += 1;
			}
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
					Page pageToReplace = getReplacementPageOpt();
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
			}else{
				clock += 1;
			}
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
	public void storeOnlineInfoOpt() {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String line;
			int pointerSequence = 0;
			while ((line = reader.readLine()) != null) {
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
							//System.out.println("Operación 'new', pid: " + pid + ", value: " + value);
							pointerSequence++;
							pointers.add(pointerSequence);
							addToProcessColor(pid);
							break;
						case "use":
							// Procesar una operación "use"
							//System.out.println("Operación 'use', ptr: " + pid);
							pointers.add(pid);
							break;
						case "delete":
							// Procesar una operación "delete"
							//System.out.println("Operación 'delete', ptr: " + pid);
							break;
						case "kill":
							// Procesar una operación "kill"
							//System.out.println("Operación 'kill', pid: " + pid);
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
		System.out.println(pointers);
	}

	// Gets the pointer to be replaced in OPT
	public Page getReplacementPageOpt(){
		int ptr = 0;
		Map<Integer, Integer> nextUseIndex = new HashMap<>();
		int requestPointer = pointers.get(useNewInstructionCount);
		for (int i = useNewInstructionCount + 1; i < pointers.size(); i++){
			ptr = pointers.get(i);
			// Checks that ptr is not the same as the request pointer and that pages in ptr are not in v ram.
			if(ptr !=  requestPointer && mmu.getSymbolTable().containsKey(ptr) && !mmu.arePagesInVRam(ptr)) {
				nextUseIndex.putIfAbsent(ptr, i);
			}
		}

		for(int pointer : pointers){ // Reviews all pointers if there is one that will no longer be used, will be selected
			if(pointer !=  requestPointer && mmu.getSymbolTable().containsKey(ptr) && !mmu.arePagesInVRam(pointer) ) {
				nextUseIndex.putIfAbsent(pointer, Integer.MAX_VALUE);
			}
		}
		int replacementPointer = -1;
		int maxUseIndex = -1;

		for (Map.Entry<Integer, Integer> entry : nextUseIndex.entrySet()) {
			if (entry.getValue() > maxUseIndex) {
				maxUseIndex = entry.getValue();
				replacementPointer = entry.getKey();
			}
		}
		// If it's the last instruction it may not find a replacement, this sets a random pointer as its replacement
		if (replacementPointer == -1) {
            List<Integer> availablePointers = new ArrayList<>(mmu.getPointersPages().keySet());
			boolean found = false;
			while (!found){
				Random random = new Random();
				int randomIndex = random.nextInt(availablePointers.size());
				replacementPointer = availablePointers.get(randomIndex);
				if (replacementPointer != requestPointer && !mmu.arePagesInVRam(replacementPointer)) {
					found = true;
				}
			}
		}
		List<Page> pages = mmu.getPointersPages().get(replacementPointer);
		for (Page page : pages){
			if (page.isLoaded()){
				page.setLoaded(false);
				mmu.addPageToVRam(page);
				return page;
			}
		}
		return null;
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
			if (colorDistance < 0.05) { // Ajusta este valor según tus preferencias
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

	public Map<Integer, Color> getProcessColor() {
		return processColor;
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