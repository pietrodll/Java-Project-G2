package user;

public interface Observable {
	
	public void registerObserver(Observer observer);
	public void removeObserver(Observer osberver);
	public void notifyObservers();

}
