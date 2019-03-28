package station;

public class TypeStationException extends Exception {
	
	private static final long serialVersionUID = 4145615811670724618L;
	private String type;
	
	

	public TypeStationException(String type) {
		super();
		this.type = type;
	}

	public String getType() {
		return type;
	}


	
	

}
