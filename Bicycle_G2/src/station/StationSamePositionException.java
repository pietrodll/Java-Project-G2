package station;

import tools.Point;

public class StationSamePositionException extends Exception {
	
	private static final long serialVersionUID = -1530901007820608772L;
	private Point position;

	public StationSamePositionException(Point position) {
		super();
		this.position = position;
		System.out.println("Could not create station because there already is a station on the position" + position);
	}

	public Point getPosition() {
		return position;
	}


	

}
