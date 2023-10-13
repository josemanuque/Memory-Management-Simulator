package Backend;

import java.util.*;

public class MMU {
	private List<Page> pages;
	private RAM ram;
	private List<Process> processes;

	/**
	 * 
	 * @param processID
	 * @param size
	 */
	public int newProcess(int processID, int size) {
		// TODO - implement MMU.newProcess
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param ptr
	 */
	public void use(int ptr) {
		// TODO - implement MMU.use
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param ptr
	 */
	public void delete(int ptr) {
		// TODO - implement MMU.delete
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pid
	 */
	public void kill(int pid) {
		// TODO - implement MMU.kill
		throw new UnsupportedOperationException();
	}

}