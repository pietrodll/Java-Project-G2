package station;

public class TypeStationException extends Exception {
	
	private String type;
	
	

	public TypeStationException(String type) {
		super();
		this.type = type;
	}

	public String getType() {
		return type;
	}


	
	

}
