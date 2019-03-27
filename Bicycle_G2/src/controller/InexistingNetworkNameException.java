package controller;

public class InexistingNetworkNameException extends Exception {
	
	private String name;
	
	public InexistingNetworkNameException(String name) {
		this.name = name;
	}
	
	@Override
	public String getMessage() {
		String message = "There is no network named \"" + this.name + "\".";
		return message;
	}

}
