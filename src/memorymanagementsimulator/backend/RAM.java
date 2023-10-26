package memorymanagementsimulator.backend;

import java.util.ArrayList;
import java.util.List;

public class RAM {

	private final short pagesQuantity = 100;
	private ArrayList<Page> pages;
	private boolean full;

	public RAM() {
		this.pages = new ArrayList<>();
		this.full = false;
	}

	public void addPages(ArrayList<Page> pages){
		for (Page page : pages) {
			if (!this.pages.contains(page)) {
				this.pages.add(page);
			}
		}
	}

	public short getPagesQuantity() {
		return this.pagesQuantity;
	}

	public int pagesLeft(){
		return this.pagesQuantity-this.pages.size();
	}

	public ArrayList<Page> getPages() {
		return this.pages;
	}

	public boolean isFull() {
		return full;
	}

	public void setFull(boolean full) {
		this.full = full;
	}

}