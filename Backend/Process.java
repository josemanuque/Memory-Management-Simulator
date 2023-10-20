package Backend;

import java.util.Map;
import java.util.HashMap;

public class Process {

	private int processID;
	private Map<Integer, Integer> pointers;
	private int size;

	public Process(int processID, int ptrId, int size) {
		this.processID = processID;
		this.pointers = new HashMap<>();
		this.addPointer(ptrId,size);
	}

	/**
	 * 
	 * @param ptrId
	 */
	public void addPointer(int ptrId, int size) {
		this.pointers.put(ptrId, size);
		this.size += size;
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