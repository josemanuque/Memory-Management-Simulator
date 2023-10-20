package Backend;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Page {

	private int pageID;
	private boolean loaded;
	private String logicalAddress;
	private String loadedTime;
	private final int size = 4; //4KB

	public Page(int pageID) {
		this.pageID = pageID;
		this.loaded = false;
		this.logicalAddress = generateRandomString(10);

	}

	private String generateRandomString(int length) {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		String s = "";
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(characters.length());
			s+=(characters.charAt(index));
		}
		return s;
	}

	private String getCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return sdf.format(date);
	}

	public int getPageID() {
		return this.pageID;
	}

	public boolean isLoaded() {
		return this.loaded;
	}

	public void setLoaded(boolean loaded) {
		if (loaded){
			this.loadedTime = getCurrentTime();
		}
		this.loaded = loaded;
	}

	public String getLoadedTime() {
		return loadedTime;
	}

	public String  getLogicalAddress() {
		return this.logicalAddress;
	}

	public void setLogicalAddress(String  logicalAddress) {
		this.logicalAddress = logicalAddress;
	}

}