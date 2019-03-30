package ui.clui;

import bike.ElectricBike;
import card.Card;
import ride.Network;
import station.Slot;
import station.Station;
import user.User;

public class CommandLineDisplay {
	
	private String display(Station s, boolean show) {
		String disp = "";
		disp += "Station: id:" + s.getId() + '\n';
		float x = (float) (Math.round(s.getP().getX()*1000)/1000.0);
		float y = (float) (Math.round(s.getP().getY()*1000)/1000.0);
		disp += '\t' + "Position: x=" + x + " y=" + y + '\n';
		disp += "Slots: " + s.getParkingSlots().size() + '\n';
		for (Slot slot : s.getParkingSlots()) {
			disp += '\t' + "id:" + slot.getId() + '\t' + (slot.isOnline() ? "Online" : "Offline") +'\t' + (slot.getisOccupied() ? "Occupied" : "Free");
			if (slot.getisOccupied()) {
				disp += '\t' + "Bike id:" + slot.getBike().getId();
				String bt = slot.getBike() instanceof ElectricBike ? "Electric" : "Mechanic";
				disp += '\t' + "Type: " + bt;
			}
			disp += '\n';
		}
		if (show) System.out.println(disp);
		return disp;
	}
	
	public String display(Station s) {
		return this.display(s, true);
	}
	
	private String display(Card c, boolean show) {
		String disp = "";
		disp += "Card: id:" + c.getId() + '\n';
		disp += '\t' + "Owner: " + c.getUser().getUserName() + '\n';
		disp += '\t' + "Type: " + c.getTypeString() + '\n';
		disp += '\t' + "Credit: " + c.getTimeCredit() + " minutes" + '\n';
		if (show) System.out.println(disp);
		return disp;
	}
	
	public String display(Card c) {
		return this.display(c, true);
	}
	
	private String display(User u, boolean show) {
		double credit = Math.round(u.getUserStat().getTotalAmount()*100)/100.0;
		String disp = "";
		disp += "User: id:" + u.getId() + '\n';
		disp += '\t' + "Name: " + u.getUserName() + '\n';
		disp += '\t' + "Total amount paid: " + credit + " euros" + '\n';
		disp += '\t' + "Total number of rides: " + u.getUserStat().getNumberRides() + '\n';
		disp += '\t' + "Total ride time: " + Math.round(u.getUserStat().getTotalTime()) + " minutes" + '\n';
		disp += '\t' + "Total credit earned: " + Math.round(u.getUserStat().getTotalCreditEarned()) + " minutes" + '\n';
		if (show) System.out.println(disp);
		return disp;
	}
	
	public String display(User u) {
		return this.display(u, true);
	}
	
	public void display(String s) {
		System.out.println(s);
	}
	
	public String display(Network net) {
		String disp = "";
		disp += "Network: " + net.getName() + '\n' + "List of stations:" + '\n';
		for (Station s : net.getStations()) {
			disp += this.display(s, false);
		}
		disp += "List of cards:" + '\n';
		for (Card c : net.getCards()) {
			disp += this.display(c, false);
		}
		disp += "List of users:" + '\n';
		for (Card c : net.getCards()) {
			disp += this.display(c.getUser(), false);
		}
		System.out.println(disp);
		return disp;
	}
	
}
