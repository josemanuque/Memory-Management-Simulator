package Backend;

public class Page {

	private int pageID;
	private boolean loaded = false;
	private int logicalAddress;
	private int loadedTime;
	/**
	 * Size in kb
	 */
	private int size = 4;

	public int getPageID() {
		return this.pageID;
	}

	public boolean isLoaded() {
		return this.loaded;
	}

	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}

	public int getLogicalAddress() {
		return this.logicalAddress;
	}

	public void setLogicalAddress(int logicalAddress) {
		this.logicalAddress = logicalAddress;
	}

}