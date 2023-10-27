package memorymanagementsimulator.backend;

import java.util.ArrayList;
import java.util.List;

public class RAM {

	private final short pagesQuantity = 100;
	private Page[] pages;
	private int pagesSize;

	public RAM() {
		this.pages = new Page[pagesQuantity];
	}

	public short getPagesQuantity() {
		return pagesQuantity;
	}

	public Page[] getPages() {
		return pages;
	}

	public void setPage(int index, Page page) {
		this.pages[index] = page;
	}

	public void removePage(int index){
		this.pages[index] = null;
	}
	public int addToEmptySpace(Page page){
		for(int i = 0; i < pagesQuantity; i++){
			if (pages[i] == null) {
				pages[i] = page;
				return i;
			}
		}
		return -1;
	}

	public boolean isFull() {
		return getFreeSpacesSize() == 0;
	}



	public int getFreeSpacesSize(){
		int freeCount = 0;
		for(int i = 0; i < pagesQuantity; i++){
			if (pages[i] == null)
				freeCount++;
		}
		return pagesQuantity - freeCount;
	}

	public int getUsedSpace(){
		int usedCount = 0;
		for(int i = 0; i < pagesQuantity; i++){
			if (pages[i] != null)
				usedCount++;
		}
		return usedCount;
	}

	public int getUsedPercentage(){
		int usedCount = 0;
		for(int i = 0; i < pagesQuantity; i++){
			if (pages[i] != null)
				usedCount++;
		}
		usedCount *= 4;
		return usedCount * 100 / (pagesQuantity * 4);
	}
}