package Backend;

import java.util.ArrayList;
import java.util.List;

public class RAM {

	private final short pagesQuantity = 100;
	private List<Integer> pages;
	private boolean full;

	public RAM() {
		this.pages = new ArrayList<>();
		this.full = false;
	}

	public short getPagesQuantity() {
		return this.pagesQuantity;
	}

	public List<Integer> getPages() {
		return this.pages;
	}

	public boolean isFull() {
		return full;
	}

	public void setFull(boolean full) {
		this.full = full;
	}
}