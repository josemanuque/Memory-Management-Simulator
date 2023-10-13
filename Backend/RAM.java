package Backend;

public class RAM {

	private short pagesQuantity = 100;
	private Int[] pages;
	private boolean full = false;

	public short getPagesQuantity() {
		return this.pagesQuantity;
	}

	public void setPagesQuantity(short pagesQuantity) {
		this.pagesQuantity = pagesQuantity;
	}

	public Int[] getPages() {
		return this.pages;
	}

}