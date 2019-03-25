package user;

import java.io.Serializable;

public class UserIDGenerator implements Serializable {
	
	private static UserIDGenerator instance = null;
	private int num;
	
	private UserIDGenerator() {}
	
	public static synchronized UserIDGenerator getInstance() {
		if (instance == null) {
			instance = new UserIDGenerator();
		}
		return instance;
		
	}
	
	public synchronized int getNextUserID() {
		return num ++;
	}

}
