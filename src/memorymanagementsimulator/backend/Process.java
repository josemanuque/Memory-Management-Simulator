package memorymanagementsimulator.backend;

import java.util.Map;
import java.util.HashMap;

public class Process {

	private int processID;
	private Map<Integer, Integer> pointers; //ptr-size
	private int size;

	public Process(int processID, int ptrId, int size) {
		this.processID = processID;
		this.pointers = new HashMap<>();
		this.addPointer(ptrId,size);
	}

	public int getProcessID() {
		return processID;
	}


	/**
	 * 
	 * @param ptrId
	 */
	public void addPointer(int ptrId, int size) {
		this.pointers.put(ptrId, size);
		this.size += size;
	}

	public int getSize() {
		return size;
	}

	public int getSizePtr(int ptrId){
		return pointers.get(ptrId);
	}

	/**
	 * 
	 * @param ptrId
	 */
	public void removePointer(int ptrId) {
		if (pointers.containsKey(ptrId)) {
			this.size -= this.pointers.get(ptrId);
			this.pointers.remove(ptrId);
		}
	}

}