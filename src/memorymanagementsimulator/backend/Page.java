package memorymanagementsimulator.backend;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Page {

	private int pageID;
	private boolean loaded;
	private String logicalAddress;
	private String loadedTime;
	private final int size = 4; //4KB
	private int referenceBit;
	private long timestamp;

	public Page(int pageID) {
		this.pageID = pageID;
		this.loaded = false;
		this.referenceBit = 0;
		this.timestamp = 0;
		setLoaded(false, "");
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

	public void setLoaded(boolean loaded, String pos) {
		if (loaded){
			this.loadedTime = getCurrentTime();
			this.logicalAddress = pos;
		} else {
			this.logicalAddress = generateRandomString(10);
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

	public int getReferenceBit() {
		return referenceBit;
	}

	public void setReferenceBit(int referenceBit) {
		this.referenceBit = referenceBit;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void updateTimestamp(long timestamp) {
		this.timestamp = System.currentTimeMillis(); //usarlo en el use para actualizar tiempo de acceso
	}
}